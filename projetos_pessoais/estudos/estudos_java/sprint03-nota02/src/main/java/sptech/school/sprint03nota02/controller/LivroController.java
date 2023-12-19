package sptech.school.sprint03nota02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.sprint03nota02.domain.Escritor;
import sptech.school.sprint03nota02.domain.Livro;
import sptech.school.sprint03nota02.dto.EscritorResumidoDto;
import sptech.school.sprint03nota02.dto.LivroAtualizarDto;
import sptech.school.sprint03nota02.dto.LivroNovoDto;
import sptech.school.sprint03nota02.dto.LivroResumido;
import sptech.school.sprint03nota02.repository.EscritorRepository;
import sptech.school.sprint03nota02.repository.LivroRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EscritorRepository escritorRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {

        List<Livro> livrosLista = this.livroRepository.findAll();

        if (livrosLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(livrosLista);
    }

    @GetMapping("/escritores")
    public ResponseEntity<Escritor> buscarPorEscritor(@RequestParam String nomeEscritor) {

        return ResponseEntity.of(this.escritorRepository.findByNomeEscritor(nomeEscritor));
    }

    @GetMapping("/escritores/nascimento/{dataNasc}")
    public ResponseEntity<List<Escritor>> listarEscritoresPorNascimento(@PathVariable LocalDate dataNasc){

        List<Escritor> listaEscritores = escritorRepository.findByDataNascLessThanEqual(dataNasc);

        if (listaEscritores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(listaEscritores);
    }

    @GetMapping("/escritores/resumidos")
    public ResponseEntity<List<EscritorResumidoDto>> escritoresResumidos() {

        List<EscritorResumidoDto> escritoresResumidos = this.escritorRepository.listarEscritorResumidos();

        if (escritoresResumidos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(escritoresResumidos);
    }

    @GetMapping("/bestsellers")
    public ResponseEntity<List<Livro>> buscarSomenteBestSellers() {

        List<Livro> livrosBestSellers = this.livroRepository.findByBestSellerTrue();

        if (livrosBestSellers.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(livrosBestSellers);
    }

    @GetMapping("/resumidos")
    public ResponseEntity<List<LivroResumido>> livrosResumidos() {

        List<LivroResumido> livrosResumidos = this.livroRepository.listarLivrosResumidos();

        if (livrosResumidos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(livrosResumidos);
    }

    @PostMapping
    public ResponseEntity<Void> livroAdd(@RequestBody LivroNovoDto livroNovoDto) {
        livroRepository.addLivro(livroNovoDto.getNomeLivro(), livroNovoDto.getEscritor(), livroNovoDto.isBestSeller());

        return ResponseEntity.status(200).build();
    }

    @PutMapping
    public ResponseEntity<LivroAtualizarDto> livrosAtt(@RequestParam Long id, @RequestBody LivroAtualizarDto livroAtualizarDto) {

        if(livroRepository.existsById(id)){
            this.livroRepository.atualizarLivro(id, livroAtualizarDto.getNomeLivro(), livroAtualizarDto.isBestSeller());
            return ResponseEntity.status(200).body(livroAtualizarDto);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPorId(@RequestParam long id) {

        if(escritorRepository.existsById(id)){
            this.livroRepository.removerPorId(id);
        }

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/escritor")
    public ResponseEntity<Void> deletarEscritorPorId(@RequestParam long id) {

        if(escritorRepository.existsById(id)){
            this.escritorRepository.removerEscritorPorId(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }


}
