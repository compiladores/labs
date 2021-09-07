package ar.uba.fi.compiladores.parte5;

import java.util.Optional;

public interface GeneralToken<S> {
    int getTokenType();
    Optional<S> getContents();
}
