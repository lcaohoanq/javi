package com.lcaohoanq.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JTextArea;

public class FileHandler {
    public static String extractExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static void readFileAndAppendTo(JTextArea fileInfoArea, String url){
        try (BufferedReader reader = Files.newBufferedReader(new File(url).toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileInfoArea.append(line + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
