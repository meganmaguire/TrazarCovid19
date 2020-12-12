package ar.com.degedev.trazar_covid.frontend.service_subscriber;

import ar.com.degedev.trazar_covid.frontend.model.Comercio;

import java.util.List;

public interface ComercioServiceSubscriber {

    void showComercio(Comercio comercio);

    void showComercios(List<Comercio> comercios);

}
