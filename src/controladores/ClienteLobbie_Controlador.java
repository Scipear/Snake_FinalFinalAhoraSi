package controladores;

import Gui.Panta_Online_ClienteLobbie;
import snake2.Front.GraficoPersonaje;
import snake2.Front.Pantalla;

public class ClienteLobbie_Controlador extends Pantalla{

//Para poder usarlo en cualquier parte del codigo
    public static Panta_Online_ClienteLobbie ventanaCliente = new Panta_Online_ClienteLobbie();
    public static GraficoPersonaje seleccion;
    public static int skinSeleccionada = -1;
    public static boolean isReady = false;

    //Metodos
    public static void mostrar() {
        inicializar(ventanaCliente);
    }

    //Metodo encargado de iniciar la pantalla de juego
    public static void eventIniciar() {
        isReady = true;
        Controlador_MenuPrinc.pararMusica();
        ocultar(ventanaCliente);
    }

    public static void eventVolverAlMenu() {
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaCliente);

    }

    public static void selec_Skin1(){
        skinSeleccionada = 1;
        System.out.println("Selecciono skin 1");
    }

    public static void selec_Skin2() {
        skinSeleccionada = 2;
        System.out.println("Selecciono skin 2");
    }

    public static void selec_Skin3() {
        skinSeleccionada = 3;
        System.out.println("Selecciono skin 3");
    }

    public static void selec_Skin4() {
        skinSeleccionada = 4;
        System.out.println("Selecciono skin 4");
    }

    public static void selec_Skin5() {
        skinSeleccionada = 5;
        System.out.println("Selecciono skin 5");
    }

    public static void selec_Skin6() {
        skinSeleccionada = 6;
        System.out.println("Selecciono skin 6");
    }

    public static int getSkinSeleccionada() {
        return skinSeleccionada;
    }

}
