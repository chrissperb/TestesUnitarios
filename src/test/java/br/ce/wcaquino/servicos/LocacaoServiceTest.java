package br.ce.wcaquino.servicos;

import Exceptions.FilmeSemEstoqueException;
import Exceptions.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocacaoServiceTest {
    private LocacaoService service;

    /* Para testes onde o cenário e a ação sao os mesmos para diversas assertivas, o professor recomenda que
     * se use a anotação @Rule e instanciar um ErrorCollector (aqui chamado de error), para que todos os erros
     * possam ser rastreados de uma vez dentro do mesmo teste. */
    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        service = new LocacaoService();
    }

    @Test
    public void deveLocarFilme() throws Exception {
        //cenario - criar e/ou instanciar todos os recursos necessários para o teste do método
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        // ação - aplicar o método no cenário criado
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);
    }

    @Test
    public void naoDeveAlugarUsuarioVazio() throws FilmeSemEstoqueException {
        //cenario
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0));

        //acao
        try {
            service.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuário vazio."));
        }
    }

    @Test
    public void naoDeveAlugarFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 3");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio.");
        //acao
        service.alugarFilme(usuario, null);
    }

    @Test
    public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario();
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0));

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(resultado.getValor(), is(11.0));
    }
    @Test
    public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario();
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0));

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(resultado.getValor(), is(13.0));
    }

    @Test
    public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario();
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0),
                new Filme("Filme 5", 2, 4.0));

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(resultado.getValor(), is(14.0));
    }

    @Test
    public void devePagar0PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario();
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0),
                new Filme("Filme 5", 2, 4.0),
                new Filme("Filme 6", 2, 4.0));

        //acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        //verificacao
        assertThat(resultado.getValor(), is(14.0));
    }

    @Test
    public void deveDevolverFilmeNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //acao
        Locacao retorno = service.alugarFilme(usuario, filmes);

        //verificacao
        boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(ehSegunda);
    }

}
