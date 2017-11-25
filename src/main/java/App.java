import utils.ConsoleEventLogger;

public class App {

    private Client client;

    public App(Client client) {
        this.client = client;
    }

    void logEvent(String message) {
        message = message.replaceAll(String.valueOf(client.getId()), client.getFullName());
        ConsoleEventLogger.log(message);
    }
}
