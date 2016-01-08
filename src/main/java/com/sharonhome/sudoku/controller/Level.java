package com.sharonhome.sudoku.controller;

public class Level {
	private String level;
	private String type;
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	
	public void setLevel(String level){
		this.level = level;
	}
	public String getLevel(){
		return this.level;
	}
	
	public String toString(){
		return level;
	};
}