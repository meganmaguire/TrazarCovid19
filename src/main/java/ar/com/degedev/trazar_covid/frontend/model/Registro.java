package ar.com.degedev.trazar_covid.frontend.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Registro {
    private Integer id;

    private Cliente cliente;
    private Comercio comercio;
    private LocalDateTime fecha;
}
