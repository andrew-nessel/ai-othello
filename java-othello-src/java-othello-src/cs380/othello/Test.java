package cs380.othello;

/**
 *
 * @author santi
 */
public class Test {
    
    
    public static void main(String args[]) {
    	
    	double wins = 0;
    	double draws = 0;
    	int plays = 100;
    	
    	for(int x = 0; x <= plays; x++) {
    		// Create the game state with the initial position for an 8x8 board:
    		OthelloState state = new OthelloState(8);
    		
    		OthelloPlayer players[] = {new OthelloMinMaxNessel(4, state.PLAYER1), new OthelloRandomPlayer()}; int isPlayer = state.PLAYER1;
    		//OthelloPlayer players[] = {new OthelloRandomPlayer(), new OthelloMinMaxNessel(4, state.PLAYER2)}; int isPlayer = state.PLAYER2;
        
    		do{
    			// Display the current state in the console:
    			System.out.println("\nCurrent state, " + OthelloState.PLAYER_NAMES[state.nextPlayerToMove] + " to move:");
    			System.out.print(state);
            
    			// Get the move from the player:
    			OthelloMove move = players[state.nextPlayerToMove].getMove(state);            
    			System.out.println(move);
    			state = state.applyMoveCloning(move);            
    		}while(!state.gameOver());

    		// Show the result of the game:
    		System.out.println("\nFinal state with score: " + state.score());
    		System.out.println(state);
    		
    		if((state.score()>0) && (isPlayer == state.PLAYER1)) {
    			wins++;
    		}else if((state.score()<0) && (isPlayer == state.PLAYER2)){
    			wins++;
    		}else if(state.score() == 0) {
    			draws++;
    		}
    	}
    	
    	double winPercent = (wins/plays)*100;
    	double drawPercent = (draws/plays)*100;
    	System.out.println("MinMax won: " + winPercent + "%");
    	System.out.println("There was a draw: " + drawPercent + "%");
    }    
    
}
