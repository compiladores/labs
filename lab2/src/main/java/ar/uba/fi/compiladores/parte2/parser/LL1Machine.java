package ar.uba.fi.compiladores.parte2.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import ar.uba.fi.compiladores.parte1.TokenType;
import ar.uba.fi.compiladores.parte1.applicator.Applicator;
import lombok.Getter;
import lombok.NonNull;
public class LL1Machine extends Applicator implements ClosedLL1Machine{
    private Stack<Tree> stack = new Stack<Tree>();
    @Getter
    @NonNull
    private ArrayList<TokenType> phrase;
    private Tree trunk = new Tree(TokenType.EXP);

    public LL1Machine(List<TokenType> phrase){
        this.phrase=new ArrayList<>(phrase);
        stack.push(trunk);
    }

    @Override
    protected void apply(TokenType nonTerminal, List<TokenType> produced) {
        Tree top = stack.pop();
        if(top.getTokenType() != nonTerminal){
            throw new RuntimeException(nonTerminal.toString()+" no es el símbolo de la punta de la pila. Desapilar "+top.getTokenType().toString());
        }
        ArrayList<TokenType> producedCopy = new ArrayList<>(produced);
        Collections.reverse(producedCopy);
        for(TokenType producedTokenType:producedCopy){
            Tree producedTree = new Tree(producedTokenType);
            top.getChildren().add(0,producedTree);
            stack.push(producedTree);
        }
    }

    public void match(){
        Tree top = stack.peek();
        TokenType first = phrase.get(0);
        if(top.getTokenType() == first){
            phrase.remove(0);
            stack.pop();
        }else{
            throw new RuntimeException(top.getTokenType().toString()+" no es el primer símbolo de la frase. Desapilar "+first.toString());
        }
    }

    public void parse(LL1Parser controller){
        while(!stack.isEmpty() && !phrase.isEmpty()){
            TokenType stackToken = null;
            if(!stack.isEmpty()){
                stackToken = stack.peek().getTokenType();
            }
            TokenType phraseToken = null;
            if(!phrase.isEmpty()){
                phraseToken = phrase.get(0);
            }
            SpiedLL1Machine decorator = new SpiedLL1Machine(this);
            controller.parse(stackToken,phraseToken,decorator);
            if(decorator.getCalls()==0){
                throw new RuntimeException("parse() no llamó a ningún método de LL1Machine para el stackToken "+stackToken.toString()+" y el phraseToken "+phraseToken.toString());
            }
            if(decorator.getCalls()>1){
                throw new RuntimeException("parse() hizo más de 1 invocación a LL1Machine para el stackToken "+stackToken.toString()+" y el phraseToken "+phraseToken.toString());
            }
        }
    }

    public List<TokenType> getPreorder(){
        return trunk.getPreOrder();
    }
    
}
