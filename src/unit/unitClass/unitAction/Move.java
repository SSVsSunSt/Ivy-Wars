package unit.unitClass.unitAction;

import ev3Controller.EV3Server;
import map.Map;
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
	public void performAction(Map map) {
		//		Unit chooser
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
