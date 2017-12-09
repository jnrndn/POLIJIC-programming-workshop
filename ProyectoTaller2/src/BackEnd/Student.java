package BackEnd;

import Services.ConnectionService;
import Services.QueryService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    
    /**
     * This methos takes a param and sent it
     * to queryservice to make a query to fill
     * the combobox with student's subjects
     * @param id
     * @return ResultSet 
     */
    public ResultSet fillComboStudentSubjects(long id){
        return QueryService.selectSubjectsForStudent(this.con, id);
    }
    
    /**
     * This method get the logic to fill table with all grades and 
     * calculate the average grade of a student.
     * @param subject_name
     * @param model 
     */
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
    
 
    
    public float calcAvegare(Object subject_name){
        int cod_seguimiento = 0;
        float p1 = 0, p2 = 0, g = 0;
        ResultSet rsEx = QueryService.selectExams(this.con, this.id, this.getSubjectCode(subject_name.toString()));
        try {
            while(rsEx.next()){
                p1 = Float.parseFloat(rsEx.getString("PARCIAL_1"));
                p2 = Float.parseFloat(rsEx.getString("PARCIAL_2"));
                cod_seguimiento = Integer.parseInt(rsEx.getString("CODIGO_SEGUIMIENTO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        ResultSet rsGr = QueryService.selectGrades(this.con, cod_seguimiento );
        try {
            while(rsGr.next()){
                g += Float.parseFloat(rsGr.getString("NOTA")); 
                i++;
            }
            //Validate if i = 0 to avoid division by 0
            if(i == 0){ i = 1;}
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        float avg = (((p1+p2)/2) + (g/i))/2;
        return avg;
    }
    
    public void deleteSubject(Object subject_name, String id) {
        //Verify input id be the same as current session id
        if (Long.parseLong(id) != this.id) {
            JOptionPane.showConfirmDialog(
                null,
                "No se a podido completar la operacion",
                "Alerta!",
                JOptionPane.WARNING_MESSAGE
            );
        } else {
            int cod_seguimiento = 0;
            ResultSet rsEx = QueryService.selectFollowUpCode(this.con, this.id, this.getSubjectCode(subject_name.toString()));
            try {
                while (rsEx.next()) {
                    cod_seguimiento = Integer.parseInt(rsEx.getString("CODIGO_SEGUIMIENTO"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
            int rows = QueryService.deleteGrades(this.con, cod_seguimiento);
            int rows1 = QueryService.deleteSubject(this.con, this.id, cod_seguimiento);
            if(rows1 != 0){
                JOptionPane.showConfirmDialog(
                null,
                 subject_name.toString() +" ha sido cancelada exitosamente",
                "Alerta!",
                JOptionPane.WARNING_MESSAGE
            );
            }
        }
    }
    
    /**
     * Method to know what's the subject code 
     * @param subject_name
     * @return subject code
     */
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
}
