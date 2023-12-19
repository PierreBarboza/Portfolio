package school.sptech.marketplaceresumido.service.calculadora;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CalculadoraServise {

    private static final double ICMS = 0.18;

    public double calcularImposto(Double valorProduto){

        if (Objects.isNull(valorProduto)){
            throw new IllegalArgumentException("Valor nao pode ser nulo");
        }

        if (valorProduto < 0){
            throw new IllegalArgumentException("valor nao pode ser negativo");
        }

        return valorProduto * ICMS;
    }
}
