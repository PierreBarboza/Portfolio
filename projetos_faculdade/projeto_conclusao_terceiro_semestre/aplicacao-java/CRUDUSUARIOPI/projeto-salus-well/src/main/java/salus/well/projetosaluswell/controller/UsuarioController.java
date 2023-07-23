package salus.well.projetosaluswell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteCadastro;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioLoginDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioTokenDto;
import salus.well.projetosaluswell.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
@Autowired
    private UsuarioService usuarioService;

//@PostMapping("/login")
//    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
//    UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
//        return ResponseEntity.status(201).body(usuarioToken);
//}
@PostMapping("/cadastrar")
    public ResponseEntity<ClienteCadastro> criar(@RequestBody ClienteCadastro usuarioDto){
            usuarioService.criar(usuarioDto);
            return ResponseEntity.status(201).body(usuarioDto);
}
}
