import javafx.animation.AnimationTimer;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * frame - takt, v ktorom sa bude vykonavat simulacia po stlaceni tlacidla "Spusti"
 * cells - pomocne 2d-pole, uchovavajuce deti korenoveho uzla typu GridPane (teda jednotlive bunky)
 */

public class MainScene extends Scene {
    private int width, height;
    private final static int MAX_GRID_SIZE = 700;

    private double frame;

    private Rectangle[][] cells;

    Simulation simulation;


    private void redraw() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (simulation.getCell(x, y) == Simulation.DEAD) {
                    cells[y][x].setFill(Color.BLACK);
                }
                if (simulation.getCell(x, y) == Simulation.ALIVE) {
                    cells[y][x].setFill(Color.YELLOW);
                }
            }
        }
    }

    /**
     * Zmeni stav bunky z zivej na mrtvu a naopak
     * pomocou suradnic relativneho umiestnenia pristupujeme k jednotlivym polickam
     * @param cell bunka, ktorej farbu a stav chceme zmenit
     */

    private void toggleColor(Rectangle cell) {
        Paint curFill = cell.getFill();
        if (Color.BLACK.equals(curFill)) {
            cell.setFill(Color.YELLOW);
            simulation.setCell(GridPane.getColumnIndex(cell), GridPane.getRowIndex(cell), Simulation.ALIVE);
        }
        if (Color.YELLOW.equals(curFill)) {
            cell.setFill(Color.BLACK);
            simulation.setCell(GridPane.getColumnIndex(cell), GridPane.getRowIndex(cell), Simulation.DEAD);
        }
    }

    public MainScene(Stage primaryStage, BorderPane mainPane, int width, int height) {
        super(mainPane);
        this.getStylesheets().add("styles.css");
        GridPane lifeGrid = new GridPane();

        this.width = width;
        this.height = height;
        simulation = new Simulation(width, height);
        cells = new Rectangle[height][width];


        mainPane.setCenter(lifeGrid);
        lifeGrid.setAlignment(Pos.CENTER);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double cellSize = MAX_GRID_SIZE / (double) Math.max(width, height);
                Rectangle cell = new Rectangle(cellSize, cellSize, Color.BLACK);
                cell.setStroke(Color.GREY);
                cell.setStrokeWidth(0.3);
                cell.setOnMouseClicked(mouseEvent -> {
                    toggleColor(cell);
                });
                cells[y][x] = cell;
                lifeGrid.add(cell, x, y);
            }
        }

        VBox buttonPanel = new VBox();                       // Vytvorenie oblasti typu VBox
        buttonPanel.setId("button-panel");

        mainPane.setRight(buttonPanel);                        // Nastavenie oblasti right ako pravej casti oblasti border

        Button btnNextStep = new Button("Krok");
        buttonPanel.getChildren().add(btnNextStep);


        Slider frameSlider = new Slider(0, 3, 0.1);
        frameSlider.setId("frame-slider");

        buttonPanel.getChildren().add(frameSlider);
        Button btnRun = new Button("Spusti");
        buttonPanel.getChildren().add(btnRun);
        Button btnStop = new Button("Zastav");
        buttonPanel.getChildren().add(btnStop);
        Button btnClear = new Button("Zmaž mriežku");
        buttonPanel.getChildren().add(btnClear);
        btnNextStep.setMaxWidth(Double.MAX_VALUE);
        btnRun.setMaxWidth(Double.MAX_VALUE);
        btnStop.setMaxWidth(Double.MAX_VALUE);
        btnClear.setMaxWidth(Double.MAX_VALUE);
        primaryStage.setScene(this);
        primaryStage.setResizable(true);
        primaryStage.sizeToScene();

        AnimationTimer animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                frame = frameSlider.getValue();
                if (now - lastUpdate >= (Math.pow(10, 9) * frame)) {
                    simulation.nextStep();
                    redraw();
                    lastUpdate = now;
                }
            }
        };

        btnNextStep.setOnAction(stepEvent -> {
            simulation.nextStep();
            redraw();
        });
        btnRun.setOnAction(runEvent -> {
            animationTimer.start();
            btnRun.setDisable(true);
            btnStop.setDisable(false);
        });
        btnStop.setOnAction(stopEvent -> {
            animationTimer.stop();
            btnStop.setDisable(true);
            btnRun.setDisable(false);
        });
        btnClear.setOnAction(clearEvent -> {
            simulation.clearGrid();
            redraw();
            btnStop.setDisable(true);
            btnRun.setDisable(false);
            animationTimer.stop();

        });
    }
}
