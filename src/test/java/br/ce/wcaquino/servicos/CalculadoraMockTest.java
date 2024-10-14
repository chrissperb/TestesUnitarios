//package br.ce.wcaquino.servicos;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class CalculadoraMockTest {
//
//    @Mock
//    private Calculadora calcMock;
//
//    @Spy
//    private Calculadora calcSpy;
//
//    //    @Spy
//    @Mock
//    private EmailService email; //O @Spy não deveria funcionar neste caso, pois interfaces não são instanciáveis.
//
////    Quando o resultado do teste é diferente do esperado, o Mock retorna o valor padrão (zero)
////    e o Spy retorna o valor do método quando executado.
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this); //No curso usamos o initMocks, que foi depreciado.
//    }
//
//    @Test
//    public void devoMostrarDiferencaEntreMockSpy() {
//        when(calcMock.somar(1, 2)).thenReturn(5); //Solicita que o Mock use o método real
//        doNothing().when(calcSpy).imprime();
//        doReturn(5).when(calcSpy).somar(1, 2); //Desta forma o método somar não foi executado.
//    }
//
//    @Test
//    public void teste() {
//        Calculadora calc = mock(Calculadora.class);
//
//        ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
//        when(calc.somar(argCapt.capture(), argCapt.capture())).thenReturn(5);
//
//        assertEquals(5, calc.somar(1, 100000));
//    }
//
//    // Esta classe e método foram criados para exemplificar que, em um matcher do Mockito,
//    // sempre que UM MATCHER FOR USADO PARA UM PARÂMETRO, TODOS OS OUTROS PARÂMETROS DEVEM
//    // USAR MATCHERS TAMBEM.
//}
