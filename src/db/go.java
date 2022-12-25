package db;


import Tools.Tools;
import static Tools.Tools.Message;
import Tools.Tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class go {
    private static String url = "";
    public static String dbName ="codingmaster";
    private static Connection con;
    
    private static void setURL(){
    url = "jdbc:mysql://localhost/"+dbName+
      "?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; //pour utiliser l'arabe
    }
    
    
    private static void setConnection(){

        try {
                setURL();
            con=DriverManager.getConnection(url,"root","");
        } catch (SQLException ex) {
            Tools.Message(ex.getMessage());
        }
    }

    
    public static boolean CheckUserPass(String user,String pass){
        
    try{    setConnection();
            Statement s = con.createStatement();
            String strcheck="select * from agent where "+"username='"+user+"' and password='"+pass+"'";
            s.executeQuery(strcheck); //تنفيذ استعلام
            while (s.getResultSet().next()){//اذا وجد نتيجة او لا 
            return true;
            }
            con.close();}
    catch (SQLException ex){
         Message(ex);
                            }
    return false; }
    
    public static boolean RunNonQuery(String sqlStatement){
    try{
    setConnection();
    Statement s = con.createStatement();
    s.execute(sqlStatement);
    s.close();
    return true;
    }
    catch(SQLException ex){ 
        
        return false;
    }}
    
    public static String getAutoNumber(String tableName , String columnName){
        try {
            setConnection();
            Statement s = con.createStatement();
            String str = "select max("+columnName+")+1 as autonum"+" from "+tableName;
            s.executeQuery(str);
            String r="";
            while (s.getResultSet().next()){
            r=s.getResultSet().getString("autonum");
            }
            con.close();
            if (r==null || "".equals(r)){
            return "1";
            } else return r;
            
        } catch (SQLException ex) {
            Message(ex);
            return "0";
        }
    }

    public static Table getTableData(String Stat){
        Tools t = new Tools();
        try {
        setConnection();
        Statement s = con.createStatement();
            ResultSet rs;
            rs = s.executeQuery(Stat);
            ResultSetMetaData res = rs.getMetaData();
            int c = res.getColumnCount(); 
            Table tab = t.new Table(c);
            while (rs.next()){
            Object row[] = new Object[c];
            for(int i = 0 ; i<c;i++){
            row[i]=rs.getString(i+1);
            }
            tab.AddNewRow(row);
            }
            con.close();
            return tab;
        }
        catch(SQLException ex){
        Message(ex);
        return t.new Table(0);
        }}
    
    public static void fillCombo(String tableName , String ColumnName , JComboBox combo){
    try{
    setConnection();
    Statement s = con.createStatement();
    ResultSet rs;
    String stat = "select "+ColumnName+" from "+tableName;
    rs= s.executeQuery(stat);
    rs.last(); //mettre rs dans la dernier ligne 
    int c = rs.getRow();
    rs.beforeFirst(); // avant le début pour inisialiser comme la premier valeur par defaut et il ya aussi .first
    int i = 0;
    String Values[] = new String[c];
    while(rs.next()){
    Values[i]=rs.getString(1); i++;
    }
    con.close();
    combo.setModel(new DefaultComboBoxModel(Values));
    }
    catch (SQLException ex){
    Message(ex);
    }}
    public static String getTypeByUser(String user){

    try{
        String stat = "select type from agent where "+"username='"+user+"'";
    String val =(String) db.go.getTableData(stat).Items[0][0];
    return val;
    }
    catch(Exception ex){
        Message(ex); return null;
    }
      
    
      
    }
    
    public static void fillToJTableWithTableName (String TableName , JTable tab){
    try{
    setConnection();
    Statement s = con.createStatement();
    ResultSet rs;
    String select;
    select="select * from "+TableName;
    rs= s.executeQuery(select);
    ResultSetMetaData rsmd = rs.getMetaData();
    int c = rsmd.getColumnCount();
    DefaultTableModel model = (DefaultTableModel)tab.getModel();
    Vector row = new Vector();
    model.setRowCount(0);
    while(rs.next()){
      row = new Vector(c);
      for (int i =1 ;i<=c;i++){
      row.add(rs.getString(i));
      }
      model.addRow(row);
    }
    if (tab.getColumnCount()!=c)
    {Message("JTable columns  count not equal to query columns count\nJTable columns count is : "
            +tab.getColumnCount()+"\nQuery columns count is : "+c);}
    con.close();
} 
    catch(SQLException ex){ 
        Message(ex.getMessage());
    }
        
    }
    public static void fillToJTableWithStatement (String SelectStatement , JTable tab){
    try{
    setConnection();
    Statement s = con.createStatement();
    ResultSet rs;
    String str =SelectStatement.substring(0, 7).toLowerCase();//toLewerCase pour convert tout les lettres miniscul et substring pour couper le string
    String select;
    select=SelectStatement;
    rs= s.executeQuery(select);
    ResultSetMetaData rsmd = rs.getMetaData();
    int c = rsmd.getColumnCount();
    DefaultTableModel model = (DefaultTableModel)tab.getModel();
    Vector row = new Vector();
    model.setRowCount(0);
    while(rs.next()){
      row = new Vector(c);
      for (int i =1 ;i<=c;i++){
      row.add(rs.getString(i));
      }
      model.addRow(row);
    }
    if (tab.getColumnCount()!=c)
    {Message("JTable columns  count not equal to query columns count\nJTable columns count is : "
            +tab.getColumnCount()+"\nQuery columns count is : "+c);}
    con.close();
} 
    catch(SQLException ex){ 
        Message(ex.getMessage());
    }
        
    }
}
