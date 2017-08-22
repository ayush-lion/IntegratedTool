/**
 * 
 */
package com.app.abacus.main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.app.abacus.Finger;
import com.app.abacus.panel.AbacusPanel;
import com.app.abacus.panel.exception.AbacusException;
import com.app.instruction.InstructionPanel;
import com.app.instructions.compiler.Action;
import com.app.instructions.compiler.InstructionCompiler;
import com.app.instructions.compiler.exception.CompilerException;

/**
 * @author prashant.joshi
 *
 */
public class TestAbacusComponent extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// Top panels
	private JPanel abacusTopPanel;
	private AbacusPanel panel;
	private JPanel playPanel;
	String filenameatt;
	//Abacus Top Panel
	private JCheckBox doWeNeedFrame;
	private JCheckBox doWeNeedFingers;
	private JTextField attrTxt;
	private JButton loadAbacus;
	private JButton showAbacus;
	
	//Abacus Bead Up Panel
	private JTextField bUpRod;
	private JTextField bUpBead;
	private JButton bUpBut;
	
	//Abacus Bead Down Panel
	private JTextField bDownRod;
	private JTextField bDownBead;
	private JButton bDownBut;
	private JButton bBlinkBut;
	private JButton reset;
	
	//Abacus Highlight ROD Panel
	private JTextField hRod;
	private JButton hRodBut;
	
	//Abacus Highlight Bead Panel
	private JTextField hBead;
	private JButton hBeadBut;
	
	//Abacus Highlight Left Panel
	private JButton hFrameButton;
	private JButton hRodButton;
	private JButton hBeadButton;
	
	//Abacus Highlight Right Panel
	private JButton hLBeadButton;
	private JButton hUBeadButton;
	private JButton hBeamButton;

	private InstructionPanel instructionpanel;
	
	private Performer performer;

	private InstructionCompiler complier;
	
	public TestAbacusComponent() throws Throwable {
		try {
			this.getContentPane().setLayout(null);
			this.setResizable(false);
			this.setTitle("Abacus. Lets start learning mind math !!!");
			this.setBounds(100, 100, 1050, 860);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBackground(Color.WHITE);
			
			/* Create Abacus Panel */
			panel = new AbacusPanel();
			panel.setBounds(10, 50, this.getWidth() - 20, this.getHeight() - 400);
			setUpAbacusTopPanel();
			instructionpanel = new InstructionPanel(null);
			instructionpanel.setBounds(10, 500,this.getWidth() - 20,280);
			/* Create Abacus Play Controls */
			setupPlayPanel();
			
			/* Add Components to Frame */
			this.getContentPane().add(abacusTopPanel);
			this.getContentPane().add(panel);
			this.getContentPane().add(instructionpanel);
		//	playPanel.repaint();
			
			this.setVisible(true);
			
			//panel.initializeAbacus();
		} catch ( Exception e ) { e.printStackTrace(); }
	}
	
	public void showPanel() throws IOException {
		this.setVisible(true);
		try {
			panel.initializeAbacus();
			instructionpanel.Initialize_Instruction_Panel(instructionpanel);
			compile("/Users/Panwar/Desktop/AppInstruction.xlsx");
		} catch (AbacusException e) { e.printStackTrace(); }
	}
	
	private void setupPlayPanel() {
		playPanel = new JPanel(new GridLayout(3, 6));
		setUpFirstControlPanel();
		setUpSecondControlPanel();
		setUpThirdControlPanel();
		playPanel.setBounds(0, this.getHeight() - 130, this.getWidth(), 90);
	}
	
	private void setUpFirstControlPanel() {
		bUpRod = new JTextField();
		bUpBead = new JTextField();
		bUpBut = new JButton("Move Bead UP");
		bUpBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rodNum = Integer.valueOf(bUpRod.getText());
					int beadNum = Integer.valueOf(bUpBead.getText());
					if(beadNum == 5) 
						panel.moveHeavenBeadUp(rodNum, Finger.LEFT_INDEX);
					else
						panel.moveEarthBeadUp(rodNum, beadNum, Finger.LEFT_THUMB);
				} catch (AbacusException ae) {
					if(ae instanceof AbacusException)
						JOptionPane.showMessageDialog(null, ((AbacusException)ae).getCustomizedMessage(), "InfoBox: Abacus Learning", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, ae.getMessage(), "InfoBox: Abacus Learning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		bDownRod = new JTextField();
		bDownBead = new JTextField();
		bDownBut = new JButton("Move Bead Down");
		bDownBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rodNum = Integer.valueOf(bDownRod.getText());
					int beadNum = Integer.valueOf(bDownBead.getText());
					if(beadNum == 5) 
						panel.moveHeavenBeadDown(rodNum, Finger.RIGHT_THUMB);
					else
						panel.moveEarthBeadDown(rodNum, beadNum, Finger.RIGHT_INDEX);
				} catch (Exception ae) {
					if(ae instanceof AbacusException)
						JOptionPane.showMessageDialog(null, ((AbacusException)ae).getCustomizedMessage(), "InfoBox: Abacus Learning", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, ae.getMessage(), "InfoBox: Abacus Learning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		playPanel.add(bUpRod);
		playPanel.add(bUpBead);
		playPanel.add(bUpBut);
		playPanel.add(bDownRod);
		playPanel.add(bDownBead);
		playPanel.add(bDownBut);
	}
	
	private void setUpSecondControlPanel() {
		
		//Abacus Highlight ROD Panel
		hRod = new JTextField();
		hRodBut = new JButton("HighLight Rod");
		hRodBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rodNum = Integer.valueOf(hRod.getText());
					panel.highlightSpecificRod(rodNum);
				} catch (Exception ae) {
						JOptionPane.showMessageDialog(null, ae.getMessage(), "InfoBox: Abacus Learning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		//Abacus Highlight Bead Panel
		hBead = new JTextField();
		hBeadBut = new JButton("HighLight Bead");
		hBeadBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rodNum = Integer.valueOf(hRod.getText());
					int beadNum = Integer.valueOf(hBead.getText());
					panel.highlightSpecificBead(rodNum, beadNum);
				} catch (Exception ae) {
						JOptionPane.showMessageDialog(null, ae.getMessage(), "InfoBox: Abacus Learning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		bBlinkBut = new JButton("Blink Bead");
		bBlinkBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rodNum = Integer.valueOf(hRod.getText());
					int beadNum = Integer.valueOf(hBead.getText());
					
					if(bBlinkBut.getText().equalsIgnoreCase("Blink Bead")) {
						bBlinkBut.setText("Stop Blink");
						panel.startBlinkBead(rodNum, beadNum);
					} else {
						bBlinkBut.setText("Blink Bead");
						panel.stopBlinkBead();
					}
				} catch (Exception ae) {
						JOptionPane.showMessageDialog(null, ae.getMessage(), "InfoBox: Abacus Learning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bBlinkBut.setText("Blink Bead");
				panel.showAbacus();
			}
		});
		
		// Add Components
		playPanel.add(hRod);
		playPanel.add(hRodBut);
		playPanel.add(hBead);
		playPanel.add(hBeadBut);
		playPanel.add(bBlinkBut);
		playPanel.add(reset);
	}
	
	private void setUpThirdControlPanel() {
		
		//Abacus Highlight Left Panel
		hFrameButton = new JButton("HighLight Frame");
		hFrameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.highlightFrame();
			}
		});
		
		hRodButton = new JButton("HighLight Rods");
		hRodButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.highlightRods();
			}
		});
		
		hBeadButton = new JButton("HighLight Beads");
		hBeadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.highlightBeads();
			}
		});
		
		//Abacus Highlight Right Panel
		hLBeadButton = new JButton("HighLight Lower Beads");
		hLBeadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.highlightLowerBeads();
			}
		});
		
		hUBeadButton = new JButton("HighLight Upper Beads");
		hUBeadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.highlightUpperBeads();
			}
		});
		
		hBeamButton = new JButton("HighLight Beam");
		hBeamButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.highlightDivider();
			}
		});
		
		playPanel.add(hFrameButton);
		playPanel.add(hRodButton);
		playPanel.add(hBeadButton);
		playPanel.add(hLBeadButton);
		playPanel.add(hUBeadButton);
		playPanel.add(hBeamButton);
	}
	
	private void setUpAbacusTopPanel() {
		abacusTopPanel = new JPanel(new GridLayout(1, 5));
		abacusTopPanel.setBounds(0, 10, this.getWidth(), 40);
		
		// Buttons
		loadAbacus = new JButton("Load Abacus Properties");
		showAbacus = new JButton("Show Abacus");
		
		// Frame
		doWeNeedFrame = new JCheckBox("Show Frame");
		doWeNeedFrame.setSelected(true);
		doWeNeedFrame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(doWeNeedFrame.isSelected()) {
					panel.hideFrame(Boolean.TRUE);
				} else {
					panel.hideFrame(Boolean.FALSE);
				}
			}
		});
		
		// Fingers
		doWeNeedFingers = new JCheckBox("Show Fingers");
		doWeNeedFingers.setSelected(true);
		doWeNeedFingers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(doWeNeedFingers.isSelected()) {
					panel.hideFingers(Boolean.TRUE);
				} else {
					panel.hideFingers(Boolean.FALSE);
				}
			}
		});
		
		attrTxt = new JTextField();
		
		// Show Abacus Button
		showAbacus.setEnabled(Boolean.FALSE);
		showAbacus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					showAbacus.setEnabled(false);
					panel.initializeAbacus();
					panel.repaint();
					
					if(panel.getAbacus().canWeDisplayFrame()) {
						doWeNeedFrame.setSelected(true);
					} else {
						doWeNeedFrame.setSelected(false);
					}
					
					if(panel.getAbacus().canWeNeedToDisplayFingers()) {
						doWeNeedFingers.setSelected(true);
					} else {
						doWeNeedFingers.setSelected(false);
					}
					
				} catch (AbacusException e1) { e1.printStackTrace(); }
			}
		});
		
		// Load Abacus
		loadAbacus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jFileChooser = new JFileChooser();
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						attrTxt.setText(selectedFile.getAbsolutePath());
					//	panel.setAbacusAttributesFileName(attrTxt.getText());
					//	showAbacus.setEnabled(true);
					}
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		
		
		// Add components in Panel
		abacusTopPanel.add(doWeNeedFrame);
		abacusTopPanel.add(doWeNeedFingers);
		abacusTopPanel.add(attrTxt);
		abacusTopPanel.add(loadAbacus);
		abacusTopPanel.add(showAbacus);
	}
	
	public void startcompilation(String filename)
	{
		compile(filename);
		
	}
	
	private void compile(String filename) {
		try {
			complier = new InstructionCompiler(filename);
			boolean isAllSet = complier.compileInstructions();
			if(!isAllSet) {
				JOptionPane.showMessageDialog(null, "Found Errors!!!. Please resolve!!!", "InfoBox: Abacus Compiler", JOptionPane.INFORMATION_MESSAGE);
				//displayErrors(complier.getMapOfErrors());
			} else {
				JOptionPane.showMessageDialog(null, "No Errors!!!.", "InfoBox: Abacus Compiler", JOptionPane.INFORMATION_MESSAGE);
				start_instructions(complier.getInstructionData());
			}
		} catch (CompilerException e1) { e1.printStackTrace(); }
	}
	
	public void start_instructions(LinkedHashMap<String, HashMap<String, List<Action>>> linkedHashMap)
	{
		
		performer = new Performer();
		performer.setAbacusPanel(panel);
		performer.setInstructionPanel(instructionpanel);
		performer.setData(linkedHashMap);
		performer.startReading();
			//odel.addRow(tableRow);
			}

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		TestAbacusComponent ob = new TestAbacusComponent();
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
			   try {
				ob.showPanel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		});
		
	}

}
