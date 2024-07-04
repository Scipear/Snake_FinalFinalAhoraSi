package controladores;

import Gui.*;
import snake2.Front.Pantalla;

public class ComoJugar_Controlador extends Pantalla {

//Para poder usarlo en cualquier parte del codigo
    public static Panta_ComoJugar ventanaPrin = new Panta_ComoJugar();
    public static Panta_ComoJugar1 ventanaSec = new Panta_ComoJugar1();

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
    }

    public static void eventVolverMenu() {
        //Boton de volver al menu principal
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaPrin);
        ocultar(ventanaSec);
    }

    public static void eventMostrarRegla1() {

        ComoJugar_Controlador.mostrar();
        ComoJugar_Controlador.ocultar(ventanaSec);

    }

    public static void eventMostrarRegla2() {
        inicializar(ventanaSec);
        ComoJugar_Controlador.ocultar(ventanaPrin);
        
    }
}
