package dragonball.model.game;

import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.dragon.Dragon;

public interface GameListener {

	public void onDragonCalled (Dragon dragon);
	
	public void onCollectibleFound(Collectible collectible);
	
	public void onBattleEvent (BattleEvent e);
	
}
