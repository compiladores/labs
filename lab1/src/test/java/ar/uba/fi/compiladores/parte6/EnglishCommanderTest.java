package ar.uba.fi.compiladores.parte6;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import ar.uba.fi.compiladores.parte4.EnglishCommander.TokenTypes;
import ar.uba.fi.compiladores.parte5.LexerException;

public class EnglishCommanderTest {
    @Test(expected=LexerException.class) 
    public void testWrongCharacters() throws IOException, LexerException{
        Reader reader = new StringReader("12");
        EnglishCommander lexer = new EnglishCommander(reader);
        lexer.yylex();
    }
    @Test 
    public void testNoKeywords() throws IOException, LexerException{
        Reader reader = new StringReader(" DOING DONER DONN DOSE");
        EnglishCommander lexer = new EnglishCommander(reader);
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DOING",lexer.yytext());
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DONER",lexer.yytext());
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DONN",lexer.yytext());
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DOSE",lexer.yytext());
        assertEquals(null,lexer.yylex());
        assertTrue(lexer.yyatEOF());
    }
    @Test(expected=LexerException.class) 
    public void testNoNumbers() throws IOException, LexerException{
        Reader reader = new StringReader(" DOING DONER DONN DOSE 4 8 15 16 23 42");
        EnglishCommander lexer = new EnglishCommander(reader);
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DOING",lexer.yytext());
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DONER",lexer.yytext());
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DONN",lexer.yytext());
        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("DOSE",lexer.yytext());
        lexer.yylex();
    }

    @Test 
    public void testWithKeywords() throws IOException, LexerException{

        Reader reader = new StringReader(" DO ADORE DON ABANDONE DONE");
        EnglishCommander lexer = new EnglishCommander(reader);
        assertEquals(TokenTypes.DO,lexer.yylex());
        assertEquals("DO",lexer.yytext());

        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("ADORE",lexer.yytext());

        assertEquals(TokenTypes.DON,lexer.yylex());
        assertEquals("DON",lexer.yytext());

        assertEquals(TokenTypes.WORD,lexer.yylex());
        assertEquals("ABANDONE",lexer.yytext());

        assertEquals(TokenTypes.DONE,lexer.yylex());
        assertEquals("DONE",lexer.yytext());

        lexer.yyatEOF();
    }
}
