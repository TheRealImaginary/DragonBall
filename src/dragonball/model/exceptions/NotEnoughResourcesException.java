package dragonball.model.exceptions;

public abstract class NotEnoughResourcesException extends DragonBallException {

	public NotEnoughResourcesException (){
		super();
	}
	
	public NotEnoughResourcesException(String msg){
		super(msg);
	}
	
}
