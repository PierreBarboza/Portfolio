package school.sptech.marketplace.service.avaliacao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {


    @InjectMocks
    private AvaliacaoService service;

    @Test
    @DisplayName("Listar quando acionado e tabela estiver vazia deve retornar lista vazia")
    void quandoAcionadoSemRegistroDeveRetornarListaVazia(){



    }
}