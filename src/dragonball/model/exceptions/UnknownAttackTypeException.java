package dragonball.model.exceptions;

public class UnknownAttackTypeException extends InvalidFormatException {
	
	private String unknownType;
	
	public UnknownAttackTypeException(){
		super();
	}
	
	public UnknownAttackTypeException(String sourceFile, int line ,String type){
		super(sourceFile,line);
		unknownType = type;
	}
	
	public UnknownAttackTypeException(String msg, String sourceFile, int sourceLine, String type){
		super(msg, sourceFile,sourceLine);
		unknownType = type;
	}

	public String getUnknownType() {
		return unknownType;
	}
	
	
}
