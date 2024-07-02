

package controladores;

import Gui.Panta_Creditos;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import snake2.Front.Pantalla;


public class Creditos_Controlador extends Pantalla{
    
    public static Panta_Creditos ventanaPrin = new Panta_Creditos();

    //Metodos
    public static void mostrar() {
        
        inicializar(ventanaPrin);
    }


    public static void eventVolverMenu() {

        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaPrin);
    }
}
