import javafx.scene.paint.Color;

public class SnakeCell {
	private int[] coords; //{y, x}, {i, j}
	private Color snakeColor = Color.LIMEGREEN;

	public SnakeCell() {
		this.coords = new int[2];
		this.coords[0] = 10;
		this.coords[1] = 10;
	}

	public SnakeCell(int i, int j) {
		this.coords = new int[2];
		this.coords[0] = i;
		this.coords[1] = j;
	}

	public int[] getCoords() {
		return this.coords;
	}

	public Color getColor() {
		return this.snakeColor;
	}

	public void setColor(Color color) {
		this.snakeColor = color;
	}

	public void setCoords(int i, int j) {
		this.coords[0] = i;
		this.coords[1] = j;
	}
		
}