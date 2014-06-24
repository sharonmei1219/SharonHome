package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class Locked {

	private int num;
	private Spot[] pp;

	public Locked(int num, Spot[] ppInBlock) {
		this.num = num;
		this.pp = ppInBlock;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Locked)) return false;
		Locked that = (Locked) obj;
		if(this.num != that.num) return false;
		for(Spot eachSpotInThat : that.pp)
			if(!contains(eachSpotInThat)) return false;
		return true;
	}
	
	public boolean contains(Spot spot) {
		for(Spot anySpot : this.pp)
			if(anySpot.equals(spot)) return true;
		return false;
	}

	@Override
	public String toString(){
		String result =  "\nnum: " + num + "\n[";
		for(Spot p : pp)
			result += "{" + p.toString() + "} ";
		result += "]";
		return result;
		
	}

	public boolean inARow() {
		int row = pp[0].geti();
		for(Spot sp : pp)
			if(sp.geti() != row) return false;
		return true;
	}

	public int row() {
		return pp[0].geti();
	}

	public int num() {
		return num;
	}

	public boolean inAColumn() {
		int column = pp[0].getj();
		for(Spot p : pp)
			if(p.getj() != column) return false;
		return true;
	}

	public int column() {
		return pp[0].getj();
	}

	public boolean inABlock() {
		int br = pp[0].geti()/3;
		int bc = pp[0].getj()/3;
		
		for(Spot p : pp)
			if(p.geti()/3 != br || p.getj()/3 != bc) return false;
		return true;
	}
	
	public int br(){
		return pp[0].geti()/3;
	}
	
	public int bc(){
		return pp[0].getj()/3;
	}
}
