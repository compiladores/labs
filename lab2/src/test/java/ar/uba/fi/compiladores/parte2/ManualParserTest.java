package ar.uba.fi.compiladores.parte2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ar.uba.fi.compiladores.parte1.TokenType;
import ar.uba.fi.compiladores.parte2.parser.ClosedLL1Machine;
import ar.uba.fi.compiladores.parte2.parser.LL1Machine;

public class ManualParserTest {
    @Test 
    public void testEx1(){
        LL1Machine m = new LL1Machine(Arrays.asList(TokenType.NUMBER));
        ClosedLL1Machine closed=m; //imposibilitar cambiar la firma
        ManualParser.ex1(closed);
        assertArrayEquals(new TokenType[]{
            TokenType.EXP,TokenType.TERM,TokenType.FACTOR,TokenType.NUMBER,TokenType.TERM2,TokenType.EXP2
        }, m.getPreorder().toArray());
    }
    @Test 
    public void testEx2(){
        LL1Machine m = new LL1Machine(Arrays.asList(new TokenType[]{
            TokenType.NUMBER,
            TokenType.PLUS,
            TokenType.NUMBER,
            TokenType.PLUS,
            TokenType.NUMBER,
        }));
        ClosedLL1Machine closed=m; //imposibilitar cambiar la firma
        ManualParser.ex2(closed);
        assertArrayEquals(new TokenType[]{
            TokenType.EXP,
                TokenType.TERM,
                    TokenType.FACTOR,
                        TokenType.NUMBER,
                    TokenType.TERM2,
                TokenType.EXP2,
                    TokenType.OPSUM,
                        TokenType.PLUS,
                    TokenType.TERM,
                        TokenType.FACTOR,
                            TokenType.NUMBER,
                        TokenType.TERM2,
                    TokenType.EXP2,
                        TokenType.OPSUM,
                            TokenType.PLUS,
                        TokenType.TERM,
                            TokenType.FACTOR,
                                TokenType.NUMBER,
                            TokenType.TERM2,
                        TokenType.EXP2,
        }, m.getPreorder().toArray());
    }
    @Test 
    public void testEx3(){
        LL1Machine m = new LL1Machine(Arrays.asList(new TokenType[]{
            TokenType.LEFT_PAREN,
            TokenType.NUMBER,
            TokenType.PLUS,
            TokenType.NUMBER,
            TokenType.RIGHT_PAREN,
        }));
        ClosedLL1Machine closed=m; //imposibilitar cambiar la firma
        ManualParser.ex3(closed);
        assertArrayEquals(new TokenType[]{
            TokenType.EXP,
                TokenType.TERM,
                    TokenType.FACTOR,
                        TokenType.LEFT_PAREN,
                        TokenType.EXP,
                            TokenType.TERM,
                                TokenType.FACTOR,
                                    TokenType.NUMBER,
                                TokenType.TERM2,
                            TokenType.EXP2,
                                TokenType.OPSUM,
                                    TokenType.PLUS,
                                TokenType.TERM,
                                    TokenType.FACTOR,
                                        TokenType.NUMBER,
                                    TokenType.TERM2,
                                TokenType.EXP2,
                        TokenType.RIGHT_PAREN,
                    TokenType.TERM2,
                TokenType.EXP2,
        }, m.getPreorder().toArray());
    }
}
