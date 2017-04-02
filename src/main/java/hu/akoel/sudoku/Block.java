package hu.akoel.sudoku;

public class Block {

	private Cell[][] arrayBlock = new Cell[3][3];

	public void set( int blockColumn, int blockRow, Cell cell){
		arrayBlock[blockColumn][blockRow] = cell;
	}
	
	public boolean isValidInBlock(int newValue){
		boolean valid = true;
		for( int i = 0; i < 3; i++ ){
			
			for( int j = 0; j < 3; j++ ){
				
				if( arrayBlock[i][j].getValue() == newValue ){
					valid = false;
					break;
				}
			}
		}
		return valid;
	}
	
	public Cell getCell(int column, int row){
		return arrayBlock[column][row];
	}
	
	public Triplet getCellsInRow( int row ){
		Triplet triplet = new Triplet();
		triplet.set(0, arrayBlock[0][row]);
		triplet.set(1, arrayBlock[1][row]);
		triplet.set(2, arrayBlock[2][row]);
		return triplet;		
	}
	
	public Triplet getCellInColumn( int column ){
		Triplet triplet = new Triplet();
		triplet.set(0, arrayBlock[column][0]);
		triplet.set(1, arrayBlock[column][1]);
		triplet.set(2, arrayBlock[column][2]);
		return triplet;		
	}
}
