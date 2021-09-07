package ar.uba.fi.compiladores.parte3;

/**
 * Interface que representa un conjunto de paráetros que, a su vez, 
 * representan el DFA de un lenguaje. El tipo S representa el tipo 
 * del estado utilizado y el tipo T representa el tipo utilizado 
 * para los tokens. Esta parametrización se utiliza para  que 
 * sea posible usar esta interfaz para todos los lenguajes.
 */
public interface LanguageAutomata<S,T> {
    S transition(S state, char  character);
    boolean isFinal(S state);
    S getDeadState();
    T stateToTokenType(S state);
    T eofTokenType();
    S getInitialState();
    S getErrorState();
}
