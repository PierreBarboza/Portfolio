package school.sptech.marketplace.controller.cliente;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.marketplace.service.cliente.ClienteService;
import school.sptech.marketplace.service.cliente.dto.ClienteAtualizacaoDto;
import school.sptech.marketplace.service.cliente.dto.ClienteConsultaDto;
import school.sptech.marketplace.service.cliente.dto.ClienteCriacaoDto;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteConsultaDto>> listar() {

        List<ClienteConsultaDto> clientes = this.service.listar();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteConsultaDto> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(this.service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteConsultaDto> cadastrar(@Valid ClienteCriacaoDto dto) {
        return ResponseEntity.ok(this.service.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteConsultaDto> atualizar(@PathVariable long id, @Valid ClienteAtualizacaoDto dto) {
        return ResponseEntity.ok(this.service.atualizar(id, dto));
    }
}
