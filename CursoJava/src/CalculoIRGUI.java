import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CalculoIRGUI extends Application {
    private TextField rentaAnualField;
    private Label impuestoLabel;
    private Label deduccionLabel;

    @Override
    public void start(Stage primaryStage) {
        // Crear los componentes de la interfaz de usuario
        Label titleLabel = new Label("Cálculo del Impuesto sobre la Renta");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#0076a3"));

        Label rentaAnualLabel = new Label("Renta anual:");
        rentaAnualLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));

        rentaAnualField = new TextField();
        rentaAnualField.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));

        Button calcularButton = new Button("Calcular");
        calcularButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        calcularButton.setOnAction(e -> calcularImpuesto());

        impuestoLabel = new Label();
        impuestoLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));

        deduccionLabel = new Label();
        deduccionLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));

        // Crear el panel principal y agregar los componentes
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(rentaAnualLabel, 0, 1);
        grid.add(rentaAnualField, 1, 1);
        grid.add(calcularButton, 0, 2, 2, 1);
        grid.add(impuestoLabel, 0, 3, 2, 1);
        grid.add(deduccionLabel, 0, 4, 2, 1);

        // Crear el panel raíz y agregar el panel principal
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(grid);

        // Crear la escena y mostrar la ventana
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Cálculo del Impuesto sobre la Renta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void calcularImpuesto() {
        String rentaAnualString = rentaAnualField.getText();
        try {
            double rentaAnual = Double.parseDouble(rentaAnualString);
            double impuesto, deduccion;

            if (rentaAnual <= 1900.0) {
                impuesto = 0.0;
                deduccion = 0.0;
            } else if (rentaAnual <= 2800.0) {
                impuesto = (rentaAnual - 1900.0) * 0.075;
                deduccion = 142.0;
            } else if (rentaAnual <= 3751.0) {
                    impuesto = (rentaAnual - 2800.0) * 0.15 + 142.5;
                    deduccion = 283.5;
                } else if (rentaAnual <= 4660.0) {
                    impuesto = (rentaAnual - 3751.0) * 0.3 + 356.0;
                    deduccion = 515.5;
                } else {
                    impuesto = (rentaAnual - 4660.0) * 0.35 + 973.0;
                    deduccion = 948.0;
                }
        
                // Mostrar los resultados
                impuestoLabel.setText("Impuesto: " + impuesto);
                deduccionLabel.setText("Deducción: " + deduccion);
            } catch (NumberFormatException e) {
                impuestoLabel.setText("Error: la renta anual debe ser un número válido");
                deduccionLabel.setText("");
            }
        }
        
        public static void main(String[] args) {
            launch(args);
        }
    }