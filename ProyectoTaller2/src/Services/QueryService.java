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
    public static ResultSet selectStudent(Connection con, long id){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(
                "SELECT NOMBRE_ESTUDIANTE, APELLIDO_ESTUDIANTE, PROGRAMA, EDAD, TELEFONO, CORREO "
                + "FROM PROYECTOTALLER.TABLA_ESTUDIANTE "
                + "WHERE ID_ESTUDIANTE="+ id
            );
            
        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }
    
    /**
     * This method make a query to find a student with id param
     * @param con
     * @param id
     * @return ResultSet with one row
     */    
    public static ResultSet selectTeacher(Connection con, long id){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(
                "SELECT NOMBRE_PROFESOR, APELLIDO_PROFESOR, TELEFONO, CORREO, PROFESION "
                + "FROM PROYECTOTALLER.TABLA_PROFESOR "
                + "WHERE ID_PROFESOR="+ id
            );
            
        } catch (SQLException e) {
            System.out.println("Error selectTeacher: " + e);
        }
        return rs;
    }
    
    /**
     * this method make a query to get all subjects in DB
     * @param con
     * @param id
     * @return subjects stored in DB
     */
    
    public static ResultSet selectSubjectsForTeacher(Connection con, long id ){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(
                "SELECT CODIGO_ASIGNATURA, NOMBRE "
                + "FROM PROYECTOTALLER.TABLA_ASIGNATURA "
                + "WHERE ID_PROFESOR ="+ id
            );
            
        } catch (SQLException e) {
            System.out.println("Error selectSubjectsForTeacher: " + e);
        }
        return rs;
    }

    public static ResultSet selectGrades(Connection con) {
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("SELECT TABLA_ESTUDIANTE.ID_ESTUDIANTE, NOMBRE_ESTUDIANTE, PARCIAL_1, PARCIAL_2 "
                    + "FROM PROYECTOTALLER.TABLA_ESTUDIANTE INNER JOIN PROYECTOTALLER.TABLA_SEGUIMIENTO "
                    + "ON TABLA_ESTUDIANTE.ID_ESTUDIANTE = TABLA_SEGUIMIENTO.ID_ESTUDIANTE "
                    + "GROUP BY TABLA_ESTUDIANTE.ID_ESTUDIANTE, NOMBRE_ESTUDIANTE, PARCIAL_1, PARCIAL_2");

        } catch (SQLException e) {
            System.out.println("Error selectGrades: " + e);
        }
        return rs;
    }
    
    
    
     public static ResultSet selectStudentsPerSubtject(Connection con, int codigo){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("SELECT E.ID_ESTUDIANTE, E.NOMBRE_ESTUDIANTE, E.APELLIDO_ESTUDIANTE, A.NOMBRE "
                    + "FROM PROYECTOTALLER.TABLA_ESTUDIANTE E "
                    + "JOIN PROYECTOTALLER.TABLA_SEGUIMIENTO S ON "
                    + "E.ID_ESTUDIANTE = S.ID_ESTUDIANTE "
                    + "JOIN PROYECTOTALLER.TABLA_ASIGNATURA A ON "
                    + "S.CODIGO_ASIGNATURA = A.CODIGO_ASIGNATURA "
                    + "WHERE A.CODIGO_ASIGNATURA = " +codigo);
        } catch (SQLException e) {
            System.out.println("Error selectStudentsPerSubtject: " + e);
        }
        return rs;
    }
}
