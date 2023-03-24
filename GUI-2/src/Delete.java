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

public class Delete extends JFrame{

        //SIZE
         private static final int FRAME_WIDTH=500;
         private static final int FRAME_HEIGHT=500;
         private static final int FRAME_X_ORIGIN=150;
         private static final int FRAME_Y_ORIGIN=250;

        //Declarations
        private JLabel studiddellabel;
        private JTextField Tfstudel;
        private JButton buttondelete;
        private deletebuthandler delhandler;

        //Json
        static JSONParser jsonParser;
        static JSONObject folder;
        static JSONArray list;
        static JSONObject info;
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
        
        //COMPONENTS
        public void createComp(){

            studiddellabel = new JLabel("Student ID to Delete: ");
            Tfstudel = new JTextField(10);
            buttondelete = new JButton("Delete");
        }
        public void setComp(){
            Container pane = getContentPane();
            
            pane.setLayout(new GridLayout(2,2));

            pane.add(studiddellabel);
            pane.add(Tfstudel);
            pane.add(buttondelete);

            delhandler = new deletebuthandler();
            buttondelete.addActionListener(delhandler);

        
        }
        public Delete(){
            //FRAME
            setTitle("Delete");
            setSize(FRAME_WIDTH,FRAME_HEIGHT);
            setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setVisible(true);
        }
        
        //DELETE FORMULA
        private class deletebuthandler implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                    try {
                        reader();
                    } catch (IOException | ParseException | org.json.simple.parser.ParseException e1) {
                        e1.printStackTrace();
                    }
                
                    
                    for (int i = 0; i < list.size(); i++) {
                        JSONObject infoObj = (JSONObject) list.get(i);
                        String getid = (String) infoObj.get("id");
                        if (getid.equals(Tfstudel.getText())) {
                            list.remove(i);
                            break;
                        }
                 } 
                 try {
                        writer();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            }
        }
        public static void main(String[] args) {
            Delete del = new Delete();
                del.createComp();
                del.setComp();
        }
}