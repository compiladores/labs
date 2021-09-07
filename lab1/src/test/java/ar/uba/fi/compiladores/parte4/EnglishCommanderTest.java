package ar.uba.fi.compiladores.parte4;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.uba.fi.compiladores.parte3.BadTokenException;
import ar.uba.fi.compiladores.parte3.ManualLexer;
import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte4.EnglishCommander.Automata;
import ar.uba.fi.compiladores.parte4.EnglishCommander.State;
import ar.uba.fi.compiladores.parte4.EnglishCommander.TokenTypes;

public class EnglishCommanderTest {
    Automata language = new Automata();
    ManualLexer<State,TokenTypes> lexer = new ManualLexer<State,TokenTypes>(language);

    @Test(expected=BadTokenException.class)
    public void testWrongCharacters() throws BadTokenException{
        lexer.lex("12");
    }
    @Test 
    public void testNoKeywords() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.WORD,"DOING"),
            new Token<>(TokenTypes.WORD,"DONER"),
            new Token<>(TokenTypes.WORD,"DONN"),
            new Token<>(TokenTypes.WORD,"DOSE")
        );
        assertEquals(expected, lexer.lex(" DOING DONER DONN DOSE"));
    }
    @Test(expected=BadTokenException.class)
    public void testNoNumbers() throws BadTokenException{
        lexer.lex(" DOING DONER DONN DOSE 4 8 15 16 23 42");
    }

    @Test 
    public void testWithKeywords() throws BadTokenException{
        List<Token<TokenTypes>> expected = Arrays.asList(
            new Token<>(TokenTypes.DO,"DO"),
            new Token<>(TokenTypes.WORD,"ADORE"),
            new Token<>(TokenTypes.DON,"DON"),
            new Token<>(TokenTypes.WORD,"ABANDONE"),
            new Token<>(TokenTypes.DONE,"DONE")
        );
        assertEquals(expected, lexer.lex(" DO ADORE DON ABANDONE DONE"));
    }
}
