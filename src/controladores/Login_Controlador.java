package controladores;

import Gui.Panta_Login;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import snake2.Front.Pantalla;

public class Login_Controlador extends Pantalla{
    public static Panta_Login ventanaPrin = new Panta_Login();
    public static JTextField usuario;
    public static String nombreUsuario;

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
    }

    public static void eventAsignarUsuario() {
        nombreUsuario = usuario.getText();

       if(!nombreUsuario.isEmpty()){
            System.out.println("Usuario creado: " + nombreUsuario);
            ocultar(ventanaPrin);
            Controlador_MenuPrinc.mostrar();
            
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de usuario.");
        }
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }
}
