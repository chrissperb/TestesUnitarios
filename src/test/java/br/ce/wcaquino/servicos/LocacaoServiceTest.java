//package br.ce.wcaquino.servicos;
//
//import br.ce.wcaquino.daos.LocacaoDAO;
//import br.ce.wcaquino.entidades.Filme;
//import br.ce.wcaquino.entidades.Locacao;
//import br.ce.wcaquino.entidades.Usuario;
//import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
//import br.ce.wcaquino.exceptions.LocadoraException;
//import br.ce.wcaquino.runners.ParallelRunner;
//import org.junit.*;
//import org.junit.rules.ErrorCollector;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//
//import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
//import static br.ce.wcaquino.builders.FilmeBuilder.umFilmeSemEstoque;
//import static br.ce.wcaquino.builders.LocacaoBuilder.umLocacao;
//import static br.ce.wcaquino.builders.UsuarioBuilder.umUsuario;
//import static br.ce.wcaquino.matchers.MatchersProprios.*;
//import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
//import static br.ce.wcaquino.utils.DataUtils.obterData;
//import static java.util.Arrays.asList;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.fail;
//import static org.mockito.Mockito.*;
//import static org.powermock.api.mockito.PowerMockito.when;
//
//@RunWith(ParallelRunner.class)
//public class LocacaoServiceTest {
//
//    @InjectMocks
//    @Spy
//    private LocacaoService service;
//
//    @Mock
//    private SPCService spc;
//    @Mock
//    private LocacaoDAO dao;
//    @Mock
//    private EmailService email;
//
//    /* Para testes onde o cenário e a ação sao os mesmos para diversas assertivas, o professor recomenda que
//     * se use a anotação @Rule e instanciar um ErrorCollector (aqui chamado de error), para que todos os erros
//     * possam ser rastreados de uma vez dentro do mesmo teste. */
//
//    @Rule
//    public ErrorCollector error = new ErrorCollector();
//
//    @Rule
//    public ExpectedException exception = ExpectedException.none();
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        System.out.println("Inicianto o teste 2 ...");
//    }
//
//    @After
//    public void tearDown(){
//        System.out.println("Finalizando 2 ...");
//    }
//
//    @Test
//    public void deveAlugarFilme() throws Exception {
//
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = Arrays.asList(umFilme().comValor(5.0).agora());
//
//        Mockito.doReturn(obterData(27, 9, 2024)).when(service).obterData();
//
//        // ação
//        Locacao locacao = service.alugarFilme(usuario, filmes);
//
//        //verificacao
//        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
//        error.checkThat(isMesmaData(locacao.getDataLocacao(), obterData(27, 9, 2024)), is(true));
//        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterData(28, 9, 2024)), is(true));
//    }
//
//    @Test(expected = FilmeSemEstoqueException.class)
//    public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = Arrays.asList(umFilmeSemEstoque().agora());
//
//        //acao
//        Locacao locacao = service.alugarFilme(usuario, filmes);
//    }
//
//    @Test
//    public void naoDeveAlugarUsuarioVazio() throws FilmeSemEstoqueException {
//        //cenario
//        List<Filme> filmes = Arrays.asList(umFilme().agora());
//
//        //acao
//        try {
//            service.alugarFilme(null, filmes);
//            fail();
//        } catch (Exception e) {
//            assertThat(e.getMessage(), is("Usuário vazio."));
//        }
//    }
//
//    @Test
//    public void naoDeveAlugarFilmeVazio() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//
//        exception.expect(LocadoraException.class);
//        exception.expectMessage("Filme vazio.");
//        //acao
//        service.alugarFilme(usuario, null);
//    }
//
//    @Test
//    public void devePagar75PctNoFilme3() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = asList(
//                new Filme("Filme 1", 2, 4.0),
//                new Filme("Filme 2", 2, 4.0),
//                new Filme("Filme 3", 2, 4.0));
//
//        //acao
//        Locacao resultado = service.alugarFilme(usuario, filmes);
//
//        //verificacao
//        assertThat(resultado.getValor(), is(11.0));
//    }
//
//    @Test
//    public void devePagar50PctNoFilme4() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = asList(
//                new Filme("Filme 1", 2, 4.0),
//                new Filme("Filme 2", 2, 4.0),
//                new Filme("Filme 3", 2, 4.0),
//                new Filme("Filme 4", 2, 4.0));
//
//        //acao
//        Locacao resultado = service.alugarFilme(usuario, filmes);
//
//        //verificacao
//        assertThat(resultado.getValor(), is(13.0));
//    }
//
//    @Test
//    public void devePagar25PctNoFilme5() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = asList(
//                new Filme("Filme 1", 2, 4.0),
//                new Filme("Filme 2", 2, 4.0),
//                new Filme("Filme 3", 2, 4.0),
//                new Filme("Filme 4", 2, 4.0),
//                new Filme("Filme 5", 2, 4.0));
//
//        //acao
//        Locacao resultado = service.alugarFilme(usuario, filmes);
//
//        //verificacao
//        assertThat(resultado.getValor(), is(14.0));
//    }
//
//    @Test
//    public void devePagar0PctNoFilme6() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = asList(
//                new Filme("Filme 1", 2, 4.0),
//                new Filme("Filme 2", 2, 4.0),
//                new Filme("Filme 3", 2, 4.0),
//                new Filme("Filme 4", 2, 4.0),
//                new Filme("Filme 5", 2, 4.0),
//                new Filme("Filme 6", 2, 4.0));
//
//        //acao
//        Locacao resultado = service.alugarFilme(usuario, filmes);
//
//        //verificacao
//        assertThat(resultado.getValor(), is(14.0));
//    }
//
//    @Test
//    public void deveDevolverFilmeNaSegundaAoAlugarNoSabado() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = Arrays.asList(umFilme().agora());
//
//        Mockito.doReturn(obterData(28, 9, 2024)).when(service).obterData();
//
//        //acao
//        Locacao retorno = service.alugarFilme(usuario, filmes);
//
//        //verificacao
//        assertThat(retorno.getDataRetorno(), caiNumaSegunda());
//    }
//
//    @Test
//    public void naoDeveAlugarFilmeParaNegativadoSPC() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        Usuario usuario2 = umUsuario().comNome("Usuario 2").agora();
//        List<Filme> filmes = asList(umFilme().agora());
//
//        when(spc.possuiNegativacao(any(Usuario.class))).thenReturn(true);
//
//        //acao
//        try {
//
//            //verificacao
//            service.alugarFilme(usuario, filmes);
//            fail();
//        } catch (LocadoraException e) {
//            assertThat(e.getMessage(), is("Usuario negativado."));
//        }
//
//        Mockito.verify(spc).possuiNegativacao(usuario);
//    }
//
//    @Test
//    public void deveEnviarEmailParaLocacoesAtrasadas() {
//        //cenario
//        Usuario usuarioAtrasado = umUsuario().comNome("Usuario atrasado").agora();
//        Usuario usuarioEmDia = umUsuario().comNome("Usuario em dia").agora();
//        Usuario outroAtrasado = umUsuario().comNome("Outro atrasado").agora();
//        List<Locacao> locacoes = Arrays.asList(
//                umLocacao().atrasada().comUsuario(usuarioAtrasado).agora(),
//                umLocacao().comUsuario(usuarioEmDia).agora(),
//                umLocacao().atrasada().comUsuario(outroAtrasado).agora(),
//                umLocacao().atrasada().comUsuario(outroAtrasado).agora());
//        when(dao.obterLocacoesPendentes()).thenReturn(locacoes);
//
//        //acao
//        service.notificarAtrasos();
//
//        //verificacao - varias possibilidades
//        Mockito.verify(email, times(3)).notificarAtraso(any(Usuario.class));
//        Mockito.verify(email).notificarAtraso(usuarioAtrasado);
//        Mockito.verify(email, atLeastOnce()).notificarAtraso(outroAtrasado);
//        Mockito.verify(email, never()).notificarAtraso(usuarioEmDia);
//        Mockito.verifyNoMoreInteractions(email);
//    }
//
///*    public static void main(String[] args) {
//        new BuilderMaster().gerarCodigoClasse(Locacao.class);
//    }
//*/
//
//    @Test
//    public void deveTratarErroNoSPC() throws Exception {
//        //cenario
//        Usuario usuario = umUsuario().agora();
//        List<Filme> filmes = Arrays.asList(umFilme().agora());
//
//        when(spc.possuiNegativacao(usuario)).thenThrow(new Exception("Falha catastrófica."));
//
//        //verificacao
//        exception.expect(LocadoraException.class);
//        exception.expectMessage("Problemas com o SPC, tente novamente.");
//
//        //acao
//        service.alugarFilme(usuario, filmes);
//
//    }
//
//    @Test
//    public void deveProrrogarUmaLocacao() {
//        //cenario
//        Locacao locacao = umLocacao().agora();
//
//        //acao
//        service.prorrogarLocacao(locacao, 3);
//
//        //verificacao
//        ArgumentCaptor<Locacao> argCapt = ArgumentCaptor.forClass(Locacao.class);
//        Mockito.verify(dao).salvar(argCapt.capture());
//        Locacao locacaoRetornada = argCapt.getValue();
//
//        error.checkThat(locacaoRetornada.getValor(), is(12.0));
//        error.checkThat(locacaoRetornada.getDataLocacao(), ehHoje());
//        error.checkThat(locacaoRetornada.getDataRetorno(), ehHojeComDiferencaDias(3));
//    }
//
//    @Test
//    public void deveCalcularValorLocacao() throws Exception {
//        //cenario
//        List<Filme> filmes = Arrays.asList(umFilme().agora());
//
//        //acao
//        Class<LocacaoService> clazz = LocacaoService.class;
//        Method metodo = clazz.getDeclaredMethod("calcularValorLocacao", List.class);
//        metodo.setAccessible(true);
//        Double valor = (Double) metodo.invoke(service, filmes);
//
//        //verificacao
//        Assert.assertThat(valor, is(4.0));
//
//    }
//
//}
