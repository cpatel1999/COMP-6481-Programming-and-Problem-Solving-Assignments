public class FileInvalidException extends Exception{

	private String error ;
	
	
	// constructor with passed param.
	public FileInvalidException(String s) {
		
		this.error = s ;
		
	}
	
	// default constructor
	public FileInvalidException() {
		error = "Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)";
	}

	public String getError() {
		return error;
	}
	
	

	
	
	
	
	
	
}