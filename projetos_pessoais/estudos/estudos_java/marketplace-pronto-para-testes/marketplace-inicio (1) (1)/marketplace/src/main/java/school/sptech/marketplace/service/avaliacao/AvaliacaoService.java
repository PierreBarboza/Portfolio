package school.sptech.marketplace.service.avaliacao;

import org.springframework.stereotype.Service;
import school.sptech.marketplace.domain.cliente.Cliente;
import school.sptech.marketplace.domain.cliente.data.ClienteRepository;
import school.sptech.marketplace.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.marketplace.domain.produto.Produto;
import school.sptech.marketplace.domain.produto.avaliacao.AvaliacaoClienteProduto;
import school.sptech.marketplace.domain.produto.avaliacao.data.AvaliacaoClienteProdutoRepository;
import school.sptech.marketplace.domain.produto.data.ProdutoRepository;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.AvaliacaoClienteProdutoAlteracaoDto;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.AvaliacaoClienteProdutoCriacaoDto;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.mapper.AvaliacaoMapper;

import java.util.List;

@Service
public class AvaliacaoService {

    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    private final AvaliacaoClienteProdutoRepository repository;


    public AvaliacaoService(
            AvaliacaoClienteProdutoRepository repository,
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository
    ) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<AvaliacaoClienteProduto> listar() {
        return this.repository.findAll();
    }

    public AvaliacaoClienteProduto buscarPorId(long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Avaliacao de id %d nao encontrada", id)
                )
        );
    }
    public AvaliacaoClienteProduto cadastrar(AvaliacaoClienteProdutoCriacaoDto novaAvaliacao) {

        final long produtoId = novaAvaliacao.getProdutoId();
        final long compradorId = novaAvaliacao.getCompradorId();

        Cliente cliente = clienteRepository.findById(compradorId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Compradador de id: %d nao encontrado", compradorId))
        );

        Produto produto = produtoRepository.findById(novaAvaliacao.getProdutoId()).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Produto de id: %d nao encontrado", produtoId))
        );

        AvaliacaoClienteProduto paraRegistrar
                = AvaliacaoMapper.mapCriacaoDtoToDomain(novaAvaliacao, produto, cliente);

        return this.repository.save(paraRegistrar);
    }


    public AvaliacaoClienteProduto atualizar(long id, AvaliacaoClienteProdutoAlteracaoDto dto) {

        AvaliacaoClienteProduto avaliacaoClienteProduto = this.repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format("Avaliacao de id %d nao encontrada", id)
                )
        );

        AvaliacaoClienteProduto paraRegistrar = AvaliacaoMapper.mapAlteracaoDtoToDomain(
                id,
                dto,
                avaliacaoClienteProduto.getProduto(),
                avaliacaoClienteProduto.getComprador()
        );

        return repository.save(paraRegistrar);
    }
}
