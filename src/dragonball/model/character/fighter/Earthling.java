package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Earthling extends PlayableFighter {
	
	public Earthling(){
	}
	/*public Earthling(){
		setBlastDamage(50);
		setPhysicalDamage(50);
		setMaxHealthPoints(1250);
		setMaxKi(4);
		setMaxStamina(4);
	}*/
	
	public Earthling (String name){
		super(name, 1250, 50, 50, 4, 4, new ArrayList<>(), new ArrayList<>());
	}
	
	public Earthling(String name, int level, int XP, int targetXP, int maxHP, int BlastDamage, int physicalDamage, int AbilityPoints, int maxKi
			,int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		super(name, level, XP, targetXP, maxHP, BlastDamage, physicalDamage, AbilityPoints, maxKi, maxStamina, superAttacks, ultimateAttacks);
		setKi(0);
	}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina() + 1);
		setKi(getKi() + 1);
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
		setKi(getKi() + 1);
	}
}
