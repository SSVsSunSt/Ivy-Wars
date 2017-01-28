package map.tile.tileType;

import java.util.LinkedList;

import map.Map;

public abstract class TileType {
	
	//	All tile types
	public static final LinkedList<TileType> ALL_TILE_TYPES = new LinkedList<TileType>();
	public static final TileType GRASSLAND = new GrassLand();
	
	//	Properties
	public final String name;
	public final int movementCost;
	public final int defenseIndex;
	
	public final boolean isPassable;
	
	public TileType(String name, int movementCost, int defIndex, boolean passable) {
		this.movementCost = movementCost;
		this.defenseIndex = defIndex;
		this.isPassable = passable;
		this.name = name;
		ALL_TILE_TYPES.add(this);
	}
	
	//	Loading tile
	public void loadTypeToTile(Map map, int x, int y) {
		map.getTile(x, y).setDefenseIndex(defenseIndex);
		map.getTile(x, y).setMovementCost(movementCost);
		map.getTile(x, y).setPassability(isPassable);
	}
	

}
