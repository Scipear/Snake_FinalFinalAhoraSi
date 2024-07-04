/*
Los controladores contienen la logica de los eventos de botones, mostrar, ocultal
desplazarce entre las distintas pantallas etc. Aqui se almacena la del menu principal
 */
package controladores;

import Gui.*;
import ost.ReproductorSonidos;
import snake2.Front.Pantalla;

public class Controlador_MenuPrinc extends Pantalla {

//Para poder usarlo en cualquier parte del codigo
    public static Principal_menu ventanaPrin = new Principal_menu();
    private static final ReproductorSonidos fondoOst = new ReproductorSonidos();
    private static final String menuOstRuta = "\\src\\ost\\Fantasia Ritmica.mp3";
    private static int modoJuego;

    public static int getModoJuego() {
        return modoJuego;
    }

    public static void setModoJuego(int modoJuego) {
        Controlador_MenuPrinc.modoJuego = modoJuego;
        System.out.println("Modo juego");
    }

    //Metodos
    public static void mostrar() {

        inicializar(ventanaPrin);
        fondoOst.musicaDeFondo(menuOstRuta);

    }

    public static void pararMusica() {
        fondoOst.detener(); //Metodo para parar la musica para reemplazarla con otra
    }

    public static void eventLobbie() {

        modoJuego = 1;
        Lobbie_Controlador.mostrar();   //Aqui abre el lobby para jugar single player
        ocultar(ventanaPrin);
    }

    public static void eventComoJugar() {

        ComoJugar_Controlador.mostrar();    //Aqui abre la Pantalla de controles y reglas
        ocultar(ventanaPrin);
    }

    public static void eventCreditos() {

        Creditos_Controlador.mostrar();     //Aqui abre la pantalla de creditos
        // Controlador_MenuPrinc.ocultar();
        ocultar(ventanaPrin);
    }

    public static void eventConectarse() {

        PreConeccion_Controlador.mostrar(); //Aqui abre la pantalla del multijugador
        ocultar(ventanaPrin);
    }

}
