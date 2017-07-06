package com.github.gelderlin.sudoku;

import java.util.ArrayList;

public class Cell implements Comparable<Cell>{
	private Row row;
	private Column col;
	private Group group;
	private int value;
	private ArrayList<Integer> possible = new ArrayList<Integer>();
	//private int dim;
	
	public Column getColumn(){
		return col;
	}
	
	public Row getRow(){
		return row;
	}
	
	public Group getGroup(){
		return group;
	}
	
	public Cell(Row row, Column col, Group group, int dim){
		this.row = row;
		this.col = col;
		this.group = group;
		//this.dim = dim;
		value = 0;
		for(int i = 1; i <= dim; i++){
			possible.add((Integer)i);
		}
	}
	
	public int getValue(){
		return value;
	}
	
	public ArrayList<Integer> getPossible(){
		return possible;
	}
	
	private void removePossible(int i){
		possible.remove((Integer)i);
	}
	
	public boolean changeValue(int i){
		if(possible.contains((Integer)i)){
			value = i;
			for(Cell c : row.getCells()){
				c.removePossible(i);
			}
			for(Cell c : col.getCells()){
				c.removePossible(i);
			}for(Cell c : group.getCells()){
				c.removePossible(i);
			}
			row.useValue(i);
			col.useValue(i);
			group.useValue(i);
			possible.clear();
			return true;
		}
		else return false;
	}

	@Override
	public int compareTo(Cell cell1) {
		int num0;
		int num1;
		if(possible.size() == 0){
			num0 = 10;
		}else num0 = possible.size();
		if(cell1.getPossible().size() == 0){
			num1 = 10;
		}else num1 = cell1.getPossible().size();
		return num0 - num1;
	}
	
	public String toString(){
		if(value != 0){
			return Integer.toString(value);
		}else return " ";
	}
}
