package ar.uba.fi.compiladores.parte1.applicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.uba.fi.compiladores.parte1.TokenType;

public abstract class Applicator {
    protected abstract void apply(TokenType nonTerminal,List<TokenType> produced);
    public void expToTermExp2(){
        apply(TokenType.EXP,Arrays.asList(TokenType.TERM,TokenType.EXP2));
    }
    public void exp2ToOpsumTermExp2(){
        apply(TokenType.EXP2,Arrays.asList(TokenType.OPSUM,TokenType.TERM,TokenType.EXP2));
    }
    public void exp2ToNothing(){
        apply(TokenType.EXP2,new ArrayList<>());
    }
    public void opsumToPlus(){
        apply(TokenType.OPSUM,Arrays.asList(TokenType.PLUS));
    }
    public void opsumToMinus(){
        apply(TokenType.OPSUM,Arrays.asList(TokenType.MINUS));
    }
    public void termToFactorTerm2(){
        apply(TokenType.TERM,Arrays.asList(TokenType.FACTOR,TokenType.TERM2));
    }
    public void term2ToOpmultFactorTerm2(){
        apply(TokenType.TERM2,Arrays.asList(TokenType.OPMULT,TokenType.FACTOR,TokenType.TERM2));
    }
    public void term2ToNothing(){
        apply(TokenType.TERM2,new ArrayList<>());
    }
    public void opmultToTimes(){
        apply(TokenType.OPMULT,Arrays.asList(TokenType.TIMES));
    }
    public void factorToParenExpParen(){
        apply(TokenType.FACTOR,Arrays.asList(TokenType.LEFT_PAREN,TokenType.EXP,TokenType.RIGHT_PAREN));
    }
    public void factorToNumber(){
        apply(TokenType.FACTOR,Arrays.asList(TokenType.NUMBER));
    }
}
