package dragonball.model.attack;


import java.io.Serializable;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class Attack implements Serializable {

	private String name;
	private int damage;
	
	public Attack(){
	}
	
	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public String getName(){
		return name;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public abstract int getAppliedDamage (BattleOpponent attacker) throws NotEnoughKiException;
	
	//TODO : Handle Ki cases
	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException {
		Fighter def = (Fighter)defender;
		int damage = this.getAppliedDamage(attacker);
		if(attacker instanceof Saiyan){
			if( ((Saiyan)attacker).isTransformed() )
				damage  = damage + (damage * 25 / 100);
		}
		//System.err.println("TESTING DAMAGE " + damage);
		if(defenderBlocking){
			while(damage > 0){
				//System.err.println("I RAN " + damage);
				damage -= 100;
				def.setStamina(def.getStamina() - 1);
				if(def.getStamina() <= 0){
					def.setHealthPoints(def.getHealthPoints() - damage);
					//System.err.println("HEALTH IS " + def.getHealthPoints() + " " + def.getClass());
					break;
				}
			}
			//if(damage > 0 )
				//def.setHealthPoints(def.getHealthPoints() - damage);
		}
		else {
			def.setHealthPoints(def.getHealthPoints() - damage);
		}
	}
	
	public boolean equals (Object o){
		if (this == o)
			return true;
		if (o == null)
			return false;
		if(o.getClass() != this.getClass())
			return false;
		Attack attack = (Attack)o;
		
		return attack.name.equals(this.name) && attack.damage == this.damage;
	}
	
	public String toString(){
		return name;
	}

	/*public static void main(String[] args) {
		Attack attack = new SuperAttack("One finger death punch", 1000);
		//System.out.println(attack.getAppliedDamage(new Frieza("frieza")));
		attack.onUse(new Frieza("frieza"), new Majin(), false);
	}*/
}
