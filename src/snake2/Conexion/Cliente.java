package snake2.Conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import controladores.Controlador_Cliente;
import controladores.Controlador_FinalPartida;
import controladores.Controlador_Host;
import controladores.Controlador_Login;
import snake2.AlertException;
import snake2.Comida;
import snake2.Jugador;
import snake2.Tablero;
import snake2.Chat.ChatCliente;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete.TiposPaquetes;
import snake2.Contenedor_Paquetes.Paquete00Login;
import snake2.Contenedor_Paquetes.Paquete01Desconectar;
import snake2.Contenedor_Paquetes.Paquete02Play;
import snake2.Contenedor_Paquetes.Paquete03Show;
import snake2.Contenedor_Paquetes.Paquete04Player;
import snake2.Contenedor_Paquetes.Paquete05Update;
import snake2.Contenedor_Paquetes.Paquete06Comida;
import snake2.Contenedor_Paquetes.Paquete10Effect;
import snake2.Contenedor_Paquetes.Paquete11Collision;
import snake2.Contenedor_Paquetes.Paquete12Window;
import snake2.Front.PantallaJuego;

/**
 * Clase que representa a un cliente que intercambia datos (Paquetes) con un servidor
 * 
 * @version 1.2
 */
public class Cliente implements Runnable{
    private static int cantidadJugadores = 0; // Cantidad de jugadores que estaran en la partida
    private int numCliente = -1; // Representa si es el primer, segundo, etc cliente en conectarse al servidor
    private boolean jugadoresListos = false; // Si todos los jugadores estan listos para iniciar la partida
    private boolean estaConectado = false; // Si el cliente esta conectado al servidor
    private int puerto; // Puerto del servidor para el intercambio de datos
    private ChatCliente chatCliente;
    private InetAddress direccionIP; // Direccion IP del servidor
    private DatagramSocket socket;
    private byte[] datos; // Forma en la que los datos se van a intercambiar entre cliente y servidor
    private Thread thread;
    private Comida comidaAux; // Comida auxiliar para  hacer que aparezca la primera fruta en la pantalla de los clientes
    private ArrayList<Jugador> jugadores = new ArrayList<>(); // Lista de los jugadores que estaran en la partida
    private PantallaJuego pantalla; // Pantalla que mostrará la partida
    private Tablero tablero; // Tablero en el que ocurrirá la partida

