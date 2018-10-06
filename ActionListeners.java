/**
 * SJSU Spring 2018 CS 151
 * Team Project Mancala
 * @author Ziqi Yuan
 * @version 1.0
 * @since 04/05/2018
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ActionListeners implements ActionListener {

	private Mancala[][] pits;
	private int row;
	private int column;
	//private Mancala target;
	private Game game;
	private Mancala lastPitVisited;
	private int id;
	private JTextArea tA;
	//private JLabel label;
	private boolean Case1;
	private boolean Case3;
	private int stones;
	private int forcase1;
	private int columnforCase1;
	public ActionListeners(Mancala[][] pits, int row, int column, Game game, int id, JTextArea tA) {
		
		this.pits=game.getPits();
		this.row=row;
		this.column=column;
		this.game=game;
		lastPitVisited=null;
		this.id=id;
		this.tA=tA;
		Case1=false;
		Case3=false;
		//this.label=label;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(pits[row][column].getNumber()==0) { //Check if this is an empty pit before updating the Model
			tA.setText("Sorry, this is an empty pit, please reselect.");
		}
		else if(game.getActivePlayer()==this.id) {
			for(ActionListeners aLs: game.getALs()) {
				if(aLs.getlastPitVisited()!=null) {
					aLs.ResetLastPitVisited();
					aLs.ResetCase1();
					aLs.ResetCase3();
				}
			}
			this.stones=pits[row][column].getNumber();
			this.columnforCase1=(this.column+this.stones)%6;
			//System.out.println(this.stones);
		game.update(pits, row, column);
		this.lastPitVisited=game.getFinalT();
		this.tA.setText("");
		 
		 boolean checkLastCase=true;
		 boolean noWin=true;
		 //Check Case I
	       	if(this.lastPitVisited.getNumber()==1) {
    			Mancala currentM=this.lastPitVisited;
    			boolean onPlayerSide=false;
    			boolean dstop=true;
    			//int count=0;
    		    while(!onPlayerSide && dstop) {
    		    	currentM=currentM.next();
    		    	if(currentM==game.getM1()) {
    		    		if(game.getActivePlayer()==0) {
    		    			onPlayerSide=true;
    		    		}
    		    		else {
    		    			dstop=false;
    		    		}
    		    	}
    		    	if(currentM==game.getM2()) {
    		    		if(game.getActivePlayer()==1) {
    		    			onPlayerSide=true;
    		    			 //currentM.SetNumber(0);
    		    			 //currentM.getLabel().setText(Integer.toString(0));
    		    			
    		    		}
    		    		else {
    		    			dstop=false;
    		    		}
    		    	}
    		    	//count++;
    		    	
    		    }
    		    
    		    if(onPlayerSide) {
    		    	 this.Case1=true;
    		    	checkLastCase=false;
    			/*for(int i=0;i<=count;i++) {
    				currentM=currentM.next();
    			}*/
    		    if(game.getActivePlayer()==0) {
    		    	//this.forcase1=currentM.getNumber();
    		    	this.forcase1=pits[1][5-this.columnforCase1].getNumber();
    			//game.AddstoneM1(1+currentM.getNumber());
    		    	game.AddstoneM1(1+this.forcase1);
    			game.getM1().getLabel().setText("      "+Integer.toString(game.getM1().getNumber()));
    			game.getM1().getIconLabel().repaint();//-------------------------------------------------> Repaint Icon
    			pits[1][5-this.columnforCase1].SetNumber(0);
    			pits[1][5-this.columnforCase1].getLabel().setText("      "+Integer.toString(0));;
    			 pits[1][5-this.columnforCase1].getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
    			}//Assuming player0 is playing the game
    		    if(game.getActivePlayer()==1) {
    		    	//this.forcase1=currentM.getNumber();
    		    	this.forcase1=pits[0][5-this.columnforCase1].getNumber();
    		    	//game.AddstoneM2(1+currentM.getNumber());
    		    	game.AddstoneM2(1+this.forcase1);
    		    	game.getM2().getLabel().setText("      "+Integer.toString(game.getM2().getNumber()));
    		    	 game.getM2().getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
    		    	
    		    	pits[0][5-this.columnforCase1].SetNumber(0);
    		    	pits[0][5-this.columnforCase1].getLabel().setText("      "+Integer.toString(0));
    		    	pits[0][5-this.columnforCase1].getIconLabel().repaint();//-------------------> Repaint Icon
    		    }
    			//currentM.SetNumber(0);
    			//currentM.getLabel().setText(Integer.toString(0));
    			this.lastPitVisited.SetNumber(0);
		    	this.lastPitVisited.getLabel().setText("      "+Integer.toString(0));
		    	this.lastPitVisited.getIconLabel().repaint();//------------------------Reapaint Icon
    		
    		}
    		    else {checkLastCase=true;}
    		
    	
          }
	       	//Check CaseII(If the game is over)
	       	int sum0=0;
        	int sum1=0;
        	for(int j=0;j<6;j++) {
        		sum0=sum0+pits[0][j].getNumber();
        	}
        	
        	for(int j=0;j<6;j++) {
        		sum1=sum1+pits[1][j].getNumber();
        	}
        	//if(sum0==0), loop through player1's pits, add all stones to pits[2][1] Mancala, sum1=pits[2][1].getNumber()
        	if(sum0==0) {
        		game.AddstoneM2(sum1);
        		game.getM2().getLabel().setText("      "+Integer.toString(game.getM2().getNumber()));
        		game.getM2().getIconLabel().repaint();//---------------------------------->Repaint icon
        		game.setNumberOfS(1, 0);
        		game.setIcons();
        		game.setLabels();
        	}
        	else if(sum1==0) {
        		game.AddstoneM1(sum0);
        		game.getM1().getLabel().setText("      "+Integer.toString(game.getM1().getNumber()));
        		game.getM1().getIconLabel().repaint();//---------------------------------->Repaint icon

        		game.setNumberOfS(0, 0);
        		game.setIcons();
        		game.setLabels();
        	}
        	if(sum0==0 || sum1==0) {
        		if(game.getM1().getNumber()>game.getM2().getNumber()) {
        			tA.setText("PlayerA won! Game ends.");
        		}
        		else if(game.getM2().getNumber()> game.getM1().getNumber()) {
        			tA.setText("PlayerB won! Game ends.");
        		}
        		else {
        			tA.setText("Both players won! Game ends.");
        		}
        		for(JButton b: game.getButtons()) {
        		b.setEnabled(false);}
  
        		noWin=false;
        	}//CaseII check ends here
        	//Check CaseIII
        	if(noWin && checkLastCase) {//Check this last case if no winner yet
        		//Check if the last stone was dropped into the active palyer's Mancala, if yes, offer free turn
        			if(game.getActivePlayer()==0 && this.lastPitVisited==game.getM1()) {
        				this.Case3=true;
        				game.setActivePlayer((game.getActivePlayer()-1)%2);
        				tA.setText("PlayerA gets a free turn.");
        			}
        			if(game.getActivePlayer()==1 && this.lastPitVisited==game.getM2()) {
        				this.Case3=true;
        				game.setActivePlayer((game.getActivePlayer()-1)%2);
        				tA.setText("PlayerB gets a free turn.");
        			}
        		
        			 
        		
        		
        	 
        	     
        	      
        		
        		}///CaseIII check ends here
        	game.setActivePlayer((game.getActivePlayer()+1)%2);
        	
        	/*if(this.lastPitVisited!=null) {
        		System.out.println("lastPitVisited is not null");
        	} //Only for debug purposes
        	else {
        		System.out.println("lastPitVisted is null");
        	}//Only for debug purposes*/
		 
		 }//If "this is your turn" section ends
		else {
			this.tA.setText("Sorry, it's not your turn");
		}
		
	}
	
	public Mancala getlastPitVisited() {
		return this.lastPitVisited;
	}
	
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void ResetLastPitVisited() {
		this.lastPitVisited=null;
	}
	
	public boolean getCase1() {
		return this.Case1;
	}
	
	public void ResetCase1() {
		this.Case1=false;
	}
	
	public boolean getCase3() {
		return this.Case3;
	}
	
	public void ResetCase3() {
		this.Case3=false;
	}
	
	public int getStones() {
		return this.stones;
	}
	
	public int getColumnforCase1() {
		return this.columnforCase1;
	}
	
	public int getIntforCase1() {
		return this.forcase1;
	}
		
	}


