public class Jogador {

    private Integer id;
    private Integer numCamisa;
    private String nome;
    private String posicao;
    private Double salario;
    private Boolean banco;
    private Boolean lesionado;

    public Jogador(Integer id, Integer numCamisa, String nome, String posicao, Double salario, Boolean banco, Boolean lesionado) {
        this.id = id;
        this.numCamisa = numCamisa;
        this.nome = nome;
        this.posicao = posicao;
        this.salario = salario;
        this.banco = banco;
        this.lesionado = lesionado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumCamisa() {
        return numCamisa;
    }

    public void setNumCamisa(Integer numCamisa) {
        this.numCamisa = numCamisa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Boolean getBanco() {
        return banco;
    }

    public void setBanco(Boolean banco) {
        this.banco = banco;
    }

    public Boolean getLesionado() {
        return lesionado;
    }

    public void setLesionado(Boolean lesionado) {
        this.lesionado = lesionado;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", numCamisa=" + numCamisa +
                ", nome='" + nome + '\'' +
                ", posicao='" + posicao + '\'' +
                ", salario=" + salario +
                ", banco=" + banco +
                ", lesionado=" + lesionado +
                '}';
    }
}
