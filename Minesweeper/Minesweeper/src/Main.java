import java.util.Scanner;

public class Main {
    // Main method to start the game
    public static void main(String[] args) {
        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(2, 2, 1);
        game.displayBoard();
        //System.exit(0);
        // Game loop

        Scanner scan = new Scanner(System.in);
        
        while (!game.getGameOver()) {
            System.out.println("Pick a row, column, and action!");
            int inRow = scan.nextInt();
            int inCol = scan.nextInt();
            String inAction = scan.next();
            inRow--;
            inCol--;

            // Get player input for row, col, and action (reveal or flag)
            // For now, just simulate a move (to be replaced with real player input logic)
            game.playerMove(inRow, inCol, inAction);
            game.displayBoard();


            // Check for win or loss conditions
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(inRow, inCol)) {
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }
    }
}