package school.sptech.marketplace.service.cliente.dto.mapper;

import school.sptech.marketplace.domain.cliente.Cliente;
import school.sptech.marketplace.service.cliente.dto.ClienteAtualizacaoDto;
import school.sptech.marketplace.service.cliente.dto.ClienteConsultaDto;
import school.sptech.marketplace.service.cliente.dto.ClienteCriacaoDto;

import java.util.Objects;

public class ClienteMapper {

    private ClienteMapper() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static ClienteConsultaDto mapDomainToClienteConsultaDto(Cliente domain) {

        if (Objects.isNull(domain)) {
            return null;
        }

        ClienteConsultaDto dto = new ClienteConsultaDto();

        dto.setId(domain.getId());
        dto.setNome(domain.getNome());
        dto.setEmail(domain.getEmail());

        return dto;
    }

    public static Cliente matClientCriacaoDtoToDomain(ClienteCriacaoDto dto) {

        if (Objects.isNull(dto)) {
            return null;
        }

        Cliente domain = new Cliente();

        domain.setNome(dto.getNome());
        domain.setEmail(dto.getEmail());
        domain.setSenha(dto.getSenha());

        return domain;
    }

    public static Cliente mapClienteAtualizacaoDtoToDomain(
            long id,
            ClienteAtualizacaoDto dto,
            String senha
    ) {

        if (Objects.isNull(dto)) {
            return null;
        }

        Cliente domain = new Cliente();

        domain.setId(id);
        domain.setNome(dto.getNome());
        domain.setEmail(dto.getEmail());
        domain.setSenha(senha);

        return domain;
    }
}
