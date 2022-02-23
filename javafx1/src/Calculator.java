import javafx.application.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;


public class Calculator extends Application {

    private double calculate(String operation, double arg1, double arg2) {
        switch (operation) {
            case "+":
                return arg1 + arg2;
            case "-":
                return arg1 - arg2;
            case "*":
                return arg1 * arg2;
            case "/":
                return arg1 / arg2;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
        TextField screen = new TextField();
        pane.getChildren().add(screen);
        screen.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        screen.setLayoutX(20);
        screen.setLayoutY(30);
        screen.setPrefWidth(300);

        int numWidth = 30;
        int numHeight = 20;
        int result;
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 4; j++) {
                Button num = new Button(Integer.toString(j + 3 * i));
                pane.getChildren().add(num);
                num.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
                num.setLayoutX((numWidth + 20) * j);
                num.setLayoutY((numHeight + 15) * i + screen.getLayoutY() + 40);
                num.setPrefWidth(numWidth);
                num.setPrefHeight(numHeight);
                num.setOnAction(actionEvent -> {
                    screen.setText(screen.getText() + num.getText()); //predpokladame ze v textovom poli budu vzdy len cisla
                });
            }
            Button zero = new Button("0");
            pane.getChildren().add(zero);
            zero.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
            zero.setLayoutX((numWidth + 20) * 2);
            zero.setLayoutY((numHeight + 15) * 3 + screen.getLayoutY() + 40);
            zero.setPrefWidth(numWidth);
            zero.setPrefHeight(numHeight);

            Button equals = new Button("=");
            pane.getChildren().add(equals);
            equals.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
            equals.setLayoutX((numWidth + 20) * 3);
            equals.setLayoutY((numHeight + 15) * 3 + screen.getLayoutY() + 40);
            equals.setPrefWidth(numWidth);
            equals.setPrefHeight(numHeight);


            Button plus = new Button("+");
            pane.getChildren().add(plus);
            plus.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
            plus.setLayoutX((numWidth + 20) * 4);
            plus.setLayoutY(screen.getLayoutY() + 40);
            plus.setPrefWidth(numWidth);
            plus.setPrefHeight(2*numHeight + 15);
            plus.setOnAction(actionEvent -> {
                final int i1 = Integer.parseInt(screen.getText());
                screen.clear();
            });


        }

    }
}
