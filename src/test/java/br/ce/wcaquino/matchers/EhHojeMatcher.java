package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;

public class EhHojeMatcher extends TypeSafeMatcher<Date> {

    private Integer qntDias;

    public EhHojeMatcher(Integer qntDias){
        this.qntDias = qntDias;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("uma data que é hoje");
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return isMesmaData(data, DataUtils.obterDataComDiferencaDias(qntDias));
    }
}

