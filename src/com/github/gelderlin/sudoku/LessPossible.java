package com.github.gelderlin.sudoku;

import java.util.Comparator;

public class LessPossible implements Comparator<Cell>{

	@Override
	public int compare(Cell cell0, Cell cell1) {
		
		return cell0.getPossible().size() - cell1.getPossible().size();
	}

}
