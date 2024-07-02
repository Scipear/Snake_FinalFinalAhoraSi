package controladores;

import Gui.*;
import snake2.Front.Pantalla;

public class ComoJugar_Controlador extends Pantalla{

//Para poder usarlo en cualquier parte del codigo
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
