package dragonball.model.exceptions;

public class NotEnoughKiException extends NotEnoughResourcesException {
	
	private int availableKi;
	private int requiredKi;
	
	public NotEnoughKiException(){
		super();
	}
	
	public NotEnoughKiException(String msg){
		super(msg);
	}
	
	public NotEnoughKiException(int requiredKi, int availableKi){
		super(availableKi + " " + requiredKi);
		this.availableKi = availableKi;
		this.requiredKi = requiredKi;
	}
	
	public int getAvailableKi (){
		return availableKi;
	}

	public int getRequiredKi() {
		return requiredKi;
	}
	
	
}
