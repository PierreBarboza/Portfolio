package school.sptech.marketplaceresumido.service.produto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.marketplaceresumido.domain.data.ProdutoRepository;
import school.sptech.marketplaceresumido.service.produto.dto.ProdutoConsultaDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    @DisplayName("Listar quando acionado e tabela estiver vazia deve retornar lista vazia")
    void quandoAcionadoSemRegistroDeveRetornarListaVazia(){


        //given
        //cenario
        final int resultadoEsperado = 0;

        //when
        //comportamento
        Mockito.when(repository.findAll()).thenReturn(List.of());

        //then
        //ação
        List<ProdutoConsultaDto> resultado = service.listar();

        //assert
        assertEquals(resultadoEsperado, resultado.size());

    }
}