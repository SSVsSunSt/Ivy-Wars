package map.tile;

import map.Map;
import map.tile.tileType.TileType;

public class Tile {

	//	Properties
	public final TileType type;
	
	public int movementCost;
	
	public int defenseIndex;
	
	public boolean isPassable;
	
	//	Location
	public final int x;
	public final int y;
	
	public Tile(TileType type, Map map, int x, int y) {
		this.type = type;
		type.loadTypeToTile(map, x, y);
		
		this.x = x;
		this.y = y;
	}

	//	Setters
	public void setPassability(boolean passability) {this.isPassable = passability;}
	public void setMovementCost(int cost) {this.movementCost = cost;}
	public void setDefenseIndex(int index) {this.defenseIndex = index;}

}
