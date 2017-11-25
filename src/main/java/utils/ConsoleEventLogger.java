package utils;

import common.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }

    static void logError(Throwable t) {
         t.printStackTrace();
    }
}
