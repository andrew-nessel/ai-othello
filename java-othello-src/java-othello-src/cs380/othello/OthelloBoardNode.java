package cs380.othello;

public class OthelloBoardNode {
	
	private OthelloBoardNode parent;
	private OthelloBoardNode child;
	private OthelloMove myMove;
	private int scoreTotal;
	private int timesVisited;
	private boolean isRoot;
	
	public OthelloBoardNode(boolean root) {
		isRoot = root;
		timesVisited = 0;
		scoreTotal = 0;
	}
	
	public OthelloBoardNode(boolean root, OthelloBoardNode newParent, OthelloBoardNode newChild, OthelloMove move) {
		isRoot = root;
		parent = newParent;
		child = newChild;
		myMove = move;
		timesVisited = 0;
		scoreTotal = 0;
	}
	
	public boolean isRoot() {
		return isRoot;
	}
	
	public OthelloMove getMove() {
		return myMove;
	}
	
	public void setMove(OthelloMove move) {
		myMove = move;
	}
	
	public OthelloBoardNode getParent() {
		return parent;
	}
	
	public void setParent(OthelloBoardNode newParent) {
		parent = newParent;
	}
	
	public OthelloBoardNode getChild() {
		return child;
	}
	
	public void setChild(OthelloBoardNode newChild) {
		child = newChild;
	}
	
	public int getScore() {
		return scoreTotal/timesVisited;
	}
	
	public void addScore(int score) {
		scoreTotal += score;
	}
	
	public int getTimesVisited() {
		return timesVisited;
	}
	
	public void addTimeVisited() {
		timesVisited += 1;
	}

}
