package school.sptech.marketplaceresumido.service.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraServiseTest {

    @Test
    @DisplayName("calcular imposto quando acionado com valor 10 Deve Retornar 1,8")
    void calcularImpostoQuandoAcionadoComValor10DeveRetornarCorretamente(){

        CalculadoraServise service = new CalculadoraServise();
        double valorProduto = 10.0;
        double valorEsperado = 1.8;
        double resultado = service.calcularImposto(valorProduto);

        assertEquals(valorEsperado, resultado, 0.5);
    }

    @Test
    @DisplayName("Calcular imposto quando acionado com o valor null deve lançar exceção")
    void quandoAcionadoComValorNuloLancaExcecao(){

        CalculadoraServise service = new CalculadoraServise();

        final String mensagemEsperada = "Valor nao pode ser nulo";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.calcularImposto(null));

        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Calcular imposto quando acionado com o valor negativo deve lançar exceção")
    void quandoAcionadoComValorNegativoLancaExcecao(){

        CalculadoraServise service = new CalculadoraServise();
        final String mensagemEsperada = "valor nao pode ser negativo";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.calcularImposto(-10.0));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

}