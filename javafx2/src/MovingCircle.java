import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.scene.input.*;
import java.util.*;

public class MovingCircle extends Application {

    private enum MoveDirection {                         // Vymenovany typ reprezentujuci mozne smery pohybu kruhu
        UP,
        RIGHT,
        DOWN,
        LEFT
    };

    private MoveDirection moveDirection;         // Aktualny smer pohybu kruhu

    // Metoda, ktora na scene scene posunie kruh circle smerom moveDirection o pocet pixelov delta:
    private void moveCircle(Scene scene, Circle circle, MoveDirection moveDirection, double delta) {
        double newX;
        double newY;
        switch (moveDirection) {
            case UP:
                newY = circle.getCenterY() - delta;
                if (newY >= circle.getRadius()) {                       // Ak kruh nevyjde von zo sceny, posun ho
                    circle.setCenterY(newY);
                } else {                                                // V opacnom pripade zmen smer o 180 stupnov
                    this.moveDirection = MoveDirection.DOWN;
                }
                break;
            case DOWN:
                newY = circle.getCenterY() + delta;
                if (newY <= scene.getHeight() - circle.getRadius()) {   // Ak kruh nevyjde von zo sceny, posun ho
                    circle.setCenterY(newY);
                } else {                                                // V opacnom pripade zmen smer o 180 stupnov
                    this.moveDirection = MoveDirection.UP;
                }
                break;
            case LEFT:
                newX = circle.getCenterX() - delta;
                if (newX >= circle.getRadius()) {                       // Ak kruh nevyjde von zo sceny, posun ho
                    circle.setCenterX(newX);
                } else {                                                // V opacnom pripade zmen smer o 180 stupnov
                    this.moveDirection = MoveDirection.RIGHT;
                }
                break;
            case RIGHT:
                newX = circle.getCenterX() + delta;
                if (newX <= scene.getWidth() - circle.getRadius()) {    // Ak kruh nevyjde von zo sceny, posun ho
                    circle.setCenterX(newX);
                } else {                                                // V opacnom pripade zmen smer o 180 stupnov
                    this.moveDirection = MoveDirection.LEFT;
                }
                break;
        }
    }

    private Color randomColour(Random random) {
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 400, 400);

        Random random = new Random();

        double radius = 20;                                                            // Fixny polomer kruhu
        double x = radius + (random.nextDouble() * (scene.getWidth() - 2 * radius));   // Nahodna pociatocna x-ova suradnica kruhu
        double y = radius + (random.nextDouble() * (scene.getHeight() - 2 * radius));  // Nahodna pociatocna y-ova suradnica kruhu

        Circle circle = new Circle(x , y, radius);                                     // Vytvorenie kruhu s danymi parametrami
        pane.getChildren().add(circle);
        circle.setFill(randomColour(random));

        moveDirection = MoveDirection.values()[random.nextInt(4)];       // Nahodne zvoleny pociatocny smer pohybu

        circle.requestFocus();                                           // Kruh dostane fokus, aby mohol reagovat na klavesnicu
        circle.setOnKeyPressed(event -> {                                // Nastavime reakciu kruhu na stlacenie klavesy
            switch (event.getCode()) {
                case UP:                                                 // Ak bola stlacena niektora zo sipok, zmenime podla nej smer
                    moveDirection = MoveDirection.UP;
                    break;
                case RIGHT:
                    moveDirection = MoveDirection.RIGHT;
                    break;
                case DOWN:
                    moveDirection = MoveDirection.DOWN;
                    break;
                case LEFT:
                    moveDirection = MoveDirection.LEFT;
                    break;
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {  // Vytvorenie casovaca
            private long lastMoveTime = 0;                      // Casova peciatka posledneho pohybu kruhu
            private long lastColourChangeTime = 0;               // Casova peciatka poslednej zmeny farby kruhu

            @Override
            public void handle(long now) {
                // Ak bol kruh naposledy posunuty pred viac ako 20 milisekundami, posun ho o 5 pixelov
                if (now - lastMoveTime >= 20000000) {
                    moveCircle(scene, circle, moveDirection, 5);
                    lastMoveTime = now;
                }

                // Ak sa farba kruhu naposledy zmenila pred viac ako 500 milisekundami, zmen ju nahodne
                if (now - lastColourChangeTime >= 500000000) {
                    circle.setFill(randomColour(random));
                    lastColourChangeTime = now;
                }
            }
        };
        animationTimer.start();                                    // Spusti casovac

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pohyblivý kruh");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}