package school.sptech.avaliacaocontinuadadois.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.avaliacaocontinuadadois.domain.Produto;
import school.sptech.avaliacaocontinuadadois.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;

/*
    TODO:
     - A equipe relatou ter dificuldades em utilizar ORM e Dynamic finders, Você poderia ajudá-los?
*/
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/contagem")
    public ResponseEntity<Long> contagemGeral() {

        return ResponseEntity.status(200).body(this.produtoRepository.count());
    }

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/contagem")
    public ResponseEntity<Long> contagemPorCategoria(@RequestParam String nome) {

        return ResponseEntity.status(200).body(this.produtoRepository.countByCategoriaIgnoreCase(nome));
    }

    // Deve ser resolvido apenas com dynamic finder e checagem de lista vazia
    @GetMapping("/categorias")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@RequestParam String nome) {

        List<Produto> listaPorCategoria = this.produtoRepository.findByCategoriaIgnoreCase(nome);

        if (listaPorCategoria.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaPorCategoria);
    }

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/maior-preco")
    public ResponseEntity<Produto> buscarPorCategoriaMaiorPreco(@RequestParam String nome) {

        Produto produtoMaisCaro = this.produtoRepository.findFirstByCategoriaIgnoreCaseOrderByPrecoDesc(nome);

        return ResponseEntity.status(200).body(produtoMaisCaro);
    }

    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/menor-preco")
    public ResponseEntity<Produto> buscarPorCategoriaMenorPreco(@RequestParam String nome) {

        Produto produtoMaisBarato = this.produtoRepository.findFirstByCategoriaIgnoreCaseOrderByPreco(nome);

        return ResponseEntity.status(200).body(produtoMaisBarato);
    }


    // Deve ser resolvido apenas com dynamic finder
    @GetMapping("/categorias/contagem/promocoes")
    public ResponseEntity<Long> contarPorCategoriaPromocao(@RequestParam String nome) {

        long produtosPromocao = this.produtoRepository.countByCategoriaIgnoreCaseAndPromocaoTrue(nome);

        return ResponseEntity.status(200).body(produtosPromocao);
    }

    // Esse endpoint exige dynamic finder + uso de laco de repeticao ou uso de stream para cálculo
    @GetMapping("/total-estoque/data")
    public ResponseEntity<Double> buscarTotalEstoquePorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        List<Produto> listaProdutos = this.produtoRepository.findByDataCriacaoBetween(inicio, fim);

        double total=0.0;
        for (Produto produto : listaProdutos){
            total += produto.getQuantidade() * produto.getPreco();
        }

        return ResponseEntity.status(200).body(total);
    }
}
