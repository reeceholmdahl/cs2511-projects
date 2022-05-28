package circles;

import java.util.stream.Stream;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A lab exercise to introduce Java 8 lambdas and streams.
 * @author Your name here
 */
public class Circles extends Application {
    
    public static final int ROWS = 4;
    public static final int COLS = 5;
    public static final int CELL_SIZE = 100;
    
    private int row = 0;
    private int col = 0;
    
    @Override
    public void start(Stage primaryStage) {
        root = new VBox();
        canvas = new Pane();
        controls = new HBox(10);
        rowSpinner = new Spinner(1, 5, ROWS);
        rowLabel = new Label("Rows");
        colSpinner = new Spinner(1, 5, COLS);
        colLabel = new Label("Columns");
        xScaleSpinner = new Spinner(-3, 3, 0);
        xScaleLabel = new Label("X Scale");
        yScaleSpinner = new Spinner(-3, 3, 0);
        yScaleLabel = new Label("Y Scale");
        sizeSlider = new Slider(50, 150, 100);
        sizeLabel = new Label("Cell Size");
        cellSizeText = new Label("100");
        
        root.setAlignment(Pos.CENTER);
        canvas.setPrefSize(5 * 150, 5 * 150);
        
        Runnable reset = () -> {
            canvas.getChildren().clear();
            addAllRowsToCanvas(makeAllRows());
        };
        
        rowSpinner.valueProperty().addListener(x -> {
            rows = (int) rowSpinner.valueProperty().get();
            reset.run();
        });
        rowSpinner.setPrefWidth(60);
        
        colSpinner.valueProperty().addListener(x -> {
            cols = (int) colSpinner.valueProperty().get();
            reset.run();
        });
        colSpinner.setPrefWidth(60);
        
        xScaleSpinner.valueProperty().addListener(x -> {
            xScale = (int) xScaleSpinner.valueProperty().get();
            reset.run();
        });
        xScaleSpinner.setPrefWidth(60);
        
        yScaleSpinner.valueProperty().addListener(x -> {
            yScale = (int) yScaleSpinner.valueProperty().get();
            reset.run();
        });
        yScaleSpinner.setPrefWidth(60);
        
        sizeSlider.valueProperty().addListener(x -> {
            cellSize = (int) sizeSlider.valueProperty().get();
            cellSizeText.setText("" + cellSize);
            reset.run();
        });
        
        controls.setAlignment(Pos.CENTER);
        
        VBox rowControl = new VBox(10, rowLabel, rowSpinner);
        rowControl.setAlignment(Pos.CENTER);
        
        VBox colControl = new VBox(10, colLabel, colSpinner);
        colControl.setAlignment(Pos.CENTER);
        
        VBox sizeControl = new VBox(10, sizeLabel, new HBox(sizeSlider, cellSizeText));
        sizeControl.setAlignment(Pos.CENTER);
        
        VBox xScaleControl = new VBox(10, xScaleLabel, xScaleSpinner);
        xScaleControl.setAlignment(Pos.CENTER);
        
        VBox yScaleControl = new VBox(10, yScaleLabel, yScaleSpinner);
        yScaleControl.setAlignment(Pos.CENTER);
        
        controls.getChildren().addAll(
                rowControl,
                colControl,
                sizeControl,
                xScaleControl,
                yScaleControl
        );
        
        root.getChildren().addAll(canvas, controls);
        
        primaryStage.setTitle("Java 8 Lab Exercise");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        reset.run();
    }
    
    private void addToCanvas(Circle circle) {
        double toX = col * cellSize + cellSize / 2;
        double toY = row * cellSize + cellSize / 2;
        double fromX = (cols - 1) * cellSize + cellSize / 2;
        double fromY = (rows - 1) * cellSize + cellSize / 2;
        circle.setCenterX(fromX);
        circle.setCenterY(fromY);
        TranslateTransition tt = new TranslateTransition(Duration.millis(500));
        tt.setNode(circle);
        tt.setByX(toX - fromX);
        tt.setByY(toY - fromY);
        tt.play();
        ScaleTransition st = new ScaleTransition(Duration.millis(750 * (1 + Math.random())));
        st.setNode(circle);
        st.setByX(xScale);
        st.setByY(yScale);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
        circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1.0));
        canvas.getChildren().add(circle);
    }
    
    private void addRowToCanvas(Stream<Circle> row) {
        col = 0;
        row.forEach(c -> {
            addToCanvas(c);
            ++col;
        });
    }
    
    private void addAllRowsToCanvas(Stream<Stream<Circle>> allRows) {
        row = 0;
        allRows.forEach(r -> {
            addRowToCanvas(r);
            ++row;
        });
    }
    
    private Stream<Circle> makeRow() {
        Stream<Circle> circles = Stream.generate(() -> new Circle(cellSize / 4));
        return circles.limit(cols);
    }
    
    private Stream<Stream<Circle>> makeAllRows() {
        Stream<Stream<Circle>> rows = Stream.generate(() -> makeRow());
        return rows.limit(this.rows);
    }
    
    private VBox root;
    private Pane canvas;
    private HBox controls;
    private Spinner rowSpinner, colSpinner, xScaleSpinner, yScaleSpinner;
    private Slider sizeSlider;
    private Label rowLabel, colLabel, sizeLabel, cellSizeText, xScaleLabel, yScaleLabel;
    private int xScale = 0;
    private int yScale = 0;
    private int cellSize = CELL_SIZE;
    private int cols = COLS;
    private int rows = ROWS;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
