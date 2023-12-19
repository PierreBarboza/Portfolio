package sptech.school.nota03.sprint02.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nota03.sprint02.biblioteca.classe.Livro;
import sptech.school.nota03.sprint02.biblioteca.repository.LivroRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<Livro> addLivro(@RequestBody Livro livro){

        return ResponseEntity.status(201).body(this.livroRepository.save(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros(){

        if (this.livroRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(this.livroRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Livro> procurarPorId(@PathVariable Long id){

        return ResponseEntity.of(this.livroRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> attLivro(@RequestBody Livro livro, @PathVariable Long id){

        livro.setId(id);
        if (livroRepository.existsById(id)){

            Livro livroAtt = livroRepository.save(livro);
            return ResponseEntity.status(200).body(livroAtt);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){

        if (livroRepository.existsById(id)){

            livroRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/escritor")
    public ResponseEntity<List<Livro>> buscarPorEscritor(@RequestParam String nome) {

        List<Livro> livros = this.livroRepository.findBynomeEscritorIgnoreCase(nome);

        if (livros.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(livros);
    }

    @GetMapping("/{data}")
    public ResponseEntity<List<Livro>> buscarPorDtaLancamento(@PathVariable LocalDate data) {

        List<Livro> livros = this.livroRepository.findBydtaLancamentoLessThanEqual(data);


        if (livros.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(livros);
    }

    @GetMapping("/qtd")
    public ResponseEntity<List<Livro>> buscarPorQtdPag(@PathVariable int qtd){

        List<Livro> livros = this.livroRepository.findByqtdPagLessThanEqual(qtd);

        if (livros.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(livros);
    }

    @GetMapping("/nome")
    public ResponseEntity<Livro> buscarPorNomeLivro(@PathVariable String nome){

        Livro livro = this.livroRepository.findBynomeCapaIgnoreCase(nome);


        return ResponseEntity.status(200).body(livro);
    }
}

