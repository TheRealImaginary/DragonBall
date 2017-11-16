package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.PlayableCharacter;

public abstract class PlayableFighter extends Fighter implements PlayableCharacter {
	
	private int xp;
	private int targetXp;
	private int abilityPoints;

	public PlayableFighter() {
	}
	
	public PlayableFighter(String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, maxHealthPoints, blastDamage, physicalDamage, maxKi, maxStamina, superAttacks, ultimateAttacks);
		this.xp = xp;
		this.targetXp = targetXp;
		this.abilityPoints = abilityPoints;
		setHealthPoints(maxHealthPoints);
		setStamina(maxStamina);
		setKi(0);
		
	}
	
	public PlayableFighter(String name, int maxHealthPoints, int blastDamage, int physicalDamage,
			int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
			ultimateAttacks){
		this(name, 1, 0, 10, maxHealthPoints, blastDamage, physicalDamage, 0, maxKi, maxStamina, superAttacks, ultimateAttacks);
		//setHealthPoints(maxHealthPoints);
		//setStamina(maxStamina);
		//setKi(0);
	}
	
	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		//System.err.println("XP IS " + xp);
		if(xp >= this.xp)
			this.xp = xp;
		else
			this.xp += xp;
		while(getTargetXp() <= this.xp){
			this.xp -= getTargetXp();
			setTargetXp(getTargetXp() + 20);
			setLevel(getLevel() + 1);
			setAbilityPoints(getAbilityPoints() + 2);
		}
		//if(old_level < getLevel())
			//this.xp = 0;
	}

	public int getTargetXp() {
		return targetXp;
	}

	public void setTargetXp(int targetXp) {
		this.targetXp = targetXp;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

	public static void main(String[] args) {
		/*PlayableFighter fighter = new Earthling("Goku", 1, 1, 100, 1, 1, 1, 1,
				5, 1, null, null);
		System.out.println(fighter.getXp());
		fighter.setXp(200);
		System.out.println(fighter.getXp());
		System.out.println(fighter.getLevel());
		
		fighter = new Earthling("Goku", 1, 1, 100, 1, 1, 1, 1, 5, 1, null, null);
		fighter.setXp(360);
		System.out.println(fighter.getXp());
		System.out.println(fighter.getLevel());*/
		
		Namekian n = new Namekian("namekian", 2, 100, 500, 300, 400, 500, 5, 6, 600, null, null);
		
		System.out.println(n.getXp());
		System.out.println(n.getLevel());
		
		n.setXp(150);
		
		System.out.println(n.getXp());
		System.out.println(n.getLevel());
	}

}
