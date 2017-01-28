package map;

import java.awt.Point;
import java.util.LinkedList;

import map.tile.Tile;
import map.tile.tileType.TileType;
import player.Player;
import unit.Unit;

public class Map {

	//	Units
	private LinkedList<Unit> units = new LinkedList<Unit>();
	
	//	Tiles
	private Tile[][] tiles;
	
	//	Players
	private Player[] players;
	
	//	Size
	public final int width;
	public final int height;
	
	public Map(int width, int height, int numberOfPlayers) {
		this.width = width;
		this.height = height;
		//	Loading tiles
		tiles = new Tile[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
			tiles[x][y] = new Tile(TileType.GRASSLAND, this, x, y);
		}}
		
		
		//	Loading players
		players = new Player[numberOfPlayers];
		for(int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player("Player" + (i + 1));
		}
		
	}
	
	//	Getters
	public Tile getTile(int x, int y) {return tiles[x][y];}
	public void removeUnit(Unit unit) {units.remove(unit);}
	public LinkedList<Unit> getAllUnits() {return units;}
	public Unit getUnit(int index) {return units.get(index);}
	public Player[] getPlayers() {return players;}
	public Tile getTile(Point p) {return tiles[p.x][p.y];}
	public boolean isAnyUnitOnTile(int x, int y) {
		for(Unit unit : units) {
			if(unit.getLocation().x == x & unit.getLocation().y == y) {return true;}
		}
		return false;
 	}
	public Unit getUnitOnTile(int x, int y) {
		for(Unit unit : units) {
			if(unit.getLocation().x == x & unit.getLocation().y == y) {return unit;}
		}
		return null;
	}
	
	//	Setters
	public void addUnit(Unit unit) {
		units.add(unit);
	}

	
	
	
}
