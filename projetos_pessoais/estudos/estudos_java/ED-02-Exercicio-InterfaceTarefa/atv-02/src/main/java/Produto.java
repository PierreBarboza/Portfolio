public abstract class Produto implements Vendavel{

    private Integer codigo;
    private Double procoCusto;

    public Produto(Integer codigo, Double procoCusto) {
        this.codigo = codigo;
        this.procoCusto = procoCusto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Double getProcoCusto() {
        return procoCusto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", procoCusto=" + procoCusto +
                '}';
    }


}
