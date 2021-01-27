import javafx.scene.paint.Color;
import java.lang.Math;
import java.util.Scanner;

public class SnakeBoard {
	private SnakeBody snake;
	private Color[][] board;
	private SnakeCell target;
	private int gridSize = 20;

	public SnakeBoard() {
		this.snake = new SnakeBody();
		this.board = new Color[gridSize][gridSize];

		int i = (int) (Math.random() * gridSize);
		int j = (int) (Math.random() * gridSize);
		this.target = new SnakeCell(i, j);
		this.target.setColor(Color.RED);
		selectTarget();
		updateBoard();
		
	}
	
	public void updateBoard() {
		for (int i = 0; i < snake.getBody().size(); i++ ) {
			int y = snake.getBody().get(i).getCoords()[0];
			int x = snake.getBody().get(i).getCoords()[1];

			board[y][x] = snake.getBody().get(i).getColor();
		}

		board[target.getCoords()[0]][target.getCoords()[1]] = target.getColor();
	}

	//If header.getCoords() is placed onto allSnakeCells() or outside of the board, validMove() returns false. 
	public boolean validMove() {
		int[] headerCoords = snake.getHeader().getCoords();
		if (headerCoords[0] > (board.length - 1) || headerCoords[0] < 0) 
			return false;
		else if (headerCoords[1] > (board.length - 1) || headerCoords[1] < 0) 
			return false;
		else if (board[headerCoords[0]][headerCoords[1]] != null && board[headerCoords[0]][headerCoords[1]] == Color.LIMEGREEN)
			return false;
		else 
			return true; 
	}

	public void appendSnake() {
		//If the coordinates of the target and header are equal, 
		if (target.getCoords()[0] == snake.getHeader().getCoords()[0] && target.getCoords()[1] == snake.getHeader().getCoords()[1]) {
			snake.addCell();
			selectTarget();
		}
	}
	//This method generates the coordinates of the target cell.
	public void selectTarget() {

		int i = target.getCoords()[0];
		int j = target.getCoords()[1];
		board[i][j] = null;

		i = (int) (Math.random() * gridSize);
		j = (int) (Math.random() * gridSize);
		while (board[i][j] == Color.LIMEGREEN) {
			i = (int) (Math.random() * gridSize);
			j = (int) (Math.random() * gridSize);
		}
		target.setCoords(i, j);
		
	}

	

	public boolean searchSnakeBody(int i, int j) {
		boolean result = false;
		for (int count = 0; count < snake.getBody().size(); count++) {
			if (i == snake.getBody().get(count).getCoords()[0] && j == snake.getBody().get(count).getCoords()[1]) {
				result = true;
				break;
			}
		}
		return result;
	}
	//Fix this!!!
	public void removeExcessiveElements() {
		int[] lastCoords = snake.getBody().get(snake.getBody().size() - 1).getCoords();

		int i = lastCoords[0];
		int j = lastCoords[1];
		for (int l = i - 1; l <= i + 1; l = l + 2) {
			if (l < 0 || l > board.length) {
				continue;
			}
			if (board[l][j] != null && board[l][j] == Color.LIMEGREEN) {
				if (searchSnakeBody(l, j)) {
					continue;
				}
				
				board[l][j] = null;
			}
		}

		for (int k = j - 1; k <= j + 1; k = k + 2) {
			if (k < 0 || k > board.length) {
				continue;
			}
			if (board[i][k] != null && board[i][k] == Color.LIMEGREEN) {
				if (searchSnakeBody(i, k)) {
					continue;
				}
				
				board[i][k] = null;
			}
		}
	}

	public SnakeBody getSnake() {
		return this.snake;
	}

	public Color[][] getBoard() {
		return this.board;
	}
	//The following code is for testing the program

	public void displayBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		SnakeBoard test = new SnakeBoard();


		test.displayBoard();
		char inputChar = input.nextLine().charAt(0);
		while (inputChar != 'q') {
			//Simulates the ticks by the clock
			if (inputChar == 't') {
				if (test.validMove()) {
					test.appendSnake();
					test.getSnake().moveBody();
					test.getSnake().selectHeader();
					test.removeExcessiveElements();
					test.updateBoard();
				}
				else {
					System.out.println("Game Over!!!");

				}
			}

			else if (inputChar == 'u') {
				test.getSnake().setDirection(2);
			}
			else if (inputChar == 'd') {
				test.getSnake().setDirection(3);
			}
			else if (inputChar == 'l') {
				test.getSnake().setDirection(1);
			}
			else if (inputChar == 'r') {
				test.getSnake().setDirection(0);
			}
			else if (inputChar == 'a') {
				test.getSnake().addCell();
				test.updateBoard();
			}
			System.out.println();
			System.out.println();
			test.displayBoard();
			inputChar = input.nextLine().charAt(0);
		}
	}
	
}