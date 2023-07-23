package salus.well.projetosaluswell.domain.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaRepository;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaDetalhesDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioDetalhesDto;

import java.util.Optional;
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private ClienteRepository usuarioRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(username);
        if(usuarioOpt.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return new UsuarioDetalhesDto(usuarioOpt.get());
    }


    public UserDetails loadUserByUsername(String username, String nutricionista) throws UsernameNotFoundException {
        Optional<Nutricionista> nutricionistaOpt = nutricionistaRepository.findByEmailIgnoreCase(username);

        if(nutricionistaOpt.isEmpty()){
            throw new UsernameNotFoundException("Nutricionista não encontrado");
        }
        return new NutricionistaDetalhesDto(nutricionistaOpt.get());
    }



}
