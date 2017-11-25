import common.Event;
import utils.ConsoleEventLogger;
import utils.EventLogger;

public class App {

    private Client client;
    private ConsoleEventLogger logger;

    public App(Client client, EventLogger logger) {
        this.client = client;
    }

    void logEvent(Event event) {
        // message = message.replaceAll(String.valueOf(client.getId()), client.getFullName());
        logger.logEvent(event);
    }
}
