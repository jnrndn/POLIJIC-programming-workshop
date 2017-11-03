/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jnjre
 */
public class ConnectionService {
    //Metodo para conectar con la base de datos
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
}
