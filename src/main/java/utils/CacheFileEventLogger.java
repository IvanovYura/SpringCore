package utils;

import common.Event;

import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    //close of context
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() {
        // flush
    }
}
