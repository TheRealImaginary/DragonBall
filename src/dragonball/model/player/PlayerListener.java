package dragonball.model.player;

import dragonball.model.dragon.DragonWish;

public interface PlayerListener {

	public void onDragonCalled ();
	
	public void onWishChosen(DragonWish wish);
	
	
}
