import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final int FPS = 60;
	
	public Animation() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setup();
		Thread myThread = new Thread(new Runner());
		myThread.start();
	}
	
	public static void main(String[] args) {
		JFrame mazeFrame = new JFrame("Maze Generator");
		mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mazeFrame.setContentPane(new Animation());
		mazeFrame.pack();
		mazeFrame.setVisible(true);
	}
	
	public void setup() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//animation
		Maze.buildMaze();
		Maze.showAll(g);
	}
	
	class Runner implements Runnable {

		@Override
		public void run() {
			Maze.positionCells();
			// TODO Auto-generated method stub
			while(Maze.areAllVisited() == false) {
				
				repaint();
				try {
					Thread.sleep(1000/FPS);
				}
				catch (Exception e) {}
			}
		}
		
	}
}