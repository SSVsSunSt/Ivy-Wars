package unit;

import java.awt.Point;
import map.Map;
import java.util.Random;

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
	
	public static final CombatInfo performCombat(Map map, Unit attackingUnit, Unit defendingUnit) 
	{
		/** @author SunnyP*/
		final Random random = new Random();
		final long FIGHT_COEFF_FIRST = 1;
		final long FIGHT_COEFF_SECOND = 1;
		final long HEALTH_COEFF = 1;
		double uFF = 1.1;
		double uSF = 1.1;
		byte uFH = 100;
		byte uSH = 100;
		double uFD = 0;
		double uSD = 0;
		float uFR = 4;
		float uSR = 4;
		boolean triple = false;
		int rndK = Math.round((1-(uFR+uSR)/8)*random.nextInt((int) (Math.round(((uFF+uFD+uFH+uSF+uSD+uSH)*FIGHT_COEFF_FIRST)/FIGHT_COEFF_SECOND))));
		if (random.nextInt(2) == 1)
		{
			rndK = -rndK;
		}
		if (triple)
		{
			byte uSC = 0, uFC = 0;
			for (byte i=1; i<4; i++)
			{
				double uFHAt = uFH-(uSF-uFD)/HEALTH_COEFF+rndK;
				double uSHAt = uFH-(uSF-uFD)/HEALTH_COEFF+rndK;
				if (Math.max(uFHAt, uSHAt) == uSHAt)
				{
					uSC++;
				}
				else
				{
					uFC++;
				}
			}
			if (Math.max(uSC, uFC) == uFC)
			{
				byte uSHA = (byte) (uSH-Math.round((uFF-uSD)/HEALTH_COEFF));
				System.out.println(uSHA);
			}
			else
			{
				byte uFHA = (byte) (uFH-Math.round((uSF-uFD)/HEALTH_COEFF));
				System.out.println(uFHA);
			}
		}
		byte uFHA = (byte) (uFH-Math.round((uSF-uFD)/HEALTH_COEFF+rndK));
		byte uSHA = (byte) (uSH-Math.round((uFF-uSD)/HEALTH_COEFF-rndK));
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
