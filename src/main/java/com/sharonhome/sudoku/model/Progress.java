package com.sharonhome.sudoku.model;

public class Progress {
	private int easy;
	private int normal;
	private int hard;
	private int evil;
	private int total;
	private boolean inProgress;
	
	private int storedEasy;
	private int storedNormal;
	private int storedHard;
	private int storedEvil;
	private String warning = "";
	
	public void setStoredEasy(int storedEasy){
		this.storedEasy = storedEasy;
	}
	
	public int getStoredEasy(){
		return storedEasy;
	}
	
	public void setStoredNormal(int storedNormal){
		this.storedNormal = storedNormal;
	}
	
	public int getStoredNormal(){
		return storedNormal;
	}
	
	public void setStoredHard(int storedHard){
		this.storedHard = storedHard;
	}
	
	public int getStoredHard(){
		return storedHard;
	}
	
	public void setStoredEvil(int storedEvil){
		this.storedEvil = storedEvil;
	}
	
	public int getStoredEvil(){
		return storedEvil;
	}
	
	public int getStoredAll(){
		return storedEasy + storedNormal + storedHard + storedEvil;
	}
	
	public void setWarning(String warning){
		this.warning = warning;
	}
	
	public String getWarning(){
		return warning;
	}
	
	public void setInProgress(boolean inProgress){
		this.inProgress = inProgress;
	}
	
	public boolean getInProgress(){
		return inProgress;
	}
	
	public void setEasy(int easy){
		this.easy = easy;
	}
	
	public int getEasy(){
		return this.easy;
	}
	
	public int getPercentageEasy(){
		return percentage(easy);
	}
	
	public void setNormal(int normal){
		this.normal = normal;
	}
	
	public int getNormal(){
		return normal;
	}
	
	public int getPercentageNormal(){
		return percentage(normal);
	}
	
	public void setHard(int hard){
		this.hard = hard;
	}
	
	public int getHard(){
		return hard;
	}
	
	public int getPercentageHard(){
		return percentage(hard);
	}
	
	private int percentage(int number){
		return (number * 100) / total;
	}
	
	public void setTotal(int total){
		this.total = total;
	}

	public void setEvil(int evil) {
		this.evil = evil;
	}
	
	public int getEvil(){
		return evil;
	}
	
	public int getPercentageEvil(){
		return percentage(evil);
	}
	
	public int getPercentageTotal(){
		return percentage(easy + normal + hard + evil);
	}
	
	public int getTotal(){
		return easy + normal + hard + evil;
	}
}
