package sptech.school.nota02;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 12)
    private String modelo;

    @NotBlank
    @Size(min = 2, max = 10)
    private String fabricante;

    @PastOrPresent
    private LocalDate dataFabricacao;

    @NotBlank
    @Min(value = 1950)
    @Max(value = 2022)
    private Integer modeloAno;

    @NotBlank
    @DecimalMin(value = "0.2")
    @DecimalMax(value = "7.0")
    private Double potenciaMotor;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Integer getModeloAno() {
        return modeloAno;
    }

    public void setModeloAno(Integer modeloAno) {
        this.modeloAno = modeloAno;
    }

    public Double getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(Double potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }
}
