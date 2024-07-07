package snake2.Conexion;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

import controladores.ClienteLobbie_Controlador;
import controladores.Login_Controlador;
import snake2.Comida;
import snake2.Jugador;
import snake2.JugadorMP;
import snake2.Tablero;
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
import snake2.Front.PantallaJuego;

public class Cliente implements Runnable{
    private static Timer timer;
    private static int cantidadJugadores = 0;
    private byte[] datos;
    private InetAddress direccionIP;
    private DatagramSocket socket;
    private int puerto;
    private Thread thread;
    private Comida comidaAux;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private PantallaJuego pantalla;
    private boolean tieneSkin = false;
    private boolean jugadoresListos = false;
    private Tablero tablero;
    private int numCliente = -1;

    public Cliente(String direccionIP){
        this.puerto = Server.getPuerto();
        try {
            this.direccionIP = InetAddress.getByName(direccionIP);
            socket = new DatagramSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        iniciarCliente();
    }

    public synchronized void iniciarCliente() {
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run(){
        Paquete00Login conexion = new Paquete00Login(Login_Controlador.getNombreUsuario(), 0);
        conexion.enviarData(this);
        
        while(true){
            if(ClienteLobbie_Controlador.isReady == true && tieneSkin == false){
                Paquete02Play play = new Paquete02Play(Login_Controlador.nombreUsuario, ClienteLobbie_Controlador.skinSeleccionada);
                play.enviarData(this);
                tieneSkin = true;
            }
            recibirPaquete();
        }
    }

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

    public void enviarPaquete(byte[] datos){
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionIP, puerto);
        try {
            socket.send(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void analizarPaquete(byte[] datos, InetAddress direccionIP, int puerto) {
        String message = new String(datos).trim();
        TiposPaquetes type = Paquete.buscarPaquete(message.substring(0, 2));
        switch(type){
        case INVALID:
            break;
        case LOGIN:
            Paquete00Login conexion = new Paquete00Login(datos);
            if(numCliente == -1){
                numCliente = conexion.getNumCliente();
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
                jugadoresListos = true;
                tablero = new Tablero(jugadores, mostrar.getMapa());
                tablero.actualizarComida(comidaAux.getPosX(), comidaAux.getPosY(), 4);
                pantalla = new PantallaJuego(tablero, jugadores, numCliente);
                pantalla.setVisible(true);
            }
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

        // case MOVE:
        //     Paquete07Move mover = new Paquete07Move(datos);
        //     actualizarEntidades(mover);
        //     break;
        }
    }

    public void guardarJugador(Paquete04Player paquete){
        Jugador jugador = new Jugador(paquete.getUsuario(), paquete.getNumero(), paquete.getX(), paquete.getY(), paquete.getDireccion(), paquete.getSkin());
        jugadores.add(jugador);
        cantidadJugadores++;
    }

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

        }// }else if(packet instanceof Paquete07Move){
        //      Paquete07Move paquete = (Paquete07Move) packet;
        //      Jugador jugador = jugadores.get(paquete.getIndice());
        //      jugador.getPersonaje().getCuerpo(0).setDireccion(paquete.getDireccion());
        // }
        if(pantalla != null){
            pantalla.actualizaMapa(jugadores, jugadores.size());
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

    public void actualizaComida(Paquete06Comida paquete){
        tablero.actualizarComida(paquete.getX(), paquete.getY(), paquete.getTipo());
    }

    public void setTieneSkin(boolean tieneSkin){
        this.tieneSkin = tieneSkin;
    }

}
