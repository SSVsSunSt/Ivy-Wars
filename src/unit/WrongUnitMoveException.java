package unit;

public class WrongUnitMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongUnitMoveException() {
		super("Unit moved incorrectly!");
	}
	
	public WrongUnitMoveException(Throwable arg0) {
		super(arg0);
		initCause(arg0);
	}

}
