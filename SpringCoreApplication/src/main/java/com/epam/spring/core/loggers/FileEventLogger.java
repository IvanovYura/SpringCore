package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String filename;

    FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(filename), event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        File file = new File(filename);
        file.canWrite();
    }
}
