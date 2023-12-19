package atipedroleite.valendonota2.questao2.controllers;

import atipedroleite.valendonota2.questao2.classes.Produto;
import atipedroleite.valendonota2.questao2.classes.ProdutoReduzido;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    List<Produto> listProdutos = new ArrayList<>();
    List<ProdutoReduzido> listProdutosReduzido = new ArrayList<>();

    @PostMapping
    public Produto addProduto(@RequestBody Produto produto){

        listProdutos.add(produto);
        produto.setId(listProdutos.size());

        ProdutoReduzido pr = new ProdutoReduzido(produto.getNome(), produto.getPreco());
        listProdutosReduzido.add(pr);

        return produto;
    }

    @GetMapping
    public List<Produto> listaProdutos(){

        return listProdutos;
    }

    @GetMapping("/{id}")
    public Produto produtoPorId(@PathVariable int id){

        for (Produto produto : listProdutos){

            if(produto.getId() == id){

                return produto;
            }
        }

        return null;
    }

    @GetMapping("/promocao")
    public List<Produto> produtoPorPromocao(){

        List<Produto> listaPromocao = new ArrayList<>();
        for (Produto produto : listProdutos){

            if(produto.isPromocao()){

                listaPromocao.add(produto);
            }
        }

        return listaPromocao;
    }

    @GetMapping("/resumo")
    public List<ProdutoReduzido> produtoResumo(){

        return listProdutosReduzido;
    }

    @PutMapping("/{id}")
    public Produto attProduto(@PathVariable int id, @RequestBody Produto produto){

        for(int i = 0; i < listProdutos.size(); i++){

            if(listProdutos.get(i).getId() == id){
                listProdutos.set(i, produto);
                listProdutos.get(i).setId(id);
                return produto;
            }
        }

        return null;
    }

    @GetMapping("/total-avaliado")
    public double valorTotal(){

        double total = 0.0;
        for (Produto produto : listProdutos){
            total += produto.getPreco();

        }

        return total;
    }

    @GetMapping("/produtos/valor-minimo/{valor}")
    public List<Produto> produtoValorMinimo(@PathVariable double valor){

        List<Produto> listaMinimo = new ArrayList<>();
        for (Produto produto : listProdutos){

            if(produto.getPreco() >= valor){

                listaMinimo.add(produto);
            }
        }

        return listaMinimo;
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable int id){

        String resposta = null;
        for(int i = 0; i < listProdutos.size(); i++){

            if(listProdutos.get(i).getId() == id){
                resposta = String.format("Produto %s excluído com sucesso", listProdutos.get(i).getNome());
                listProdutos.remove(i);
                return resposta;
            }
        }

        return String.format("Produto de id: %d não encontrado", id);
    }

}
