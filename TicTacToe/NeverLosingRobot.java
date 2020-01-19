import java.util.Scanner;

public class NeverLosingRobot {

    public static void main(String[] args) {

        //1: create matrix 3x3
        char[][] matrix = new char[3][3];
        //2: fill the matrix with spaces
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = ' ';
            }
        }
        //3: print the matrix with | after each element and -- -- -- after each row
        printMatrix(matrix);
        int player = 1;
        int numberTurns = 0;
        int lastRow = 0;
        int lastCol = 0;
        int[] lastCoordinates = {lastRow,lastCol};
        while (true) {
            if (player == 1) {
                askRobotForCoordinates(matrix,numberTurns, lastCoordinates[0], lastCoordinates[1]);
            }
            else {
                lastCoordinates = askPlayerForCoordinates(matrix);
            }
            numberTurns++;
            printMatrix(matrix);
            int winner = winner(matrix);
            if (winner == 1) {
                System.out.println("Robot wins the game!");
                break;
            }
            if (winner == 2) {
                System.out.println("Congratulations, you won the game!");
                break;
            }
            if (isMatrixFull(matrix)) {
                System.out.println("It's a tie!");
                break;
            }
            player = player == 1 ? 2 : 1;
        }

    }

    public static void printMatrix(char[][] matrix) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(matrix[row][col]);
                if (col != 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            for (int i = 0; i < 3; i++) {
                System.out.print("――  ");
            }
            System.out.println();
        }
    }

    public static boolean canRobotWin(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == ' ') {
                    matrix[row][col] = 'x';
                    if (winner(matrix) == 1) {
                        return true;
                    }
                    else {
                        matrix[row][col] = ' ';
                    }
                }
            }
        }
        return false;
    }

    public static void askRobotForCoordinates(char[][] matrix,int numberTurns, int lastRow, int lastCol) {
        System.out.println("Robot's turn: ");
        if (numberTurns == 0) {
            matrix[1][1] = 'x';
            return;
        }
        if (canRobotWin(matrix)) {
            return;
        }
        if (lastCol + 1 < matrix[0].length && matrix[lastRow][lastCol + 1] == ' ') {
            matrix[lastRow][lastCol + 1] = 'x';
            return;
        }
        if (lastCol - 1 >= 0 && matrix[lastRow][lastCol - 1] == ' ') {
            matrix[lastRow][lastCol - 1] = 'x';
            return;
        }
        if (lastRow + 1 < matrix.length && matrix[lastRow + 1][lastCol] == ' ') {
            matrix[lastRow + 1][lastCol] = 'x';
            return;
        }
        if (lastRow - 1 >= 0 && matrix[lastRow - 1][lastCol] == ' ') {
            matrix[lastRow - 1][lastCol] = 'x';
            return;
        }
        generateRandomCoordinates(matrix);
    }

    public static void generateRandomCoordinates(char[][] matrix) {
        int row;
        int col;
        do {
            row = (int)(Math.random() * 3);
            col = (int)(Math.random() * 3);
        } while (matrix[row][col] != ' ');

        matrix[row][col] = 'x';
    }

    public static int[] askPlayerForCoordinates(char[][] matrix) {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter coordinates: ");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (row < 0 || row > 2 || col < 0 || col > 2 || matrix[row][col] != ' ');

        matrix[row][col] = 'o';
        int[] coordinates = {row,col};
        return coordinates;
    }

    public static boolean isMatrixFull(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int winner(char[][] matrix) {
        //check if there is a line of x or o
        int col = 0;
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][col] == ' ') {
                continue;
            }
            if (matrix[row][col] == matrix[row][col + 1] && matrix[row][col] == matrix[row][col + 2]) {
                if (matrix[row][col] == 'x') {
                    return 1;
                }
                else {
                    return 2;
                }
            }
        }
        //check if there is a column of x or o
        int row = 0;
        for (int currentCol = 0; currentCol < matrix.length; currentCol++) {
            if (matrix[row][currentCol] == ' ') {
                continue;
            }
            if (matrix[row][currentCol] == matrix[row + 1][currentCol] && matrix[row][currentCol] == matrix[row + 2][currentCol]) {
                if (matrix[row][currentCol] == 'x') {
                    return 1;
                }
                else {
                    return 2;
                }
            }
        }
        //first diagonal
        if (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]) {
            if (matrix[0][0] == 'x') {
                return 1;
            }
            if (matrix[0][0] == 'o') {
                return 2;
            }
        }
        //second dagonal
        if (matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0]) {
            if (matrix[0][2] == 'x') {
                return 1;
            }
            if (matrix[0][2] == 'o') {
                return 2;
            }
        }
        return 0;
    }
}
