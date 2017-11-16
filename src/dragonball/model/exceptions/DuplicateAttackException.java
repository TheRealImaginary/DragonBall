package dragonball.model.exceptions;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class DuplicateAttackException extends InvalidAssignAttackException {

	private Attack attack;
	
	public DuplicateAttackException(){
		super();
	}
	
	public DuplicateAttackException(Attack attack){
		super("Attack " + attack.getName() + " has already been learned!");
		this.attack = attack;
	}
		
	public Attack getAttack (){
		return attack;
	}
	
	
}
