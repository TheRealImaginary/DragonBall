package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.exceptions.NotEnoughKiException;

public class MaximumCharge extends SuperAttack {
	
	public MaximumCharge (){
		super("Maximum Charge", 0);
	}
	
	public MaximumCharge (String name, int damage){
		super(name, damage);
	}
	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		Fighter pf = (Fighter)attacker;
		
		//if(pf.getKi() < 3)
		//pf.setKi(pf.getKi() + 3);
		
		return 0;
	}
	
	public void onUse (BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		Fighter f = (Fighter)attacker;
		f.setKi(f.getKi() + 3);
		super.onUse(attacker, defender, defenderBlocking);
	}
	
	//public static void main(String[] args) {
		//MaximumCharge m = new MaximumCharge();
	//}
}
