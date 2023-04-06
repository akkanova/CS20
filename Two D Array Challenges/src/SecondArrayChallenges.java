import java.util.*;

public class SecondArrayChallenges  {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    // Crossword Config
    public static final int CW_HEIGHT = 10;
    public static final int CW_WIDTH = 10;

    public static void main(String[] args) {
        // Challenge 1
        Integer[][] tableA = new Integer[4][4];
        System.out.printf(
            "This is the default values of a 2D Integer array :\n%s\n",
            stringifyTable(tableA)
        );

        // Challenge 2
        Integer[][] tableB = fillTable(tableA, (i) -> getRandomInt(0, 100));
        System.out.printf(
            "This is the same array with some random values, with all the values adding up to %d :\n%s\n",
            getTableTotal(tableB),
            stringifyTable(tableB)
        );

        // Challenge 4
        int columns = Integer.parseInt( askQuestion("How many columns?") );
        int rows = Integer.parseInt( askQuestion("How many rows?") );
        Integer[][] tableC = fillTable(new Integer[rows][columns], (i) -> i + 1);
        System.out.printf(
            "2D Array with %d columns, %d rows and %d cells :\n%s\n",
            columns, rows,
            tableC[rows - 1][columns - 1],
            stringifyTable(tableC)
        );

        // Challenge 6
        Character[][] crossword = fillTable(new Character[CW_HEIGHT][CW_WIDTH], (i) -> (char) getRandomInt(65, 90));
        String[] words = parseInputToArray(askQuestion(
            "What words to include in the crossword? " +
            "(word-length =< " + CW_WIDTH +
            " & no-spaces & separate words with ',')"
        ));

        insertWords(crossword, words);

        System.out.printf(
            "A Crossword puzzle with the following hidden words: \n%s\n%s\n",
            String.join(", ", words),
            stringifyTable(crossword)
        );

        // Challenge 8
        int amountOfBellNumbers = Integer.parseInt(askQuestion("How many bell numbers to generate?"));
        Integer[][] bellTriangle = generateBellTriangle(amountOfBellNumbers);

        System.out.printf(
            "Bell Triangle: \n%s",
            stringifyTable(bellTriangle)
        );
    }


    public static String askQuestion(String question) {
        System.out.println(question);
        return scanner.nextLine().trim();
    }

    // Since Random.nextInt(min, max) doesn't work everywhere
    // this will compensate for it.
    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    // Similar to 'Array.deepToString', but displays each row in new lines.
    public static <T> String stringifyTable(T[][] table) {
        StringBuilder output = new StringBuilder();
        for (T[] row : table) {
            String[] columns = Arrays.stream(row)
                .map(obj -> obj == null ? "null" : obj.toString())
                .toArray(String[]::new);

            output
                .append(String.join(" ", columns))
                .append("\n");
        }

        return output.toString();
    }

    // Clones the given table, and iterates over its elements and fills its
    // value with the one generated by the FillerCallback.
    public static <T> T[][] fillTable(T[][] table, FillerCallback<T> filler) {
        T[][] clone = table.clone();
        int index = 0;

        for (T[] row : clone) {
            for (int column = 0; column < row.length; column++)
                row[column] = filler.run(index++);
        }

        return clone;
    }

    // Returns the total of all the numbers in given table.
    public static int getTableTotal(Integer[][] table) {
        int total = 0;
        for (Integer[] row : table) {
            for (int element : row)
                total += element;
        }

        return total;
    }

    // Parses user input into a word array
    public static String[] parseInputToArray(String answer) {
        return Arrays.stream(answer.split(","))
            .map((elem) -> elem.replaceAll("[^A-Za-z]+", "")) // Removes any non letter characters
            .filter((elem) -> elem.length() > 1)
            .map((elem) -> elem
                .substring(0, Math.min(CW_WIDTH, elem.length()))  // Max 10 characters
                .toUpperCase()) // All uppercase
            .limit(CW_HEIGHT) // Max 10 words
            .toArray(String[]::new);
    }

    // Inserts words into the crossword table horizontally
    public static void insertWords(Character[][] crossword, String[] words) {
        Set<Integer> usedRows = new HashSet<>();
        for (String word : words) {
            int column = getRandomInt(0, CW_WIDTH - word.length());
            int row;
            do {
                // Generate a unique unused row
                row = getRandomInt(0, CW_HEIGHT - 1);
            } while (usedRows.contains(row));

            for (int i = 0; i < word.length(); i++)
                crossword[row][column + i] = word.charAt(i);

            usedRows.add(row);
        }
    }

    // Generates a bell triangle
    public static Integer[][] generateBellTriangle(int amount) {
        Integer[][] bellTriangle = new Integer[amount][];
        bellTriangle[0] = new Integer[]{1};

        for (int i = 1; i < amount; i++) {
            // Create a new row with one more element than the previous row
            bellTriangle[i] = new Integer[i + 1];
            // Set the first element of the new row to the last element of the previous row
            bellTriangle[i][0] = bellTriangle[i - 1][i - 1];

            for (int j = 1; j <= i; j++) {
                // Set the current element to the sum of the previous element in
                // the new row and the corresponding element in the previous row
                bellTriangle[i][j] = bellTriangle[i][j - 1] + bellTriangle[i - 1][j - 1];
            }
        }

        return bellTriangle;
    }
}

interface FillerCallback<T> {
    T run(int index);
}