package ar.uba.fi.compiladores.parte2.parser;

import java.util.ArrayList;
import java.util.List;

import ar.uba.fi.compiladores.parte1.TokenType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Tree {
    @Getter
    @NonNull
    private TokenType tokenType;

    @Getter
    private ArrayList<Tree> children = new ArrayList<>();

    List<TokenType> getPreOrder(){
        ArrayList<TokenType> ret = new ArrayList<>();
        ret.add(tokenType);
        for(Tree child: children){
            ret.addAll(child.getPreOrder());
        }
        return ret;
    }
}
