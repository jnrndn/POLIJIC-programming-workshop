package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <strong>QueryService</strong> class<br>
 * In this class you're gonna found all the necessary queries to provide or save
 * data
 * <br>
 *
 * @author jnjre
 * @version 0.1.0, November 2017
 */
public class QueryService {

    /**
     * This method make a query to find a student with id param
     *
     * @param con
     * @param id
     * @return ResultSet with one row
     */
    public static ResultSet selectStudent(Connection con, int id) {
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("SELECT NOMBRE_ESTUDIANTE, APELLIDO_ESTUDIANTE, PROGRAMA, EDAD, TELEFONO,"
                    + "CORREO FROM PROYECTOTALLER.TABLA_ESTUDIANTE WHERE ID_ESTUDIANTE = "+ id);

        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }

    public static ResultSet selectCalificaiones(Connection con) {
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("SELECT TABLA_ESTUDIANTE.ID_ESTUDIANTE,NOMBRE_ESTUDIANTE,PARCIAL_1,PARCIAL_2 "
                    + "FROM PROYECTOTALLER.TABLA_ESTUDIANTE   INNER JOIN PROYECTOTALLER.TABLA_SEGUIMIENTO "
                    + "ON TABLA_ESTUDIANTE.ID_ESTUDIANTE=TABLA_SEGUIMIENTO.ID_ESTUDIANTE "
                    + "GROUP BY TABLA_ESTUDIANTE.ID_ESTUDIANTE,NOMBRE_ESTUDIANTE,PARCIAL_1,PARCIAL_2");

        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }

}
