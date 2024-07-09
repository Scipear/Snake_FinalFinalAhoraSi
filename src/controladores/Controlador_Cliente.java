package controladores;

import Gui.Panta_Online_ClienteLobbie;
import snake2.AlertException;
import snake2.Contenedor_Paquetes.Paquete01Desconectar;
import snake2.Contenedor_Paquetes.Paquete02Play;
import snake2.Front.GraficoPersonaje;
import snake2.Front.Pantalla;

/**
 * Clase que se encarga de manipular la ventada del lobby de los usuarios que se unen a un servidor
 * 
 * @version 1.1.5
 */
public class Controlador_Cliente extends Pantalla{

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_Online_ClienteLobbie ventana = new Panta_Online_ClienteLobbie();
    public static GraficoPersonaje seleccion;
    public static int skinSeleccionada = -1;
    public static boolean isReady = false;

    /**
     * Accion que se ejecuta al presionar el boton de iniciar en la ventana, indica que el jugador
     * esta listo para inicar la partida.
     * 
     * @version 1.1.5
     */
    public static void eventIniciar(){
        try{
            if(skinSeleccionada == -1){
                throw new AlertException("Debes elegir una skin para poder iniciar");
            }
            Paquete02Play play = new Paquete02Play(Controlador_Login.nombreUsuario, Controlador_Cliente.skinSeleccionada);
            play.enviarData(Controlador_PreConeccion.cliente);
            Controlador_MenuPrincipal.pararMusica();
            ocultar(ventana);
        }catch(AlertException e){
            e.mostrarAlerta();
        }
    }
    /**
     * Accion que se ejecuta al presionar el boton de volver en la ventana, cierra la ventada actual
     * y muestra la anterior a esa
     * 
     * @version 1.1.5
     */
    public static void eventVolverAlPre(){
        skinSeleccionada = -1;
        Paquete01Desconectar desconectar = new Paquete01Desconectar(Controlador_Login.nombreUsuario);
        desconectar.enviarData(Controlador_PreConeccion.cliente);
        Controlador_PreConeccion.cliente.cerrarCliente();
        Controlador_PreConeccion.cliente = null;
        Controlador_PreConeccion.inicializar(Controlador_PreConeccion.ventana);
        ocultar(ventana);
    }

    public static void selec_Skin1(){
        skinSeleccionada = 1;
        System.out.println("Selecciono skin 1");
    }

    public static void selec_Skin2() {
        skinSeleccionada = 2;
        System.out.println("Selecciono skin 2");
    }

    public static void selec_Skin3() {
        skinSeleccionada = 3;
        System.out.println("Selecciono skin 3");
    }

    public static void selec_Skin4() {
        skinSeleccionada = 4;
        System.out.println("Selecciono skin 4");
    }

    public static void selec_Skin5() {
        skinSeleccionada = 5;
        System.out.println("Selecciono skin 5");
    }

    public static void selec_Skin6() {
        skinSeleccionada = 6;
        System.out.println("Selecciono skin 6");
    }

    public static int getSkinSeleccionada() {
        return skinSeleccionada;
    }

}
