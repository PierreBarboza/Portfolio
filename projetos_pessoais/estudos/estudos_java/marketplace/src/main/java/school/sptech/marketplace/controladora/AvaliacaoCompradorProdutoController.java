package school.sptech.marketplace.controladora;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.marketplace.dominio.AvaliacaoCompradorProduto;
import school.sptech.marketplace.dominio.Comprador;
import school.sptech.marketplace.dominio.Produto;
import school.sptech.marketplace.dto.AvaliacaoCompradorProdutoAlteracaoDto;
import school.sptech.marketplace.dto.AvaliacaoCompradorProdutoCriacaoDto;
import school.sptech.marketplace.repositorio.AvaliacaoCompradorProdutoRepository;
import school.sptech.marketplace.repositorio.CompradorRepository;
import school.sptech.marketplace.repositorio.ProdutoRepository;
import school.sptech.marketplace.service.AvaliacaoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoCompradorProdutoController {

    @Autowired
    private AvaliacaoCompradorProdutoRepository avaliacaoRepository;

    @Autowired
    private CompradorRepository compradorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AvaliacaoService service;

    @GetMapping
    public ResponseEntity<List<AvaliacaoCompradorProduto>> listarAvaliacoes() {

        List<AvaliacaoCompradorProduto> avaliacoes = service.listar();

        if (avaliacoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoCompradorProduto> listarAvaliacaoPorId(@PathVariable Long id) {
       AvaliacaoCompradorProduto avaliacaoCompradorProduto = this.service.buscarPorId(id);

        return ResponseEntity.status(200).build();
    }

    @PostMapping
    public ResponseEntity<AvaliacaoCompradorProduto> criarAvaliacao(
            @RequestBody @Valid AvaliacaoCompradorProdutoCriacaoDto novaAvaliacao) {

        Optional<Comprador> comprador = compradorRepository.findById(novaAvaliacao.getCompradorId());
        Optional<Produto> produto = produtoRepository.findById(novaAvaliacao.getProdutoId());

        if (!comprador.isPresent() || !produto.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        AvaliacaoCompradorProduto avaliacao = new AvaliacaoCompradorProduto(
                comprador.get(),
                produto.get(),
                novaAvaliacao.getNota(),
                novaAvaliacao.getComentario()
        );

        return ResponseEntity.status(201).body(avaliacaoRepository.save(avaliacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoCompradorProduto> editarAvaliacao(
            @PathVariable Long id,
            @RequestBody @Valid AvaliacaoCompradorProdutoAlteracaoDto avaliacao) {

        Optional<AvaliacaoCompradorProduto> avaliacaoOpt =
                avaliacaoRepository.findById(id);

        if (avaliacaoOpt.isPresent()) {
            AvaliacaoCompradorProduto avaliacaoParaEditar = avaliacaoOpt.get();

            if (avaliacao.getComentario() != null) {
                avaliacaoParaEditar.setComentario(avaliacao.getComentario());
            }
            if (avaliacao.getNota() != null) {
                avaliacaoParaEditar.setNota(avaliacao.getNota());
            }

            return ResponseEntity.status(200).body(avaliacaoRepository.save(avaliacaoParaEditar));
        }

        return ResponseEntity.status(404).build();
    }
}
