package unit;

import java.awt.Point;

import map.Map;

public class UnitMovesManager {
	
	/**
	 * This method gives movement points for fastest route to target;
	 * @param map : Map;
	 * @param unit : Unit;
	 * @param x : Integer - destination X;
	 * @param y : Integer - destination Y;
	 * @return : Integer;
	 * @throws WrongUnitMoveException : If it stumbles on impassable tile;
	 */
	public static final int getMovementPoints(Map map, Unit unit, int x, int y) throws WrongUnitMoveException {
		Point[] traversedTiles = calculateFastestRoute(unit, x, y);
		
		int totalMovementPoints = unit.getCurrentMovementPoints();
		
		for(Point p : traversedTiles) {
			if(!map.getTile(p).isPassable) {
				throw new WrongUnitMoveException();
			}
			if(map.isAnyUnitOnTile(p.x, p.y)) {
				if(map.getUnitOnTile(p.x, p.y).owner == unit.owner) {
				throw new WrongUnitMoveException();
			}}
 			totalMovementPoints = totalMovementPoints - map.getTile(p).movementCost;
		}
		return totalMovementPoints;
	}
	
	/**
	 * This method returns fastest route; (For now it is just a BROKEN LINE); 
	 * @param unit : Unit;
	 * @param x : Integer - Destination X;
	 * @param y : Integer - Destination Y;
	 * @return : Point[] : Point[] - the way;
	 */
	public static final Point[] calculateFastestRoute(Unit unit, int x, int y) {
		Point[] traversedTiles = new Point[getDistance(unit, x, y)];
		
		
		int curX = unit.getLocation().x;
		int curY = unit.getLocation().y;
		
		for(int i = 0; i < getDistance(unit, x, y); i++) {
			if(Math.abs(curX - x) != 0 & Math.abs(curY - y) != 0) {
				if(x < curX) {
					if(y > curY) {
						traversedTiles[i] = new Point(curX - 1, curY + 1);
					} else {
						traversedTiles[i] = new Point(curX - 1, curY - 1);
					}
				} else {
					if(y > curY) {
						traversedTiles[i] = new Point(curX + 1, curY + 1);
					} else {
						traversedTiles[i] = new Point(curX + 1, curY - 1);
					}
				}
			} else {
				if(curX - x == 0) {
					if(y > curY) {
						traversedTiles[i] = new Point(curX, curY + 1);
					} else {
						traversedTiles[i] = new Point(curX, curY - 1);
					}
				} else {
					if(x > curX) {
						traversedTiles[i] = new Point(curX + 1, curY);
					} else {
						traversedTiles[i] = new Point(curX - 1, curY);
					}
				}
				
			}
			curX = traversedTiles[i].x;
			curY = traversedTiles[i].y;
		}
		return traversedTiles;
	}
	
	/**
	 * This method returns BROKEN LINE way distance (it can be with impassable tiles);
	 * @param unit : Unit;
	 * @param x : Integer - destination Y; 
	 * @param y : Integer - destination Y;
	 * @return : Integer - distance;
	 */
	public static final int getDistance(Unit unit, int x, int y) {
		return (Math.abs(unit.getLocation().x - x) >= Math.abs(unit.getLocation().y - y))
				? Math.abs(unit.getLocation().x - x) : Math.abs(unit.getLocation().y - y);
	}
	
	/**
	 * This method checks movement points for unit to go to given location;
	 * @param destination : Point - destination point;
	 * @param unit : Unit;
	 * @param map : Map;
	 * @return : Boolean;
	 * @throws WrongUnitMoveException : If it stumbles on impassable tile;
	 */
	public static final boolean canGoTo(Point destination, Unit unit, Map map) throws WrongUnitMoveException {
		if(unit.getCurrentMovementPoints() < getMovementPoints(map, unit, destination.x, destination.y)) {
			return false;
		}
		return true;
	}
	
	
}
