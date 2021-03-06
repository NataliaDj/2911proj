import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;


public class RightButtonPanel extends JPanel {
	private JButton undoButton;
	private JButton redoButton;
	private JButton newGameButton;
	private JButton hintButton;
	private JPanel showPlayer;
	private Circle c;
	private JLabel player;
	
	private JTextArea instructions;
	private JTextArea feedback;
	private JButton nextTut;
	private JButton prevTut;
	private int tutePassed;
	
	/**
	 * @param isTutorial = if it's tutorial mode,
	 * 		 isTutorial == true else isTutorial == false
	 */
	public RightButtonPanel(boolean isTutorial) {
		this.setBackground(new Color (201, 182, 129));
		this.tutePassed = 0;
		this.undoButton = new JButton("Undo");
		this.redoButton = new JButton("Redo");
		this.newGameButton = new JButton("Menu");
		this.hintButton = new JButton("Hint");
		this.instructions = new JTextArea();
		this.feedback = new JTextArea();
		this.nextTut = new JButton("Next");
		this.prevTut = new JButton("Back");
		if(!isTutorial) {
			initButtons();
		} else {
			initTutorial();
		}
	}

	/**
	 * Initialize showPlayer panel
	 * Contains Circle, showing the current player
	 * And label, showing the color (text) of the player
	 */
	private void initShowPlayer() {
		this.showPlayer = new JPanel();
		this.c = new Circle(-1,-1);
		this.player = new JLabel();
		this.player.setFont(new Font("Courier", Font.PLAIN,16));
		
		GroupLayout GLsmall = new GroupLayout(showPlayer);
		GLsmall.setAutoCreateGaps(true);
		GLsmall.setAutoCreateContainerGaps(true);
		showPlayer.setLayout(GLsmall);
	
		GroupLayout.SequentialGroup topBottom = GLsmall.createSequentialGroup();
		topBottom.addComponent(this.c, 100, 100, 150);
	    topBottom.addComponent(this.player);
	    GroupLayout.ParallelGroup mid = GLsmall.createParallelGroup(GroupLayout.Alignment.CENTER);
		mid.addComponent(this.c, 100, 100, 160);
		mid.addComponent(this.player);
		GroupLayout.SequentialGroup leftRight = GLsmall.createSequentialGroup();
		leftRight.addGroup(mid);
		
		GLsmall.setHorizontalGroup(leftRight);
	    GLsmall.setVerticalGroup(topBottom);
		showPlayer.setBackground(new Color (201, 182, 129));
		showPlayer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true),
				"Current Player", 0, 0, new Font("Courier", Font.PLAIN,14), Color.BLACK));
		
		this.player.setHorizontalAlignment(SwingConstants.CENTER);
		c.setPreferredSize(new Dimension (80,80));
	}
	
	/**
	 * Initializes buttons for normal game mode (isTutorial == false)
	 */
	private void initButtons() {
		this.undoButton.setFont(new Font("Courier", Font.PLAIN,16));
		this.undoButton.setEnabled(false);
		this.redoButton.setFont(new Font("Courier", Font.PLAIN,16));
		this.redoButton.setEnabled(false);
		this.newGameButton.setFont(new Font("Courier", Font.PLAIN,16));
		this.hintButton.setFont(new Font("Courier", Font.PLAIN,16));
		
		initShowPlayer();
		
		GroupLayout GL = new GroupLayout(this);
		GL.setAutoCreateGaps(true);
		GL.setAutoCreateContainerGaps(true);
		this.setLayout(GL);
		GroupLayout.SequentialGroup leftToRight = GL.createSequentialGroup();

		GroupLayout.ParallelGroup columnMiddle = GL.createParallelGroup(GroupLayout.Alignment.TRAILING);
		columnMiddle.addComponent(undoButton, 100, 100, 200);
		columnMiddle.addComponent(redoButton, 100, 100, 200);
	    columnMiddle.addComponent(newGameButton, 100, 100, 200);
	    columnMiddle.addComponent(hintButton, 100, 100, 200);
	    columnMiddle.addComponent(showPlayer, 100, 100, 200);
	    leftToRight.addGroup(columnMiddle);

		GroupLayout.SequentialGroup topToBottom = GL.createSequentialGroup();
	    topToBottom.addComponent(undoButton, 30, 50, 50);
	    topToBottom.addComponent(redoButton, 30, 50, 50);
	    topToBottom.addComponent(newGameButton, 30, 50, 50);
	    topToBottom.addComponent(hintButton, 30, 50, 50);
	    topToBottom.addPreferredGap(hintButton, showPlayer, LayoutStyle.ComponentPlacement.UNRELATED, 0, 500);
	    topToBottom.addComponent(showPlayer, 100, 150, 200);
	    GL.setHorizontalGroup(leftToRight);
	    GL.setVerticalGroup(topToBottom);
	}
	
	/**
	 * Initializes textAreas and buttons for tutorial mode (isTutorial == true)
	 */
	private void initTutorial() {

		this.instructions.setFont(new Font("Courier", Font.PLAIN,14));
		this.instructions.setText("Welcome to Connect Four!\n" + "Your color is red.\n" + "Click on a column to place your move.\n");
		this.instructions.setLineWrap(true);
		this.instructions.setWrapStyleWord(true);
		this.instructions.setOpaque(false);
		this.instructions.setEditable(false);
		
		this.feedback.setFont(new Font("Courier", Font.PLAIN,14));
		this.feedback.setText("Try placing your move on the third column from the left.");
		this.feedback.setLineWrap(true);
		this.feedback.setWrapStyleWord(true);
		this.feedback.setOpaque(false);
		this.feedback.setEditable(false);
		
		initShowPlayer();
		
		GroupLayout GL = new GroupLayout(this);
		GL.setAutoCreateGaps(true);
		GL.setAutoCreateContainerGaps(true);
		this.setLayout(GL);
		
		GroupLayout.SequentialGroup leftToRight = GL.createSequentialGroup();
		GroupLayout.ParallelGroup columnLeft = GL.createParallelGroup(GroupLayout.Alignment.LEADING);
		columnLeft.addComponent(instructions, 100, 100, 200);
		columnLeft.addComponent(feedback, 100, 100, 200);
		columnLeft.addComponent(undoButton, 100, 100, 200);
		columnLeft.addComponent(newGameButton, 100, 100, 200);
		columnLeft.addComponent(nextTut, 100, 100, 200);
		columnLeft.addComponent(showPlayer, 100, 100, 200);
	    leftToRight.addGroup(columnLeft);
	   
	    

		GroupLayout.SequentialGroup topToBottom = GL.createSequentialGroup();
		
	    topToBottom.addComponent(instructions, 50, 75, 100);
	    topToBottom.addComponent(feedback, 40, 60, 75);
	    topToBottom.addComponent(undoButton, 20, 40, 40);
	    topToBottom.addComponent(nextTut, 20, 40, 40);
	    topToBottom.addComponent(newGameButton, 20, 40, 40);
	    topToBottom.addPreferredGap(newGameButton, showPlayer, LayoutStyle.ComponentPlacement.UNRELATED, 0, 500);
	    topToBottom.addComponent(showPlayer, 160, 180, 240);
	    GL.setHorizontalGroup(leftToRight);
	    GL.setVerticalGroup(topToBottom);
	    
	}
	
	/**
	 * @return JButton
	 */
	public JButton getUndoButton() {
		return this.undoButton;
	}
	
	/**
	 * @return JButton
	 */
	public JButton getRedoButton() {
		return this.redoButton;
	}
	
	/**
	 * @return JButton
	 */
	public JButton getNewGameButton() {
		return this.newGameButton;
	}
	
	/**
	 * @return JButton
	 */
	public JButton getHintButton() {
		return this.hintButton;
	}
	
	/**
	 * Sets the color of the Circle inside showPlayer panel
	 * Also sets the text of the JLabel to show the color of current player
	 * @param 	player == 0 := red
	 * 			player == 1 := yellow
	 */
	public void setColor(int player) {
	    if (player == 0) {
	    	c.setValue(0);
	    	this.player.setText("Red");
	    } else if (player == 1) {
	    	c.setValue(1);
	    	this.player.setText("Yellow");

	    }
	}

	/**
	 * @return JTextArea
	 */
	public JTextArea getInstructions() {
		return instructions;
	}

	/**
	 * @return JTextArea
	 */
	public JTextArea getFeedback() {
		return feedback;
	}

	/**
	 * @return JButton
	 */
	public JButton getNextTut() {
		return nextTut;
	}
	
	/**
	 * @return JButton
	 */
	public JButton getPrevTut() {
		return prevTut;
	}

	/**
	 * @return int
	 */
	public int getTutePassed() {
		return tutePassed;
	}
	
	/**
	 * @precond tutePassed >= 0
	 * @param tutePassed
	 */
	public void setTutePassed(int tutePassed) {
		this.tutePassed = tutePassed;
	}
	
}
