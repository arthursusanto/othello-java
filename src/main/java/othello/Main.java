package othello;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Select game mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Random");
        System.out.println("3. Human vs Greedy");
        System.out.println("4. Run Random vs Random Simulation");
        System.out.println("5. Run Random vs Greedy Simulation");
        System.out.print("Enter choice (1-5): ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> OthelloControllerHumanVSHuman.main(new String[]{});
            case 2 -> OthelloControllerHumanVSRandom.main(new String[]{});
            case 3 -> OthelloControllerHumanVSGreedy.main(new String[]{});
            case 4 -> OthelloControllerRandomVSRandom.main(new String[]{});
            case 5 -> OthelloControllerRandomVSGreedy.main(new String[]{});
            default -> System.out.println("Invalid choice.");
        }
    }
}
