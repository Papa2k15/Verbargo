package verbargo.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import verbargo.limits.vLimits;

public class vTeamManager extends ArrayList<vTeam> implements vLimits {
	
	private static final long serialVersionUID = 1L;
	
	Random rand = new Random();
	
	public vTeamManager(){};

	public String[] getTeamNames(){
		String[] names = new String[this.size()];
		for(int i = 0;i < this.size(); i++){
			names[i] = this.get(i).getTeamName();
		}
		return names;
	}
	
	public vTeam getTeam(String teamName){
		vTeam returnTeam;
		for(int i = 0; i < this.size(); i++){
			if(teamName.equalsIgnoreCase(this.get(i).getTeamName())){
				returnTeam = this.get(i);
				return returnTeam;
			}
		}
		return null;
	}
	
	public vTeam getCurrentTeam(){
		if(allTeamsWent()){
			resetTeamTurns();
		}
		for(int i = 0; i < this.size(); i++){
			if(this.get(i).isTurnOver() == false){
				this.get(i).isTurn();
				return this.get(i);
			}
		}
		return null;
	}
	
	private void resetTeamTurns() {
		for(int i = 0; i < this.size(); i++){
			this.get(i).resetTurn();
		}
	}

	private boolean allTeamsWent() {
		int went = 0;
		for(int i = 0; i < this.size(); i++){
			if(this.get(i).isTurnOver() == true){
				went++;
			}
		}
		if(went == this.size()){
			return true;
		}
		return false;
	}

	public void setTeamGameOrder(){
		Collections.shuffle(this);
	}
}
