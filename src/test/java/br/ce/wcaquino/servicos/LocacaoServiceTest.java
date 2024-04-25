package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocacaoServiceTest {

    @Test
    public void teste() {
        //cenario - criar e/ou instanciar todos os recursos necessários para o teste do método
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        // ação - aplicar o método no cenário criado
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        // Usei o static import para a classe Assert
        assertEquals(5.0, locacao.getValor(), 0.01);
        assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));

        // Usando o assertThat e o CoreMatchers do hamcrest (static import)
        assertThat(locacao.getValor(), is(equalTo(5.0))); // A declaracao dos valores é invertido em relacao ao assertEquals, primeiro o atual, depois o esperado
        assertThat(locacao.getValor(), is(not(6.0))); // Forma negativa
        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }
}
