package ar.uba.fi.compiladores.parte6;

import java.util.Optional;

import ar.uba.fi.compiladores.parte5.GeneralToken;
import lombok.Getter;

public class ClToken implements GeneralToken<Object> {
    @Getter
    private int tokenType;
    @Getter
    private Optional<Object> contents = Optional.empty();
    public ClToken(int tokenType){
        this.tokenType=tokenType;
    }
    public static ClToken number(Number n){
        ClToken ret = new ClToken(Compilisp.Lexer.NUMBER);
        ret.contents=Optional.of(n);
        return ret;
    }
    public static ClToken str(String s){
        ClToken ret = new ClToken(Compilisp.Lexer.STRING);
        ret.contents=Optional.of(s);
        return ret;
    }
    public static ClToken sym(String s){
        ClToken ret = new ClToken(Compilisp.Lexer.SYMBOL);
        ret.contents=Optional.of(s);
        return ret;
    }
    public static ClToken nl(){
        return new ClToken(Compilisp.Lexer.NEWLINE);
    }
    
}
