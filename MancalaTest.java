/** SJSU Spring 2018 CS 151
 * Team Project Mancala
 * @author Ziqi Yuan, Brandon Zhou, Pratyusha Pogaru
 * @version 1.0
 * @since 04/05/2018
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class MancalaTest {

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
	private static JLabel[] iconLabels;

	
	public static void main(String[] args) {
		
	     //In order to attach listeners to the buttons in this program, we must create a Game class first and 
		//extract the pit 2D array
	
		Game game=new Game();
		iconLabels=new JLabel[14];
		for(int i=0;i<iconLabels.length;i++) {
			iconLabels[i]=new JLabel();
		}
	
	
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
		
		frame.setSize(800,600);
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
				for(int i=0;i<=13;i++) {
					if(i==6) {
						iconLabels[i].setIcon(new StyleRIcon(game.getM1()));
					}
					else if(i==13) {
						iconLabels[i].setIcon(new StyleRIcon(game.getM2()));
					}
					else if(i>5) {
						iconLabels[i].setIcon(new StyleRIcon(pits[1][i-7]));
					}
					else {
						iconLabels[i].setIcon(new StyleRIcon(pits[0][i]));
					}
				}
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
				
				for(int i=0;i<=13;i++) {
					if(i==6) {
						iconLabels[i].setIcon(new StyleBIcon(game.getM1()));
					}
					else if(i==13) {
						iconLabels[i].setIcon(new StyleBIcon(game.getM2()));
					}
					else if(i>5) {
						iconLabels[i].setIcon(new StyleBIcon(pits[1][i-7]));
					}
					else {
						iconLabels[i].setIcon(new StyleBIcon(pits[0][i]));
					}
				}
				
				
				
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
		
		bigMancalaA.add(iconLabels[6]);//----------------------> add iconLabel
		game.getM1().setIcon(iconLabels[6]);
		
		//Second, for pitA1
		JButton A1button=new JButton("A1");
		buttons[1]=A1button;
		JLabel pitA1numberOfS=new JLabel();
	    pits[0][0].setLabel(pitA1numberOfS);
		al[0]=new ActionListeners(pits,0,0,game,0,textA);
		A1button.addActionListener(al[0]);//----------> ActionListener added for A1button here, 
		
		pitA1.add(A1button);
		pitA1.add(pitA1numberOfS);
		
		pitA1.add(iconLabels[0]);//---------------------------> add iconLabel
		pits[0][0].setIcon(iconLabels[0]);
		
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
		
		pitA2.add(iconLabels[1]);
		pits[0][1].setIcon(iconLabels[1]);//--------------------->add iconLabel
		
		//Fourth,for pitA3
		JLabel pitA3numberOfS=new JLabel();
		pits[0][2].setLabel(pitA3numberOfS);
		JButton A3button=new JButton("A3");//-------------------->Button!
		buttons[3]=A3button;
		al[2]=new ActionListeners(pits,0,2,game,0,textA);
		A3button.addActionListener(al[2]);
		pitA3.add(A3button);
		pitA3.add(pitA3numberOfS);
		
		pitA3.add(iconLabels[2]);//---------------------->add iconLabel
		pits[0][2].setIcon(iconLabels[2]);
		
		//Fifth, for pitA4
		JLabel pitA4numberOfS=new JLabel();
		pits[0][3].setLabel(pitA4numberOfS);

		JButton A4button=new JButton("A4");//----------------------> Button!
		buttons[4]=A4button;
		al[3]=new ActionListeners(pits,0,3,game,0,textA);
		A4button.addActionListener(al[3]);
		pitA4.add(A4button);
		pitA4.add(pitA4numberOfS);
		
		pitA4.add(iconLabels[3]);//--------------------------> add iconLabel
		pits[0][3].setIcon(iconLabels[3]);
		
		//Sixth, for pitA5
		JLabel pitA5numberOfS=new JLabel();
		pits[0][4].setLabel(pitA5numberOfS);

		JButton A5button=new JButton("A5");//------------------------> Button!
		buttons[5]=A5button;
		al[4]=new ActionListeners(pits,0,4,game,0,textA);
		A5button.addActionListener(al[4]);
		pitA5.add(A5button);
		pitA5.add(pitA5numberOfS);
		
		pitA5.add(iconLabels[4]);//-------------------->add iconLabel
		pits[0][4].setIcon(iconLabels[4]);
		
		//Seventh, for pitA6
		JLabel pitA6numberOfS=new JLabel();
		pits[0][5].setLabel(pitA6numberOfS);

		JButton A6button=new JButton("A6");//------------------------> Button!
		buttons[6]=A6button;
		al[5]=new ActionListeners(pits,0,5,game,0,textA);
		A6button.addActionListener(al[5]);
		pitA6.add(A6button);
		pitA6.add(pitA6numberOfS);
		
		pitA6.add(iconLabels[5]);
		pits[0][5].setIcon(iconLabels[5]);//---------------------->add iconLabel
		
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
				
				bigMancalaB.add(iconLabels[13]);
				game.getM2().setIcon(iconLabels[13]);//----------------> add iconLabel
				
				//Second, for pitB1
				JLabel pitB1numberOfS=new JLabel();
				pits[1][0].setLabel(pitB1numberOfS);
		
				JButton B1button=new JButton("B1");//------------------->Button!
				buttons[7]=B1button;
				al[6]=new ActionListeners(pits,1,0,game,1,textA);
				B1button.addActionListener(al[6]);
				pitB1.add(B1button);
				pitB1.add(pitB1numberOfS);
				
				pitB1.add(iconLabels[7]);//-----------------> add iconLabel
				pits[1][0].setIcon(iconLabels[7]);
				
				//Third, for pit B2
				JLabel pitB2numberOfS=new JLabel();
				pits[1][1].setLabel(pitB2numberOfS);
				JButton B2button=new JButton("B2");//----------------------> Button!
				buttons[8]=B2button;
				al[7]=new ActionListeners(pits,1,1,game,1,textA);
				B2button.addActionListener(al[7]);
				pitB2.add(B2button);
				pitB2.add(pitB2numberOfS);
				
				pitB2.add(iconLabels[8]);//-------------------> add iconLabel
				pits[1][1].setIcon(iconLabels[8]);
				
				//Fourth,for pitB3
				JLabel pitB3numberOfS=new JLabel();
				pits[1][2].setLabel(pitB3numberOfS);

				JButton B3button=new JButton("B3");//------------------------> Button!
				buttons[9]=B3button;
				al[8]=new ActionListeners(pits,1,2,game,1,textA);
				B3button.addActionListener(al[8]);
				pitB3.add(B3button);
				pitB3.add(pitB3numberOfS);
				
				pitB3.add(iconLabels[9]);///--------------------------> add iconLabel
				pits[1][2].setIcon(iconLabels[9]);
				
				//Fifth, for pitB4
				JLabel pitB4numberOfS=new JLabel();
				pits[1][3].setLabel(pitB4numberOfS);

				JButton B4button=new JButton("B4");//-------------------------> Button!
				buttons[10]=B4button;
				al[9]=new ActionListeners(pits,1,3,game,1,textA);
				B4button.addActionListener(al[9]);
				pitB4.add(B4button);
				pitB4.add(pitB4numberOfS);
				
				pitB4.add(iconLabels[10]);
				pits[1][3].setIcon(iconLabels[10]);//------------------>add iconLabel
				
				//Sixth, for pitB5
				JLabel pitB5numberOfS=new JLabel();
				pits[1][4].setLabel(pitB5numberOfS);

				JButton B5button=new JButton("B5");//--------------------------> Button!
				buttons[11]=B5button;
				al[10]=new ActionListeners(pits,1,4,game,1,textA);
				B5button.addActionListener(al[10]);
				pitB5.add(B5button);
				pitB5.add(pitB5numberOfS);
				
				pitB5.add(iconLabels[11]);
				pits[1][4].setIcon(iconLabels[11]);//----------------------->add iconLabel
				
				//Seventh, for pitB6
				JLabel pitB6numberOfS=new JLabel();
				pits[1][5].setLabel(pitB6numberOfS);

				JButton B6button=new JButton("B6");//-------------------------->Button!
				buttons[12]=B6button;
				al[11]=new ActionListeners(pits,1,5,game,1,textA);
				B6button.addActionListener(al[11]);
				pitB6.add(B6button);
				pitB6.add(pitB6numberOfS);
				
				pitB6.add(iconLabels[12]);
				pits[1][5].setIcon(iconLabels[12]);
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
			            		aLs.ResetLastPitVisited();
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
								  game.getM1().getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
								  pits[1][5-column].SetNumber(forcase1);
								  pits[1][5-column].getLabel().setText("      "+Integer.toString(pits[1][5-column].getNumber()));
								  pits[1][5-column].getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
								  currentM.SetNumber(1);
								  currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
								  currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
						
								  for(int i=1;i<=stones;i++) {
									  if(currentM!=game.getM2()) {
										  currentM.decrementS();
										  currentM.getLabel().setText("       "+Integer.toString(currentM.getNumber()));
										  currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
									  }
									  else {
										  i--;}
									  currentM=currentM.prev();
								  }
								  
								  currentM.SetNumber(stones);
									currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
									 currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
									textA.setText("Successful undo! \n Please re-select a pit.");
							  }
						
						else{UndoTimes0++;
						for(int i=1;i<=stones;i++) {
							//Remember to skip the opponent's Mancala
							if(currentM!=game.getM2()) {
								currentM.decrementS();
								currentM.getLabel().setText("     "+Integer.toString(currentM.getNumber()));
								 currentM.getIconLabel().repaint();}//---------------------------------------------------->Repaint Icon}
							else {
								i--;}
						        currentM=currentM.prev();
						 
						
						}
						
						currentM.SetNumber(stones);
						currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
						 currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
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
								  game.getM2().getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
								  
								  pits[0][5-column].SetNumber(forcase1);
								  pits[0][5-column].getLabel().setText("      "+Integer.toString(pits[0][5-column].getNumber()));
								  pits[0][5-column].getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
								  currentM.SetNumber(1);
								  currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
								  currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
								  
							
								  for(int i=1;i<=stones;i++) {
									  if(currentM!=game.getM1()) {
										  currentM.decrementS();
										  currentM.getLabel().setText("       "+Integer.toString(currentM.getNumber()));
										  currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
									  }
									  else {
										  i--;}
									  currentM=currentM.prev();
								  }
								  
								  currentM.SetNumber(stones);
									currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
									 currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
									textA.setText("Successful undo! \n Please re-select a pit.");
							  }
						
						else{UndoTimes1++;
						for(int i=1;i<=stones;i++) {
							//Remember to skip the opponent's Mancala
							//System.out.println(currentM.getNumber());
							if(currentM!=game.getM1()) {
								currentM.decrementS();
								currentM.getLabel().setText("     "+Integer.toString(currentM.getNumber()));
								 currentM.getIconLabel().repaint();}//---------------------------------------------------->Repaint Icon}
							else {
								i--;}
						        currentM=currentM.prev();
						     
						
						}
				
						
						currentM.SetNumber(stones);
						currentM.getLabel().setText("      "+Integer.toString(currentM.getNumber()));
						 currentM.getIconLabel().repaint();//---------------------------------------------------->Repaint Icon
							textA.setText("Successful undo! \n Please re-select a pit.");	}
							
						}
						
					}
				});
				
				
				textF.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JTextField tF=(JTextField)e.getSource();
					
						
						inputI=Integer.parseInt(tF.getText());
						textF.setEditable(false);
						textA.setText("Game begins!\n PlayerA goes first and take turns, please.");
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
			     game.setIcons();
			     selectStoneN=false;
			    }
			
			    }
                
            
                               


				 
			
	}
	
}

/*import java.util.Scanner;

import javax.swing.JTextField;

//This is the View and Controller

public class MancalaTest {//Use a while loop for user swap

	
	private static Mancala[][] pits;
	private static Game game;
	private static int UndoTimes0;
	private static int UndoTimes1;
	private static Scanner sc;
	private static JTextField Jtext;
	
	public static void main(String[] args) {
	sc=new Scanner(System.in);
    game=new Game();
    Jtext=new JTextField();
	pits=game.getPits();
	UndoTimes0=0;
	UndoTimes1=0;
	boolean undo=false;
	boolean noWin=true;
	
	
	System.out.println("How many stones in each pit do you want to start with for both players? Please enter 3 or 4.");
    int input=0;
    boolean selectStoneN=true;
    while(selectStoneN) {
      input=Integer.parseInt(sc.nextLine());
    if(input!=3 && input !=4) {
   	 System.out.println("Please re-select a valid number of stones.");}
    else {
   	 game.setNumberOfS(0, input);
   	 game.setNumberOfS(1, input);
   	 selectStoneN=false;
    }
    }
    
    System.out.println("Please select a board style");
    //Take user input, use LayoutManager Strategy pattern.
    //work on this part later when we add the GUI
	
	//BorderLayout or FlowLayout
	//if BorderLayout is selected, set Panels EAST,WEST,CENTER
	//CENTER panel should do GridLayout
	//EAST panel takes one MancalaIcon
	//West panel takes one MancalaIcon
    
    System.out.println("If you go first and select a pit in the first row, you would be Player0, or if you select a pit in the second row, you will be Player1.");
    
    //Assume the first player to go is Player0;
	int playerID=0;
    boolean keepGo=true;

	
	while(keepGo) {
	    playerID=playerID%2;
        undo=false;
	    //Assume the Player0 selects pit[0][0], do the following inside the actionPerformed()
	    //Depends on which pit/button the player clicks
	int row=0;int column=0;
	
	Mancala target=pits[row][column]; //Set the target pit to the pit after the selected pit
	int stones=target.getNumber();
	((Pit)(pits[row][column])).removeAllS();
	//Remember to skip the opponent's Mancala
	for(int in=1;in<=stones;in++) {
		target=target.next();
	
		if( target!=game.getM1()) {//Get M2() when it's player1's turn
		target.IncrementS();}
		else {
			in--;
		}
		
		
	}
	
	//If Player0 press UNDO
	//If UndoTimes0>3, not allowed to undo
	//If not, allow undo
	//Do the following undo check inside the actionPerformed()
	
	if(playerID==0) {
		if(UndoTimes0>=3) {
			System.out.println("Sorry, you are running out of chances to undo your selection");
	
		}
	}
	else{UndoTimes0++;
	for(int i=1;i<=stones;i++) {
		//Remember to skip the opponent's Mancala
		if(target!=game.getM1()) {
			target.decrementS();}
		else {
			i--;}
	        target=target.prev();
	
	
	}
	undo=true;
	target.SetNumber(stones);}
	
	if(!undo) {// If the player didn't undo, check the following cases,otherwise let the player re-enter the while(keepGo) loop above
	//First of all, check If the pit last stone dropped into is empty on your side, 
   //clear(removeAllS) the last stone dropped at and opposing stones, add them all to the Mancala
		
		
		if(target.getNumber()==1) {
			Mancala currentM=target;
			boolean onPlayerSide=false;
			boolean dstop=true;
			int count=0;
		    while(!onPlayerSide && dstop) {
		    	target=target.next();
		    	if(target==game.getM1()) {
		    		if(playerID==0) {
		    			onPlayerSide=true;
		    			 currentM.SetNumber(0);
		    		}
		    		else {
		    			dstop=false;
		    		}
		    	}
		    	if(target==game.getM2()) {
		    		if(playerID==1) {
		    			onPlayerSide=true;
		    			 currentM.SetNumber(0);
		    		}
		    		else {
		    			dstop=false;
		    		}
		    	}
		    	count++;
		    	
		    }
		    
		    if(onPlayerSide) {
			for(int i=0;i<=count;i++) {
				target=target.next();
			}
		
			game.AddstoneM1(1+target.getNumber());//Assuming player0 is playing the game
			target.SetNumber(0);
			
		}
		
	
      }
		
	//Second, Check if the game is over 
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
	}
	else if(sum1==0) {
		game.AddstoneM1(sum0);
	}
	if(sum0==0 || sum1==0) {
		if(game.getM1().getNumber()>game.getM2().getNumber()) {
			System.out.println("Player0 won!");
		}
		else if(game.getM2().getNumber()> game.getM1().getNumber()) {
			System.out.println("Player1 won!");
		}
		else {
			System.out.println("Both won!");
		}
		keepGo=false;
		noWin=false;
	}
	
      if(noWin) {//Check this last case if no winner yet
	//Check if the last stone was dropped into the active palyer's Mancala, if yes, offer free turn
		if(target==game.getM1()) {
			playerID--;
			System.out.println("You get a free turn.");
		}
	
		 playerID++;//Swap player, or don't swapped if "palyerID--" is done before
	
	
 
     
      
	
	}

	}
	
	
	
}}}*/


