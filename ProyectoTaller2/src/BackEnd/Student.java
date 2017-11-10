package BackEnd;

import Services.ConnectionService;
import Services.QueryService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <strong>Student</strong> class
 * This class contains all methods
 * related to studen role
 * @author Juan José Rendón
 * @version 0.1.0 November 2017
 */
public class Student {
    
    private int id;
    private String nombre;
    private String apellido;
    private String programa;
    private int edad;
    private int telefono;
    private String correo;
    private Connection con;
    
    /**
     * Empty constructor to connect with DB
     */
    public Student() { 
        this.getConnection();
    }

    /**
     * Constructor overload to fill all the params
     * @param id
     * @param nombre
     * @param apellido
     * @param programa
     * @param edad
     * @param telefono
     * @param correo 
     */
    public Student(int id, String nombre, String apellido, String programa, int edad, int telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.programa = programa;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    /**
     * Method to connect with DB
     */
    private void getConnection(){
        this.con = ConnectionService.connect();
    }
    
    /**
     * This method take id param to make a query to DB 
     * and get a speceific student
     * @param id 
     */
    public void getStudent(int id){
        ResultSet rs = QueryService.selectStudent(this.con, id);
        try {
            while(rs.next()){
                System.out.println(
                        rs.getString("NOMBRE_ESTUDIANTE") + " " +
                        rs.getString("APELLIDO_ESTUDIANTE") + " " + 
                        rs.getString("TELEFONO")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
