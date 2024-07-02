package controladores;

import Gui.Panta_PreConeccion;
import snake2.Front.Pantalla;

public class PreConeccion_Controlador extends Pantalla{

//Para poder usarlo en cualquier parte del codigo
    public static Panta_PreConeccion ventanaPrin = new Panta_PreConeccion();

    //Metodos
    public static void mostrar() { 
        inicializar(ventanaPrin);

    }


    public static void eventOnlineHost() {

        Controlador_MenuPrinc.setModoJuego(2);
        HostLobbie_Controlador.mostrar(); //Abrir la pantalla del Host Online
        ocultar(ventanaPrin);
    }

    public static void eventOnlineCliente() {
        
        
        ClienteLobbie_Controlador.mostrar(); //Abrir la pantalla del Host Online
        System.out.println("Se abrio el cliente");
        ocultar(ventanaPrin);
    }

    public static void eventVolverAlMenu() {
        Controlador_MenuPrinc.mostrar();
        ocultar(ventanaPrin);
    }

}
