package ar.uba.fi.compiladores.parte6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import ar.uba.fi.compiladores.parte3.BrainfuckAdder.BrainfuckAdderTokens;
import ar.uba.fi.compiladores.parte5.LexerException;

public class BrainfuckAdderTest {
    
    @Test public void testSimpleBrainfuckStatement() throws LexerException, IOException{
        Reader reader = new StringReader(" +-++ - ++");
        BrainfuckAdder lexer = new BrainfuckAdder(reader);
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.INTEGER);
        assertEquals(lexer.yytext(),"+-++");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.DIFFERENCE);
        assertEquals(lexer.yytext(),"-");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.INTEGER);
        assertEquals(lexer.yytext(),"++");
        assertNull(lexer.yylex());
        assertTrue(lexer.yyatEOF());
    }

    @Test(expected = LexerException.class)
    public void testSimpleBadBrainfuckStatement() throws LexerException, IOException{
        Reader reader = new StringReader(" +-++ - peras ++");
        BrainfuckAdder lexer = new BrainfuckAdder(reader);
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.INTEGER);
        assertEquals(lexer.yytext(),"+-++");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.DIFFERENCE);
        assertEquals(lexer.yytext(),"-");
        lexer.yylex();
    }

    @Test public void testNonsenseStatement() throws LexerException, IOException{
        Reader reader = new StringReader(" + - ++ --");
        BrainfuckAdder lexer = new BrainfuckAdder(reader);

        assertEquals(lexer.yylex(),BrainfuckAdderTokens.ADDITION);
        assertEquals(lexer.yytext(),"+");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.DIFFERENCE);
        assertEquals(lexer.yytext(),"-");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.INTEGER);
        assertEquals(lexer.yytext(),"++");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.INTEGER);
        assertEquals(lexer.yytext(),"--");
        assertNull(lexer.yylex());
        assertTrue(lexer.yyatEOF());
        
    }

    @Test public void testNonsenseStatementWithBars() throws LexerException, IOException{
        Reader reader = new StringReader("||| +|- ++|--");
        BrainfuckAdder lexer = new BrainfuckAdder(reader);

        assertEquals(lexer.yylex(),BrainfuckAdderTokens.ADDITION);
        assertEquals(lexer.yytext(),"+");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.DIFFERENCE);
        assertEquals(lexer.yytext(),"-");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.INTEGER);
        assertEquals(lexer.yytext(),"++");
        assertEquals(lexer.yylex(),BrainfuckAdderTokens.INTEGER);
        assertEquals(lexer.yytext(),"--");
        assertNull(lexer.yylex());
        assertTrue(lexer.yyatEOF());
    }
}
