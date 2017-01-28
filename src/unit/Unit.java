package unit;

import java.awt.Point;

import map.Map;
import player.Player;
import unit.unitClass.UnitClass;
import unit.unitClass.unitAction.UnitAction;

public class Unit {

	//	Unit class
	public final UnitClass unitClass;
	
	//	Location
	private int x;
	private int y;
	
	//	Owner
	public final Player owner;
	
	//	Moves
	private byte movementPoints;
	
	//	Health
	private byte currentHealth;
	
	//	Rank
	private float rank;
	
	public Unit(UnitClass unitClass, Player player) {
		this.unitClass = unitClass;
		this.owner = player;
		currentHealth = 100;
		movementPoints = unitClass.movement;
	}
	
	/**
	 * This method sets location to this unit;
	 * @param x : Integer;
	 * @param y : Integer;
	 */
	public void setLocation(int x, int y) {this.x = x; this.y = y; }
	/**
	 * This method returns this unit current location;
	 * @return : Point;
	 */
	public Point getLocation() {return new Point(x, y);}
	
	/**
	 * This method moves this unit to give coordinates; (locX + x, locY + y);
	 * It will throw exception if distance is more than it can move;
	 * @param x : Integer;
	 * @param y : Integer;
	 */
	public void move(int x, int y, Map map) throws WrongUnitMoveException {
		if(!UnitMovesManager.canGoTo(new Point(this.x + x, this.y + y), this, map)) {
			throw new WrongUnitMoveException();
		}
		if(UnitBattleManager.battleBegins(map, this, this.x + x, this.y + y)) {
			UnitBattleManager.fight(map, this, map.getUnitOnTile(this.x + x, this.y + y));
		} else {
			this.x = this.x + x;
			this.y = this.y + y;
		}
		
	}
	
	/**
	 * This method resets unit movements points;
	 */
	public void resetUnitMovementPoint() {
		movementPoints = unitClass.movement;
	}
	
	/**
	 * This method gives current movement points;
	 * @return movemetPoints : Integer;
	 */
	public int getCurrentMovementPoints() {
		return movementPoints;
	}
	
	/**
	 * This method gives current health of the unit;
	 * @return : Integer;
	 */
	public byte getHealth() {return currentHealth;}
	
	/**
	 * This method sets health to this unit;
	 * @param health : Integer;
	 */
	public void setHealth(byte health) {this.currentHealth = health;}
	
	/**
	 * This method gives rank of the unit;
	 * @return : float;
	 * @author SunnyP
	 */
	public float getRank() {
		return rank;
	}
	
	/**
	 * This method sets rank to this unit;
	 * @param rank : float;
	 * @author SunnyP
	 */
	public void setRank(float rankN) {
		this.rank = rankN;
	}
	
	/**
	 * This method gives action;
	 * @param index : Integer;
	 * @return : Action;
	 * @author timat
	 */
	public UnitAction getAction(int index) {
		return unitClass.actions[index];
	}
}

