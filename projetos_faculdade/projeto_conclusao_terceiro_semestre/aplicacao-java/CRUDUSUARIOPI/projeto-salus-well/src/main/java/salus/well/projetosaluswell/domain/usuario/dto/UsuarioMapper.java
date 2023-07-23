package salus.well.projetosaluswell.domain.usuario.dto;

import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteCadastro;
import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario.UsuarioTokenDto;

public class UsuarioMapper {
    public static Cliente of(ClienteCadastro usuarioDto){
        Cliente usuario = new Cliente();
        usuario.setNome(usuarioDto.nome());
        usuario.setEmail(usuarioDto.email());
        usuario.setSenha(usuarioDto.senha());
        usuario.setAvatar(usuarioDto.avatar());
        usuario.setGenero(usuarioDto.genero());
        usuario.setEndereco(usuarioDto.endereco());
        usuario.setComorbidade(usuarioDto.comorbidade());
        usuario.setAtivo(usuarioDto.ativo());
        usuario.setTelefone(usuarioDto.telefone());

        return usuario;
    }

    public static UsuarioTokenDto of(Cliente usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setSenha(usuario.getSenha());
        usuarioTokenDto.setToken(token);
        return usuarioTokenDto;
    }
}
