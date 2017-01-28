package main;

import gui.MainGameFrame;
import gui.MenuPanel;

public class Start {
	
	public static void main(String[] args) {
		
		MainGameFrame frame = new MainGameFrame();
		frame.add(new MenuPanel(frame));
		
	}
	
}
