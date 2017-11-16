package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Frieza extends PlayableFighter {

	public Frieza (){
	}
	
	/*public Frieza(){
		setBlastDamage(75);
		setPhysicalDamage(75);
		setMaxHealthPoints(1100);
		setMaxKi(4);
		setMaxStamina(4);
	}*/
	
	public Frieza (String name){
		super(name, 1100, 75, 75, 4, 4, new ArrayList<>(), new ArrayList<>());
	}
	
	public Frieza(String name, int level, int XP, int targetXP, int maxHP, int BlastDamage, int physicalDamage, int AbilityPoints, int maxKi
			,int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		super(name, level, XP, targetXP, maxHP, BlastDamage, physicalDamage, AbilityPoints, maxKi, maxStamina, superAttacks, ultimateAttacks);
		setKi(0);
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
