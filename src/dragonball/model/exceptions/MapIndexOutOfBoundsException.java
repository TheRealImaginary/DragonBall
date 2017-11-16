package dragonball.model.exceptions;

public class MapIndexOutOfBoundsException extends IndexOutOfBoundsException {

	private int row;
	private int column;
	
	public MapIndexOutOfBoundsException(){
		super();
	}
	
	public MapIndexOutOfBoundsException(String msg){
		super(msg);
	}
	
	public MapIndexOutOfBoundsException (int row, int column){
		this(row + " " + column);
		this.row = row;
		this.column = column;
	}
	
	public int getRow (){
		return row;
	}
	
	public int getColumn (){
		return column;
	}
	
	
}
