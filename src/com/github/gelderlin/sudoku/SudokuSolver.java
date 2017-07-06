package com.github.gelderlin.sudoku;

public class SudokuSolver {
	private Sudoku puzzle;
	private int numLeft;
	private int dim;
	
	public SudokuSolver(int dim, Sudoku puzzle){
		this.dim = dim;
		numLeft = dim * dim;
		this.puzzle = puzzle;
		for(Cell c : puzzle.getBoard()){
			if(c.getPossible().size() == 0){
				numLeft--;
			}
		}
	}
	
	public boolean fullSolve() throws Exception{
		while(!puzzle.check()){
			solveStep();
		}
		return true;
	}
	
	public boolean solveStep() throws Exception{
		boolean check = false;
		for(Cell c : puzzle.getBoard()){
			//System.out.println(c.getPossible());
			if(c.getPossible().size() == 1){
				c.changeValue(c.getPossible().get(0));
				check = true;
				break;
			}
		}
		if(check) return puzzle.check();
		else{
			check = false;
			//throw new Exception("This puzzle cannot be solved by this solver.");
			for(Cell c : puzzle.getBoard()){
				for(Integer x : c.getPossible()){
					int count = 0;
					for(Cell o : c.getGroup().getCells()){
						if(o.equals(c)){continue;}
						else{
							if(o.getPossible().contains(x)){
								continue;
							}else{
								count++;
							}
						}
					}
					if(count == dim - 1){
						check = true;
						c.changeValue(x);
						return puzzle.check();
					}else continue;
				}
			}
			if(!check){
				throw new Exception("Puzzle cannot be solved by this algorithm");
			}
			throw new Exception("Puzzle Cannot be solved by this algorithm.");
		}
	}
	
	public int getNumLeft(){
		return numLeft;
	}
	
	public int getDim(){
		return dim;
	}
}
