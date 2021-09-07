package ar.uba.fi.compiladores.parte7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import ar.uba.fi.compiladores.parte3.Token;
import ar.uba.fi.compiladores.parte5.LexerException;

public class CompilispTest {
    @Test
    public void testNumber() throws IOException, LexerException{
        Reader reader = new StringReader("8");
        Compilisp lexer = new Compilisp(reader);
        Token<CompilispTokenTypes> lexed = lexer.yylex();
        assertEquals(CompilispTokenTypes.NUMBER, lexed.getType());
        assertEquals("8", lexed.getContents());
    }

    @Test
    public void testApplication() throws IOException, LexerException{
        Reader reader = new StringReader("( - 8 + 7 si)");
        Compilisp lexer = new Compilisp(reader);
        assertEquals(CompilispTokenTypes.LEFT_PAREN, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.NAME, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.NUMBER, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.NAME, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.NUMBER, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.NAME, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.RIGHT_PAREN, lexer.yylex().getType());
    }

    @Test
    public void testWhitespaces() throws IOException, LexerException{
        Reader reader = new StringReader(" ( \n\n\t )");
        Compilisp lexer = new Compilisp(reader);
        assertEquals(CompilispTokenTypes.LEFT_PAREN, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.RIGHT_PAREN, lexer.yylex().getType());
    }

    @Test
    public void testComments() throws IOException, LexerException{
        Reader reader = new StringReader(" ( \n // 78 90 \n\t )");
        Compilisp lexer = new Compilisp(reader);
        assertEquals(CompilispTokenTypes.LEFT_PAREN, lexer.yylex().getType());
        assertEquals(CompilispTokenTypes.RIGHT_PAREN, lexer.yylex().getType());
    }

    @Test
    public void testStrings() throws IOException, LexerException{
        Reader reader = new StringReader("\"one\" \"a\\bar\" \"with \\\"a\\\" subs\" ");
        Compilisp lexer = new Compilisp(reader);
        assertEquals("one", lexer.yylex().getContents());
        assertEquals("a\\bar", lexer.yylex().getContents());
        assertEquals("with \"a\" subs", lexer.yylex().getContents());
    }
}
