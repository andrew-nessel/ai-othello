package cs380.othello;

import java.util.Random;

public class NesselTournamentTest {

	public static void main(String args[]) {
    	
		//final long startTime = System.currentTimeMillis();
	
		int PLAYER1 = OthelloState.PLAYER1;
		int PLAYER2 = OthelloState.PLAYER2;
	
    	double wins = 0;
    	double draws = 0;
    	int plays = 10;
    	int wentOver = 0;
    	long biggestTime = 0;

		Random r = new Random();
    	
    	//play some large amount of games set above
    	for(int x = 1; x <= plays; x++) {
    		
    		long maxTime = r.nextInt(500)+200;
    		System.out.println("max time is " + maxTime);
    		
    		// Create the game state with the initial position for an 8x8 board:
    		OthelloState state = new OthelloState(8);
    		
    		//OthelloPlayer players[] = {new OthelloNesselTournament(maxTime, PLAYER1), new OthelloRandomPlayer()}; int isPlayer = PLAYER1;
    		//OthelloPlayer players[] = {new OthelloRandomPlayer(), new OthelloNesselTournament(maxTime, PLAYER2)}; int isPlayer = PLAYER2;
    		OthelloPlayer players[] = {new OthelloAlphaBetaNessel(5, PLAYER1), new OthelloNesselTournament(maxTime, PLAYER2)}; int isPlayer = PLAYER2;
        
    		do{
    			// Display the current state in the console:
    			System.out.println("\nCurrent state, " + OthelloState.PLAYER_NAMES[state.nextPlayerToMove] + " to move:");
    			System.out.print(state);
    			
    			long startTime = System.currentTimeMillis();
    			
    			// Get the move from the player:
    			OthelloMove move = players[state.nextPlayerToMove].getMove(state); 
    			
    			long endTime = System.currentTimeMillis();
    			
    			System.out.println("Total execution time: " + (endTime - startTime));
    			
    			long curTime = endTime-startTime;
    			if(curTime > maxTime) {
    				wentOver++;
    				
    				if((maxTime-curTime)>biggestTime) {
    					biggestTime = maxTime-curTime;
    				}
    			}
    			
    			System.out.println(move);
    			state = state.applyMoveCloning(move);            
    		}while(!state.gameOver());

    		// Show the result of the game:
    		System.out.println("\nFinal state with score: " + state.score());
    		System.out.println(state);
    		
    		if((state.score()>0) && (isPlayer == PLAYER1)) {
    			wins++;
    		}else if((state.score()<0) && (isPlayer == PLAYER2)){
    			wins++;
    		}else if(state.score() == 0) {
    			draws++;
    		}
    	}
    	
    	//figure out how long this all took
    	//final long endTime = System.currentTimeMillis();
    	
    	//System.out.println("Total execution time: " + (endTime - startTime) / 1000);
    	
    	//figure out win percentage and draw percentage
    	double winPercent = (wins/plays)*100;
    	double drawPercent = (draws/plays)*100;
    	System.out.println("AlphaBeta won: " + winPercent + "%");
    	System.out.println("There was a draw: " + drawPercent + "%");
    	System.out.println("We went over " + wentOver);
    	System.out.println("Worst time was " + biggestTime);
    }  

}
