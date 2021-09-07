package ar.uba.fi.compiladores.parte3;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * Token: una clase que representa un token de un lenguaje de programación. 
 * El parámetro T puede especificar un Enum o similar, de forma que esta clase
 * pueda usarse para distintos lenguajes. La anotación @Data, provista por 
 * lombok, genera getters, setters, equals, toString, etc.
 */
@AllArgsConstructor
@Data public class Token<T> {
    @NonNull private T type;
    private String contents;
}
