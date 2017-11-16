package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class PhysicalAttack extends Attack {
	
	public PhysicalAttack(){
		super("Physical Attack" , 50);
	}
	
	public PhysicalAttack (String name, int damage){
		super(name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		Fighter f = (Fighter)attacker;
		
		//if(f.getKi() + 1 < f.getMaxKi())
		//f.setKi(f.getKi() + 1);
		
		return 50 + f.getPhysicalDamage();
	}
	
	@Override
	public void onUse (BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		Fighter f = (Fighter)attacker;
		f.setKi(f.getKi() + 1);
		super.onUse(attacker, defender, defenderBlocking);
		/*int damage = getAppliedDamage(attacker);
		Fighter def = (Fighter)defender;
		
		if(defenderBlocking){
			while(damage >= 100){
				damage -= 100;
				def.setStamina(def.getStamina() - 1);
				if(def.getStamina() <= 0){
					def.setHealthPoints(def.getBlastDamage() - damage);
					break;
				}
			}
		}
		else {
			def.setHealthPoints(def.getHealthPoints() - damage);
		}*/
	}
	
}
