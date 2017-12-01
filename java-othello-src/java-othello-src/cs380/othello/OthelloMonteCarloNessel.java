package cs380.othello;

import java.util.List;
import java.util.Random;

public class OthelloMonteCarloNessel {
	
	private int PlayerNumber;	
	
	OthelloMove MonteCarloTreeSearch(OthelloState startState, int iterations){
		return null; //NOT FINISHED
	}
	
	OthelloBoardNode createNode(){
		return null; //NOT FINISHED
	}
	
	OthelloBoardNode bestChild(OthelloBoardNode node){//find and return the best scoring child node
		
		List<OthelloBoardNode> children = node.getChildren();
		OthelloBoardNode best = null;
		int lowestScore = 100000;
		int highestScore = -100000;
        
		for (OthelloBoardNode child : children) {
			int childScore = child.getScore();
			if((PlayerNumber == OthelloState.PLAYER1) && (childScore>highestScore)){
				best = child;
				highestScore = childScore;
			}else if((PlayerNumber == OthelloState.PLAYER2) && (childScore<lowestScore)) {
				best = child;
				lowestScore = childScore;
			}
		}
		
		return best; //NOT FINISHED
	}
		
	OthelloBoardNode treePolicy(OthelloBoardNode node){
		return null; //NOT FINISHED
	}

	OthelloBoardNode defautPolicy(OthelloBoardNode node){
		
		Random r = new Random();
		
		OthelloState state = node.getState();
		
		while(!state.gameOver()) {
			List<OthelloMove> moves = state.generateMoves();           
        
			// return one at random:
			if (!moves.isEmpty()) {
				OthelloMove move = moves.get(r.nextInt(moves.size()));
				state.applyMove(move);
			}
		}
		
		return state; //NOT FINISHED
	}

	int score(OthelloState state){ //a new heuristic for othello
		return 0; //NOT FINISHED
	}

	void backup(OthelloBoardNode node, int score){ //go back up the tree and update the scores and times visited
		
		node.addScore(score);
		node.addTimeVisited();
		
		if(!node.isRoot()) {
			backup(node.getParent(), score);
		}		
		
		return;
	}

}
