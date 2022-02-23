import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Uloha1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        FlowPane pane = new FlowPane();

        for (int i = 1; i <= n; i++) {
            Button button = new Button(Integer.toString(i));
            pane.getChildren().add(button);
            final int finalI = i;
            button.setOnAction(actionEvent -> {
                System.out.println(finalI);
            });
        }


        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setTitle("Uloha1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}