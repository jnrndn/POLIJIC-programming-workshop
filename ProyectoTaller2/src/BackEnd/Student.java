package BackEnd;

import Services.ConnectionService;
import Services.QueryService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * <strong>Student</strong> class
 * This class contains all methods
 * related to studen role
 * @author Juan José Rendón
 * @version 0.1.0 November 2017
 */
public class Student {
    
    private long id;
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
    public Student(long id, String nombre, String apellido, String programa, int edad, int telefono, String correo) {
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
     * and get a speceific student, return a Resultset 
     * with student information
     * @param id
     * @return true if result set is not empty
     */
    public boolean isStudent(long id){
        ResultSet rs = QueryService.selectStudent(this.con, id);
        boolean isStudent =false;
        try {
            while (rs.next()) {
                this.id = id;
                this.setNombre(rs.getString("NOMBRE_ESTUDIANTE"));
                this.setApellido(rs.getString("APELLIDO_ESTUDIANTE"));
                this.setTelefono( Integer.parseInt(rs.getString("TELEFONO")));
                isStudent = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isStudent;
    }
    
    public ResultSet fillComboStudentSubjects(long id){
        return QueryService.selectSubjectsForStudent(this.con, id);
    }
    
    public void getExams(Object subject_name, DefaultTableModel model){
        Object[] dataSet = new Object[2];
        int cod_seguimiento = 0;
        ResultSet rsE = QueryService.selectExams(this.con, this.id, this.getSubjectCode(subject_name.toString()));
        try {
            while(rsE.next()){
                dataSet[0] = rsE.getString("PARCIAL_1");
                dataSet[1] = rsE.getString("PARCIAL_2");
                cod_seguimiento = Integer.parseInt(rsE.getString("CODIGO_SEGUIMIENTO"));
                model.addRow(dataSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 1;
        ResultSet rsG = QueryService.selectGrades(this.con, cod_seguimiento );
        try {
            while(rsG.next()){
                model.addColumn(
                        "Nota " + i + " - "+ rsG.getString("NOMBRE"),
                        new String[]{rsG.getString("NOTA")} 
                );
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int getSubjectCode(String subject_name) {
        int code = 0;
        switch (subject_name) {
            case "Matematicas":
                code = 001;
                break;
            case "Ciencias naturales":
                code = 002;
                break;
            case "Humanidades":
                code = 003;
                break;
            case "Etica":
                code = 004;
                break;
        }
        return code;
    }
        
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void getGrades(){
        ResultSet rs = QueryService.selectGradesBySubject(this.con);
        
        try{
            while(rs.next()){
                
                System.out.println(
                        rs.getString("ID_ESTUDIANTE")+" "+
                        rs.getString("NOMBRE_ESTUDIANTE")+" "+
                        rs.getString("PARCIAL_1")+" "+
                        rs.getString("PARCIAL_2")
                );      
            }
        }catch (SQLException ex){
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    
}
