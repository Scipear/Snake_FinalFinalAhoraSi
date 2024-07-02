package controladores;

import ost.ReproductorSonidos;
import snake2.*;

public class Game_Controlador extends Game {

//Para poder usarlo en cualquier parte del codigo

    private static final ReproductorSonidos fondoOst = new ReproductorSonidos();
    private static final String gameOstRuta = "C:\\Users\\PC1\\Desktop\\UNIVERSIDAD\\IV Semestre\\Programación III\\Mine\\pantallas_Snake\\src\\ost\\ARRR.wav";


    //Metodos
    public static void nuevoJuego() {
        Game game = new Game();
        game.iniciarPartida();
        fondoOst.musicaDeFondo(gameOstRuta);
    }

    public static void ocultar() {
        if (gameOver == true) {
            
            FinalPartida_Controlador.mostrar();
            
        }
    }
}
