package ar.com.degedev.trazar_covid.frontend.view;

import ar.com.degedev.trazar_covid.backend.service.ComercioService;
import ar.com.degedev.trazar_covid.backend.service.Service;
import ar.com.degedev.trazar_covid.backend.util.CustomAlert;
import ar.com.degedev.trazar_covid.backend.util.ExpressionChecker;
import ar.com.degedev.trazar_covid.frontend.Main;
import ar.com.degedev.trazar_covid.frontend.model.Cliente;
import ar.com.degedev.trazar_covid.frontend.model.Comercio;
import ar.com.degedev.trazar_covid.frontend.model.Registro;
import ar.com.degedev.trazar_covid.frontend.service_subscriber.ComercioServiceSubscriber;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDateTime;
import java.util.List;

public class VentanaPrincipalController implements ComercioServiceSubscriber {


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

        private List<Service> services;
        private ExpressionChecker expressionChecker;

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

        try {

            this.services.get(0).searchComercios();
        } catch (Exception exception) {

            System.out.println("No exploto solo no tiene los sericios cargados\nTenga un buen dia");
            exception.printStackTrace();
        }
    }

    public void setListadoClientes(Main main){
        this.main = main;

        tablaListadoClientes.setItems(main.getClientes());
    }

    public void setComboBox(ComboBox combobox,Main main){
        combobox.setConverter(new StringConverter<Comercio>() {
            @Override
            public String toString(Comercio comercio) {
                return comercio.getNombre();
            }

            @Override
            public Comercio fromString(String string) {
                return null;
            }
        });
        combobox.setItems(main.getComercios());
        combobox.getSelectionModel().selectFirst();
    }
    public void setComboBoxClientes(Main main){

        setComboBox(comercioListaDesplegable,main);
    }

    public void setComboBoxClientesPorComercio(Main main) {

        setComboBox(comercioListaDesplegableConsulta,main);
    }

    @FXML
    public void buscarPersonaPorComercio(){

        Comercio comercio = comercioListaDesplegableConsulta.getValue();


    }

    @Override
    public void showComercio(Comercio comercio) {
        new CustomAlert(Alert.AlertType.INFORMATION, "Comercio seleccionado", "Informacion:\n\n" +
                "id: " + comercio.getId()+"\nCUIT: "+comercio.getCUIT()+"\nnombre: "+comercio.getNombre()+
                "\ndirección: "+comercio.getDireccion()+"\ntelefono: "+comercio.getTelefono());
    }

    protected void loadComercioListaDesplegable(List data) {
        comercioListaDesplegable.setItems(null);
    }

    @Override
    public void showComercios(List<Comercio> comercios) {
        this.loadComercioListaDesplegable(comercios);
    }
}

