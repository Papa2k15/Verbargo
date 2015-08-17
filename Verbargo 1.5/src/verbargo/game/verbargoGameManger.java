package verbargo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import verbargo.card.vCardDataBase;
import verbargo.exception.EmptyTeamException;
import verbargo.exception.FullTeamException;
import verbargo.exception.InvalidFileFormatException;
import verbargo.exception.NoSuchPlayerException;
import verbargo.exception.NoTeamException;
import verbargo.limits.vLimits;
import verbargo.team.vPlayer;
import verbargo.team.vTeam;
import verbargo.team.vTeamManager;

public class verbargoGameManger implements vLimits   {
	
	private String dataBaseName;
	private String dataBaseAuthor;
	private int dataBaseSize;
	public vCardDataBase vcarddatabase;
	public vTeamManager vteammanager;
	
	public verbargoGameManger(String fileName) throws FileNotFoundException{
		vcarddatabase = new vCardDataBase();
		vteammanager = new vTeamManager();
		processFile(fileName);
	}
	
	public void createTeam(String name){
		vTeam newTeam = new vTeam(name);
		vteammanager.add(newTeam);
	}
	
	public void removeTeam(String name){
		vTeam removeTeam = new vTeam(name);
		vteammanager.remove(removeTeam);
	}
	
	public void processFile(String fileName) throws FileNotFoundException {	
		File vCardFile = new File(fileName);
		Scanner vScan = new Scanner(vCardFile);
		dataBaseName = vScan.nextLine();
		dataBaseAuthor = vScan.nextLine();
		dataBaseSize = vScan.nextInt();
		vScan.nextLine();
		int countValidate = 0;
		while(vScan.hasNextLine()){
			String line = vScan.nextLine();
			String[] cardParts = line.split("\\.");
			vcarddatabase.add(cardParts[0], cardParts[1], cardParts[2], cardParts[3], cardParts[4], cardParts[5], cardParts[6]);
			countValidate++;
		}
		vScan.close();
		if(countValidate != dataBaseSize){
			throw new InvalidFileFormatException();
		}
	}

	public void addPlayerToTeam(vPlayer player, vTeam team) throws FullTeamException {
		team.addToBack(player.getName());
	}
	
	public void removePlayerFromTeam(vPlayer player, vTeam team) throws NoTeamException, NoSuchPlayerException, EmptyTeamException{
		if(vteammanager.contains(team)){
			team.removePlayer(player.getName());
		} else {
			throw new NoTeamException();
		}
	}
	
	public vCardDataBase getCardDatabase(){
		return vcarddatabase;
	}
	
	public String getDatabaseName(){
		return dataBaseName;
	}
	
	public String getDatabaseAuthor(){
		return dataBaseAuthor;
	}
}
