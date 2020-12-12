package ar.com.degedev.trazar_covid.backend.interfaz;

import ar.com.degedev.trazar_covid.frontend.model.Comercio;

public interface ComercioOperator extends CrudOperator<Comercio> {
    Comercio find(Integer id) throws Exception;
    Comercio delete(Integer id) throws Exception;
}
