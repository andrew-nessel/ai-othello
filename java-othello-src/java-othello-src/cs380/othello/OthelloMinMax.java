package cs380.othello;

import java.util.List;

public class OthelloMinMax extends OthelloPlayer {
	
	private int maxDepth;
	private int playerNumber;
	
	public OthelloMinMax(int depth) {
		maxDepth = depth;
		playerNumber = playerNumber;
	}
      
    public OthelloMove getMove(OthelloState state) {
        
    	if(playerNumber == 0) {
        	return getMax(state, maxDepth);
    	}
    	
    	return getMin(state, maxDepth);
    	
    }
    
    public OthelloMove getMin(OthelloState state, int depth) {
    	
    	if(state.gameOver()) {
    		return null;
    	}
    	int v = 10000;
    	List<OthelloMove> moves = state.generateMoves();
    	
    	return null;
    }
    
    public OthelloMove getMax(OthelloState state, int depth) {
    	
    	if(state.gameOver()) {
    		return null;
    	}
    	int v = -10000;
    	List<OthelloMove> moves = state.generateMoves();
    	
    	return null;
    }
    
    
    
}