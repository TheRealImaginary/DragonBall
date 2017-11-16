package dragonball.model.cell;

public enum Collectible {
	
	SENZU_BEAN ,DRAGON_BALL;
	
	public String toString(){
		if(this == Collectible.SENZU_BEAN)
			return "Senzu Bean";
		if(this == Collectible.DRAGON_BALL)
			return "Dragon Ball";
		return "";
	}
}
