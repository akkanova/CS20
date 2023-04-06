public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal(
            "Pig",
            Animal.Species.Chicken,
            5,
            true
        );

        System.out.println(animal.speak());
    }
}