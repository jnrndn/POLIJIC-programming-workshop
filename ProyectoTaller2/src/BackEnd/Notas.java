
package BackEnd;

import Services.ConnectionService;
import java.sql.Connection;


public class Notas {
    private int codigo_nota;
    private String nombre;
    private int nota;
    private  int codigo_seguimiento;
    private Connection con;

    public Notas(int codigo_nota, String nombre, int nota, int codigo_seguimiento) {
        this.codigo_nota = codigo_nota;
        this.nombre = nombre;
        this.nota = nota;
        this.codigo_seguimiento = codigo_seguimiento;
    }
    
     public Notas() { 
        this.getConnection();
    }
      private void getConnection(){
        this.con = ConnectionService.connect();
    }
    
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    

    public int getCodigo_nota() {
        return codigo_nota;
    }

    public void setCodigo_nota(int codigo_nota) {
        this.codigo_nota = codigo_nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getCodigo_seguimiento() {
        return codigo_seguimiento;
    }

    public void setCodigo_seguimiento(int codigo_seguimiento) {
        this.codigo_seguimiento = codigo_seguimiento;
    }
    
}
