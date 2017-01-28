package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import gameLoop.StepByStepGameLoop;

public class MainGameFrame extends JFrame {

	//	Size
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	//	Loop
	protected StepByStepGameLoop loop;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainGameFrame() {
		//	LAF
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		repaint();
		
		setVisible(true);
	}

}
