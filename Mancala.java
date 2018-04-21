package mancalagame;

//Modeling the Mancala 
public class Mancala {
    
	private int numberOfS;
	
	public Mancala() {
		this.numberOfS=0;
	}
	
	public void decrementS() {
		numberOfS--;
	}
	
	public int getNumberOfS() {
		return numberOfS;
	}
	
	public void IncrementS() {
		numberOfS++;
	}
	
	public void SetZero() {
		numberOfS=0;
	}
	
}
