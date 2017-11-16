package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Saiyan extends PlayableFighter {
	
	private boolean transformed;
	
	public Saiyan (){
	}
	
	/*public Saiyan (){
		setBlastDamage(150);
		setPhysicalDamage(100);
		setMaxHealthPoints(1000);
		setMaxKi(5);
		setMaxStamina(3);	
	}*/
	
	public Saiyan (String name){
		super(name, 1000, 150, 100, 5, 3, new ArrayList<>(), new ArrayList<>());
	}
	
	public Saiyan(String name, int level, int XP, int targetXP, int maxHP, int BlastDamage, int physicalDamage, int AbilityPoints, int maxKi
			,int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		super(name, level, XP, targetXP, maxHP, BlastDamage, physicalDamage, AbilityPoints, maxKi, maxStamina, superAttacks, ultimateAttacks);
		setKi(0);
	}
	
	public boolean isTransformed() {
		return transformed;
	}

	public void setTransformed(boolean transform){
		transformed = transform;
	}

	@Override
	public void onAttackerTurn() {
		setStamina(getStamina() + 1);
		if(transformed) {
			setKi(getKi() - 1);
			if(getKi() <= 0){
				transformed = false;
				setStamina(0);
				setKi(0);
			}
		}
	}

	@Override
	public void onDefenderTurn() {
		setStamina(getStamina() + 1);
		if(transformed) {
			setKi(getKi() - 1);
			if(getKi() == 0){
				transformed = false;
				setStamina(0);
			}
		}
	}
	
}
