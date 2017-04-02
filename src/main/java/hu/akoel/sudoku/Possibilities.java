package hu.akoel.sudoku;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Possibilities {

	private List<Integer> list;
	private Iterator<Integer> iterator;
	
	Possibilities(){
		this.list = new ArrayList<Integer>();
		this.iterator = this.list.iterator();
	}
	
	public Integer getNext(){
		if( iterator.hasNext() )
			return iterator.next();
		else
			return null;		
	}
	
	public void add( Integer value ){
		this.list.add( value );
		this.iterator = this.list.iterator();
	}
	
	public boolean hasNext(){
		return iterator.hasNext();
	}
}
