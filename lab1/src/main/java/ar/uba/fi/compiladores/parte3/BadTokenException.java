package ar.uba.fi.compiladores.parte3;

public class BadTokenException extends Exception{
    public BadTokenException(String tokenContent) {
        super("Bad token: "+tokenContent);
    } 
}
