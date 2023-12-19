package school.sptech.marketplace.service.produto.dto;

public class ProdutoConsultaDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private int quantidade;

    public ProdutoConsultaDto() {
    }

    public ProdutoConsultaDto(Long id, String nome, String descricao, Double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotalEmEstoque() {
        return this.preco * this.quantidade;
    }
}
