/**
 * SJSU Spring 2018 CS 151
 * Team Project Mancala
 * @author Ziqi Yuan, Brandon Zhou, Pratyusha Pogaru
 * @version 1.0
 * @since 04/05/2018
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class MancalaGUITester {

	private static Mancala[][] pits;
	private static Mancala target;
	private static int UndoTimes0;
	private static int UndoTimes1;
	private static ActionListeners[] al;
	private static String inputS;
	private static int inputI;
	private static boolean undo;
	private static boolean undoAllowed;
	private static int stones;
	private static JButton[]  buttons;

	
	public static void main(String[] args) {
		
	     //In order to attach listeners to the buttons in this program, we must create a Game class first and 
		//extract the pit 2D array
	
		Game game=new Game();
		buttons=game.getButtons();
		pits=game.getPits();
		target=null;
		UndoTimes0=0;
		UndoTimes1=0;
	    undo=false;
	    boolean selectStoneN=true;
	    al=game.getALs();
	    undoAllowed=false;
	    
	   
		JFrame frame=new JFrame("Mancala Game");
		frame.setLayout(new BorderLayout());
		JPanel MancalaA=new JPanel();
		
		JPanel MancalaB=new JPanel();
		JPanel other=new JPanel();
		MancalaA.setVisible(false);
		MancalaB.setVisible(false);
		
		other.setLayout(new BorderLayout());
		JButton Undo=new JButton("UNDO");
		Undo.setVisible(false);
		buttons[0]=Undo;//------------------------> Undo button
		other.add(Undo, BorderLayout.NORTH);
		
		frame.setSize(600,400);
		 frame.setVisible(true);
		
		JPanel text=new JPanel();
		BoxLayout textBL=new BoxLayout(text,BoxLayout.X_AXIS);
		text.setLayout(textBL);
		JTextField textF=new JTextField("Please empty this field before you enter any data.");

		JTextArea textA=new JTextArea("How many stones in each pit\n do you want to start with for both players?\n Please"
				+ " only enter 3 or 4. \n Box to the left"
				+ "  takes all user inputs.", 5,5);
		textA.setEditable(false);
		
		JButton colorR=new JButton("REDstyle");
		JButton colorB=new JButton("BLUEstyle");
		text.add(textF);
		text.add(textA);
		text.add(colorR);
		text.add(colorB);
		other.add(text, BorderLayout.CENTER);
		
		textF.setVisible(false);
		textA.setVisible(false);
		
		colorR.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorB.setVisible(false);
				colorR.setVisible(false);
				MancalaA.setVisible(true);
				MancalaB.setVisible(true);
				Undo.setVisible(true);
				textF.setVisible(true);
				textA.setVisible(true);
			}
			
		});
		
		colorB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorB.setVisible(false);
				colorR.setVisible(false);
				MancalaA.setVisible(true);
				MancalaB.setVisible(true);
				Undo.setVisible(true);
				textF.setVisible(true);
				textA.setVisible(true);
			}
		});
		
		//frame contains two JPanels
		frame.add(MancalaA, BorderLayout.NORTH);
		frame.add(MancalaB, BorderLayout.CENTER);
		frame.add(other, BorderLayout.SOUTH);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//For each JPanel MancalaA and Mancala B, set BoxLayout
	    BoxLayout boxLA=new BoxLayout(MancalaA, BoxLayout.X_AXIS);
		MancalaA.setLayout(boxLA);
		BoxLayout boxLB=new BoxLayout(MancalaB, BoxLayout.X_AXIS);
		MancalaB.setLayout(boxLB);
		
		//For JPanel MancalaA, add 7 JPanels holding 6 pits and 1 Mancala, and set BoxLayout Vertical for each of them
		JPanel bigMancalaA=new JPanel();
		bigMancalaA.setLayout(new BoxLayout(bigMancalaA, BoxLayout.Y_AXIS));
		JPanel pitA1=new JPanel();
		pitA1.setLayout(new BoxLayout(pitA1, BoxLayout.Y_AXIS));
		JPanel pitA2=new JPanel();
		pitA2.setLayout(new BoxLayout(pitA2, BoxLayout.Y_AXIS));
		JPanel pitA3=new JPanel();
		pitA3.setLayout(new BoxLayout(pitA3, BoxLayout.Y_AXIS));
		JPanel pitA4=new JPanel();
		pitA4.setLayout(new BoxLayout(pitA4, BoxLayout.Y_AXIS));
		JPanel pitA5=new JPanel();
		pitA5.setLayout(new BoxLayout(pitA5, BoxLayout.Y_AXIS));
		JPanel pitA6=new JPanel();
		pitA6.setLayout(new BoxLayout(pitA6, BoxLayout.Y_AXIS));
		//Add JButtons and JLabels for each JPanel
		//First, for bigMancalaA
		JLabel bigMA=new JLabel("Mancala A");
		bigMA.setSize(50,100);
		
		JLabel  bigMAnumberOfS=new JLabel();
		game.getM1().setLabel(bigMAnumberOfS);
		bigMAnumberOfS.setText("     "+Integer.toString(0));
		bigMancalaA.add(bigMA);
		bigMancalaA.add(bigMAnumberOfS);
		//Second, for pitA1
		JButton A1button=new JButton("A1");
		buttons[1]=A1button;
		JLabel pitA1numberOfS=new JLabel();
	    pits[0][0].setLabel(pitA1numberOfS);
		al[0]=new ActionListeners(pits,0,0,game,0,textA);
		A1button.addActionListener(al[0]);//----------> ActionListener added for A1button here, 
		
		pitA1.add(A1button);
		pitA1.add(pitA1numberOfS);
		//Third, for pit A2
		JButton A2button=new JButton("A2");
		buttons[2]=A2button;
		JLabel pitA2numberOfS=new JLabel();
		pits[0][1].setLabel(pitA2numberOfS);
		
        //----------------------------------------------------->Please add a listener for buttonA2, it is corresponded to pits[0][1] 
		al[1]=new ActionListeners(pits,0,1,game,0,textA);
		A2button.addActionListener(al[1]);
		pitA2.add(A2button);
		pitA2.add(pitA2numberOfS);
		//Fourth,for pitA3
		JLabel pitA3numberOfS=new JLabel();
		pits[0][2].setLabel(pitA3numberOfS);
		JButton A3button=new JButton("A3");//-------------------->Button!
		buttons[3]=A3button;
		al[2]=new ActionListeners(pits,0,2,game,0,textA);
		A3button.addActionListener(al[2]);
		pitA3.add(A3button);
		pitA3.add(pitA3numberOfS);
		//Fifth, for pitA4
		JLabel pitA4numberOfS=new JLabel();
		pits[0][3].setLabel(pitA4numberOfS);

		JButton A4button=new JButton("A4");//----------------------> Button!
		buttons[4]=A4button;
		al[3]=new ActionListeners(pits,0,3,game,0,textA);
		A4button.addActionListener(al[3]);
		pitA4.add(A4button);
		pitA4.add(pitA4numberOfS);
		//Sixth, for pitA5
		JLabel pitA5numberOfS=new JLabel();
		pits[0][4].setLabel(pitA5numberOfS);

		JButton A5button=new JButton("A5");//------------------------> Button!
		buttons[5]=A5button;
		al[4]=new ActionListeners(pits,0,4,game,0,textA);
		A5button.addActionListener(al[4]);
		pitA5.add(A5button);
		pitA5.add(pitA5numberOfS);
		//Seventh, for pitA6
		JLabel pitA6numberOfS=new JLabel();
		pits[0][5].setLabel(pitA6numberOfS);

		JButton A6button=new JButton("A6");//------------------------> Button!
		buttons[6]=A6button;
		al[5]=new ActionListeners(pits,0,5,game,0,textA);
		A6button.addActionListener(al[5]);
		pitA6.add(A6button);
		pitA6.add(pitA6numberOfS);
		//Add all 7 JPanels to MancalaA
		MancalaA.add(bigMancalaA);
		MancalaA.add(pitA6);
		MancalaA.add(pitA5);
		MancalaA.add(pitA4);
		MancalaA.add(pitA3);
		MancalaA.add(pitA2);
		MancalaA.add(pitA1);
		
		
		//For JPanelMancalaB
		JPanel bigMancalaB=new JPanel();
		bigMancalaB.setLayout(new BoxLayout(bigMancalaB, BoxLayout.Y_AXIS));
		JPanel pitB1=new JPanel();
		pitB1.setLayout(new BoxLayout(pitB1, BoxLayout.Y_AXIS));
		JPanel pitB2=new JPanel();
		pitB2.setLayout(new BoxLayout(pitB2, BoxLayout.Y_AXIS));
		JPanel pitB3=new JPanel();
		pitB3.setLayout(new BoxLayout(pitB3, BoxLayout.Y_AXIS));
		JPanel pitB4=new JPanel();
		pitB4.setLayout(new BoxLayout(pitB4, BoxLayout.Y_AXIS));
		JPanel pitB5=new JPanel();
		pitB5.setLayout(new BoxLayout(pitB5, BoxLayout.Y_AXIS));
		JPanel pitB6=new JPanel();
		pitB6.setLayout(new BoxLayout(pitB6, BoxLayout.Y_AXIS));
		//Add JButtons and JLabels for each JPanel
		//First, for bigMancalaB
		JLabel bigMB=new JLabel("Mancala B");
		JLabel  bigMBnumberOfS=new JLabel();
		game.getM2().setLabel(bigMBnumberOfS);
		bigMBnumberOfS.setText("     "+Integer.toString(0));
				bigMancalaB.add(bigMB);
				bigMancalaB.add(bigMBnumberOfS);
				//Second, for pitB1
				JLabel pitB1numberOfS=new JLabel();
				pits[1][0].setLabel(pitB1numberOfS);
		
				JButton B1button=new JButton("B1");//------------------->Button!
				buttons[7]=B1button;
				al[6]=new ActionListeners(pits,1,0,game,1,textA);
				B1button.addActionListener(al[6]);
				pitB1.add(B1button);
				pitB1.add(pitB1numberOfS);
				//Third, for pit B2
				JLabel pitB2numberOfS=new JLabel();
				pits[1][1].setLabel(pitB2numberOfS);
				JButton B2button=new JButton("B2");//----------------------> Button!
				buttons[8]=B2button;
				al[7]=new ActionListeners(pits,1,1,game,1,textA);
				B2button.addActionListener(al[7]);
				pitB2.add(B2button);
				pitB2.add(pitB2numberOfS);
				//Fourth,for pitB3
				JLabel pitB3numberOfS=new JLabel();
				pits[1][2].setLabel(pitB3numberOfS);

				JButton B3button=new JButton("B3");//------------------------> Button!
				buttons[9]=B3button;
				al[8]=new ActionListeners(pits,1,2,game,1,textA);
				B3button.addActionListener(al[8]);
				pitB3.add(B3button);
				pitB3.add(pitB3numberOfS);
				//Fifth, for pitB4
				JLabel pitB4numberOfS=new JLabel();
				pits[1][3].setLabel(pitB4numberOfS);

				JButton B4button=new JButton("B4");//-------------------------> Button!
				buttons[10]=B4button;
				al[9]=new ActionListeners(pits,1,3,game,1,textA);
				B4button.addActionListener(al[9]);
				pitB4.add(B4button);
				pitB4.add(pitB4numberOfS);
				//Sixth, for pitB5
				JLabel pitB5numberOfS=new JLabel();
				pits[1][4].setLabel(pitB5numberOfS);

				JButton B5button=new JButton("B5");//--------------------------> Button!
				buttons[11]=B5button;
				al[10]=new ActionListeners(pits,1,4,game,1,textA);
				B5button.addActionListener(al[10]);
				pitB5.add(B5button);
				pitB5.add(pitB5numberOfS);
				//Seventh, for pitB6
				JLabel pitB6numberOfS=new JLabel();
				pits[1][5].setLabel(pitB6numberOfS);

				JButton B6button=new JButton("B6");//-------------------------->Button!
				buttons[12]=B6button;
				al[11]=new ActionListeners(pits,1,5,game,1,textA);
				B6button.addActionListener(al[11]);
				pitB6.add(B6button);
				pitB6.add(pitB6numberOfS);
				//Add all 7 JPanels to MancalaB
				
				MancalaB.add(pitB1);
				MancalaB.add(pitB2);
				MancalaB.add(pitB3);
				MancalaB.add(pitB4);
				MancalaB.add(pitB5);
				MancalaB.add(pitB6);
				MancalaB.add(bigMancalaB);
				
				
			
				Undo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Mancala currentM=null;
						undoAllowed=false;
			             boolean case3=false;
			             boolean case1=false;
			             int column=0;
			             int forcase1=0;
			         
			            
			             for(ActionListeners aLs:game.getALs()) {
			            	 
			            	 if(aLs.getlastPitVisited()!=null) {
			            		// System.out.println("this als is not null");
			            		stones=aLs.getStones();
			            		//System.out.println(stones);
			            		currentM=aLs.getlastPitVisited();
			            		undoAllowed=true;
			            		case3=aLs.getCase3();
			            		case1=aLs.getCase1();
			            		column=aLs.getColumnforCase1();
			            		forcase1=aLs.getIntforCase1();
			            	 }
			             }
						 game.setActivePlayer((game.getActivePlayer()+1)%2);
						
						 if(case3) {
							 game.setActivePlayer((game.getActivePlayer()+1)%2);
						 }
						
						if(game.getActivePlayer()==0) {
							
							  if(UndoTimes0>=3) {
								textA.setText("Sorry, you are either running out of chances\n to undo your selection or \n you are"
										+ " not allowed to do a row of\n multiple undos");
								 game.setActivePlayer((game.getActivePlayer()+1)%2);
								
							    }
							  else if(!undoAllowed) {
								  textA.setText("Sorry, you are either running out of chances\n to undo your selection or \n you are"
											+ " not allowed to do a row of\n multiple undos");
								  game.setActivePlayer((game.getActivePlayer()+1)%2);
									
							  }
							  
							  else if(case1) {//------------->If case1 is true, do the following
								  game.getM1().SetNumber(game.getM1().getNumber()-(1+forcase1));
								  game.getM1().getLabel().setText("      "+Integer.toString(game.getM1().getNumber()));
								  pits[1][5-column].SetNumber(forcase1);
								  pits[1][5-column].getLabel().setText("      "+Integer.toString(pits[1][5-column].getNumber()));
								  currentM.SetNumber(1);
								  currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
								  
						
								  for(int i=1;i<=stones;i++) {
									  if(currentM!=game.getM2()) {
										  currentM.decrementS();
										  currentM.getLabel().setText("       "+Integer.toString(currentM.getNumber()));
									  }
									  else {
										  i--;}
									  currentM=currentM.prev();
								  }
								  
								  currentM.SetNumber(stones);
									currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
									textA.setText("Successful undo! \n Please re-select a pit.");
							  }
						
						else{UndoTimes0++;
						for(int i=1;i<=stones;i++) {
							//Remember to skip the opponent's Mancala
							if(currentM!=game.getM2()) {
								currentM.decrementS();
								currentM.getLabel().setText("     "+Integer.toString(currentM.getNumber()));}
							else {
								i--;}
						        currentM=currentM.prev();
						 
						
						}
						
						currentM.SetNumber(stones);
						currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
						textA.setText("Successful undo! \n Please re-select a pit.");}
						}
						
						else {
							if(UndoTimes1>=3 ) {
								textA.setText("Sorry, you are either running out of chances\n to undo your selection or \n you are"
										+ " not allowed to do a row of\n multiple undos");
								 game.setActivePlayer((game.getActivePlayer()+1)%2);
							}
							else if(!undoAllowed) {
								textA.setText("Sorry, you are either running out of chances\n to undo your selection or \n you are"
										+ " not allowed to do a row of\n multiple undos");
								 game.setActivePlayer((game.getActivePlayer()+1)%2);
							}
							else if(case1) {//------------->If case1 is true, do the following
								  game.getM2().SetNumber(game.getM2().getNumber()-(1+forcase1));
								  game.getM2().getLabel().setText("      "+Integer.toString(game.getM2().getNumber()));
								  pits[0][5-column].SetNumber(forcase1);
								  pits[0][5-column].getLabel().setText("      "+Integer.toString(pits[0][5-column].getNumber()));
								  currentM.SetNumber(1);
								  currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
								  
							
								  for(int i=1;i<=stones;i++) {
									  if(currentM!=game.getM1()) {
										  currentM.decrementS();
										  currentM.getLabel().setText("       "+Integer.toString(currentM.getNumber()));
									  }
									  else {
										  i--;}
									  currentM=currentM.prev();
								  }
								  
								  currentM.SetNumber(stones);
									currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
									textA.setText("Successful undo! \n Please re-select a pit.");
							  }
						
						else{UndoTimes1++;
						for(int i=1;i<=stones;i++) {
							//Remember to skip the opponent's Mancala
							//System.out.println(currentM.getNumber());
							if(currentM!=game.getM1()) {
								currentM.decrementS();
								currentM.getLabel().setText("     "+Integer.toString(currentM.getNumber()));}
							else {
								i--;}
						        currentM=currentM.prev();
						     
						
						}
				
						
						currentM.SetNumber(stones);
						currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
							textA.setText("Successful undo! \n Please re-select a pit.");	}
							
						}
						
					}
				});
				
				
				textF.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JTextField tF=(JTextField)e.getSource();
						try {
							Integer.parseInt(tF.getText());
						}
						catch(NumberFormatException exception) {
						inputS=tF.getText();
						textF.setEditable(false);
						textA.setText("Game begins!\n PlayerA goes first and take turns, please.");
					}
						
						inputI=Integer.parseInt(tF.getText());
					}
					
				});
				
            //////////////////////////////////////////All GUI and listeners are complete\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
				selectStoneN=true;
			    while(selectStoneN) {
			     
			    if(inputI!=3 && inputI!=4) {
			   	 textA.setText("Please enter 3 or 4 stones \n for each pit to start,\n  no reselection is allowed.\n Box to the left takes all user inputs.\n"
			   	 	);}
			    else {
			   	 game.setNumberOfS(0, inputI);
			   	 game.setNumberOfS(1, inputI);
			     game.setLabels();
			     textA.setText("Please select a boardStyle now");
			     selectStoneN=false;
			    }
			
			    }
                
            
                               


				 
			
	}
	
}
