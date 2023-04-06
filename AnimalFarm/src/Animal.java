public class Animal {
    private String name;
    private Species species;
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
        int numberOfLegs,
        boolean hasTail
    ) {
        // The usage of 'this' is to differentiate
        // between the two similarly named variables.
        this.name = name;
        this.species = species;
        this.numberOfLegs = numberOfLegs;
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
            (this.numberOfLegs + otherParent.getNumberOfLegs()) / 2,
            this.hasTail || otherParent.getTailBoolean()
        );

        child.setUniqueSound(
            this.speak() + otherParent.speak()
        );

        return child;
    }

    @Override
    public String toString() {
        return capitalizeFirstLetter(name) +
            " Info's \nSpecies : " +
            species.name() +
            "\nNumber of legs : " +
            numberOfLegs +
            "\nHas a tail : " +
            hasTail;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public void setTailBoolean(boolean hasTail) {
        this.hasTail = hasTail;
    }

    public void setUniqueSound(String sound) {
        this.uniqueSound = sound;
    }


    // Getters
    public String getName() {
        return name;
    }

    public Species getSpecies() {
        return species;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public boolean getTailBoolean() {
        return hasTail;
    }


    // Private helper function(s)
    private static String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
