package dragonball.model.cell;

public class EmptyCell extends Cell {
	
	public EmptyCell(){
	}

	@Override
	public void onStep() {
	}
	
	@Override
	public String toString(){
		return "[ ]";
	}

}
