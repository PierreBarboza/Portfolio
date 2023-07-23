package salus.well.projetosaluswell.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.alimento.AlimentoRepository;
import salus.well.projetosaluswell.domain.email.EmailService;
import salus.well.projetosaluswell.exception.AlimentoNaoEncontradoException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoRepository alimentoRepository;



    private ListObj<Alimento> alimentos = new ListObj<>(20);
    private FilaObj<Alimento> alimentosFila = new FilaObj<>(20);



    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Alimento>> buscarTodosAlimentos(){
        if(alimentoRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(alimentoRepository.findAll());
    }



    @GetMapping("/busca-binaria-calorias")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Object> buscaBinariaCalorias(@RequestParam double calorias){
        if(alimentos.buscaBinaria(calorias) == null){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(alimentos.buscaBinaria(calorias));
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/ordenacao-calorias")
    public ResponseEntity<Object[]> buscarTodosAlimentosOrdenados(){
        if(alimentoRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(alimentos.ordenarPorCaloria());
    }

    @GetMapping("/diabete")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Alimento>> indicacaoParaDiabetes(){
        if(alimentoRepository.findByDiabeteTrue().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(alimentoRepository.findByDiabeteTrue());
    }

    @GetMapping("/colesterol")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Alimento>> indicacaoParaColesterol(){
        if(alimentoRepository.findByColesterolTrue().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(alimentoRepository.findByColesterolTrue());
    }

    @GetMapping("/hipertensao")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Alimento>> indicacaoParaHipertensao(){
        if(alimentoRepository.findByHipertensaoTrue().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(alimentoRepository.findByHipertensaoTrue());
    }

    @GetMapping("/tipo-alimento/{tipo}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Alimento>> buscaPorTipo(@PathVariable String tipo){
        if(alimentoRepository.findByTipoIgnoreCase(tipo).isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(alimentoRepository.findByTipoIgnoreCase(tipo));
    }

    @GetMapping("/nome-alimento/{nome}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Optional<Alimento>> buscaPorNome(@PathVariable String nome){
        if(alimentoRepository.findByNomeIgnoreCase(nome).isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(alimentoRepository.findByNomeIgnoreCase(nome));



    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Alimento> cadastroAlimento(@Valid @RequestBody Alimento alimento) throws EmailException {
       for(int i = 0; i < alimentoRepository.findAll().size(); i++){
           if(alimentoRepository.findAll().get(i).getNome().equals(alimento.getNome())){
               return ResponseEntity.status(409).build();
           }
       }

        double caloriasTotal = 0;

        if(alimento.getCarboidrato() > 0){
            caloriasTotal += alimento.getCarboidrato() * 4;
        }

        if(alimento.getProteina() > 0){
            caloriasTotal += alimento.getProteina() * 4;
        }

        if(alimento.getGorduraTotal() > 0){
            caloriasTotal += alimento.getGorduraTotal() * 9;
        }

        alimento.setCalorias(caloriasTotal);

        alimentoRepository.save(alimento);
        alimentos.adiciona(alimento);

        alimentosFila.insert(alimento);
        alimentosFila.gravaArquivoCsv("Alimentos");

        alimentoRepository.setRelatorio(
                alimento.getId(),  ("nome;"+ "tipo;" + "proteina;" + "carboidrato;" + "gordura total;" + "calorias;\n"
                        + alimento.getNome() + ";"  +alimento.getTipo()+ ";" +alimento.getProteina()+ ";"
                        +alimento.getCarboidrato()+  ";"   +alimento.getGorduraTotal()+ ";"
                +alimento.getCalorias()+  ";").getBytes());
        return ResponseEntity.status(201).body(alimento);
    }





    @GetMapping(value = "/relatorio/{id}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<byte[]> getRelatorio(@PathVariable Long id) {
        if (!alimentoRepository.existsById(id)) {
            throw new AlimentoNaoEncontradoException();
        }

        byte[] relatorio = alimentoRepository.getRelatorio(id);

        // esse header "content-disposition" indica o nome do arquivo em caso de download em navegador
        return ResponseEntity.status(200).header("content-disposition", "attachment; filename=\"Aliments.csv\"").body(relatorio);
    }




}
