package dragonball.model.cell;

import java.io.IOException;

import dragonball.model.character.fighter.NonPlayableFighter;

public class FoeCell extends Cell {
	
	private NonPlayableFighter foe;
	
	public FoeCell(){
	}
	
	public FoeCell(NonPlayableFighter foe){
		this.foe = foe;
	}
	
	public NonPlayableFighter getFoe (){
		return foe;
	}
	
	public String toString(){
		if (foe.isStrong())
			return "[b]";
		return "[w]";
	}

	@Override
	public void onStep() throws ClassNotFoundException, IOException {
		if(getListener() != null){
			getListener().onFoeEncountered(foe);
		}
	}
}
