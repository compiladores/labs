package ar.uba.fi.compiladores.parte3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ar.uba.fi.compiladores.parte3.BrainfuckAdder.BrainfuckAdder;
import ar.uba.fi.compiladores.parte3.BrainfuckAdder.BrainfuckAdderState;
import ar.uba.fi.compiladores.parte3.BrainfuckAdder.BrainfuckAdderTokens;

public class ManualLexerTest {
    BrainfuckAdder language = new BrainfuckAdder();
    ManualLexer<BrainfuckAdderState,BrainfuckAdderTokens> lexer = new ManualLexer<BrainfuckAdderState,BrainfuckAdderTokens>(language);

    @Test public void testGetOneLeadingWhitespace(){
        SingleTokenExtraction<BrainfuckAdderState> result = lexer.extractToken("   +-++ - ++");
        assertEquals(0,result.getLastFinalIndex());
        assertEquals(BrainfuckAdderState.DEAD,result.getFinalState());
    }

    @Test public void testExtractSingleToken(){
        SingleTokenExtraction<BrainfuckAdderState> result = lexer.extractToken("+");
        assertEquals(0,result.getLastFinalIndex());
        assertEquals(BrainfuckAdderState.ADDITION,result.getFinalState());
    }

    @Test public void testGetOneSingleLeadingWhitespace(){
        SingleTokenExtraction<BrainfuckAdderState> result = lexer.extractToken(" +-++ - ++");
        assertEquals(0,result.getLastFinalIndex());
        assertEquals(BrainfuckAdderState.DEAD,result.getFinalState());
    }

    @Test public void testGetOneNoLeadingWhitespace(){
        SingleTokenExtraction<BrainfuckAdderState> result = lexer.extractToken("+-++ - ++");
        assertEquals(3,result.getLastFinalIndex());
        assertEquals(BrainfuckAdderState.INTEGER,result.getFinalState());
    }

    @Test public void testGetFailure(){
        SingleTokenExtraction<BrainfuckAdderState> result = lexer.extractToken("HOLI - ++");
        assertEquals(3,result.getLastFinalIndex());
        assertEquals(BrainfuckAdderState.ERROR,result.getFinalState());
    }

    @Test public void testSimpleBrainfuckStatement() throws BadTokenException{
        List<Token<BrainfuckAdderTokens>> expected = Arrays.asList(
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.INTEGER,"+-++"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.DIFFERENCE,"-"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.INTEGER,"++"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.EOF,null)
        );
        assertEquals(expected, lexer.lex(" +-++ - ++"));
    }

    @Test(expected = BadTokenException.class)
    public void testSimpleBadBrainfuckStatement() throws BadTokenException{
        assertEquals(null, lexer.lex(" +-++ - peras ++"));
    }

    @Test public void testNonsenseStatement() throws BadTokenException{
        List<Token<BrainfuckAdderTokens>> expected = Arrays.asList(
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.ADDITION,"+"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.DIFFERENCE,"-"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.INTEGER,"++"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.INTEGER,"--"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.EOF,null)
        );
        assertEquals(expected, lexer.lex(" + - ++ --"));
    }

    @Test public void testNonsenseStatementWithBars() throws BadTokenException{
        List<Token<BrainfuckAdderTokens>> expected = Arrays.asList(
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.ADDITION,"+"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.DIFFERENCE,"-"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.INTEGER,"++"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.INTEGER,"--"),
            new Token<BrainfuckAdderTokens>(BrainfuckAdderTokens.EOF,null)
        );
        assertEquals(expected, lexer.lex("||| +|- ++|--"));
    }
}
