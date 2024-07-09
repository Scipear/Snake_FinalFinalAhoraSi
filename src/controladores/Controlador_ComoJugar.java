package controladores;

import Gui.*;
import snake2.Front.Pantalla;

/**
 * Clase que se encarga de manipular la ventana que muestra la guia
 * sobre como jugar a los usuarios
 * 
 * @version 1.1.4
 */
public class Controlador_ComoJugar extends Pantalla {

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_ComoJugar ventanaPrincipal = new Panta_ComoJugar();
    public static Panta_ComoJugar1 ventanaSecundaria = new Panta_ComoJugar1();
     /**
     * Accion que se ejecuta al presionar el boton de volver en la ventana, cierra la ventada actual
     * y muestra la anterior a esa
     * 
     * @version 1.1.4
     */
    public static void eventVolverMenu(){
        Controlador_MenuPrincipal.inicializar(Controlador_MenuPrincipal.ventana);
        ventanaPrincipal.dispose();
    }
    /**
     * Accion que se ejecuta al presionar el boton de atras cuando se esta en la segunda
     * pagina de la seccion Como Jugar 
     * 
     * @version 1.1.6
     */
    public static void eventMostrarRegla1() {
        inicializar(ventanaPrincipal);
        ventanaSecundaria.dispose();
    }

    /**
     * Accion que se ejecuta al presionar el boton de siguiente cuando se esta en la primera
     * pagina de la seccion Como Jugar 
     * 
     * @version 1.1.6
     */
    public static void eventMostrarRegla2() {
        inicializar(ventanaSecundaria);
        ventanaPrincipal.dispose();
    }
}
