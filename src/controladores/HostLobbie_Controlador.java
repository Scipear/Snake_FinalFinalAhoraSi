package controladores;

import javax.swing.JOptionPane;

import Gui.Panta_Online_HostLobbie;
import snake2.Game;
import snake2.Contenedor_Paquetes.Paquete02Play;

public class HostLobbie_Controlador extends ClienteLobbie_Controlador{

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_Online_HostLobbie ventanaHost = new Panta_Online_HostLobbie();
    public static int mapaSeleccionado = -1;

    //Metodos
    public static void mostrar() {
        inicializar(ventanaHost);
        System.out.println("Pantalla del lobbie del host");
    }

    //Metodo encargado de iniciar la pantalla de juego
    public static void eventIniciar() {
        if(skinSeleccionada == -1 || mapaSeleccionado == -1){
            JOptionPane.showMessageDialog(null, "Por favor, elige una skin y un mapa para poder jugar");        
        
        }else{
            Paquete02Play play = new Paquete02Play(Login_Controlador.getNombreUsuario(), skinSeleccionada);
            play.enviarData(PreConeccion_Controlador.cliente);
            Controlador_MenuPrinc.pararMusica();
            ocultar(ventanaHost);
        }
    }

    public static void eventVolverAlMenu() {
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaHost);
    }

    public static void selec_Mapa1() {
        mapaSeleccionado = 0;
        System.out.println("Selecciono mapa 1");
    }

    public static void selec_Mapa2() {
        mapaSeleccionado = 1;
        System.out.println("Selecciono mapa 2");
    }

    public static void selec_Mapa3() {
        mapaSeleccionado = 2;
        System.out.println("Selecciono mapa 3");
    }

    public static void selec_Mapa4() {
        mapaSeleccionado = 3;
        System.out.println("Selecciono mapa 3");
    }

    public static int getMapaSeleccionada() {
        return mapaSeleccionado;
    }

}
