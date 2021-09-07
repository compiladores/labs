package ar.uba.fi.compiladores.parte6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ar.uba.fi.compiladores.parte5.GeneralToken;

public class CompiLispTest {
    @SafeVarargs
    private final void test(Object expected, Map<String, Object> context, GeneralToken<Object>... tokens) throws IOException{
        Compilisp p = new Compilisp(new ClFakeLexer(Arrays.asList(
            tokens
        )), context);
        p.parse();
        assertEquals(expected,p.getValue());
    }
    @Test
    public void testStringAtom() throws IOException{
        test("one\n",null,ClToken.str("one"),ClToken.nl());
    }
    @Test
    public void testNumberAtom() throws IOException{
        test("1\n",null,ClToken.number(1),ClToken.nl());
    }
    @Test
    public void testSymbolAtom() throws IOException{
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("x",1);
        test("1\n",context,ClToken.sym("x"),ClToken.nl());
    }
    @Test
    public void testEmptyList() throws IOException{
        test("( )\n",null,
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }
    @Test
    public void testSimpleList() throws IOException{
        test("( 1 2 3 )\n",null,
            new ClToken(Compilisp.Lexer.QUOTE),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.number(1),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }
    @Test
    public void testDottedList() throws IOException{
        test("( 1 2 3 )\n",null,
            new ClToken(Compilisp.Lexer.QUOTE),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.number(1),
            ClToken.number(2),
            new ClToken(Compilisp.Lexer.DOT),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }
    @Test
    public void testQuotedList() throws IOException{
        test("1\n", null,
            new ClToken(Compilisp.Lexer.QUOTE),
            ClToken.number(1),
            ClToken.nl()
        );
    }
    @Test
    public void testQuotedListMultiple() throws IOException{
        test("( 1 2 3 )\n", null,
            new ClToken(Compilisp.Lexer.QUOTE),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.number(1),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }

    @Test
    public void testFunctionExp() throws IOException{
        test("5\n", null,
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.sym("+"),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }
    @Test
    public void testFunctionTimesExp() throws IOException{
        test("6\n", null,
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.sym("*"),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }
    @Test
    public void testCalculationInQuotedList() throws IOException{
        test("( 6 2 3 )\n", null,
            new ClToken(Compilisp.Lexer.QUOTE),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.sym("*"),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }
    @Test
    public void testFunctionInFunction() throws IOException{
        test("11\n", null,
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.sym("+"),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.sym("*"),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }
    @Test
    public void testUnmatchedParens() throws IOException{
        assertThrows(CompilispBisonParserException.class, ()->test("11\n", null,
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.sym("+"),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.sym("*"),
            ClToken.number(2),
            ClToken.number(3),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        ));
    }
    
    @Test
    public void testLineCalculated() throws IOException{
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("y","x");
        test("( one 1 x )\n", context,
            new ClToken(Compilisp.Lexer.QUOTE),
            new ClToken(Compilisp.Lexer.LEFT_PAREN),
            ClToken.str("one"),
            ClToken.number(1),
            ClToken.sym("y"),
            new ClToken(Compilisp.Lexer.RIGHT_PAREN),
            ClToken.nl()
        );
    }

    @Test
    public void testLineWithoutEndline() throws IOException{
        ;
        assertThrows(CompilispBisonParserException.class, ()->test("( one 1 x )\n", null,ClToken.number(1)));
    }

    @Test
    public void testMultilineInput() throws IOException{
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("y","x");
        test("1\nx\none\n", context,
            ClToken.number(1),
            ClToken.nl(),
            ClToken.sym("y"),
            ClToken.nl(),
            ClToken.str("one"),
            ClToken.nl()
        );
    }

    @Test
    public void testTwoValuesInSameLine() throws IOException{
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("y","x");
        assertThrows(CompilispBisonParserException.class, ()->test("1\nx\none\n", context,
            ClToken.number(1),
            ClToken.sym("y"),
            ClToken.nl(),
            ClToken.str("one"),
            ClToken.nl()
        ));
    }
}
