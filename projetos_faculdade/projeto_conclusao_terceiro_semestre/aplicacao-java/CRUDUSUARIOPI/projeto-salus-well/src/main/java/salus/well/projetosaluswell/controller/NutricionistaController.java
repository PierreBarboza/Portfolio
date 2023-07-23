package salus.well.projetosaluswell.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteDto;
import salus.well.projetosaluswell.domain.nutricionista.*;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaLoginDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaTokenDto;
import salus.well.projetosaluswell.service.NutricionistaService;
import salus.well.projetosaluswell.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/nutricionistas")

public class NutricionistaController {
    @Autowired
    private NutricionistaRepository repository;

    @Autowired
    private NutricionistaService nutricionistaService;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Nutricionista>> exibir() {

        return ResponseEntity.status(200).body(repository.findByAtivoTrue());
    }

    @GetMapping("/search/{nome}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Nutricionista>> exibirNomes(@PathVariable String nome) {
        try{
            List<Nutricionista> nutricionistas = nutricionistaService.nutricionistasNome(nome);
            return ResponseEntity.ok(nutricionistas);
        }catch (Exception e){
                return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Nutricionista> exibirPorId(@PathVariable Long id){
        return ResponseEntity.of(repository.findById(id));
    }



    @PostMapping("/cadastrar")
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Nutricionista> cadastrar(@RequestBody @Valid NutricionistaCadastro dados){
        if(repository.findByEmailIgnoreCase(dados.email()).isPresent()){
            return ResponseEntity.status(409).build();
        }

        var nutricionista = new Nutricionista(dados);
        nutricionistaService.criar(dados);


        return ResponseEntity.status(201).body(nutricionista);
    }

    @PostMapping("/login")
    public ResponseEntity<NutricionistaTokenDto> login(@RequestBody NutricionistaLoginDto usuarioLoginDto){
        NutricionistaTokenDto usuarioToken = nutricionistaService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(201).body(usuarioToken);
    }


    @PutMapping
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<NutricionistaAtualizar> atualizar(@RequestBody NutricionistaAtualizar dados){
        var nutricionista = repository.getReferenceById(dados.id());
        nutricionista.atualizarInformacoes(dados);

        return ResponseEntity.status(200).body(dados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
//        if(repository.findByAtivoTrueId(id).isAtivo()){
//            return ResponseEntity.status(404).build();
//        }

        if(repository.existsById(id)) {
            var nutricionista = repository.getReferenceById(id);
            nutricionista.excluir();

            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/{id}/{avaliacao}")
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Cliente> avaliar(@PathVariable Long id, @PathVariable double avaliacao){
        if(repository.findById(id).isPresent()){
            repository.findById(id).get().setAvaliacao(repository.findById(id).get().avaliacaoTotal() + avaliacao);
            repository.findById(id).get().setContagemAvaliacao(repository.findById(id).get().getContagemAvaliacao() + 1);

            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();

    }


}
