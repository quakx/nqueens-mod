import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the chessboard: ");
        int n = scanner.nextInt();

        NQueens nqueens = new NQueens();
        nqueens.solveNQueens(n);
    }
}
