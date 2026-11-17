import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String [ROWS][COLS];
    private static String currentPlayer = "X";
    public static void clearBoard(){
        for (int row=0; row < ROWS; row++){
            for (int col = 0; col< COLS; col++){
                board[row][col]=" ";
            }
        }
    }
    private static void display(){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                System.out.print(board[row][col]);
                if(col < COLS - 1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(row < ROWS - 1){
                System.out.println("-----");
            }
        }
    }
    private static boolean isValidMove(int row, int col){
        if(board[row][col].equals(" ")){
            return true;
        }
        return false;
    }
    private static boolean isRowWin (String player){
        for(int row = 0; row < ROWS; row++){
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][COLS - 1].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin (String player){
        for(int col = 0; col < COLS; col++){
            if(board[col][0].equals(player) && board[col][1].equals(player) && board[col][ROWS - 1].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin (String player){
        if(board[0][0].equals(player) && board [1][1].equals(player) && board[2][2].equals(player)){
            return true;
        }
        if(board[0][COLS - 1].equals(player) && board[1][1].equals(player) && board[ROWS - 1][0].equals(player)){
            return true;
        }
        return false;
    }
    private static boolean isWin(String player){
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)){
            return true;
        }
        return false;
    }
    private static boolean isTie(){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                if(board[row][col].equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int row, col;
        String rowPrompt,colPrompt;
        boolean done;
        int moveCount;
        boolean playAgain;
        do {
            clearBoard();
            currentPlayer = "X";
            done = false;
            moveCount = 0;
            do {
                while (true) {
                    rowPrompt = "Player " + currentPlayer + ", enter row";
                    colPrompt = "Player " + currentPlayer + ", enter column";
                    row = SafeInput.getRangedInt(in, rowPrompt, 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, colPrompt, 1, 3) - 1;
                    if (isValidMove(row, col)) {
                        board[row][col] = currentPlayer;
                        moveCount++;
                        break;
                    } else {
                        System.out.println("That space is already occupied. Try again.");
                    }
                }
                if (currentPlayer.equals("X")) {
                    currentPlayer = "O";
                } else {
                    currentPlayer = "X";
                }
                if (isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    done = true;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    done = true;
                }
            } while (!done);
            display();
            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");
        }while(!playAgain);
        System.out.println("Thanks for playing!");
        in.close();
    }

}
