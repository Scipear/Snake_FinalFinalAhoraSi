package controladores;

import Gui.Panta_Online_HostLobbie;
import snake2.Contenedor_Paquetes.Paquete01Desconectar;
import snake2.Contenedor_Paquetes.Paquete02Play;
import snake2.AlertException;

/**
 * Clase que se encarga de manipular la ventana del lobby para los usuarios que han creado un
 * servidor
 * 
 * @version 1.1.5
 */
public class Controlador_Host extends Controlador_Cliente{

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_Online_HostLobbie ventana = new Panta_Online_HostLobbie();
    public static int mapaSeleccionado = -1;

    /**
     * Accion que se ejecuta al presionar el boton iniciar de la ventana, esta inicia el juego para 
     * todos los clientes conectados en el servidor 
     * 
     * @version 1.1.5
     */
    public static void eventIniciar() {
        try{
            if(skinSeleccionada == -1 || mapaSeleccionado == -1){        
                throw new AlertException("Por favor, elige una skin y un mapa para poder jugar");
            }
            if(Controlador_PreConeccion.server.getJugadoresListos() != Controlador_PreConeccion.server.getJugadoresActivos()-1){
                throw new AlertException("Debe esperar a que todos los jugadores esten listos para jugar");
            }
            Paquete02Play play = new Paquete02Play(Controlador_Login.nombreUsuario, skinSeleccionada);
            play.enviarData(Controlador_PreConeccion.cliente);
            Controlador_MenuPrincipal.pararMusica();
            ocultar(ventana);
        }catch(AlertException e){
            e.mostrarAlerta();
        }
    }


    /**
     * Accion que se ejecuta al presionar el boton atras que se muestra en la ventana
     * 
     * @version 1.1.5
     */
    public static void eventVolverAlPre(){
        Paquete01Desconectar desconectar = new Paquete01Desconectar(Controlador_Login.nombreUsuario);
        desconectar.enviarData(Controlador_PreConeccion.cliente);
        Controlador_PreConeccion.cliente.cerrarCliente();
        Controlador_PreConeccion.inicializar(Controlador_PreConeccion.ventana);
        ocultar(ventana);
    }

    public static void selec_Mapa1() {
        mapaSeleccionado = 0;
        System.out.println("Selecciono mapa 1");
    }

    public static void selec_Mapa2() {
        mapaSeleccionado = 1;
        System.out.println("Selecciono mapa 2");
    }

    public static void selec_Mapa3() {
        mapaSeleccionado = 2;
        System.out.println("Selecciono mapa 3");
    }

    public static void selec_Mapa4() {
        mapaSeleccionado = 3;
        System.out.println("Selecciono mapa 3");
    }

    public static int getMapaSeleccionada() {
        return mapaSeleccionado;
    }

}
