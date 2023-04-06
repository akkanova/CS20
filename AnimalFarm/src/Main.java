public class Main {
    public static void main(String[] args) {
        // ... test creating animals, changing its fields,
        // and printing out its information.

        Animal betty = new Animal(
            "Betty", Animal.Species.Cattle,
            "bisque", 4, true
        );

        System.out.println(
            "Betty with her original information :\n" +
            betty // Animal.toString() is automatically called.
        );

        betty.setSpecies(Animal.Species.Chicken);
        betty.setName("Totally not betty");
        betty.setTailBoolean(false);
        betty.setUniqueSound("Maw");
        betty.setColour("almond");
        betty.setNumberOfLegs(5);

        System.out.println(
            "\nBetty with all of her properties changed :\n" +
            betty
        );

        // Crossbreed

    }
}