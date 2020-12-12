package ar.com.degedev.trazar_covid.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercio {

    private Integer Id;

    private Long cuit;
    private String nombre;
    private String direccion;
    private String telefono;

    @Override
    public String toString() {
        return this.nombre;
    }
}
