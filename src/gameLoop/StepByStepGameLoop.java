package gameLoop;

import java.io.IOException;

import ev3Controller.EV3Server;
import map.Map;
import unit.WrongUnitMoveException;

public class StepByStepGameLoop implements Runnable {

	//	Game progress
	public int currentTurn;
	
	//	Controllers
	private static EV3Server server;
	
	//	Game stuff
	protected Map map;
	
	//	Thread
	private Thread gameThread;
	public static boolean running = false;
	
	/**
	 * This constructor creates new GameLoop object;
	 */
	public StepByStepGameLoop() {
	}

	
	/**
	 * This method sets map to the game; Not work if the game is already started;
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * This method initializes all game components;
	 * @throws IOException 
	 */
	public void initConnection() throws IOException {
		//	Loading map
		if(map == null) {
			map = new Map(10, 10, 2);
		}
		//	Connecting controllers
		server = new EV3Server();
		server.registerControllers();
		server.startControllerThreads();
	}
	
	/**
	 * This method begins the game;
	 */
	public void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		running = true;
		
		while(!isAnyPlayerWins() & running) {
			//	Player 1
			Thread thread1 = new Thread(new Runnable() {
				@Override
				public void run() {
					String message;
					int unitIndex = 0;
					int phase = -1;
					while(running) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {}
						message = server.getCommandFromSecondController(); 
						if(message == null) {continue;}
						
						//	Action chooser
						if(phase == -1) {
							
						}
						
						//	Unit chooser
						if(phase == 0) {
							switch(Integer.parseInt(message)) {
								case EV3Server.RIGHT : 
									unitIndex++;
									if(map.getAllUnits().get(unitIndex).owner != map.getPlayers()[0]) {
										unitIndex++;
									}
									if(unitIndex > map.getAllUnits().size()) {
										unitIndex = 0;
									}
								break;
								case EV3Server.LEFT : 
									unitIndex--;
									if(map.getAllUnits().get(unitIndex).owner != map.getPlayers()[0]) {
										unitIndex--;
									}
									if(unitIndex < 0) {
										unitIndex = map.getAllUnits().size() - 1;
									}
								break;
							}
						}
						
						//	Unit selector
						if(Integer.parseInt(message) == EV3Server.ENTER & phase == 0) {
							phase = 1;
						}
						
						//	Location chooser
						try {
						if(phase == 1) {
							switch(Integer.parseInt(message)) {
							case EV3Server.UP : map.getAllUnits().get(unitIndex).move(0, 1, map);
							break;
							case EV3Server.DOWN : map.getAllUnits().get(unitIndex).move(0, -1, map);
							break;
							case EV3Server.RIGHT : map.getAllUnits().get(unitIndex).move(1, 0, map);
							break;
							case EV3Server.LEFT : map.getAllUnits().get(unitIndex).move(-1, 0, map);
							break;
							}
						}
						} catch(WrongUnitMoveException e) {
							
						}
						if(map.getAllUnits().get(unitIndex).getCurrentMovementPoints() <= 0) {
							return;
						}
						
						//	Back
						if(Integer.parseInt(message) == EV3Server.ESCAPE) {
							phase = 0;
						}
						
					}
				}
			} ,"player1Thread");
			thread1.start();
			
			//	Player 2
			Thread thread2 = new Thread(new Runnable() {
				@Override
				public void run() {
					String message;
					int unitIndex = 0;
					int phase = 0;
					while(running) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {}
						message = server.getCommandFromFirstController(); 
						if(message == null) {continue;}
						
						//	Unit chooser
						if(phase == 0) {
							switch(Integer.parseInt(message)) {
								case EV3Server.RIGHT : 
									unitIndex++;
									if(map.getAllUnits().get(unitIndex).owner != map.getPlayers()[1]) {
										unitIndex++;
									}
									if(unitIndex > map.getAllUnits().size()) {
										unitIndex = 0;
									}
								break;
								case EV3Server.LEFT : 
									unitIndex--;
									if(map.getAllUnits().get(unitIndex).owner != map.getPlayers()[1]) {
										unitIndex--;
									}
									if(unitIndex < 0) {
										unitIndex = map.getAllUnits().size() - 1;
									}
								break;
							}
						}
						
						//	Unit selector
						if(Integer.parseInt(message) == EV3Server.ENTER & phase == 0) {
							phase = 1;
						}
						
						//	Location chooser
						try {
						if(phase == 1) {
							switch(Integer.parseInt(message)) {
							case EV3Server.UP : map.getAllUnits().get(unitIndex).move(0, 1, map);
							break;
							case EV3Server.DOWN : map.getAllUnits().get(unitIndex).move(0, -1, map);
							break;
							case EV3Server.RIGHT : map.getAllUnits().get(unitIndex).move(1, 0, map);
							break;
							case EV3Server.LEFT : map.getAllUnits().get(unitIndex).move(-1, 0, map);
							break;
							}
						}
						} catch(WrongUnitMoveException e) {
							
						}
						if(map.getAllUnits().get(unitIndex).getCurrentMovementPoints() <= 0) {
							return;
						}
						
						//	Back
						if(Integer.parseInt(message) == EV3Server.ESCAPE) {
							phase = 0;
						}
						
					}
				}
			} ,"player2Thread");
			thread2.start();
			
			while(thread1.isAlive() | thread2.isAlive()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
			}
			currentTurn++;
		}
	}
	
	//	Victory
	private boolean isAnyPlayerWins() {
		return false;
	}
	
	//	Getters
	public static EV3Server getServer() {return server;}
}
