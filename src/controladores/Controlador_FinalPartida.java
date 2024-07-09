package controladores;

import snake2.Game;
import snake2.Contenedor_Paquetes.Paquete02Play;
import snake2.Contenedor_Paquetes.Paquete12Window;
import Gui.Panta_FinalPartida;
import snake2.Front.Pantalla;

/**
 * Clase que se encarga de manipular la ventana que se muestra al finalizar una partida
 * 
 * @version 1.1.5
 */
public class Controlador_FinalPartida extends Pantalla{

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_FinalPartida ventana = new Panta_FinalPartida();

    /**
     * Accion que se ejecuta al presionar el boton de volver a jugar en dicha ventana, esta reinicia
     * la partida
     * 
     * @version 1.1.5
     */
    public static void eventniciarJuego(){
        if(Controlador_Host.modo == 2){
            Paquete02Play play = new Paquete02Play(Controlador_Login.nombreUsuario, Controlador_Host.skinSeleccionada);
            play.enviarData(Controlador_PreConeccion.cliente);

        }else if(Controlador_Cliente.modo == 3){
            Paquete02Play play = new Paquete02Play(Controlador_Login.nombreUsuario, Controlador_Cliente.skinSeleccionada);
            play.enviarData(Controlador_PreConeccion.cliente);

        }else{
            Game game = new Game();
            game.iniciarSinglePlayer();
        }
        ventana.dispose();
    }

    /**
     * Accion que se ejecuta al presionar el boton de volver al menu principal en dicha ventada,
     * esta devuelve al usuario a su respectivo lobby
     * 
     * @version 1.1.5
     */
    public static void eventVolverMenu() {
        if(Controlador_Host.modo == 2){
            Controlador_Host.inicializar(Controlador_Host.ventana);
            Controlador_Host.mapaSeleccionado = -1;
            Controlador_Host.skinSeleccionada = -1;
            Paquete12Window ventana = new Paquete12Window(2);
            ventana.enviarData(Controlador_PreConeccion.cliente);

        }else if(Controlador_Cliente.modo == 3){
            Controlador_Cliente.inicializar(Controlador_Cliente.ventana);
            Controlador_Cliente.skinSeleccionada = -1;
            Paquete12Window ventana = new Paquete12Window(2);
            ventana.enviarData(Controlador_PreConeccion.cliente);

        }else{
            Controlador_Lobby.inicializar(Controlador_Lobby.ventana);
            Controlador_Lobby.mapaSeleccionado = -1;
            Controlador_Lobby.skinSeleccionada = -1;
        }      
        ventana.dispose();
    }


}
