package unit.unitClass.unitAction;

import java.util.LinkedList;

import map.Map;
import unit.Unit;

public abstract class UnitAction {

	//	All actions
	public static final LinkedList<UnitAction> ALL_UNIT_ACTIONS = new LinkedList<UnitAction>();
	
	
	//	Properties
	public final String name;
	public final int ID;
	
	public UnitAction(String name, int id) {
		this.name = name;
		this.ID = id;
		
		ALL_UNIT_ACTIONS.add(this);
	}

	//	Action
	public abstract void performAction(Map map, Unit unit);
}
