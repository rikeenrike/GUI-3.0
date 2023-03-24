import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame{
    
    private static final int FRAME_WIDTH=500;
    private static final int FRAME_HEIGHT=500;
    private static final int FRAME_X_ORIGIN=150;
    private static final int FRAME_Y_ORIGIN=250;

    private JMenuBar menubar;
    private JMenu menu1, menu2, submenu;
    private JMenuItem item1, item2, item3, item4, item5, item6, item7, otheritem, otheritem2;
    private ImageIcon addimage;
    private addmenuhandler addhandler;
    private static JLabel relayresult;
    
    public void createFrameComp(){
        relayresult = new JLabel("", SwingConstants.CENTER);
    }

    public void setFrameComp(){
        
        Container pane = getContentPane();

        pane.setLayout(new GridLayout(1,1));

        pane.add(relayresult);
    }

    public static void setresults(int x){
        relayresult.setText("sum is " + x);
    }
    private void jmenucomp(){
        menubar = new JMenuBar();
            menu1 = new JMenu("OPEN FOR SUPRISE");
            menu1.setMnemonic(KeyEvent.VK_C);
            item1 = new JMenuItem("File");
            item2 = new JMenuItem("Upload");
            item3 = new JMenuItem("Open File");
            item4 = new JMenuItem("Add");
            
            
            addimage = new ImageIcon("C:\\Users\\ITE Account\\Downloads\\monki.png");
            item1 = new JMenuItem("File", addimage);
            addhandler = new addmenuhandler();
            item1.addActionListener(addhandler);
            item1.setToolTipText("MONKEY");
            
            submenu = new JMenu("Other Options");
                otheritem = new JMenuItem("subitem");
                otheritem2 = new JMenuItem("subitem2");
            submenu.add(otheritem);
            submenu.add(otheritem2);

            menu2 = new JMenu("Edit");
            menu2.setMnemonic(KeyEvent.VK_E);
            item5 = new JMenuItem("Sequence Settings");
            item6 = new JMenuItem("Frame Scale");
            item7 = new JMenuItem("Global Settings");
            
            menu1.add(item1);
            menu1.add(item2);
            menu1.add(item3);
            menu1.add(item4);
            menu2.add(item5);
            menu2.add(item6);
            menu2.add(item7);
           
            menubar.add(menu1);
            menubar.add(menu2);
            menu1.add(submenu);
            
            setJMenuBar(menubar);

 }
 public void framesettings(){
    setTitle("MY OPTIONS"); 
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    
    getContentPane().setBackground(new Color(255, 153 ,204));
 }
 
  private class addmenuhandler implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        App app = new App();
        app.createComp();
        app.setComp();
       
    }


 }
 public static void main(String[] args) {
    
    menu x  = new menu();
        x.jmenucomp(); 
        x.createFrameComp();
        x.setFrameComp();
        x.framesettings();

 }
}


