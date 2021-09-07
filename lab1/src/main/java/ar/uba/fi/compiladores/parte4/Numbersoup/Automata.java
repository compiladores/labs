package ar.uba.fi.compiladores.parte4.Numbersoup;

import ar.uba.fi.compiladores.parte3.LanguageAutomata;

public class Automata implements LanguageAutomata<State,TokenTypes> {

    @Override
    public State transition(State state, char character) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFinal(State state) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public State getDeadState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TokenTypes stateToTokenType(State state) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TokenTypes eofTokenType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State getInitialState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State getErrorState() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
