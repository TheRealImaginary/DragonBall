package dragonball.model.exceptions;

public class MissingFieldException extends InvalidFormatException {
	
	private int missingFields;
	
	public MissingFieldException(String sourceFile, int sourceLine, int missingFields){
		super(sourceFile, sourceLine);
		this.missingFields = missingFields;
	}
	
	public MissingFieldException(String msg, String sourceFile, int sourceLine, int missingFields){
		super(msg, sourceFile, sourceLine);
		this.missingFields = missingFields;
	}
	
	public int getMissingFields (){
		return missingFields;
	}
	
}
