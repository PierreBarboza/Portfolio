package school.sptech.marketplace.controller.avaliacao;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.sptech.marketplace.domain.produto.avaliacao.AvaliacaoClienteProduto;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.AvaliacaoClienteProdutoAlteracaoDto;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.AvaliacaoClienteProdutoCriacaoDto;
import school.sptech.marketplace.service.avaliacao.AvaliacaoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoCompradorProdutoController {

    private final AvaliacaoService service;

    public AvaliacaoCompradorProdutoController(AvaliacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoClienteProduto>> listarAvaliacoes() {
        List<AvaliacaoClienteProduto> avaliacoes = service.listar();

        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoClienteProduto> listarAvaliacaoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoClienteProduto> criarAvaliacao(
            @RequestBody @Valid AvaliacaoClienteProdutoCriacaoDto novaAvaliacao) {

        AvaliacaoClienteProduto avaliacao = service.cadastrar(novaAvaliacao);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(avaliacao.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(avaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoClienteProduto> editarAvaliacao(
            @PathVariable Long id,
            @RequestBody @Valid AvaliacaoClienteProdutoAlteracaoDto avaliacao) {
        return ResponseEntity.ok((service.atualizar(id, avaliacao)));
    }
}
