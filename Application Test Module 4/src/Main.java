import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Try with resource (file-reader)
        // Reading the Input file
        try (FileReader fileReader = new FileReader("painting.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input = String.join("\n",
                bufferedReader
                    .lines()
                    .toArray(String[]::new)
            );

            System.out.println(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}