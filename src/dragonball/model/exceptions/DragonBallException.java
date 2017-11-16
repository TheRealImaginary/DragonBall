package dragonball.model.exceptions;

public abstract class DragonBallException extends Exception{
	
	public DragonBallException (){
		super ();
	}
	
	public DragonBallException(String msg){
		super(msg);
	}
	
}
