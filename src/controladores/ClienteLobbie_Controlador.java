package controladores;

import Gui.Panta_Online_ClienteLobbie;
import snake2.Game;
import snake2.Front.GraficoPersonaje;
import snake2.Front.Pantalla;

public class ClienteLobbie_Controlador extends Pantalla{

//Para poder usarlo en cualquier parte del codigo
    public static Panta_Online_ClienteLobbie ventanaPrin = new Panta_Online_ClienteLobbie();
    public static GraficoPersonaje seleccion;
    public static int skinSeleccionada;

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
    }

    //Metodo encargado de iniciar la pantalla de juego
    public static void eventIniciar() {

        Controlador_MenuPrinc.setModoJuego(3);
        Controlador_MenuPrinc.pararMusica();
        Game game = new Game();
        ocultar(ventanaPrin);
        System.out.println("Pantalla del lobbie del cliente");
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

}
