public class VariableChallenges {
    public static void main(String[] args) {
        // Question 2
        char seatLetter = 'B';
        System.out.println("Your airplane seat letter : " + seatLetter);

        // Question 4
        boolean isMenuItemLowCalorie = false;
        System.out.println(
            "That menu item is : " +
            // Ternary Operator (shortcut for if-else-statement)
            // (if statement is true) ? (do this) : (else do this instead)
            (isMenuItemLowCalorie
                ? "Low Calorie"
                : "Not Low Calorie")
        );

        // Question 6
        boolean isInMultiplayerMode = false;
        System.out.println(
            "The game is in : " +
            // Another Ternary Operator
            (isInMultiplayerMode
                ? "Multiplayer Mode"
                : "Campaign Mode")
        );

        // Question 8
        float owedCustomerBalance = -97.50f;
        System.out.println(
            "Your credit card bill " +
            "(- means the credit card company owes you money, and vice versa) : " +
            owedCustomerBalance
        );
    }
}