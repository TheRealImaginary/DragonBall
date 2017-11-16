package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperAttack extends Attack {

	public SuperAttack(){
		super();
	}
	
	public SuperAttack(String name, int damage){
		super(name, damage);
	}
	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) throws NotEnoughKiException {
		Fighter f = (Fighter)attacker;
		
		//if(f instanceof Saiyan){
			//if(((Saiyan)f).isTransformed())
				//return getDamage() + f.getBlastDamage();	
		//}
		//if(f.getKi() >= 1){
			//f.setKi(f.getKi() - 1);
		//}
		//System.err.println("I RAN IN SUPER ATTACK");
		//System.err.println("KI " + f + " " + f.getKi());
		//if(f.getKi() < 1)
			//throw new NotEnoughKiException(1, f.getKi());
		return getDamage() + f.getBlastDamage();
		//return 0;
	}
	
	public void onUse (BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		Fighter f = (Fighter)attacker;
		if(f instanceof Saiyan){
			if(!((Saiyan)f).isTransformed() && !(this instanceof MaximumCharge)){
				if(f.getKi() >= 1)
					f.setKi(f.getKi() - 1);
				else
					throw new NotEnoughKiException(1, f.getKi());
			}
		}
		else if(!(this instanceof MaximumCharge)){
			if(f.getKi() >= 1)
				f.setKi(f.getKi() - 1);
			else
				throw new NotEnoughKiException(1, f.getKi());
		}
		super.onUse(attacker, defender, defenderBlocking);
		/*Fighter def = (Fighter)defender;
		int damage = getAppliedDamage(attacker);
		if(defenderBlocking){
			while(damage >= 100){
				damage -= 100;
				def.setStamina(def.getStamina() - 1);
				if(def.getStamina() <= 0){
					def.setHealthPoints(def.getHealthPoints() - damage);
					break;
				}
			}
		}
		else {
			def.setHealthPoints(def.getHealthPoints() - damage);
		}*/
	}
}
