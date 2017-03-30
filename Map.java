import java.util.ArrayList;

public class Map {
	private MapComponent[][] occupantArray;
	private boolean[][] maze;

	public Map() {
		this(13);
	}

	public Map(int s) {
		int size = 4 * s + 1;
		int randX = 2 * (int)(Math.random() * (size / 2 - 1));
		int randY = 2 * (int)(Math.random() * (size / 2 - 1));
		maze = MazeGenerator.generateMaze(size, size, randX, randY);
		System.out.println(randX + " " + randY);
	}

	public static void main(String[] args) {
		Map map = new Map(13);
		map.print();
	}

	public void moveComponent(int fromx, int fromy, int tox, int toy) {
		occupantArray[tox][toy] = occupantArray[fromx][fromy];
		occupantArray[fromx][fromy] = null;
	}

	public void print() {
		for (boolean[] row : maze) {
			for (boolean b : row) {
				if (b) System.out.print("* ");
				else System.out.print("| ");
			}
			System.out.println();
		}
	}
}