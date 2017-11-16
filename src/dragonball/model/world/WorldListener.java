package dragonball.model.world;

import java.io.IOException;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;

public interface WorldListener {

	public void onFoeEncountered(NonPlayableFighter foe) throws ClassNotFoundException, IOException;
	
	public void onCollectibleFound (Collectible collectible);
	
	
}
