package sptech.school.pedroleitesprint2nota1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.pedroleitesprint2nota1.classes.Filme;
import sptech.school.pedroleitesprint2nota1.classes.Oscar;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    List<Filme> filmes = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilmes(){

        if(filmes.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(filmes);
        }
    }

    @GetMapping("/{indice}")
    public ResponseEntity <Filme> filmesPorIndice(@PathVariable int indice){

        if(filmes.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            if(indice >= 0 && indice < filmes.size()){
                return ResponseEntity.status(200).body(filmes.get(indice));
            }else{
                return ResponseEntity.status(400).build();
            }

        }
    }

    @PostMapping
    public ResponseEntity <Filme> addFIlme(@RequestBody Filme filmeCadastro){

        if(filmeCadastro.getAnoLancamento() > 1895 && filmeCadastro.getNome().length() >= 2){
            filmes.add(filmeCadastro);
            return ResponseEntity.status(200).body(filmeCadastro);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity <Filme> attFilme(@PathVariable int indice, @RequestBody Filme filmeCadastro){

        if(indice >= 0 && indice < filmes.size()){

            if(filmeCadastro.getAnoLancamento() > 1895 && filmeCadastro.getNome().length() >= 2){
                filmes.set(indice, filmeCadastro);
                return ResponseEntity.status(200).body(filmeCadastro);
            }else{
                return ResponseEntity.status(400).build();
            }

        }else{
            return ResponseEntity.status(400).build();
        }

    }

    @PatchMapping("/{indice}/oscars")
    public ResponseEntity<Filme> attQtdOscars(@PathVariable int indice, @RequestBody Oscar quantidade){

        if(indice >= 0 && indice < filmes.size()){

            filmes.get(indice).setQtdOscar(quantidade.getQuantidade());
            return ResponseEntity.status(200).body(filmes.get(indice));

        }else{
            return ResponseEntity.status(400).build();
        }
    }


}
