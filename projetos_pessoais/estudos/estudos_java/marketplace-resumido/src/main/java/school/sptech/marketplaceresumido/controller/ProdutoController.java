package school.sptech.marketplaceresumido.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.marketplaceresumido.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.marketplaceresumido.service.produto.dto.ProdutoConsultaDto;
import school.sptech.marketplaceresumido.service.produto.dto.ProdutoCriacaoDto;
import school.sptech.marketplaceresumido.service.produto.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoService produtoService;

    //Funciona igual o @Autowired o final é para obdecer a lei de imutabilidade.
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoConsultaDto>> listar() {

        List<ProdutoConsultaDto> produtos = this.produtoService.listar();

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoConsultaDto> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(this.produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoConsultaDto> criar(@RequestBody @Valid ProdutoCriacaoDto produtoCriacaoDto) {

        return ResponseEntity.created(null).body(this.produtoService.cadastrar(produtoCriacaoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoConsultaDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoAtualizacaoDto produtoAtualizacaoDto) {

        return ResponseEntity.ok(this.produtoService.atualizar(id, produtoAtualizacaoDto));
    }
}
