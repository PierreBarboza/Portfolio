package school.sptech.marketplace.controller.produto;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.marketplace.service.produto.ProdutoService;
import school.sptech.marketplace.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.marketplace.service.produto.dto.ProdutoConsultaDto;
import school.sptech.marketplace.service.produto.dto.ProdutoCriacaoDto;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoConsultaDto>> listar() {

        List<ProdutoConsultaDto> produtos = this.service.listar();

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoConsultaDto> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(this.service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoConsultaDto> cadastrar(@Valid ProdutoCriacaoDto dto) {
        return ResponseEntity.ok(this.service.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoConsultaDto> atualizar(@PathVariable long id, @Valid ProdutoAtualizacaoDto dto) {
        return ResponseEntity.ok(this.service.atualizar(id, dto));
    }
}
