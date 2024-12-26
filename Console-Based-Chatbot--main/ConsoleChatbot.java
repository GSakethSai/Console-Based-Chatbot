import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ConsoleChatbot {
    // Method to open an application
    @SuppressWarnings("deprecation")
    public static void openApplication(String appName) throws IOException {
        String command;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            command = "cmd /c start " + appName;
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            command = "open -a " + appName;
        } else {
            command = appName; // For Linux systems
        }
        Runtime.getRuntime().exec(command);
    }

    // Method to open the browser and perform a search
    public static void openWebBrowser(String query) throws IOException, URISyntaxException {
        String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(url));
        } else {
            System.out.println("Desktop is not supported. Cannot open the browser.");
        }
    }

    // Main chatbot logic
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Console Chatbot! How can I assist you today?");
        
        while (true) {
            System.out.println("\nEnter a command (type 'exit' to quit):");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.startsWith("open")) {
                // Open specified application
                String appName = input.replace("open ", "");
                try {
                    openApplication(appName);
                    System.out.println("Opening " + appName + "...");
                } catch (IOException e) {
                    System.out.println("Failed to open " + appName);
                }
            } else if (input.startsWith("search")) {
                // Search in the web browser
                String query = input.replace("search ", "");
                try {
                    openWebBrowser(query);
                    System.out.println("Searching for: " + query);
                } catch (IOException | URISyntaxException e) {
                    System.out.println("Failed to perform the search.");
                }
            } else {
                System.out.println("Sorry, I didn't understand that command. Try 'open [application]' or 'search [query]'.");
            }
        }
        scanner.close();
    }
}
