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
    
    public void getTeacher(long id){
         ResultSet rs = QueryService.selectTeacher(this.con, id);
        try {
            while(rs.next()){
                System.out.println(
                        rs.getString("NOMBRE_PROFESOR") + " " +
                        rs.getString("APELLIDO_PROFESOR") + " " + 
                        rs.getString("PROFESION")
                );
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
