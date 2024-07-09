package controladores;

import javax.swing.JTextField;

import Gui.Panta_Login;
import snake2.AlertException;
import snake2.Front.Pantalla;

/**
 * Clase que se encarga de manipular la ventana del inicio de sesion
 * 
 * @version 1.1.5
 */
public class Controlador_Login extends Pantalla{
    public static Panta_Login ventana = new Panta_Login();
    public static JTextField usuario;
    public static String nombreUsuario;

    /**
     * Accion que se ejecuta al presionar el boton Ingresar que se encuentra en la pantalla, esta
     * toma el texto ingresado por el usuario
     * 
     * @version 1.1.5
     */
    public static void eventAsignarUsuario() {
        nombreUsuario = usuario.getText();
        
        try{
            if(nombreUsuario.isEmpty()){
                throw new AlertException("Por favor, ingrese un nombre de usuario.");
            }
            System.out.println("Usuario creado: " + nombreUsuario);
            ventana.dispose();
            Controlador_MenuPrincipal.inicializar(Controlador_MenuPrincipal.ventana);
            Controlador_MenuPrincipal.iniciarMusica();
        }catch(AlertException e){
            e.mostrarAlerta();
        }  
    }
}
