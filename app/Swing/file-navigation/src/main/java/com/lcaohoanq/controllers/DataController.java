package com.lcaohoanq.controllers;

import com.lcaohoanq.models.FileModel;
import com.lcaohoanq.utils.AudioHandler;
import com.lcaohoanq.views.DataFrame;
import com.lcaohoanq.views.FunctionFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

public class DataController implements ActionListener {

    private FunctionFrame functionFrame;
    public DataController(FunctionFrame functionFrame) {
        this.functionFrame = functionFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FileModel file = functionFrame.getFileModel();
        if(file != null) {
            AudioHandler.playAudio("/sound.wav");
            new DataFrame(new File(file.getAbsolutePath())).setVisible(true);
        } else {
            AudioHandler.playAudio("/errorV2.wav");
            JOptionPane.showMessageDialog(functionFrame, "Please select a file to view data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
