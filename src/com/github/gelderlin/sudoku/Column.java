package com.github.gelderlin.sudoku;

import java.util.ArrayList;

public class Column {
	private Cell[] cells;
	private int cellCount = 0;
	private Row[] rows;
	private Group[] groups;
	private ArrayList<Integer> available = new ArrayList<Integer>();
	
	public Column(int dim){
		cells = new Cell[dim];
		rows = new Row[(int)Math.sqrt(dim)];
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
	
	public void addRow(Row row, int index){
		rows[index] = row;
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
	
	public Cell[] getCells(){
		return cells;
	}
	
	public boolean returnValue(int i){
		return available.add((Integer)i);
	}
}
