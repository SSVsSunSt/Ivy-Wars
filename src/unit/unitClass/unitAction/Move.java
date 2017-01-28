package unit.unitClass.unitAction;

import ev3Controller.EV3Server;
import gameLoop.StepByStepGameLoop;
import map.Map;
import unit.Unit;
import unit.WrongUnitMoveException;

public class Move extends UnitAction {

	/**
	 * 
	 * 
	 * @author timat
	 */
	public Move() {
		super("Move", 1);
	}

	
	@Override
	public void performAction(Map map, Unit unit) {
		String message;
		while(StepByStepGameLoop.running) {
			
		//	Location chooser
		try {
		
			switch(Integer.parseInt(message)) {
			case EV3Server.UP : unit.move(0, 1, map);
			break;
			case EV3Server.DOWN : unit.move(0, -1, map);
			break;
			case EV3Server.RIGHT : unit.move(1, 0, map);
			break;
			case EV3Server.LEFT : unit.move(-1, 0, map);
			break;
			}
		
		} catch(WrongUnitMoveException e) {
			
		}
		if(unit.getCurrentMovementPoints() <= 0) {
			return;
		}
		
		}
	}

}
