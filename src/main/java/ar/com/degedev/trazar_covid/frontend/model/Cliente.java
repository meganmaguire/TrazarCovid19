package ar.com.degedev.trazar_covid.frontend.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {

    private Integer DNI;

    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
}
