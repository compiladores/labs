package ar.uba.fi.compiladores.parte6;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;
import ar.uba.fi.compiladores.parte4.Numbersoup.TokenTypes;
import ar.uba.fi.compiladores.parte5.LexerException;

public class NumbersoupTest {

    @Test 
    public void testOtherTokensAsPrefixes() throws LexerException, IOException{
        Reader reader = new StringReader(" 0110 102 018F 0AFx010");
        Numbersoup lexer = new Numbersoup(reader);

        assertEquals(TokenTypes.BIN, lexer.yylex());
        assertEquals("0110", lexer.yytext());

        assertEquals(TokenTypes.DEC, lexer.yylex());
        assertEquals("102", lexer.yytext());
        
        assertEquals(TokenTypes.HEX, lexer.yylex());
        assertEquals("018F", lexer.yytext());

        assertEquals(TokenTypes.BINHEX, lexer.yylex());
        assertEquals("0AFx010", lexer.yytext());
    }
    @Test 
    public void testOtherTokensAsPostfixes() throws  LexerException, IOException{
        Reader reader = new StringReader(" 210 F801");
        Numbersoup lexer = new Numbersoup(reader);

        assertEquals(TokenTypes.DEC, lexer.yylex());
        assertEquals("210", lexer.yytext());

        assertEquals(TokenTypes.HEX, lexer.yylex());
        assertEquals("F801", lexer.yytext());
    }
    @Test
    public void testBadNumber() throws LexerException, IOException{
        Reader reader = new StringReader(" 0AFx0102");
        Numbersoup lexer = new Numbersoup(reader);
        assertThrows(LexerException.class,()->lexer.yylex());
    }
    @Test
    public void testBadCharacters() throws LexerException, IOException{
        Reader reader = new StringReader("ho1a");
        Numbersoup lexer = new Numbersoup(reader);
        assertThrows(LexerException.class,()->lexer.yylex());
    }
    
}
