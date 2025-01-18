package model;

import controller.ButtonManagement;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class App extends JPanel {

  ButtonManagement bm = new ButtonManagement();
  Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
  JLabel manufacturer = new JLabel();
  JButton energy = new JButton();
  JLabel model = new JLabel();
  JLabel digitSuport = new JLabel();
  JLabel digitText = new JLabel(); //DIGITS

  public static JTextArea viewer = new JTextArea(3, 50); //height, width
  //    JTextField viewerV1 = new JTextField(50); //height, width
  JPanel topPanel = new JPanel(new FlowLayout()); //để mặc định cũng được
  JPanel topPanel_digit = new JPanel();

  JPanel midPanel = new JPanel(new BorderLayout());
  public static JPanel bottomPanel = new JPanel(new GridLayout(6, 5, 5, 5));

  public App() {
    initApp();
  }

  //-----------------  Config element zone
  private void config() {
    configHeading();
    configViewerV2();
    configButton();
    configColor();
  }

  private void configColor(){
    //làm trước màu của khung JPanel
    this.setBackground(Color.BLACK);
  }

  private void configHeading() {
    manufacturer.setText("CASIO");
    manufacturer.setFont(new Font("Arial", Font.BOLD, 30));
    energy.setBackground(Color.YELLOW);
    model.setText("MX-12B");
    model.setFont(new Font("Arial", Font.BOLD, 5));
    digitSuport.setText("12");
    digitSuport.setFont(new Font("Arial", Font.BOLD, 20));
    digitText.setText("DIGITS");
    digitText.setFont(new Font("Arial", Font.BOLD, 10));
    //digiSupport và digiText sẽ vô một Box Layout Y_AXIS ->
  }

  private void configViewerV2() {
    viewer.setEditable(false);
    viewer.setLineWrap(true);
    viewer.setWrapStyleWord(true);
    viewer.setFont(new Font("Arial", Font.BOLD, 50));
    viewer.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    DefaultCaret caret = (DefaultCaret) viewer.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
  }

  private void configButton() {
//    for (String item : btnName) {
//      button = new JButton(item);
//      if (item.isEmpty()) {
//        button.setEnabled(false);
//      }
//      if (item.equals(PatternRegex.IS_DIGIT.getRegex())) {
//        button.setBackground(Color.GRAY);
//        button.setForeground(Color.WHITE);
//      }
//    }
  }
  //-----------------  Config element zone

  //-------------------init elementZone
  private void init() {
    //viet them
    initHeading();
    initScreen();
//    bm.initButton();
  }

  private void initHeading() {
    topPanel_digit.setLayout(new BoxLayout(topPanel_digit, BoxLayout.Y_AXIS));
    topPanel_digit.add(digitSuport); //12
    topPanel_digit.add(digitText); //DIGITS
    topPanel.add(manufacturer);
    topPanel.add(energy);
    topPanel.add(model);
    topPanel.add(topPanel_digit);
  }

  private void initScreen() {
    //vì việc dùng JTextField mình khó có thể line wrapping được
    midPanel.add(viewer, BorderLayout.CENTER);
  }
  //------------------init elementZone

  //------------------Layout zone



  private void layoutManager() {
    setLayout(new BorderLayout());
    add(topPanel, BorderLayout.NORTH);
    add(midPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);
  }

  //------------------Layout zone
  private void setBorder() {
    energy.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    topPanel.setBorder(padding);
    viewer.setBorder(padding);
    midPanel.setBorder(padding);
    bottomPanel.setBorder(padding);
  }

  private void initApp() {
    setBorder();
    init();
    config();
    layoutManager();

  }


}
