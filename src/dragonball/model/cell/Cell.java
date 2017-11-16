package dragonball.model.cell;

import java.io.IOException;
import java.io.Serializable;

public abstract class Cell implements Serializable {
	
	private CellListener listener;
	
	public void setListener (CellListener listener){
		this.listener = listener;
	}
	
	public CellListener getListener (){
		return listener;
	}
	
	public abstract void onStep () throws ClassNotFoundException, IOException;
	
	public abstract String toString ();
	
	public static void main(String[] args) {
		//Cell cell = new EmptyCell();
		//System.out.println(cell);
	}
}
