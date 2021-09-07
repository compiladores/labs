package ar.uba.fi.compiladores.parte5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class AdditionCalcTest {
    @Test
    public void testSimpleAddition() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(CalcToken.number(1),new CalcToken(CalcFakeLexer.PLUS),CalcToken.number(2))
        ));
        calc.parse();
        assertEquals(3,calc.value);
    }

    @Test
    public void testSubstraction() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(CalcToken.number(1),new CalcToken(CalcFakeLexer.MINUS),CalcToken.number(2))
        ));
        calc.parse();
        assertEquals(-1,calc.value);
    }

    @Test
    public void testAdditionAndSubstraction() throws IOException{
        Calc calc = new Calc(new CalcFakeLexer(
            Arrays.asList(CalcToken.number(1),new CalcToken(CalcFakeLexer.MINUS),CalcToken.number(2),new CalcToken(CalcFakeLexer.PLUS),CalcToken.number(10))
        ));
        calc.parse();
        assertEquals(9,calc.value);
    }
}
