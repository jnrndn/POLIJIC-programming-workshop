/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author jnjre
 */
public class Teacher {

    private long id;
    private String nombre;
    private String apellido;
    private int telefono;
    private String correo;
    private String profesion;
    private Connection con;

    public Teacher() {
        this.getConnection();
    }

    public Teacher(long id, String nombre, String apellido, int telefono, String correo, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.profesion = profesion;
    }
    
    private void getConnection(){
        this.con = ConnectionService.connect();
    }
    
    public boolean isTeacher(long id){
        ResultSet rs = QueryService.selectTeacher(this.con, id);
        boolean isTeacher = false;
        try {
            while (rs.next()) {
                this.id = id;
                this.setNombre(rs.getString("NOMBRE_PROFESOR"));
                this.setApellido(rs.getString("APELLIDO_PROFESOR"));
                this.setProfesion(rs.getString("PROFESION"));
                isTeacher = true;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isTeacher;
    }

    
    public ResultSet fillComboSubjects(long id){
        return QueryService.selectSubjectsForTeacher(this.con, id);
    }
    
    public void getStudentsPerSubtject(Object name, DefaultTableModel model){
        int code = this.getSubjectCode(name.toString());
        Object[] dataSet = new Object[3];
        ResultSet rs =  QueryService.selectStudentsPerSubject(this.con, code);
        try {
            while(rs.next()){
                dataSet[0] = rs.getString("ID_ESTUDIANTE");
                dataSet[1] = rs.getString("NOMBRE_ESTUDIANTE");
                dataSet[2] = rs.getString("APELLIDO_ESTUDIANTE");
                model.addRow(dataSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public float[] getAverageGrades(Object name){
        int code = this.getSubjectCode(name.toString());
        
        ResultSet rs = QueryService.selectAverageGrades(this.con, code);
        float[] avgs = new float[3];
        try {
            while(rs.next()){
                avgs[0] = Float.parseFloat(rs.getString("PROMP1"));
                avgs[1] = Float.parseFloat(rs.getString("PROMP2"));
                avgs[2] = Float.parseFloat(rs.getString("NOTAP"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avgs;
    }
    
    public int getSubjectCode(String subject_name){
        int code = 0;
        switch(subject_name){
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

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    public int insertGrade(int cod, String nombre, float nota, int codSegui) {
        
        return  QueryService.InsertGradreStudent(con, cod, nombre, nota, codSegui);
        
    }

    
    public void getUpdateExams(int subject_name, DefaultTableModel model, long id){
        Object[] dataSet = new Object[2];
        int cod_seguimiento = 0;
        ResultSet rsE = QueryService.selectFollowUpCode(this.con, id, subject_name);
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
   
    
}
