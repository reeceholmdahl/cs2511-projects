package circles;

import java.util.stream.Stream;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        starter = new Button("Circles");
        
        root.setAlignment(Pos.CENTER);
        canvas.setPrefSize(COLS * CELL_SIZE, ROWS * CELL_SIZE);
        
        addButtonHandler();  // You must write
        
        root.getChildren().addAll(canvas, starter);
        
        primaryStage.setTitle("Java 8 Lab Exercise");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        //makeRow().forEach(x -> System.out.println(x));
//        makeRow().forEach(x -> System.out.println(x));
        //makeAllRows().forEach(r -> r.forEach(x -> System.out.println(x)));
    }
    
    /**
     * This method adds the handler to the button that gives
     * this application its behavior.
     */
    private void addButtonHandler() {
//        starter.setOnAction(e -> addToCanvas(new Circle(CELL_SIZE / 4)));
//        starter.setOnAction(e -> addRowToCanvas(makeRow()));
        starter.setOnAction(e -> {
            canvas.getChildren().clear();
            addAllRowsToCanvas(makeAllRows());
        });
    }
    
    private void addToCanvas(Circle circle) {
        double toX = col * CELL_SIZE + CELL_SIZE / 2;
        double toY = row * CELL_SIZE + CELL_SIZE / 2;
        double fromX = (COLS - 1) * CELL_SIZE + CELL_SIZE / 2;
        double fromY = (ROWS - 1) * CELL_SIZE + CELL_SIZE / 2;
        circle.setCenterX(fromX);
        circle.setCenterY(fromY);
        TranslateTransition tt = new TranslateTransition(Duration.millis(500));
        tt.setNode(circle);
        tt.setByX(toX - fromX);
        tt.setByY(toY - fromY);
        tt.play();
        ScaleTransition st = new ScaleTransition(Duration.millis(750 * (1 + Math.random())));
        st.setNode(circle);
        double randomScale = 1 + Math.random();
        st.setByX(randomScale);
        st.setByY(randomScale);
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
        Stream<Circle> circles = Stream.generate(() -> new Circle(CELL_SIZE / 4));
        return circles.limit(COLS);
    }
    
    private Stream<Stream<Circle>> makeAllRows() {
        Stream<Stream<Circle>> rows = Stream.generate(() -> makeRow());
        return rows.limit(ROWS);
    }
    
    private VBox root;
    private Pane canvas;
    private Button starter;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
