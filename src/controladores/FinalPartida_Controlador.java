package controladores;

import snake2.Game;
import Gui.Panta_FinalPartida;
import snake2.Front.Pantalla;

public class FinalPartida_Controlador extends Pantalla{

//Para poder usarlo en cualquier parte del codigo
    public static Panta_FinalPartida ventanaPrin = new Panta_FinalPartida();

    //Metodos
    public static void mostrar() {
        inicializar(ventanaPrin);
    }


    public static void eventniciarJuego() {
        //Boton de reiniciar partida
        
        // Instancia del juego
        Game game = new Game(); 
        ocultar(ventanaPrin);
    }

    public static void eventVolverMenu() {
        //Boton de volver al menu principal
        Controlador_MenuPrinc.mostrar();        
        ocultar(ventanaPrin);
    }


}
