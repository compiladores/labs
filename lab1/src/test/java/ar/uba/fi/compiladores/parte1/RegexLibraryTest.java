package ar.uba.fi.compiladores.parte1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Parte 1 .
 */
public class RegexLibraryTest 
{
    RegexLibrary regexLibrary = new RegexLibrary();

    boolean matchDecimal(String s){
        return regexLibrary.getDecimalsRegex().matcher(s).matches();
    }
    @Test public void decimals_1() { assertTrue(matchDecimal("254")); }
    @Test public void decimals_2() { assertFalse(matchDecimal("00001")); }
    @Test public void decimals_3() { assertFalse(matchDecimal("22 23 24")); }
    @Test public void decimals_4() { assertTrue(matchDecimal("1234567890")); }
    @Test public void decimals_5() { assertFalse(matchDecimal("")); }

    boolean matchHexa(String s){
        return regexLibrary.getHexaRegex().matcher(s).matches();
    }
    @Test public void hexa_1() { assertTrue(matchHexa("2FB54")); }
    @Test public void hexa_2() { assertFalse(matchHexa("000FB01")); }
    @Test public void hexa_3() { assertFalse(matchHexa("22 23 FB 24")); }
    @Test public void hexa_4() { assertTrue(matchHexa("1234567890ABCDEF")); }
    @Test public void hexa_5() { assertFalse(matchHexa("")); }

    boolean matchBrainfucks(String s){
        return regexLibrary.getBrainfuckRegex().matcher(s).matches();
    }
    @Test public void brainfuck_1() { assertTrue(matchBrainfucks("+--++-+++")); }
    @Test public void brainfuck_2() { assertFalse(matchBrainfucks("+ + -")); }
    @Test public void brainfuck_3() { assertFalse(matchBrainfucks("   -  ")); }
    @Test public void brainfuck_4() { assertTrue(matchBrainfucks("-")); }
    @Test public void brainfuck_5() { assertTrue(matchBrainfucks("+")); }
    @Test public void brainfuck_6() { assertFalse(matchBrainfucks("")); }

    boolean matchNames(String s){
        return regexLibrary.getNamesRegex().matcher(s).matches();
    }
    @Test public void matchNames_1() { assertTrue(matchNames("Alejandro")); }
    @Test public void matchNames_2() { assertFalse(matchNames("ana")); }
    @Test public void matchNames_3() { assertFalse(matchNames("Op")); }
    @Test public void matchNames_4() { assertTrue(matchNames("Ana")); }
    @Test public void matchNames_5() { assertFalse(matchNames("GERMAN")); }
    @Test public void matchNames_6() { assertFalse(matchNames("")); }
    @Test public void matchNames_7() { assertFalse(matchNames(" Ana ")); }
}
