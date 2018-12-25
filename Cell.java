import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	public int width;
	public int height;
	public int row;
	public int col;
	public boolean isVisited = false;
	/**
	 * top, right, bottom, left edges - respectively
	 */
	public boolean[] sidesStatus = new boolean[] {true, true, true, true};
	
	public Cell(int _row, int _col) {
		this.width = 10;
		this.height = 10;
		this.row = _row;
		this.col = _col;
	}
	
	public void show(Graphics g) {
		g.setColor(Color.BLACK);
		//top
		if(this.sidesStatus[0]) {
			g.drawLine(this.col*this.width, this.row*this.height, this.col*this.width + this.width, this.row*this.height);
		}
		if(this.sidesStatus[1]) {
			//right
			g.drawLine(this.col*this.width + this.width, this.row*this.height, this.col*this.width + this.width, this.row*this.height + this.height);
		}
		if(this.sidesStatus[2]) {
			//bottom
			g.drawLine(this.col*this.width + this.width, this.row*this.height + this.height, this.col*this.width, this.row*this.height + this.height);
		}
		if(this.sidesStatus[3]) {
			//left
			g.drawLine(this.col*this.width, this.row*this.height + this.height, this.col*this.width, this.row*this.height);
		}
		if(this.isVisited == true) {
			g.setColor(Color.GREEN);
			g.fillRect(this.col*this.width, this.row*this.height, this.width, this.height);
		}
		if(this == Maze.currentCell) {
			g.setColor(Color.MAGENTA);
			g.fillRect(this.col*this.width, this.row*this.height, this.width, this.height);
		}
	}
}