package ar.uba.fi.compiladores.parte5;

import java.util.Collection;

public class CalcFakeLexer extends FakeLexer<Number> implements Calc.Lexer {

    public CalcFakeLexer(Collection<GeneralToken<Number>> tokens) {
        super(tokens);
    }
    
}
