//package br.ce.wcaquino.servicos;
//
//import br.ce.wcaquino.daos.LocacaoDAO;
//import br.ce.wcaquino.entidades.Filme;
//import br.ce.wcaquino.entidades.Locacao;
//import br.ce.wcaquino.entidades.Usuario;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//@RunWith(Parameterized.class)
//public class CalculoValorLocacaoTest {
//
//    @InjectMocks
//    private LocacaoService service;
//
//    @Mock
//    private LocacaoDAO dao;
//
//    @Mock
//    private SPCService spc;
//
//    @Parameterized.Parameter
//    public List<Filme> filmes;
//    @Parameterized.Parameter(value = 1)
//    public Double valorLocacao;
//    @Parameterized.Parameter(value = 2)
//    public String cenario;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    private static Filme filme1 = umFilme().agora();
//
//    private static Filme filme2 = umFilme().agora();
//    private static Filme filme3 = umFilme().agora();
//    private static Filme filme4 = umFilme().agora();
//    private static Filme filme5 = umFilme().agora();
//    private static Filme filme6 = umFilme().agora();
//    private static Filme filme7 = umFilme().agora();
//
//    @Parameterized.Parameters(name = "Teste {2}")
//    public static Collection<Object[]> getParametros() {
//        return Arrays.asList(new Object[][]{
//                {Arrays.asList(filme1, filme2), 8.0, "2 Filmes: Sem Desconto"},
//                {Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes: 25%"},
//                {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 Filmes: 50%"},
//                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
//                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes: 100%"},
//                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 Filmes: Sem Desconto"}
//        });
//    }
//
//    @Test
//    public void deveCalcularValorLocacaoConsiderandoDescontos() throws Exception {
//        //cenario
//        Usuario usuario = new Usuario();
//        //acao
//        Locacao resultado = service.alugarFilme(usuario, filmes);
//        //verificacao
//        assertThat(resultado.getValor(), is(valorLocacao));
//    }
//}
