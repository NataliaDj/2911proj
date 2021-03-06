
import java.util.ArrayList;
import java.util.Stack;


public class GameEngine {
	private GameState gs;
	private Stack<Integer> pastMoves;	//Stores a list of all moves made
	private Stack<Integer> pastUndoes;
	private AIInterface computer;
	private int ai;
	private AIInterface hint;
	//place constructor here
	public GameEngine() {
		gs = new GameState();
		pastMoves = new Stack<Integer>();	//Stores a list of all moves made
		pastUndoes = new Stack<Integer>();
		computer = null;
		hint = null;
		System.out.println("----------------------------");
		System.out.println("New Game started");
		this.ai = 0;
	}
	
	/**
	 * Checks if a move is valid
	 * @param column	The column the move is inserting into
	 * @return	The row to insert into if the move is valid, otherwise -1
	 */
	public int validMove(int column) {
		ArrayList<ArrayList<Integer>> board = gs.getBoard();
		int size = board.get(column).size();
		if (size < 6) {
			return (5 - size);
		}
		return -1;
	}
	
	public int getPlayer() {
		return gs.getPlayer();
	}
	/**
	 * makes a move
	 * PRECONDITION: column >= 0 && column < 7
	 * @param column	The column to add to
	 */
	public void makeMove(int column) {
		System.out.println("----------------------------");
		System.out.println("Player " + getPlayer() + "'s turn");
		System.out.println("0 is red and 1 is yellow");
		gs.add(column);
		pastMoves.push(column);		//add a pastmove
		for (int i = 5; i >= 0; i--) {
			for (ArrayList<Integer> a: gs.getBoard()) {
				if (a.size() <= i) {
					System.out.print(" - ");
				} else {
					System.out.print(" " + a .get(i) + " ");
				}
			}
			System.out.print("\n");
		}
		//empty pastUndoes, cannot redo anything before this
		while (!pastUndoes.empty()) {	
			pastUndoes.pop();
		}
	}
	
	/**
	 * undoes the most recent move
	 * @precondition !pastMoves.isEmpty() 
	 * @return the column of move undone
	 */
	public int undoMove() {
		
		//removes a move from the moves list
		int move = pastMoves.pop();
		
		//add the move to be undo-ed to the undoes list
		pastUndoes.push(move);
		
		//undo the move
		if (move < 7) {
			gs.remove(move);
		} else {
			gs.push(move - 7);
		}
		
		return move;
	}

	/**
	 * Check if an undo can be done or not
	 * @return	True if an undo can be done, otherwise false
	 */
	public boolean undoAvailable() {
		if (pastMoves.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Redoes a move
	 * @precondition !pastUndoes.isEmpty()
	 * @return the column of the move redone
	 */
	public int redoMove() {
		
		//removes a move from the undoes list
		int move = pastUndoes.pop();
		
		//add the move to the pastMoves
		pastMoves.push(move);
		
		//make the move
		if (move < 7) {
			gs.add(move);
		} else {
			gs.popOut(move - 7);
		}
		
		return move;
	}
	
	/**
	 * Check if a redo can be done or not
	 * @return	True if a redo can be done, otherwise false
	 */
	public boolean redoAvailable() {
		if (pastUndoes.empty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * placeholder
	 * @return
	 */
	public int callAi() {
		return computer.decideMove(gs);	
	}
	
	/**
	 * call the hint AI
	 * @return
	 */
	public int callHint() {
		return hint.decideMove(gs);
	}
	
	/**
	 * call the win condition check from game state
	 * @param column
	 * @param player
	 * @return
	 */
	public boolean checkWinCond(int column, int player) {
		return gs.winCond(column, player);
	}
	
	/**
	 * Check if the whole board is full
	 * 
	 * @return	true if there is a draw, otherwise false
	 */
	public boolean checkDrawCond() {
		if (gs.isFull()) {
			return true;
		}
		return false;
	}
	/**
	 * set the AI according to what the user choose
	 * @param int
	 */
	public void setComputer(int level) {
		if (level == 1) {
			this.computer = new AIEasy();
			this.ai = 1;
			this.hint = new AIMed();
		} else if (level == 2) {
			this.computer = new AIMed();
			this.ai = 2;
			this.hint = new AIHard();
		} else if (level == 3){
			this.computer = new AIHard();
			this.ai = 3;
			this.hint = new AIMed();
		} else {
			this.computer = new AIHard();
		}
	}
	/**
	 * returns an int that represents the current AI
	 * @return int
	 */
	public int getAi() {
		return this.ai;
	}
	
	/**
	 * returns the array of int containing all the winning columns
	 * @return int[]
	 */
	public int[] getWinCol() {
		return gs.getWinCol();
	}
	
	/**
	 * returns the array of int containing all the winning rows
	 * @return int[]
	 */
	public int[] getWinRow() {
		return gs.getWinRow();
	}
}
