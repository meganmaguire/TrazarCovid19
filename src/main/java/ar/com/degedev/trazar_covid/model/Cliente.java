package ar.com.degedev.trazar_covid.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

@Data
public class Cliente {

    private IntegerProperty DNI;

    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty direccion;
    private StringProperty telefono;

    public Cliente(Integer DNI, String nombre, String apellido, String direccion, String telefono){
        this.DNI = new SimpleIntegerProperty(DNI);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
    }

    public Integer getDNI() {
        return DNI.get();
    }


    public String getNombre() {
        return nombre.get();
    }


    public String getApellido() {
        return apellido.get();
    }


    public String getDireccion() {
        return direccion.get();
    }


    public String getTelefono() {
        return telefono.get();
    }

    public IntegerProperty getDNIProperty(){
        return DNI;
    }

    public StringProperty getNombreProperty(){
        return nombre;
    }

    public StringProperty getApellidoProperty(){
        return apellido;
    }

    public StringProperty getDireccionProperty(){
        return direccion;
    }

    public StringProperty getTelefonoProperty(){
        return telefono;
    }
}
