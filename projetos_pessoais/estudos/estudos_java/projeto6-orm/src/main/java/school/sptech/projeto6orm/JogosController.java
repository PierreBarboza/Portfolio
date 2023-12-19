package school.sptech.projeto6orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
public class JogosController {

    @Autowired //Ponto de injeção de dependencia, delegamos ao spring a implementação concreta da interface.
    private JogoRepository jogoRepository;

    @GetMapping
    public ResponseEntity<List<Jogo>> listarJogos(){

        List<Jogo> jogos = this.jogoRepository.findAll();

        if(jogos.isEmpty()){

            return ResponseEntity.status(204).build();
        }else{

            return ResponseEntity.status(200).body(jogos);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Jogo> jogoPorIndice(@PathVariable Long id){

        return ResponseEntity.of(this.jogoRepository.findById(id)); // Esse .of vai validar se dentro da caixa do option tem algo, se tiver ele retorna o objeto e status 200
        // Caso não tenha nada ele retorna o status code 404
        // Esse .of resume tudo a baixo

        /*
        Optional<Jogo> jogoOpt = this.jogoRepository.findById(id);

        if(jogoOpt.isPresent()){

            Jogo jogoEncontrado = jogoOpt.get();
            return ResponseEntity.status(200).body(jogoEncontrado);
        }else{

            return ResponseEntity.status(404).build();
        }
        */

    }


    @PostMapping()
    public ResponseEntity<Jogo> addJogo(@RequestBody Jogo jogo){

        Jogo jogoRegistrado = this.jogoRepository.save(jogo);
        return ResponseEntity.status(201).body(jogoRegistrado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorIndice(@PathVariable Long id){


        if(this.jogoRepository.existsById(id)){

            this.jogoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }else{

            return ResponseEntity.status(404).build();
        }



    }


    @PostMapping("/[id]")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogo){

        if(this.jogoRepository.existsById(id)){

            jogo.setId(id);
            Jogo jogoAtualizado = this.jogoRepository.save(jogo);
            return ResponseEntity.status(200).body(jogoAtualizado);
        }else{

            return ResponseEntity.status(404).build();
        }
    }


    @GetMapping("/contagem")
    public ResponseEntity<Long> contagem(){

        return ResponseEntity.status(200).body(this.jogoRepository.count());
    }


}
