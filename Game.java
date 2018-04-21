package mancalagame;

import java.util.ArrayList;
import javax.swing.event.ChangeListener;

//Holding all the data in a game
public class Game {
    
	//private Player player1;
	//private Player player2;
	private Mancala[][] pits;
	//private ArrayList<Mancala> pits1;
	//private ArrayList<Mancala> pits2;
	private Mancala m1;
	private Mancala m2;
	private ArrayList<ChangeListener> listeners;
	
	public Game() {
		//this.player1=player1;
		//this.player2=player2;
		//this.pits1=this.player1.getPits();
		//this.pits2=this.player2.getPits();
		pits=new Mancala[3][6];
		this.m1=new Mancala();
		this.m2=new Mancala();
		this.listeners=new ArrayList<ChangeListener>();
		
		
	}
	
	public void addChangeListener(ChangeListener l) {
		listeners.add(l);
	}
	
	public void update(Pit pit, int id) {
		
	}
	
	
	
	
	
}
