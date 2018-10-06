/**
 * SJSU Spring 2018 CS 151
 * Team Project Mancala
 * @author Ziqi Yuan, Brandon Zhou, Pratyusha Pogaru
 * @version 1.0
 * @since 04/05/2018
 */



import javax.swing.Icon;
import javax.swing.JLabel;

//Modeling the Mancala 
public class Mancala {
    
	private int numberOfS;
	private Mancala next;
	private Mancala previous;
	private JLabel label;
	private JLabel IconLabel;
	
	public Mancala() {
		this.numberOfS=0;
	}
	
	public void decrementS() {
		numberOfS--;
	}
	
	public int getNumber() {
		return numberOfS;
	}
	
	public void IncrementS() {
		numberOfS++;
	}
	
	public void SetNumber(int number) {
		numberOfS=number;
	}
	
	public void setNext(Mancala m) {
		this.next=m;
	}
	
	public void setPrevious(Mancala m) {
		this.previous=m;
	}
	
	public Mancala next() {
		return this.next;
	}
	
	public Mancala prev() {
		return this.previous;
	}
	
	public void setLabel(JLabel label) {
		this.label=label;
	}
	
	public JLabel getLabel() {
		return this.label;
	}
	
	public void setIcon(JLabel label) {
		this.IconLabel=label;
	}
	
	public JLabel getIconLabel(){
		return this.IconLabel;
	}
}
