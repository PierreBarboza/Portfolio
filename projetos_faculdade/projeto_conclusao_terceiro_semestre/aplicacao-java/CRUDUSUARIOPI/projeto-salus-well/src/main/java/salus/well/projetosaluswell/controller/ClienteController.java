package salus.well.projetosaluswell.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import salus.well.projetosaluswell.domain.cliente.*;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioLoginDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioTokenDto;
import salus.well.projetosaluswell.exception.AlimentoNaoEncontradoException;
import salus.well.projetosaluswell.service.UsuarioService;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UsuarioService usuarioService;
    ListObj<Cliente> clientes = new ListObj<>(10);
    ListObj<Object> listObj = new ListObj<>(50);
    List<Cliente> clientes1 = new ArrayList<>();
    List<ClienteDto> dtos = new ArrayList<>();
    @Transactional
    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Cliente> cadastroUsuario(@RequestBody @Valid ClienteCadastro dados){
        if(repository.findByEmailIgnoreCase(dados.email()).isPresent()){
            return ResponseEntity.status(409).build();
        }

        var cliente = new Cliente(dados);
        clientes.adiciona(cliente);
        usuarioService.criar(dados);


        clientes1.add(cliente);
        listObj.gravaArquivoTxt(clientes1, "cliente.txt");

        System.out.println(Arrays.toString(cliente.getNome().getBytes()));

        return ResponseEntity.status(201).body(cliente);

    }

    @GetMapping(value = "/txt/{id}", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<byte[]> getRelatorio(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new AlimentoNaoEncontradoException();
        }

        byte[] relatorio = repository.getRelatorio(id);

        // esse header "content-disposition" indica o nome do arquivo em caso de download em navegador
        return ResponseEntity.status(200).header("content-disposition", "attachment; filename=\"cliente.txt\"").body(relatorio);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(201).body(usuarioToken);
    }

    @Transactional
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Cliente>> exibirUsuarios(){
        //Retornar esse finbyativo em um vetor para ordenar ele
//    List<Cliente> clientela = repository.findByAtivoTrue();

        if(repository.findByAtivoTrue().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(repository.findByAtivoTrue()) ;
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Cliente> exibirPorId(@PathVariable Long id){

        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/busca-por-nome")
    public ResponseEntity<List<Cliente>> buscaPorNome(@RequestParam String nome){
        if(repository.buscaPorNome(nome).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.buscaPorNome(nome));
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("busca-unico-nutricionista")
    public ResponseEntity<Optional<Nutricionista>> buscaNutricionista(@RequestParam Long idCliente){
        if(repository.findNutricionistaByClienteId(idCliente).isPresent()){
            return ResponseEntity.ok(repository.findNutricionistaByClienteId(idCliente));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/teste")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> teste(){
        listObj.leArquivoTxt("cliente.txt");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Resource> downloadFile() throws EmailException {

        Resource resource = new FileSystemResource("/cliente.txt");

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cliente.txt");


        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);

        if(repository.existsById(id)){
            cliente.excluir();
            return ResponseEntity.status(200).build();
        }
       return ResponseEntity.status(404).build();



    }



    @PutMapping
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteAtualizar> atualizar(@RequestBody ClienteAtualizar dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.status(200).body(dados);
    }


    @PutMapping("/mensagem/{idCliente}/{idNutri}")
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> updateNutricionista(@PathVariable Long idCliente, @PathVariable Long idNutri){
        try {
            usuarioService.atualizarNutri(idCliente, idNutri);
            return ResponseEntity.ok("Nutricionista add com sucesso");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/desvincular/{id}")
    @Transactional
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> desvincular(@PathVariable Long id){
        try {
            usuarioService.desvincularNutri(id);
            return ResponseEntity.ok("Desvinculado com sucesso");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }



    }

    @GetMapping("/sem-vinculo")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Cliente>> exibirSemVinculo(){
        return ResponseEntity.ok(repository.clientesSemVinculo());
    }

    @GetMapping("/com-vinculo/{idNutri}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Cliente>> clientesDeUmNutri(Long idNutri){
        return ResponseEntity.ok(repository.findAllByNutricionistaId(idNutri));
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

    @GetMapping("com-vinculo/por-nome/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteDto>> listarNomes(@PathVariable Long id) {

        List<Cliente> all = repository.findAllByNutricionistaId(id);
        if(all.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ClienteDto.converter(all));
    }

}
