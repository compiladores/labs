package ar.uba.fi.compiladores.parte6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.Map;

public abstract class CompilispHelper {
    private Map<String, Object> context;
    private String value;
    public CompilispHelper(Map<String, Object> context){
        if(context==null){
            this.context=new HashMap<>();
        }else{
            this.context=new HashMap<>(context);
        }
    }
    protected Object valeOfSymbol(Object name){
        return context.get((String) name);
    }

    protected void setValue(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }
}
