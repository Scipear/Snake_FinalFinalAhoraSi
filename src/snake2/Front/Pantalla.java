package snake2.Front;

import javax.swing.JFrame;

/**
 * Clase parte del GUI. Esta clase padre es la que se encarga de mostrar las
 * ventanas del programa, de ella heredan los diferentes tipos de ventanas que
 * habra en el juego, como por ejemplo la ventada de inicio, la ventada del
 * lobby, la ventada de la partida
 *
 * @version 1.0
 *
 */
public abstract class Pantalla extends JFrame {

    protected static final int ancho = 700;
    protected static final int alto = 700;/* Tamanio fijo que tendra cada ventana, esto podria ajustarse
    en un futuro, en caso que en la resolucion de las pantallas del laboratorio o alguna computadora no
    entre completa o se vea muy pequeña, hay que ir haciendo pruebas*/
    
    /**
     * Iniicaliza los valores de la ventana y la muestra en pantalla
     * 
     * @version 1.0
     */
    public static void inicializar(JFrame ventana) {
        ventana.setSize(ancho, alto);
        ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);
        ventana.setVisible(true);
    }
    /**
     * Quita la visibilidad de una ventada
     * 
     * @param ventana Frame que dejara de ser visible
     * @version 1.1.4
     */
    public static void ocultar(JFrame ventana) {
        ventana.setVisible(false);
    }

}
