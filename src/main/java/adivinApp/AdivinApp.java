package adivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	private TextField Textfield;
	private Button ComprobarButton;
	private VBox root;

	private Text enunciado;
	private int solucion, intentos = 0;

	public void start(Stage primaryStage) throws Exception {
		solucion = (int) ((Math.random() * 100) + 1);
		ComprobarButton = new Button("Comprobar");
		ComprobarButton.setOnAction(e -> onComprobarAction(e));
		ComprobarButton.setDefaultButton(true);
		ComprobarButton.setTooltip(new Tooltip("Comprueba si el numero introducido es correcto."));

		Textfield = new TextField();
		Textfield.setPromptText("Introduce un numero");

		enunciado = new Text();
		enunciado.setText("adivina un numero del 1 al 100");

		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(enunciado, Textfield, ComprobarButton);
		root.setSpacing(5.0);
		root.setFillWidth(false);
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onComprobarAction(ActionEvent e) {
		try {
			intentos++;
			int numero = Integer.parseInt(Textfield.getText());

			if (numero > 100 || numero < 0) {
				throw new NumberFormatException();
			}

			if (numero == solucion) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Has ganado");
				alert.setContentText("Has acertado en " + intentos + " intentos eres una maquina");
				alert.showAndWait();
				solucion = (int) ((Math.random() * 100) + 1);
			} else if (numero > solucion) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("la solucion es menor que " + numero);
				alert.setContentText("Vuelve a intentarlo");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("la solucion es mayor que " + numero);
				alert.setContentText("Vuelve a intentarlo");
				alert.showAndWait();
			}

		} catch (NumberFormatException e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("No es un numero válido");
			alert.setContentText("Introduce un numero entero del 1 al 100");
			alert.showAndWait();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
