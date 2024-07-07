package snake2.Conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import controladores.ClienteLobbie_Controlador;
import controladores.Login_Controlador;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete.TiposPaquetes;
import snake2.Contenedor_Paquetes.Paquete00Login;
import snake2.Contenedor_Paquetes.Paquete01Desconectar;
import snake2.Contenedor_Paquetes.Paquete02Play;

public class Cliente implements Runnable{
    private byte[] datos;
    private InetAddress direccionIP;
    private DatagramSocket socket;
    private int puerto;
    private Thread thread;
    private boolean tieneSkin = false;

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
        Paquete00Login conexion = new Paquete00Login(Login_Controlador.getNombreUsuario());
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
            //String mensaje = new String(paquete.getData());
            //System.out.println("El cliente ha recibido un paquete: " + mensaje);
            analizarPaquete(datos, paquete.getAddress(), paquete.getPort());
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
            System.out.println("[" + direccionIP.getHostAddress() + ":" + puerto + "] "
                    + conexion.getUsername() + " has joined...");
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

    public void setTieneSkin(boolean tieneSkin){
        this.tieneSkin = tieneSkin;
    }
    /*
    public void sendData(byte[] data) 
    {
            if (!game.isApplet) {
            DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }    }*/
}
