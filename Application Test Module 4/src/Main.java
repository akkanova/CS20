import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Read File First
        String[] lines;

        // Try with resource is different from a normal try-catch because
        // it ensures that the resource (FileReader) is closed no matter the outcome
        try (FileReader fileReader = new FileReader("source.dat")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            lines = bufferedReader.lines().toArray(String[]::new);
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            // Exit code was not really specified
            // Exit code 0 usually signifies success
            // and exit code > 0 is failure
            System.exit(0);
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Write file output using Try-With-Resource statement
        try (FileWriter fileWriter = new FileWriter("redacted.dat")) {
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (String line : lines) {
                String output = line;

                if (line.contains("secret")) continue;
                if (line.contains("James Bond"))
                    output = line.replace("James Bond", "*".repeat(10));

                printWriter.println(output);
            }

            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}