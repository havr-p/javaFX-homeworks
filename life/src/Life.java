import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Life extends Application {




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane initialPane = new GridPane();
        initialPane.setId("initial-pane");
        InitialScene initialScene = new InitialScene(initialPane, primaryStage);
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(initialScene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
