import java.util.Random;

public class Animal {
    private static final Random random = new Random();

    // Access-modifier | type | name
    private String name;
    private Species species;
    private String colour;
    private int numberOfLegs;
    private boolean hasTail;
    // The getter for this is this.speak()
    private String uniqueSound;

    enum Species {
        Cattle,
        Chicken,
        Goat,
        Horse,
        Pig,
        Sheep
    }

    public Animal(
        String name,
        Species species,
        String colour,
        int numberOfLegs,
        boolean hasTail
    ) {
        // The usage of 'this' is to differentiate
        // between the two similarly named variables.
        this.name = name;
        this.species = species;
        this.colour = colour;
        this.numberOfLegs = Math.abs(numberOfLegs); // Prevents negative legs
        this.hasTail = hasTail;
    }

    public String speak() {
        if (this.uniqueSound != null)
            return this.uniqueSound;

        return switch (species) {
            case Cattle -> "Moo";
            case Chicken -> "Cluck";
            case Goat -> "Bleat";
            case Horse -> "Neighs";
            case Pig -> "Oink";
            case Sheep -> "Baa";
        };
    }

    public Animal crossbreed(Animal otherParent) {
        if (!this.species.equals(otherParent.getSpecies()))
            return null;

        Animal child = new Animal(
            this.name + otherParent.getName(),
            this.species,
            chooseBetween(this.colour, otherParent.getColour()),
            (this.numberOfLegs + otherParent.getNumberOfLegs()) / 2,
            chooseBetween(this.hasTail, otherParent.getTailBoolean())
        );

        child.setUniqueSound(
            this.speak() + otherParent.speak()
        );

        return child;
    }

    @Override
    public String toString() {
        return "Name : " +
            capitalizeFirstLetter(name) +
            "\nSpecies : " +
            species.name() +
            "\nColour : " +
            colour +
            "\nNumber of legs : " +
            numberOfLegs +
            "\nHas a tail : " +
            hasTail;
    }


    // Setters
    // Instead of throwing an exception, using math.abs prevents
    // the setting of numberOfLegs to a negative number
    public void setNumberOfLegs(int numberOfLegs) { this.numberOfLegs = Math.abs(numberOfLegs); }
    public void setTailBoolean(boolean hasTail) { this.hasTail = hasTail; }
    public void setUniqueSound(String sound) { this.uniqueSound = sound; }
    public void setSpecies(Species species) { this.species = species; }
    public void setColour(String colour) { this.colour = colour; }
    public void setName(String name) { this.name = name; }


    // Getters
    public int getNumberOfLegs() { return numberOfLegs; }
    public boolean getTailBoolean() { return hasTail; }
    public Species getSpecies() { return species; }
    public String getColour() { return colour; }
    public String getName() {return name; }


    // Private helper function(s)
    private static String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private static <T> T chooseBetween(T a, T b) {
        return random.nextBoolean() ? a : b;
    }
}
