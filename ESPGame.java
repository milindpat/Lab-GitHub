/*
 * Class: CMSC203 
 * Instructor: Grigoriy Grinberg
 * Description: This class implements a simple ESP game where the user guesses a randomly selected color.
 * Due: 06/16/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
 * independently. I have not copied the code from a student or any source. I have not given my code to any student.
 * Print your Name here: ___Milind__Patel___
 */

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Random;

public class ESPGame {

    public static void main(String[] args) throws Exception {
        final int GAME_ROUNDS = 3;

        final String COLOR_A = "black";
        final String COLOR_B = "white";
        final String COLOR_C = "gray";
        final String COLOR_D = "silver";
        final String COLOR_E = "maroon";
        final String COLOR_F = "red";
        final String COLOR_G = "purple";
        final String COLOR_H = "fuchsia";
        final String COLOR_I = "green";
        final String COLOR_J = "lime";
        final String COLOR_K = "olive";
        final String COLOR_L = "yellow";
        final String COLOR_M = "navy";
        final String COLOR_N = "blue";
        final String COLOR_O = "teal";
        final String COLOR_P = "aqua";

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scan.nextLine();

        System.out.print("Write a short bio: ");
        String playerBio = scan.nextLine();

        System.out.print("Enter due date (MM/DD/YY): ");
        String due = scan.nextLine();

        System.out.println("\nChoose difficulty level:");
        System.out.println("1. Full (16 colors)");
        System.out.println("2. Medium (10 colors)");
        System.out.println("3. Easy (5 colors)");
        System.out.println("4. Exit");

        System.out.print("Select an option (1-4): ");
        String gameMode = scan.nextLine();

        int colorsLimit = 0;

        switch (gameMode) {
            case "1":
                colorsLimit = 16;
                break;
            case "2":
                colorsLimit = 10;
                break;
            case "3":
                colorsLimit = 5;
                break;
            default:
                System.out.println("Goodbye!");
                return;
        }

        // Display color list from file
        System.out.println("\nAvailable Colors:");
        Scanner fileReader = new Scanner(new File("colors.txt"));
        int counter = 0;
        while (fileReader.hasNextLine() && counter < colorsLimit) {
            String colorName = fileReader.nextLine();
            System.out.println((counter + 1) + ". " + colorName);
            counter++;
        }
        fileReader.close();

        Random gen = new Random();
        int score = 0;

        for (int turn = 1; turn <= GAME_ROUNDS; turn++) {
            int randIndex = gen.nextInt(colorsLimit) + 1;
            String chosenColor = "";

            switch (randIndex) {
                case 1:  chosenColor = COLOR_A; break;
                case 2:  chosenColor = COLOR_B; break;
                case 3:  chosenColor = COLOR_C; break;
                case 4:  chosenColor = COLOR_D; break;
                case 5:  chosenColor = COLOR_E; break;
                case 6:  chosenColor = COLOR_F; break;
                case 7:  chosenColor = COLOR_G; break;
                case 8:  chosenColor = COLOR_H; break;
                case 9:  chosenColor = COLOR_I; break;
                case 10: chosenColor = COLOR_J; break;
                case 11: chosenColor = COLOR_K; break;
                case 12: chosenColor = COLOR_L; break;
                case 13: chosenColor = COLOR_M; break;
                case 14: chosenColor = COLOR_N; break;
                case 15: chosenColor = COLOR_O; break;
                case 16: chosenColor = COLOR_P; break;
            }

            System.out.print("\nRound " + turn + " - Guess the color: ");
            String playerGuess = scan.nextLine().toLowerCase();

            // Validate guess from file
            boolean colorValid = false;
            Scanner validation = new Scanner(new File("colors.txt"));
            int check = 0;
            while (validation.hasNextLine() && check < colorsLimit) {
                String testColor = validation.nextLine();
                if (testColor.equalsIgnoreCase(playerGuess)) {
                    colorValid = true;
                    break;
                }
                check++;
            }
            validation.close();

            if (!colorValid) {
                System.out.println("Invalid guess. Skipping this round.");
                continue;
            }

            System.out.println("Computer selected: " + chosenColor);
            if (playerGuess.equalsIgnoreCase(chosenColor)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }
        }

        // Show summary
        System.out.println("\nGame Over");
        System.out.println("You guessed " + score + " out of " + GAME_ROUNDS + " correctly.");
        System.out.println("Due Date: " + due);
        System.out.println("Username: " + playerName);
        System.out.println("User Description: " + playerBio);
        System.out.println("Date: " + due);

        // Write to results file
        FileWriter logWriter = new FileWriter("EspGameResults.txt");
        logWriter.write("Game Over\n");
        logWriter.write("You guessed " + score + " out of " + GAME_ROUNDS + " colors correctly.\n");
        logWriter.write("Due Date: " + due + "\n");
        logWriter.write("Username: " + playerName + "\n");
        logWriter.write("User Description: " + playerBio + "\n");
        logWriter.write("Date: " + due + "\n");
        logWriter.close();

        scan.close();
    }
}