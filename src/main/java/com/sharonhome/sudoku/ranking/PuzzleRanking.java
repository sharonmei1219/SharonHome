package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

public class PuzzleRanking {
	
	public int ranking(Puzzle inputPuzzle){
		int ranking = 0;
		
		Puzzle puzzle = inputPuzzle.deepCopy();
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);

		while (true) {
			ArrayList<Single> newSingleList = PV.findNewSingle();
			for (Single vpt : newSingleList) {
				PV.update(vpt);
				PP.update(vpt);
				puzzle.update(vpt);
			}
			if(newSingleList.size() > 0){
				ranking += 1;
				continue;
			}
			
			newSingleList = PP.findNewSingle();
			for (Single vpt : newSingleList) {
				PV.update(vpt);
				PP.update(vpt);
				puzzle.update(vpt);
			}
			if(newSingleList.size() > 0) {
				ranking += 1;
				continue;
			}
			
			ArrayList<Locked> newLockedList = PV.findNewLocked();
			for(Locked locked : newLockedList){
				PV.update(locked);
				PP.update(locked);
			}
			if(newLockedList.size() > 0) {
				ranking += 3;
				continue;
			}
			
			newLockedList = PP.findNewLocked();
			for(Locked locked : newLockedList){
				PV.update(locked);
				PP.update(locked);
			}
			if(newLockedList.size() > 0) {
				ranking += 3;
				continue;
			}
			
			ArrayList<Pair> newPairList = PV.findNewNakedPair();
			for(Pair pair : newPairList){
				PV.update(pair);
				PP.update(pair);
			}
			if(newPairList.size() > 0) 	{
				ranking += 5;
				continue;
			}

			newPairList = PP.findNewHiddenPair();
			for(Pair pair : newPairList){
				PV.update(pair);
				PP.update(pair);
			}
			if(newPairList.size() > 0)	{
				ranking += 5;
				continue;
			}
			
			ArrayList<Triple> newTripleList = PV.findNewNakedTriple();
			for(Triple tripple : newTripleList){
				PV.update(tripple);
				PP.update(tripple);
			}
			if(newTripleList.size() > 0) {
				ranking += 10;
				continue;
			}
			
			newTripleList = PP.findNewHiddenTriple();
			for(Triple tripple : newTripleList){
				PV.update(tripple);
				PP.update(tripple);
			}
			if(newTripleList.size() > 0) {
				ranking += 10;
				continue;
			}
			
			ArrayList<XWing> newXWingList = PV.findNewXWing();
			for(XWing xwing : newXWingList){
				PV.update(xwing);
				PP.update(xwing);
			}
			if(newXWingList.size() > 0) {
				ranking += 10;
				continue;
			}
			
			newXWingList = PP.findNewXWing();
			for(XWing xwing : newXWingList){
				PV.update(xwing);
				PP.update(xwing);
			}
			if(newXWingList.size() > 0) {
				ranking += 10;
				continue;
			}
			
			break;
		}
		
		if(!puzzle.solved()){
			ranking = 500;
		}
		return ranking;
	}

}
