/*
Los controladores contienen la logica de los eventos de botones, mostrar, ocultal
desplazarce entre las distintas pantallas etc. Aqui se almacena la del Lobbie
 */
package controladores;

import Gui.Panta_Lobbie;
import snake2.Game;
import snake2.Front.GraficoPersonaje;
import snake2.Front.Pantalla;

public class Lobbie_Controlador extends Pantalla {

//Para poder usarlo en cualquier parte del codigo
    public static Panta_Lobbie ventanaPrin = new Panta_Lobbie();
    public static GraficoPersonaje seleccion;
    public static int skinSeleccionada;
    public static int mapaSeleccionado;

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
    }

    //Metodo encargado de iniciar la pantalla de juego
    public static void eventIniciar() {
        Controlador_MenuPrinc.pararMusica();
        Game game = new Game();
        ocultar(ventanaPrin);
    }

    public static void eventVolverAlMenu() {
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaPrin);
    }

    public static void selec_Skin1() {
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

    public static void selec_Mapa1() {
        mapaSeleccionado = 1;
        System.out.println("Selecciono mapa 1");
    }

    public static void selec_Mapa2() {
        mapaSeleccionado = 2;
        System.out.println("Selecciono mapa 2");
    }

    public static void selec_Mapa3() {
        mapaSeleccionado = 3;
        System.out.println("Selecciono mapa 3");
    }

    public static int getMapaSeleccionada() {
        return mapaSeleccionado;
    }
}
