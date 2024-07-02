package controladores;

import Gui.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import snake2.Front.Pantalla;
import snake2.Snake2;

public class ComoJugar_Controlador extends Pantalla{

//Para poder usarlo en cualquier parte del codigo
    private static final int ancho = 700; // Hacerlo heredar de la clase pantalla
    private static final int alto = 700;  // despues xd
    public static Panta_ComoJugar ventanaPrin = new Panta_ComoJugar();

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
    }


    public static void eventVolverMenu() {
            //Boton de volver al menu principal
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaPrin);
    }
}
