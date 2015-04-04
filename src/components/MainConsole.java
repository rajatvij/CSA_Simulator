package components;

/**
 * @authors Ashwini Prabhu, Rajat Vij, Rahul Dhamnani, Xiobei Yu, Anirud Pandey
 * @class MainConsole Class is the main class to setup the GUI
 */




import java.io.*;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

import java.awt.Toolkit;

import javax.swing.JScrollBar;

import components.Keyboard;
import components.Printer;
import components.AsciiConverter;

public class MainConsole extends JFrame {

	private JPanel contentPane;
	

	Register R0;
	Register R1;
	Register R2;
	Register R3;
	Register IX1;
	Register IX2;
	Register IX3;
	Register MAR;
	Register MBR;
	Register OPCODE;
	Register Instruction;
	Register ADDR;
	Register TRAPCODE;
	Register PC;
	Register CC;
	Register instrCnt;
	Register deviceId;
	Keyboard KEYBOARD;
	Printer PRINTER;
	AsciiConverter CONVERTER;

	static Memory MEMORY;

	JLabel lblOpcode;
	JLabel lblInstrVal;
	JLabel lblInstruction;

	String SwitcheValue;

	JRadioButton IB1, IB2, IB3, IB4, IB5, IB6, IB7, IB8, IB9, IB10, IB11, IB12,
			IB13, IB14, IB15, IB16, IB17, IB18, IB19, IB20;

	JTextField memoryValue;
	JTextField memoryAddress;
	static JTextArea textField;
	

	JToggleButton tglbtnNewToggleButton;
	BufferedImage image;

	ALU alu;
	InstructionComponents IndexREG, REG, I, T;
	private JLabel label;
	/**
	 * @wbp.nonvisual location=69,81
	 */
	private final ImageIcon imageIconOn = new ImageIcon();
	/**
	 * @wbp.nonvisual location=59,81
	 */
	private final ImageIcon imageIconOff = new ImageIcon();
	private boolean running;
	private JButton btnClear;
	private JLabel OPCODEvalue;
	private JLabel PCvalue;
	private JLabel CCvalue;
	

	/**
	 * Create the main frame box.
	 * 
	 * @return
	 */
	public MainConsole() {
		imageIconOn.setImage(Toolkit.getDefaultToolkit().getImage("src/images/On.png"));
		imageIconOff.setImage(Toolkit.getDefaultToolkit().getImage("src/images/Off.png"));
		//label.setIcon(imageIconOff);
		// For shutting down the program
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, -11, 1183, 759);

