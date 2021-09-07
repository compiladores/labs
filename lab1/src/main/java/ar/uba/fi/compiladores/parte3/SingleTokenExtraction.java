package ar.uba.fi.compiladores.parte3;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * Results of extracting a single token.
 */
@AllArgsConstructor
@Data public class SingleTokenExtraction<T> {
    @Setter(AccessLevel.NONE) private T finalState;
    @Setter(AccessLevel.NONE) private int lastFinalIndex;
}
