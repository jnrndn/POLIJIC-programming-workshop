package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionService class
 * We're gonna use this class
 * as a service to connect and 
 * desconnect our Data Base
 * <br>
 * @author Juan José Rendón
 * @version 1.0, November 2017.
 * 
 */
public class ConnectionService {
    
    /**
     * This method create a connection with data base
     * @return Connection object
     */
    public static Connection connect(){
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "123456");
            System.out.println(con);
            if (con != null){
                System.out.println("Conexión establecida.");
            }
        } catch (SQLException e) {
            System.out.println(e + "SQLException");
        } catch (ClassNotFoundException ex){
            System.out.println(ex.getCause() + " ClassNotFound");
        }
        return con;
    }
    
    /**
     * This method recives a Connection object and 
     * close the connection with data base
     * @param con
     * @return true if connection was succesfuly closed or false if connection is still up
     */
    public static boolean desconnect(Connection con ){
        boolean desconected = false; 
        try {
            con.close();
            if(con.isClosed()){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error desconnnectig DB: " + e);
        }
        return desconected;
    }
}
