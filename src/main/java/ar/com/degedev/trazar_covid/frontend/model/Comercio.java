package ar.com.degedev.trazar_covid.frontend.model;

import lombok.Data;

@Data
public class Comercio {

    private Integer Id;

    private Long CUIT;
    private String nombre;
    private String direccion;
    private String telefono;
}
