package com.originalgames.mitsurin.log;

import java.io.*;
import java.nio.file.Paths;
import java.util.Date;

public class ErrorWriter {
    private static final String logFilePath = "src/com/originalgames/test/log/error_log.txt";
    private OutputStream outputStream;
    private Object instance;

    public ErrorWriter(Object instance) {
        this.instance = instance;
        try {
            outputStream = new FileOutputStream(Paths.get(logFilePath).toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void appendErrorLog(String errorMessage) {
        Date date = new Date();
        String message = "date : [" + String.valueOf(date)
                + "]\nfile : " + String.valueOf(instance.getClass())
                + ".class\nmessage : " + errorMessage + "\n\n";
        for(int i = 0; i < message.length(); i++) {
            try {
                outputStream.write(message.charAt(i));
            } catch (IOException e) {
                return;
            }
        }
        try {
            outputStream.flush();
        } catch (IOException e) {
            return;
        }
    }

    public void closeStream() {
        try {
            this.outputStream.close();
        } catch (IOException e) {
            return;
        }
    }
}

