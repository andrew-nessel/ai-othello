package cs380.othello;


public class HW4 {
	
	public static void main(String args[]) {
	    	
	    	final long startTime = System.currentTimeMillis();
	    	
	    	int PLAYER1 = OthelloState.PLAYER1;
	    	//int PLAYER2 = OthelloState.PLAYER2;
	    	
	    	String algorithm = args[0];
	    	OthelloPlayer players[] = {new OthelloMinMaxNessel(4, PLAYER1), new OthelloRandomPlayer()};


	    	// Create the game state with the initial position for an 8x8 board:
	    	OthelloState state = new OthelloState(8);
	    	
	    	//determine which algorithm to use based on parameters
	    	if(algorithm.equals("mm")) {
	    		int depth = Integer.parseInt(args[1]);
	    		OthelloPlayer temp[] = {new OthelloMinMaxNessel(depth, PLAYER1), new OthelloRandomPlayer()}; 
	    		players = temp;
	    	}else if (algorithm.equals("ab")) {
	    		int depth = Integer.parseInt(args[1]);
	    		OthelloPlayer temp[] = {new OthelloAlphaBetaNessel(depth, PLAYER1), new OthelloRandomPlayer()}; 
	    		players = temp;
	    	}else if (algorithm.equals("to")) {
	    		long time = Long.parseLong(args[1]);
	    		OthelloPlayer temp[] = {new OthelloNesselTournament(time, PLAYER1), new OthelloRandomPlayer()}; 
	    		players = temp;
	    	}else {
	    		System.out.println("Something went wrong with the parameters. Please try again or refer to README");
	    		return;
	    	}
	        
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
	    	
	    	final long endTime = System.currentTimeMillis();
	    	
	    	System.out.println("Total execution time: " + (endTime - startTime) / 1000);

	    }  

}
