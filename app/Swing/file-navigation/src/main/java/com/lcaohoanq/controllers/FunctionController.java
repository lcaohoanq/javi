package com.lcaohoanq.controllers;

import com.lcaohoanq.views.FunctionFrame;
import com.lcaohoanq.views.ProgressBarFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class FunctionController implements ActionListener {

    private FunctionFrame functionFrame;
    private String frameType;

    public FunctionController(FunctionFrame functionFrame, String frameType) {
        this.functionFrame = functionFrame;
        this.frameType = frameType;
    }

    public FunctionController(FunctionFrame functionFrame) {
        this.functionFrame = functionFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (!functionFrame.checkState()) {
//            ProgressBarFrame progressBarFrame = new ProgressBarFrame();
//            progressBarFrame.setVisible(true);
//            progressBarFrame.triggerAction();
//        }else{
//            JOptionPane.showMessageDialog(functionFrame, "Please select a file to process", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }
}
