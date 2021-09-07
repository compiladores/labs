package ar.uba.fi.compiladores.parte4;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ar.uba.fi.compiladores.parte3.BadTokenException;
import ar.uba.fi.compiladores.parte3.ManualLexer;
import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte4.Lol.Automata;
import ar.uba.fi.compiladores.parte4.Lol.State;
import ar.uba.fi.compiladores.parte4.Lol.TokenTypes;

public class LolTest {
    Automata language = new Automata();
    ManualLexer<State,TokenTypes> lexer = new ManualLexer<State,TokenTypes>(language);

    @ParameterizedTest
    @ValueSource(strings={
        "hola",
        "PALARALA",
        "PERELE",
        "PIRILI",
        "POROLO",
        "PAPE",
        "LALI",
        "LIPA",
        "PALO",
        "3",
        "!?=",
    })
    public void badToken(String token) throws BadTokenException{
        assertThrows(BadTokenException.class, ()->lexer.lex(token));
    }


    @Test 
    public void testSmallTokens() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.LAL,"PA"),
            new Token<>(TokenTypes.LEL,"PE"),
            new Token<>(TokenTypes.LIL,"LI"),
            new Token<>(TokenTypes.LOL,"LO")
        );
        assertEquals(expected, lexer.lex(" PA PE LI LO"));
    }

    @Test 
    public void testLargeTokens() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.LAL,"PARARARAPAPAPAPARA"),
            new Token<>(TokenTypes.LEL,"PEPE"),
            new Token<>(TokenTypes.LIL,"LIRIRIRIRILILI"),
            new Token<>(TokenTypes.LOL,"LOLOLOLOLO")
        );
        assertEquals(expected, lexer.lex(" PARARARAPAPAPAPARA PEPE LIRIRIRIRILILI LOLOLOLOLO"));
    }
}
