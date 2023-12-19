package sptech.school.projetocinema.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetocinema.dto.FilmeResumoRespostaDto;
import sptech.school.projetocinema.repository.FilmeRepository;
import sptech.school.projetocinema.domain.Filme;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Filmes", description = "Grupo de requisicoes entidade filme")
@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filme) {
        Filme filmeRegistrado = this.filmeRepository.save(filme);
        return ResponseEntity.status(201).body(filmeRegistrado);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description =
                    "Não há filmes cadastrados.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Filmes encontradas.")
    })
    @GetMapping
    public ResponseEntity<List<Filme>> listar() {

        List<Filme> filmes = this.filmeRepository.findAll();

        if (filmes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable long id) {
        return ResponseEntity.of(this.filmeRepository.findById(id));
    }

    // localhost:8080/filmes/titulo?nome=Lagoa+Azul&diretor=Steven
    @GetMapping("/titulo")
    public ResponseEntity<Filme> buscarPorNome
            (@RequestParam String nome) {
        return ResponseEntity.of(this.filmeRepository.findByNome(nome));
    }

    @GetMapping("/diretor")
    public ResponseEntity<List<Filme>> buscarPorDiretor(
            @RequestParam String nome) {

        List<Filme> filmesFiltrados =
                this.filmeRepository.findByDiretorNomeContainsIgnoreCase(nome);

        if (filmesFiltrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(filmesFiltrados);
    }

    @GetMapping("/periodo/{data}")
    public ResponseEntity<List<Filme>> buscarPorDiretor(@PathVariable LocalDate data) {

        List<Filme> filmesFiltrados =
                this.filmeRepository.findByDataLancamentoLessThanEqual(data);

        if (filmesFiltrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(filmesFiltrados);
    }

    @GetMapping("/indicados")
    public ResponseEntity<List<Filme>> buscarSomenteIndicados() {

        List<Filme> filmesFiltrados = this.filmeRepository.findByIndicacaoOscarTrue();

        if (filmesFiltrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(filmesFiltrados);
    }

    @GetMapping("/nao-indicados/quantidade")
    public ResponseEntity<Integer> contarSomenteNaoIndicados() {
        int contador = this.filmeRepository.countByIndicacaoOscarFalse();
        return ResponseEntity.status(200).body(contador);
    }

    @GetMapping("/custo-producao/{custo}")
    public ResponseEntity<List<Filme>> buscarFilmesComCustoAlto(@PathVariable double custo) {

        List<Filme> filmesFiltrados = this.filmeRepository.findByCustoProducaoGreaterThan(custo);

        if (filmesFiltrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(filmesFiltrados);
    }

    @GetMapping("/contagem/indicados")
    public ResponseEntity<Integer> contagemIndicados() {

        Integer contagem = this.filmeRepository.contagemSomenteIndicados();


        return ResponseEntity.status(200).body(contagem);
    }

    @GetMapping("/custo/media")
    public ResponseEntity<Double> contagemMedia() {

        double media = this.filmeRepository.mediaCustoProducao();


        return ResponseEntity.status(200).body(media);
    }

    @GetMapping("/contagem/diretor/oscars")
    public ResponseEntity<Integer> contagemOscarsDiretor(@RequestParam String nome) {

        int media = this.filmeRepository.contagemIndicadosPorDiretor(nome);


        return ResponseEntity.status(200).body(media);
    }

    @GetMapping("/listar/resumidos")
    public ResponseEntity<List<FilmeResumoRespostaDto>> listarFilmesResumidos() {




        return ResponseEntity.status(200).body(this.filmeRepository.listarFilmesResumido());
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletarPorId(@RequestParam long id) {

        this.filmeRepository.removerPorId(id);


        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/id")
    public ResponseEntity<Filme> atualizarPorId(@RequestParam long id, @RequestParam String nome) {

        if(filmeRepository.existsById(id)){

            return ResponseEntity.status(200).body(this.filmeRepository.atualizarPorId(id, nome));
        }

        return ResponseEntity.status(404).build();
    }
}
