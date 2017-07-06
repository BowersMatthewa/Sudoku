package com.github.gelderlin.sudoku;

import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static int dim;
	static Sudoku game;
	static SudokuSolver solver;
	
	public static void main(String[] args) {
		boolean check = false;
		int row = 1;
		while(!check){
			getDim();
			try{
				game = new Sudoku(dim);
				System.out.println("Make Puzzle");
				check = true;
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		for(int i = 0; i < dim; i++){
			getRow(i);
		}

		solver = new SudokuSolver(dim, game);
		System.out.println("Original Board");
		System.out.println(game.toString());
		try{
			solver.solveStep();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("step 1");
		System.out.println(game.toString());
		try{
			solver.solveStep();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("step 2");
		System.out.println(game.toString());
		try{
			solver.fullSolve();
		}catch(Exception e){
			e.printStackTrace();
			for(Cell c : game.getBoard()){
				System.out.println(c.getPossible());
			}
		}
		System.out.println("finished");
		System.out.println(game.toString());
		
	}
	
	private static void getRow(int row){
		System.out.printf("Please enter line %d of the puzzle. Use a 0 for a blank and a space between each number.\n", row);
		String line = scan.nextLine();
		String[] nums = line.split(" ");
		System.out.println(nums.length);
		if(nums.length != dim){
			System.out.printf("You must enter %d values.\n", dim);
			getRow(row);
		}else{
			for(int i = 0; i < dim; i++){
				int value = Integer.parseInt(nums[i]);
				if(value == 0){
					continue;
				}else{
					System.out.printf("Adding cell: %d\n", (row)*dim+i);
					game.getBoard()[(row)*dim+i].changeValue(value);
				}
			}
		}
	}
	
	private static void getDim(){
		System.out.println("Please enter the number of rows in the puzzle.");
		dim = scan.nextInt();
	}

}
