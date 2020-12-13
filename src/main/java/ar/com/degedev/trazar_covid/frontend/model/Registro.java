package ar.com.degedev.trazar_covid.frontend.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Registro {

    public Registro(Cliente cliente, Comercio comercio, LocalDateTime fecha) {
        this.cliente = cliente;
        this.comercio = comercio;
        this.fecha = fecha;
    }

    private Integer id;

    private Cliente cliente;

    private Comercio comercio;

    private LocalDateTime fecha;

}
