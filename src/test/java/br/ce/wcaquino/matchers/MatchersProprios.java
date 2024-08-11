package br.ce.wcaquino.matchers;

import java.util.Calendar;
import java.util.Date;

public class MatchersProprios {
    public static DiaSemanaMatcher caiEm(Integer diaSemana) {
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda() {
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static EhHojeMatcher ehHoje() {
        return new EhHojeMatcher(0);
    }

    public static EhHojeMatcher ehHojeComDiferencaDias(Integer qntDias) {
        return new EhHojeMatcher(qntDias);
    }
}
