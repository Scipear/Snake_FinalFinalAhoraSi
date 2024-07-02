package controladores;

import Gui.Panta_Login;
import javax.swing.JTextField;
import snake2.Front.Pantalla;
import snake2.Jugador;

public class Login_Controlador extends Pantalla{


    public static Panta_Login ventanaPrin = new Panta_Login();
    public static JTextField usuario = new JTextField();
    public static String nombreUsuario;

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
    }

    public static void eventAsignarUsuario() {

        nombreUsuario = usuario.getText();

        //Jugador jugador = new Jugador(nombreUsuario);
        System.out.println("Usuario creado");
        ocultar(ventanaPrin);
        Controlador_MenuPrinc.mostrar(); // En esta linea se abre la pantalla del menu principal
        //comprobacion, revisar luego
       /* if (!nombreUsuario.isEmpty()) {
            Jugador jugador = new Jugador(nombreUsuario);
            System.out.println("Usuario creado");
            ocultar();
            Controlador_MenuPrinc.mostrar();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de usuario.");
            Controlador_MenuPrinc.mostrar();
            ocultar();
        }*/
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }
}
