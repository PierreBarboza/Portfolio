package school.sptech.marketplace.service.produto;

import org.springframework.stereotype.Service;
import school.sptech.marketplace.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.marketplace.domain.produto.Produto;
import school.sptech.marketplace.domain.produto.data.ProdutoRepository;
import school.sptech.marketplace.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.marketplace.service.produto.dto.ProdutoConsultaDto;
import school.sptech.marketplace.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.marketplace.service.produto.dto.mapper.ProdutoMapper;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoConsultaDto> listar() {
        return repository.findAll().stream()
                .map(ProdutoMapper::mapToConsultaDto)
                .toList();
    }

    public ProdutoConsultaDto buscarPorId(long id) {

        Produto produto = repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format("Produto de id: %d não encontrado", id))
        );

        return ProdutoMapper.mapToConsultaDto(produto);
    }

    public ProdutoConsultaDto cadastrar(ProdutoCriacaoDto dto) {

        Produto produto = ProdutoMapper.mapProdutoCriacaoDtoToDomain(dto);

        Produto produtoSalvo = repository.save(produto);

        return ProdutoMapper.mapToConsultaDto(produtoSalvo);
    }

    public ProdutoConsultaDto atualizar(long id, ProdutoAtualizacaoDto dto) {

        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException(String.format("Produto de id: %d não encontrado", id));
        }

        Produto domain = ProdutoMapper.mapProdutoAtualizacaoDtoToDomain(id, dto);

        return ProdutoMapper.mapToConsultaDto(repository.save(domain));
    }
}
