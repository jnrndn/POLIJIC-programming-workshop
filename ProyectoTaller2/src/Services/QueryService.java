package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *<strong>QueryService</strong> class<br>
 * In this class you're gonna found all 
 * the necessary queries to provide or save data
 * <br>
 * @author jnjre
 * @version 0.1.0, November 2017 
 */
public class QueryService {
    
    /**
     * This method make a query to find a student with id param
     * @param con
     * @param id
     * @return ResultSet with one row
     */    
    public static ResultSet selectStudent(Connection con, int id){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("SELECT NOMBRE_ESTUDIANTE, APELLIDO_ESTUDIANTE, PROGRAMA, EDAD, TELEFONO, "
                    + "CORREO FROM PROYECTOTALLER.TABLA_ESTUDIANTE WHERE ID_ESTUDIANTE="+ id);
            
        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }
    
    
    
     public static ResultSet selectStudentsPerSubtject(Connection con, int codigoA){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("select E.ID_ESTUDIANTE, E.NOMBRE_ESTUDIANTE, A.NOMBRE "
                    + "from TABLA_ESTUDIANTE E "
                    + "join TABLA_SEGUIMIENTO S on "
                    + "E.ID_ESTUDIANTE = S.ID_ESTUDIANTE "
                    + "join TABLA_ASIGNATURA A on "
                    + "S.CODIGO_ASIGNATURA = A.CODIGO_ASIGNATURA "
                    + "where A.CODIGO_ASIGNATURA= " +codigoA);
        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }
}
