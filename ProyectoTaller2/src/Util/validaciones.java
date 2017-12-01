package util;
//IMPORTACIONES
import FrontEnd.StartView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class validaciones{
    //Método para validar que un TXT sea de sólo Letras
    public void Letras(final JTextField a){
        a.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent KE){
                char c=KE.getKeyChar();
                if(!Character.isLetter(c)&&c!=' '){//AQUÍ ESTOY COMPARANDO SI ES UNA LETRA O ESPACIO EN BLANCO
                    KE.consume();}}});//ANULA EVENTOS DEL TECLADO
    }
    //Método para validar que un TXT sea de sólo Números
    public void Numeros(JTextField a){
        a.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent KE){
                char c=KE.getKeyChar();
                if(!Character.isDigit(c)){//AQUÍ ESTOY COMPARANDO SI ES UN NÚMERO
                    KE.consume();}}});//ANULA EVENTOS DEL TECLADO
    }
    //Métodp para validar que un TXT sea de letras y números
    public void LetrasyNumeros(JTextField a){
        a.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent KE){
                char c=KE.getKeyChar();
                if(!Character.isLetterOrDigit(c)&&c!=' '){//AQUÍ ESTOY COMPARANDO SI SON NÚMEROS, LETRAS O ESPACIO EN BLANCO
                    KE.consume();}}});//ANULA EVENTOS DEL TECLADO
    }
    //Método para válidar si un correo esta bien
    public boolean esMail(String correo){
        boolean correcto=false;
        Pattern patronEmail=Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcherMail=patronEmail.matcher(correo.toLowerCase());
        if(matcherMail.matches()){
            correcto=true;}
        return correcto;
    }
    //Método para saber si un String es Número
    public boolean esNúmero(String texto){
        try{
            Integer.parseInt(texto);
            return true;}
        catch(NumberFormatException e){
           return false;}
    }
    
//    public boolean Salir(){
//        String boton [] = {"Aceptar"};
//        int eleccion = JOptionPane.showOptionDialog(this,  "¿Seguro que desea cerrar sesión?", "Titulo",
//                0, 0, null, boton, this);
//        if (eleccion == JOptionPane.YES_OPTION){
//            //this.dispose();
//            StartView startview = new StartView();
//        }
//    }
    
}
    

