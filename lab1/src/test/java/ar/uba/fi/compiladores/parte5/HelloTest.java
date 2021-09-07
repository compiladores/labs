package ar.uba.fi.compiladores.parte5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class HelloTest {
    @Test
    public void testItIsGenerated() throws IOException, LexerException {
        Reader reader = new StringReader(" hello world ");
        Hello hello = new Hello(reader);
        assertEquals(hello.yylex(),HelloToken.HELLO);
        assertEquals(hello.yytext(),"hello");
        assertEquals(hello.yylex(),HelloToken.WORLD);
        assertEquals(hello.yytext(),"world");
        assertNull(hello.yylex());
        assertTrue(hello.yyatEOF());
    }

    @Test
    public void testNumbersAreLexed() throws IOException, LexerException {
        Reader reader = new StringReader(" hello 1 world 25 087");
        Hello hello = new Hello(reader);
        assertEquals(hello.yylex(),HelloToken.HELLO);
        assertEquals(hello.yytext(),"hello");
        assertEquals(hello.yylex(),HelloToken.NUMBER);
        assertEquals(hello.yytext(),"1");
        assertEquals(hello.yylex(),HelloToken.WORLD);
        assertEquals(hello.yytext(),"world");
        assertEquals(hello.yylex(),HelloToken.NUMBER);
        assertEquals(hello.yytext(),"25");
        assertEquals(hello.yylex(),HelloToken.NUMBER);
        assertEquals(hello.yytext(),"087");
        assertNull(hello.yylex());
        assertTrue(hello.yyatEOF());
    }

    @Test(expected = LexerException.class)
    public void testBadCharacter() throws IOException, LexerException {
        Reader reader = new StringReader(" hello q");
        Hello hello = new Hello(reader);
        assertEquals(hello.yylex(),HelloToken.HELLO);
        assertEquals(hello.yytext(),"hello");
        hello.yylex();
    }

    @Test
    public void testEmptyMatchesNothing() throws IOException, LexerException {
        Reader reader = new StringReader("");
        Hello hello = new Hello(reader);
        assertNull(hello.yylex());
        assertTrue(hello.yyatEOF());
    }
}