		// set content panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {                
	          image = ImageIO.read(new File("image name and path"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
		

		// IPL button
		 JToggleButton tglbtnIpl = new JToggleButton("IPL");
	        tglbtnIpl.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                 if (running) {
	                      running = false;
	                      label.setIcon(imageIconOff);
	                      dispose();
	                      MainConsole frame = new MainConsole();
	                      frame.setVisible(true);
	                        
	                    }
	                    else {
	                       running = true;
	                       label.setIcon(imageIconOn); 
	                    }
	                initialize();    
	            }
	        });


	final JButton btnClear_1 = new JButton("Clear");
	        btnClear_1.setBounds(1009, 426, 89, 23);
	        contentPane.add(btnClear_1);
	        btnClear_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if(e.getSource() == btnClear_1)
	                {
	                    textField.setText("");
	                }
	            }
	        });
		tglbtnIpl.setBounds(20, 84, 115, 47);
		contentPane.add(tglbtnIpl);

		// New Panel for Address and Value
		JPanel AddrValuepanel = new JPanel();
		AddrValuepanel.setBounds(20, 203, 294, 92);
		contentPane.add(AddrValuepanel);

		JLabel labelAddress = new JLabel("Address");
		JLabel labelValue = new JLabel("Value");

		memoryAddress = new JTextField();
		memoryAddress.setColumns(10);

		memoryValue = new JTextField();
		memoryValue.setColumns(10);

		// Adding "Deposit into Memory" button
		JButton btnDepositeMemory = new JButton("Deposite into Memory");

		btnDepositeMemory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainConsole.this.btnDepositActionPerformed(evt);
			}
		});

		GroupLayout gl_panel = new GroupLayout(AddrValuepanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(memoryAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addComponent(memoryValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(32)
					.addComponent(labelAddress)
					.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
					.addComponent(labelValue)
					.addGap(71))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(53)
					.addComponent(btnDepositeMemory)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelAddress)
						.addComponent(labelValue))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(memoryAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(memoryValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDepositeMemory)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		AddrValuepanel.setLayout(gl_panel);

		// Creating new panel for Instruction bits, registers and run single
		// step button
		JPanel IBRSSpanel = new JPanel();
		IBRSSpanel.setBounds(326, 203, 608, 92);
		contentPane.add(IBRSSpanel);

		IB1 = new JRadioButton("1");
		IB2 = new JRadioButton("2");
		IB3 = new JRadioButton("3");
		IB4 = new JRadioButton("4");
		IB5 = new JRadioButton("5");
		IB6 = new JRadioButton("6");
		IB7 = new JRadioButton("7");
		IB8 = new JRadioButton("8");
		IB9 = new JRadioButton("9");
		IB10 = new JRadioButton("10");
		IB11 = new JRadioButton("11");
		IB12 = new JRadioButton("12");
		IB13 = new JRadioButton("13");
		IB14 = new JRadioButton("14");
		IB15 = new JRadioButton("15");
		IB16 = new JRadioButton("16");
		IB17 = new JRadioButton("17");
		IB18 = new JRadioButton("18");
		IB19 = new JRadioButton("19");
		IB20 = new JRadioButton("20");
		
		JButton btnRunSingleStep = new JButton("Run Single Step");

		R0 = new Register("R0", 20, true);
		R1 = new Register("R1", 20, true);
		R2 = new Register("R2", 20, true);
		R3 = new Register("R3", 20, true);
		IX1 = new Register("IX1", 13, true);
		IX2 = new Register("IX2", 13, true);
		IX3 = new Register("IX3", 13, true);
		MBR = new Register("MBR", 20, true);
		MAR = new Register("MAR", 20, true);
		PC = new Register("PC", 13, false);
		CC = new Register("CC", 4, false);
		instrCnt = new Register("instrCnt", 5, false);
		deviceId = new Register("DEVID", 4, false);

		R0.setBitLength(20);
		R0.setOpaque(false);
		R0.setRegName("R0");
		R1.setRegName("R1");
		R2.setRegName("R2");
		R3.setRegName("R3");
		IX1.setRegName("IX1");
		IX2.setRegName("IX2");
		IX3.setRegName("IX3");
		MBR.setRegName("MBR");
		MAR.setRegName("MAR");

		// Creating a new panel for Instruction
		JPanel ISpanel = new JPanel();
		ISpanel.setBounds(961, 461, 196, 23);
		contentPane.add(ISpanel);

		lblInstruction = new JLabel("Instruction");
		ISpanel.add(lblInstruction);

		lblInstrVal = new JLabel("");
		ISpanel.add(lblInstrVal);
		textField = new JTextArea();
		
		String sAddr = Long.toBinaryString(0L);
	    sAddr = String.format(new StringBuilder().append("%0").append(20 - sAddr.length()).append("d").toString(), new Object[] { Integer.valueOf(0) }) + sAddr;
	    PC.set(sAddr);
	    CC.set("0010");
		
		
		btnRunSingleStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// getting the switch values
				
				SwitcheValue = GetSwitchValues();
				Instruction.set(SwitcheValue);
				DecodeSwitch();
				runSingleStep(SwitcheValue);
				instructionExecution();
				
			}

		});

		GroupLayout gl_panel_1 = new GroupLayout(IBRSSpanel);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRunSingleStep)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(IB1)
								.addComponent(IB11))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(IB2)
								.addComponent(IB12))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(IB3)
								.addComponent(IB13))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(IB4)
								.addComponent(IB14))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(IB5)
								.addComponent(IB15))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(IB6)
								.addComponent(IB16))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(IB7)
						.addComponent(IB17))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(IB8)
						.addComponent(IB18))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(IB9)
						.addComponent(IB19))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(IB10)
						.addComponent(IB20))
					.addContainerGap(208, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(IB1)
						.addComponent(IB2)
						.addComponent(IB3)
						.addComponent(IB4)
						.addComponent(IB5)
						.addComponent(IB6)
						.addComponent(IB7)
						.addComponent(IB8)
						.addComponent(IB9)
						.addComponent(IB10))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(IB11)
						.addComponent(IB12)
						.addComponent(IB13)
						.addComponent(IB14)
						.addComponent(IB15)
						.addComponent(IB16)
						.addComponent(IB17)
						.addComponent(IB18)
						.addComponent(IB19)
						.addComponent(IB20))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRunSingleStep)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		IBRSSpanel.setLayout(gl_panel_1);

		// Adding Registers, Index Registers, MAR, MBR on the panel

		R0 = new Register("R0", 20, true);
		R0.setBounds(20, 307, 914, 35);
		contentPane.add(R0);

		R1 = new Register("R1", 20, true);
		R1.setBounds(20, 354, 914, 35);
		contentPane.add(R1);

		R2 = new Register("R2", 20, true);
		R2.setBounds(20, 401, 914, 35);
		contentPane.add(R2);

		R3 = new Register("R3", 20, true);
		R3.setBounds(20, 449, 914, 35);
		contentPane.add(R3);

		IX1 = new Register("IX1", 13, true);
		IX1.setBounds(20, 496, 914, 35);
		contentPane.add(IX1);

		IX2 = new Register("IX2", 13, true);
		IX2.setBounds(20, 543, 914, 35);
		contentPane.add(IX2);

		IX3 = new Register("IX3", 13, true);
		IX3.setBounds(20, 590, 914, 35);
		contentPane.add(IX3);

		MAR = new Register("MAR", 20, true);
		MAR.setBounds(20, 637, 914, 35);
		contentPane.add(MAR);

		MBR = new Register("MBR", 20, true);
		MBR.setBounds(20, 684, 914, 35);
		contentPane.add(MBR);

		JButton btnNewButton = new JButton("Load from File\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Browse and Choose File	
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        fileChooser.setAcceptAllFileFilterUsed(false);
		 
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		        	readFile(fileChooser.getSelectedFile().toString());
		        }
		        runInstructions();
			}
		});
		btnNewButton.setBounds(20, 143, 115, 48);
		contentPane.add(btnNewButton);

		textField = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textField);
		contentPane.add(scrollPane);
		scrollPane.setBounds(951, 7, 189, 394);
		scrollPane.setVisible(true);
		label = new JLabel("");
		label.setIcon(new ImageIcon("src/images/Off.png"));
		label.setBounds(26, 11, 82, 61);
		contentPane.add(label);
		JLabel lblOpcode_1 = new JLabel("OPCODE");
		lblOpcode_1.setBounds(961, 493, 70, 23);
		contentPane.add(lblOpcode_1);
		
		JLabel lblPc = new JLabel("PC");
		lblPc.setBounds(961, 528, 48, 23);
		contentPane.add(lblPc);
		
		JLabel lblCc = new JLabel("CC");
		lblCc.setBounds(961, 563, 70, 23);
		contentPane.add(lblCc);
		
		OPCODEvalue = new JLabel();
		OPCODEvalue.setBounds(1028, 493, 129, 23);
		contentPane.add(OPCODEvalue);
		
		CCvalue = new JLabel("");
		CCvalue.setBounds(1028, 563, 129, 23);
		contentPane.add(CCvalue);
		KEYBOARD = new Keyboard();
		KEYBOARD.setBounds(147, 7, 787, 184);
		contentPane.add(KEYBOARD);
		PRINTER = new Printer();
		PRINTER.setBounds(961, 605, 206, 114);
		contentPane.add(PRINTER);
		
		PCvalue = new JLabel("");
		PRINTER.add(PCvalue);
		OPCODE = new Register("OPCODE", 6, false);
		ADDR = new Register("ADDR", 8, false);
		Instruction = new Register("IR", 20, false);
		
	}
	
	public void instructionExecution()
	{	
		switch (Integer.parseInt(Integer.toOctalString(Integer.parseInt(OPCODE.get(), 2)))) {
		case 0:
			addInTextForm("HLT");
			HLT();
			break;
		case 1:
			addInTextForm("LDR");
			LDR();
			showText();
			break;
		case 2:
			addInTextForm("STR");
			STR();
			showText();
			break;
		case 3:
			addInTextForm("LDA");
			LDA();
			showText();
			break;
		case 4:
			addInTextForm("AMR");
			AMR();
			showText();
			break;
		case 5:
			addInTextForm("SMR");
			SMR();
			showText();
			break;
		case 6:
			addInTextForm("AIR");
			AIR();
			showText();
			break;
		case 7:
			addInTextForm("STR");
			SIR();
			showText();
			break;
		case 10: 
			addInTextForm("JZ");
		    JZ();
			showText();
		    break;
		case 11: 
		    addInTextForm("JNE");
		    JNE();
			showText();
		    break;
		case 12: 
		    addInTextForm("JCC");
		    JCC();
			showText();
		    break;
		case 13: 
		    addInTextForm("JMP");
		    JMP();
			showText();
		    break;
		case 14: 
		    addInTextForm("JSR");
		    JSR();
			showText();
		    break;
		case 15: 
		   	addInTextForm("RFS");
		    RFS();
			showText();
		    break;
		case 16: 
		   	addInTextForm("SOB");
		    SOB();
			showText();
		    break;
		case 17: 
		   	addInTextForm("JGE");
		    JGE();
			showText();
		    break;
		case 20: 
		   	addInTextForm("MLT");
		    MLT();
		    break;
		case 21: 
		   	addInTextForm("DVD");
		    DVD();
		    break;
		case 22: 
		   	addInTextForm("TRR");
		    TRR();
		    break;
		case 23: 
		   	addInTextForm("AND");
		    AND();
		    break;
		case 24: 
		   	addInTextForm("ORR");
		    ORR();
		    break;
		case 25: 
		   	addInTextForm("NOT");
		    NOT();
		    break;
		case 30:
			addInTextForm("TRAP");
			TRAP();
			break;
		case 31: 
		  	addInTextForm("SRC");
		    SRC();
		    break;
		case 32: 
		   	addInTextForm("RRC");
		    RRC();
		    break;
		case 36: 
		   	addInTextForm("TRAP");
		    TRAP();
		    break;
		case 41:
			addInTextForm("LDX");
			LDX();
			showText();
			break;
		case 42:
			addInTextForm("STX");
			STX();
			showText();
			break;
		case 61:
			addInTextForm("IN");
			IN();
			break;
		case 62:
			addInTextForm("OUT");
			OUT();
			break;
		case 63:
			addInTextForm("CHK");
			CHK();
			break;
		default:
			addInTextForm("Invalid Opcode");
			break;
		}
	}
	
	
	protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

	protected void btnDepositActionPerformed(ActionEvent evt) {
		String sVal = Long.toBinaryString(Integer.parseInt(this.memoryValue
				.getText()));
		sVal = new StringBuilder()
				.append(String.format(
						new StringBuilder().append("%0")
								.append(20 - sVal.length()).append("d")
								.toString(),
						new Object[] { Integer.valueOf(0) })).append(sVal)
				.toString();

		String sAddr = Long.toBinaryString(Integer.parseInt(this.memoryAddress
				.getText()));
		sAddr = new StringBuilder()
				.append(String.format(
						new StringBuilder().append("%0")
								.append(20 - sAddr.length()).append("d")
								.toString(),
						new Object[] { Integer.valueOf(0) })).append(sAddr)
				.toString();

		this.MEMORY.set(sVal, sAddr);
		this.memoryAddress.setText("");
		this.memoryValue.setText("");

	}
	
	public void HLT(){
		this.PC.set("0");
	}
	
	public void TRAP(){
		//get the table address
		String tableAddr = MEMORY.getDirect("0");
		 String output = null;
		  if(Integer.parseInt(deviceId.get(),2)==0){
			  output = "Illegal Memory Address to Reserved Locations \n";
			  if(null!=Instruction.get())
				  MEMORY.setDirect(Instruction.get(), "000000000001");
			  else if(null!=SwitcheValue)
				  MEMORY.setDirect(SwitcheValue, "000000000001");
			  MEMORY.setDirect(PC.get(), "000000000100");
		  	  }
		  else if(Integer.parseInt(deviceId.get(),2)==1){
			  output = "Illegal TRAP Code \n";
			  if(null!=Instruction.get())
				  MEMORY.setDirect(Instruction.get(), "000000000001");
			  else if(null!=SwitcheValue)
				  MEMORY.setDirect(SwitcheValue, "000000000001");
			  MEMORY.setDirect(PC.get(), "000000000100");
		  	  }
		  else if(Integer.parseInt(deviceId.get(),2)==2){
			  output = "Illegal Operation Code \n";
			  if(null!=Instruction.get())
				  MEMORY.setDirect(Instruction.get(), "000000000001");
			  else if(null!=SwitcheValue)
				  MEMORY.setDirect(SwitcheValue, "000000000001");
			  MEMORY.setDirect(PC.get(), "000000000100");
			  }
		  else if(Integer.parseInt(deviceId.get(),2)==3){
			  output = "Illegal Memory Address Beyond 2048 \n";
			  if(null!=Instruction.get())
				  MEMORY.setDirect(Instruction.get(), "000000000001");
			  else if(null!=SwitcheValue)
				  MEMORY.setDirect(SwitcheValue, "000000000001");
			  MEMORY.setDirect(PC.get(), "000000000100");
			  }
		  else{
			  output = "Trap Instruction \n";
			  incrementPC();
			  if(null!=Instruction.get())
				  MEMORY.setDirect(Instruction.get(), "000000000000");
			  else if(null!=SwitcheValue)
				  MEMORY.setDirect(SwitcheValue, "000000000000");
			  MEMORY.setDirect(PC.get(), "000000000010");
		  }
		  textField.append(output);
		  System.out.println(output);
	}

	public void LDR() {
		incrementPC();
		ComputeEffectiveAddress();
		GPRRegisterCheck().set(MBR.get());

	}

	public void STR() {
		incrementPC();
		ComputeEffectiveAddress();
		MEMORY.set(GPRRegisterCheck().get(), MAR.get());
	}

	public void LDA() {
		incrementPC();
		ComputeEffectiveAddress();
		GPRRegisterCheck().set(MAR.get());

	}

	public void SMR() {
		incrementPC();
		ComputeEffectiveAddress();
		alu.Operand2.set(MBR.get());
		alu.Operand1.set(GPRRegisterCheck().get());
		alu.sub();
		GPRRegisterCheck().set(alu.RESULT.get());

	}

	public void AMR() {
		incrementPC();
		ComputeEffectiveAddress();
		alu.Operand2.set(MBR.get());
		alu.Operand1.set(GPRRegisterCheck().get());
		alu.add();
		GPRRegisterCheck().set(alu.RESULT.get());

	}

	public void AIR() {
		incrementPC();
		ComputeEffectiveAddress();
		alu.Operand2.set(ADDR.get());
		alu.Operand1.set(GPRRegisterCheck().get());
		alu.add();
		GPRRegisterCheck().set(alu.RESULT.get());

	}

	public void SIR() {
		incrementPC();
		ComputeEffectiveAddress();
		alu.Operand2.set(ADDR.get());
		alu.Operand1.set(GPRRegisterCheck().get());
		alu.sub();
		GPRRegisterCheck().set(alu.RESULT.get());
	}

	public void LDX() {
		incrementPC();
		computeEffectiveAddressForNonIndex();
		IndexRegisterCheck().set(MAR.get());

	}

	public void STX() {
		incrementPC();
		computeEffectiveAddressForNonIndex();
		MEMORY.set(IndexRegisterCheck().get(), MAR.get());
	}
	
	public void JZ()
	  {
	    ComputeEffectiveAddress();
	    if (GPRRegisterCheck().get().equals(String.format("%020d", new Object[] { Integer.valueOf(0) })))
	    {
	      if (I.get().equals("0")) {
	        PC.set(MAR.get());
	      } else {
	    	  PC.set(MEMORY.get(MAR.get()));
	      }
	    }
	    else
	    {
	    	incrementPC();
	    	alu.Operand2.set(PC.get());
	    	alu.Operand1.set("1");
	    	alu.add();
	      PC.set(alu.RESULT.get());
	    }
	  }
	  
	public void JNE()
	  {
	    ComputeEffectiveAddress();
	    if (!GPRRegisterCheck().get().equals(String.format("%020d", new Object[] { Integer.valueOf(0) })))
	    {
	      if (I.get().equals("0")) {
	        PC.set(MAR.get());
	      } else {
	    	  PC.set(MEMORY.get(MAR.get()));
	      }
	    }
	    else
	    {
	    	incrementPC();
	    	alu.Operand2.set(PC.get());
	    	alu.Operand1.set("1");
	    	alu.add();
	      PC.set(alu.RESULT.get());
	    }
	  }
	  
	  public void JCC()
	  {
	    ComputeEffectiveAddress();
	    if (CC.getValue(REG.Index.intValue() + 1) == 1)
	    {
	      if (I.get().equals("0")) {
	        PC.set(MAR.get());
	      } else {
	    	  PC.set(MEMORY.get(MAR.get()));
	      }
	    }
	    else
	    {
	    	incrementPC();
	    	alu.Operand2.set(PC.get());
	    	alu.Operand1.set("1");
	    	alu.add();
	      PC.set(alu.RESULT.get());
	    }
	  }
	  
	  public void JMP()
	  {
	    ComputeEffectiveAddress();
	    if (I.get().equals("0")) {
	      PC.set(MAR.get());
	    } else {
	    	PC.set(MEMORY.get(MAR.get()));
	    }
	  }
	  
	  public void JSR()
	  {
	    ComputeEffectiveAddress();
	    incrementPC();
	    alu.Operand2.set(PC.get());
	    alu.Operand1.set("1");
	    alu.add();
	    REG.set("11");
	    GPRRegisterCheck().set(alu.RESULT.get());
	    if (I.get().equals("1")) {
	    	PC.set(MEMORY.get(MAR.get()));
	    }
	  }
	  
	  public void RFS()
	  {
	    ComputeEffectiveAddress();
	    REG.set("00");
	    GPRRegisterCheck().set(ADDR.get());
	    REG.set("11");
	    PC.set(GPRRegisterCheck().get());
	  }
	  
	  public void SOB()
	  {
	    ComputeEffectiveAddress();
	    alu.Operand2.set(GPRRegisterCheck().get());
	    alu.Operand1.set("1");
	    alu.sub();
	    if (GPRRegisterCheck().getValue(20) == 0)
	    {
	      if (I.get().equals("0")) {
	        PC.set(MAR.get());
	      } else {
	    	  PC.set(MEMORY.get(MAR.get()));
	      }
	    }
	    else
	    {
	    	incrementPC();
	    	alu.Operand2.set(PC.get());
	    	alu.Operand1.set("1");
	    	alu.add();
	      PC.set(alu.RESULT.get());
	    }
	  }
	  
	  public void JGE()
	  {
	    ComputeEffectiveAddress();
	    if (GPRRegisterCheck().getValue(20) == 0)
	    {
	      if (I.get().equals("0")) {
	        PC.set(MAR.get());
	      } else {
	    	  PC.set(MEMORY.get(MAR.get()));
	      }
	    }
	    else
	    {
	    	incrementPC();
	    	alu.Operand2.set(PC.get());
	    	alu.Operand1.set("1");
	    	alu.add();
	      PC.set(alu.RESULT.get());
	    }
	  }
	  
	  public void MLT()
	  {
		  incrementPC();
	    if (((REG.get().equals("00")) || (REG.get().equals("10"))) && ((REG.get().equals("00")) || (REG.get().equals("10"))))
	    {
	    	alu.Operand1.set(GPRRegisterCheck().get());
	
	      REG.set(IndexREG.get());
	      alu.Operand2.set(GPRRegisterCheck().get());
	      alu.mult();
	      GPRRegisterCheck().set(alu.ENDRESULT.get().substring(0, 20));
	      textField.append("Higher Order"+Integer.parseInt((alu.ENDRESULT.get().substring(0, 20)),2)+"\n");
	      SelectNextGPRRegister().set(alu.ENDRESULT.get().substring(20, 40));
	      textField.append("Mul="+Integer.parseInt((alu.ENDRESULT.get().substring(20, 40)),2)+"\n");
	    }
	  }
	  
	  public void DVD()
	  {
		  incrementPC();
		  System.out.println("IndexReg: "+IndexRegisterCheck());
	    if (((IndexREG.get().equals("00")) || (IndexREG.get().equals("10"))) && ((REG.get().equals("00")) || (REG.get().equals("10")))) {
	      if ((GPRRegisterCheck().get().equals("00000000000000000000")) || (GPRRegisterCheck().get().equals("10000000000000000000")))
	      {
	        CC.setBit(3, "1");
	      }
	      else
	      {
	    	alu.Operand1.set(GPRRegisterCheck().get());
	        REG.set(IndexREG.get());
	        alu.Operand2.set(GPRRegisterCheck().get());
	        alu.div();
	        
	        GPRRegisterCheck().set(alu.RESULT.get());
	        textField.append("Quotient : "+Integer.parseInt(GPRRegisterCheck().value,2)+"\n");
	        SelectNextGPRRegister().set(alu.RESULT1.get());
	        textField.append("Remainder : "+Integer.parseInt(SelectNextGPRRegister().value,2)+"\n");
	      }
	    }
	  }
	  
	  public void TRR()
	  {
		  incrementPC();
		  alu.Operand1.set(GPRRegisterCheck().get());
	    REG.set(IndexREG.get());
	    alu.Operand2.set(GPRRegisterCheck().get());
	    if(alu.Operand1.get().equals(alu.Operand2.get())) {
	      CC.setBit(4, "1");
	      textField.append("Register are equal \n");
	    } else {
	      CC.setBit(4, "0");
	      textField.append("Register are not equal \n");
	    }
	  }
	  
	  public void AND()
	  {
		  incrementPC();
		  alu.Operand1.set(GPRRegisterCheck().get());
	    REG.set(IndexREG.get());
	    alu.Operand2.set(GPRRegisterCheck().get());
	    alu.and();
	    
	    GPRRegisterCheck().set(alu.RESULT.get());
	    textField.append("R0 AND R2 = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");
	  }
	  
	  public void ORR()
	  {
		  incrementPC();
		  alu.Operand2.set(GPRRegisterCheck().get());
	    
	    REG.set(IndexREG.get());
	    alu.Operand2.set(GPRRegisterCheck().get());
	    alu.or();
	    
	    GPRRegisterCheck().set(alu.RESULT.get());
	    textField.append("R0 OR R2 = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");
	  }
	  
	  public void NOT()
	  {
		  incrementPC();
	    REG.set(IndexREG.get());
	    alu.Operand1.set(GPRRegisterCheck().get());
	    alu.not();
	    GPRRegisterCheck().set(alu.RESULT.get());
	    textField.append("NOT R0 = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");	    
	  }
	  
	  public void SRC()
	  {
		  incrementPC();
	    int number = Integer.parseInt(instrCnt.get(), 2);
	    alu.Operand1.set(GPRRegisterCheck().get());
	    if ((I.get().equals("0")) && (T.get().equals("0")))
	    {
	    	alu.ShiftRightArithmetic(number);
	    	GPRRegisterCheck().set(alu.RESULT.get());
		    textField.append("Right Arithmatic Shift = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");	    
	    }
	    else if ((I.get().equals("0")) && (T.get().equals("1")))
	    {
	    	alu.ShiftLeftArithmetic(number);
	    	GPRRegisterCheck().set(alu.RESULT.get());
		    textField.append("Left Arithmatic Shift = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");	    
	    }
	    else if ((I.get().equals("1")) && (T.get().equals("0")))
	    {
	      alu.logicalShiftRight(number);
	      GPRRegisterCheck().set(alu.RESULT.get());
		    textField.append("Right Logical Shift = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");	    
	    }
	    else
	    {
	    	alu.logicalShiftLeft(number);
	    	GPRRegisterCheck().set(alu.RESULT.get());
		    textField.append("Left Logical Shift = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");	    
	    }
	  }
	  
	  public void RRC()
	  {
		  incrementPC();
	    int number = Integer.parseInt(instrCnt.get(), 2);
	    alu.Operand1.set(GPRRegisterCheck().get());
	    if ((I.get().equals("1")) && (T.get().equals("0")))
	    {
	    	alu.logicalRotateRight(number);
	    	GPRRegisterCheck().set(alu.RESULT.get());
		    textField.append("Right Logical Rotate = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");	    
	    }
	    else
	    {
	    	alu.logicalRotateLeft(number);
		    GPRRegisterCheck().set(alu.RESULT.get());
		    textField.append("Left Logical Rotate = "+Integer.parseInt(alu.RESULT.get(),2)+"\n");	    
	    }
	    GPRRegisterCheck().set(alu.RESULT.get());
	  }
	  public void IN() {
		  incrementPC();
		    String DATA = "";

		    switch (Integer.parseInt(deviceId.get(), 2)) {
		    case 0:
		      DATA = KEYBOARD.transferData();
		      break;
		    case 2:
		      break;
		    case 3:
		      DATA = R1.get();
		    case 1:
		    }
		    int numericValue = Integer.parseInt(DATA, 2);
		    if ((48 <= numericValue) && (numericValue <= 57)) {
		      numericValue = Character.getNumericValue(numericValue);
		    }
		    else if (numericValue != 13) {
		      HLT();                                 //Change this ************************
		    }

		    GPRRegisterCheck().set(Long.toBinaryString(numericValue));
		  }

		  public void OUT()
		  {
			  incrementPC();

		    switch (Integer.parseInt(deviceId.get(), 2)) {
		    case 1:
		      PRINTER.GetData(GPRRegisterCheck().get());
		      break;
		    case 2:
		      break;
		    case 3:
		      R1.set(GPRRegisterCheck().get());
		    }
		  }

		  public void CHK()
		  {
			  incrementPC();

		    switch (Integer.parseInt(this.deviceId.get(), 2)) {
		    case 0:
		    	GPRRegisterCheck().set(KEYBOARD.status);
		      break;
		    case 1:
		    	GPRRegisterCheck().set(PRINTER.STATUS);
		    }
		  }


	/*
	 * Checking which GPR register is selected
	 */
	private Register GPRRegisterCheck() {

		Register RegisterCheck;

		switch (REG.Index) {
		case 0:
			RegisterCheck = R0;
			break;
		case 1:
			RegisterCheck = R1;
			break;
		case 2:
			RegisterCheck = R2;
			break;
		case 3:
			RegisterCheck = R3;
			break;
		default:
			RegisterCheck = null;
			break;
		}

		return RegisterCheck;

	}
	private int GPRRegisterCheck2() {

		Register RegisterCheck;
		int temp =-1;
		switch (REG.Index) {
		case 0:
			RegisterCheck = R0;
			temp =0;
			break;
		case 1:
			RegisterCheck = R1;
			temp =1;
			break;
		case 2:
			RegisterCheck = R2;
			temp =3;
			break;
		case 3:
			RegisterCheck = R3;
			temp=4;
			break;
		default:
			RegisterCheck = null;
			break;
		}

		return temp;

	}

	/*
	 * Checking which Index register is selected
	 */
	private Register IndexRegisterCheck() {

		Register IRCheck;

		switch (IndexREG.Index) {
		case 0:
			IRCheck = IX1;
			break;
		case 1:
			IRCheck = IX2;
			break;
		case 2:
			IRCheck = IX3;
			break;
		default:
			IRCheck = null;
			break;
		}

		return IRCheck;

	}
	
	private Register SelectNextGPRRegister()
	  {
		Register RegisterCheck;
	    switch (REG.Index)
	    {
	    case 0: 
	    	RegisterCheck = R1;
	      break;
	    case 2: 
	    	RegisterCheck = R3;
	      break;
	    default: 
	    	RegisterCheck = null;
	    }
	    return RegisterCheck;
	  }

	private void DecodeSwitch() {
		OPCODE.set(SwitcheValue.substring(0, 6));
		IndexREG.set(SwitcheValue.substring(6, 8));
		REG.set(SwitcheValue.substring(8, 10));
		I.set(SwitcheValue.substring(10, 11));
		T.set(SwitcheValue.substring(11, 12));
		ADDR.set(SwitcheValue.substring(12, 20));
		instrCnt.set(SwitcheValue.substring(15, 20));
		OPCODEvalue.setText(OPCODE.get());
		deviceId.set(SwitcheValue.substring(16, 20));
		//TRAPCODE.set(SwitcheValue.substring(16, 20));
	}

	/*
	 * Building the Switch value Checking each Instruction bit and according
	 * appending 0 or 1
	 */
	private String GetSwitchValues() {
		this.SwitcheValue = "";
		StringBuilder switchVal = new StringBuilder("");

		switchVal.append(IB1.isSelected() ? "1" : "0").toString();
		switchVal.append(IB2.isSelected() ? "1" : "0").toString();
		switchVal.append(IB3.isSelected() ? "1" : "0").toString();
		switchVal.append(IB4.isSelected() ? "1" : "0").toString();
		switchVal.append(IB5.isSelected() ? "1" : "0").toString();
		switchVal.append(IB6.isSelected() ? "1" : "0").toString();
		switchVal.append(IB7.isSelected() ? "1" : "0").toString();
		switchVal.append(IB8.isSelected() ? "1" : "0").toString();
		switchVal.append(IB9.isSelected() ? "1" : "0").toString();
		switchVal.append(IB10.isSelected() ? "1" : "0").toString();
		switchVal.append(IB11.isSelected() ? "1" : "0").toString();
		switchVal.append(IB12.isSelected() ? "1" : "0").toString();
		switchVal.append(IB13.isSelected() ? "1" : "0").toString();
		switchVal.append(IB14.isSelected() ? "1" : "0").toString();
		switchVal.append(IB15.isSelected() ? "1" : "0").toString();
		switchVal.append(IB16.isSelected() ? "1" : "0").toString();
		switchVal.append(IB17.isSelected() ? "1" : "0").toString();
		switchVal.append(IB18.isSelected() ? "1" : "0").toString();
		switchVal.append(IB19.isSelected() ? "1" : "0").toString();
		switchVal.append(IB20.isSelected() ? "1" : "0").toString();

		return switchVal.toString();
	}

	public void ComputeEffectiveAddress() {
		if ((this.IndexREG.get().equals("00") & this.I.get().equals("0"))) {
			this.MAR.set(this.ADDR.get());
			
			this.MBR.set(this.MEMORY.get(this.MAR.get()));
		} else if ((((this.IndexREG.get().equals("01"))
				|| (this.IndexREG.get().equals("10")) || (this.IndexREG.get()
				.equals("11"))) & this.I.get().equals("0"))) {
			alu.Operand1.set(this.ADDR.get());
			this.alu.Operand2.set(IndexRegisterCheck().get());
			this.alu.add();
			this.MAR.set(this.alu.RESULT.get());
			
			this.MBR.set(this.MEMORY.get(this.MAR.get()));
		} else if ((this.IndexREG.get().equals("00") & this.I.get().equals("1"))) {
			this.MAR.set(this.ADDR.get());
			String memoryAddr = this.MAR.get();
			this.MBR.set(this.MEMORY.get(memoryAddr));			
			this.MAR.set(this.MBR.get());
			 this.MBR.set(this.MEMORY.get(this.MAR.get()));
		} else {
			this.alu.Operand1.set(this.ADDR.get());
			this.alu.Operand2.set(IndexRegisterCheck().get());
			this.alu.add();
			 this.MAR.set(this.MEMORY.get(this.alu.RESULT.get()));
			 this.MBR.set(this.MEMORY.get(this.MAR.get()));
		}
	}

	public void computeEffectiveAddressForNonIndex() {
		if (this.I.get().equals("0")) {
			this.MAR.set(this.ADDR.get());
			this.MBR.set(this.MEMORY.get(this.MAR.get()));
		} else {
			this.MAR.set(this.MEMORY.get(this.ADDR.get()));
		}
		this.MBR.set(this.MEMORY.get(this.MAR.get()));
	}


	private void runSingleStep(String instr) {
		lblInstrVal.setText(instr);
	}

	private void showText()
	  {
		  String output = null;
		  if(null != MAR.get()){
		  output = "Memory("+Integer.parseInt(MAR.get(), 2)+") : "+Integer.parseInt(MEMORY.get(MAR.get()),2)+"\n";
		  }
		  textField.append(output);
		  System.out.println(output);
	  }
	
	
