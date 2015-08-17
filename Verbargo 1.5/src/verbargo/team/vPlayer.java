package verbargo.team;

import verbargo.exception.InvalidNameException;

public class vPlayer {
	
	private String name;
	
	public vPlayer next;
		
	public vPlayer(String name){
		this(name, null);
	}
	
	public vPlayer(String name, vPlayer next){
		if(name.equals("") || name == null){
			throw new InvalidNameException();
		}
		this.name = name;
		this.next = next;
	}
	
	public String getName(){
		return name;
	}
	
	
}
