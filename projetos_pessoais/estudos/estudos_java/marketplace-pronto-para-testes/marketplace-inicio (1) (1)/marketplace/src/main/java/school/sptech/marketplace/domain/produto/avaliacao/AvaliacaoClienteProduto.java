package school.sptech.marketplace.domain.produto.avaliacao;

import jakarta.persistence.*;
import school.sptech.marketplace.domain.cliente.Cliente;
import school.sptech.marketplace.domain.produto.Produto;

@Entity
public class AvaliacaoClienteProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Produto produto;

    private Integer nota;
    private String comentario;

    public AvaliacaoClienteProduto() {
    }

    public AvaliacaoClienteProduto(Cliente cliente, Produto produto, Integer nota, String comentario) {
        this.cliente = cliente;
        this.produto = produto;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getComprador() {
        return cliente;
    }

    public void setComprador(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
