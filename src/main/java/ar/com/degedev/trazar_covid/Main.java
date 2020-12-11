package ar.com.degedev.trazar_covid;


import ar.com.degedev.trazar_covid.model.Cliente;
import ar.com.degedev.trazar_covid.view.VentanaPrincipalController;
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

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public Main(){
        clientes.add(new Cliente(39490591, "Megan", "Maguire", "Av. Ejército de los Andes 569", "2664828390"));
        clientes.add(new Cliente(39381308, "Franco", "Merenda", "Av. Ejército de los Andes 569", "260339838"));
    }

    public ObservableList<Cliente> getClientes(){
        return clientes;
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
        }
        catch (IOException e){

        }
    }
}
