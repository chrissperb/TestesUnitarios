package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocacaoServiceTest {

    /* Para testes onde o cenário e a ação sao os mesmos para diversas assertivas, o professor recomenda que
     * se use a anotação @Rule e instanciar um ErrorCollector (aqui chamado de error), para que todos os erros
     * possam ser rastreados de uma vez dentro do mesmo teste. */
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testeLocacao() {
        //cenario - criar e/ou instanciar todos os recursos necessários para o teste do método
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        // ação - aplicar o método no cenário criado
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        // Usei o static import para a classe Assert
        // Usei um exemplo do uso do ErrorCollector para cada assertiva
        assertEquals(5.0, locacao.getValor(), 0.01);
        collector.checkThat(locacao.getValor(), is(equalTo(5.0)));
        assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        collector.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));
        collector.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(2)), is(true));

        // Usando o assertThat e o CoreMatchers do hamcrest (static import)
        assertThat(locacao.getValor(), is(equalTo(5.0))); // A declaracao dos valores é invertido em relacao ao assertEquals, primeiro o atual, depois o esperado
        assertThat(locacao.getValor(), is(not(6.0))); // Forma negativa
        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }
}
