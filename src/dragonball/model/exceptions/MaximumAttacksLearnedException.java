package dragonball.model.exceptions;

public class MaximumAttacksLearnedException extends InvalidAssignAttackException {

	public MaximumAttacksLearnedException(){
		super();
	}
	
	public MaximumAttacksLearnedException(String msg){
		super(msg);
	}
	
}
