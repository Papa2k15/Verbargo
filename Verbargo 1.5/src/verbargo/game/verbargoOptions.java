package verbargo.game;

import verbargo.exception.InvalidOptionException;
import verbargo.limits.vLimits;

public class verbargoOptions implements vLimits {
	
	private int difficulty;
	
	private boolean timedGame;
	
	private int time;
	
	public verbargoOptions(){
		difficulty = DEFAULT_DIFFICULTY;
		timedGame = DEFAULT_TIME_OPTION;
		time = DEFAULT_SECONDS;
	}
	
	public void setDifficulty(int difficulty) throws InvalidOptionException{
		if(difficulty >= 0 && difficulty < 3){
			this.difficulty = difficulty;
		} else {
			throw new InvalidOptionException();
		}
	}
	
	public void setTimedOption(boolean timedGame){
		this.timedGame = timedGame;
	}
	
	public void setTime(int seconds){
		this.time = seconds;
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public boolean getTimedOption(){
		return timedGame;
	}
	
	public int getTime(){
		return time;
	}
	

}
