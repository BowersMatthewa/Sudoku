package com.github.gelderlin.sudoku;

import java.util.ArrayList;

public class Row {
	private Cell[] cells;
	private int cellCount = 0;
	private Column[] cols;
	private Group[] groups;
	private ArrayList<Integer> available = new ArrayList<Integer>();
	
	public Row(int dim){
		cells = new Cell[dim];
		cols = new Column[(int)Math.sqrt(dim)];
		groups = new Group[(int)Math.sqrt(dim)];
		for(int i = 1; i <= dim; i++){
			available.add((Integer)i);
		}
	}
	
	private boolean checkValue(int i){
		return available.contains((Integer) i); 
	}
	
	public void addCell(Cell cell){
		cells[cellCount++] = cell;
	}
	
	public void addColumn(Column col, int index){
		cols[index] = col;
	}
	
	public void addGroup(Group group, int index){
		groups[index] = group; 
	}
	
	public boolean useValue(int i){
		if(checkValue(i)){
			available.remove((Integer)i);
			return true;
		}
		else return false;
	}
	
	public boolean isComplete(){
		return available.isEmpty();
	}
	
	public boolean returnValue(int i){
		return available.add((Integer)i);
	}
	
	public Cell[] getCells(){
		return cells;
	}
}
