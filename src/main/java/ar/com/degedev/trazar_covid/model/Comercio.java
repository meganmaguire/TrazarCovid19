package ar.com.degedev.trazar_covid.model;

import lombok.Data;

@Data
public class Comercio {

    private Integer Id;

    private Long CUIT;
    private String nombre;
    private String direccion;
    private String telefono;

    public Comercio(){

    }

    public Comercio(Long CUIT, String nombre, String direccion, String telefono){
        // TODO hardcodeado, sacar
        this.Id = 1;

        this.CUIT = CUIT;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    public String toString(Comercio comercio){
        return comercio.nombre;
    }
}
