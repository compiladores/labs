package ar.uba.fi.compiladores.parte6;

import java.util.Collection;

import ar.uba.fi.compiladores.parte5.FakeLexer;
import ar.uba.fi.compiladores.parte5.GeneralToken;

public class ClFakeLexer extends FakeLexer<Object> implements Compilisp.Lexer {

    public ClFakeLexer(Collection<GeneralToken<Object>> tokens) {
        super(tokens);
    }

    @Override
    public void yyerror(String msg) {
        throw new CompilispBisonParserException(msg);
    }
    
}
