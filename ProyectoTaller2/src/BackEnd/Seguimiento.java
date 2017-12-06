
package BackEnd;

import Services.ConnectionService;
import Services.QueryService;
import java.sql.Connection;
import java.sql.ResultSet;


public class Seguimiento {
    private int codigo_seguimiento;
    private int parcial;
    private int parcia2;
    private int codigo_asignatura;
    private int id_estudiante;
    private Connection con;
     
    public void NotasSeguimiento(int p1,int p2){
        
    this.parcial=p1;
    this.parcia2=p2; 
    }
    
     ResultSet rs =  QueryService.UptadeStudent(this.con,this.parcial,this.parcia2);
     
     public Seguimiento() { 
        this.getConnection();
    }
      private void getConnection(){
        this.con = ConnectionService.connect();
    }
    public int getCodigo_seguimiento() {
        return codigo_seguimiento;
    }

    public void setCodigo_seguimiento(int codigo_seguimiento) {
        this.codigo_seguimiento = codigo_seguimiento;
    }

    public int getParcial() {
        return parcial;
    }

    public void setParcial(int parcial) {
        this.parcial = parcial;
    }

    public int getParcia2() {
        return parcia2;
    }

    public void setParcia2(int parcia2) {
        this.parcia2 = parcia2;
    }

    public int getCodigo_asignatura() {
        return codigo_asignatura;
    }

    public void setCodigo_asignatura(int codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
}
