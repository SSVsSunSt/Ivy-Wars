package unit;

import java.awt.Point;

import map.Map;

public class UnitBattleManager {

	/**
	 * This method checks if battle between unit and unit on tile (x, y) can be started;
	 * @param map : Map;
	 * @param unit : Unit;
	 * @param x : Integer - destination X;
	 * @param y : Integer - destination Y;
	 * @return : Boolean;
	 * @throws WrongUnitMoveException : If friendly unit is on destination tile;
	 */
	public static final boolean battleBegins(Map map, Unit unit, int x, int y) throws WrongUnitMoveException {
		if(map.getUnitOnTile(x, y).owner != unit.owner) {return true;}
		if(map.getUnitOnTile(x, y).owner == unit.owner) {throw new WrongUnitMoveException();}
		return false;
	}
	
	public static final CombatInfo performCombat(Map map, Unit attackingUnit, Unit defendingUnit) {
		/**
		 * @author SunnyP
		 */
		return(null);
	}
	public class CombatInfo {
		
		//	Parameters
		public final Unit retreatingUnit;
		public final Point retreatPoint;
		
		public CombatInfo(Unit unit, Point point) {
			retreatingUnit = unit;
			retreatPoint = point;
		}
		
	}
}
