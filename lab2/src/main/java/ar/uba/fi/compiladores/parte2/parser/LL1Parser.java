package ar.uba.fi.compiladores.parte2.parser;

import ar.uba.fi.compiladores.parte1.TokenType;

public interface LL1Parser {

    void parse(TokenType stackToken, TokenType phraseToken, ClosedLL1Machine machine);

}
