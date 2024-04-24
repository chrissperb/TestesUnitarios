package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class LocacaoServiceTest {

    @Test
    public void teste() {
        //cenario - criar e/ou instanciar todos os recursos necessários para o teste do método
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        // ação - aplicar o método no ecnário criado
        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        Assertions.assertEquals(5.0, locacao.getValor(), 0.01);
        Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assertions.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
