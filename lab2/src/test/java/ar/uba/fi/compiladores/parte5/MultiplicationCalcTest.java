package ar.uba.fi.compiladores.parte5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MultiplicationCalcTest {
    @Test
    public void testSimpleMultiplication() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(
                CalcToken.number(2),
                new CalcToken(CalcFakeLexer.TIMES),
                CalcToken.number(3)
            )
        ));
        calc.parse();
        assertEquals(6,calc.value);
    }

    @Test
    public void testMultipleMultiplication() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(
                CalcToken.number(2),
                new CalcToken(CalcFakeLexer.TIMES),
                CalcToken.number(3),
                new CalcToken(CalcFakeLexer.TIMES),
                CalcToken.number(4)
            )
        ));
        calc.parse();
        assertEquals(24,calc.value);
    }

    @Test
    public void testTermPriorityTimesFirst() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(
                CalcToken.number(2),
                new CalcToken(CalcFakeLexer.TIMES),
                CalcToken.number(3),
                new CalcToken(CalcFakeLexer.MINUS),
                CalcToken.number(4)
            )
        ));
        calc.parse();
        assertEquals(2,calc.value);
    }
    @Test
    public void testTermPriorityTimesLater() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(
                CalcToken.number(1),
                new CalcToken(CalcFakeLexer.MINUS),
                CalcToken.number(2),
                new CalcToken(CalcFakeLexer.TIMES),
                CalcToken.number(3),
                new CalcToken(CalcFakeLexer.MINUS),
                CalcToken.number(4)
            )
        ));
        calc.parse();
        assertEquals(-9,calc.value);
    }

    @Test
    public void testParensWithAddition() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(
                CalcToken.number(1),
                new CalcToken(CalcFakeLexer.MINUS),
                new CalcToken(CalcFakeLexer.L_PAREN),
                CalcToken.number(2),
                new CalcToken(CalcFakeLexer.PLUS),
                CalcToken.number(3),
                new CalcToken(CalcFakeLexer.R_PAREN),
                new CalcToken(CalcFakeLexer.TIMES),
                CalcToken.number(3)
            )
        ));
        calc.parse();
        assertEquals(-14,calc.value);
    }

    @Test
    public void testNumberInsideParens() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(
                new CalcToken(CalcFakeLexer.L_PAREN),
                CalcToken.number(42),
                new CalcToken(CalcFakeLexer.R_PAREN)
            )
        ));
        calc.parse();
        assertEquals(42,calc.value);
    }
    @Test
    public void testParensInsideParens() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(
                new CalcToken(CalcFakeLexer.L_PAREN),
                CalcToken.number(1),
                new CalcToken(CalcFakeLexer.PLUS),
                new CalcToken(CalcFakeLexer.L_PAREN),
                CalcToken.number(2),
                new CalcToken(CalcFakeLexer.PLUS),
                CalcToken.number(3),
                new CalcToken(CalcFakeLexer.R_PAREN),
                new CalcToken(CalcFakeLexer.R_PAREN)
            )
        ));
        calc.parse();
        assertEquals(6,calc.value);
    }
}
