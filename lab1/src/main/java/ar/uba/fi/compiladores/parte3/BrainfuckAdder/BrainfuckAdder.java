package ar.uba.fi.compiladores.parte3.BrainfuckAdder;

import ar.uba.fi.compiladores.parte3.LanguageAutomata;
import java.util.stream.Stream;

public class BrainfuckAdder implements LanguageAutomata<BrainfuckAdderState,BrainfuckAdderTokens> {

    @Override
    public BrainfuckAdderState transition(BrainfuckAdderState state, char character) {
        switch(state){
            case INITIAL: switch(character){
                case '+': return BrainfuckAdderState.ADDITION;
                case '-': return BrainfuckAdderState.DIFFERENCE;
                case ' ':
                case '|':
                return BrainfuckAdderState.DEAD;
                default : return BrainfuckAdderState.ERROR;
            }
            case INTEGER:
            case DIFFERENCE:
            case ADDITION: switch(character){
                case '+':
                case '-':
                return BrainfuckAdderState.INTEGER;
                case ' ':
                case '|':
                return BrainfuckAdderState.DEAD;
                default : return BrainfuckAdderState.ERROR;
            }
            case ERROR:
            if(character==' ' || character=='|'){
                return BrainfuckAdderState.DEAD;
            }else{
                return BrainfuckAdderState.ERROR;
            }
            default: return BrainfuckAdderState.ERROR;
        }
    }

    @Override
    public boolean isFinal(BrainfuckAdderState state) {
        return Stream.of(
            BrainfuckAdderState.ADDITION,
            BrainfuckAdderState.DEAD,
            BrainfuckAdderState.DIFFERENCE,
            BrainfuckAdderState.INTEGER,
            BrainfuckAdderState.ERROR

        ).anyMatch(finalState -> finalState.equals(state));
    }

    @Override
    public BrainfuckAdderTokens stateToTokenType(BrainfuckAdderState state) {
        switch(state){
            case ADDITION:return BrainfuckAdderTokens.ADDITION;
            case DEAD: return null;
            case DIFFERENCE: return BrainfuckAdderTokens.DIFFERENCE;
            case INITIAL: return null;
            case INTEGER: return BrainfuckAdderTokens.INTEGER;
            default: return null;
        }
    }

    @Override
    public BrainfuckAdderState getInitialState() {
        return BrainfuckAdderState.INITIAL;
    }

    @Override
    public BrainfuckAdderState getErrorState() {
        return BrainfuckAdderState.ERROR;
    }

    @Override
    public BrainfuckAdderTokens eofTokenType() {
        return BrainfuckAdderTokens.EOF;
    }

    @Override
    public BrainfuckAdderState getDeadState() {
        return BrainfuckAdderState.DEAD;
    }
    
}
