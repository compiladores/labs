package ar.uba.fi.compiladores.parte4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class ClToken {
    @Getter
    @NonNull
    ClTokenType tokenType;
    @Getter
    String value;
}
