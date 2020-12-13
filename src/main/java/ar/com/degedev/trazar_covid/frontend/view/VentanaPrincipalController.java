package ar.com.degedev.trazar_covid.frontend.view;

import ar.com.degedev.trazar_covid.backend.api.ComercioAPI;
import ar.com.degedev.trazar_covid.backend.service.ApplicationCtx;
import ar.com.degedev.trazar_covid.backend.util.ExpressionChecker;
import ar.com.degedev.trazar_covid.frontend.Main;
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
    private TableView<Comercio> tablaConsultaComercioPorCliente;

    @FXML
    private TableView<Cliente> tablaConsultaClientePorComercio;

    @FXML
    private TableColumn<Cliente, Comercio> dniClientesPorComercio;

    private ExpressionChecker expressionChecker;
    private ComercioAPI comercioAPI;

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
    private void createRegistro() {
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


    public VentanaPrincipalController() {

    }

    @FXML
    private void initialize() {
        this.comercioAPI = ApplicationCtx.getInstance().getAPIs().getComercioAPI();
        apellidoListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getApellidoProperty());
        nombreListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
        dniListadoClientes.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDNI()));
        dirListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getDireccionProperty());
        telListadoClientes.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());

        apellidoClientesPorComercio.setCellValueFactory(cellData -> cellData.getValue().getApellidoProperty());
        nombreClientesPorComercio.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
        dniClientesPorComercio.setCellValueFactory(cellData -> (ObservableValue) new SimpleIntegerProperty(cellData.getValue().getDNI()));
        dirClientesPorComercio.setCellValueFactory(cellData -> cellData.getValue().getDireccionProperty());
        telClientesPorComercio.setCellValueFactory(cellData -> cellData.getValue().getTelefonoProperty());


    }

    public void setListadoClientes(Main main) {

        tablaListadoClientes.setItems(main.getClientes());
    }

    public void setComboBox(ComboBox<Comercio> combobox) {
        try{
            val comercios = FXCollections.observableArrayList(this.comercioAPI.listarComercios().execute().body());
            combobox.setItems(comercios);
            combobox.getSelectionModel().selectFirst();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setComboBoxClientes() {

        setComboBox(comercioListaDesplegable);
    }

    public void setComboBoxClientesPorComercio() {
        setComboBox(comercioListaDesplegableConsulta);
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
}
