package main;

import gui.GamePanel;
import gui.MainGameFrame;

public class Start {
	
	public static void main(String[] args) {
		
		MainGameFrame frame = new MainGameFrame();
		frame.add(new GamePanel(frame, null));
		
	}
	
}
