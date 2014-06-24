package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class PossiblePositionVector {

	PossiblePositions[] vector = new PossiblePositions[9];
	private ArrayList<Pair> pairList = new ArrayList<Pair>();
	private ArrayList<Locked> lockedList = new ArrayList<Locked>();
	private ArrayList<XWing> xWingList = new ArrayList<XWing>();
	private ArrayList<Triple> tripleList = new ArrayList<Triple>();

	public PossiblePositionVector(Puzzle puzzle) {
		for (int i = 0; i < 9; i++)
			vector[i] = new PossiblePositions();

		ArrayList<Single> vpts = puzzle.getDeterminedValues();
		for (Single vpt : vpts)
			update(vpt);
	}

	public void update(Single vpt) {
		for (int i = 0; i < 9; i++) {
			vector[i].notPossibleIn(vpt.geti(), vpt.getj());
		}
		valueNotPossibleInRow(vpt.getValue(), vpt.geti());
		valueNotPossibleInColumn(vpt.getValue(), vpt.getj());
		valueNotPossibleInBlock(vpt.getValue(), vpt.geti(), vpt.getj());

	}

	private void valueNotPossibleInBlock(int value, int row, int column) {
		int minI = row - row % 3;
		int minJ = column - column % 3;
		for (int i = minI; i < minI + 3; i++)
			for (int j = minJ; j < minJ + 3; j++)
				vector[value].notPossibleIn(i, j);

	}

	private void valueNotPossibleInColumn(int value, int column) {
		for (int i = 0; i < 9; i++) {
			vector[value].notPossibleIn(i, column);
		}

	}

	private void valueNotPossibleInRow(int value, int row) {
		for (int j = 0; j < 9; j++) {
			vector[value].notPossibleIn(row, j);
		}
	}

	public void update(Pair pair) {
		if (pair.inARow())
			updatePairInRow(pair);
		if (pair.inAColumn())
			updatePairInColumn(pair);
		if (pair.inABlock())
			updatePairInBlock(pair);
	}

	private void updatePairInBlock(Pair pair) {
		int[] pv = pair.possibilities().possibleValue();
		int br = pair.block().geti();
		int bc = pair.block().getj();
		for (int i = br * 3; i < br * 3 + 3; i++) {
			for (int j = bc * 3; j < bc * 3 + 3; j++) {
				if (pair.has(new Spot(i, j))){
					notPossibleExcept(pv, i, j);
				}
				else{
					vector[pv[0]].notPossibleIn(i, j);
					vector[pv[1]].notPossibleIn(i, j);
				}
			}
		}
	}

	private void updatePairInColumn(Pair pair) {
		int[] pv = pair.possibilities().possibleValue();
		int column = pair.column();
		for (int i = 0; i < 9; i++) {
			if (pair.has(new Spot(i, column))){
				notPossibleExcept(pv, i, column);
			}
			else{
				vector[pv[0]].notPossibleIn(i, column);
				vector[pv[1]].notPossibleIn(i, column);
			}
		}
	}

	private void updatePairInRow(Pair pair) {
		int[] pv = pair.possibilities().possibleValue();
		int row = pair.row();
		for (int j = 0; j < 9; j++) {
			if (pair.has(new Spot(row, j))){
				notPossibleExcept(pv, row, j);
			}else{
				vector[pv[0]].notPossibleIn(row, j);
				vector[pv[1]].notPossibleIn(row, j);
			}
		}
	}

	private void notPossibleExcept(int[] pv, int row, int j) {
		for(int value = 0; value < 9; value ++){
			if(contains(pv, value)) continue;
			vector[value].notPossibleIn(row, j);
		}
	}

	public ArrayList<Single> findNewSingle() {
		ArrayList<Single> result = new ArrayList<Single>();
		for (int i = 0; i < 9; i++) {
			ArrayList<Spot> determinedPos = vector[i]
					.onlyPossiblePosInARegion();
			for (Spot pos : determinedPos)
				result.add(new Single(i, pos));
		}

		return result;
	}

	public ArrayList<Pair> findNewHiddenPair() {
		ArrayList<Pair> result = new ArrayList<Pair>();
		for (int row = 0; row < 9; row++)
			findPairsInRow(result, row);
		for (int column = 0; column < 9; column++)
			findPairsInColumn(result, column);
		for (int br = 0; br < 3; br++) {
			for (int bc = 0; bc < 3; bc++) {
				findPairsInBlock(result, br, bc);
			}
		}

		return result;
	}

	private void findPairsInBlock(ArrayList<Pair> result, int br, int bc) {
		for (int n = 0; n < 8; n++) {
			if (2 != vector[n].getNumberOfPossiblePositionsInBlock(br, bc))
				continue;
			for (int cn = n + 1; cn < 9; cn++) {
				PossiblePositions ppNumber = vector[n];
				PossiblePositions ppCN = vector[cn];
				Spot[] spots = ppNumber.possiblePosInBlock(br, bc);
				if (ppNumber.matchesInBlock(ppCN, br, bc)) {
					Pair found = new Pair(new PossibleValues(new int[] { n, cn }), 
							              spots[0],
							              spots[1]);
					addToResult(result, found);
				}
			}
		}
	}

	private void findPairsInColumn(ArrayList<Pair> result, int column) {
		for (int number = 0; number < 8; number++) {
			if (2 != vector[number]
					.getNumberOfPossiblePositionsInColumn(column))
				continue;
			for (int cn = number + 1; cn < 9; cn++) {
				PossiblePositions ppNumber = vector[number];
				PossiblePositions ppCN = vector[cn];
				if (ppNumber.matchesInColumn(ppCN, column)) {
					int[] ros = ppNumber.possiblePosInColumn(column);
					Pair found = new Pair(new PossibleValues(new int[] { number, cn }), 
							              new Spot(ros[0], column), 
							              new Spot(ros[1],column));
					addToResult(result, found);
				}
			}
		}
	}

	private void findPairsInRow(ArrayList<Pair> result, int row) {
		for (int number = 0; number < 8; number++) {
			if (2 != vector[number].getNumberOfPossiblePositionsInRow(row))
				continue;
			for (int cnumber = number + 1; cnumber < 9; cnumber++) {
				PossiblePositions ppNumber = vector[number];
				PossiblePositions ppCNumber = vector[cnumber];
				if (ppNumber.matchesInRow(ppCNumber, row)) {
					int[] cos = ppNumber.possiblePosInRow(row);
					Pair found = new Pair(new PossibleValues(new int[] {number, cnumber }), 
							              new Spot(row, cos[0]),
							              new Spot(row, cos[1]));
					addToResult(result, found);
				}
			}
		}
	}

	private void addToResult(ArrayList<Pair> result, Pair found) {
		if (!pairList.contains(found)) {
			result.add(found);
			pairList.add(found);
		}
	}

	public void update(Locked locked) {
		if(locked.inARow())	updateLockedInRow(locked);
		if(locked.inAColumn()) updateLockedInColumn(locked);

	}

	private void updateLockedInColumn(Locked locked) {
		int column = locked.column();
		int num = locked.num();
		for(int i = 0; i < 9; i++){
			if(!locked.contains(new Spot(i, column)))
				vector[num].notPossibleIn(i, column);
		}
		
	}

	private void updateLockedInRow(Locked locked) {
		int row = locked.row();
		int num = locked.num();
		for(int j = 0; j < 9; j++){
			if(!locked.contains(new Spot(row, j)))
				vector[num].notPossibleIn(row, j);
		}
	}

	public ArrayList<Locked> findNewLocked() {
		ArrayList<Locked> result = new ArrayList<Locked>();
		for(int num = 0; num < 9; num ++){
			PossiblePositions possiblePositions = vector[num];
			searchLockedInBlocks(result, num, possiblePositions);
			searchLockedInRows(result, num, possiblePositions);
			for(int column = 0; column < 9; column ++){
				Spot[] ppInAColumn = possiblePositions.inColumn(column);
				if(ppInAColumn.length > 3 || ppInAColumn.length == 0) continue;
				if(! inABlock(ppInAColumn)) continue;
				Locked found = new Locked(num, ppInAColumn);
				addToResult(result, found);
			}
		}
		return result;
	}

	private void searchLockedInRows(ArrayList<Locked> result, int num,
			PossiblePositions possiblePositions) {
		for(int row = 0; row < 9; row ++){
			Spot[] ppInArow = possiblePositions.inRow(row);
			if(ppInArow.length > 3 || ppInArow.length == 0) continue;
			if(! inABlock(ppInArow)) continue;
			Locked found = new Locked(num, ppInArow);
			addToResult(result, found);
		}
	}

	private boolean inABlock(Spot[] ppInArow) {
		int bc = ppInArow[0].getj()/3;
		for(Spot p : ppInArow)
			if(p.getj()/3 != bc) return false;
		return true;
	}

	private void searchLockedInBlocks(ArrayList<Locked> result, int num,
			PossiblePositions possiblePositions) {
		for(int br = 0; br < 3; br ++){
			for(int bc = 0; bc < 3; bc ++){
				Spot[] ppInBlock = possiblePositions.inBlock(br, bc);
				if(ppInBlock.length > 3 || ppInBlock.length == 0) continue;
				if(inARow(ppInBlock) || inAColumn(ppInBlock)) {
					Locked found = new Locked(num, ppInBlock);
					addToResult(result, found);
				}
			}
		}
	}

	private boolean inAColumn(Spot[] ppInBlock) {
		int column = ppInBlock[0].getj();
		for(Spot eachP : ppInBlock)
			if(eachP.getj() != column) return false;
		return true;
	}

	private void addToResult(ArrayList<Locked> result, Locked found) {
		if(!lockedList.contains(found)){
			result.add(found);
			lockedList.add(found);
		}
	}	

	private boolean inARow(Spot[] ppInBlock) {
		int row = ppInBlock[0].geti();
		for(Spot eachSpot : ppInBlock)
			if(eachSpot.geti() != row) return false;
		return true;
	}

	public void update(Triple tripple) {
		if(tripple.inARow()){
			updateTrippleInRow(tripple);
		}
		if(tripple.inAColumn()){
			updateTrippleInColumn(tripple);
		}
		if(tripple.inABlock()){
			updateTrippleInBlock(tripple);
		}

	}

	private void updateTrippleInBlock(Triple tripple) {
		int br = tripple.br();
		int bc = tripple.bc();
		int [] pv = tripple.possibleValues().possibleValue();
		for(int i = br * 3; i < br * 3 + 3; i ++){
			for(int j = bc * 3; j < bc * 3 + 3; j++){
				if(tripple.contains(new Spot(i, j))){
					notPossibleExcept(pv, i, j);
				}else{
					for(int value: pv)
						vector[value].notPossibleIn(i, j);
				}
			}
		}
	}

	private void updateTrippleInColumn(Triple tripple) {
		int column = tripple.column();
		int[] pv = tripple.possibleValues().possibleValue();
		for(int i = 0; i < 9; i++){
			if(tripple.contains(new Spot(i, column))){
				notPossibleExcept(pv, i, column);
			}else{
				for(int value: pv)
					vector[value].notPossibleIn(i, column);
			}
		}
	}

	private void updateTrippleInRow(Triple tripple) {
		int row = tripple.row();
		int[] pv = tripple.possibleValues().possibleValue();
		for(int j = 0; j < 9; j++){
			if(tripple.contains(new Spot(row, j))){
				notPossibleExcept(pv, row, j);
			}else{
				for(int value : pv) {
					vector[value].notPossibleIn(row, j);
				}
			}
		}
	}

	private boolean contains(int[] pv, int value) {
		for(int v : pv)
			if(v == value) return true;
		return false;
	}

	public ArrayList<Triple> findNewHiddenTriple() {
		ArrayList<Triple> result = new ArrayList<Triple>();
		for(int br = 0; br < 3; br ++){
			for(int bc = 0; bc < 3; bc ++){
				for(int n1 = 0; n1 < 7; n1++){
					Spot[] sN1 = vector[n1].inBlock(br, bc);
					if(sN1.length > 3 || sN1.length == 0) continue;
					for(int n2 = n1 + 1; n2 < 8; n2 ++){
						Spot[] sN2 = vector[n2].inBlock(br, bc);
						if(sN2.length > 3 || sN2.length == 0) continue;
						Spot[] sunion = union(sN1, sN2);
						if(sunion.length != 3) continue;
						for(int n3 = n2 + 1; n3 < 9; n3++){
							Spot[] sN3 = vector[n3].inBlock(br, bc);
							if(sN3.length > 3 || sN3.length == 0) continue;
							sunion = union(sunion, sN3);
							if (sunion.length == 3){
								Triple found = new Triple(new PossibleValues(new int[]{n1, n2, n3}),
										                   sunion);
								if(!tripleList.contains(found)){
									result.add(found);
									tripleList.add(found);
								}
							}
						}
						
					}
				}
			}
		}
		return result;
	}

	private Spot[] union(Spot[] sN1, Spot[] sN2) {
		ArrayList <Spot> list = new ArrayList<Spot>();
		for(Spot eachS : sN1)
			list.add(eachS);
		for(Spot eachS : sN2)
			if(!list.contains(eachS))
				list.add(eachS);
		return listToArray(list);
	}

	private Spot[] listToArray(ArrayList<Spot> list) {
		Spot [] result = new Spot[list.size()];
		for(int i = 0; i < result.length; i++)
			result[i] = list.get(i);
		return result;
	}

	public void update(XWing xwing) {
		if(xwing.columnXWing()){
			setRowsInXWingNotImpossible(xwing);
		}
		if(xwing.rowXWing()){
			int[] columns = xwing.columns();
			int num = xwing.num();
			for(int i = 0; i < 9; i++){
				if(xwing.contains(new Spot(i, columns[0]))) continue;
				vector[num].notPossibleIn(i, columns[0]);
				vector[num].notPossibleIn(i, columns[1]);
			}
		}

	}

	private void setRowsInXWingNotImpossible(XWing xwing) {
		int [] rows = xwing.rows();
		int num = xwing.num();
		for(int j = 0; j < 9; j++){
			if(xwing.contains(new Spot(rows[0], j)))continue;
			vector[num].notPossibleIn(rows[0], j);
			vector[num].notPossibleIn(rows[1], j);
		}
	}

	public ArrayList<XWing> findNewXWing() {
		ArrayList<XWing> result = new ArrayList<XWing>();
		for(int num = 0; num < 9; num ++){
			findShaddowColumn(result, num);
			findShadowRows(result, num);
		}
		return result;
	}

	private void findShadowRows(ArrayList<XWing> result, int num) {
		for(int r1 = 0; r1 < 8; r1 ++){
			Spot[] spotInR1 = vector[num].inRow(r1);
			if(spotInR1.length != 2) continue;
			for(int r2 = r1 + 1; r2 < 9; r2 ++){
				Spot[] spotInR2 = vector[num].inRow(r2);
				if(spotInR2.length != 2) continue;
				if(!shadowRow(spotInR1, spotInR2)) continue;
				XWing found = new XWing(XWing.ROW, num, new Spot[]{
						               spotInR1[0], spotInR1[1],
						               spotInR2[0], spotInR2[1]});
				addToResult(result, found);
			}
		}
	}

	private boolean shadowRow(Spot[] spotInR1, Spot[] spotInR2) {
		int column1 = spotInR1[0].getj();
		int column2 = spotInR1[1].getj();
		if(spotInR2[0].getj() == column1 && spotInR2[1].getj() == column2) return true;
		if(spotInR2[0].getj() == column2 && spotInR2[1].getj() == column1) return true;
		return false;
	}

	private void findShaddowColumn(ArrayList<XWing> result, int num) {
		for(int c1 = 0; c1 < 8; c1 ++){
			Spot[] posInC1 = vector[num].inColumn(c1);
			if(posInC1.length != 2) continue;
			for(int c2 = c1+1; c2 < 9; c2 ++ ){
				Spot[] posInC2 = vector[num].inColumn(c2);
				if(posInC2.length != 2) continue;
				if(!shadowColumn(posInC1, posInC2)) continue;
				XWing found = new XWing(XWing.COLUMN, num, new Spot[]{
						posInC1[0], posInC1[1],
						posInC2[0], posInC2[1]
					});
				addToResult(result, found);
			}
		}
	}

	private void addToResult(ArrayList<XWing> result, XWing found) {
		if(!xWingList.contains(found)){
		result.add(found);
		xWingList.add(found);}
	}

	private boolean shadowColumn(Spot[] posInC1, Spot[] posInC2) {
		int row1 = posInC1[0].geti();
		int row2 = posInC1[1].geti();
		if((posInC2[0].geti() == row1 && posInC2[1].geti() == row2)) return true;
		if((posInC2[0].geti() == row2 && posInC2[1].geti() == row1)) return true;
		return false;
	}

}
