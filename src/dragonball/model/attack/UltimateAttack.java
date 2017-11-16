package dragonball.model.attack;

import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class UltimateAttack extends Attack {

	public UltimateAttack(){
	}
	
	public UltimateAttack (String name, int damage){
		super(name, damage);
	}
	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) throws NotEnoughKiException {
		Fighter f = (Fighter)attacker;
		
		//if(f instanceof Saiyan){
			//if(((Saiyan)f).isTransformed())
				//return getDamage() + f.getBlastDamage();	
		//}
		//if(f.getKi() >= 3){
			//f.setKi(f.getKi() - 3);
		//}
		//System.err.println("I RAN IN SUPER ATTACK");
		//if(f.getKi() < 3)
			//throw new NotEnoughKiException(3, f.getKi());
		return getDamage() + f.getBlastDamage();
		//return 0;
	}
	
	public void onUse (BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		Fighter f = (Fighter)attacker;
		if(f instanceof Saiyan){
			if(!((Saiyan)f).isTransformed()){
				if(f.getKi() >= 3)
					f.setKi(f.getKi() - 3);
				else
					throw new NotEnoughKiException(3, f.getKi());
			}
		}
		else {
			if(f.getKi() >= 3)
				f.setKi(f.getKi() - 3);
			else
				throw new NotEnoughKiException(3, f.getKi());
		}
		
		super.onUse(attacker, defender, defenderBlocking);
		/*Fighter def = (Fighter)defender;
	
		int damage = getAppliedDamage(attacker);
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
		//if(att.getKi() >= 3)
	}
	
	/*public static void main(String[] args) {
		Namekian n = new Namekian("Test");
		System.out.println("KI " + n.getKi());
		NonPlayableFighter npf = new NonPlayableFighter("SHIT", 1, 100, 3, 3, 3, 3, false, null, null);
		
		Battle b = new Battle(n, npf);
		System.out.println("Health " + npf.getHealthPoints());
		b.start();
		
		b.attack(new UltimateAttack("xx", 1000));
		System.out.println( ((NonPlayableFighter)(b.getFoe())).getHealthPoints() );
		System.out.println(npf.getHealthPoints());
		System.out.println(n.getKi());
	}*/
}
