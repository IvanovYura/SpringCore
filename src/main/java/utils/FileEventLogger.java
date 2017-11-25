package utils;

import common.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private File file;
    private String filename;

    FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void logEvent(Event event) {
        // use apache utils
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            ConsoleEventLogger.logError(e);
        }
    }

    private void init() throws IOException {
        this.file = new File(filename);
    }
}
