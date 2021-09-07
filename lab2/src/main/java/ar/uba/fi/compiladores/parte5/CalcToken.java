package ar.uba.fi.compiladores.parte5;

import java.util.Optional;

import lombok.Getter;

public class CalcToken implements GeneralToken<Number>{
    @Getter
    private int tokenType;
    @Getter
    private Optional<Number> contents = Optional.empty();
    public CalcToken(int tokenType){
        this.tokenType=tokenType;
    }
    public static CalcToken number(Number n){
        CalcToken ret = new CalcToken(Calc.Lexer.NUMBER);
        ret.contents=Optional.of(n);
        return ret;
    }
}
