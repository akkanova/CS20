import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Array of animals, print out their information.

        Animal[] animals = {
                // These are just fictional examples
                new Animal("Betty", Animal.Species.Cattle, "Red", 4, true),
                new Animal("Snowball", Animal.Species.Chicken, "Orange", 2, false),
                new Animal("Billy the Kid", Animal.Species.Goat, "Yellow", 2, false),
                new Animal("Sugar", Animal.Species.Horse, "Green", 4, true),
                new Animal("Doodle", Animal.Species.Pig, "Blue", 4, true),
                new Animal("Mittens", Animal.Species.Sheep, "Indigo", 4, true),

                // Additional animals for higher chance of crossbreeding
                new Animal("Debbie", Animal.Species.Cattle, "Violet", 4, true),
                new Animal("Cactus", Animal.Species.Chicken, "Red-Violet", 2, false),
        };

        // Change the sound that 'Cactus' makes
        animals[7].setUniqueSound("Ka-ka-doodle-doo");

        System.out.println("============Animals============");
        for (Animal animal : animals) {
            // When concatenated with a string,
            // Object.toString is automatically called.
            System.out.println(animal);
            System.out.printf(
                "%s says %s\n\n",
                animal.getName(),
                animal.speak()
            );
        }


        // Crossbreed test
        // Choose random parents
        Random random = new Random();
        Animal parentA = animals[random.nextInt(animals.length)];
        Animal parentB = animals[random.nextInt(animals.length)];

        System.out.println("========Crossbreed-Test========");
        System.out.printf(
            "When Parent A : \n%s\n\nand Parent B \n%s\n\nis crossbreed we get Child \n%s",
            parentA, parentB, // Object.toString() is called by printF
            parentA.crossbreed(parentB)
        );
    }
}