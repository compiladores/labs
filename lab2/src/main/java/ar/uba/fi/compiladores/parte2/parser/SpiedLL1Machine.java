package ar.uba.fi.compiladores.parte2.parser;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

class SpiedLL1Machine implements ClosedLL1Machine{
    @NonNull
    private ClosedLL1Machine spied;
    @Getter
    private int calls=0;

    @Override
    public void match() {
        this.calls+=1;
        spied.match();
    }

    @Override
    public void expToTermExp2() {
        this.calls+=1;
        spied.expToTermExp2();
    }

    @Override
    public void exp2ToOpsumTermExp2() {
        this.calls+=1;
        spied.exp2ToOpsumTermExp2();
    }

    @Override
    public void exp2ToNothing() {
        this.calls+=1;
        spied.exp2ToNothing();
    }

    @Override
    public void opsumToPlus() {
        this.calls+=1;
        spied.opsumToPlus();
    }

    @Override
    public void opsumToMinus() {
        this.calls+=1;
        spied.opsumToMinus();
    }

    @Override
    public void termToFactorTerm2() {
        this.calls+=1;
        spied.termToFactorTerm2();
    }

    @Override
    public void term2ToOpmultFactorTerm2() {
        this.calls+=1;
        spied.term2ToOpmultFactorTerm2();
    }

    @Override
    public void term2ToNothing() {
        this.calls+=1;
        spied.term2ToNothing();
    }

    @Override
    public void opmultToTimes() {
        this.calls+=1;
        spied.opmultToTimes();
    }

    @Override
    public void factorToParenExpParen() {
        this.calls+=1;
        spied.factorToParenExpParen();
    }

    @Override
    public void factorToNumber() {
        this.calls+=1;
        spied.factorToNumber();
    }
}
