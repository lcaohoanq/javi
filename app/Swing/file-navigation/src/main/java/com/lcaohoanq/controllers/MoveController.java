package com.lcaohoanq.controllers;

import com.lcaohoanq.utils.AudioHandler;
import com.lcaohoanq.views.MoveFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

public class MoveController implements ActionListener {
    private MoveFrame moveFrame;

    public MoveController(MoveFrame moveFrame){
        this.moveFrame = moveFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("You are in the Move Action");

        File sourceFile;
        String destinationPath;

        if(!moveFrame.checkState()){
            sourceFile = moveFrame.getFile();
            destinationPath = moveFrame.getFolderModel().getAbsolutePath();
            // Check if source file and destination path are not null
            if (sourceFile != null && destinationPath != null) {
                try {
                    // Create a Path for the destination file
                    Path destinationFile = Paths.get(destinationPath, sourceFile.getName());

                    // Use Files.move() method to move the file
                    Files.move(sourceFile.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

                    AudioHandler.playAudio("/success.wav");
                    System.out.println("File moved successfully to " + destinationFile);
                    JOptionPane.showMessageDialog(moveFrame, "File moved successfully to " + destinationFile, "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    System.out.println("Error moving file: " + ex.getMessage());
                }
            }
        }else{
            AudioHandler.playAudio("/errorV2.wav");
            JOptionPane.showMessageDialog(moveFrame, "Please select a file to process", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
