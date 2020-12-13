package ar.com.degedev.trazar_covid.frontend.model;


import lombok.Data;

@Data
public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}