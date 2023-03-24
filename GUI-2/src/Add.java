//IMPORTS
import java.awt.Container;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.GridLayout;
//Main Class
public class Add extends JFrame{

         // SIZE
         private static final int FRAME_WIDTH=500;
         private static final int FRAME_HEIGHT=500;
         private static final int FRAME_X_ORIGIN=150;
         private static final int FRAME_Y_ORIGIN=250;
        
         //Declarations
            private JLabel studIDlabel, fnlabel, lnlabel, bdaylabel, genderlabel;
            private JButton buttonsave, buttonclear;
            private JTextField Tfstudid, Tffn, Tfln, Tfbday, TfGender;
            private savebuthandler savehandler;
            private clearbuthhandler clearhandler;
            
        //Json Declarations  
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
    // JSON READERS AND WRITERS METHOD       
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
    // GUI COMPS
    public void createComp(){
            //instantaite 
        studIDlabel = new JLabel("Student ID: ");
        fnlabel = new JLabel("First Name: ");
        lnlabel = new JLabel("Last Name: ");
        bdaylabel = new JLabel("Birthday: ");
        genderlabel = new JLabel("Gender: ");
        

        Tfstudid = new JTextField(10);
        Tffn = new JTextField(10);
        Tfln = new JTextField(10);
        Tfbday = new JTextField(10);
        TfGender =  new JTextField(10);
       

        buttonclear = new JButton("Clear");
        buttonsave = new JButton("Save");
    }
    public void setComp(){
        Container pane =  getContentPane();

        pane.setLayout(new GridLayout(6, 2));

        pane.add(studIDlabel);
        pane.add(Tfstudid);
        pane.add(fnlabel);
        pane.add(Tffn);
        pane.add(lnlabel);
        pane.add(Tfln);
        pane.add(bdaylabel);
        pane.add(Tfbday);
        pane.add(genderlabel);
        pane.add(TfGender);
        pane.add(buttonsave);
        pane.add(buttonclear);

        savehandler = new savebuthandler();
        buttonsave.addActionListener(savehandler);

        clearhandler = new clearbuthhandler();
        buttonclear.addActionListener(clearhandler);
    }
    public Add(){
        //FRAME
        setTitle("Add");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }
    
    // SAVE FORMULA
    private class savebuthandler implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
                    
            folder = new JSONObject();
            list = new JSONArray();
            info = new JSONObject();
            jsonParser = new JSONParser();
            
            try {
                reader();
            } catch (IOException | ParseException | org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
                        boolean exists = false;
                        for (int i = 0; i < list.size(); i++) {
                            JSONObject infoObj = (JSONObject) list.get(i);
                            String getid = (String) infoObj.get("id");
                            if (getid.equals(Tfstudid.getText())) {
                                exists = true;
                                break;
                            }   
                           }  
                            if (exists) {
                                new ExistsOptionPane();
                                System.out.println("Exists!");
                            }else{
                        
                        info.put("id", Tfstudid.getText());
                        info.put("firstname", Tffn.getText());
                        info.put("lastname",Tfln.getText());
                        info.put("birthday", Tfbday.getText());
                        info.put("gender", Tfln.getText());
                                
                                try {
                                    reader();
                                } catch (IOException | ParseException | org.json.simple.parser.ParseException e) {
                                    e.printStackTrace();
                                }
                                
                                list.add(info);
                                folder.put("folder", list);
                                
                                try {
                                    writer();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                new SavedOptionPane();
                            }
                        }
        }
    //JOPTIONS
    private class ExistsOptionPane {
        JFrame f =new JFrame();
        ExistsOptionPane(){
            
            JOptionPane.showMessageDialog(f,"Student Already Exists!","EXISTS!",JOptionPane.WARNING_MESSAGE);
        }
    }
    private class SavedOptionPane {
        JFrame f =new JFrame();
        SavedOptionPane(){
            
            JOptionPane.showMessageDialog(f,"Student Saved Successfully!","SAVED",JOptionPane.PLAIN_MESSAGE);
        }
    }

    //CLEAR FORMULA
    private class clearbuthhandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Tfstudid.setText("");
            Tffn.setText("");
            Tfln.setText("");
            Tfbday.setText("");
            TfGender.setText("");
        }
    }

   //MAIN METHOD
    public static void main(String[] args){
       Add add = new Add();
        add.createComp();
        add.setComp();
    }
}