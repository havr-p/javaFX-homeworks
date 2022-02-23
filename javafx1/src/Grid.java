import javafx.application.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;

import java.util.*;

public class Grid extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Scanner scanner = new Scanner(System.in);
        int sceneWidth = scanner.nextInt();
        int sceneHeight = scanner.nextInt();
        Pane pane = new Pane();

        Scene scene = new Scene(pane, sceneWidth, sceneHeight);


        int width = (int) scene.getWidth()/10;
        int height = (int) scene.getHeight()/10;


        for (int y = 0; y < scene.getHeight(); y+=height) {
            for (int x = 0; x < scene.getWidth(); x+=width) {
                Shape rec = new Rectangle(x, y, width, height);
                rec.setFill(Color.WHITE);
                rec.setStroke(Color.BLACK);
                rec.setOnMouseDragExited(mouseEvent -> {
                    Paint curFill = rec.getFill();
                    if (Color.WHITE.equals(curFill)) {
                        rec.setFill(Color.ORANGE);
                    }
                    if (Color.ORANGE.equals(curFill)) {
                        rec.setFill(Color.WHITE);
                    }
                });
                pane.getChildren().add(rec);
            }
        }

        primaryStage.setTitle("Grid");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
