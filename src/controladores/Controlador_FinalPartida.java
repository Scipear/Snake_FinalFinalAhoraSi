package controladores;

import snake2.Game;
import Gui.Panta_FinalPartida;
import snake2.Front.Pantalla;

/**
 * Clase que se encarga de manipular la ventana que se muestra al finalizar una partida
 * 
 * @version 1.1.5
 */
public class Controlador_FinalPartida extends Pantalla{

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_FinalPartida ventana = new Panta_FinalPartida();

    /**
     * Accion que se ejecuta al presionar el boton de volver a jugar en dicha ventana, esta reinicia
     * la partida
     * 
     * @version 1.1.5
     */
    public static void eventniciarJuego() {
        Game game = new Game(); 
        ocultar(ventana);
    }

    /**
     * Accion que se ejecuta al presionar el boton de volver al menu principal en dicha ventada,
     * esta devuelve al usuario al menu principal
     * 
     * @version 1.1.5
     */
    public static void eventVolverMenu() {
        //Boton de volver al menu principal
        Controlador_MenuPrincipal.inicializar(Controlador_MenuPrincipal.ventana);        
        ocultar(ventana);
    }


}
