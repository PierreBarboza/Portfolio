package school.sptech.marketplaceresumido.service.produto;
import org.springframework.stereotype.Service;
import school.sptech.marketplaceresumido.domain.Produto;
import school.sptech.marketplaceresumido.domain.data.ProdutoRepository;
import school.sptech.marketplaceresumido.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.marketplaceresumido.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.marketplaceresumido.service.produto.dto.ProdutoConsultaDto;
import school.sptech.marketplaceresumido.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.marketplaceresumido.service.produto.dto.mapper.ProdutoMapper;

import java.util.List;


//Componentes do Spring
/*
    @Component
    @Controller
    @RestController
    @Repository
    @Service
 */
@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoConsultaDto> listar(){
        List<Produto> produtos = this.repository.findAll();

        return  produtos.stream()
                .map(ProdutoMapper::mapProdutoToProdutoConsultaDto)
                .toList();

    }

    public ProdutoConsultaDto buscarPorId(long id){

        Produto produto = this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto Não encontrado"));

        return ProdutoMapper.mapProdutoToProdutoConsultaDto(produto);
    }

    public ProdutoConsultaDto cadastrar(ProdutoCriacaoDto dto){

        Produto produto = ProdutoMapper.mapProdutoCriacaoDtoToProduto(dto);

        Produto produtoSalvo = this.repository.save(produto);

        return ProdutoMapper.mapProdutoToProdutoConsultaDto(produtoSalvo);
    }

    public ProdutoConsultaDto atualizar(long id, ProdutoAtualizacaoDto  dto){

        if (!this.repository.existsById(id)){

            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }

        Produto produto = ProdutoMapper.mapProdutoAtualizacaoDtoToProduto(id, dto);

        Produto produtoSalvo = this.repository.save(produto);

        return ProdutoMapper.mapProdutoToProdutoConsultaDto(produtoSalvo);
    }
}
