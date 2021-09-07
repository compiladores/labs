package ar.uba.fi.compiladores.parte4;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.uba.fi.compiladores.parte3.BadTokenException;
import ar.uba.fi.compiladores.parte3.ManualLexer;
import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte4.Numbersoup.Automata;
import ar.uba.fi.compiladores.parte4.Numbersoup.State;
import ar.uba.fi.compiladores.parte4.Numbersoup.TokenTypes;

public class NumbersoupTest {
    Automata language = new Automata();
    ManualLexer<State,TokenTypes> lexer = new ManualLexer<State,TokenTypes>(language);

    @Test void testOtherTokensAsPrefixes() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.BIN,"0110"),
            new Token<>(TokenTypes.DEC,"102"),
            new Token<>(TokenTypes.HEX,"018F"),
            new Token<>(TokenTypes.BINHEX,"0AFx010")
        );
        assertEquals(expected, lexer.lex(" 0110 102 018F 0AFx010"));
    }
    @Test void testOtherTokensAsPostfixes() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.DEC,"210"),
            new Token<>(TokenTypes.HEX,"F801")
        );
        assertEquals(expected, lexer.lex(" 210 F801"));
    }
    @Test(expected = BadTokenException.class) void testBadNumber() throws BadTokenException{
        lexer.lex(" 0AFx0102");
    }
    @Test(expected = BadTokenException.class) void testBadCharacters() throws BadTokenException{
        lexer.lex("ho1a");
    }
    
}
