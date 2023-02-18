import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculadoraIMC extends Application {

    private Label resultadoLabel;
    private Label rangoLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de IMC");

        // Declarar las variables para los campos de texto
        final TextField pesoTextField, alturaTextField;

        // Crear etiquetas y campos de texto para peso y altura
        Label pesoLabel = new Label("Peso (kg):");
        pesoTextField = new TextField();
        pesoTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    calcularIMC(pesoTextField, alturaTextField);
                }
            }

            final TextField pesoTextField = new TextField();
            final TextField alturaTextField = new TextField();

        });

        Label alturaLabel = new Label("Altura (m):");
        alturaTextField = new TextField();
        alturaTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    calcularIMC(pesoTextField, alturaTextField);
                }
            }
        });

        // Crear botón para calcular el IMC
        Button calcularButton = new Button("Calcular");
        calcularButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                calcularIMC(pesoTextField, alturaTextField);
            }
        });

        // Crear etiquetas para mostrar el resultado y el rango del IMC
        resultadoLabel = new Label();
        rangoLabel = new Label();

        // Crear un layout para organizar los elementos
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.addRow(0, pesoLabel, pesoTextField);
        root.addRow(1, alturaLabel, alturaTextField);
        root.addRow(2, calcularButton);
        root.addRow(3, resultadoLabel);
        root.addRow(4, rangoLabel);

        // Mostrar la ventana
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Calcula el rango del IMC para el valor dado.
     *
     * @param imc el valor del IMC
     * @return el rango del IMC
     */
    private String obtenerRango(double imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }

    /**
     * Muestra el resultado y el rango del IMC en las etiquetas correspondientes.
     *
     * @param imc   el valor del IMC
     * @param rango el rango del IMC
     */
    private void mostrarResultado(double imc, String rango) {
        resultadoLabel.setText(String.format("IMC: %.2f", imc));
        rangoLabel.setText(String.format("Rango: %s", rango));
    }

    /**
     * Calcula el IMC a partir de los valores en los campos de texto y muestra el
     * resultado.
     *
     * @param pesoTextField   el campo de texto para el peso
     * @param alturaTextField el campo de texto para la altura
     */
    private void calcularIMC(TextField pesoTextField, TextField alturaTextField) {
        try {
            double peso = Double.parseDouble(pesoTextField.getText());
            double altura = Double.parseDouble(alturaTextField.getText());

            if (altura == 0) {
                resultadoLabel.setText("Altura no puede ser cero.");
                rangoLabel.setText("");
                return;
            }

            double imc = peso / (altura * altura);
            String rango = obtenerRango(imc);
            mostrarResultado(imc, rango);
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Valores inválidos.");
            rangoLabel.setText("");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}