    /**
     * Constructor de la clase
     * 
     * @param direccionIP La direccion del servidor
     */
    public Cliente(String direccionIP){
        this.puerto = Server.getPuerto();
        try {
            this.direccionIP = InetAddress.getByName(direccionIP);
            socket = new DatagramSocket();
            chatCliente = new ChatCliente(Controlador_Login.nombreUsuario);
            chatCliente.setVisible(true);
            iniciarCliente();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicia el hilo de la clase, lo que hace que se ejecute el metodo run automaticamente
     * 
     * @version 1.2
     */
    public synchronized void iniciarCliente() {
        thread = new Thread(this);
        thread.start();
        estaConectado = true;
    }

    /**
     * Cierra el hilo de la clase, funciona para cerrar el socket del cliente
     * 
     * @version 1.2 
     */
    public synchronized void cerrarCliente(){
        try {
            estaConectado = false;
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo implementado que proviene de Runnable
     * 
     * Ejecutara ciertas acciones al mismo tiempo que otras en el programa,
     * esto gracias al uso de los hilos
     * 
     * @version 1.2
     * 
     */
    @Override
    public void run(){
        Paquete00Login conexion = new Paquete00Login(Controlador_Login.nombreUsuario, 0);
        conexion.enviarData(this); // Avisa al servidor que esta conectandose a este
        
        while(estaConectado){
            recibirPaquete();
        }
        chatCliente.setVisible(false);
        chatCliente = null;
        socket.close();
    }

    /**
     * Recibe un paquete enviado por el servidor
     * 
     * @version 1.2
     */
    public void recibirPaquete(){
        datos = new byte[1024];
        DatagramPacket paquete = new DatagramPacket(datos, datos.length);
        try {
            socket.receive(paquete);
            analizarPaquete(datos, paquete.getAddress(), paquete.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Le envia un paquete al servidor
     * 
     * @param datos Lo que se enviara al servidor
     */
    public void enviarPaquete(byte[] datos){
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionIP, puerto);
        try {
            socket.send(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Determina que tipo de paquete fue recibido, dependiendo del tipo de paquete se hara una accion distinta
     * 
     * @param datos Lo que contiene el paquete
     * @param direccionIP IP del paquete
     * @param puerto Puerto del paquete
     * @version 1.2
     */
    private void analizarPaquete(byte[] datos, InetAddress direccionIP, int puerto){
        String message = new String(datos).trim();
        TiposPaquetes type = Paquete.buscarPaquete(message.substring(0, 2));
        switch(type){
        default:
        case INVALID:
            break;
        case LOGIN:
            try{
                Paquete00Login conexion = new Paquete00Login(datos);
                if(conexion.getNumCliente() == Server.getMAX_JUGADORES()-1){
                    throw new AlertException("No se puede ingresar al servidor debido a que se encuentra lleno");
                } // Si el servidor se encuentra lleno desconecta al cliente del servidor

                if(numCliente == -1){
                    numCliente = conexion.getNumCliente();
                } // Toma el numero de conexion del cliente

            }catch(AlertException e){
                e.mostrarAlerta();
                Controlador_Cliente.eventVolverAlPre();
            }
            break;

        case DISCONNECT:
            Paquete01Desconectar desconexion = new Paquete01Desconectar(datos);
            System.out.println("[" + direccionIP.getHostAddress() + ":" + puerto + "] "
                    + desconexion.getUsername() + " has left...");
            break;

        case PLAY:
            Paquete02Play jugar = new Paquete02Play(datos);
            System.out.println("El jugador " + jugar.getUsuario() + " ya esta listo para jugar");
            break;

        case SHOW:
            Paquete03Show mostrar = new Paquete03Show(datos);
            if((cantidadJugadores == mostrar.getCantidad()) && !jugadoresListos){
                Controlador_Cliente.ventana.dispose();
                jugadoresListos = true;
                tablero = new Tablero(jugadores, mostrar.getMapa());
                tablero.actualizarComida(comidaAux.getPosX(), comidaAux.getPosY(), 4);
                pantalla = new PantallaJuego(tablero, jugadores, numCliente);
                pantalla.setVisible(true);
            } /*Inicia el juego para los jugadores creando el tablero y 
                la ventana que estara en constante actualizacion con los cambios 
                que haya en el servidor*/
            break;

        case PLAYER:
            Paquete04Player guardar = new Paquete04Player(datos);
            guardarJugador(guardar);
            break;
        
        case UPDATE:
            Paquete05Update actualizar = new Paquete05Update(datos);
            actualizarEntidades(actualizar);
            break;
        
        case FOOD:
            Paquete06Comida comida = new Paquete06Comida(datos);
            actualizarEntidades(comida);
            break;

        case EFFECT:
            Paquete10Effect efecto = new Paquete10Effect(datos);
            actualizarEntidades(efecto);
            break;

        case COLLISION:
            Paquete11Collision colision = new Paquete11Collision(datos);
            actualizarTablero(colision);
            break;

        case WINDOW:
            Paquete12Window ventana = new Paquete12Window(datos);
            gestionaVentanas(ventana);
            break;
        }

    }

    /**
     * Tomas los datos de un jugador enviados por el servidor y los guarda en la lista
     * @param paquete Datos de un jugador
     * @version 1.2
     */
    public void guardarJugador(Paquete04Player paquete){
        Jugador jugador = new Jugador(paquete.getUsuario(), paquete.getNumero(), paquete.getX(), paquete.getY(), paquete.getDireccion(), paquete.getSkin());
        jugadores.add(jugador);
        cantidadJugadores++;
        System.out.println("Nuevo tamanio" + jugadores.size());
    }

    /**
     * Actualiza estados que estan sobre el tablero como posiciones de jugadores o comida
     * 
     * @param packet Datos para la actualizacion
     * @version 1.2
     */
    public void actualizarEntidades(Paquete packet){
        if(packet instanceof Paquete05Update){
            Paquete05Update paquete = (Paquete05Update) packet;

            if(!jugadores.get(paquete.getIndiceP()).getPersonaje().getCuerpo(paquete.getIndiceC()).getTipo().equals(paquete.getTipo())){
                jugadores.get(paquete.getIndiceP()).getPersonaje().getCuerpo(paquete.getIndiceC()).setTipo(paquete.getTipo());
            }
    
            if(!jugadores.get(paquete.getIndiceP()).getPersonaje().getCuerpo(paquete.getIndiceC()).getDireccion().equals(paquete.getDireccion())){
                jugadores.get(paquete.getIndiceP()).getPersonaje().getCuerpo(paquete.getIndiceC()).setDireccion(paquete.getDireccion());
            }
    
            jugadores.get(paquete.getIndiceP()).getPersonaje().getCuerpo(paquete.getIndiceC()).setPosX(paquete.getX());
            jugadores.get(paquete.getIndiceP()).getPersonaje().getCuerpo(paquete.getIndiceC()).setPosY(paquete.getY());
            
        }else if(packet instanceof Paquete06Comida){
            Paquete06Comida paquete = (Paquete06Comida) packet;
            comidaAux = new Comida(paquete.getX(), paquete.getY());
            if(tablero != null){
                tablero.actualizarComida(paquete.getX(), paquete.getY(), paquete.getTipo());
            }

        }else if(packet instanceof Paquete10Effect){
            Paquete10Effect paquete = (Paquete10Effect) packet;
            tablero.hacer_Efectos(paquete.getX(), paquete.getY(), paquete.getTipo(), paquete.getIndiceP());

        }
        if(pantalla != null){
            pantalla.actualizaMapa();
        }
    }
    
    /**
     * Actualiza el estado del tablero
     * 
     * @param paquete Datos para la actualzacion
     * @version 1.2.2
     */
    public void actualizarTablero(Paquete11Collision paquete){
        switch(paquete.getEstado()){
            case 0:
                jugadores.get(paquete.getIndice()).getPersonaje().setEstado(false);
                break;

            case 1:
                tablero.setPausa(false);
                pantalla.muestraPausa(tablero);
                break;

            case 2:
                tablero.setPausa(true);
                pantalla.muestraPausa(tablero);
                break;
            
            case 3:
                jugadores.get(paquete.getIndice()).aumentaPuntaje();
                pantalla.actualizaPuntaje(jugadores);
                break;
        }
    }

    public void gestionaVentanas(Paquete12Window paquete){
        switch(paquete.getTipo()){
            case 1:
                pantalla.detenerMusica();
                pantalla.dispose();
                jugadoresListos = false;
                cantidadJugadores = 0;
                jugadores.clear();
                System.out.println("Tamanio de la lista " + jugadores.size());
                Controlador_FinalPartida.inicializar(Controlador_FinalPartida.ventana);
                break;
            
            case 2:
                if(Controlador_Host.modo == 2){
                Controlador_Host.inicializar(Controlador_Host.ventana);
                Controlador_Host.mapaSeleccionado = -1;
                Controlador_Host.skinSeleccionada = -1;

                }else if(Controlador_Cliente.modo == 3){
                Controlador_Cliente.inicializar(Controlador_Cliente.ventana);
                Controlador_Cliente.skinSeleccionada = -1;

                }
                Controlador_FinalPartida.ventana.dispose();
                break;
        }
    }

    public int getIndiceJugador(String usuario){
        int indice = 0;
        for(Jugador j : jugadores){
            String user = j.getUsuario();
            if(user.equals(usuario)){
                break;
            }
            indice++;
        }
        return indice;
    }
}
