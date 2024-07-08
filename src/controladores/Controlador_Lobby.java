/*
Los controladores contienen la logica de los eventos de botones, mostrar, ocultal
desplazarce entre las distintas pantallas etc. Aqui se almacena la del Lobbie
 */
package controladores;

import Gui.Panta_Lobbie;
import snake2.AlertException;
import snake2.Game;

/**
 * Clase que se encarga de manipular la ventana del lobby para los usuarios que han decidido
 * jugar en solitario
 * 
 * @version 1.1.4
 */
public class Controlador_Lobby extends Controlador_Host{

    public static Panta_Lobbie ventana = new Panta_Lobbie();

    /**
     * Accion que se ejecuta al presionar el boton iniciar de la ventana, esta hace que inice el juego
     * 
     * @version 1.1.4
     */
    public static void eventIniciar(){
        try{
            if(skinSeleccionada == -1 || mapaSeleccionado == -1){
                throw new AlertException("Por favor, elige una skin y un mapa para poder jugar");        
            }
            Controlador_MenuPrincipal.pararMusica();
            Game game = new Game();
            game.iniciarSinglePlayer();
            ocultar(ventana);
        }catch(AlertException e){
            e.mostrarAlerta();
        }
    }

    /**
     * Accion que se ejecuta al presionar el boton atras que se muestra en la ventana, esta muestra el
     * menu principal
     * 
     * @version 1.1.4
     */
    public static void eventVolverAlMenu(){
        Controlador_MenuPrincipal.inicializar(Controlador_MenuPrincipal.ventana);
        ocultar(ventana);
    }
}
