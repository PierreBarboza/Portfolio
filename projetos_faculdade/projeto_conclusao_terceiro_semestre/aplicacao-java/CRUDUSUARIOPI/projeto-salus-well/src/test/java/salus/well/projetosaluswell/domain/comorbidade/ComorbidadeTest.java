package salus.well.projetosaluswell.domain.comorbidade;


import org.junit.jupiter.api.Test;
import salus.well.projetosaluswell.domain.cliente.Cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ComorbidadeTest {

    @Test
    void testConstructorAndGetters() {

        Cliente cliente = new Cliente();


        Comorbidade comorbidade = new Comorbidade(true, false, true, cliente);


        assertThat(comorbidade.getDiabete()).isTrue();
        assertThat(comorbidade.getColesterol()).isFalse();
        assertThat(comorbidade.getHipertensao()).isTrue();
        assertThat(comorbidade.getCliente()).isEqualTo(cliente);
    }

    @Test
    void testSetterAndGetters() {

        Cliente cliente = new Cliente();


        Comorbidade comorbidade = new Comorbidade();


        comorbidade.setDiabete(true);
        comorbidade.setColesterol(false);
        comorbidade.setHipertensao(true);
        comorbidade.setCliente(cliente);


        assertThat(comorbidade.getDiabete()).isTrue();
        assertThat(comorbidade.getColesterol()).isFalse();
        assertThat(comorbidade.getHipertensao()).isTrue();
        assertThat(comorbidade.getCliente()).isEqualTo(cliente);
    }

    @Test
    void testAtualizarInformacoes() {

        Comorbidade comorbidade = new Comorbidade(true, false, false, new Cliente());


        ComorbidadeAtualizar atualizar = new ComorbidadeAtualizar(1L , true, false, false);


        comorbidade.atualizarInformacoes(atualizar);


        assertThat(comorbidade.getDiabete()).isTrue();
        assertThat(comorbidade.getColesterol()).isFalse();
        assertThat(comorbidade.getHipertensao()).isFalse();
    }

}