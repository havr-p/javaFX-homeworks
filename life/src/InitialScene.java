import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class InitialScene extends Scene {
    public InitialScene(GridPane initialPane, Stage primaryStage) {
        super(initialPane);
        this.getStylesheets().add("styles.css");

        Label lblRowCount = new Label("Počet riadkov: ");
        initialPane.add(lblRowCount, 0, 1);

        Label lblColCount = new Label("Počet stĺpcov: ");
        initialPane.add(lblColCount, 0, 2);

        Spinner heightSpinner = new Spinner(10, 60, 10);
        initialPane.add(heightSpinner, 1, 1, 1, 1);

        Spinner widthSpinner = new Spinner(10, 60, 10);
        initialPane.add(widthSpinner, 1, 2, 1, 1);

        Button btnOK = new Button("OK");
        initialPane.add(btnOK, 1, 3);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setId("btnOK");
        btnOK.setOnAction(actionEvent -> {
            int width = (int) widthSpinner.getValue();
            int height = (int) heightSpinner.getValue();
            BorderPane mainPane = new BorderPane();
            MainScene mainScene = new MainScene(primaryStage, mainPane, width, height);

            primaryStage.setScene(mainScene);
            primaryStage.setResizable(true);
            primaryStage.sizeToScene();
        });


        ColumnConstraints cc = new ColumnConstraints();
        cc.setPrefWidth(100);
        cc.setHgrow(Priority.ALWAYS);
        initialPane.getColumnConstraints().addAll(new ColumnConstraints(), cc);

    }
}
