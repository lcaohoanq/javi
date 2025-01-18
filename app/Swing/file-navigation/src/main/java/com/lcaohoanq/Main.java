package com.lcaohoanq;

import com.lcaohoanq.views.DirectionFrame;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new DirectionFrame().setVisible(true);
        });
    }

}