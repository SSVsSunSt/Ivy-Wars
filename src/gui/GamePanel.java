package gui;

import javax.swing.JPanel;

import gameLoop.StepByStepGameLoop;

public class GamePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//	Frame and loop
	private MainGameFrame frame;
	private StepByStepGameLoop loop;
	
	//	Thread
	private Thread screenUpdatingThread;
	
	public GamePanel(MainGameFrame frame, StepByStepGameLoop loop) {
		this.loop = loop;
		this.frame = frame;
		
		screenUpdatingThread = new Thread(this);
		screenUpdatingThread.start();
	}
	
	
	
	@Override
	public void run() {
	
		
		
	}

}
