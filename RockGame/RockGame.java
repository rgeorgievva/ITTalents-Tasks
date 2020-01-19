import java.util.Scanner;

public class RockGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char wantToPlay = 'y';
        do {
            playGame();
            System.out.println("Do you want to play again? Press 'y' for yes and any other symbol for no:");
            wantToPlay = sc.next().charAt(0);
        } while (wantToPlay == 'y');
    }

    public static void playGame() {
        Scanner sc = new Scanner(System.in);
        //1: ask for a level of difficulty
        int level = chooseLevel();
        //2: create matrix with number spaces depending on the chosen level
        char[][] world = new char[20][20];
        int numberBushes = 20;
        switch (level) {
            case 1:
                break;
            case 2:
                world = new char[40][40];
                numberBushes = 160;
                break;
            case 3:
                world = new char[50][50];
                numberBushes = 500;
                break;
        }
        createWorldWithSpaces(world);
        //3: put number bushes depending on the chosen level
        generateBushes(world,0, numberBushes);
        //4: generate a player
        generatePlayer(world);
        //5: generate a rock
        generateRock(world);
        //6: generate the end symbol
        int lastRow = world.length - 1;
        int lastCol = world[0].length -1;
        world[lastRow][lastCol] = 'E';
        //7: print the world
        printWorld(world);
        while (!isRockTrapped(world)) {
            //8: ask for direction
            char direction = getDirection();
            switch (direction) {
                case 'w':
                    goUp(world);
                    break;
                case 's':
                    goDown(world);
                    break;
                case 'd':
                    goRight(world);
                    break;
                case 'a':
                    goLeft(world);
                    break;
            }
            printWorld(world);
            if (hasReachedTheEnd(world)) {
                System.out.println("Congratilations! You won!");
                break;
            }
        }
        if (isRockTrapped(world)) {
            System.out.println("Game over!");
        }
    }

    public static int chooseLevel() {
        Scanner sc = new Scanner(System.in);
        int level = 0;
        do {
            System.out.println("Choose a level from 1 to 3: ");
            if (sc.hasNextInt()) {
                level = sc.nextInt();
            }
            else {
                sc.next();
            }
        } while (level < 1 || level > 3);

        return level;
    }

    public static void createWorldWithSpaces(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = ' ';
            }
        }
    }

    public static int[] generateCoordinates(char[][] matrix) {
        int row;
        int col;
        int max = matrix.length - 1;
        do {
            row = (int)(Math.random() * max);
            col = (int)(Math.random() * max);
        } while (matrix[row][col] != ' ');

        int[] coordinates = {row,col};
        return coordinates;
    }

    public static void generateBushes(char[][] matrix, int currentNumberBushes, int numberBushes) {
        if (currentNumberBushes == numberBushes) {
            return;
        }
        int row = generateCoordinates(matrix)[0];
        int col = generateCoordinates(matrix)[1];
        matrix[row][col] = '*';
        generateBushes(matrix, currentNumberBushes + 1, numberBushes);
    }

    public static void generatePlayer(char[][] matrix) {
        char player = (char)0xC6C3;
        int row = generateCoordinates(matrix)[0];
        int col = generateCoordinates(matrix)[1];
        matrix[row][col] = player;
    }

    public static void generateRock(char[][] matrix) {
        char rock = 9679;
        int row = generateCoordinates(matrix)[0];
        int col = generateCoordinates(matrix)[1];
        matrix[row][col] = rock;
    }

    public static void printWorld(char[][] matrix) {
        for (int dash = 0; dash <= matrix.length; dash++) {
            System.out.print("――");
        }
        System.out.println();
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("| ");
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println("|");
        }
        for (int dash = 0; dash <= matrix.length; dash++) {
            System.out.print("――");
        }
        System.out.println();
    }

    public static boolean hasReachedTheEnd(char[][] world) {
        int[] rockCoordinates = getRockCoordinates(world);
        if (rockCoordinates[0] == world.length - 1 && rockCoordinates[1] == world[0].length - 1) {
            return true;
        }
        return false;
    }

    public static boolean isRockTrapped(char[][] world) {
        int[] rockCoordinates = getRockCoordinates(world);
        int rockRow = rockCoordinates[0];
        int rockCol = rockCoordinates[1];
        int lastRow = world.length;
        int lastCol = world[0].length;
        int firstRow = 0;
        int firstCol = 0;
        char bush = '*';
        if (rockRow == firstRow && rockCol == firstCol) {
            return true;
        }
        if (rockRow == firstRow && rockCol == lastCol) {
            return true;
        }
        if (rockRow == lastRow && rockCol == firstCol) {
            return true;
        }
        //bushes above and on the right
        if (rockRow - 1 >= 0 && world[rockRow - 1][rockCol] == bush &&
            rockCol + 1 < world[0].length && world[rockRow][rockCol + 1] == bush) {
            return true;
        }
        //bushes above and on the left
        if (rockRow - 1 >= 0 && world[rockRow - 1][rockCol] == bush &&
                rockCol - 1 >= 0  && world[rockRow][rockCol - 1] == bush) {
            return true;
        }
        //bushes below and on the right
        if (rockRow + 1 < world.length && world[rockRow + 1][rockCol] == bush &&
                rockCol + 1 < world[0].length  && world[rockRow][rockCol + 1] == bush) {
            return true;
        }
        //bushes below and on the left
        if (rockRow + 1 < world.length && world[rockRow + 1][rockCol] == bush &&
                rockCol - 1 >= 0  && world[rockRow][rockCol - 1] == bush) {
            return true;
        }
        //first column and a bush above or below
        if (rockCol == firstCol &&
                (world[rockRow + 1][rockCol] == bush || world[rockRow - 1][rockCol] == bush)) {
            return true;
        }
        //last column and a bush  above
        if (rockCol == lastCol && world[rockRow - 1][rockCol] == bush) {
            return true;
        }
        //last column and a bush below
        if (rockCol == lastCol && rockRow + 1 < world.length &&  world[rockRow + 1][rockCol] == bush) {
            return true;
        }
        //first row and a bush on the left or right
        if (rockRow == firstRow &&
                (world[rockRow][rockCol + 1] == bush || world[rockRow][rockCol - 1] == bush)) {
            return true;
        }
        //last row and a bush on the left
        if (rockRow == lastRow && world[rockRow][rockCol - 1] == bush) {
            return true;
        }
        //last row and a bush on the right
        if (rockRow == lastCol && rockCol + 1 < world[0].length &&  world[rockRow][rockCol + 1] == bush) {
            return true;
        }
        return false;
    }

    public static char getDirection() {
        Scanner sc = new Scanner(System.in);
        char direction;
        do {
            System.out.println("Enter direction(a = left, d = right, w = up, s = down):");
            direction = sc.next().charAt(0);
        } while (direction != 'a' && direction != 's' && direction != 'd' && direction != 'w');

        return direction;
    }

    public static int[] getPlayerCoordinates(char[][] world) {
        char player = (char)0xC6C3;
        int[] playerCoordinates = new int[2];
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[row].length; col++) {
                if (world[row][col] == player) {
                    playerCoordinates[0] = row;
                    playerCoordinates[1] = col;
                    break;
                }
            }
        }
        return playerCoordinates;
    }

    public static int[] getRockCoordinates(char[][] world) {
        char rock = 9679;
        int[] rockCoordinates = new int[2];
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[row].length; col++) {
                if (world[row][col] == rock) {
                    rockCoordinates[0] = row;
                    rockCoordinates[1] = col;
                    break;
                }
            }
        }
        return rockCoordinates;
    }

    public static void goUp(char[][] world) {
        int[] playerCoordinates = getPlayerCoordinates(world);

        int playerRow = playerCoordinates[0];
        int playerCol = playerCoordinates[1];
        char player = (char)0xC6C3;
        char rock = 9679;
        char bush = '*';
        //player is on first row
        if (playerRow - 1 < 0) {
            return;
        }
        //there is a bush above the player
        if (world[playerRow - 1][playerCol] == bush) {
            return;
        }
        //player is on second row and the rock is above him
        if (world[playerRow - 1][playerCol] == rock && playerRow - 2 < 0) {
            return;
        }
        //the rock is above the player but there is a bush above the rock
        if (world[playerRow - 1][playerCol] == rock && world[playerRow - 2][playerCol] == bush) {
            return;
        }
        //there is nothing above the player
        if (world[playerRow - 1][playerCol] == ' ') {
            world[playerRow - 1][playerCol] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
        //the rock is above the player
        if (world[playerRow - 1][playerCol] == rock) {
            world[playerRow - 2][playerCol] = rock;
            world[playerRow - 1][playerCol] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
    }

    public static void goDown(char[][] world) {
        int[] playerCoordinates = getPlayerCoordinates(world);
        int playerRow = playerCoordinates[0];
        int playerCol = playerCoordinates[1];
        char player = (char)0xC6C3;
        char rock = 9679;
        char bush = '*';
        //player is on last row
        if (playerRow + 1 == world.length) {
            return;
        }
        //there is a bush below the player
        if (world[playerRow + 1][playerCol] == bush) {
            return;
        }
        //player is on the row before the last and the rock is below him
        if (world[playerRow + 1][playerCol] == rock && playerRow + 2 == world.length) {
            return;
        }
        //the rock is below the player but there is a bush below the rock
        if (world[playerRow + 1][playerCol] == rock && world[playerRow + 2][playerCol] == bush) {
            return;
        }
        //there is nothing below the player
        if (world[playerRow + 1][playerCol] == ' ') {
            world[playerRow + 1][playerCol] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
        //the rock is above the player
        if (world[playerRow + 1][playerCol] == rock) {
            world[playerRow + 2][playerCol] = rock;
            world[playerRow + 1][playerCol] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
    }

    public static void goRight(char[][] world) {
        int[] playerCoordinates = getPlayerCoordinates(world);
        int playerRow = playerCoordinates[0];
        int playerCol = playerCoordinates[1];
        char player = (char)0xC6C3;
        char rock = 9679;
        char bush = '*';
        //player is on last column
        if (playerCol + 1 == world[0].length) {
            return;
        }
        //there is a bush on the right of the player
        if (world[playerRow][playerCol + 1] == bush) {
            return;
        }
        //player is on the column before the last and the rock is on its right
        if (world[playerRow][playerCol + 1] == rock && playerCol + 2 == world[0].length) {
            return;
        }
        //the rock is on the right side of the player but there is a bush on the other side of the rock
        if (world[playerRow][playerCol + 1] == rock && world[playerRow][playerCol + 2] == bush) {
            return;
        }
        //there is nothing on the right side of the player
        if (world[playerRow][playerCol + 1] == ' ') {
            world[playerRow][playerCol + 1] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
        //the rock is on the right side of the player
        if (world[playerRow][playerCol + 1] == rock) {
            world[playerRow][playerCol + 2] = rock;
            world[playerRow][playerCol + 1] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
    }

    public static void goLeft(char[][] world) {
        int[] playerCoordinates = getPlayerCoordinates(world);
        int playerRow = playerCoordinates[0];
        int playerCol = playerCoordinates[1];
        char player = (char)0xC6C3;
        char rock = 9679;
        char bush = '*';
        //player is on first column
        if (playerCol - 1 < 0) {
            return;
        }
        //there is a bush on the left of the player
        if (world[playerRow][playerCol - 1] == bush) {
            return;
        }
        //player is on the second column and the rock is on its left
        if (world[playerRow][playerCol - 1] == rock && playerCol - 2 == 0) {
            return;
        }
        //the rock is on the left side of the player but there is a bush on the other side of the rock
        if (world[playerRow][playerCol - 1] == rock && world[playerRow][playerCol - 2] == bush) {
            return;
        }
        //there is nothing on the left side of the player
        if (world[playerRow][playerCol - 1] == ' ') {
            world[playerRow][playerCol - 1] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
        //the rock is on the left side of the player
        if (world[playerRow][playerCol - 1] == rock) {
            world[playerRow][playerCol - 2] = rock;
            world[playerRow][playerCol - 1] = player;
            world[playerRow][playerCol] = ' ';
            return;
        }
    }
}
