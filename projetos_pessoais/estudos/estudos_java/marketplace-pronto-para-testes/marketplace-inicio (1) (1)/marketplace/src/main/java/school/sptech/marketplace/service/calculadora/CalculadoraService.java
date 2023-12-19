package school.sptech.marketplace.service.calculadora;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    private final double ICMS = 0.18;

    public double calcularImposto(double valorProduto) {

        if (valorProduto <= 0) {
            throw new IllegalArgumentException("Valor do produto deve ser maior que zero");
        }

        return valorProduto * ICMS;
    }
}
