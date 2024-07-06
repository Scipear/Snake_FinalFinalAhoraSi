package snake2.Conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente implements Runnable{
    private byte[] datos;
    private InetAddress direccionIP;
    private DatagramSocket socket;
    private int puerto;
    private Thread thread;

    public Cliente(String direccionIP){
        datos = new byte[1024];
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
    public void run() {
        while(true){
            recibirPaquete();
        }
    }

    public void recibirPaquete(){
        DatagramPacket paquete = new DatagramPacket(datos, datos.length);
        try {
            socket.receive(paquete);
            String mensaje = new String(paquete.getData());
            System.out.println("El cliente ha recibido un paquete: " + mensaje);
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
}
