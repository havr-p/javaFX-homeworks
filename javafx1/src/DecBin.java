import javafx.application.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import java.util.*;

public class DecBin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 200);

        primaryStage.setTitle("Bin-Dec conventer");
        primaryStage.setScene(scene);
        primaryStage.show();

        TextField binTextField = new TextField();
        pane.getChildren().add(binTextField);
        binTextField.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        binTextField.setLayoutX(20);
        binTextField.setLayoutY(20);

        TextField decTextField = new TextField();
        pane.getChildren().add(decTextField);
        decTextField.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        decTextField.setLayoutX(200);
        decTextField.setLayoutY(20);



        Button toDecButton = new Button("to decimal");
        pane.getChildren().add(toDecButton);
        toDecButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        toDecButton.setLayoutX(60);
        toDecButton.setLayoutY(60);
        toDecButton.setPrefWidth(80);
        toDecButton.setPrefHeight(30);
        toDecButton.setOnAction(actionEvent -> {
            int num = Integer.parseInt(binTextField.getText(), 2);
            decTextField.setText(Integer.toString(num));
        });

        Button toBinButton = new Button("to binary");
        pane.getChildren().add(toBinButton);
        toBinButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        toBinButton.setLayoutX(240);
        toBinButton.setLayoutY(60);
        toBinButton.setPrefWidth(80);
        toBinButton.setPrefHeight(30);
        toBinButton.setOnAction(actionEvent -> {
            int num = Integer.parseInt(decTextField.getText());
            binTextField.setText(Integer.toBinaryString(num));
        });


    }
}
