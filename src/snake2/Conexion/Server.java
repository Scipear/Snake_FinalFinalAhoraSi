package snake2.Conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import snake2.Game;
import snake2.JugadorMP;
import snake2.Chat.ChatServer;
import snake2.Jugador;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete.TiposPaquetes;
import snake2.Contenedor_Paquetes.Paquete00Login;
import snake2.Contenedor_Paquetes.Paquete01Desconectar;
import snake2.Contenedor_Paquetes.Paquete02Play;
import snake2.Contenedor_Paquetes.Paquete03Show;
import snake2.Contenedor_Paquetes.Paquete04Player;
import snake2.Contenedor_Paquetes.Paquete07Move;
import snake2.Contenedor_Paquetes.Paquete11Collision;
import snake2.Contenedor_Paquetes.Paquete12Window;

/**
 * Clase que representa al servidor que contendra la informacion principal de la partida y se mantendra
 * en constante comunicacion con los clientes
 * 
 * @version 1.2
 */
public class Server implements Runnable{
    private static final int MAX_JUGADORES = 4; // Maximo de clientes que pueden conectarse al servidor
    private static final int puerto = 9999; // Puerto del servidor
    private boolean juegoIniciado = false; // Si el juego ya ha comenzado
    private boolean servidorActivo = false; // Si el servidor se encuentra activo
    private byte[] datos; // Forma en la que los datos se van a intercambiar entre cliente y servidor
    private ChatServer chatServer;
    private DatagramSocket socket;
    private Thread thread;
    private ArrayList<JugadorMP> jugadoresActivos = new ArrayList<>(); // Arreglo que guarda los jugadores que se van conectando
    private int jugadoresListos; // Cantidad de jugadores que ya estan listos para iniciar la partida
    private Game game; // La partida que va a ser compartida con los clientes
    private int mapa; // Mapa elegido por el host
 
