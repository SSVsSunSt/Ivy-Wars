package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gameLoop.StepByStepGameLoop;

public class ConnectionPanel extends JPanel  {

	//	Size
	private int WIDTH = MainGameFrame.WIDTH;
	private int HEIGHT = MainGameFrame.HEIGHT;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	Loop
	private StepByStepGameLoop loop;
	
	//	Listeners
	private ItemListener listener = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			switch(((String) arg0.getItem())) {
			case "Not sellected!" : connect.setEnabled(false); break;
			case "Step by step" : connect.setEnabled(true); loop = new StepByStepGameLoop();
			}
		}
		
	};
	
	//	Location
	private final int xLoc;
	
	//	Outside components
	private JButton connect;
	private JComboBox<String> box;
	private JButton status;
	private JButton go;
	private JButton back;
	
	public ConnectionPanel(MainGameFrame frame) {
		xLoc = MainGameFrame.WIDTH / 9;
		
		setSize(MainGameFrame.WIDTH, MainGameFrame.HEIGHT);
		setLayout(null);
		
		repaint();
		
		box = new JComboBox<String>(new String[] {
				"Not sellected!", "Step by step"
		});
		box.setLocation(this.xLoc, HEIGHT / 3);
		box.setSize(WIDTH / 4, HEIGHT / 12);
		box.addItemListener(listener);
		add(box);
		
       status = new JButton("Status: Not connected!");
       status.setFont(new Font("Times new roman", Font.PLAIN, MainGameFrame.HEIGHT / 20));
       status.setLocation(this.xLoc, MainGameFrame.HEIGHT / 7);
       status.setSize(WIDTH / 7 * 4, HEIGHT / 6);    
       status.setFocusable(false);
       
       add(status);
       
       go = new JButton("GO!");
       go.setEnabled(false);
       go.setLocation(this.xLoc, HEIGHT / 3 * 2);
       go.setSize(WIDTH / 7, HEIGHT / 7);
       go.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			loop.startGameLoop();
			frame.remove(ConnectionPanel.this);
			frame.add(new GamePanel(frame, loop));
		}});
       add(go);
       
       
       connect = new JButton("Connect!");
       connect.setEnabled(false);
       connect.setFont(new Font("Times new roman", Font.PLAIN, MainGameFrame.HEIGHT / 15));
       connect.setLocation(this.xLoc, HEIGHT / 7 * 3);
       connect.setSize(WIDTH / 3, HEIGHT / 5);
       connect.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			status.setText("Status: Connecting!");
			new Thread(new Runnable() {
				@Override
				public void run() {
				back.setEnabled(false);
				try {	
				loop.initConnection();
				loop.getServer().registerControllers();
				} catch (Exception e) {
					return;
				}
				status.setText("Status: Connected!");
				go.setEnabled(true);	  
				}	
			}).start();
			
			
		}});
       add(connect);
       
       back = new JButton("Back!");
       back.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.remove(ConnectionPanel.this);
			frame.add(new MenuPanel(frame));
		}});
       back.setLocation(WIDTH / 4 * 3, HEIGHT / 10);
       back.setSize(WIDTH / 7, HEIGHT / 8);
       add(back);
       
       SwingUtilities.updateComponentTreeUI(frame);
	}
	
	
	 @Override
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
			g.drawImage(ImageIO.read(ClassLoader.class.getResourceAsStream("/backgrounds/WallPaper1.png")), 0, 0, MainGameFrame.WIDTH, MainGameFrame.HEIGHT, null);
		} catch (IOException e) {e.printStackTrace();}
        g.setFont(new Font("Times new roman", Font.PLAIN, MainGameFrame.HEIGHT / 20));
 		g.drawString("Controller1", MainGameFrame.WIDTH / 3, MainGameFrame.HEIGHT / 10);
     }
}
