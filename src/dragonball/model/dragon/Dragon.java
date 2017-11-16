package dragonball.model.dragon;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Dragon implements Serializable {

	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;
	
	public Dragon (){
	}
	
	public Dragon(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks,
			int senzuBeans, int abilityPoints) {
		super();
		this.name = name;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.abilityPoints = abilityPoints;
	}

	public String getName() {
		return name;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}
	
	public DragonWish [] getWishes (){
		//In case of test where some attributes are not there
		int n = 0;
		boolean a = false, s = false, sa = false, ua = false;
		if (senzuBeans > 0){
			n++;
			s = true;
		}
		if(abilityPoints > 0){
			n++;
			a = true;
		}
		if(!superAttacks.isEmpty()){
			n++;
			sa = true;
		}
		if(!ultimateAttacks.isEmpty()){
			n++;
			ua = true;
		}
		DragonWish [] result = new DragonWish [n];
		int i = 0;
		if(s)
			result[i++] = new DragonWish(this, DragonWishType.SENZU_BEANS, senzuBeans);
		if(a)
			result[i++] = new DragonWish(this, DragonWishType.ABILITY_POINTS, abilityPoints);
		if(sa)
			result[i++] = new DragonWish(this, DragonWishType.SUPER_ATTACK, superAttacks.get(new Random().nextInt(superAttacks.size())));
		if(ua)
			result[i] = new DragonWish(this, DragonWishType.ULTIMATE_ATTACK, ultimateAttacks.get(new Random().nextInt(ultimateAttacks.size())));
		
		return result;
	}
	
}
