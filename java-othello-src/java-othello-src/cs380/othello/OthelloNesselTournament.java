package cs380.othello;

import java.util.List;

public class OthelloNesselTournament extends OthelloPlayer{

	private long maxTime;
	private int playerNumber;
	
	public OthelloNesselTournament(long time, int player) {
		
		if(time/4 < 50) {
			maxTime = time - time/4;
		}else {
			maxTime = time - 50;
		}
		
		playerNumber = player;
	}
      
    public OthelloMove getMove(OthelloState state) {
        
    	long startTime = System.currentTimeMillis();
    	
    	OthelloMove myMove = null;
    	
    	for(int x = 1; x<10; x++) {
    		
    		OthelloMove newMove;
    		
    		if(playerNumber == OthelloState.PLAYER1) {
    			newMove = maxMove(state, x, startTime);
    		}else {
    			newMove = minMove(state, x, startTime);
    		}
    		
    		long endTime = System.currentTimeMillis();
    		
    		if((endTime - startTime)<maxTime) {
    			myMove = newMove;
    		}else {
    			break;
    		}
    		
    	}
    	
    	return myMove;
    	
    }
    
    public OthelloMove maxMove(OthelloState state, int maxDepth, long startTime) {
    	
    	int v = -10000;
    	List<OthelloMove> moves = state.generateMoves();
    	OthelloMove maxMove = null;
    	
    	for(OthelloMove move : moves) {
    		OthelloState curState = state.applyMoveCloning(move);
    		int max = getMin(curState, maxDepth-1, -10001, 10001, startTime);
    		
        	if(max > v) {
        		v = max;
        		maxMove = move;
        	}
        	
        	long endTime = System.currentTimeMillis();
    		if((endTime - startTime)>maxTime) {
    			return maxMove;
    		}
    	}
    	
    	return maxMove;
    }
    
    
    public OthelloMove minMove(OthelloState state, int maxDepth, long startTime) {
    	
    	int v = 10000;
    	List<OthelloMove> moves = state.generateMoves();
    	OthelloMove minMove = null;
    	
    	for(OthelloMove move : moves) {
    		OthelloState curState = state.applyMoveCloning(move);
    		int min = getMax(curState, maxDepth-1, -10001, 10001, startTime);
    		
        	if(min < v) {
        		v = min;
        		minMove = move;
        	}
        	
        	long endTime = System.currentTimeMillis();
    		if((endTime - startTime)>maxTime) {
    			return minMove;
    		}
    	}
    	
    	return minMove;
    }
    
    public int getMin(OthelloState state, int depth, int alpha, int beta, long startTime) {
    	
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
    		int max = getMax(curState, depth-1, alpha, beta, startTime);
    		
        	if(max < v) {
        		v = max;
        	}
        	
        	if(v < beta) {
        		beta = v;
        	}
        	
        	if(v <= alpha) {
        		return v;
        	}
        	
        	long endTime = System.currentTimeMillis();
    		if((endTime - startTime)>maxTime) {
    			return v;
    		}
        	
    	}
    	
    	return v;
    }
    
    public int getMax(OthelloState state, int depth, int alpha, int beta, long startTime) {
    	
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
    		int min = getMin(curState, depth-1, alpha, beta, startTime);
    		
    		if(min > v) {
        		v = min;
        	}	
    		
    		if(v > alpha) {
    			alpha = v;
    		}
    		
    		if(v >= beta) {
    			return v;
    		}
    		
    		long endTime = System.currentTimeMillis();
    		if((endTime - startTime)>maxTime) {
    			return v;
    		}
    		
    	}
    	
    	return v;
    }
    

}
