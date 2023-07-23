package salus.well.projetosaluswell.domain.alimento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;



@Table(name = "alimentos",uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"})})
@Entity(name = "Alimento")
@EqualsAndHashCode(of = "id")
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Size(min=3)
    @Column(name = "nome",nullable = false, unique = true)
    private String nome;
    @NotBlank
    @NotNull
    @Size(min=3)
    private String tipo;
    private boolean diabete;
    private boolean colesterol;
    private boolean hipertensao;
    @NotNull
    @PositiveOrZero
    private double proteina;
    @NotNull
    @PositiveOrZero
    private double carboidrato;
    @NotNull
    @PositiveOrZero
    private double gorduraTotal;
    private double calorias;

    @JsonIgnore
    @Column(length = 10 * 1024 * 1024)
    private byte[] relatorioExcel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDiabete() {
        return diabete;
    }

    public void setDiabete(boolean diabete) {
        this.diabete = diabete;
    }

    public boolean isColesterol() {
        return colesterol;
    }

    public void setColesterol(boolean colesterol) {
        this.colesterol = colesterol;
    }

    public boolean isHipertensao() {
        return hipertensao;
    }

    public void setHipertensao(boolean hipertensao) {
        this.hipertensao = hipertensao;
    }

    public double getProteina() {
        return proteina;
    }

    public void setProteina(double proteina) {
        this.proteina = proteina;
    }

    public double getCarboidrato() {
        return carboidrato;
    }

    public void setCarboidrato(double carboidrato) {
        this.carboidrato = carboidrato;
    }

    public double getGorduraTotal() {
        return gorduraTotal;
    }

    public void setGorduraTotal(double gorduraTotal) {
        this.gorduraTotal = gorduraTotal;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public Alimento() {
    }

    public Alimento(Long id, String nome, String tipo, boolean diabete, boolean colesterol, boolean hipertensao, double proteina, double carboidrato, double gorduraTotal, double calorias) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.diabete = diabete;
        this.colesterol = colesterol;
        this.hipertensao = hipertensao;
        this.proteina = proteina;
        this.carboidrato = carboidrato;
        this.gorduraTotal = gorduraTotal;
        this.calorias = calorias;
    }

    public boolean existeNomeAlimento(String nome){
        if(this.nome.equals(nome)){
            return true;
        }
        return false;
    }

}
