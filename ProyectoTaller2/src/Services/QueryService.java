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
            System.out.println("Error selectSubjects: " + e);
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
    
    
     public static ResultSet selectStudentsPerSubtject(Connection con, int codigoA){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("SELECT E.ID_ESTUDIANTE, E.NOMBRE_ESTUDIANTE, A.NOMBRE "
                    + "FROM TABLA_ESTUDIANTE E "
                    + "JOIN TABLA_SEGUIMIENTO S ON "
                    + "E.ID_ESTUDIANTE = S.ID_ESTUDIANTE "
                    + "JOIN TABLA_ASIGNATURA A ON "
                    + "S.CODIGO_ASIGNATURA = A.CODIGO_ASIGNATURA "
                    + "WHERE A.CODIGO_ASIGNATURA= " +codigoA);
        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }
     
     
     public static ResultSet selectTeachersPerSubtject(Connection con ){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("select NOMBRE_PROFESOR P, NOMBRE_ESTUDIANTE E, NOMBRE A " 
                    + "from TABLA_PROFESOR P " 
                    + "join TABLA_ASIGNATURA A on "
                    + "P.ID_PROFESOR = A.ID_PROFESOR " 
                    + "join TABLA_SEGUIMIENTO S on " 
                    + "A.CODIGO_ASIGNATURA = S.CODIGO_ASIGNATURA " 
                    + "join TABLA_ESTUDIANTE E on " 
                    + "S.ID_ESTUDIANTE = E.ID_ESTUDIANTE;");
        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }
     
     
     public static ResultSet selectAverageGrades(Connection con, int codigoA ){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("select AVG(S.PARCIAL_1) AS PROMP1, AVG(S.PARCIAL_2) AS PROMP2, AVG(N.NOTA) AS NOTAP "
                    + "FROM TABLA_SEGUIMIENTO S "
                    + "JOIN TABLA_NOTA N ON " 
                    + "S.CODIGO_SEGUIMIENTO = N.CODIGO_SEGUIMIENTO " 
                    + "JOIN TABLA_ASIGNATURA A ON " 
                    + "S.CODIGO_ASIGNATURA = A.CODIGO_ASIGNATURA " 
                    + "WHERE A.CODIGO_ASIGNATURA = 001;" + codigoA);
        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }
}
