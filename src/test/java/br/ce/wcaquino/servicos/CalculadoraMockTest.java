package br.ce.wcaquino.servicos;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CalculadoraMockTest {

    @Test
    public void teste() {
        Calculadora calc = Mockito.mock(Calculadora.class);

        ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(calc.somar(argCapt.capture(), argCapt.capture())).thenReturn(5);

        assertEquals(5, calc.somar(1, 100000));
        System.out.println(argCapt.getAllValues());
    }

    // Esta classe e método foram criados para exemplificar que, em um matcher do Mockito,
    // sempre que UM MATCHER FOR USADO PARA UM PARÂMETRO, TODOS OS OUTROS PARÂMETROS DEVEM
    // USAR MATCHERS TAMBEM.
}
