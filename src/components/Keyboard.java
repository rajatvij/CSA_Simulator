package components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Keyboard extends JPanel
{
  private String buffer = "";
  private int asciiVal;
  public String status = "0";
  private JButton btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, btnP, btnA, btnS, btnD, buttonF, buttonJ, buttonG, buttonH, buttonK, buttonL,
  				  buttonZ, buttonX, buttonC, buttonV, buttonB, buttonN, buttonM,button_Equals, button1, button2, button3, button4, button5, button6, 
  				button7, button8, button9, button0, buttonDot, buttonComma, buttonColon, buttonEnter;

  
  public Keyboard()
  {
  	setLayout(null);
  	
  	btnQ = new JButton("Q");
  	btnQ.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	btnQ.setBounds(0, 6, 75, 29);
  	add(btnQ);
  	
  	btnW = new JButton("W");
  	btnW.setBounds(73, 6, 75, 29);
  	btnW.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnW);
  	
  	btnE = new JButton("E");
  	btnE.setBounds(148, 6, 75, 29);
  	btnE.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnE);
  	
  	btnR = new JButton("R");
  	btnR.setBounds(222, 6, 75, 29);
  	btnR.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnR);
  	
  	btnT = new JButton("T");
  	btnT.setBounds(309, 6, 75, 29);
  	btnT.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnT);
  	
  	btnY = new JButton("Y");
  	btnY.setBounds(385, 6, 75, 29);
  	btnY.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnY);
  	
  	btnU = new JButton("U");
  	btnU.setBounds(462, 6, 75, 29);
  	btnU.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnU);
  	
  	btnI = new JButton("I");
  	btnI.setBounds(541, 6, 75, 29);
  	btnI.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnI);
  	
  	btnO = new JButton("O");
  	btnO.setBounds(621, 6, 75, 29);
  	btnO.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnO);
  	
  	btnP = new JButton("P");
  	btnP.setBounds(695, 6, 75, 29);
  	btnP.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnP);
  	
  	btnA = new JButton("A");
  	btnA.setBounds(40, 40, 75, 29);
  	btnA.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnA);
  	
  	btnS = new JButton("S");
  	btnS.setBounds(120, 40, 75, 29);
  	btnS.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnS);
  	
  	btnD = new JButton("D");
  	btnD.setBounds(200, 40, 75, 29);
  	btnD.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(btnD);
  	
  	buttonF = new JButton("F");
  	buttonF.setBounds(280, 40, 75, 29);
  	buttonF.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(buttonF);
  	
  	buttonG = new JButton("G");
  	buttonG.setBounds(360, 40, 75, 29);
  	buttonG.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(buttonG);
  	
  	buttonH = new JButton("H");
  	buttonH.setBounds(440, 40, 75, 29);
  	buttonH.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(buttonH);
  	
  	buttonJ = new JButton("J");
  	buttonJ.setBounds(520, 40, 75, 29);
  	buttonJ.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(buttonJ);
  	
  	buttonK = new JButton("K");
  	buttonK.setBounds(600, 40, 75, 29);
  	buttonK.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(buttonK);
  	
  	buttonL = new JButton("L");
  	buttonL.setBounds(680, 40, 75, 29);
  	buttonL.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	add(buttonL);
  	
  	buttonZ = new JButton("Z");
  	buttonZ.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonZ.setBounds(80, 70, 75, 29);
  	add(buttonZ);
  	
  	buttonX = new JButton("X");
  	buttonX.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonX.setBounds(160, 70, 75, 29);
  	add(buttonX);
  	
  	buttonC = new JButton("C");
  	buttonC.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonC.setBounds(240, 70, 75, 29);
  	add(buttonC);
  	
  	buttonV = new JButton("V");
  	buttonV.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonV.setBounds(320, 70, 75, 29);
  	add(buttonV);
  	
  	buttonB = new JButton("B");
  	buttonB.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonB.setBounds(400, 70, 75, 29);
  	add(buttonB);
  	
  	buttonN = new JButton("N");
  	buttonN.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonN.setBounds(480, 70, 75, 29);
  	add(buttonN);
  	
  	buttonM = new JButton("M");
  	buttonM.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonM.setBounds(560, 70, 75, 29);
  	add(buttonM);
  	
  	button_Equals = new JButton("=");
  	button_Equals.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button_Equals.setBounds(640, 70, 75, 29);
  	add(button_Equals);
  	
  	button1 = new JButton("1");
  	button1.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button1.setBounds(120, 99, 75, 29);
  	add(button1);
  	
  	button2 = new JButton("2");
  	button2.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button2.setBounds(200, 99, 75, 29);
  	add(button2);
  	
  	button3 = new JButton("3");
  	button3.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button3.setBounds(280, 99, 75, 29);
  	add(button3);
  	
  	button4 = new JButton("4");
  	button4.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button4.setBounds(360, 99, 75, 29);
  	add(button4);
  	
  	button5 = new JButton("5");
  	button5.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button5.setBounds(440, 99, 75, 29);
  	add(button5);
  	
  	button6 = new JButton("6");
  	button6.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button6.setBounds(520, 99, 75, 29);
  	add(button6);
  	
  	button7 = new JButton("7");
  	button7.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button7.setBounds(600, 99, 75, 29);
  	add(button7);
  	
  	button8 = new JButton("8");
  	button8.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button8.setBounds(160, 129, 75, 29);
  	add(button8);
  	
  	button9 = new JButton("9");
  	button9.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button9.setBounds(240, 129, 75, 29);
  	add(button9);
  	
  	button0 = new JButton("0");
  	button0.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	button0.setBounds(320, 129, 75, 29);
  	add(button0);
  	
  	buttonDot = new JButton(".");
  	buttonDot.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonDot.setBounds(400, 129, 75, 29);
  	add(buttonDot);
  	
  	buttonComma = new JButton(",");
  	buttonComma.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonComma.setBounds(480, 129, 75, 29);
  	add(buttonComma);
  	
  	buttonColon = new JButton(":");
  	buttonColon.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getClick(e);
  		}
  	});
  	buttonColon.setBounds(560, 129, 75, 29);
  	add(buttonColon);
  	
  	buttonEnter = new JButton("Enter");
  	buttonEnter.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			getFirstClick(e);
  		}
  	});
  	buttonEnter.setBounds(360, 159, 75, 29);
  	add(buttonEnter);
  }

  private void getClick(ActionEvent evt)
  {
    JButton j = (JButton)evt.getSource();
    char character = j.getText().charAt(0);
    asciiVal = character;
    status = "1";
    buffer = Long.toBinaryString(asciiVal);
  }
  private void getFirstClick(ActionEvent evt)
  {
	  asciiVal = 13;
    status = "1";
    buffer = Long.toBinaryString(asciiVal);
  }

  public String transferData()
  {
    status = "0";

    return buffer;
  }
  
}