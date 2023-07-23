package salus.well.projetosaluswell.domain.nutricionista;


import salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista.NutricionistaTokenDto;

public class NutricionistaMapper {
    public static Nutricionista of(NutricionistaCadastro usuarioDto){
        Nutricionista nutricionista = new Nutricionista();

        nutricionista.setNome(usuarioDto.nome());
        nutricionista.setEmail(usuarioDto.email());
        nutricionista.setSenha(usuarioDto.senha());
        nutricionista.setAvatar(usuarioDto.avatar());
        nutricionista.setGenero(usuarioDto.genero());
        nutricionista.setEndereco(usuarioDto.endereco());
        nutricionista.setCrn(usuarioDto.crn());
        nutricionista.setAtivo(usuarioDto.ativo());
        nutricionista.setTelefone(usuarioDto.telefone());


        return nutricionista;
    }

    public static NutricionistaTokenDto of(Nutricionista nutricionista, String token){
        NutricionistaTokenDto nutricionistaTokenDto = new NutricionistaTokenDto();

        nutricionistaTokenDto.setUserId(nutricionista.getId());
        nutricionistaTokenDto.setEmail(nutricionista.getEmail());
        nutricionistaTokenDto.setSenha(nutricionista.getSenha());
        nutricionistaTokenDto.setToken(token);
        return nutricionistaTokenDto;
    }
}
