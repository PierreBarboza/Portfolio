package salus.well.projetosaluswell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import salus.well.projetosaluswell.configuration.jwt.GerenciadorTokenJwt;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteCadastro;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaCadastro;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaMapper;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaRepository;
import salus.well.projetosaluswell.domain.usuario.UsuarioRepository;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaLoginDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaTokenDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioLoginDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioTokenDto;
import salus.well.projetosaluswell.domain.usuario.dto.UsuarioMapper;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private ClienteRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public void criar(ClienteCadastro usuarioDto){

        final Cliente novoUsuario = UsuarioMapper.of(usuarioDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        usuarioRepository.save(novoUsuario);
    }


    public void atualizarNutri(Long idCliente, Long idNutri){
        Cliente cliente = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Nutricionista nutricionista = nutricionistaRepository.findById(idNutri)
                .orElseThrow(() -> new RuntimeException("Nutricionista não encontrado"));

                cliente.setNutricionista(nutricionista);

                usuarioRepository.save(cliente);
    }

    public void trazerClientes(){
       List<Cliente> clientes = usuarioRepository.clientesSemVinculo();
    }


    public void trazerClientesDeUmNutri(Long idNutri){
        List<Cliente> clientes = usuarioRepository.findByNutricionistaId(idNutri);
    }

    public void desvincularNutri(@PathVariable Long id){

            Cliente cliente = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            cliente.setNutricionista(null);
            usuarioRepository.save(cliente);

    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Cliente usuarioAutenticado =
                usuarioRepository.findByEmailIgnoreCase(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email não encontrado", null)
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token);

    }


}
