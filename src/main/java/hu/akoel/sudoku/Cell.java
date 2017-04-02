package hu.akoel.sudoku;

import java.util.List;

public class Cell {

	private int value;
	private boolean fix;
	private int column;
	private int row;
	private Cell previous;
	private Cell next;
	private Possibilities possibilities;
	
	public Cell( Cell previous, int value, int column, int row){
		super();
		this.previous = previous;
		this.value = value;
		this.fix = false;
		this.column = column;
		this.row = row;
		this.possibilities = new Possibilities();	
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void setFixValue(int value){
		this.value = value;
		this.fix = true;
	}

	public boolean isFix() {
		return fix;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public Cell getPrevious() {
		return previous;
	}

	public Cell getNext() {
		return next;
	}

	public void setNext( Cell next ){
		this.next = next;
	}

	public Integer getNextPossibility(){
		return possibilities.getNext();
	}
	
	public void resetPossibilities(){
		this.value = 0;
		this.possibilities = new Possibilities();
	}
	
	public void addPossibility(Integer value){
		this.possibilities.add(value);
	}
	
	public boolean hasPossibility(){
		return this.possibilities.hasNext();
	}
	
}
