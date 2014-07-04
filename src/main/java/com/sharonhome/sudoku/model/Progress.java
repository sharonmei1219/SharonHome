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
	
	public boolean progressEnd(){
		return getTotal() >= total;
	}
	
	public void newProgress(int total){
		this.total = total;
		this.easy = 0;
		this.hard = 0;
		this.normal = 0;
		this.evil = 0;
		warning = "";
	}
	
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
	
	public int getEasy(){
		return this.easy;
	}
	
	public int getPercentageEasy(){
		return percentage(easy);
	}
	
	public int getNormal(){
		return normal;
	}
	
	public int getPercentageNormal(){
		return percentage(normal);
	}
	
	public int getHard(){
		return hard;
	}
	
	public int getPercentageHard(){
		return percentage(hard);
	}
	
	private int percentage(int number){
		if(total == 0) return 0;
		return (number * 100) / total;
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

	public void inc(String level) {
		if(level.equals("easy")) {
			easy++;
			storedEasy ++;
		}else if(level.equals("normal")){
			normal ++;
			storedNormal ++;
		}else if(level.equals("hard")){
			hard ++;
			storedHard ++;
		}else if(level.equals("evil")){
			evil ++;
			storedEvil ++;
		}
	}
}
