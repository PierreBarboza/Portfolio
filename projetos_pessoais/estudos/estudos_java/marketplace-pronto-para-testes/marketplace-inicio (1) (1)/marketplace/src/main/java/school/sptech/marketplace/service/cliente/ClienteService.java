package school.sptech.marketplace.service.cliente;

import org.springframework.stereotype.Service;
import school.sptech.marketplace.domain.cliente.Cliente;
import school.sptech.marketplace.domain.cliente.data.ClienteRepository;
import school.sptech.marketplace.domain.exception.EntidadeNaoEncontradaException;
import school.sptech.marketplace.service.cliente.dto.ClienteAtualizacaoDto;
import school.sptech.marketplace.service.cliente.dto.ClienteConsultaDto;
import school.sptech.marketplace.service.cliente.dto.ClienteCriacaoDto;
import school.sptech.marketplace.service.cliente.dto.mapper.ClienteMapper;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteConsultaDto> listar() {

        List<Cliente> clientes = this.repository.findAll();

        return clientes.stream()
                .map(ClienteMapper::mapDomainToClienteConsultaDto)
                .toList();
    }

    public ClienteConsultaDto buscarPorId(long id) {

        Cliente cliente = this.repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format("Cliente de id: %d não encontrado", id))
        );

        return ClienteMapper.mapDomainToClienteConsultaDto(cliente);
    }

    public ClienteConsultaDto cadastrar(ClienteCriacaoDto dto) {

        Cliente cliente = ClienteMapper.matClientCriacaoDtoToDomain(dto);

        return ClienteMapper.mapDomainToClienteConsultaDto(this.repository.save(cliente));
    }

    public ClienteConsultaDto atualizar(long id, ClienteAtualizacaoDto dto) {

        Cliente cliente = this.repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format("Cliente de id: %d não encontrado", id))
        );

        Cliente domain = ClienteMapper.mapClienteAtualizacaoDtoToDomain(id, dto, cliente.getSenha());

        return ClienteMapper.mapDomainToClienteConsultaDto(this.repository.save(domain));
    }
}