public void showotherdetails()
{
	  textField.append(GPRRegisterCheck2() + " ");
	  textField.append(Integer.parseInt(GPRRegisterCheck().get(),2)+"\n");
}
	private void readFile(String fName) {
		FileInputStream fs = null;
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		BufferedReader dumpReader = null;
		int addrCount = 0, linecount =0;
		try {
			fs = new FileInputStream(fName);
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			String data;
			dumpReader = new BufferedReader(new FileReader(fName));
			System.out.println("Reading!!");
			while ((data = dumpReader.readLine()) != null) {
				System.out.println(data);
				MEMORY.set(data, Integer.toBinaryString(addrCount++));
				linecount++;
			}
			System.out.println("Read ends here");
			fs.close();
			baos.close();
			dos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void runInstructions()
	  {
		  for(int i = 5; i< 35; i++)
		  {
			 System.out.println("Instruction.set"+MEMORY.get(Integer.toBinaryString(i)));
			 Instruction.set(MEMORY.get(Integer.toBinaryString(i)));
			 System.out.println("OPCODE.set"+MEMORY.get(Integer.toBinaryString(i)).substring(0,6));
			 OPCODE.set(MEMORY.get(Integer.toBinaryString(i)).substring(0,6));
			 System.out.println("IndexReg.set"+MEMORY.get(Integer.toBinaryString(i)).substring(6,8));
			 IndexREG.set(MEMORY.get(Integer.toBinaryString(i)).substring(6,8));
			 System.out.println("Reg.set"+MEMORY.get(Integer.toBinaryString(i)).substring(8,10));
			 REG.set(MEMORY.get(Integer.toBinaryString(i)).substring(8,10));
			 System.out.println("I.set"+MEMORY.get(Integer.toBinaryString(i)).substring(10,11));
			 I.set(MEMORY.get(Integer.toBinaryString(i)).substring(10,11));
			 System.out.println("T.set"+MEMORY.get(Integer.toBinaryString(i)).substring(11,12));
			 T.set(MEMORY.get(Integer.toBinaryString(i)).substring(11,12));
			 System.out.println("instruction count.set"+MEMORY.get(Integer.toBinaryString(i)).substring(15,20));
			 instrCnt.set(MEMORY.get(Integer.toBinaryString(i)).substring(15,20));
			 System.out.println("address.set"+MEMORY.get(Integer.toBinaryString(i)).substring(12,20));
			 ADDR.set(MEMORY.get(Integer.toBinaryString(i)).substring(12,20));
			 //System.out.println("address.set"+MEMORY.get(Integer.toBinaryString(i)).substring(12,20));
			 //TRAPCODE.set(MEMORY.get(Integer.toBinaryString(i)).substring(16,20));
			 deviceId.set(MEMORY.get(Integer.toBinaryString(i)).substring(16,20));
		  switch (Integer.parseInt(Integer.toOctalString(Integer.parseInt(OPCODE.get(), 2)))){
		  	case 0:
				addInTextForm("HLT");
				HLT();
				break;
			case 1:
				addInTextForm("LDR");
				LDR();
				showText();
				break;
			case 2:
				addInTextForm("STR");
				STR();
				showText();
				break;
			case 3:
				addInTextForm("LDA");
				LDA();
				showText();
				break;
			case 4:
				addInTextForm("AMR");
				AMR();
				showText();
				break;
			case 5:
				addInTextForm("SMR");
				SMR();
				showText();
				break;
			case 6:
				addInTextForm("AIR");
				AIR();
				showText();
				break;
			case 7:
				addInTextForm("STR");
				SIR();
				showText();
				break;
			case 10: 
				addInTextForm("JZ");
			    JZ();
				showText();
			    break;
			case 11: 
			    addInTextForm("JNE");
			    JNE();
				showText();
			    break;
			case 12: 
			    addInTextForm("JCC");
			    JCC();
				showText();
			    break;
			case 13: 
			    addInTextForm("JMP");
			    JMP();
				showText();
			    break;
			case 14: 
			    addInTextForm("JSR");
			    JSR();
				showText();
			    break;
			case 15: 
			   	addInTextForm("RFS");
			    RFS();
				showText();
			    break;
			case 16: 
			   	addInTextForm("SOB");
			    SOB();
				showText();
			    break;
			case 17: 
			   	addInTextForm("JGE");
			    JGE();
				showText();
			    break;
			case 20: 
			   	addInTextForm("MLT");
			    MLT();
			    break;
			case 21: 
			   	addInTextForm("DVD");
			    DVD();
			    break;
			case 22: 
			   	addInTextForm("TRR");
			    TRR();
			    break;
			case 23: 
			   	addInTextForm("AND");
			    AND();
			    break;
			case 24: 
			   	addInTextForm("ORR");
			    ORR();
			    break;
			case 25: 
			   	addInTextForm("NOT");
			    NOT();
			    break;
			case 31: 
			  	addInTextForm("SRC");
			    SRC();
			    break;
			case 32: 
			   	addInTextForm("RRC");
			    RRC();
			    break;
			case 36: 
			   	addInTextForm("TRAP");
			    TRAP();
			    break;
			case 41:
				addInTextForm("LDX");
				LDX();
				showText();
				break;
			case 42:
				addInTextForm("STX");
				STX();
				showText();
				break;
			case 61:
				addInTextForm("IN");
				IN();
				break;
			case 62:
				addInTextForm("OUT");
				OUT();
				break;
			case 63:
				addInTextForm("CHK");
				CHK();
				break;
			default:
				addInTextForm("Invalid Opcode");
				break;

		  	}		    
		  }
	}

	private void initialize()
	{
		MEMORY = new Memory();
		alu = new ALU();
		REG = new InstructionComponents();
		IndexREG = new InstructionComponents();
		I = new InstructionComponents();
		T = new InstructionComponents();
	}
	
	private void addInTextForm(String sInst)
	  {
	    String instr = "";
	    
	    instr = instr + sInst + ", ";
	    instr = instr + String.format("%s", new Object[] { "R" + Integer.parseInt(REG.get(), 2) + ", " });
	    instr = instr + (Integer.parseInt(IndexREG.get(), 2) != 0 ? String.format("%s", new Object[] { Integer.parseInt(IndexREG.get(), 2) + ", " }) : "");
	    instr = instr + (Integer.parseInt(I.get(), 2) != 0 ? String.format("%s", new Object[] { Integer.parseInt(I.get(), 2) + ", " }) : "");
	    instr = instr + (Integer.parseInt(T.get(), 2) != 0 ? String.format("%s", new Object[] { Integer.parseInt(T.get(), 2) + ", " }) : "");
	    instr = instr + (Integer.parseInt(ADDR.get(), 2) != 0 ? String.format("%s", new Object[] { Integer.parseInt(ADDR.get(), 2) + ", " }) : "");
	    textField.append(instr.substring(0, instr.length() - 2) + "\n");
	  }
	
	public void incrementPC()
	  {
		System.out.println("PC: "+PC.get());
		System.out.println("CC: " +CC.get());
	    int count = Integer.parseInt(PC.get(), 2);
	    if (count < 8191)
	    {
	      count++;
	      this.PC.set(Long.toBinaryString(count));
	    }
	    else
	    {
	      this.PC.set(Long.toBinaryString(count));
	    }
	    PCvalue.setText(PC.get());
	    CCvalue.setText(CC.get());
	  }


	/*
	 * Main Method to start the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainConsole frame = new MainConsole();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
