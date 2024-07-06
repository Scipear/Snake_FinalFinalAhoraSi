package controladores;

import javax.swing.JOptionPane;

import Gui.Panta_PreConeccion;
import snake2.Conexion.Cliente;
import snake2.Conexion.Server;
import snake2.Front.Pantalla;

public class PreConeccion_Controlador extends Pantalla{

    //Para poder usarlo en cualquier parte del codigo
    public static Panta_PreConeccion ventanaPrin = new Panta_PreConeccion();
    public static Server server;
    public static Cliente cliente;

    //Metodos
    public static void mostrar() { 
        inicializar(ventanaPrin);
    }

    public static void eventOnlineHost() {
        server = new Server();
        System.out.println(server.getDireccionIP());
        Controlador_MenuPrinc.setModoJuego(2);
        HostLobbie_Controlador.mostrar(); //Abrir la pantalla del Host Online
        ocultar(ventanaPrin);
    }

    public static void eventOnlineCliente(){
        String direccionIP = JOptionPane.showInputDialog("Ingrese la direccion IP de la partida que desea ingresar");  
        cliente = new Cliente(direccionIP);
        cliente.enviarPaquete("Hola mundoooooo soy cliente".getBytes());
        ClienteLobbie_Controlador.mostrar(); //Abrir la pantalla del Host Online
        System.out.println("Se abrio el cliente");
        ocultar(ventanaPrin);
    }

    public static void eventVolverAlMenu() {
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaPrin);
    }

}
