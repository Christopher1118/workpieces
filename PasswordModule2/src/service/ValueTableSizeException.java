package service;

public class ValueTableSizeException extends Exception {

	/**
	 * 
	 */
	private String message = "value table size not match";
	
	
	private static final long serialVersionUID = -6746674976376006035L;


	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage() + "\n"+message;
	}
	
	
}
