package fundamentals;

import java.io.File;
import java.io.FileNotFoundException;

public class ExceptionExample {
    public static void main(String[] args) {
        // No need to explicitly handle unchecked exceptions.
        //unChecked();

        // Exceptions are handled centrally for anything that happens in this scope.
        try {
            var example = new ExceptionExample();
            example.loadConfig();
        } catch (FileNotFoundException ex) {
            System.out.printf("Required file not found: %s%n", ex);
        } catch (Exception ex) {
            System.out.printf("General error: %s%n", ex);
        } finally {
            System.out.println("Program completed");
        }
    }

    private void loadConfig() throws Exception {
        loadConfigFile("user.config.json");
        loadConfigFile("system.config.json");
    }

    // Note that the function indicates that it can throw an exception.
    private void loadConfigFile(String location) throws FileNotFoundException {
        var file = new File(location);
        if (!file.exists()) {
            // Let the code above know there was an exception.
            throw new FileNotFoundException("couldn't find: " + location);
        }

        // Otherwise load the configuration
    }

    private static void unChecked() {
        throw new RuntimeException();
    }
}