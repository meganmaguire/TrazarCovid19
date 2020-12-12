package ar.com.degedev.trazar_covid.backend.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

@Getter
public class CustomAlert extends Alert {
    public static final String DEFAULT_SUCCES_HEADER = "Operacion realizada con éxito";
    public static final String DEFAULT_ERROR_HEADER = "¡Ha ocurrido un error!. Para más información haga click en \"Ver detalles\".";
    public static final String DEFAULT_WORKING_ON_HEADER = "Trabajo en proceso. Por favor espere...";

    public static final String DEFAULT_SUCCES_TITLE = "ÉXITO";
    public static final String DEFAULT_WORKING_ON_TITLE = "TRABAJANDO";
    public static final String DEFAULT_ERROR_TITLE = "ERROR";

    public static final String DEFAULT_DESCRIPTION = "Sin mensaje que mostrar";

    private String description;
    private Exception exception;

    public CustomAlert() {
        super(AlertType.INFORMATION);
        this.setTitle(DEFAULT_SUCCES_TITLE);
        this.setHeaderText(DEFAULT_SUCCES_HEADER);
        this.setResizable(false);
    }

    public CustomAlert(AlertType alertType, String title, String headerText) {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.setResizable(false);
    }

    public CustomAlert(AlertType alertType, String title, String headerText, String description, Exception exception) {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.description = description;
        this.exception = exception;
        this.setResizable(false);
    }

    public CustomAlert(AlertType alertType) {
        super(alertType);
    }

    public CustomAlert(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
    }

    public static Exception irrelevantException() {
        return new Exception("Irrelevant exception");
    }

    private void build() {
        if (this.getException() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            this.getException().printStackTrace(pw);
            String stackTraceMessage = sw.toString(); //stack trace as a string
            TextArea textArea = new TextArea((this.getDescription() != null ? this.getDescription() + "\n\n" : "") + stackTraceMessage);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            Pane pane = new Pane();
            pane.getChildren().add(textArea);
            this.getDialogPane().setExpandableContent(pane);
        }
    }

    public Optional<ButtonType> customShow() {
        build();
        Optional<ButtonType> ret = null;
        ((Stage) this.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);

        if (this.getAlertType() != AlertType.NONE) {
            ret = this.showAndWait();
        } else {
            this.show();
        }
        return ret;
    }

    public void customClose() {
        this.setResult(ButtonType.CLOSE);
        this.close();
    }

}
