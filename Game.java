/**
 * SJSU Spring 2018 CS 151
 * Team Project Mancala
 * @author Ziqi Yuan, Brandon Zhou, Pratyusha Pogaru
 * @version 1.0
 * @since 04/05/2018
 */



import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.event.ChangeListener;

//This class works as the Model
public class Game {
    
	private Mancala[][] pits;
	private Mancala m1;
	private Mancala m2;
	private ArrayList<ChangeListener> listeners;
	private Mancala finalT;
	private int activePlayer;
    private JButton[] buttons;
    private ActionListeners[] aL;
 

	
	public Game() {
		
		aL=new ActionListeners[12];
		buttons=new JButton[13];
		activePlayer=0;
		pits=new Mancala[2][6]; //the Mancala 2D array only holds pits
		this.m1=new Mancala();
		this.m2=new Mancala();
		//pits[2][0]=this.m1;
		//pits[2][1]=this.m2;
		for(int i=0;i<2;i++) {    // Create two sets of 6 pits
			for(int j=0;j<6;j++) {
				pits[i][j]=new Pit();
			}
		}
			for(int j=0;j<=4;j++) {   // The following is to build a doubly LinkedList with setNext() setPrevious()
				pits[0][j].setNext((pits[0])[j+1]);
				pits[0][j+1].setPrevious(pits[0][j]);
			}
			pits[0][5].setNext(this.m1);
			this.m1.setPrevious(pits[0][5]);
			this.m1.setNext(pits[1][0]);
			pits[1][0].setPrevious(this.m1);
			for(int j=0;j<=4;j++) {
				pits[1][j].setNext(pits[1][j+1]);
				pits[1][j+1].setPrevious(pits[1][j]);
			}
			pits[1][5].setNext(this.m2);
			this.m2.setPrevious(pits[1][5]);
			this.m2.setNext(pits[0][0]);
			pits[0][0].setPrevious(this.m2);
			
		
		this.listeners=new ArrayList<ChangeListener>();
}
		
	
	
	public void addChangeListener(ChangeListener l) {
		listeners.add(l);
	}
	
	public void update(Mancala[][] pits, int row, int column) {
		
		Mancala target=pits[row][column];
		int stones=target.getNumber();
		((Pit)(pits[row][column])).removeAllS();//Updates database
		target.getLabel().setText("      "+Integer.toString(0));//Modifies the View
		 target.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
		//Remember to skip the opponent's Mancala
		for(int in=1;in<=stones;in++) {
			target=target.next();
		    if(this.getActivePlayer()==0) {
			if( target!=this.getM2()) {//Get M1() when it's player1's turn
			target.IncrementS();
			
			target.getLabel().setText("     "+Integer.toString(target.getNumber()));
			 target.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
			}
			else {
				in--;
			}}
		    if(this.getActivePlayer()==1) {
		    	if(target!=this.getM1()) {
		    		target.IncrementS();
		    		
					target.getLabel().setText("     "+Integer.toString(target.getNumber()));
					target.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
		    	}
		    	else {
		    		in--;
		    	}
		    }
			
			
		}
		
		finalT=target;
	}
	
	public Mancala[][] getPits(){
		return pits;
	}
	
	public void setNumberOfS(int id, int numberOfS) { //To initialize the number of stones before the game starts
		for(int j=0;j<6;j++) {
			pits[id][j].SetNumber(numberOfS);
		}
	}
	
   public Mancala getM1() {
	   return this.m1;
   }
	
   public Mancala getM2() {
	   return this.m2;
   }
   
   public void AddstoneM1(int added) {
	   this.m1.SetNumber(this.m1.getNumber()+added);
   }
   
   public void AddstoneM2(int added) {
	   this.m2.SetNumber(this.m2.getNumber()+added);
   }
	
	public Mancala getFinalT() {
		return finalT;
	}
	
	public void setLabels() {
		for(int i=0;i<=1;i++) {
			for(int j=0;j<=5;j++) {
				pits[i][j].getLabel().setText("      "+Integer.toString(pits[i][j].getNumber()));
			}
		}
	}
	
	public int getActivePlayer() {
		return this.activePlayer;
	}
	
	public void setActivePlayer(int number) {
		this.activePlayer=number;
	}
	/*
	 * @return buttons
	 */
	public JButton[]  getButtons() {
		return buttons;
	}
	/*
	 * @return ActionListeners
	 */
	public ActionListeners[] getALs() {
	return this.aL;}
	
	public void setIcons() {
		for(int i=0;i<=1;i++) {
			for(int j=0;j<=5;j++) {
				pits[i][j].getIconLabel().repaint();
			}
		}
		
		this.m1.getIconLabel().repaint();
		this.m2.getIconLabel().repaint();
	}
	

	

}
