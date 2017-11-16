package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperSaiyan extends UltimateAttack {

	public SuperSaiyan(){
		super("Super Saiyan", 0);
	}
	
	public SuperSaiyan(String name, int damage){
		super(name, damage);
	}
	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		//Fighter pf = (Fighter)attacker;
		
		//if(pf.getKi() >= 3){
			//pf.setKi(pf.getKi() + 1);
			//if(pf instanceof Saiyan)
				//((Saiyan)pf).setTransformed(true);
		//}
		
		return 0;
	}
	
	public void onUse (BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		Fighter att = (Fighter)attacker;
//		Fighter def = (Fighter)defender;
//		boolean saiyan = false;
		if(att instanceof Saiyan){
			if(att.getKi() >= 3){
				((Saiyan)att).setTransformed(true);
				//saiyan = true;
			}
			else
				throw new NotEnoughKiException(3, att.getKi());
		}
		super.onUse(attacker, defender, defenderBlocking);
		//boolean saiyan = false;
		//if(att instanceof Saiyan){
			//if(((Saiyan)att).isTransformed())
				//saiyan = true;
		//}
		/*int damage = getAppliedDamage(attacker);
		if(saiyan)
			damage += (25*damage/100);
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
