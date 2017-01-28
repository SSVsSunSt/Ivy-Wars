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
	
	
	public static final CombatInfo fight(Map map, Unit attackingUnit, Unit defendingUnit) 
	{
		/** @author SunnyP*/
		final Random random = new Random();
		final long FIGHT_COEFF_FIRST = 1;
		final long FIGHT_COEFF_SECOND = 1;
		final long HEALTH_COEFF = 1;
		double uFF = 1.1;
		double uSF = 1.1;
		double uFD = 0;
		double uSD = 0;
		boolean triple = false;
		int rndK = Math.round((1-(attackingUnit.getRank()+defendingUnit.getRank())/8)*random.nextInt((int) (Math.round(((uFF+uFD+attackingUnit.getHealth()+uSF+uSD+defendingUnit.getHealth())*FIGHT_COEFF_FIRST)/FIGHT_COEFF_SECOND))));
		if (random.nextInt(2) == 1)
		{
			rndK = -rndK;
		}
		if (triple)
		{
			byte attackingUnitPoints = 0, defendingUnitPoints = 0;
			for (byte i=1; i<4; i++)
			{
				double attackingUnitHealthTemp = defendingUnit.getHealth()-(uSF-uFD)/HEALTH_COEFF+rndK;
				double defendingUnitHealthTemp = attackingUnit.getHealth()-(uSF-uFD)/HEALTH_COEFF+rndK;
				if (Math.max(attackingUnitHealthTemp, defendingUnitHealthTemp) == defendingUnitHealthTemp)
				{
					attackingUnitPoints++;
				}
				else
				{
					defendingUnitPoints++;
				}
			}
			if (Math.max(attackingUnitPoints, defendingUnitPoints) == attackingUnitPoints)
			{
				defendingUnit.setHealth((byte) (defendingUnit.getHealth()-Math.round((uFF-uSD)/HEALTH_COEFF)));
				return(null);
			}
			else
			{
				attackingUnit.setHealth((byte) (attackingUnit.getHealth()-Math.round((uSF-uFD)/HEALTH_COEFF)));
				return(null);
			}
		}
		attackingUnit.setHealth((byte) (attackingUnit.getHealth()-Math.round((uSF-uFD)/HEALTH_COEFF+rndK)));
		defendingUnit.setHealth((byte) (defendingUnit.getHealth()-Math.round((uFF-uSD)/HEALTH_COEFF-rndK)));
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
