package ar.uba.fi.compiladores.parte3.BrainfuckAdder;

public enum BrainfuckAdderState {
    INITIAL,
    ADDITION,
    DIFFERENCE,
    INTEGER,
    DEAD, 
    ERROR,
}
