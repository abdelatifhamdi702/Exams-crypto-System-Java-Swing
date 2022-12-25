package Tools;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

    import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tools{
    public static void writeToExcell(JTable table, Path path) throws FileNotFoundException, IOException {
    
    XSSFWorkbook wb = new XSSFWorkbook(); //Excell workbook
    XSSFSheet sheet = wb.createSheet(); //WorkSheet
    Row row = sheet.createRow(2); //Row created at line 3
    TableModel model = table.getModel(); //Table model


    Row headerRow = sheet.createRow(0); //Create row at line 0
    for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
    }

    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        for(int cols = 0; cols < table.getColumnCount(); cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }

        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 3)); 
    }
    wb.write(new FileOutputStream(path.toString()));//Save the file     
}
    public static void writeToExcell2(JTable table, Path path) throws FileNotFoundException, IOException {
    
    XSSFWorkbook wb = new XSSFWorkbook(); //Excell workbook
    XSSFSheet sheet = wb.createSheet(); //WorkSheet
    Row row = sheet.createRow(0); //Row created at line 3
    TableModel model = table.getModel(); //Table model

    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        int cols = 5;
            row.createCell(0).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        

        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 1)); 
    }
    wb.write(new FileOutputStream(path.toString()));//Save the file     
}
    public static void OpenForm(JFrame f){
        try {
            f.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource("emp.jpg"));
            f.setIconImage(img);
            f.getContentPane().setBackground(Color.WHITE);
            f.setDefaultCloseOperation(2);
            //f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            f.setVisible(true);
        } catch (IOException ex) {
            Message(ex);
        }}
    public static String getFileExtension(File file) {
    String name = file.getName();
    int lastIndexOf = name.lastIndexOf(".");
    if (lastIndexOf == -1) {
        return ""; // empty extension
    }
    return name.substring(lastIndexOf);
}
    
    public static boolean ConfirmMessage(String message){
        int c = JOptionPane.showConfirmDialog(null, message);
        return c==JOptionPane.YES_OPTION;
}

    public static void Message(Object msg){  
        javax.swing.JOptionPane.showMessageDialog(null, msg);
    }
    
    public static void Write(Object out){  
    System.out .println(out);
    }
    
    public static int ReadInt (){                  
      Scanner s = new Scanner(System.in);
                return s.nextInt();
    }
    public static Boolean ReadBoolean (){                  
      Scanner s = new Scanner(System.in);
                return s.nextBoolean();
    }
    public static double ReadDouble (){                  
      Scanner s = new Scanner(System.in);
                return s.nextDouble();
    }
    public static String ReadString (){                  
      Scanner s = new Scanner(System.in);
                return s.nextLine();
    }
    
    public static void ClearTexts(Container form){
    for(Component c : form.getComponents()){
        if ( c instanceof JTextField){
            JTextField txt =(JTextField)c;
        txt.setText("");
        }
        else if (c instanceof Container){
        ClearTexts((Container)c);
        }}}
    public static void Capture(String ImageName , JFrame form){
        try {
            form.setState(1);
            Robot r = new Robot();
            Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage img = r.createScreenCapture(rec);
            ImageIO.write(img, "jpg", new File(ImageName +".jpg")); 
            form.setState(0);
        } catch (AWTException | HeadlessException | IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Object InputBox(String Title){
    Object obj = JOptionPane.showInputDialog(Title);
    return obj;
    }
    
        public class Table{
        
            public int NbrColumn;
            public Object[][] Items;
            
            public Table(int NbrColumn){
            this.NbrColumn=NbrColumn;
            Items = new Object[0][NbrColumn];
            }
            
            public void AddNewRow(Object[] row){
            Object[][] ItemsTap = Items;                
            Items = new Object [Items.length+1][NbrColumn];
            for (int i = 0 ; i<ItemsTap.length;i++){
            Items[i]=ItemsTap[i];
            }
            Items[Items.length-1]=row;
            }
            
            public void PrintItems(){
            for ( Object[] c : Items){
                for (Object obj : c){
                    System.out.print(obj+" ; ");
                }
                System.out.println();
                }}
            
            
            }
        
        
        
        }