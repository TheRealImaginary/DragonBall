package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Majin extends PlayableFighter {

	public Majin(){
	}
	
	/*public Majin (){
		setBlastDamage(50);
		setPhysicalDamage(50);
		setMaxHealthPoints(1500);
		setMaxKi(3);
		setMaxStamina(6);
	}*/
	
	public Majin (String name){
		super(name, 1500, 50, 50, 3, 6, new ArrayList<>(), new ArrayList<>());
	}
	
	public Majin(String name, int level, int XP, int targetXP, int maxHP, int BlastDamage, int physicalDamage, int AbilityPoints, int maxKi
			,int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		super(name, level, XP, targetXP, maxHP, BlastDamage, physicalDamage, AbilityPoints, maxKi, maxStamina, superAttacks, ultimateAttacks);
		setKi(0);
	}

	@Override
	public void onAttackerTurn() {
		
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
	}
}
