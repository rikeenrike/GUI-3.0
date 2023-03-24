import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class App extends JFrame{


         private static final int FRAME_WIDTH=500;
         private static final int FRAME_HEIGHT=500;
         private static final int FRAME_X_ORIGIN=150;
         private static final int FRAME_Y_ORIGIN=250;
        
         //Declarations
            private JLabel labelnum1, labelnum2, labelnum3, labelsum;
            private JButton buttonsum, buttonclear;
            private JTextField Tfnum1, Tfnum2, Tfnum3, Tfsum;
            private calbuthandler sumhandler;
            private clearbuthhandler clearhandler;
     
        
         public void createComp(){
            //instantaite 
        labelnum1 = new JLabel("Enter First Variable: ");
        labelnum2 = new JLabel("Enter Second Variable: ");
        labelsum = new JLabel("The Sum is: ");

        Tfnum1 = new JTextField(10);
        Tfnum2 = new JTextField(10);
        Tfsum = new JTextField(10);

        buttonclear = new JButton("Clear");
        buttonsum = new JButton("Calculate");
    }
    public void setComp(){
        Container pane =  getContentPane();

        pane.setLayout(new GridLayout(4, 2));

        pane.add(labelnum1);
        pane.add(Tfnum1);
        pane.add(labelnum2);
        pane.add(Tfnum2);
        pane.add(labelsum);
        pane.add(Tfsum);
        pane.add(buttonsum);
        pane.add(buttonclear);

        sumhandler = new calbuthandler();
        buttonsum.addActionListener(sumhandler);

        clearhandler = new clearbuthhandler();
        buttonclear.addActionListener(clearhandler);
    }

    public App(){
        //FRAME
        setTitle("My first subclass");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class calbuthandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            int num1, num2, sum;
            num1 = Integer.parseInt(Tfnum1.getText());
            num2 = Integer.parseInt(Tfnum2.getText());
            sum = num1 + num2;
            Tfsum.setText("" + sum);
        
            menu.setresults(sum);
        }
    }

    private class clearbuthhandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Tfnum1.setText("");
            Tfnum2.setText("");
            Tfsum.setText("");

        }

    }
   
    public static void main(String[] args){
       App app = new App();
        app.createComp();
        app.setComp();
    }

}
