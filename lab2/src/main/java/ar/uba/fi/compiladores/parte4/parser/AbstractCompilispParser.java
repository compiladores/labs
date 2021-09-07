package ar.uba.fi.compiladores.parte4.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import ar.uba.fi.compiladores.parte4.ClToken;
import ar.uba.fi.compiladores.parte4.ClTokenType;

public abstract class AbstractCompilispParser {

    protected Map<String,Object> context;
    private ArrayList<ClToken> program;
    public AbstractCompilispParser(Collection<ClToken> program, Map<String,Object> context){
        this.program = new ArrayList<ClToken>(program);
        this.context = context;
    }
    protected void match(ClTokenType tokenType){
        if(program.isEmpty()){
            throw new CompilispNoMatch("El programa terminó inesperadamente");
        }
        ClTokenType nextTokenType = program.get(0).getTokenType();
        if(nextTokenType != tokenType){
            throw new CompilispNoMatch("Fracaso intentando matchear "+nextTokenType.toString()+ "  y el tokenType deseado: "+tokenType.toString());
        }
        this.program.remove(0);
    }
    protected ClToken nextToken(){
        if(program.isEmpty()){
            throw new CompilispNoMatch("El programa terminó inesperadamente");
        }
        return this.program.get(0);
    }
    protected boolean getProgramIsOver(){
        return program.isEmpty();
    }

    public abstract Object input();
    public abstract Object line();
    public abstract Object sExp();
    public abstract Object list();
    public abstract Object atom();

}
