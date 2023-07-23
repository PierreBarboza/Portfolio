package salus.well.projetosaluswell.domain.nutricionista;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;
import static org.junit.jupiter.api.Assertions.*;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NutricionistaCadastroTest {

    @Test
    public void testConstructorAndGetters() {

        Long id = 1L;
        String nome = "Pedro";
        String email = "pedro@gmail.com";
        String senha = "teste";
        int avatar = 2;
        String genero = "masculino";
        String endereco = "rua";
        String crn = "12345";
        String telefone = "1234567890";
        boolean ativo = true;


        NutricionistaCadastro nutricionista = new NutricionistaCadastro(id, nome, email, senha, avatar, genero, endereco, crn, telefone,ativo);


        Assertions.assertEquals(id, nutricionista.id());
        Assertions.assertEquals(nome, nutricionista.nome());
        Assertions.assertEquals(email, nutricionista.email());
        Assertions.assertEquals(senha, nutricionista.senha());
        Assertions.assertEquals(avatar, nutricionista.avatar());
        Assertions.assertEquals(genero, nutricionista.genero());
        Assertions.assertEquals(endereco, nutricionista.endereco());
        Assertions.assertEquals(crn, nutricionista.crn());
        Assertions.assertEquals(ativo, nutricionista.ativo());
    }

    @Test
    public void testEqualsAndHashCode() {

        NutricionistaCadastro nutricionista1 = new NutricionistaCadastro(1L, "Pedro", "pedro@gmail.com", "teste", 2, "masculino", "rua", "12345", "1234567890",true);
        NutricionistaCadastro nutricionista2 = new NutricionistaCadastro(1L, "Pedro", "pedro@gmail.com", "teste", 2, "masculino", "rua", "12345", "1234567890",true);



        Assertions.assertEquals(nutricionista1, nutricionista2);
        Assertions.assertEquals(nutricionista1.hashCode(), nutricionista2.hashCode());

    }
}


