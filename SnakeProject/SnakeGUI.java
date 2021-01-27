import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import java.util.Timer; 
import java.util.TimerTask; 

public class SnakeGUI extends Application {
	private SnakeBoard snake;
	private Group game;
	private Scene window;
	private static final int wSide = 600;
	private static final int cellSize = 30;	
	private Timer timer;

	@Override
	public void start(Stage primaryStage) {
		snake = new SnakeBoard();
		game = new Group();
		int count = 0;
		for (int i = 0; i < wSide; i = i + cellSize) {
			for (int j = 0; j < wSide; j = j + cellSize) {
				Rectangle temp = new Rectangle((double) j, (double) i, (double) cellSize, (double) cellSize);
				temp.setStroke(Color.WHITE);
				temp.setFill(Color.BLACK);
				game.getChildren().add(temp);
				count++;
			}
		}
		
		snake.updateBoard();

		

		window = new Scene(game, wSide, wSide);
		timer = new Timer();
		timer.schedule(new task(), 0, 200);

		window.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				snake.getSnake().setDirection(2);
			}
			else if (e.getCode() == KeyCode.LEFT) {
				snake.getSnake().setDirection(1);
			}
			else if (e.getCode() == KeyCode.RIGHT) {
				snake.getSnake().setDirection(0);
			}
			else if (e.getCode() == KeyCode.DOWN) {
				snake.getSnake().setDirection(3);
			}
		});


		primaryStage.setScene(window);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch();
	}

	
	public void updateGame() {
		int count = 0; 
		for (int i = 0; i < snake.getBoard().length; i++) {
			for (int j = 0; j < snake.getBoard()[i].length; j++) {
				if (snake.getBoard()[i][j] != null) {
					Rectangle temp = (Rectangle) game.getChildren().get(count);
					temp.setStroke(Color.BLACK);
					temp.setFill(snake.getBoard()[i][j]);
				}
				else {
					Rectangle temp = (Rectangle) game.getChildren().get(count);
					temp.setStroke(Color.WHITE);
					temp.setFill(Color.BLACK);
				}
				//System.out.println(i + " " + j);
				//System.out.println(count);
				count++;
			}
		}
	}

	class task extends TimerTask {
		public void run() {
			if (snake.validMove()) {
				snake.appendSnake();
				snake.getSnake().moveBody();
				snake.getSnake().selectHeader();
				snake.removeExcessiveElements();
				snake.updateBoard(); 
				//System.out.println("ran");
				updateGame(); //Fix this.
				//System.out.println("ran1");
			}
		}
	}
}