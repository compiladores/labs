package ar.uba.fi.compiladores.parte6;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ar.uba.fi.compiladores.parte4.Lol.TokenTypes;
import ar.uba.fi.compiladores.parte5.LexerException;

public class LolTest {
    
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
    public void badToken(String badToken) throws IOException{
        Reader reader = new StringReader(badToken);
        Lol lexer = new Lol(reader);
        assertThrows(LexerException.class, () -> {
            lexer.yylex();
        });
    }

    @Test 
    public void testSmallTokens() throws LexerException, IOException{
        Reader reader = new StringReader(" PA PE LI LO");
        Lol lexer = new Lol(reader);

        assertEquals(TokenTypes.LAL, lexer.yylex());
        assertEquals("PA", lexer.yytext());

        assertEquals(TokenTypes.LEL, lexer.yylex());
        assertEquals("PE", lexer.yytext());

        assertEquals(TokenTypes.LIL, lexer.yylex());
        assertEquals("LI", lexer.yytext());

        assertEquals(TokenTypes.LOL, lexer.yylex());
        assertEquals("LO", lexer.yytext());
    }

    @Test 
    public void testLargeTokens() throws LexerException, IOException{
        Reader reader = new StringReader(" PARARARAPAPAPAPARA PEPE LIRIRIRIRILILI LOLOLOLOLO");
        Lol lexer = new Lol(reader);

        assertEquals(TokenTypes.LAL, lexer.yylex());
        assertEquals("PARARARAPAPAPAPARA", lexer.yytext());

        assertEquals(TokenTypes.LEL, lexer.yylex());
        assertEquals("PEPE", lexer.yytext());

        assertEquals(TokenTypes.LIL, lexer.yylex());
        assertEquals("LIRIRIRIRILILI", lexer.yytext());

        assertEquals(TokenTypes.LOL, lexer.yylex());
        assertEquals("LOLOLOLOLO", lexer.yytext());
    }
}
