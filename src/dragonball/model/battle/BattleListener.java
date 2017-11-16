package dragonball.model.battle;

import java.io.IOException;

public interface BattleListener {

	public void onBattleEvent (BattleEvent e) throws ClassNotFoundException, IOException ;
	
	
}
