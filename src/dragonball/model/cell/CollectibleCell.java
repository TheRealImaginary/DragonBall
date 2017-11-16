package dragonball.model.cell;

public class CollectibleCell extends Cell {
	
	private Collectible collectible;
	
	public CollectibleCell(){	
	}
	
	public CollectibleCell(Collectible collectible){
		this.collectible = collectible;
	}
	
	public Collectible getCollectible (){
		return collectible;
	}
	
	@Override
	public void onStep() {
		if(getListener() != null)
			getListener().onCollectibleFound(collectible);
	}
	
	@Override
	public String toString(){
		if (collectible == Collectible.DRAGON_BALL)
			return "[d]";
		return "[s]";
	}

	
}
