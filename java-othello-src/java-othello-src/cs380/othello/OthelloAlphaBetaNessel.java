package cs380.othello;

import java.util.List;

public class OthelloAlphaBetaNessel extends OthelloPlayer {
	
	private int maxDepth;
	private int playerNumber;
	
	public OthelloAlphaBetaNessel(int depth, int player) {
		maxDepth = depth;
		playerNumber = player;
	}
      
    public OthelloMove getMove(OthelloState state) {
        
    	if(playerNumber == state.PLAYER1) {
        	return maxMove(state);
    	}
    	
    	return minMove(state);
    	
    }
    
    public OthelloMove maxMove(OthelloState state) {
    	
    	int v = -10000;
    	List<OthelloMove> moves = state.generateMoves();
    	OthelloMove maxMove = null;
    	
    	for(OthelloMove move : moves) {
    		OthelloState curState = state.applyMoveCloning(move);
    		int max = getMin(curState, maxDepth-1, 10001, -10001);
    		
        	if(max > v) {
        		v = max;
        		maxMove = move;
        	}
    	}
    	
    	return maxMove;
    }
    
    
    public OthelloMove minMove(OthelloState state) {
    	
    	int v = 10000;
    	List<OthelloMove> moves = state.generateMoves();
    	OthelloMove minMove = null;
    	
    	for(OthelloMove move : moves) {
    		OthelloState curState = state.applyMoveCloning(move);
    		int min = getMax(curState, maxDepth-1, 10001, -10001);
    		
        	if(min < v) {
        		v = min;
        		minMove = move;
        	}
    	}
    	
    	return minMove;
    }
    
    public int getMin(OthelloState state, int depth, int alpha, int beta) {
    	
    	if(state.gameOver()) {
    		return state.score();
    	}
    	
    	if(depth < 1) {
    		return state.score();
    	}
    	
    	int v = 10000;
    	List<OthelloMove> moves = state.generateMoves();
    	
    	if(moves.isEmpty()) {
    		return state.score();
    	}
    	
    	for(OthelloMove move : moves) {
    		OthelloState curState = state.applyMoveCloning(move);
    		int max = getMax(curState, depth-1, alpha, beta);
    		
        	if(max < v) {
        		v = max;
        	}
        	
        	if(v < beta) {
        		beta = v;
        	}
        	
        	if(v <= alpha) {
        		return v;
        	}
        	
    	}
    	
    	return v;
    }
    
    public int getMax(OthelloState state, int depth, int alpha, int beta) {
    	
    	if(state.gameOver()) {
    		return state.score();
    	}
    	
    	if(depth < 1) {
    		return state.score();
    	}    	
    	
    	int v = -10000;
    	List<OthelloMove> moves = state.generateMoves();
    	
    	if(moves.isEmpty()) {
    		return state.score();
    	}
    	
    	for(OthelloMove move : moves) {
    		OthelloState curState = state.applyMoveCloning(move);
    		int min = getMin(curState, depth-1, alpha, beta);
    		
    		if(min > v) {
        		v = min;
        	}	
    		
    		if(v > alpha) {
    			alpha = v;
    		}
    		
    		if(v >= beta) {
    			return v;
    		}
    		
    	}
    	
    	return v;
    }
    

}
