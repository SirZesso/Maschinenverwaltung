package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final String LOG_DIRECTORY = "src/main/resources/logfile/";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    private static final DateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void log(String logdata) {
        String filename = LOG_DIRECTORY + DATE_FORMATTER.format(new Date()) + ".txt";

        File logFile = new File(filename);

        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            FileWriter writer = new FileWriter(logFile, true);
            writer.write(TIME_FORMATTER.format(new Date()) + " - " + logdata + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the log file: " + e.getMessage());
        }
    }
}
