import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class View extends JFrame {
      
       //SIZE
       private static final int FRAME_WIDTH=500;
       private static final int FRAME_HEIGHT=500;
       private static final int FRAME_X_ORIGIN=150;
       private static final int FRAME_Y_ORIGIN=250;

      //Declarations
      private JLabel studidviewlabel;
      private JTextField Tfstudview;
      private JButton buttonview;
      private viewbuthandler viewhandler;
      
      
       //Json
       static JSONObject folder;
       static JSONArray list;
       static JSONObject info;
       static JSONParser jsonParser;
       static {
              jsonParser = new JSONParser();
              folder = new JSONObject();
              list = new JSONArray();
              info = new JSONObject();
         }
       
         //READER AND WRITER
    static void reader() throws IOException, ParseException, org.json.simple.parser.ParseException{
        FileReader reader = new FileReader("src\\STUDLIST.json");
        if (reader.ready()) {
            Scanner collect = new Scanner(reader);
            String line = "";
            while(collect.hasNext()){
                line = line + collect.nextLine();
            }
            if (!line.equals("")) {
                reader.close();
                FileReader reader2 = new FileReader("src\\STUDLIST.json");
                folder = (JSONObject) jsonParser.parse(reader2);
                list = (JSONArray) folder.get("folder");
            }
        }
        reader.close();
    }
    static void writer() throws IOException {
        FileWriter writer = new FileWriter("src\\STUDLIST.json");
        writer.write(folder.toJSONString());
        writer.close();
    }
    
        //CONSTRUCTOR
      public void createComp(){

          studidviewlabel = new JLabel("Student ID to View: ");
          Tfstudview = new JTextField(10);
          buttonview = new JButton("View");
      }
      public void setComp(){
          Container pane = getContentPane();
          
          pane.setLayout(new GridLayout(2,2));

          pane.add(studidviewlabel);
          pane.add(Tfstudview);
          pane.add(buttonview);

          viewhandler = new viewbuthandler();
          buttonview.addActionListener(viewhandler);

      
      }
      public View(){
          //FRAME
          setTitle("View");
          setSize(FRAME_WIDTH,FRAME_HEIGHT);
          setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
          setDefaultCloseOperation(HIDE_ON_CLOSE);
          setVisible(true);
      }
      
      private class viewbuthandler implements ActionListener{

        
        public void actionPerformed(ActionEvent e) {
            try {
                reader();
            } catch (IOException | ParseException | org.json.simple.parser.ParseException e1) {
                e1.printStackTrace();
            }
        
            int i = 0;
            for (; i < list.size(); i++) {
                JSONObject infoObj = (JSONObject) list.get(i);
                String sampleid = (String) infoObj.get("id");
                if (sampleid.equals(Tfstudview.getText())) {     
                    break;
                }
               
         }
                JSONObject studinfo = (JSONObject) list.get(i);  
                String getID = (String) studinfo.get("id");
                String getfn = (String) studinfo.get("firstname");
                String getln = (String) studinfo.get("lastname");
                String getbday = (String) studinfo.get("birthday");
                String getgender = (String) studinfo.get("gender"); 
                System.out.println(getID + " " + getfn + " " + getln + " " + getbday + " " + getgender);
                
                viewtab view = new viewtab();
                view.createComp();
                view.setComp();
                viewtab.settoview(getID, getfn, getln, getbday, getgender);
         try {
                writer();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } 
      }
      public static void main(String[] args) {
          View view = new View();
              view.createComp();
              view.setComp();
      }

}
