package hu.akoel.sudoku;

import java.util.HashSet;
import java.util.Set;

public class Sudo {

	private Block[][] blocks = new Block[3][3];
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";

	public static void main(String args[]) {

		Sudo sudo = new Sudo();

		sudo.fillUp();
		
		sudo.solveIt();
		
		sudo.printout();

	}

	public Sudo() {
		initialization();
	}

	private void initialization() {
		int blocksRow = 0;
		int blocksColumn = 0;
		int blockRow = 0;
		int blockColumn = 0;

		Cell previous = null;

		// Row
		for (int j = 0; j < 9; j++) {
			blocksRow = j / 3;
			blockRow = j % 3;

			// Column
			for (int i = 0; i < 9; i++) {

				blocksColumn = i / 3;
				blockColumn = i % 3;

				Cell cell = new Cell(previous, 0, i, j);
				if (null != previous) {
					previous.setNext(cell);
				}

				previous = cell;

				if (null == blocks[blocksColumn][blocksRow]) {
					blocks[blocksColumn][blocksRow] = new Block();
				}
				blocks[blocksColumn][blocksRow].set(blockColumn, blockRow, cell);

			}
		}
	}

	private void fillUp(){
		blocks[0][0].getCell(0, 0).setFixValue(1);
		blocks[0][0].getCell(1, 1).setFixValue(2);
		blocks[0][0].getCell(2, 2).setFixValue(3);		
		
		blocks[1][1].getCell(0, 0).setFixValue(4);
		blocks[1][1].getCell(1, 1).setFixValue(5);
		blocks[1][1].getCell(2, 2).setFixValue(6);
				
		blocks[2][2].getCell(0, 0).setFixValue(7);
		blocks[2][2].getCell(1, 1).setFixValue(8);
		blocks[2][2].getCell(2, 2).setFixValue(9);
	}
	
	private void solveIt(){
	
		//Get the first cell
		Cell actualCell = blocks[0][0].getCell(0, 0);
		boolean goForward = true;

		int count = 0;
		
		//Go through the all cell
		while (actualCell != null) {
			
			//If the actual cell is NOT fix
			if( !actualCell.isFix() ){
				
				if( goForward ){

					actualCell.resetPossibilities();
					
					//Search possibilities for the cell
					for( int i = 1; i < 10; i++ ){
						if( isValid( actualCell, i) ){
							actualCell.addPossibility( i );
						}
					}

					//If there is no possibility in the actual cell
					if( !actualCell.hasPossibility() ){
						
						//Then we have to go back
						goForward = false;						
					
					//Get the first possibility	
					}else{
						actualCell.setValue( actualCell.getNextPossibility() );
					}
					
					
				//We go backward
				}else{
					
					//Get the next possibility
					Integer nextPossibility = actualCell.getNextPossibility();
					
					//If it is not null then set it and go forward
					if( null != nextPossibility ){
						actualCell.setValue(nextPossibility);
						goForward = true;
					}
				}

			}

			//Get the next/previous cell
			if( goForward ){
				actualCell = actualCell.getNext();
				
				System.out.println(count++);
				printout();

			}else{
				actualCell = actualCell.getPrevious();
			}
		}
			
	}
	
	private Set<Integer> goThroughRows(Set<Integer> rowCollector, Block block, Integer column, Integer exception){
		for( int i = 0; i < 3; i++ ){
			if( null == exception || i != exception ){
				rowCollector.add( block.getCell(column, i).getValue() );
			}
		}
		return rowCollector;
	}
	
	private Set<Integer> goThroughColumns(Set<Integer> columnCollector, Block block, Integer row, Integer exception){
		for( int i = 0; i < 3; i++ ){
			if( null == exception || i != exception ){
				columnCollector.add( block.getCell(i, row).getValue() );
			}
		}
		return columnCollector;
	}
	
	private boolean isValid( Cell cell, int value ){
		Set<Integer> mixedCollector = new HashSet<Integer>();
		int column = cell.getColumn();
		int row = cell.getRow();
		
		int blocksColumn = column / 3;
		int blockColumn = column % 3;
		
		int blocksRow = row / 3;
		int blockRow = row % 3;
		
		//If the number is not valid in the block
		Block block = blocks[blocksColumn][blocksRow];
		if ( !block.isValidInBlock(value) ){
			return false;
		}		
		//Actual block
		goThroughColumns(mixedCollector, block, blockRow, blockColumn);
		goThroughRows(mixedCollector, block, blockColumn, blockRow);
		
		//Horizontaly through columns
		for( int i = 0; i < 3; i++ ){
			if( i != blocksColumn ){
				block = blocks[i][blocksRow];
				goThroughColumns(mixedCollector, block, blockRow, null);
			}
		}
		
		//Vertically through rows
		for( int i = 0; i < 3; i++ ){
			if( i != blocksRow ){
				block = blocks[blocksColumn][i];
				goThroughRows(mixedCollector, block, blockColumn, null);
			}
		}
		
		if( mixedCollector.contains(value) )
			return false;
		
		return true;
	}
	
	private void printout() {

		Cell actualCell = blocks[0][0].getCell(0, 0);
		int oldRow = actualCell.getRow();
		while (actualCell != null) {

			int column = actualCell.getColumn();
			int row = actualCell.getRow();

			if (oldRow != row) {
				System.out.println("|");

				if (row % 3 == 0) {
					System.out.println("");
				}
				oldRow = row;
			}

			if (column % 3 == 0) {
				System.out.print("|");
			}

			System.out.format("%2d", actualCell.getValue());
			System.out.print(" ");
			actualCell = actualCell.getNext();

		}
		System.out.print("|");
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
