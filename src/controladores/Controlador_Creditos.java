

package controladores;

import Gui.Panta_Creditos;
import snake2.Front.Pantalla;

/**
 * Clase que se encarga de manipular la ventana de los creditos del juego
 * 
 * @version 1.1.4
 */
public class Controlador_Creditos extends Pantalla{
    
    public static Panta_Creditos ventana = new Panta_Creditos();

    /**
    * Accion que se ejecuta al presionar el boton de atras cuando se esta en la ventana de creditos,
    * esta muestra la ventana del menu principal
    * 
    * @version 1.1.4
    */
    public static void eventVolverMenu() {
        Controlador_MenuPrincipal.inicializar(Controlador_MenuPrincipal.ventana);
        ocultar(ventana);
    }
}
