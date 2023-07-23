package salus.well.projetosaluswell.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salus.well.projetosaluswell.domain.alimento.AlimentoRepository;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.comorbidade.Comorbidade;
import salus.well.projetosaluswell.domain.comorbidade.ComorbidadeAtualizar;
import salus.well.projetosaluswell.domain.comorbidade.ComorbidadeRepository;
import salus.well.projetosaluswell.domain.form.ComorbidadeForm;

import jakarta.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comorbidades")
public class ComorbidadeController {

    @Autowired
    private ComorbidadeRepository comorbidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AlimentoRepository alimentoRepository;


    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Comorbidade>> exibir(){
        List<Comorbidade> comorbidades = comorbidadeRepository.findAll();
        if(comorbidades.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(comorbidades);
    }

    @GetMapping("/busca-por-email")
    public ResponseEntity<Optional<Comorbidade>> buscaPorEmail(@RequestParam String email){
        if(comorbidadeRepository.buscaComorbidadePorEmail(email).isPresent()){
            return ResponseEntity.ok(comorbidadeRepository.buscaComorbidadePorEmail(email));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/todos-sem-vinculo")
    public ResponseEntity<List<Comorbidade>> buscaPorUsuarioSemNutricionista(){
        if(comorbidadeRepository.buscaTodosSemVinculo().isEmpty()){
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(comorbidadeRepository.buscaTodosSemVinculo());
    }

    @GetMapping("/todos-de-um-nutricionista")
    public ResponseEntity<List<Comorbidade>> buscaPorUsuarioComUmCertoNutricionista(@RequestParam Long idNutricionista){
        if(comorbidadeRepository.buscaTodosDeUmNutricionista(idNutricionista).isEmpty()){
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(comorbidadeRepository.buscaTodosDeUmNutricionista(idNutricionista));
    }

    @GetMapping("/busca-por-nome")
    public ResponseEntity<List<Comorbidade>> buscaComorbidadePorNomeUsuario(@RequestParam String nomeCliente){
        if(comorbidadeRepository.buscaComorbidadePorNomeDoCliente(nomeCliente).isEmpty()){
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(comorbidadeRepository.buscaComorbidadePorNomeDoCliente(nomeCliente));
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Comorbidade> cadastrar(@Valid @RequestBody ComorbidadeForm form){

        Comorbidade comorbidade = form.converter(clienteRepository);
        comorbidadeRepository.save(comorbidade);
        return ResponseEntity.status(201).body(comorbidade);

    }
   @PutMapping
    @Transactional
   @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ComorbidadeAtualizar> atualizar(@Valid @RequestBody ComorbidadeAtualizar dados){
        var comorbidade = comorbidadeRepository.getReferenceById(dados.id());
        comorbidade.atualizarInformacoes(dados);
        if(comorbidadeRepository.existsById(dados.id())) {
            return ResponseEntity.status(200).body(dados);
        }
        return ResponseEntity.status(404).build();
    }

}

