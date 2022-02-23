import javafx.application.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import java.util.*;

public class radixConverter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 300);

        primaryStage.setTitle("Bin-Dec conventer");
        primaryStage.setScene(scene);
        primaryStage.show();

        TextField firstTextField = new TextField();
        pane.getChildren().add(firstTextField);
        firstTextField.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        firstTextField.setLayoutX(20);
        firstTextField.setLayoutY(20);

        TextField secondTextField = new TextField();
        pane.getChildren().add(secondTextField);
        secondTextField.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        secondTextField.setLayoutX(200);
        secondTextField.setLayoutY(20);

        TextField firstRadix = new TextField();
        pane.getChildren().add(firstRadix);
        firstRadix.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        firstRadix.setLayoutX(20);
        firstRadix.setLayoutY(60);

        TextField secondRadix = new TextField();
        pane.getChildren().add(secondRadix);
        secondRadix.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        secondRadix.setLayoutX(200);
        secondRadix.setLayoutY(60);




        Button toFirstRadixButton = new Button("to firstRadix");
        pane.getChildren().add(toFirstRadixButton);
        toFirstRadixButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        toFirstRadixButton.setLayoutX(60);
        toFirstRadixButton.setLayoutY(200);
        toFirstRadixButton.setPrefWidth(120);
        toFirstRadixButton.setPrefHeight(30);
        toFirstRadixButton.setOnAction(actionEvent -> {
            int num = Integer.parseInt(firstTextField.getText(), Integer.parseInt(firstRadix.getText()));
            secondTextField.setText(Integer.toString(num, Integer.parseInt(secondRadix.getText())));
        });

        Button toSecondRadixButton = new Button("to secondRadix");
        pane.getChildren().add(toSecondRadixButton);
        toSecondRadixButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        toSecondRadixButton.setLayoutX(240);
        toSecondRadixButton.setLayoutY(200);
        toSecondRadixButton.setPrefWidth(120);
        toSecondRadixButton.setPrefHeight(30);
        toSecondRadixButton.setOnAction(actionEvent -> {
            int num = Integer.parseInt(secondTextField.getText(), Integer.parseInt(secondRadix.getText()));
            firstTextField.setText(Integer.toString(num, Integer.parseInt(firstRadix.getText())));
        });


    }
}
