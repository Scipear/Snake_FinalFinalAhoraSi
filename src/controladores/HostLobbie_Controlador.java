package controladores;

import Gui.Panta_Online_HostLobbie;
import snake2.Front.GraficoPersonaje;
import snake2.Front.Pantalla;

public class HostLobbie_Controlador extends Pantalla{

//Para poder usarlo en cualquier parte del codigo
    public static Panta_Online_HostLobbie ventanaPrin = new Panta_Online_HostLobbie();
    public static GraficoPersonaje seleccion;
    public static int skinSeleccionada;
    public static int mapaSeleccionado;

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
        System.out.println("Pantalla del lobbie del host");
    }



    //Metodo encargado de iniciar la pantalla de juego
    public static void eventIniciar() {
        Controlador_MenuPrinc.pararMusica();
        Game_Controlador.nuevoJuego();
        ocultar(ventanaPrin);
    }

    public static void eventVolverAlMenu() {
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaPrin);
    }

    public static void selec_Skin1() {
        skinSeleccionada = 1;
        System.out.println("Selecciono skin ");
    }

    public static void selec_Skin2() {
        skinSeleccionada = 2;
        System.out.println("Selecciono skin ");
    }

    public static void selec_Skin3() {
        skinSeleccionada = 3;
        System.out.println("Selecciono skin ");
    }

    public static void selec_Skin4() {
        skinSeleccionada = 4;
        System.out.println("Selecciono skin ");
    }

    public static void selec_Skin5() {
        skinSeleccionada = 5;
    }

    public static void selec_Skin6() {
        skinSeleccionada = 6;
    }

    public static int getSkinSeleccionada() {
        return skinSeleccionada;
    }
    /*
    public static void selec_Mapa1() {

    }

    public static void selec_Mapa2() {

    }

    public static void selec_Mapa3() {

    }
    
    public static int getSkinSeleccionada() {
        return mapaSeleccionado;
    }
     */
}
