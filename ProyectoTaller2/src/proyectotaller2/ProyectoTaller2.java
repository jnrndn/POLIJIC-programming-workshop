package proyectotaller2;

import BackEnd.Student;
import java.sql.SQLException;

public class ProyectoTaller2 {

    public static void main(String[] args) {
        
        Student stud = new Student();
       stud.getStudent(1152456846);
        stud.getCalificaciones();
    }
    
}
