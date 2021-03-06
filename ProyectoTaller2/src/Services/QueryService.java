package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

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
    
    public static ResultSet selectSubjectsForStudent(Connection con, long id){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(
                "SELECT A.NOMBRE " +
                "FROM PROYECTOTALLER.TABLA_ASIGNATURA A INNER JOIN PROYECTOTALLER.TABLA_SEGUIMIENTO S " +
                "ON S.CODIGO_ASIGNATURA = A.CODIGO_ASIGNATURA " +
                "WHERE S.ID_ESTUDIANTE = " + id
            );
            
        } catch (SQLException e) {
            System.out.println("Error selectSubjectsForTeacher: " + e);
        }
        return rs;
    }

    public static ResultSet selectGradesBySubject(Connection con) {
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
    
    
    
     public static ResultSet selectStudentsPerSubject(Connection con, int codigo){
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
     
     
     public static ResultSet selectTeachersPerSubtject(Connection con ){
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery("SELECT NOMBRE_PROFESOR P, NOMBRE_ESTUDIANTE E, NOMBRE A " 
                    + "FROM  PROYECTOTALLER.TABLA_PROFESOR P " 
                    + "JOIN  PROYECTOTALLER.TABLA_ASIGNATURA A ON "
                    + "P.ID_PROFESOR = A.ID_PROFESOR " 
                    + "JOIN  PROYECTOTALLER.TABLA_SEGUIMIENTO S ON " 
                    + "A.CODIGO_ASIGNATURA = S.CODIGO_ASIGNATURA " 
                    + "JOIN  PROYECTOTALLER.TABLA_ESTUDIANTE E ON " 
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
            rs = st.executeQuery("SELECT AVG(S.PARCIAL_1) AS PROMP1, AVG(S.PARCIAL_2) AS PROMP2, AVG(N.NOTA) AS NOTAP "
                    + "FROM  PROYECTOTALLER.TABLA_SEGUIMIENTO S "
                    + "JOIN  PROYECTOTALLER.TABLA_NOTA N ON " 
                    + "S.CODIGO_SEGUIMIENTO = N.CODIGO_SEGUIMIENTO " 
                    + "JOIN  PROYECTOTALLER.TABLA_ASIGNATURA A ON " 
                    + "S.CODIGO_ASIGNATURA = A.CODIGO_ASIGNATURA " 
                    + "WHERE A.CODIGO_ASIGNATURA = " + codigoA);
        } catch (SQLException e) {
            System.out.println("Error selectStudent: " + e);
        }
        return rs;
    }
     
    public static ResultSet selectExams(Connection con, long id, int cod) {
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(
                    "SELECT CODIGO_SEGUIMIENTO, PARCIAL_1, PARCIAL_2 "
                    + "FROM PROYECTOTALLER.TABLA_SEGUIMIENTO "
                    + "WHERE ID_ESTUDIANTE = " + id + " AND CODIGO_ASIGNATURA = " + cod
            );
        } catch (SQLException e) {
            System.out.println("Error selectExams: " + e);
        }
        return rs;
    }
    
    public static ResultSet selectGrades(Connection con, int cod) {
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(
                    "SELECT CODIGO_NOTA, NOMBRE, NOTA "
                    + "FROM PROYECTOTALLER.TABLA_NOTA "
                    + "WHERE CODIGO_SEGUIMIENTO = " + cod
            );
        } catch (SQLException e) {
            System.out.println("Error selectGrades: " + e);
        }
        return rs;
    }
    
    public static ResultSet selectFollowUpCode(Connection con, long id, int cod) {
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(
                    "SELECT CODIGO_SEGUIMIENTO, PARCIAL_1, PARCIAL_2 "
                    + "FROM PROYECTOTALLER.TABLA_SEGUIMIENTO "
                    + "WHERE ID_ESTUDIANTE = " + id + " AND CODIGO_ASIGNATURA = " + cod
            );
        } catch (SQLException e) {
            System.out.println("Error selectFollowUpCode: " + e);
        }
        return rs;
    }
    
 
    
    public static int deleteGrades(Connection con, int cod) {
        int filas = 0;
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            filas = st.executeUpdate(
                    "DELETE "
                    + "FROM PROYECTOTALLER.TABLA_NOTA "
                    + "WHERE TABLA_NOTA.CODIGO_SEGUIMIENTO = " + cod
            );
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error deleteGrades: " + e);
        }
        return filas;
    }
    
    public static int deleteSubject(Connection con, long id, int cod) {
        int filas = 0;
        ResultSet rs = null;
        try {
            Statement st;
            st = con.createStatement();
            filas = st.executeUpdate(
                    "DELETE "
                    + "FROM PROYECTOTALLER.TABLA_SEGUIMIENTO "
                    + " WHERE ID_ESTUDIANTE = " + id + "AND CODIGO_ASIGNATURA = " + cod
            );
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error deleteSubject: " + e);
        }
        return filas;
    }
    
    
     public static int InsertGradreStudent(Connection con, int cod, String nombre, float nota, int codSegui) {
        int filas = 0;
        try {
            Statement st;
            String sql = "INSERT INTO PROYECTOTALLER.TABLA_NOTA VALUES("+ cod +",'" +nombre +"',"+ nota +","+codSegui+")";
            st = con.createStatement();
            filas = st.executeUpdate(sql);
        } catch (SQLException e) {

        }
        return filas;
    }

}
