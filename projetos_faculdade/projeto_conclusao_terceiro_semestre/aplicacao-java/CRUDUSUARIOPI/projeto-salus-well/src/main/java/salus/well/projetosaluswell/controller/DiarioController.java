package salus.well.projetosaluswell.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.alimento.AlimentoRepository;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.diario.DiarioAlimentar;
import salus.well.projetosaluswell.domain.diario.DiarioRepository;
import salus.well.projetosaluswell.domain.form.DiarioAlimentarForm;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaRepository;

import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import salus.well.projetosaluswell.service.DiarioService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diarios")
public class DiarioController {
    @Autowired
    private DiarioRepository diarioRepository;
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private NutricionistaRepository nutricionistaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    private PilhaObj<DiarioAlimentar> pilhaDiario = new PilhaObj<>(20);

    private final DiarioService diarioService;

    public DiarioController(DiarioService diarioService) {
        this.diarioService = diarioService;
    }


    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<DiarioAlimentar>> listar(){

        if(diarioRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(diarioRepository.findAll());
    }

    @GetMapping("/listar-consumir")
    public ResponseEntity<List<DiarioAlimentar>> listarPorId(@RequestParam Long id, LocalDate data){
        if(diarioRepository.alimentosConsumirCliente(id,data).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(diarioRepository.alimentosConsumirCliente(id,data));
    }

    @PutMapping("/consumido")
    public ResponseEntity<DiarioAlimentar> diarioConsumido(@RequestParam Long idCliente, Long idDiario){
        Optional<DiarioAlimentar> optDiario = diarioRepository.diarioNaoConsumidoPorId(idCliente,idDiario);

        if(optDiario.isPresent()){

            DiarioAlimentar diarioAlimentar = optDiario.get();

            diarioAlimentar.setId(diarioAlimentar.getId());
            diarioAlimentar.setConsumido(true);
            diarioAlimentar.setDataConsumida(LocalDateTime.now());

            diarioRepository.save(diarioAlimentar);

            pilhaDiario.empilhar(diarioAlimentar);

            return ResponseEntity.ok(diarioAlimentar);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/desfazer-consumido")
    public ResponseEntity<DiarioAlimentar> desfazerConsumido() {

        if(!pilhaDiario.estaVazia()){
            DiarioAlimentar diarioAlimentar = pilhaDiario.peek();

            diarioAlimentar.setConsumido(false);
            diarioAlimentar.setDataConsumida(null);
            diarioAlimentar.setId(pilhaDiario.desempilhar().getId());

            diarioRepository.save(diarioAlimentar);

            return ResponseEntity.ok(diarioAlimentar);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/historico")
    public ResponseEntity<List<DiarioAlimentar>> historico(@RequestParam Long idCliente){
        if(diarioRepository.diarioConsumidoHistorico(idCliente).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(diarioRepository.diarioConsumidoHistorico(idCliente));
    }

// o QUE CONSUMIR EM DETERMINADO DIA

    @PostMapping

    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DiarioAlimentarForm> cadastrar(@RequestBody @Valid DiarioAlimentarForm form) {
        DiarioAlimentar diario = form.converter(nutricionistaRepository, clienteRepository);
        diarioRepository.save(diario);
        return ResponseEntity.status(201).body(form);
    }


   /* @GetMapping("/busca-por-ids")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Alimento>> buscarAlimentosPorNome(@RequestParam List<Long> ids){
        List<Alimento> alimentos = diarioService.findAlimentosByIdsInDiarios(ids);
        return new ResponseEntity<>(alimentos, HttpStatus.OK);
    }*/
}
