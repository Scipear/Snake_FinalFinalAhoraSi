package controladores;

import javax.swing.JOptionPane;

import Gui.Panta_PreConeccion;
import snake2.AlertException;
import snake2.Conexion.Cliente;
import snake2.Conexion.Server;
import snake2.Front.Pantalla;


/**
 * Clase que se encarga de manipular la ventana en donde el usuario elige que manera online jugar
 * 
 * @version 1.1.5
 */
public class Controlador_PreConeccion extends Pantalla{

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_PreConeccion ventana = new Panta_PreConeccion();
    public static Server server;
    public static Cliente cliente;

    /**
     * Accion que se ejecuta al presionar el boton Crear en la ventana, esta crea un nuevo servidor
     * en el que ocurrira la partida
     * 
     * @version 1.1.5
     */
    public static void eventOnlineHost(){
        server = new Server();
        System.out.println(server.getDireccionIP());
        cliente = new Cliente(server.getDireccionIP());
        Controlador_Host.inicializar(Controlador_Host.ventana); //Abrir la pantalla del Host Online
        ventana.dispose();
    }


     /**
     * Accion que se ejecuta al presionar el boton Conectarse en la ventana, esta crea un nuevo cliente
     * que pueda conectarse con el servidor ingresado
     * 
     * @version 1.1.5
     */
    public static void eventOnlineCliente(){
        String direccionIP;
        try{
            direccionIP = JOptionPane.showInputDialog("Ingrese la direccion IP de la partida a la que desea unirse");  
            if(direccionIP.isEmpty()){
                throw new AlertException("Direccion IP invalida");
            }  
            cliente = new Cliente(direccionIP);
            Controlador_Cliente.inicializar(Controlador_Cliente.ventana); //Abrir la pantalla del Host Online
            System.out.println("Se abrio el cliente");
            ventana.dispose();
        }catch(AlertException e){
            e.mostrarAlerta();
        }

    }


    /**
     * Accion que se ejecuta al presionar el boton atras en la ventana, lleva al menu principal
     * 
     * @version 1.1.5
     */
    public static void eventVolverAlMenu() {
        Controlador_MenuPrincipal.inicializar(Controlador_MenuPrincipal.ventana);
        ventana.dispose();
    }

}
