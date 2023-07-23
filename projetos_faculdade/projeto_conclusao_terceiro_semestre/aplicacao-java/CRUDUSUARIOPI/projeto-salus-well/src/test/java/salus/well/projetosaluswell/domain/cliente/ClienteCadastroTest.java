package salus.well.projetosaluswell.domain.cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteCadastroTest {

    @Test
    public void testConstructorAndGetters() {
        String nome = "Gu";
        String email = "opa@gmail.com";
        String senha = "teste";
        String telefone = "1234567890";
        int avatar = 5;
        String genero = "masculino";
        String endereco = "rua";
        boolean comorbidade = true;
        boolean ativo = true;

        ClienteCadastro cliente = new ClienteCadastro(nome, email, senha, avatar, genero, endereco, comorbidade,telefone, ativo);

        assertEquals(nome, cliente.nome());
        assertEquals(email, cliente.email());
        assertEquals(senha, cliente.senha());
        assertEquals(avatar, cliente.avatar());
        assertEquals(genero, cliente.genero());
        assertEquals(endereco, cliente.endereco());
        assertEquals(comorbidade, cliente.comorbidade());
        assertEquals(ativo, cliente.ativo());
    }

    @Test
    public void testRecordEqualsAndHashCode() {
        ClienteCadastro cliente1 = new ClienteCadastro("Gu", "opa@gmail.com", "teste", 5, "masculino", "rua", true, "1234567890", true);
        ClienteCadastro cliente2 = new ClienteCadastro("Gu", "opa@gmail.com", "teste", 5, "masculino", "rua", true, "1234567890", true);

        assertEquals(cliente1, cliente2);
        assertEquals(cliente1.hashCode(), cliente2.hashCode());
    }


}
