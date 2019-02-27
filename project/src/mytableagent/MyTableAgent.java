/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytableagent;

import java.util.Scanner;

public class MyTableAgent {

    private static Direction[][] directionTable;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows, cols, dirt;
        System.out.println("Total rows: ");
        rows = input.nextInt();
        System.out.println("Total columns: ");
        cols = input.nextInt();
        System.out.println("Enter the number of Dirty cells: ");
        dirt = input.nextInt();

        Environment env = new Environment(rows, cols, dirt);

        // Generate direction table which will guide the bot
        // on how to move in the grid to clean all the dirt
        generateDirectionTable(rows, cols);

        int totalDirty = dirt;

        System.out.println("\nEnvironment before cleaning:");
        System.out.println(env.toString());

        //Clean dirt
        while (totalDirty > 0) {
            // Clean the dirt if the current cell is dirty
            if (env.isDirty()) {
                env.suck();
                totalDirty--;
                System.out.println("\nClean:");
                System.out.println(env.toString());
                if (totalDirty == 0) {
                    break;
                }
            }
            // Move in the grid based on the direction table
            switch (directionTable[env.getRow()][env.getColumn()]) {
                case UP:
                    env.moveUp();
                    System.out.println("\nGoing Up:");
                    System.out.println(env.toString());
                    break;
                case DOWN:
                    env.moveDown();
                    System.out.println("\nGoing Down:");
                    System.out.println(env.toString());
                    break;
                case LEFT:
                    env.moveLeft();
                    System.out.println("\nGoing Left:");
                    System.out.println(env.toString());
                    break;
                case RIGHT:
                    env.moveRight();
                    System.out.println("\nGoing Right:");
                    System.out.println(env.toString());
                    break;
            }
        }

        System.out.println("\nGrid after cleaning all the dirt:");
        System.out.println(env.toString());
        System.out.println("Score: " + env.getScore());
    }

    private static void generateDirectionTable(int rows, int cols) {
        // This function generates direction table
        // in such way that the vaccum cleaner bot
        // will traverse the grid in snake like manner
        // until it finds all the dirt to be cleaned
        directionTable = new Direction[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j != (cols - 1)) {
                    if (i == 0 || i % 2 == 0) {
                        directionTable[i][j] = Direction.RIGHT;
                    } else {
                        directionTable[i][j] = Direction.LEFT;
                    }
                }
                if (j == 0) {
                    if (i % 2 == 1) {
                        directionTable[i][j] = Direction.DOWN;
                    } else {
                        directionTable[i][j] = Direction.RIGHT;
                    }
                }
                if (j == cols - 1) {
                    if (i % 2 == 1) {
                        directionTable[i][j] = Direction.LEFT;
                    } else {
                        directionTable[i][j] = Direction.DOWN;
                    }
                }
            }
        }
    }
}
