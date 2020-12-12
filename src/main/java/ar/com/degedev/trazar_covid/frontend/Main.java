package ar.com.degedev.trazar_covid.frontend;

import ar.com.degedev.trazar_covid.frontend.model.Cliente;
import ar.com.degedev.trazar_covid.frontend.model.Comercio;
import ar.com.degedev.trazar_covid.frontend.view.VentanaPrincipalController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static final String API_HOSTNAME = "34.74.103.67"; // Maquina Virtual Lauti

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    private ObservableList<Comercio> comercios = FXCollections.observableArrayList();

    public Main(){
        clientes.add(new Cliente(39490591, "Megan", "Maguire", "Av. Ejército de los Andes 569", "2664828390"));
        clientes.add(new Cliente(39381308, "Franco", "Merenda", "Av. Ejército de los Andes 569", "260339838"));
        comercios.add(new Comercio(20395405962L, "La Verdulería", "Rivadavia 632", "2664123656"));
        comercios.add(new Comercio(20394905912L, "La despensa", "San Martin 234", "2664565656"));
    }

    public ObservableList<Cliente> getClientes(){
        return clientes;
    }

    public ObservableList<Comercio> getComercios() {
        return comercios;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage  = primaryStage;
        this.primaryStage.setTitle("Trazar - COVID19");

        initRootLayout();
        showVentanaPrincipal();
    }

    public void initRootLayout(){
        try {
            // Carga el layout principal desde el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Muestra la escena que contiene el layout principal
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showVentanaPrincipal(){
        try{
            // Carga la ventana principal
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VentanaPrincipal.fxml"));
            AnchorPane ventanaPrincipal = (AnchorPane) loader.load();

            // Ubica la ventana principal en el centro del layout principal
            rootLayout.setCenter(ventanaPrincipal);

            VentanaPrincipalController controller = loader.getController();
            controller.setListadoClientes(this);
            controller.setComboBoxClientes(this);
            controller.setComboBoxClientesPorComercio(this);
        }
        catch (IOException e){

        }
    }
}