    /**
     * Constructor de la clase
     * 
     */
    public Server(){
        try {
            jugadoresListos = 0;
            socket = new DatagramSocket(puerto);
            chatServer = new ChatServer();
            iniciarServidor();        
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicia el hilo de la clase, lo que hace que se ejecute el metodo run automaticamente
     * 
     * @version 1.2
     */
    public synchronized void iniciarServidor() {
        thread = new Thread(this);
        thread.start();
        servidorActivo = true;
    }

    /**
     * Cierra el hilo de la clase, funciona para cerrar el socket del servidor y asi detenga su ejecucion
     * 
     * @version 1.2 
     */
    public synchronized void cerrarServidor(){
        try {
            chatServer.dispose();
            chatServer = null;
            servidorActivo = false;
            socket.close();
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo implementado que viene de Runnable
     * 
     * Ejecutara ciertas acciones al mismo tiempo que otras en el programa,
     * esto gracias al uso de los hilos
     * 
     * @version 1.2
     * 
     */
    @Override
    public void run(){
        while (servidorActivo){
            recibirPaquete();
            if((jugadoresActivos.size() == jugadoresListos) && !juegoIniciado){
                game = new Game();
                game.iniciarMultiplayer(jugadoresActivos);
                mapa = game.getMapa();
                juegoIniciado = true;
                // game.getPantalla().setVisible(true);
                enviarJugadores();
                Paquete03Show mostrar = new Paquete03Show(jugadoresListos, mapa);
                mostrar.enviarData(this);
            } //Si todos los jugadores estan listos para jugar, entonces comienza la partida
        }
    }

    /**
     * Recibe un paquete enviado por algun cliente
     * 
     * @version 1.2
     */
    public void recibirPaquete(){
        datos = new byte[1024];
        DatagramPacket paquete = new DatagramPacket(datos, datos.length);
        try {
            socket.receive(paquete);
            analizarPaquete(paquete.getData(), paquete.getAddress(), paquete.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Envia un paquete a un unico cliente
     * 
     * @param datos Lo que se enviara al cliente
     * @param direccionIP Direccion del cliente
     * @param port  Puerto del cliente
     * @version 1.2
     */
    public void enviarPaquete(byte[] datos, InetAddress direccionIP, int port){
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionIP, port);
        try {
            socket.send(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Le envia un paquete a todos los clientes
     * 
     * @param datos Lo que se enviara a los clientes
     * @version 1.2
     */
    public void enviarAtodosLosClientes(byte[] datos){
        for (JugadorMP j : jugadoresActivos) {
            enviarPaquete(datos, j.getDireccionIP(), j.getPuerto());
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
    private void analizarPaquete(byte[] datos, InetAddress direccionIP, int puerto) {
        String message = new String(datos).trim();
        TiposPaquetes type = Paquete.buscarPaquete(message.substring(0, 2));

        switch(type){
        default:
        case INVALID:
            break;

        case LOGIN:
            Paquete00Login conexion = new Paquete00Login(datos);
            conectarJugador(conexion, direccionIP, puerto);
            System.out.println("[" + direccionIP.getHostAddress() + ":" + puerto + "] "
                    + conexion.getUsername() + " has connected...");
            conexion.setNumCliente(jugadoresActivos.size() - 1);
            conexion.enviarData(this); // Envia el mismo paquete a todos los clientes
            break;
            
        case DISCONNECT:
            Paquete01Desconectar desconexion = new Paquete01Desconectar(datos);
            System.out.println("[" + direccionIP.getHostAddress() + ":" + puerto + "] "
                    + desconexion.getUsername() + " has left...");
            desconectarJugador(desconexion);
            break;

        case PLAY:
            Paquete02Play jugar = new Paquete02Play(datos);
            System.out.println("El usuario " + jugar.getUsuario() + " esta listo para jugar");
            prepararJugador(jugar);
            break;

        case MOVE:
            Paquete07Move mover = new Paquete07Move(datos);
            actualizarMovimiento(mover); 
            break;

        case COLLISION:
            Paquete11Collision efecto = new Paquete11Collision(datos);
            actualizarTablero(efecto);
            break;
        
        case WINDOW:
            Paquete12Window ventana = new Paquete12Window(datos);
            volverALobby(ventana);
            break;
        }


    }

    /**
     * Toma a un jugador que acaba de conectarse y lo agrega a la lista de jugadores
     * 
     * @param paquete Datos del jugador
     * @param direccion IP del paquete
     * @param puerto Puerto del paquete
     * @version 1.2
     */
    public void conectarJugador(Paquete00Login paquete, InetAddress direccion, int puerto){
        JugadorMP jugador = new JugadorMP(paquete.getUsername(), jugadoresActivos.size(), direccion, puerto);
        boolean conectado = false; // Si el jugador ya se encuentra dentro de la lista, por lo tanto ya esta conectado
        
        for(JugadorMP j : jugadoresActivos){
            if(jugador.getUsuario().equalsIgnoreCase(j.getUsuario())){
                if(j.getDireccionIP() == null){
                    j.setDireccionIP(jugador.getDireccionIP());
                }
                if(j.getPuerto() == -1){
                    j.setPuerto(jugador.getPuerto());
                }
                conectado = true;
            }else{
                enviarPaquete(paquete.getData(), j.getDireccionIP(), j.getPuerto());
            }
        }
        if(!conectado){
            jugadoresActivos.add(jugador);
        }
    }
    
    /**
     * Saca a un jugador de la lista en caso de que este se desconecte del servidor
     * 
     * @param paquete Datos del jugador
     * @version 1.2
     */
    public void desconectarJugador(Paquete01Desconectar paquete){
        jugadoresActivos.remove(getIndiceJugador(paquete.getUsername()));
        if(comprobarJugadores()){
            paquete.enviarData(this);
        }
    }

    /**
     * Cuando un jugador este listo para jugar se le asignara los datos
     * de su personaje, dependiendo la skin que haya elegido y su numero
     * de conexion
     * 
     * @param paquete Datos del personaje del jugador
     * @version 1.2
     */
    public void prepararJugador(Paquete02Play paquete){
        String direccion = "";
        int x = -1;
        int y = -1;

        switch(getIndiceJugador(paquete.getUsuario())){
            case 0:
                direccion = "Derecha";
                x = 5;
                y = 1;
                break;
            case 1:
                direccion = "Izquierda";
                x = 16;
                y = 20;
                break;
            case 2:
                direccion = "Arriba";
                x = 1;
                y = 16;
                break;
            case 3:
                direccion = "Abajo";
                x = 20;
                y = 5;
                break;
        }
        jugadoresActivos.get(getIndiceJugador(paquete.getUsuario())).asignaPersonaje(x, y, direccion, paquete.getSkin());

        if(!jugadoresActivos.get(getIndiceJugador(paquete.getUsuario())).getEstaListo()){
            jugadoresListos++;
            jugadoresActivos.get(getIndiceJugador(paquete.getUsuario())).alistaJugador(true);
        }
    }

    /**
     * Le envia los datos de los jugadores que estaran en la partida a los clientes
     * 
     * @version 1.2
     */
    public void enviarJugadores(){
        for(int i = 0; i < jugadoresActivos.size(); i++){
            Jugador jugador = jugadoresActivos.get(i);
            Paquete04Player guardar = new Paquete04Player(jugador.getUsuario(), jugador.getNumero(), jugador.getPersonaje().getCuerpo(0).getPosX(),
                                                        jugador.getPersonaje().getCuerpo(0).getPosY(), jugador.getPersonaje().getCuerpo(0).getDireccion(),
                                                         jugador.getPersonaje().getSkin());
            guardar.enviarData(this);
        }
    }

    /**
     * Si uno de los clientes movio a su personaje, 
     * actualiza el movimiento de su personaje en el juego iniciado en el servidor
     * 
     * @param paquete Datos del movimiento
     * @version 1.2
     */
    public void actualizarMovimiento(Paquete07Move paquete){
        JugadorMP jugador = jugadoresActivos.get(paquete.getIndice());
        jugador.getPersonaje().getCuerpo(0).setDireccion(paquete.getDireccion());
        // paquete.enviarData(this);
    }

    /**
     * Actualiza el estado del tablero dependiendo de lo enviado por el cliente
     * 
     * @param paquete Datos del estado del tablero
     * @version 1.2.2
     */
    public void actualizarTablero(Paquete11Collision paquete){
        if(paquete.getEstado() == 1){
            game.getTablero().setPausa(false);

        }else if(paquete.getEstado() == 2){
            game.getTablero().setPausa(true);

        }
        paquete.enviarData(this);
    }

    /**
     * Comprueba si aun hay jugadores conectados al servidor
     * 
     * @return true si el servidor aun cuenta con jugadores
     * @version 1.2
     */
    public boolean comprobarJugadores(){
        System.out.println(jugadoresActivos.size());
        if(jugadoresActivos.size() == 0){
            cerrarServidor();
            return false;
        }
        return true;
    }

    /**
     * Cambia los valores al estado de una partida no iniciada
     * 
     * @version 1.2.3
     */
    public void acabarPartida(){
        for(int i = 0; i < jugadoresActivos.size(); i++){
            if(jugadoresActivos.get(i).getEstaListo()){
                jugadoresActivos.get(i).alistaJugador(false);
            }
        }
        juegoIniciado = false;
        jugadoresListos = 0;
    }

    /**
     * En caso de que un cliente no quiera volver a jugar y vuelva al lobby, cambia el estado
     * de los jugadores listos y envia a todos los clientes al lobby
     * 
     * @param paquete Datos para el cambio
     * @version 1.2.3
     */
    public void volverALobby(Paquete12Window paquete){
        acabarPartida();
        paquete.enviarData(this);
    }
        
    public int getIndiceJugador(String usuario){
        int indice = 0;
        for(Jugador j : jugadoresActivos){
            String user = j.getUsuario();
            if(user.equals(usuario)){
                break;
            }
            indice++;
        }
        return indice;
    }

    public String getDireccionIP(){
        InetAddress direccionIP;
        try{
            direccionIP = InetAddress.getLocalHost();
            return direccionIP.getHostAddress();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getJugadoresActivos(){
        return jugadoresActivos.size();
    }

    public int getJugadoresListos(){
        return jugadoresListos;
    }
                                    
    public static int getPuerto() {
        return puerto;
    }
    
    public static int getMAX_JUGADORES(){
        return MAX_JUGADORES;
    }
}
