package com.lcaohoanq.views;

import com.lcaohoanq.controllers.DataController;
import com.lcaohoanq.models.FileModel;
import com.lcaohoanq.models.FolderModel;
import com.lcaohoanq.utils.FileHandler;
import com.lcaohoanq.utils.ImageHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class FunctionFrame extends JFrame {

    private JFileChooser sourceFileChooser;
    private JFileChooser destinationFileChooser;
    private JPanel topPanel;
    private JSplitPane middlePanel;
    private JTextArea directoryInfoArea;
    private JScrollPane sourceScrollPane;
    private JScrollPane directoryScrollPane;
    private JPanel bottomPanel;
    private JTextArea fileInfoArea;
    protected JButton processButton;
    protected JButton copyButton;
    protected JButton moveButton;
    private JButton viewFileDataButton;
    private Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    protected File selectedFile;
    protected File selectedDirectory;
    private FileModel fileModel;
    private FolderModel folderModel;
    public FunctionFrame() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageHandler().icon);
        this.setVisible(false);

        initUI();
    }

    private void initUI() {
        initTop();
        initMiddle();
        initBottom();
        initContainer();
        doAction();
    }

    private void initTop() {
        topPanel = new JPanel(new GridLayout(1, 2));
        sourceFileChooser = new JFileChooser();
        destinationFileChooser = new JFileChooser();
        destinationFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        destinationFileChooser.setApproveButtonText("Select Folder");
        topPanel.add(sourceFileChooser);
        topPanel.add(destinationFileChooser);
    }

    private void initMiddle() {
        fileInfoArea = new JTextArea();
        fileInfoArea.setEditable(false);
        fileInfoArea.setBorder(border);
        fileInfoArea.setFont(new Font("Roboto", Font.PLAIN, 15));

        directoryInfoArea = new JTextArea();
        directoryInfoArea.setEditable(false);
        directoryInfoArea.setBorder(border);
        directoryInfoArea.setFont(new Font("Roboto", Font.PLAIN, 15));

        sourceScrollPane = new JScrollPane(fileInfoArea);
        directoryScrollPane = new JScrollPane(directoryInfoArea);

        middlePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sourceScrollPane,
            directoryScrollPane);
        middlePanel.setResizeWeight(0.5); // This will divide the panel equally
        middlePanel.setDividerSize(20);
    }

    private void initBottom() {
        bottomPanel = new JPanel();
        processButton = new JButton("Process");
        processButton.setPreferredSize(new Dimension(300, 50));
        processButton.setFont(new Font("Roboto", Font.BOLD, 20));
        processButton.setBackground(Color.GREEN);
        processButton.setForeground(Color.WHITE);
        processButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewFileDataButton = new JButton("View Data");
        viewFileDataButton.setPreferredSize(new Dimension(300, 50));
        viewFileDataButton.setFont(new Font("Roboto", Font.BOLD, 20));
        viewFileDataButton.setBackground(Color.BLUE);
        viewFileDataButton.setForeground(Color.WHITE);
        viewFileDataButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(viewFileDataButton);
        bottomPanel.add(processButton);
    }

    private void initContainer() {
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void viewFileData() {
        sourceFileChooser.addActionListener(e -> {
            selectedFile = null;
            fileInfoArea.setText("");
            if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                selectedFile = sourceFileChooser.getSelectedFile();

                fileModel = new FileModel(selectedFile.getName(), selectedFile.getAbsolutePath(),
                    selectedFile.length(), FileHandler.extractExtension(selectedFile));

                String nameLine = "Name: " + fileModel.getName() + "\n";
                String pathLine = "Path: " + fileModel.getAbsolutePath() + "\n";
                String sizeLine = "Size: " + fileModel.getLength() + " Bytes\n";
                String typeLine = "Type: " + fileModel.getExtension() + "\n";

                fileInfoArea.append(nameLine);
                fileInfoArea.append(pathLine);
                fileInfoArea.append(sizeLine);
                fileInfoArea.append(typeLine);

            } else if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                fileInfoArea.setText("");
            }
        });

        destinationFileChooser.addActionListener(e -> {
            if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                selectedDirectory = destinationFileChooser.getSelectedFile();

                folderModel = new FolderModel(selectedDirectory.getName(),
                    selectedDirectory.getAbsolutePath());

                String nameLine = "Name: " + folderModel.getName() + "\n";
                String pathLine = "Path: " + folderModel.getAbsolutePath() + "\n";

                directoryInfoArea.append(nameLine);
                directoryInfoArea.append(pathLine);
            } else if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                directoryInfoArea.setText("");
            }
        });
    }

    protected void doAction() {
        viewFileData();
        viewFileDataButton.addActionListener(new DataController(this));
    }

    public File getFile(){
        return selectedFile;
    }

    // need to open the file data to continue the processing
    public boolean checkState(){
        return fileInfoArea.getText().isEmpty() || directoryInfoArea.getText().isEmpty();
    }

    public FileModel getFileModel() {
        return this.fileModel;
    }

    public FolderModel getFolderModel() {
        return this.folderModel;
    }

}
