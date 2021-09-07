package ar.uba.fi.compiladores.parte4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ar.uba.fi.compiladores.parte4.parser.CompilispNoMatch;
import ar.uba.fi.compiladores.parte4.parser.CompilispParser;

public class CompiLispParserTest {
    @Test
    public void testStringAtom(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.STRING,"one")
        ), null);
        assertEquals("one",p.atom());
    }
    @Test
    public void testNumberAtom(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.NUMBER,"1")
        ), null);
        assertEquals(1,p.atom());
    }
    @Test
    public void testSymbolAtom(){
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("x",1);
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.SYMBOL,"x")
        ), context);
        assertEquals(1,p.atom());
    }
    @Test
    public void testEmptyList(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(Arrays.asList(),p.list());
    }
    @Test
    public void testSimpleList(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.NUMBER,"1"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(Arrays.asList(1,2,3),p.list());
    }
    @Test
    public void testDottedList(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.NUMBER,"1"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.DOT),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(Arrays.asList(1,2,3), p.list());
    }
    @Test
    public void testQuotedList(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.QUOTE),
            new ClToken(ClTokenType.NUMBER,"1")
        ), null);
        assertEquals(1, p.list());
    }
    @Test
    public void testStringsExp(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.STRING,"one")
        ), null);
        assertEquals("one",p.sExp());
    }
    @Test
    public void testNumbersExp(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.NUMBER,"1")
        ), null);
        assertEquals(1, p.sExp());
    }
    @Test
    public void testSymbolsExp(){
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("x",1);
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.SYMBOL,"x")
        ), context);
        assertEquals(1, p.sExp());
    }
    @Test
    public void testEmptyListsExp(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(Arrays.asList(),p.sExp());
    }

    @Test
    public void testQuotedListsExp(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.QUOTE),
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.NUMBER,"1"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(Arrays.asList(1,2,3),p.sExp());
    }

    @Test
    public void testFunctionExp(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.SYMBOL,"+"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals( 5,p.sExp());
    }
    @Test
    public void testFunctionTimesExp(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.SYMBOL,"*"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(6,p.sExp());
    }
    @Test
    public void testCalculationInQuotedList(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.QUOTE),
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.SYMBOL,"*"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(Arrays.asList(6,2,3),p.sExp());
    }
    @Test
    public void testFunctionInFunction(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.SYMBOL,"+"),
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.SYMBOL,"*"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertEquals(11,p.sExp());
    }
    @Test
    public void testUnmatchedParens(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.QUOTE),
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.SYMBOL,"*"),
            new ClToken(ClTokenType.NUMBER,"2"),
            new ClToken(ClTokenType.NUMBER,"3"),
            new ClToken(ClTokenType.RIGHT_PAREN)
        ), null);
        assertThrows(CompilispNoMatch.class, ()->p.sExp());
    }
    
    @Test
    public void testLineCalculated(){
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("y","x");
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.QUOTE),
            new ClToken(ClTokenType.LEFT_PAREN),
            new ClToken(ClTokenType.STRING,"one"),
            new ClToken(ClTokenType.NUMBER,"1"),
            new ClToken(ClTokenType.SYMBOL,"y"),
            new ClToken(ClTokenType.RIGHT_PAREN),
            new ClToken(ClTokenType.NEWLINE)
        ), context);
        assertEquals("( one 1 x )\n", p.line());
    }

    @Test
    public void testLineWithoutEndline(){
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.NUMBER,"3")
        ), null);
        assertThrows(CompilispNoMatch.class, ()->p.line());
    }

    @Test
    public void testMultilineInput(){
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("y","x");
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.NUMBER,"1"),
            new ClToken(ClTokenType.NEWLINE),
            new ClToken(ClTokenType.SYMBOL,"y"),
            new ClToken(ClTokenType.NEWLINE),
            new ClToken(ClTokenType.STRING,"one"),
            new ClToken(ClTokenType.NEWLINE)
        ), context);
        assertEquals("1\nx\none\n", p.input());
    }

    @Test
    public void testTwoValuesInSameLine(){
        Map<String, Object> context = new HashMap<String,Object>();
        context.put("y","x");
        CompilispParser p = new CompilispParser(Arrays.asList(
            new ClToken(ClTokenType.NUMBER,"1"),
            new ClToken(ClTokenType.SYMBOL,"y"),
            new ClToken(ClTokenType.NEWLINE),
            new ClToken(ClTokenType.STRING,"one"),
            new ClToken(ClTokenType.NEWLINE)
        ), context);
        assertThrows(CompilispNoMatch.class, ()->p.input());
    }
}
