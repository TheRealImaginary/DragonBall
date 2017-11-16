package dragonball.model.cell;

import java.io.IOException;

import dragonball.model.character.fighter.NonPlayableFighter;

public interface CellListener {

	public void onFoeEncountered(NonPlayableFighter foe) throws ClassNotFoundException, IOException;
	
	public void onCollectibleFound (Collectible collectible);
	
	
}
