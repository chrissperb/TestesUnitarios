package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;

public class EhHojeMatcher extends TypeSafeMatcher<Date> {

    private Integer qntDias;

    public EhHojeMatcher(Integer qntDias) {
        this.qntDias = qntDias;
    }

    @Override
    public void describeTo(Description desc) {
        Date dataEsperada = DataUtils.obterDataComDiferencaDias(qntDias);
        DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        desc.appendText("A data esperada é " + format.format(dataEsperada));
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return isMesmaData(data, DataUtils.obterDataComDiferencaDias(qntDias));
    }
}

