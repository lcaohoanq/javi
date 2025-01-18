package com.lcaohoanq.views;

import com.lcaohoanq.utils.AudioHandler;
import com.lcaohoanq.utils.ImageHandler;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DirectionFrame extends JFrame implements ActionListener {

    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton jButtonCopy = new JButton("Copy");
    private JButton jButtonMove = new JButton("Move");
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem jMenuAbout;

    public DirectionFrame() {
        this.setTitle("Copy-Move File");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageHandler().icon);
        this.setResizable(false);

        AudioHandler.playAudio("/pedro.wav");

        initMenu();
        initPanelButton();
    }

    private void initMenu(){
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("HELP");
        jMenuAbout = new JMenuItem("About");
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        jMenuBar.add(jMenu);
        jMenu.add(jMenuAbout);

        jMenuAbout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "This is a simple app to copy or move file", "About", JOptionPane.INFORMATION_MESSAGE);
        });

        this.setJMenuBar(jMenuBar);
    }

    private void initPanelButton() {
        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        jButtonCopy.setBackground(Color.decode("#19b719"));
        jButtonCopy.setFont(new Font("Arial", Font.BOLD, 20));
        jButtonCopy.setPreferredSize(new Dimension(100, 50));
        jButtonCopy.setForeground(Color.WHITE);
        jButtonCopy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonCopy.addActionListener(this);

        jButtonMove.setBackground(Color.decode("#ff0000"));
        jButtonMove.setFont(new Font("Arial", Font.BOLD, 20));
        jButtonMove.setPreferredSize(new Dimension(100, 50));
        jButtonMove.setForeground(Color.WHITE);
        jButtonMove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonMove.addActionListener(this);

        jPanel.add(jButtonCopy);
        jPanel.add(jButtonMove);
        jPanel.setBorder(border);
        this.add(jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AudioHandler.playAudio("/ding.wav");
        if(e.getActionCommand().equals("Copy")) {
            CopyFrame copyFrame = new CopyFrame();
            copyFrame.setVisible(true);
        } else if(e.getActionCommand().equals("Move")) {
            MoveFrame moveFrame = new MoveFrame();
            moveFrame.setVisible(true);
        }
    }
}