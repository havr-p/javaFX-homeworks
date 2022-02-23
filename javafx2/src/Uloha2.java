import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Uloha2 extends Application {
    private int buttonCount;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        Pane pane = new AnchorPane();

        Button addingButton = new Button("Pridaj");
        pane.getChildren().add(addingButton);
        AnchorPane.setTopAnchor(addingButton, 20.0);
        addingButton.setOnAction(actionEvent -> {
            buttonCount++;
            Button b = new Button(Integer.toString(buttonCount));
            pane.getChildren().add(b);
            b.setOnAction(actionEvent1 -> System.out.println(b.getText()));
        });
        Button leftBtn = new Button("Left");
        pane.getChildren().add(leftBtn);
        AnchorPane.setTopAnchor(leftBtn, 30.0);
        AnchorPane.setLeftAnchor(leftBtn, 15.0);
        AnchorPane.setBottomAnchor(leftBtn, 30.0);

        Button rightBtn = new Button("Right");
        pane.getChildren().add(rightBtn);
        AnchorPane.setTopAnchor(rightBtn, 30.0);
        AnchorPane.setRightAnchor(rightBtn, 10.0);
        AnchorPane.setBottomAnchor(rightBtn, 30.0);



        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Uloha1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
