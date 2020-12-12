package ar.com.degedev.trazar_covid.frontend.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    private String userName;
    private String password;
}
