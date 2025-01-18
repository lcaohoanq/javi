package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import model.App;
import regex.PatternRegex;

public class ButtonManagement implements ActionListener {

  App app;

  public ButtonManagement() {
    initButton();
    clickButton();
  }

  private static String[] btnName = {"", "", "", "%", "MU",
      "MC", "MR", "M-", "M+", "/",
      "+/_", "7", "8", "9", "X",
      "|>", "4", "5", "6", "-",
      "C/AC", "1", "2", "3", "+",
      "0", "00", ".", "=", "+"}; //enum

//  JButton btn_1_1 = new JButton(btnName[0]);
//  JButton btn_2_1 = new JButton(btnName[1]);
//  JButton btn_3_1 = new JButton(btnName[2]);
//  JButton btn_4_1 = new JButton(btnName[3]);
//  JButton btn_5_1 = new JButton(btnName[4]);
//  JButton btn_1_2 = new JButton(btnName[5]);
//  JButton btn_2_2 = new JButton(btnName[6]);
//  JButton btn_3_2 = new JButton(btnName[7]);
//  JButton btn_4_2 = new JButton(btnName[8]);
//  JButton btn_5_2 = new JButton(btnName[9]);
//  JButton btn_1_3 = new JButton(btnName[10]);
//  JButton btn_2_3 = new JButton(btnName[11]);
//  JButton btn_3_3 = new JButton(btnName[12]);
//  JButton btn_4_3 = new JButton(btnName[13]);
//  JButton btn_5_3 = new JButton(btnName[14]);
//  JButton btn_1_4 = new JButton(btnName[15]);
//  JButton btn_2_4 = new JButton(btnName[16]);
//  JButton btn_3_4 = new JButton(btnName[17]);
//  JButton btn_4_4 = new JButton(btnName[18]);
//  JButton btn_5_4 = new JButton(btnName[19]);
//  JButton btn_1_5 = new JButton(btnName[20]);
//  JButton btn_2_5 = new JButton(btnName[21]);
//  JButton btn_3_5 = new JButton(btnName[22]);
//  JButton btn_4_5 = new JButton(btnName[23]);
//  JButton btn_5_5 = new JButton(btnName[24]);
//  JButton btn_1_6 = new JButton(btnName[25]);
//  JButton btn_2_6 = new JButton(btnName[26]);
//  JButton btn_3_6 = new JButton(btnName[27]);
//  JButton btn_4_6 = new JButton(btnName[28]);
//  JButton btn_5_6 = new JButton(btnName[29]);

  //Tạo 1 HashMap chứa nút (key = tọa độ, value = tên)

  //Tạo 1 ArrayList cho đơn giản :))))
  ArrayList<JButton> btnlist = new ArrayList<>();

  private JButton button = null;
  static String isDigitCal = PatternRegex.IS_DIGIT_CALCULATOR.getRegex();
  static int countFunction = 0; //

  public void initButton() {
//    for(int i = 0; i < btnName.length; i++){
//      btnlist.add(new JButton(btnName[i]));
//      btnlist.get(i).addActionListener(this);
//      app.bottomPanel.add(btnlist.get(i)); //grid 6x5
//    }

    for (String item : btnName) {
      button = new JButton(item);
      //khi khởi động tất cả btn bị disable
      if(!item.equals("C/AC")){
        button.setEnabled(false); //khi mà nhấn AC mới khởi động
      }else if(item.equals(".") || item.equals(PatternRegex.IS_DIGIT)){
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
      }else if(!item.equals(PatternRegex.IS_DIGIT) && !item.equals(".")){
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
      }
      else{
        //nút này là AC
//        button.setEnabled(true);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
      }


      button.addActionListener(this);
      btnlist.add(button);
      app.bottomPanel.add(button); //grid 6x5
    }
  }

  public void clickButton() {
    for (int i = 0; i < btnlist.size(); i++) {
      btnlist.get(i).addActionListener(this);
    }
  }

  public void handleOnOff(String btnValue) {
    if (btnValue.equals("C/AC")) {
      App.viewer.setText("");
      App.viewer.append("0");
      for (JButton item : btnlist) {
        //khi khởi động tất cả btn bị disable
        item.setEnabled(true);
        item.addActionListener(this);
      }
    }
  }

  public void handlePress(String btnValue) {
//nếu bấm lần đầu thì cho nối bình thường
    //bấm lần thứ 2 sẽ nối rỗng
    App.viewer.setText("");
    System.out.println("DDang nhan " + btnValue);
    if (!btnValue.matches(isDigitCal)) {
      //nếu là kí tự không phải số
      ++countFunction;
      if (countFunction == 3) {
        countFunction = 0;
        App.viewer.append("");
      } else {
        App.viewer.append(btnValue + " ");
      }
    } else {
      App.viewer.append(btnValue + " ");
    }
  }

  public void handleExpression() {
    String text = App.viewer.getText();
    Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");
    Matcher matcher = pattern.matcher(text);
    System.out.println("Giá trị của viewerV2: " + text);
    System.out.println("Giá trị của matcher: " + matcher);
    if (matcher.find()) {
      String[] result = new String[]{
          matcher.group(1),  // First number
          matcher.group(2),  // Operator
          matcher.group(3)   // Second number
      };

      // Print the array elements
      for (String element : result) {
        System.out.println(element);
      }
    }
  }

  public void handleEqual(String btnValue) {
    if (btnValue.equals("=")) {
      //nếu mà viewer
    }
  }

  public void handleClear(String btnValue) {
    if (btnValue.equals("|>") || btnValue.equals("C/AC")) {
      App.viewer.setText("");
    }
  }

  public void handleFunction(String btnValue) {
    if (!btnValue.equals(PatternRegex.IS_OPERATOR.getRegex()) && !btnValue.equals(
        PatternRegex.IS_DIGIT.getRegex())) {
      App.viewer.append("");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton btn = (JButton) e.getSource();
    String btnValue = btn.getText();
    System.out.println(btnlist.toArray());
    System.out.println("Đang nhấn: " + btnValue);
//    System.out.println("Index: " + btnlist.get);

//    handleFunction(btnValue); //nếu mà nhấn các nút chức năng sẽ không có in ra trên viewerV2
    handleOnOff(btnValue);
    handlePress(btnValue);
    handleExpression();
    handleEqual(btnValue);
    handleClear(btnValue);


    //khi mình nhấn dấu = thì sẽ tính toán và set lại viewerV2 là kết quả và

  }
}
