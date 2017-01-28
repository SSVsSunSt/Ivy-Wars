package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MenuPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuPanel(MainGameFrame frame) {
		setSize(MainGameFrame.WIDTH, MainGameFrame.HEIGHT);
		setLayout(null);
		
		
		
		
		repaint();
		
		JButton play = new JButton("Play!");
		play.setFont(new Font("Times new roman", Font.PLAIN, MainGameFrame.HEIGHT / 12));
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(MenuPanel.this);
				frame.add(new ConnectionPanel(frame));
				}});
		play.setSize(MainGameFrame.WIDTH / 3, 100);
		play.setLocation(MainGameFrame.WIDTH / 4, MainGameFrame.HEIGHT / 3);
		
		JButton exit = new JButton("Exit!");
		exit.setFont(new Font("Times new roman", Font.PLAIN, MainGameFrame.HEIGHT / 12));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
		exit.setSize(MainGameFrame.WIDTH / 3, 100);
		exit.setLocation(MainGameFrame.WIDTH / 4, MainGameFrame.HEIGHT / 2);
		
		
		
		add(exit);
		add(play);
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	 @Override
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
			g.drawImage(ImageIO.read(ClassLoader.class.getResourceAsStream("/backgrounds/WallPaper1.png")), 0, 0, MainGameFrame.WIDTH, MainGameFrame.HEIGHT, null);
		} catch (IOException e) {e.printStackTrace();}
        g.setFont(new Font("Times new roman", Font.PLAIN, MainGameFrame.HEIGHT / 10));
		g.drawString("Table wars", MainGameFrame.WIDTH / 4, MainGameFrame.HEIGHT / 5);
	 }
	

}
