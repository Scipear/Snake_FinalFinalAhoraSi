/*
Los controladores contienen la logica de los eventos de botones, mostrar, ocultal
desplazarce entre las distintas pantallas etc. Aqui se almacena la del Lobbie
 */
package controladores;

import javax.swing.JOptionPane;
import Gui.Panta_Lobbie;
import snake2.Game;

public class Lobbie_Controlador extends HostLobbie_Controlador{
    public static Panta_Lobbie ventanaLobbie = new Panta_Lobbie();
    //Metodos
    public static void mostrar() {
        inicializar(ventanaLobbie);
    }

    //Metodo encargado de iniciar la pantalla de juego
    public static void eventIniciar(){
        if(skinSeleccionada == -1 || mapaSeleccionado == -1){
            JOptionPane.showMessageDialog(null, "Por favor, elige una skin y un mapa para poder jugar");        
        }else{
            Controlador_MenuPrinc.pararMusica();
            Game game = new Game();
            game.iniciarSinglePlayer();
            ocultar(ventanaLobbie);
        }
    }

    public static void eventVolverAlMenu() {
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaLobbie);
    }
}
