package ar.uba.fi.compiladores.parte5;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public abstract class FakeLexer<S> {
    Queue<GeneralToken<S>> tokens;
    GeneralToken<S> lastLexed=null;
    public FakeLexer(Collection<GeneralToken<S>> tokens){
        this.tokens=new LinkedList<GeneralToken<S>>(tokens);
    }

    public S getLVal() {
        if(!lastLexed.getContents().isPresent()){
            return null;
        }
        return lastLexed.getContents().get();
    }

    public int yylex() throws IOException {
        if(tokens.isEmpty()){
            return 0;
        }
        lastLexed = tokens.remove();
        return lastLexed.getTokenType();
    }

    public void yyerror(String msg) {
        throw new RuntimeException("Error de sintaxis");
    }
    
}
