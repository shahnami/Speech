package speech.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author namishah
 */
public class Database implements Log{
    
    private final Connection connection;

    public Database(){
        connection = DAO.getInstance().getConnection();
    }
    
    @Override
    public List<String> getLogsByDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getLogsByTime(String time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getLogsByPartialContent(String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLogById(int id) {
        List<String> lstContent = new ArrayList<>();
        try {
            String sql = "SELECT id, content, date, time, author FROM tblLog WHERE id = " + id;
            Statement selectStatement = connection.createStatement();

            ResultSet results = selectStatement.executeQuery(sql);            
            while(results.next()){
                String content = results.getString("content");
                lstContent.add(content);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstContent.get(0);
    }
   
    @Override
    public void insertLog(String content, String author) {
        try {
            Date now = Calendar.getInstance().getTime();
            SimpleDateFormat datefm = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timefm = new SimpleDateFormat("HH:mm");
            
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO tblLog(content, date, time, author) "
                            + "VALUES(?, ?, ?, ?)");
            statement.setString(1, content);
            statement.setString(2, datefm.format(now));
            statement.setString(3, timefm.format(now));
            statement.setString(4, author);
            if(!content.isEmpty()){
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<String> getAllLogsFormat() {
        List<String> lstContent = new ArrayList<>();
        try {
            String sql = "SELECT id, date, time, author FROM tblLog";
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while(results.next()){
                String date = results.getString("date");
                String time = results.getString("time");
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat dow = new SimpleDateFormat( "dd/MM/yy" );
                java.util.Date date1 = dow.parse(date);
                dow.applyPattern("EEE");
                String day= dow.format(date1); 
                String author = results.getString("author");
                String id = results.getString("id");
                // Mon 17/05/2014 at 14:30 [17]
                lstContent.add(0, day + " " + date + " " + " at " + time + " [" + id + "]");
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstContent;
    }

    @Override
    public void deleteLog(int id) {
        try {
            Statement deleteStatement = connection.createStatement();
            String sql = "DELETE FROM tblLog WHERE id = " + id;
            deleteStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveLog(int id, String content) {
        try {
            if(!content.isEmpty()){
                PreparedStatement statement = connection.prepareStatement("UPDATE tblLog SET content = ? WHERE id = ?");
                statement.setString(1, content);
                statement.setInt(2, id);
                statement.executeUpdate();
            } else {
                //Don't want to do that now do we?
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}