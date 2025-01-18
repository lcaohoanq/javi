package com.lcaohoanq.views;

import com.lcaohoanq.controllers.MoveController;
import javax.swing.JButton;

public class MoveFrame extends FunctionFrame{


    public MoveFrame() {
        super();
        moveButton = new JButton("Move");
        this.setTitle("Move Files");
    }

    @Override
    protected void doAction() {
        super.doAction();
        processButton.addActionListener(new MoveController(this));
    }

    public static void main(String[] args) {
        new MoveFrame().setVisible(true);
    }
}