package ar.com.degedev.trazar_covid.frontend;

import ar.com.degedev.trazar_covid.backend.service.ApplicationCtx;
import ar.com.degedev.trazar_covid.frontend.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ApplicationCtx.getInstance().getAPIs().login(new User(0, "admin", "admin"));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/VentanaPrincipal.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Trazar - COVID19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
