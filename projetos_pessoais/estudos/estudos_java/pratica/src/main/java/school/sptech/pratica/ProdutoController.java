package school.sptech.pratica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping()
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto){

        if(produto.getNome().length() > 2 && produto.getPrecoUnitario() >= 0.01 && produto.getQuantidadeEstoque() >= 1){

            this.produtoRepository.save(produto);
            return ResponseEntity.status(200).body(produto);
        }else{

            return ResponseEntity.status(404).build();
        }
    }


    @GetMapping()
    public ResponseEntity<List<Produto>> listarProdutos(){

        if(this.produtoRepository.findAll().isEmpty()){

            return ResponseEntity.status(204).build();
        }else{

            return ResponseEntity.status(200).body(this.produtoRepository.findAll());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){

        return ResponseEntity.of(this.produtoRepository.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePorId(@PathVariable Long id){
        Optional<Produto> produto = this.produtoRepository.findById(id);
        if (produto.isEmpty()){

            return ResponseEntity.status(404).build();
        }else{

            this.produtoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

    }

}
