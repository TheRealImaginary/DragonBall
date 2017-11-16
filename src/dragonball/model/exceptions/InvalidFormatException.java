package dragonball.model.exceptions;

import java.io.IOException;

public abstract class InvalidFormatException extends IOException {
	
	private String sourceFile;
	private int sourceLine;
	
	public InvalidFormatException(){
		super();
	}
	
	public InvalidFormatException(String file, int line){
		super();
		sourceFile = file;
		sourceLine = line;
	}
	
	public InvalidFormatException(String msg, String file, int line){
		super(msg);
		this.sourceFile = file;
		this.sourceLine = line;
	}
	
	public String getSourceFile (){
		return sourceFile;
	}
	
	public int getSourceLine (){
		return sourceLine;
	}
	
	public static void main(String[] args) throws IOException {
		//throw new InvalidFormatException();
	}
}
