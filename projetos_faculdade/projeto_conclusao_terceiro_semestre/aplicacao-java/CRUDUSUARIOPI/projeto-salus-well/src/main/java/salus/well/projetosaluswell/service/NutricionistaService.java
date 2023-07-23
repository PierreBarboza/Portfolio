package salus.well.projetosaluswell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import salus.well.projetosaluswell.configuration.jwt.GerenciadorTokenJwt;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteDto;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaCadastro;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaMapper;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaRepository;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaLoginDto;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaTokenDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NutricionistaService {
    @Autowired
    private NutricionistaRepository nutricionistaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(NutricionistaCadastro nutricionistaDto){

        final Nutricionista novoNutricionista = NutricionistaMapper.of(nutricionistaDto);
        String senhaCriptografada = passwordEncoder.encode(novoNutricionista.getSenha());
        novoNutricionista.setSenha(senhaCriptografada);
        nutricionistaRepository.save(novoNutricionista);
    }

    public NutricionistaTokenDto autenticar(NutricionistaLoginDto nutricionistaLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                nutricionistaLoginDto.getEmail(), nutricionistaLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Nutricionista nutricionistaAutenticado =
                nutricionistaRepository.findByEmailIgnoreCase(nutricionistaLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email não encontrado", null)
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return NutricionistaMapper.of(nutricionistaAutenticado, token);

    }


    public List<Nutricionista> nutricionistasNome(String nome){
        List<Nutricionista> nutricionistas = nutricionistaRepository.findByNomeIgnoreCase(nome);

        if(nutricionistas.isEmpty()){
            throw new RuntimeException("Nenhum nutricionista necontrado");
        }
        return nutricionistas;
    }


    }

