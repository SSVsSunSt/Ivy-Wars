package unit.unitClass.unitAction;

import java.util.LinkedList;

public abstract class UnitAction {

	//	All actions
	public static final LinkedList<UnitAction> ALL_UNIT_ACTIONS = new LinkedList<UnitAction>();

	
	//	Properties
	public final String name;
	
	public UnitAction(String name) {
		this.name = name;
	
		ALL_UNIT_ACTIONS.add(this);
	}

	//	Action
	public abstract void performAction();
}
