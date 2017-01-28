package unit.unitClass;

import java.util.LinkedList;

import unit.unitClass.unitAction.UnitAction;

public class UnitClass {

	//	All unit classes
	public static final LinkedList<UnitClass> ALL_UNIT_CLASSES = new LinkedList<UnitClass>();
	
	
	//	Properties
	public final String name;
	
	public final int strength;
	public final int movement;
	
	public final int maxRank;
	
	//	Actions
	public final UnitAction[] actions;
	
	public UnitClass(String name, int strength, int movements, int maxRank, UnitAction[] actions) {
		this.strength = strength;
		this.movement = movements;
		this.maxRank = maxRank;
		this.actions = actions;
		this.name = name;
		
		ALL_UNIT_CLASSES.add(this);
	}

}
