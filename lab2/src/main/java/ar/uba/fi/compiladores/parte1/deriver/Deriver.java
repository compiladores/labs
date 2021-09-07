package ar.uba.fi.compiladores.parte1.deriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import ar.uba.fi.compiladores.parte1.TokenType;
import ar.uba.fi.compiladores.parte1.applicator.Applicator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Deriver extends Applicator{
    @Getter
    private List<TokenType> phrase;
    protected void apply(TokenType nonTerminal,List<TokenType> produced){
        TokenType firstNonTerminal = getFirstNonTerminal();
        if(firstNonTerminal != nonTerminal){
            throw new RuntimeException("Esta regla se aplica a un token que no es el primer no-terminal. Se aplica una producción a un token "+nonTerminal.toString()+" pero debe aplicarse a un token de tipo "+firstNonTerminal.toString());
        }
        int nonTerminalIndex = IntStream
            .range(0,phrase.size())
            .filter( i -> phrase.get(i).equals(nonTerminal))
            .findFirst()
            .getAsInt();
        List<TokenType> newPhrase = new ArrayList<>();
        List<TokenType> start = phrase.subList(0, nonTerminalIndex);
        List<TokenType> rest = phrase.subList(nonTerminalIndex+1, phrase.size());
        newPhrase.addAll(start);
        newPhrase.addAll(produced);
        newPhrase.addAll(rest);
        phrase=newPhrase;
    }
    private TokenType getFirstNonTerminal(){
        List<TokenType> nonTerminals = Arrays.asList(
            TokenType.EXP,
            TokenType.EXP2,
            TokenType.FACTOR,
            TokenType.OPMULT,
            TokenType.OPSUM,
            TokenType.TERM,
            TokenType.TERM2
        );
        return phrase.stream().filter(token->nonTerminals.contains(token)).findFirst().get();
    }
}
