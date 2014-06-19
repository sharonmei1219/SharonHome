package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

public class PuzzleRanking {
	
	public int ranking(Puzzle inputPuzzle){
		int ranking = 0;
		
		Puzzle puzzle = inputPuzzle.deepCopy();
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		ArrayList<Single> singlelist = new ArrayList<Single>();
		ArrayList<Pair> pairlist = new ArrayList<Pair>();
		ArrayList<Tripple> tripplelist = new ArrayList<Tripple>();
		ArrayList<Locked> lockedlist = new ArrayList<Locked>();
		ArrayList<XWing> xwinglist = new ArrayList<XWing>();

		while (true) {
			ArrayList<Single> newSingleList = PV.findNewSingle(singlelist);
			for (Single vpt : newSingleList) {
				PV.update(vpt);
				PP.update(vpt);
				puzzle.update(vpt);
			}
			if(newSingleList.size() > 0) continue;
			
			newSingleList = PP.findNewSingle(singlelist);
			for (Single vpt : newSingleList) {
				PV.update(vpt);
				PP.update(vpt);
				puzzle.update(vpt);
			}
			if(newSingleList.size() > 0) continue;
			
			ArrayList<Pair> newPairList = PV.findNewNakedPair();
			for(Pair pair : newPairList){
				PV.update(pair);
				PP.update(pair);
			}
			if(newPairList.size() > 0) 	continue;

			ranking += 1;
			newPairList = PP.findNewHiddenPair(pairlist);
			for(Pair pair : newPairList){
				PV.update(pair);
				PP.update(pair);
			}
			if(newPairList.size() > 0)	continue;
		
			ranking += 1;
			ArrayList<Locked> newLockedList = PV.findNewLocked(lockedlist);
			for(Locked locked : newLockedList){
				PV.update(locked);
				PP.update(locked);
			}
			if(newLockedList.size() > 0) continue;
			
			newLockedList = PP.findNewLocked(lockedlist);
			for(Locked locked : newLockedList){
				PV.update(locked);
				PP.update(locked);
			}
			if(newLockedList.size() > 0) continue;

			ranking += 1;
			
			ArrayList<Tripple> newTrippleList = PV.findNewTripple(tripplelist);
			for(Tripple tripple : newTrippleList){
				PV.update(tripple);
				PP.update(tripple);
			}
			if(newTrippleList.size() > 0) continue;
			
			newTrippleList = PP.findNewTripple(tripplelist);
			for(Tripple tripple : newTrippleList){
				PV.update(tripple);
				PP.update(tripple);
			}
			if(newTrippleList.size() > 0) continue;
			
			ArrayList<XWing> newXWingList = PV.findNewXWing(xwinglist);
			for(XWing xwing : newXWingList){
				PV.update(xwing);
				PP.update(xwing);
			}
			if(newXWingList.size() > 0) continue;
			
			newXWingList = PP.findNewXWing(xwinglist);
			for(XWing xwing : newXWingList){
				PV.update(xwing);
				PP.update(xwing);
			}
			if(newXWingList.size() > 0) continue;
			
			break;
		}
		
		if(!puzzle.solved()) ranking += 100;
		
		return ranking;
	}

}
