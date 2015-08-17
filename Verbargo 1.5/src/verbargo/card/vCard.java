package verbargo.card;

public class vCard {
	
	private boolean used;
	
	private String vWord;
	
	private String[] rvWords;
	
	public vCard(String vWord, String[] rvWords){
		used = false;
		this.vWord = vWord;
		this.rvWords = rvWords;
	}
	
	public String getvWord(){
		return vWord;
	}
	
	public String[] getrvWords(){
		return rvWords;
	}
	
	public void useCard(){
		used = true;
	}
	
	public boolean hasBeenUsed(){
		return used;
	}
	
	public void resetUsage(){
		used = false;
	}
}
