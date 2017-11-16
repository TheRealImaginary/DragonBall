package dragonball.model.exceptions;

public class NotEnoughAbilityPointsException extends NotEnoughResourcesException {

	public NotEnoughAbilityPointsException(){
		super();
	}
	
	public NotEnoughAbilityPointsException(String msg){
		super(msg);
	}
}
