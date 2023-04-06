public class OutputChallenges {
    public static void main(String[] args) {
        String[] outputs = {
            // Output Challenges
            // Question 1
            "Hello, World!",

            // Question 2
            "************\n" +
            "* SUCCESS! *\n" +
            "************",

            // Question 3
            "+---+------------+\n" +
            "| 1 | 4GB of RAM |\n" +
            "+---+------------+",

            // Question 4
            "\"Everything was going fine,\" he said, \"and then I got Zerg rushed.\"",

            // Question 5
            ">>>Error on Drive C:\\<<<",

            // Question 6
            "/------------------\\\n" +
            "| All your base    |\n" +
            "| are belong to us |\n" +
            "\\_____  ___________/\n" +
            "      \\/",



            // Math Challenges
            // The Floats / Integers are automatically converted into
            // String through truncation ('+')

            // Question 1
            "Sum of 5 + 37 = " + (5 + 37),

            // Question 2
            "Quotient of 13 / 6 = " + (13.0 / 6.0),

            // Question 3
            "Product of 46 * 0.5 = " + (46 * 0.5),

            // Question 4
            "Difference of 100 - 58 = " + (100 - 58),

            // Question 5
            "Answer for 7 - 4 * 9 - 34 / 2 = " + (7 - 4 * 9 - 34 / 2),

            // Question 6
            "The absolute value of -72 = " + Math.abs(-72),

            // Question 7
            "The value of PI = " + Math.PI,

            // Question 8
            "The value of 16 squared = " + Math.pow(16, 2),

            // Question 9
            "The area of a circle with a radius of 10 = " + getCircleArea(10),

            // Question 10
            "The value of 2 to the exponent 16 = " + Math.pow(2, 16)
        };

        // Print out each element of the string array "outputs"
        for (String output: outputs) {
            System.out.println(output);
        }
    }

    public static double getCircleArea(int radius) {
        return Math.PI * Math.pow(radius, 2);
    }
}