package hu.akoel.sudoku;

public class Triplet {

	private Cell[] triplet = new Cell[3];
	
	public void set(int position, Cell value){
		triplet[position] = value;
	}
	
	public Cell[] get(){
		return triplet;
	}
}
