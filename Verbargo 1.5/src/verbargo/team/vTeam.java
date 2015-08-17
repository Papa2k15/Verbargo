package verbargo.team;

import verbargo.exception.EmptyTeamException;
import verbargo.exception.FullTeamException;
import verbargo.exception.InvalidNameException;
import verbargo.exception.InvalidScoreException;
import verbargo.exception.NoSuchPlayerException;
import verbargo.limits.vLimits;

public class vTeam implements vLimits {
	
	private int counter;

	private vPlayer front;
	
	private vPlayer back;
	
	private boolean turn;
	
	private String name;
	
	private int size;
	
	private int score;

	public vTeam(String name) {
		if(name.equals("") || name == null){
			throw new InvalidNameException();
		}
		this.name = name;
		front = null;
		back = null;
		turn = false;
		size = 0;
	}

	public void addToBack(String name) throws FullTeamException {
		if(size < MAX_TEAM_PLAYERS){
			if(isEmpty()) {
				front = back = new vPlayer(name);
			} else {
				vPlayer t = front;
				while(t.next != null) {
					t = t.next;
				}
				t.next = new vPlayer(name);
				back = t.next;
			}
			size++;
		} else {
			throw new FullTeamException();
		}
	}

	public void printPlayers() throws EmptyTeamException {
		if(!isEmpty()){
			vPlayer print = front;
			while(print != null) {
				System.out.println(print.getName());
				print = print.next;
			}
		} else {
			throw new EmptyTeamException();
		}
	}

	public boolean isEmpty() {
		if(front == null) {
			return true;
		}
		return false;
	}

	public int getSize() {
		return size;
	}
	
	public vPlayer getAt(int position) {
		if (position == 0) {
			return front;
		} else if (position == (size - 1)) {
			return back;
		} else {
			int idx = 0;
			vPlayer t = front;
			while(idx < size) {
				if(idx < position) {
					t = t.next;
					idx++;
				} else {
					return t;
				}
			}
			return null;
		}
	}
		

	public vPlayer MTFLookUp(String s) throws EmptyTeamException {
		if(!isEmpty()) {
			if(s.equals(front.getName())) {
				return front;
			} else if(s.equals(back.getName()))	{
				vPlayer t = back;
				t.next = front;
				front = t;
				back = null;
				int i = 0;
				vPlayer g = front;
				while(i < size - 1) {
					g = g.next;
					i++;
				}
				back = g;
				return t;
			} else {
				vPlayer t = front;
				int i = 0;
				while(i < size) {
					if(s.equals(t.next.getName())) {
						vPlayer l = t.next;
						t.next = t.next.next;
						l.next = front;
						front = l;
						return l;
					}
					t = t.next;
				}
				return null;
			}
		} else {
			throw new EmptyTeamException();
		}
	}

	public void removePlayer(String name) throws NoSuchPlayerException, EmptyTeamException{
		if (!isEmpty()) {
			if(name.equalsIgnoreCase(front.getName())){
				front = front.next;
				size--;
			} else if (name.equalsIgnoreCase(back.getName())) {
				vPlayer v = front;
				while(v.next != back){
					v = v.next;
				} 
				back = v;
				v.next = null;
				size--;
			} else {
				vPlayer t = front;
				while(t.next != null){
					if(t.next.getName().equalsIgnoreCase(name)){
						t.next = t.next.next;
						size--;
						return;
					}
					t = t.next;
				}
				throw new NoSuchPlayerException();
			} 
		} else {
			throw new EmptyTeamException();
		}
	}
	
	public String getPlayersOnTeam(){
		String s = "Team " + getTeamName() + "\n";
		vPlayer p = front;
		int i = 1;
		while(p != null){
			s += i + ". " + p.getName() + "\n";
			p = p.next;
			i++;
		}
		return s;
	}
	
	public void incrementScore() throws InvalidScoreException{
		if ( score+1 > MAX_SCORE ){
			throw new InvalidScoreException();
		} else {
			score++;
		}  
	}

	public void decrementScore() throws InvalidScoreException{
		if ( score-1 < MIN_SCORE ){
			throw new InvalidScoreException();
		} else {
			score--;
		}  
	}
	
	public String getGuiScore(){
		return "Current Score: " + score;
	}
	
	public int getScore(){
		return score;
	}
	
	public void isTurn(){
		turn = true;
	}
	
	public boolean isTurnOver(){
		return turn;
	}
	
	public void resetTurn(){
		turn = false;
	}
	
	public String getTeamName(){
		return name;
	}
	
	/*
	 * 
	 */
	public void incCounter(){
		if(counter+1 < size){
			counter++;
		} else {
			counter = 0;
		}
	}
	
	public int getCounter(){
		return counter;
	}
}
