package gui;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gameLoop.StepByStepGameLoop;

public class GamePanel extends JPanel implements Runnable {

//	Size
	public static final int WIDTH = MainGameFrame.WIDTH;
	public static final int HEIGHT = MainGameFrame.HEIGHT;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//	Frame and loop
	private MainGameFrame frame;
	private StepByStepGameLoop loop;
	
	//	Thread
	private Thread screenUpdatingThread;
	
	//	Graphics 
	private Graphics g;
	
	public GamePanel(MainGameFrame frame, StepByStepGameLoop loop) { 
		this.loop = loop;
		this.frame = frame;
		repaint();
		
		
		screenUpdatingThread = new Thread(this);
		screenUpdatingThread.start();
		
		
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	 @Override
     public void paintComponent(Graphics g) {System.out.println("t");
        super.paintComponent(g);
        this.g = g;
        try {
			g.drawImage(ImageIO.read(ClassLoader.class.getResourceAsStream("/backgrounds/WallPaper1.png")), 0, 0, MainGameFrame.WIDTH, MainGameFrame.HEIGHT, null);
		} catch (IOException e) {e.printStackTrace();}
        g.setFont(new Font("Times new roman", Font.PLAIN, MainGameFrame.HEIGHT / 10));
        ((Graphics2D) g).setStroke(new BasicStroke(8));
        
		g.drawString("Player 1", MainGameFrame.WIDTH / 20, MainGameFrame.HEIGHT / 11);
		g.drawString("Player 2", MainGameFrame.WIDTH /  7 * 2, MainGameFrame.HEIGHT / 11);
		g.drawString("Battles: ", WIDTH / 7 * 4, HEIGHT / 11);
		
		g.drawRect(1, 1, WIDTH / 4, HEIGHT);
		g.drawRect(WIDTH / 4, 1, WIDTH / 4, HEIGHT);
		g.drawRect(WIDTH / 2, 1, WIDTH / 2, HEIGHT / 2);
		g.drawLine(0, HEIGHT / 8, WIDTH, HEIGHT / 8);
		
		try {
			g.drawImage(ImageIO.read(ClassLoader.class.getResourceAsStream("/icons/FightIcon.png")), WIDTH / 4 * 3 - HEIGHT / 12, HEIGHT / 5, MainGameFrame.HEIGHT / 6, MainGameFrame.HEIGHT / 6, null);
		} catch (IOException e) {}
		
		
	 }
	
	@Override
	public void run() {
		while(StepByStepGameLoop.running) {
			
		}
	}

}
