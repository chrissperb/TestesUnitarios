package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test() {
        Assert.assertTrue(true); // Confere se a expressão é verdadeira
        Assert.assertTrue(!false); // Confere se a expressão é verdadeira
        Assert.assertFalse(false); // Confere se a expressão é falsa

        Assert.assertEquals(1, 1); // Verifica se dois valores são iguais (primeiro o ESPERADO e depois o ATUAL)
        Assert.assertEquals(0.51, 0.51, 0.1); // Compara dois valores com um delta de variação, uma margem de erro

        // Criando dois objetos em formatos diferentes, tipo primitivo e objeto Wrapper, eles não podem ser comparados diretamente
        int i = 5;
        Integer i2 = 5;
        // Assert.assertEquals(i, i2); --> Esta igualdade não é permitida, entao convertemos destas duas formas possiveis:
        Assert.assertEquals(Integer.valueOf(i), i2);
        Assert.assertEquals(i, i2.intValue());

        // Comparando strings
        Assert.assertEquals("bola", "bola"); // Com letra maiúscula o teste nao passaria, e precisaria contornar usando:
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));
        Assert.assertNotEquals("bola", "cola"); // testa se sao diferentes

        // Comparando objetos
        Usuario u1 = new Usuario("Usuario 1");
        Usuario u2 = new Usuario("Usuario 1");
        Usuario u3 = null;

        Assert.assertEquals(u1, u2); // O teste nao passa quando usamos a comparação da superclasse Object, pois ambos os usuarios, apesar de mesmo atributo nome, possuem endereços diferentes na memória do programa
        // Para resolver isto, e comparar os atribtos internos, criamos o método de comparação dentro da classe Usuario usando o Alt+Insert --> Generate --> equals and hash code
        // Repetindo o teste, ele passa. Se os usuários tiverem o mesmo nome, o teste nao passara.

        Assert.assertSame(u1, u1); // testa se os objetos pertencem à mesma instância
        Assert.assertSame(u2, u2); // testa se os objetos pertencem à mesma instância
        Assert.assertNotSame(u2, u1); // testa se os objetos pertencem à instâncias diferentes

        // Para verificar se o objeto é nulo
        Assert.assertNull(u3);
        Assert.assertNotNull(u2);

        // Colocando uma mensagem de erro
        Assert.assertNotNull("Erro de comparação.", u1);
    }
}
