package com.lcaohoanq.views;

import com.lcaohoanq.controllers.CopyController;
import javax.swing.JButton;

public class CopyFrame extends FunctionFrame {

    public CopyFrame() {
        super();
        copyButton = new JButton("Copy");
        this.setTitle("Copy Files");
    }

    @Override
    protected void doAction() {
        super.doAction();
        processButton.addActionListener(new CopyController(this));
    }

    public static void main(String[] args) {
        new CopyFrame().setVisible(true);
    }
}