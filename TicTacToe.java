import java.util.Scanner;

class Main {
    public static void viewGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char e : row) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public static boolean move(char[][] grid, int turns) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter position:");
        int row = sc.nextInt(), col = sc.nextInt();

        if (grid[row][col] == '.') { // if empty space
            grid[row][col] = (turns % 2 == 0 ? 'O' : 'X');
            return false; // No overlap
        } else {
            System.out.println("Space is already occupied!\nPlease re-enter:\n");
            return true; // Overlap
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '.'; // all empty spaces will have a dot
            }
        }

        System.out.println("Player 1 = O\nPlayer 2 = X\n");

        // there can be maximum 9 turns in TicTacToe
        for (int turns = 0; turns < 9; turns++) {
            boolean overlap = false, win = false;
            if (turns % 2 == 0) {
                System.out.println("---Player 1's move---");
                overlap = move(grid, turns);
                if (overlap) {
                    turns--;
                    continue;
                }
                System.out.println("Current Grid: ");
                viewGrid(grid);
            } else {
                System.out.println("---Player 2's move---");
                overlap = move(grid, turns);
                if (overlap) {
                    turns--;
                    continue;
                }
                System.out.println("Current Grid: ");
                viewGrid(grid);
            }
            System.out.println("\n- - - - - - - -");

            // win-conditions  1. row-match  2. col-match  3. diagonal match

            for (int i = 0; i < 3; i++) { // 1
                int p1Count = 0, p2Count = 0;
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == 'O') {
                        p1Count++;
                    } else if (grid[i][j] == 'X') {
                        p2Count++;
                    }
                }
                if (p1Count == 3) {
                    System.out.println("Player 1 Wins");
                    win = true;
                } else if (p2Count == 3) {
                    System.out.println("Player 2 Wins");
                    win = true;
                }
            }

            if(win){break;}

            for (int j = 0; j < 3; j++) { // 2
                int p1Count = 0, p2Count = 0;
                for (int i = 0; i < 3; i++) {
                    if (grid[i][j] == 'O') {
                        p1Count++;
                    } else if (grid[i][j] == 'X') {
                        p2Count++;
                    }
                }
                if (p1Count == 3) {
                    System.out.println("Player 1 Wins");
                    win = true;
                } else if (p2Count == 3) {
                    System.out.println("Player 2 Wins");
                    win = true;
                }
            }
            if(win){break;}

            int p1Count = 0, p2Count = 0;
            for (int i = 0; i < 3; i++) { // major diagonal
                if (grid[i][i] == 'O') {
                    p1Count++;
                } else if (grid[i][i] == 'X') {
                    p2Count++;
                }
            }
            if (p1Count == 3) {
                System.out.println("Player 1 Wins");
                win = true;
            } else if (p2Count == 3) {
                System.out.println("Player 2 Wins");
                win = true;
            }
            if(win){break;}

            // check minor diagonal
            p1Count = 0;
            p2Count = 0;
            for (int i = 0; i < 3; i++) {
                if (grid[i][2 - i] == 'O') {
                    p1Count++;
                } else if (grid[i][2 - i] == 'X') {
                    p2Count++;
                }
            }
            if (p1Count == 3) {
                System.out.println("Player 1 Wins");
                win = true;
            } else if (p2Count == 3) {
                System.out.println("Player 2 Wins");
                win = true;
            }
            if(win){break;}

            // draw-condition check if win conditions not satisfied
            //no player can win after 8 turns unless someone already won
            if (turns == 8) {
                System.out.println("DRAW");
                break;
            }
        }
    }
}