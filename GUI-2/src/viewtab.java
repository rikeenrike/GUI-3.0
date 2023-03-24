
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class viewtab extends JFrame{
            //SIZE
         private static final int FRAME_WIDTH=500;
         private static final int FRAME_HEIGHT=500;
         private static final int FRAME_X_ORIGIN=150;
         private static final int FRAME_Y_ORIGIN=250;
            //Declarations
         private static JLabel studIDlabel, fnlabel, lnlabel, bdaylabel, genderlabel;
            
          
            

        //CONSTRUCTOR
        public void createComp(){
                //instantaite 
            studIDlabel = new JLabel("");
            fnlabel = new JLabel("");
            lnlabel = new JLabel("");
            bdaylabel = new JLabel("");
            genderlabel = new JLabel("");
            

        }
        public void setComp(){
            Container pane =  getContentPane();
    
            pane.setLayout(new GridLayout(5, 1));
    
            pane.add(studIDlabel);
            pane.add(fnlabel);
            pane.add(lnlabel);
            pane.add(bdaylabel);
            pane.add(genderlabel);
             
   }
        public viewtab(){
            //FRAME
            setTitle("View");
            setSize(FRAME_WIDTH,FRAME_HEIGHT);
            setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setVisible(true);
        }
        //SETTER
        public static void settoview(String id, String fn, String ln,String bday ,String sex){
            System.out.println(id + fn + ln + bday + sex);
            studIDlabel.setText("ID: " + id);
            fnlabel.setText("FIRST NAME: " + fn);
            lnlabel.setText("LAST NAME: " + ln);
            bdaylabel.setText("BIRTH DATE: " + bday);
            genderlabel.setText("GENDER: " + sex);
        }
        
        //MAIN
            public static void main(String[] args) {
                viewtab view = new viewtab();
                view.createComp();
                view.setComp();
            }
}
