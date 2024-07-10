/*
Los controladores contienen la logica de los eventos de botones, mostrar, ocultal
desplazarce entre las distintas pantallas etc. Aqui se almacena la del menu principal
 */
package controladores;

import Gui.*;
import ost.ReproductorSonidos;
import snake2.AlertException;
import snake2.Estadisticas;
import snake2.Front.Pantalla;

/**
 * Clase que se encarga de manipular la ventana del menu principal
 * 
 * @version 1.1.4
 */
public class Controlador_MenuPrincipal extends Pantalla {

    //Para poder usarlo en cualquier parte del codigo
    public static Principal_menu ventana = new Principal_menu();
    private static final ReproductorSonidos fondoOst = new ReproductorSonidos();
    private static final String menuOstRuta = "\\src\\ost\\Fantasia Ritmica.mp3";
    private static Estadisticas estadisticas = new Estadisticas(Controlador_Login.nombreUsuario);

    //Metodos
    // public static void mostrar() {
    //     inicializar(ventana);
    //     fondoOst.musicaDeFondo(menuOstRuta);
    // }

    /**
     * Inicia la reproduccion de la musica que se escucha al moverse por las distintas ventanas
     * 
     * @version 1.1.6
     */
    public static void iniciarMusica(){
        fondoOst.musicaDeFondo(menuOstRuta);
    }

    /**
     * Metodo que detiene la musica para reemplazarla con otra
     * 
     * @version 1.1.6
     */
    public static void pararMusica() {
        fondoOst.detener(); //
    }

    /**
     * Accion que se ejecuta al presionar el boton de Jugar en la ventana, esta muestra la ventana
     * del lobby para el modo de un solo jugador
     * 
     * @version 1.1.4
     */
    public static void eventLobbie() {
        Controlador_Lobby.inicializar(Controlador_Lobby.ventana);   //Aqui abre el lobby para jugar single player
        ventana.dispose();
    }

    /**
     * Accion que se ejecuta al presionar el boton de Como Jugar en la ventana, esta abre la ventana
     * que muestra las indicaciones sobre como jugar
     * 
     * @version 1.1.4
     */
    public static void eventComoJugar() {
        Controlador_ComoJugar.inicializar(Controlador_ComoJugar.ventanaPrincipal);    //Aqui abre la Pantalla de controles y reglas
        ventana.dispose();
    }

    /**
     * Accion que se ejecuta al presionar el boton de Creditos en la ventana, esta muestra la ventana
     * que ensenia los creditos del juego
     * 
     * @version 1.1.4
     */
    public static void eventCreditos() {
        Controlador_Creditos.inicializar(Controlador_Creditos.ventana);     //Aqui abre la pantalla de creditos
        ventana.dispose();
    }

    /**
     * Accion que se ejecuta al presionar el boton de Conectarse en la ventana, esta muestra la ventana
     * en donde el usuario puede elegir si crear un servidor o conectarse a uno
     * 
     * @version 1.1.5
     */
    public static void eventConectarse() {
        Controlador_PreConeccion.inicializar(Controlador_PreConeccion.ventana); //Aqui abre la pantalla del multijugador
        ventana.dispose();
    }

    public static void eventPuntajes(){
        try{
            estadisticas.mostrarPuntajes();
        }catch(AlertException e){
            e.mostrarAlerta();
        }
    }

}
