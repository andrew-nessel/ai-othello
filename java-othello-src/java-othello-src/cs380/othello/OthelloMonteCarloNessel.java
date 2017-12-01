package cs380.othello;

import java.util.List;
import java.util.Random;

public class OthelloMonteCarloNessel extends OthelloPlayer {
	
	private int PlayerNumber;
	private int iterations;
	
	OthelloMonteCarloNessel(int playerNumber, int iterations){
		PlayerNumber = playerNumber;
		this.iterations = iterations;
	}
	
	@Override
	public OthelloMove getMove(OthelloState state) {
		return MonteCarloTreeSearch(state);
	}
	
	OthelloMove MonteCarloTreeSearch(OthelloState startState){
		OthelloBoardNode root = new OthelloBoardNode(true, startState);
		
		for(int x = 0; x <= iterations; x++) {
			OthelloBoardNode node = treePolicy(root);
			if(node != null) {
				OthelloState defaultFinalState = defaultPolicy(node);
				backup(node, defaultFinalState.score());
			}
		}
		
		return bestChild(root).getMove(); 
	}
	
	OthelloBoardNode bestChild(OthelloBoardNode node){//find and return the best scoring child node
		
		List<OthelloBoardNode> children = node.getChildren();
		OthelloBoardNode best = node;
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
		
		OthelloState state = node.getState();
		List<OthelloMove> moves = state.generateMoves();
		List<OthelloBoardNode> children = node.getChildren();
		
		
		if(state.gameOver() || moves.isEmpty()) { //If 'node' is a terminal node (no actions can be performed). Then it returns "node"
			return node;
		}
		
		//If 'node' is not a terminal but all its children are in the tree, 
				//then: 90% of the times "nodetmp = bestChild(node)", 
				//and 10% of the times "nodetmp = [a child of node at random]" (if you are curious, this is called an epsilon-greedy strategy). 
				//Then, the function returns "treePolicy(nodetmp)"
		
		if((moves.size() == children.size())){
			
			Random r = new Random();
			int rand = r.nextInt(10);
			
			if(rand > 8) {
				return children.get(r.nextInt(children.size()));
			}
			
			return treePolicy(bestChild(node));
		}

		//If 'node' still has any children that are not in the tree, 
		//then it generates one of those children ('newnode'), 
		//it adds 'newnode' as a child to 'node', and returns 'newnode'.
		
		for(OthelloMove move : moves) {
			boolean contained = false;
			for(OthelloBoardNode child : children) {
				if(child.getMove() == move) {
					contained = true;
				}
			}
			if(!contained) {
				OthelloBoardNode newNode = new OthelloBoardNode(false, node, state.applyMoveCloning(move), move);
				node.addChild(newNode);
				return newNode;
			}
		}
		
		return null; //something went wrong :(
	}

	OthelloState defaultPolicy(OthelloBoardNode node){
		
		Random r = new Random();
		OthelloRandomPlayer rando = new OthelloRandomPlayer();
		
		OthelloState state = node.getState();
		int playerNum = 0;

		int otherPlayer = OthelloState.PLAYER1;
		if(PlayerNumber == OthelloState.PLAYER1) {
			otherPlayer = OthelloState.PLAYER2;
		}
		
		while(!state.gameOver()) {          
        
			// return one at random:
			if(playerNum == PlayerNumber) {
				
				List<OthelloMove> moves = state.generateMoves();   
				
				if (!moves.isEmpty()) {
					OthelloMove move = moves.get(r.nextInt(moves.size()));
					state.applyMove(move);
				}
				
				playerNum = otherPlayer;
			}else {
				state.applyMove(rando.getMove(state));
				playerNum = PlayerNumber;				
			}
			
		}
		
		return state;
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
