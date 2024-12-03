import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Enhanced Number Guessing Game!");
        System.out.println("You need to guess a number between 1 and 100.");
        System.out.println("Points are awarded based on the number of attempts!");

        System.out.print("Enter the number of rounds you want to play: ");
        int rounds = scanner.nextInt();
        int totalScore = 0;

        for (int round = 1; round <= rounds; round++) {
            System.out.println("\n--- Round " + round + " ---");
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attempts = 0;
            int maxAttempts = 10; // Limiting the number of attempts
            boolean guessedCorrectly = false;

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + ": Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    int points = Math.max(0, 10 - attempts); // Points decrease as attempts increase
                    totalScore += points;
                    System.out.println("You earned " + points + " points this round.");
                    guessedCorrectly = true;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts! The correct number was " + numberToGuess + ".");
            }
        }

        System.out.println("\n--- Game Over ---");
        System.out.println("Your total score is: " + totalScore);
        scanner.close();
    }
}