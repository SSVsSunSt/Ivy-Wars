package ev3Controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import gameLoop.StepByStepGameLoop;

public class EV3Server {
	
	//	Button constants
	public static final int ESCAPE = 0;
	public static final int ENTER = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	public static final int RIGHT = 5;
	
	//	Messages
	private LinkedList<String> controller1Messages = new LinkedList<String>();
	private LinkedList<String> controller2Messages = new LinkedList<String>();
	
	//	Server
	private ServerSocket server;
	private final int port = 25565;
	
	//	Controllers
	private Socket controller1;
	private Socket controller2;
	
	//	Threads
	private Thread controller1Thread = new Thread() {
		@Override
		public void run() {
			try {
				DataInputStream dis = new DataInputStream(controller1.getInputStream());
			while(StepByStepGameLoop.running) {
				controller1Messages.addLast(dis.readUTF());
			}
			} catch (IOException e) {System.err.println("Error in controller 1 DIS.");return;} 
		}
	};
	private Thread controller2Thread = new Thread() {
		@Override
		public void run() {
			try {
				DataInputStream dis = new DataInputStream(controller2.getInputStream());
			while(StepByStepGameLoop.running) {
				controller2Messages.addLast(dis.readUTF());
			}
			} catch (IOException e) {System.err.println("Error in controller 2 DIS.");return;} 
		
			
		}
	};
	
	public EV3Server() throws IOException {
		server = new ServerSocket(port);
	}
	
	//	Connection
	public Socket[] registerControllers() throws IOException {
		return new Socket[] {
		controller1 = (server.accept()),
		controller2 = (server.accept())
		};
	}
	public void startControllerThreads() {
		controller1Thread.start();
		controller2Thread.start();
	}
	
	//	Messages
	public String getCommandFromFirstController() {
		if(controller1Messages.isEmpty()) {return null;}
		return controller1Messages.removeFirst();
	}
	public String getCommandFromSecondController() {
		if(controller2Messages.isEmpty()) {return null;}
		return controller2Messages.removeFirst();
	}

}
