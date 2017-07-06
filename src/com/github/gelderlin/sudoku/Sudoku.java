package com.github.gelderlin.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku {
	private Cell[] board;
	private int dim = 4;
	private Group[] groups;
	private Column[] cols;
	private Row[] rows;
	private final ArrayList<Integer> DIMS = new ArrayList<Integer>(Arrays.asList(4, 9, 16, 25, 36));
	
	public Sudoku(int dim)throws IllegalArgumentException{
		setDim(dim);
		System.out.println("Creating Board");
		System.out.printf("dim: %d\n", dim);
		board = new Cell[dim*dim];
		groups = new Group[dim];
		cols = new Column[dim];
		rows = new Row[dim];
		setup();
	}
	
	private void setDim(int dim)throws IllegalArgumentException{
		if(DIMS.contains((Integer)dim)){
			this.dim = dim;
		}else throw new IllegalArgumentException("Puzzle must be one of the following: " + DIMS.toString());
	}
	
	public Group getGroup(int i){
		return groups[i];
	}
	
	public Column getColumn(int i){
		return cols[i];
	}
	
	public Row getRow(int i){
		return rows[i];
	}
	public Cell[] getBoard(){
		return board;
	}
	
	public void setup(){
		System.out.println("Populating rows, cols, and groups");
		for(int i = 0; i < dim; i++){
			System.out.printf("Row %d", i);
			rows[i] = new Row(dim);
			System.out.printf("Col %d", i);
			cols[i] = new Column(dim);
			System.out.printf("Group %d\n", i);
			groups[i] = new Group(dim);
		}
		for(int i = 0; i < dim*dim; i++){
			System.out.printf("Making cell %d\n", i);
			int row = i/dim;
			int col = i % dim;
			int group =  row / (int)Math.sqrt(dim) * (int)Math.sqrt(dim) + col / (int)Math.sqrt(dim);
			board[i] = new Cell(rows[row], cols[col], groups[group], dim);
			rows[row].addCell(board[i]);
			cols[col].addCell(board[i]);
			groups[group].addCell(board[i]);
		}
	}
	
	/**
	 * Checks if the puzzle is complete.
	 * @return true if complete, false otherwise
	 */
	public boolean check(){
		boolean check = true;
		for(Cell c : board){
			if(c.getPossible().size() != 0){
				check = false;
				break;
			}
		}
		return check;
	}
	
	/**
	 * Returns a printable string of the game board
	 */
	public String toString(){
		StringBuilder asString = new StringBuilder();
		for(int i = 0; i < dim; i++){
			if(i>0){asString.append(System.lineSeparator());}
			for(int j = 0; j < dim; j++){
				asString.append(board[i*dim+j].toString() + " ");
			}
		}
		return asString.toString();
	}
	
	private void setBoard(Cell[] board){
		this.board = board;
	}
	
	public Sudoku copy(){
		Sudoku newGame = new Sudoku(dim);
		Cell[] newBoard = newGame.getBoard();
		for(int i = 0; i < dim * dim; i++){
			if(board[i].getValue() != 0)
				newBoard[i].changeValue(board[i].getValue());
		}
		return newGame;
	}
	
}
