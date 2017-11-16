package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.NonPlayableCharacter;

public class NonPlayableFighter extends Fighter implements NonPlayableCharacter {
	
	private boolean strong;
	
	public NonPlayableFighter (){
		
	}
	
	public NonPlayableFighter(String name, int level,int maxHealthPoints, int blastDamage,
			int physicalDamage, int maxKi, int maxStamina, boolean strong, ArrayList<SuperAttack>
			superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		
		super(name, level, maxHealthPoints, blastDamage, physicalDamage, maxKi, maxStamina, superAttacks, ultimateAttacks);
		this.strong = strong;
	}
	
	public boolean isStrong (){
		return strong;
	}
	
	//public String toString(){
		//return getSuperAttacks().toString() + " " + getUltimateAttacks().toString();
	//}
	
	public static void main(String[] args) {
	

	}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina() + 1);
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
	}

}
