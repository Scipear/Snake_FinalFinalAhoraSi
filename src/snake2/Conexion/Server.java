package snake2.Conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server implements Runnable{
    private static final int puerto = 9999;
    private byte[] datos;
    private DatagramSocket socket;
    private Thread thread;

    public Server(){
        datos = new byte[1024];
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
            System.out.println("El servidor ha recibido un paquete: " + mensaje);
            enviarPaquete("Hola carebola".getBytes(), paquete.getAddress(), paquete.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarPaquete(byte[] datos, InetAddress direccionIP, int port){
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionIP, port);
        try {
            socket.send(paquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static int getPuerto(){
        return puerto;
    }

    public boolean mensajeVacio(DatagramPacket paquete){
        String mensaje = new String(paquete.getData());
        return mensaje == null;
    }
}
