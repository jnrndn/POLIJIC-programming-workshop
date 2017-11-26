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
    
}