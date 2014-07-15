package speech.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import speech.gui.Constants;


/**
 *
 * @author namishah
 */
public class DAO implements Constants {
    private static final DAO instance = new DAO();
    public static DAO getInstance(){
        return instance;
    }
    private Connection conn;
    
    private DAO(){
        try {
            Class.forName(Constants.DRIVER).newInstance();
            String url = Constants.URL;
            conn = DriverManager.getConnection(url, Constants.USER, Constants.PASS);        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection(){
        return conn;
    }
}
