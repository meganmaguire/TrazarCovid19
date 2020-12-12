package ar.com.degedev.trazar_covid.frontend.view;

import ar.com.degedev.trazar_covid.backend.api.ClienteAPI;
import ar.com.degedev.trazar_covid.backend.api.ComercioAPI;
import ar.com.degedev.trazar_covid.backend.api.RegistroAPI;
import ar.com.degedev.trazar_covid.backend.service.ApplicationCtx;
import ar.com.degedev.trazar_covid.backend.util.ExpressionChecker;
import ar.com.degedev.trazar_covid.frontend.Main;
import ar.com.degedev.trazar_covid.frontend.model.Cliente;
import ar.com.degedev.trazar_covid.frontend.model.Comercio;
import ar.com.degedev.trazar_covid.frontend.model.Registro;
import com.google.gson.Gson;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.val;

import java.io.IOException;
import java.time.LocalDateTime;

public class VentanaPrincipalController {

    @FXML
    private ComboBox<Comercio> comercioListaDesplegable;

    @FXML
    private TableColumn<Comercio, String> dirListadoComercios;

    @FXML
    private DatePicker hastaComerciosPorCliente;

    @FXML
    private TextField direccionCliente;

    @FXML
    private DatePicker desdeClientesPorComercio;

    @FXML
    private TableColumn<Cliente, String> dirListadoClientes;

    @FXML
    private TableColumn<Cliente, Integer> dniListadoClientes;

    @FXML
    private TableColumn<Comercio, String> telListadoComercios;

    @FXML
    private TableColumn<Cliente, String> telClientesPorComercio;

    @FXML
    private TextField nombreCliente;

    @FXML
    private TextField dniComerciosPorCliente;

    @FXML
    private TextField telCliente;

    @FXML
    private Button limpiarRegistro;

    @FXML
    private TableView<Cliente> tablaListadoClientes;

    @FXML
    private TableColumn<Comercio, Integer> idListadoComercios;

    @FXML
    private DatePicker desdeComerciosPorCliente;

    @FXML
    private DatePicker hastaClientesPorComercio;

    @FXML
    private TableColumn<Cliente, String> apellidoListadoClientes;

    @FXML
    private Tab clientesSegunFechaTab;

    @FXML
    private Button enviarRegistro;

    @FXML
    private Tab listadoClientesTab;

    @FXML
    private TableColumn<Comercio, String> nombreListadoComercios;

    @FXML
    private TableColumn<Comercio, Long> cuitListadoComercios;

    @FXML
    private TableColumn<Cliente, String> nombreListadoClientes;

    @FXML
    private TableColumn<Comercio, String> nombreClientesPorComercio;

    @FXML
    private TextField dniCliente;

    @FXML
    private ComboBox<Comercio> comercioListaDesplegableConsulta;

    @FXML
    private Button buscarComercios;

    @FXML
    private TableColumn<Cliente, String> telListadoClientes;

    @FXML
    private TableColumn<Cliente, String> apellidoClientesPorComercio;

    @FXML
    private Tab nuevoRegistroTab;

    @FXML
    private TableColumn<Cliente, String> dirClientesPorComercio;

    @FXML
    private TextField apellidoCliente;

    @FXML
    private Tab comerciosSegunFechaTab;

    @FXML
    private Button buscarClientes;

    @FXML
    private TableView<Comercio> tablaConsultaComercioPorCliente;

    @FXML
    private TableView<Cliente> tablaConsultaClientePorComercio;

    @FXML
    private TableColumn<Cliente, Comercio> dniClientesPorComercio;

    private ExpressionChecker expressionChecker;
    private ComercioAPI comercioAPI;
    private ClienteAPI clientesAPI;
    private RegistroAPI registroAPI;

    private ObservableList<Comercio> comercios;
    private ObservableList<Cliente> clientes;

    @FXML
    private void cleanFields() {
        nombreCliente.setText("");
        apellidoCliente.setText("");
        dniCliente.setText("");
        direccionCliente.setText("");
        telCliente.setText("");
        comercioListaDesplegable.getSelectionModel().selectFirst();
        comercioListaDesplegable.setValue(null);
    }

    @FXML
    private void createRegistro() {
        try {
            String nombre = nombreCliente.getText();
            String apellido = apellidoCliente.getText();
            Integer dni = Integer.valueOf(dniCliente.getText());
            String direccion = direccionCliente.getText();
            String telefono = telCliente.getText();

            Cliente cliente = new Cliente(dni, nombre, apellido, direccion, telefono);

            Comercio comercio = this.comercioListaDesplegable.getValue();

            Registro nuevoRegistro = new Registro(0,cliente, comercio, LocalDateTime.now());

            val variableDelLauti = this.registroAPI.altaRegistro(nuevoRegistro).execute();


            System.out.println(new Gson().toJson(nuevoRegistro));

            System.out.println("");
        } catch (Exception e) {
            System.out.println("Algo esta roto pibe");
        }

    }


    @FXML
    private void initialize() {
        val apis = ApplicationCtx.getInstance().getAPIs();
        this.comercioAPI = apis.getComercioAPI();
        this.clientesAPI = apis.getClienteAPI();
        this.registroAPI = apis.getRegistroAPI();

        try {
            this.comercios = FXCollections.observableArrayList(this.comercioAPI.listarComercios().execute().body());
            this.clientes = FXCollections.observableArrayList(this.clientesAPI.listarClientes().execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ComboBox
        comercioListaDesplegable.setItems(this.comercios);
        comercioListaDesplegable.getSelectionModel().selectFirst();
        comercioListaDesplegableConsulta.setItems(this.comercios);
        comercioListaDesplegableConsulta.getSelectionModel().selectFirst();
        comercioListaDesplegable.getSelectionModel().selectFirst();
        tablaListadoClientes.setItems(this.clientes);

        // Tabla Clientes
        apellidoListadoClientes.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getApellido()));
        nombreListadoClientes.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        dniListadoClientes.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDni()));
        dirListadoClientes.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDireccion()));
        telListadoClientes.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTelefono()));
    }

    @FXML
    public void buscarPersonaPorComercio() {
        Comercio comercio = comercioListaDesplegableConsulta.getValue();
    }
}

