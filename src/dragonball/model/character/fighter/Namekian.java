package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Namekian extends PlayableFighter {

	public Namekian(){
	}
	
	/*public Namekian(){
		setBlastDamage(0);
		setPhysicalDamage(50);
		setMaxHealthPoints(1350);
		setMaxKi(3);
		setMaxStamina(5);
	}*/
	
	public Namekian (String name){
		super(name, 1350, 0, 50, 3, 5, new ArrayList<>(), new ArrayList<>());
	}
	
	public Namekian(String name, int level, int XP, int targetXP, int maxHP, int BlastDamage, int physicalDamage, int AbilityPoints, int maxKi
			,int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		super(name, level, XP, targetXP, maxHP, BlastDamage, physicalDamage, AbilityPoints, maxKi, maxStamina, superAttacks, ultimateAttacks);
		setKi(0);
	}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina() + 1);
		setHealthPoints(getHealthPoints() + 50);
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
		setHealthPoints(getHealthPoints() + 50);
	}
	
	/*public static void main(String[] args) {
		Namekian n = new Namekian("x");
		System.out.println(n.getTargetXp());
		System.out.println(n.getXp());
	}*/
}
