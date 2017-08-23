package com.app.abacus.main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.app.abacus.panel.AbacusPanel;
import com.app.abacus.panel.exception.AbacusException;
import com.app.instruction.InstructionPanel;
import com.app.instructions.compiler.Action;

public class Performer implements Runnable {

	LinkedHashMap<String, HashMap<String, List<Action>>> data;

	private Thread readerThread;
	InstructionPanel instructionPanel;
	AbacusPanel abacusPanel;

	/**
	 * @return the instructionPanel
	 */
	public InstructionPanel getInstructionPanel() {
		return instructionPanel;
	}

	/**
	 * @param instructionPanel
	 *            the instructionPanel to set
	 */
	public void setInstructionPanel(InstructionPanel instructionPanel) {
		this.instructionPanel = instructionPanel;
	}

	/**
	 * @return the abacusPanel
	 */
	public AbacusPanel getAbacusPanel() {
		return abacusPanel;
	}

	/**
	 * @param abacusPanel
	 *            the abacusPanel to set
	 */
	public void setAbacusPanel(AbacusPanel abacusPanel) {
		this.abacusPanel = abacusPanel;
	}

	/**
	 * @return the data
	 */
	public LinkedHashMap<String, HashMap<String, List<Action>>> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(LinkedHashMap<String, HashMap<String, List<Action>>> data) {
		this.data = data;
	}

	public void startReading() {
		readerThread = new Thread(this);
		readerThread.start();
	}

	public void start_instructing() {
		Set<Entry<String, HashMap<String, List<Action>>>> entrySet = data.entrySet();
		for (Entry<String, HashMap<String, List<Action>>> entry : entrySet) {
			Object[] tableRow = new Object[3];
			String key = entry.getKey();
			tableRow[0] = key;

			HashMap<String, List<Action>> map = entry.getValue();
			Set<Entry<String, List<Action>>> sEntry = map.entrySet();
			for (Entry<String, List<Action>> entry2 : sEntry) {
				String instruction = entry2.getKey();

				instructionPanel.performinstruction(instruction, instructionPanel);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				List<Action> listOfActions = entry2.getValue();
				StringBuffer actBuf = new StringBuffer();
				for (Action action : listOfActions) {

					if (action.getActionName().contains("HighlightFrame")) {
						abacusPanel.highlightFrame();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().contains("HighlightRods")) {
						abacusPanel.highlightRods();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("HighlightBeam")) {
						abacusPanel.highlightDivider();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("HighlightLowerBeads")) {
						abacusPanel.highlightLowerBeads();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("HighlightUpperBeads")) {
						abacusPanel.highlightUpperBeads();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("HighlightDots")) {
						abacusPanel.resetAbacus();
					} else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** first add rod action performed */

					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "leftIndex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("Display")) {
						System.out.println("didplay function");
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** second add rod action performed */
					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "leftIndex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("Display")) {
						System.out.println("didplay function");
					} else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** third add rod action performed */
					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "leftIndex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("Display")) {
						System.out.println("didplay function");
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}

					/** fourth add rod action performed */

					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "leftIndex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("Display")) {
						System.out.println("didplay function");
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** fifth add rod action performed */
					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveHeavenBeadUp(action.getRodNumber(), "leftIndex");
						} catch (AbacusException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("Display")) {
						System.out.println("didplay function");
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** reset add function */

					else if (action.getActionName().contains("Reset")) {
						abacusPanel.resetAbacus();
					}

					/** two times wait function */

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** again Add rod action performed on row38 */

					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userightindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					/** wait function again */

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** again add rod */

					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userightindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					/** add rod again */

					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userightindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					/** two times wait function */

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** minus rod row 42 */

					else if (action.getActionName().contains("MinusRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userightindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("MinusRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "useleftindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** minus rod row 44 */
					
					else if (action.getActionName().contains("MinusRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userighttindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else if (action.getActionName().contains("Reset")) {
						abacusPanel.resetAbacus();
					}
					
					
					/** again last add rod*/
					

					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userighttindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					

					/** again add rod */
					
					else if (action.getActionName().contains("AddRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userighttindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else if (action.getActionName().contains("Wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					/** add last */
					
					else if (action.getActionName().contains("MinusRod")) {
						try {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "userighttindex");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				}
			}
		}
	}
	// odel.addRow(tableRow);

	@Override
	public void run() {
		start_instructing();
		// TODO Auto-generated method stub
	}
}
