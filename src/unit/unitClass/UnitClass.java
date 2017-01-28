package unit.unitClass;

import java.util.LinkedList;

import unit.unitClass.unitAction.UnitAction;

public class UnitClass {
	/**
	 * @author SunnyP
	 * Серый был тут
	 */
	//	All unit classes
	public static final LinkedList<UnitClass> ALL_UNIT_CLASSES = new LinkedList<UnitClass>();
	
	
	//	Properties
	public final String name;
	
	public final float force;
	
	public final byte movement;
	
	public final byte maxRank;
	
	//	Actions
	public final UnitAction[] actions;
	
	public UnitClass(String name, float force, byte movements, byte maxRank, UnitAction[] actions) {
		this.force = force;
		this.movement = movements;
		this.maxRank = maxRank;
		this.actions = actions;
		this.name = name;
		
		ALL_UNIT_CLASSES.add(this);
	}
}
