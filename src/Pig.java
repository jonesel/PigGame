/**
 * This is the skeleton of the Pig game. It is your job to fill in the empty
 functions to create a functional game of pig.
 * @author Adam Smith. Modified by Brian Wilkinson
 * @version 1.2
 */
class Pig {
    /**
     * The score needed to win a round.
     */
    public static final int WINNING_SCORE = 100;
    private int player1Score;
    private int player2Score;
    public static void main(String[] args) {
// intro, initialize players
        System.out.println("Welcome to Pig!");
        PigPlayer human = new HumanPigPlayer("Human");
        PigPlayer opponent = new ComputerPigPlayer("Skynet"); // could be human too
        int[] roundsWon = new int[2];
// round 1
        System.out.println("Round 1!");
        if (playRound(human, opponent)) roundsWon[0]++;
        else roundsWon[1]++;
        System.out.println();
// round 2
        System.out.println("Round 2!");
        if (playRound(opponent, human)) roundsWon[1]++;
        else roundsWon[0]++;
// report the final results
        reportFinalTally(roundsWon, human, opponent);
    }
/**
 * Do one round, crediting the winner.
 * @param player1 the first player
 * @param player2 the second player
 * @return true if player1 won, false if player2
 */
    private static boolean playRound(PigPlayer player1, PigPlayer player2)
    {
        int turnNum = 0;
        int score = 0;
        int opponentsScore = 0;
        playTurn(player1, turnNum, score, opponentsScore);
        // This function must do the following:
        // 1. Enter a loop, with player 1 taking a turn, then player 2.

        while (score <100 && opponentsScore<100)
        {
            score += playTurn(player1, turnNum, score, opponentsScore);
            if (score >= 100)
            {
                System.out.println("The winner is " + player1.getName());
                return true;
            }
           opponentsScore += playTurn(player2, turnNum, opponentsScore, score);
           turnNum++;
        }
        System.out.println("The winner is " + player2.getName());

        // 2. Keep track of each player's score and the turn number.
        // 3. When a player wins, print the winner, and break out of the loop.
        // 4. Return a boolean value

        return false;
    }
/**
 * Play a single turn, returning how many points the player got.
 * @param player the player whose turn it is
 * @param turnNum the turn number (0-indexed)
 * @param score the player's score
 * @param opponentsScore the player's adversary's score
 * @return the points that the player won
 */
    private static int playTurn(PigPlayer player, int turnNum, int score, int opponentsScore) {
        // This function must do the following:
        // 1. Call the player's beginTurn() method.
        player.beginTurn(score, opponentsScore);
        // 2. Loop so long as the player wants to continue rolling.
        boolean keepGoing = true;
        int rollNumber = 0;
        int poolSize = 0;
        while (keepGoing)
        {

            keepGoing = player.decideIfShouldRoll(turnNum, rollNumber, poolSize, score, opponentsScore);
            if (keepGoing)
            {
                int roll = (int) (Math.random()*6+1);
                System.out.println(player.getName() + " rolled a " + roll + ".");
                if (roll == 1)
                {
                    return 0;
                }
                poolSize += roll;
                rollNumber++;
            }
        }
        // 3. Roll a die:
            // a. If a 1 is rolled, return 0.
            // b. On any other roll, add it to the pool.
        // 4. If the loop ends, return the pool's value.
        // 5. Be sure to print events frequently, so the human player can see what's happening!
        return poolSize;
    }
    /**
     * Deliver a final report, indicating the overall winner after all
     rounds
     * have been played.
     * @param roundsWon an array of <code>int</code>s indicating the
    number of rounds each player won
     * @param player1 the first player
     * @param player2 the second player
     */

    private static void reportFinalTally(int[] roundsWon, PigPlayer player1, PigPlayer player2) {
        // This function must do the following:
        // 1. Print out both player's scores.
        // 2. Indicate who the winner was (or if there was a tie).
        System.out.println(player1.getName() + "'s score is " + roundsWon[0]);
        System.out.println(player2.getName() + "'s score is " + roundsWon[1]);
        if ( roundsWon[0] == roundsWon[1])
        {
            System.out.println("There was a tie.");
        }
        else if (roundsWon[0] > roundsWon[1])
        {
            System.out.println(player1.getName() + " won!");
        }
        else
        {
            System.out.println(player2.getName() + " won!");
        }




    }
}