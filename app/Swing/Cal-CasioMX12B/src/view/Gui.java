package view;

import model.App;
import java.awt.Color;
import javax.swing.JFrame;

public class Gui extends JFrame{
    
    public Gui(){
        initGui();
    }
    
    private void initGui(){

        add(new App());
        
        setTitle("MX-12B");
//        setSize(new Dimension(378,232)); //cách tạo này nếu như cần một Dimension làm gì đó
        setSize(400,600);
//        pack(); //cách này thì nó sẽ tự động set size cho mình

        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setPreferredSize(this.getSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
//        try{
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //mac os
//            new Gui();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        new Gui();
    }
    
}
