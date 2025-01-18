package com.lcaohoanq.views;

import com.lcaohoanq.utils.FileHandler;
import com.lcaohoanq.utils.ImageHandler;
import java.awt.Font;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class DataFrame extends JFrame {

    private JTextArea fileInfoArea;
    private JScrollPane dataPanel;
    private Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    public File file;

    public DataFrame(File file) {
        this.setTitle("File Data Viewer");
        this.setSize(1000, 600);
        this.setIconImage(new ImageHandler().icon);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
        this.file = file;

        initUI();
    }

    private void initUI() {
        fileInfoArea = new JTextArea();
        fileInfoArea.setFont(new Font("Roboto", Font.PLAIN, 15));
        fileInfoArea.setBorder(border);
        fileInfoArea.setEditable(false);

        FileHandler.readFileAndAppendTo(fileInfoArea, file.getAbsolutePath());

        dataPanel = new JScrollPane(fileInfoArea);
        dataPanel.addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                Font currentFont = fileInfoArea.getFont();
                int newFontSize = currentFont.getSize() - e.getWheelRotation();
                if (newFontSize > 0) {
                    Font newFont = currentFont.deriveFont((float) newFontSize);
                    fileInfoArea.setFont(newFont);
                }
            }
        });

        this.add(dataPanel);
    }

}
