package cs380.othello;

import java.util.List;

public class OthelloBoardNode {
	
	private OthelloBoardNode parent;
	private List<OthelloBoardNode> children;
	private OthelloMove move;
	private OthelloState state;
	private int scoreTotal;
	private int timesVisited;
	private boolean isRoot;
	
	public OthelloBoardNode(boolean root) {
		isRoot = root;
		timesVisited = 0;
		scoreTotal = 0;
	}
	
	public OthelloBoardNode(boolean root, OthelloBoardNode parent, OthelloBoardNode child, OthelloState state, OthelloMove move) {
		isRoot = root;
		this.parent = parent;
		this.state = state;
		this.move = move;
		timesVisited = 0;
		scoreTotal = 0;
	}
	
	public boolean isRoot() {
		return isRoot;
	}
	
	public OthelloState getState() {
		return state;
	}
	
	public void setState(OthelloState state) {
		this.state = state;
	}
	
	public OthelloMove getMove() {
		return move;
	}
	
	public void setMove(OthelloMove move) {
		this.move = move;
	}
	
	public OthelloBoardNode getParent() {
		return parent;
	}
	
	public void setParent(OthelloBoardNode parent) {
		this.parent = parent;
	}
	
	public List<OthelloBoardNode> getChildren() {
		return children;
	}
	
	public void addChild(OthelloBoardNode child) {
		children.add(child);
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
