package ar.com.degedev.trazar_covid.view;

import ar.com.degedev.trazar_covid.Main;
import ar.com.degedev.trazar_covid.model.Cliente;
import ar.com.degedev.trazar_covid.model.Comercio;
import ar.com.degedev.trazar_covid.model.Registro;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class VentanaPrincipalController {

    @FXML
    private Button enviarRegistro;

    @FXML
    private ComboBox<String> comercioListaDesplegable;

    @FXML
    private Tab listadoClientesTab;

    @FXML
    private TextField direccionCliente;

    @FXML
    private TableColumn<Cliente, String> nombreListadoClientes;

    @FXML
    private TextField dniCliente;

    @FXML
    private TableColumn<Cliente, String>dirListadoClientes;

    @FXML
    private TableColumn<Cliente, String> telListadoClientes;

    @FXML
    private TableColumn<Cliente, Integer> dniListadoClientes;

    @FXML
    private Tab nuevoRegistroTab;

    @FXML
    private TextField nombreCliente;

    @FXML
    private TextField telCliente;

    @FXML
    private Button limpiarRegistro;

    @FXML
    private TextField apellidoCliente;

    @FXML
    private TableView<Cliente> tablaListadoClientes;

    @FXML
    private TableView<?> tablaConsulta2;

    @FXML
    private TableView<?> tablaConsulta1;

    @FXML
    private TableColumn<Cliente, String> apellidoListadoClientes;

    @FXML
    private void cleanFields() {
        nombreCliente.setText("");
        apellidoCliente.setText("");
        dniCliente.setText("");
        direccionCliente.setText("");
        telCliente.setText("");
        comercioListaDesplegable.getSelectionModel().clearSelection();
        comercioListaDesplegable.setValue(null);
    }

    @FXML
    private void createRegistro(){
        try {
            String nombre = nombreCliente.getText();
            String apellido = apellidoCliente.getText();
            Integer dni = Integer.valueOf(dniCliente.getText());
            String direccion = direccionCliente.getText();
            String telefono = telCliente.getText();

            Cliente cliente = new Cliente(dni, nombre, apellido, direccion, telefono);
            // ToDo get comercio from data base.
            Comercio comercio = new Comercio();

            Registro nuevoRegistro = new Registro(cliente, comercio, LocalDateTime.now());

            System.out.println(nuevoRegistro.toString());
        } catch (Exception e) {
            System.out.println("Algo esta roto pibe");
        }

    }


    private Main main;

    public VentanaPrincipalController(){

    }

    @FXML
    private void initialize(){
        apellidoListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getApellidoProperty());
        nombreListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
        dniListadoClientes.setCellValueFactory(cellData -> (ObservableValue) new SimpleIntegerProperty(cellData.getValue().getDNI()));
        dirListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getDireccionProperty());
        telListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());
    }

    public void setListadoClientes(Main main){
        this.main = main;

        tablaListadoClientes.setItems(main.getClientes());
    }

    @FXML
    public void buscarPersonaPorComercio(){

    }
}

