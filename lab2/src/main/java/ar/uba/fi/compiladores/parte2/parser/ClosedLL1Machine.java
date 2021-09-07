package ar.uba.fi.compiladores.parte2.parser;

public interface ClosedLL1Machine {
    void match();
    void expToTermExp2();
    void exp2ToOpsumTermExp2();
    void exp2ToNothing();
    void opsumToPlus();
    void opsumToMinus();
    void termToFactorTerm2();
    void term2ToOpmultFactorTerm2();
    void term2ToNothing();
    void opmultToTimes();
    void factorToParenExpParen();
    void factorToNumber();
}
