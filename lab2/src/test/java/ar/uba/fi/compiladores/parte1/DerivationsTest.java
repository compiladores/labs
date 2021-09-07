package ar.uba.fi.compiladores.parte1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ar.uba.fi.compiladores.parte1.deriver.Deriver;

public class DerivationsTest {
    @Test 
    public void testEx1(){
        ArrayList<TokenType> list = new ArrayList<>();
        list.add(TokenType.EXP);
        Deriver d = new Deriver(list);
        Derivations.ex1(d);
        assertArrayEquals(new TokenType[]{
            TokenType.NUMBER,
        }, d.getPhrase().toArray());
    }
    @Test 
    public void testEx2(){
        ArrayList<TokenType> list = new ArrayList<>();
        list.add(TokenType.EXP);
        Deriver d = new Deriver(list);
        Derivations.ex2(d);
        assertArrayEquals(new TokenType[]{
            TokenType.NUMBER,
            TokenType.PLUS,
            TokenType.NUMBER,
            TokenType.PLUS,
            TokenType.NUMBER,
        }, d.getPhrase().toArray());
    }
    @Test 
    public void testEx3(){
        ArrayList<TokenType> list = new ArrayList<>();
        list.add(TokenType.EXP);
        Deriver d = new Deriver(list);
        Derivations.ex3(d);
        assertArrayEquals(new TokenType[]{
            TokenType.LEFT_PAREN,
            TokenType.NUMBER,
            TokenType.PLUS,
            TokenType.NUMBER,
            TokenType.RIGHT_PAREN,
        }, d.getPhrase().toArray());
    }
}
