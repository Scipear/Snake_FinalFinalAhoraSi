package snake2.Conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import snake2.Comida;
import snake2.Game;
import snake2.JugadorMP;
import snake2.Jugador;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete.TiposPaquetes;
import snake2.Contenedor_Paquetes.Paquete00Login;
import snake2.Contenedor_Paquetes.Paquete01Desconectar;
import snake2.Contenedor_Paquetes.Paquete02Play;
import snake2.Contenedor_Paquetes.Paquete03Show;
import snake2.Contenedor_Paquetes.Paquete04Player;
import snake2.Contenedor_Paquetes.Paquete05Update;
import snake2.Contenedor_Paquetes.Paquete06Comida;
import snake2.Contenedor_Paquetes.Paquete07Move;


public class Server implements Runnable{
    private static final int puerto = 9999;
    private boolean juegoIniciado = false;
    private byte[] datos;
    private DatagramSocket socket;
    private Thread thread;
    private ArrayList<JugadorMP> jugadoresActivos = new ArrayList<>();
    private int jugadoresListos;
    private Game game;
    private int mapa;
 

    public Server(){
        jugadoresListos = 0;
        try {
            socket = new DatagramSocket(puerto);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        iniciarServidor();        
    }
    
    public synchronized void iniciarServidor() {
        thread = new Thread(this);
        thread.start();
    }

    public void run(){
        while (true) {
            recibirPaquete();
            if((jugadoresActivos.size() == jugadoresListos) && !juegoIniciado){
                game = new Game();
                game.iniciarMultiplayer(jugadoresActivos);
                mapa = game.getMapa();
                juegoIniciado = true;
                //game.getPantalla().setVisible(true);
                Paquete03Show mostrar = new Paquete03Show(jugadoresListos, mapa);
                mostrar.enviarData(this);
            }
        }
    }


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
            conexion.enviarData(this);
            break;
            
        case DISCONNECT:
            Paquete01Desconectar desconexion = new Paquete01Desconectar(datos);
            System.out.println("[" + direccionIP.getHostAddress() + ":" + puerto + "] "
                    + desconexion.getUsername() + " has left...");
            desconectarJugador(desconexion);
            break;

        case PLAY:
            Paquete02Play jugar = new Paquete02Play(datos);
            System.out.println("El usuario " + jugar.getUsuario() + " ha elegido la skin " + jugar.getSkin() + " y esta listo para jugar");
            prepararJugador(jugar);
            break;

        case MOVE:
            Paquete07Move mover = new Paquete07Move(datos);
            actualizarMovimiento(mover); 
            break;     
        }

    }

    public void conectarJugador(Paquete00Login paquete, InetAddress direccion, int puerto){
        JugadorMP jugador = new JugadorMP(paquete.getUsername(), jugadoresActivos.size(), direccion, puerto);
        boolean conectado = false;
        
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
    
    public void desconectarJugador(Paquete01Desconectar paquete){
        jugadoresActivos.remove(getIndiceJugador(paquete.getUsername()));
        //paquete.enviarData(this);
    }

    public void prepararJugador(Paquete02Play paquete){
        Jugador jugador;
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
        jugador = jugadoresActivos.get(getIndiceJugador(paquete.getUsuario()));
        Paquete04Player guardar = new Paquete04Player(jugador.getUsuario(), jugador.getNumero(), jugador.getPersonaje().getCuerpo(0).getPosX(),
                                                        jugador.getPersonaje().getCuerpo(0).getPosY(), jugador.getPersonaje().getCuerpo(0).getDireccion(),
                                                         jugador.getPersonaje().getSkin());
        guardar.enviarData(this);
    }

    public void actualizarMovimiento(Paquete07Move paquete){
        JugadorMP jugador = jugadoresActivos.get(paquete.getIndice());
        jugador.getPersonaje().getCuerpo(0).setDireccion(paquete.getDireccion());
        paquete.enviarData(this);
    }
       
    public void enviarPaquete(byte[] datos, InetAddress direccionIP, int port){
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionIP, port);
        try {
            socket.send(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void enviarAtodosLosClientes(byte[] datos){
        for (JugadorMP j : jugadoresActivos) {
            enviarPaquete(datos, j.getDireccionIP(), j.getPuerto());
        }
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
                                    
    public static int getPuerto() {
        return puerto;
    }                              
}
