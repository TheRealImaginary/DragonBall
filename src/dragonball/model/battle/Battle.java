package dragonball.model.battle;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle implements Serializable {
	
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;
	
	public Battle (BattleOpponent me, BattleOpponent foe){
		this.me = me;
		this.foe = foe;
		attacker = me;
		Fighter fm = (Fighter)me;
		Fighter ff = (Fighter)foe;
		
		//fm.setHealthPoints(fm.getMaxHealthPoints() / 2);  //For testing
		fm.setHealthPoints(fm.getMaxHealthPoints());
		fm.setStamina(fm.getMaxStamina());
		fm.setKi(0);
		
		//ff.setHealthPoints(ff.getHealthPoints() / 2);
		ff.setHealthPoints(ff.getMaxHealthPoints());
		ff.setStamina(ff.getMaxStamina());
		ff.setKi(0);
		
		if(fm instanceof Saiyan){
			Saiyan s = (Saiyan)fm;
			if(s.isTransformed())
				s.setTransformed(false);
		}
		
		if(ff instanceof Saiyan){
			Saiyan s = (Saiyan)ff;
			if(s.isTransformed())
				s.setTransformed(false);
		}
	}
	
	public void attack (Attack attack) throws NotEnoughKiException, ClassNotFoundException, IOException {
		BattleOpponent defender = null;
		boolean defenderBlocking = false;
		if(attacker == me){
			defender = foe;
			defenderBlocking = isFoeBlocking();
		}
		else {
			defender = me;
			defenderBlocking = isMeBlocking();
		}
		System.err.println("BATTLE " + attacker + " " + defender + " " + defenderBlocking);
		System.err.println(attack);
		attack.onUse(attacker, defender, defenderBlocking);
		//System.err.println(attacker + " " + ((Fighter)attacker).getKi());
		if(listener != null)
			listener.onBattleEvent(new BattleEvent(this, BattleEventType.ATTACK, attack));
		endTurn();
	}
	
	public void block () throws ClassNotFoundException, IOException{
		//if(listener != null)
			//listener.onBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));
		if(attacker == me)
			meBlocking = true;
		else if(attacker == foe)
			foeBlocking = true;
		
		if(listener != null)
			listener.onBattleEvent(new BattleEvent(this,BattleEventType.BLOCK));
		
		endTurn();
	}
	
	public void use (Player player, Collectible collectible) throws ClassNotFoundException, IOException, NotEnoughSenzuBeansException{
		if(collectible == Collectible.SENZU_BEAN){
			if(player.getSenzuBeans() > 0){
				player.setSenzuBeans(player.getSenzuBeans() - 1);
				player.getActiveFighter().setHealthPoints(player.getActiveFighter().getMaxHealthPoints());
				player.getActiveFighter().setStamina(player.getActiveFighter().getMaxStamina());
				
				if(listener != null)
					listener.onBattleEvent(new BattleEvent(this, BattleEventType.USE, collectible));
				
				endTurn();
			}
			else
				throw new NotEnoughSenzuBeansException("You don't have enough senzu beans required 1");
		}
	}
	
	public BattleOpponent getDefender (){
		return attacker == foe ? me:foe;
	}
	
	public void play () throws NotEnoughKiException, ClassNotFoundException, IOException{
		Random r = new Random ();
		int x = r.nextInt(100);
		NonPlayableFighter npf = (NonPlayableFighter)foe;
		if(x % 2 == 0){
			block();
			//if(listener != null)
				//listener.onBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));
		}
		else {
			ArrayList<Attack> attacks = getAssignedAttacks();
			Random rr = new Random();
			int xx = rr.nextInt(attacks.size());
			//System.err.println(npf.getSuperAttacks());
			//if(xx < 3)
			//if( ((NonPlayableFighter)(foe)).getKi() == 0 )
				//attack(new PhysicalAttack());
			//else if( ((NonPlayableFighter)(foe)).getKi() <= 2)
			//else if(xx < 5)
				//attack(npf.getSuperAttacks().get(new Random().nextInt(npf.getSuperAttacks().size())));
			//else
			//else if( ((NonPlayableFighter)(foe)).getKi() >= 3)
				//attack(npf.getUltimateAttacks().get(new Random().nextInt(npf.getUltimateAttacks().size())));
			try {
				attack(attacks.get(xx));
			}
			catch (NotEnoughKiException ex){
				attack(attacks.get(0));
			}
		//	if(listener != null)
			//	listener.onBattleEvent(new BattleEvent(this, BattleEventType.ATTACK));
		}
			
	}
	
	public void start () throws ClassNotFoundException, IOException{
		attacker = me;
		if(listener != null)
			listener.onBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
	}
	
	public void endTurn () throws ClassNotFoundException, IOException{
		switchTurn();
	}
	
	public void switchTurn () throws ClassNotFoundException, IOException{
		System.err.println(((NonPlayableFighter)foe).getHealthPoints() + " " + ((Fighter)(me)).getHealthPoints() + " " + attacker);
		System.err.println("LISTENER " + listener);
		if(attacker == me){
			//System.err.println("FOE " + ((NonPlayableFighter)foe).getName());
			if( ((NonPlayableFighter)foe).getHealthPoints() <= 0 ){
				if(listener != null){
					listener.onBattleEvent(new BattleEvent(this, BattleEventType.ENDED, attacker));
				}
			}
			else {
				attacker = foe;
				foeBlocking = false;
				foe.onAttackerTurn();
				me.onDefenderTurn();
				if(listener != null){
					listener.onBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
				}
			}
		}
		else {
			if( ((PlayableFighter)me).getHealthPoints() <= 0 ){
				if(listener != null){
					listener.onBattleEvent(new BattleEvent(this, BattleEventType.ENDED, attacker));
				}
				
			}
			else {
				attacker = me;
				meBlocking = false;
				me.onAttackerTurn();
				foe.onDefenderTurn();
				if(listener != null){
					listener.onBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
				}
			}
		}
	}
	

	public BattleOpponent getMe(){
		return me;
	}
	
	public BattleOpponent getFoe(){
		return foe;
	}

	public BattleOpponent getAttacker(){
		return attacker;
	}
	
	public boolean isMeBlocking(){
		return meBlocking;
	}
	
	public boolean isFoeBlocking (){
		return foeBlocking;
	}
	
	public void setListener (BattleListener listener){
		this.listener = listener;
	}
	
	public ArrayList<Attack> getAssignedAttacks (){
		Fighter f = (Fighter)attacker;
		
		ArrayList<Attack> result = new ArrayList<>();
		result.add(new PhysicalAttack());
		result.addAll(f.getSuperAttacks());
		result.addAll(f.getUltimateAttacks());
		
		return result;
	}
	
}
