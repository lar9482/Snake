import javafx.scene.paint.Color;
import java.util.ArrayList;

public class SnakeBody {
	private ArrayList<SnakeCell> allSnakeCells;
	private SnakeCell header;
	private int direction; // 0: Right, 1: Left, 2: UP, 3: Down.

	public SnakeBody() {
		this.allSnakeCells = new ArrayList<SnakeCell>();
		this.allSnakeCells.add(new SnakeCell());
		this.allSnakeCells.add(new SnakeCell(10, 9));
		this.header = new SnakeCell(10, 11);
		this.header.setColor(null);
		this.direction = 0;
	}

	//If moveBody() is called, a temporary copy of the header's coordinates of created. 
	//Then, a loop scans 
	public void moveBody() {
		int[] headerCoords = new int[2];
		headerCoords[0] = header.getCoords()[0];
		headerCoords[1] = header.getCoords()[1];

		for (int i = 0; i < allSnakeCells.size(); i++) {
			int[] temp = new int[2];
			temp[0] = headerCoords[0];
			temp[1] = headerCoords[1];

			headerCoords[0] = allSnakeCells.get(i).getCoords()[0];
			headerCoords[1] = allSnakeCells.get(i).getCoords()[1];

			allSnakeCells.get(i).getCoords()[0] = temp[0];
			allSnakeCells.get(i).getCoords()[1] = temp[1];

		}
	}

	public void selectHeader() {
		switch(direction) {
			case 0:
				header.getCoords()[1]++;
				break;
			case 1:
				header.getCoords()[1]--;
				break;
			case 2:
				header.getCoords()[0]--;
				break;
			case 3:
				header.getCoords()[0]++;
				break;
		}
	}
	//If a target and the header's coordinates overlap, a cell is added to the snake's body.
	public void addCell() {
		int[] firstSet = allSnakeCells.get(allSnakeCells.size() - 2).getCoords();
		int[] secondSet = allSnakeCells.get(allSnakeCells.size() - 1).getCoords();

		if (firstSet[0] == secondSet[0]) { // if the first and second cell have the same i or y
			if (firstSet[1] < secondSet[1]) {
				allSnakeCells.add(new SnakeCell(secondSet[0], secondSet[1] + 1));
			}
			else {
				allSnakeCells.add(new SnakeCell(secondSet[0], secondSet[1] - 1));
			}
		}

		else if (firstSet[1] == secondSet[1]) { // if the first and second cell have the same j or x
			if (firstSet[0] < secondSet[0]) {
				allSnakeCells.add(new SnakeCell(secondSet[0] + 1, secondSet[1]));
			}
			else {
				allSnakeCells.add(new SnakeCell(secondSet[0] - 1, secondSet[1]));
			}
		}
	}
	public ArrayList<SnakeCell> getBody() {
		return this.allSnakeCells;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public SnakeCell getHeader() {
		return this.header;
	}
}