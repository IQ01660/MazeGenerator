import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Maze {
	public static Cell sampleCell = new Cell(100, 100);
	public static final int COLS = Animation.WIDTH / sampleCell.width;
	public static final int ROWS = Animation.HEIGHT / sampleCell.height;
	public static Cell currentCell;
	public static Cell[][] cellsMatrix = new Cell[ROWS][COLS];
	public static Random rand = new Random();
	public static ArrayList<Cell> visitedCells = new ArrayList<Cell>();
	
	public static void positionCells() {
		for(int r = 0; r < ROWS; r++) {
			for(int c = 0; c < COLS; c++) {
				Maze.cellsMatrix[r][c] = new Cell(r, c);
				//the top left cell is currentCell initially
				if (r == 0 && c == 0) {
					Maze.currentCell = Maze.cellsMatrix[r][c];
					Maze.currentCell.isVisited = true;
				}
			}
		}
	}
	public static void showAll(Graphics g) {
		for(int r = 0; r < ROWS; r++) {
			for(int c = 0; c < COLS; c++) {
				Maze.cellsMatrix[r][c].show(g);
			}
		}
	}
	public static boolean areAllVisited() {
		boolean status = true;
		for(int r = 0; r < ROWS; r++) {
			for(int c = 0; c < COLS; c++) {
				//System.out.println(Maze.cellsMatrix[r][c].isVisited);

				if(Maze.cellsMatrix[r][c].isVisited == false) {
					status = false;
					break;
				}
			}
		}
		return status;
	}
	
	/**
	 * checks whether a coordinate is inside the matrix
	 * @param cur
	 */
	public static boolean isIn(int _row, int _col) {
		boolean status = false;
		if (_row >= 0 && _row < ROWS && _col >= 0 && _col < COLS) {
			status = true;
		}
		return status;
	}
	
	public static Cell unvisitedNeighbor(Cell cur) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		int topRow = cur.row - 1;
		int topCol = cur.col;
		if (Maze.isIn(topRow, topCol) && Maze.cellsMatrix[topRow][topCol].isVisited == false) {
			neighbors.add(Maze.cellsMatrix[topRow][topCol]);
		}
		int rightRow = cur.row;
		int rightCol = cur.col + 1;
		if (Maze.isIn(rightRow, rightCol) && Maze.cellsMatrix[rightRow][rightCol].isVisited == false) {
			neighbors.add(Maze.cellsMatrix[rightRow][rightCol]);
		}
		int bottomRow = cur.row + 1;
		int bottomCol = cur.col;
		if (Maze.isIn(bottomRow, bottomCol) && Maze.cellsMatrix[bottomRow][bottomCol].isVisited == false) {
			neighbors.add(Maze.cellsMatrix[bottomRow][bottomCol]);
		}
		int leftRow = cur.row;
		int leftCol = cur.col - 1;
		if (Maze.isIn(leftRow, leftCol) && Maze.cellsMatrix[leftRow][leftCol].isVisited == false) {
			neighbors.add(Maze.cellsMatrix[leftRow][leftCol]);
		}
		//picking a random cell
		Cell chosen = neighbors.get(rand.nextInt(neighbors.size()));
		//System.out.println(currentCell.col + " " + currentCell.row + " ; " + chosen.col + " " + chosen.row);
		
		return chosen;
	}
	public static boolean hasUnvisitedNeighbor(Cell cur) {
		boolean status = false;
		int topRow = cur.row - 1;
		int topCol = cur.col;
		if (Maze.isIn(topRow, topCol) && Maze.cellsMatrix[topRow][topCol].isVisited == false) {
			status = true;
		}
		int rightRow = cur.row;
		int rightCol = cur.col + 1;
		if (Maze.isIn(rightRow, rightCol) && Maze.cellsMatrix[rightRow][rightCol].isVisited == false) {
			status = true;
		}
		int bottomRow = cur.row + 1;
		int bottomCol = cur.col;
		if (Maze.isIn(bottomRow, bottomCol) && Maze.cellsMatrix[bottomRow][bottomCol].isVisited == false) {
			status = true;
		}
		int leftRow = cur.row;
		int leftCol = cur.col - 1;
		if (Maze.isIn(leftRow, leftCol) && Maze.cellsMatrix[leftRow][leftCol].isVisited == false) {
			status = true;
		}
		//picking a random cell
		return status;
	}
	public static void removeWall() {
		Cell chosen = Maze.visitedCells.get(visitedCells.size() - 1);
		if(chosen.col - currentCell.col < 0) {
			chosen.sidesStatus[1] = false;
			currentCell.sidesStatus[3] = false;
		}
		else if(chosen.col - currentCell.col > 0) {
			chosen.sidesStatus[3] = false;
			currentCell.sidesStatus[1] = false;
		}
		else if(chosen.row - currentCell.row < 0) {
			chosen.sidesStatus[2] = false;
			currentCell.sidesStatus[0] = false;
		}
		else if(chosen.row - currentCell.row > 0) {
			chosen.sidesStatus[0] = false;
			currentCell.sidesStatus[2] = false;
		}
		chosen.isVisited = true;
		//change the current cell
		currentCell = chosen;
		//System.out.println(currentCell.col + " " + currentCell.row);
	}
	public static void buildMaze() {
			if (Maze.hasUnvisitedNeighbor(Maze.currentCell)) {
				Maze.visitedCells.add(Maze.unvisitedNeighbor(currentCell));
				Maze.removeWall();
			}
			else if (Maze.visitedCells.size() > 0) {
				Maze.currentCell = Maze.visitedCells.remove(visitedCells.size() - 1);
			}
	}
}