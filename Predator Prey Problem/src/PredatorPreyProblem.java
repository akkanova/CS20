import java.util.Random;
import java.util.Scanner;

public class PredatorPreyProblem {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static int wolvesPopulation;
    public static int rabbitPopulation;
    public static int daysPassed;

    // Advanced Configs
    public static int wolfCatchChance = 50; // % Representing the chances of a wolf catching a prey
    public static int wolfBreedingDay = 10; // How many days until the wolves reproduces
    public static int rabbitBreedingDay = 5; // How many days until the rabbits reproduces
    public static int rabbitMaxPopulation = 50_000; // Max population until it halves itself from lack of food

    public static void main(String[] args) {
        // Allow user to configure starting population
        wolvesPopulation = askNumericQuestion("What is the starting population of the wolves (Predator)?");
        rabbitPopulation = askNumericQuestion("What is the starting population of the rabbits (Prey)?");

        // Allow further configuration of Advanced Configs
        boolean showAdvanceConfig = askQuestion("Do you want to configure more variables? (y/n)").equalsIgnoreCase("y");
        if (showAdvanceConfig) {
            wolfCatchChance = askNumericQuestion("What is the % of a wolf catching a rabbit? (0-100)");
            wolfBreedingDay = askNumericQuestion("How many days until the wolves reproduces?");
            rabbitBreedingDay = askNumericQuestion("How many days until the rabbit reproduces?");
            rabbitMaxPopulation = askNumericQuestion("What is the max population for rabbits?");
        }

        beginSimulation();
    }

    public static void beginSimulation() {
        // Simulated Day Cycle
        while (wolvesPopulation > 0 && rabbitPopulation > 0) {
            int initialWolvesPop = wolvesPopulation;
            for (int i = 0; i < initialWolvesPop; i++) {
                // At the beginning of every day, a wolf must eat
                // one rabbit to survive;
                int luck = random.nextInt(100);

                // if there are living rabbits, a wolf has a
                // 50% chance of catching each one,
                if (rabbitPopulation > 0 && luck <= wolfCatchChance) {
                    // but can only catch one rabbit; that is, after catching a rabbit,
                    // the wolf will not try to catch any others that day.
                    rabbitPopulation--;
                } else {
                    wolvesPopulation--;
                }
            }

            daysPassed++;

            // If 'rabbitBreedingDay' have passed, each remaining pair
            // of rabbits breeds and gives birth to 3 more rabbits.
            if ((daysPassed % rabbitBreedingDay) == 0)
                rabbitPopulation += rabbitPopulation / 2 * 3;

            // If 'wolfBreedingDay' have passed, each pair of wolves breeds and
            // gives birth to one more wolf. If there is an odd rabbit
            // or wolf, it does not breed.
            if ((daysPassed % wolfBreedingDay) == 0 && isEven(wolvesPopulation) && isEven(rabbitPopulation))
                wolvesPopulation += wolvesPopulation / 2;

            // If the number of rabbits at the end of any day exceeds 'rabbitMaxPopulation',
            // then half of them die due to starvation because there is not
            // enough plant life to support them.
            if (rabbitPopulation > rabbitMaxPopulation)
                rabbitPopulation *= 0.5;

            System.out.printf(
                "%d Days has elapsed | %d Wolves Remained | %d Rabbits Remained\n",
                daysPassed, wolvesPopulation, rabbitPopulation
            );
        }

        System.out.printf(
            "The Biosphere survived for a total of %d days",
            daysPassed
        );
    }

    // Using modulus operator '%', we can get the remainder of the two numbers
    // after division. We can use this to find if a number is divisible by x.
    // Using this operator above we can find out if x amount of days have passed.
    public static Boolean isEven(int number) {
        return (number % 2) == 0;
    }

    public static String askQuestion(String question) {
        System.out.println(question);
        return scanner.nextLine().trim();
    }

    public static Integer askNumericQuestion(String question) {
        return Integer.parseInt(askQuestion(question));
    }
}