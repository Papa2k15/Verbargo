package verbargo.card;

import java.util.ArrayList;
import java.util.Random;

import verbargo.exception.InvalidCardException;
import verbargo.exception.UsedAllCardsException;
import verbargo.limits.vLimits;

public class vCardDataBase extends ArrayList<vCard> implements vLimits{
	
	Random rand = new Random();

	private static final long serialVersionUID = 1L;
	
	public vCardDataBase(){}
	
	public void add(String vWord, String rv1, String rv2, String rv3, String rv4, String rv5, String rv6){
		if(vWord == null || rv1 == null || rv2 == null || rv3 == null || rv4 == null || rv5 == null || rv6 == null){
			throw new InvalidCardException();
		} else if (vWord.equalsIgnoreCase("") || rv1.equalsIgnoreCase("") || rv2.equalsIgnoreCase("") || rv3.equalsIgnoreCase("") || rv4.equalsIgnoreCase("") || rv5.equalsIgnoreCase("") || rv6.equalsIgnoreCase("")){
			throw new InvalidCardException();
		} else {
			String[] rvWords = new String[MAX_RV_WORDS];
			rvWords[0] = rv1;
			rvWords[1] = rv2;
			rvWords[2] = rv3;
			rvWords[3] = rv4;
			rvWords[4] = rv5;
			rvWords[5] = rv6;
			vCard newCard = new vCard(vWord, rvWords);
			this.add(newCard);
		}
	}
	
	public boolean allCardsUsed(){
		boolean n = false;
		int countUsedCards = 0;
		for(int i = 0; i < this.size(); i++){
			if(this.get(i).hasBeenUsed()){
				countUsedCards++;
			}
		}
		if(countUsedCards == this.size()){
			n = true;
		}
		return n;
	}
	
	public vCard getCurrentvCard() throws UsedAllCardsException{
		if(allCardsUsed()){
			throw new UsedAllCardsException();
		}
		int n = rand.nextInt(this.size());
		while(this.get(n).hasBeenUsed()){
			n = rand.nextInt(this.size());
		}
		this.get(n).useCard();
		return this.get(n);
	}
	
	public void resetCardUsage() {
		for(int i = 0; i < this.size(); i++){
			this.get(i).resetUsage();
		}
	}
}
