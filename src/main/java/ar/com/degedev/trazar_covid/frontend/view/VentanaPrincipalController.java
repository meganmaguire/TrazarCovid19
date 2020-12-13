package ar.com.degedev.trazar_covid.frontend.view;

import ar.com.degedev.trazar_covid.backend.api.ClienteAPI;
import ar.com.degedev.trazar_covid.backend.api.ComercioAPI;
import ar.com.degedev.trazar_covid.backend.api.RegistroAPI;
import ar.com.degedev.trazar_covid.backend.service.ApplicationCtx;
import ar.com.degedev.trazar_covid.backend.util.ExpressionChecker;
import ar.com.degedev.trazar_covid.frontend.model.Cliente;
import ar.com.degedev.trazar_covid.frontend.model.Comercio;
import ar.com.degedev.trazar_covid.frontend.model.Registro;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.val;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    private TableColumn<Cliente, String> nombreClientesPorComercio;

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
    private Button buscarPorDni;

    @FXML
    private TableView<Comercio> tablaConsultaComercioPorCliente;

    @FXML
    private TableView<Cliente> tablaConsultaClientePorComercio;

    @FXML
    private TableColumn<Cliente, Integer> dniClientesPorComercio;

    @FXML
    private TextField horaHastaComerciosPorCliente;

    @FXML
    private TextField horaDesdeComerciosPorCliente;

    @FXML
    private TextField horaHastaClientesPorComercio;

    @FXML
    private TextField horaDesdeClientesPorComercio;



    private ExpressionChecker expressionChecker;
    private ComercioAPI comercioAPI;
    private ClienteAPI clientesAPI;
    private RegistroAPI registroAPI;
    private Cliente cliente;

    private ObservableList<Comercio> comercios;
    private ObservableList<Cliente> clientes;

    @FXML
    private void limpiar() {
        cleanFields(false);
    }

    private void cleanFields(boolean createdRegistro) {
        buscarPorDni.setStyle("-fx-text-fill: #4d4d4d");
        nombreCliente.setText("");
        apellidoCliente.setText("");
        dniCliente.setText("");
        direccionCliente.setText("");
        telCliente.setText("");

        if(!createdRegistro) {
            comercioListaDesplegable.getSelectionModel().selectFirst();
        }
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

            if (!this.clientes.contains(cliente)) {
                this.clientes.add(cliente);
            }

            cleanFields(true);

            this.registroAPI.altaRegistro(nuevoRegistro).execute();

        } catch (Exception ignored) {
            // GSon lanza una excepcion que es necesaria para el correcto funcionamiento de la aplicacion
            // Un to-do seria buscar esta excepcion mas adentro
        }

    }


    @FXML
    private void initialize() {
        val apis = ApplicationCtx.getInstance().getAPIs();
        this.comercioAPI = apis.getComercioAPI();
        this.clientesAPI = apis.getClienteAPI();
        this.registroAPI = apis.getRegistroAPI();
        this.comercioAPI = ApplicationCtx.getInstance().getAPIs().getComercioAPI();




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

        // Tabla Consulta Clientes por Comercio
        apellidoClientesPorComercio.setCellValueFactory(cellData ->  new SimpleObjectProperty<>(cellData.getValue().getApellido()));
        nombreClientesPorComercio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNombre()));
        dniClientesPorComercio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDni()));
        dirClientesPorComercio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDireccion()));
        telClientesPorComercio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTelefono()));
    }

    @FXML
    public void buscarPersonaPorComercio(){

        try {
            Comercio comercio = comercioListaDesplegableConsulta.getValue();
            String desde = desdeClientesPorComercio.getEditor().getText();
            String hasta = hastaClientesPorComercio.getEditor().getText();

            System.out.println(desde);
            System.out.println(hasta);
            // TODO traer los datos de la bd
            ObservableList<Cliente> clientes = FXCollections.observableArrayList();
            // TODO hardcodeado, sacar
            clientes.add(new Cliente(39490591, "Megan", "Maguire", "Av. Ejército de los Andes 569", "2664828390"));
            clientes.add(new Cliente(39381308, "Franco", "Merenda", "Av. Ejército de los Andes 569", "260339838"));


            tablaConsultaClientePorComercio.setItems(clientes);
        }
        catch (Exception e){
            System.out.println("Problema de control de datos");
            e.printStackTrace();
        }
    }
    @FXML
    public void buscarPersonaPorDni() {
        String clienteDni = dniCliente.getText();
        if (ExpressionChecker.getExpressionChecker().onlyNumbers(clienteDni, false)) {
            try {
                this.cliente = this.clientesAPI.clientePorDNI(Integer.valueOf(clienteDni)).execute().body();
                if (this.cliente != null) {
                    nombreCliente.setText(this.cliente.getNombre());
                    apellidoCliente.setText(this.cliente.getApellido());
                    direccionCliente.setText(this.cliente.getDireccion());
                    telCliente.setText(this.cliente.getTelefono());
                }
            } catch (IOException e) {
                this.buscarPorDni.setStyle("-fx-text-fill: red");
            }
        } else {
            dniCliente.setText("Ingrese solo números");
        }
    }
}

