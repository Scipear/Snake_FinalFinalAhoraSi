/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2;
import java.net.InetAddress;
/**
 * Clase que representa un jugador en multijugador
 * 
 * @author IVANNA
 * @version 1.2
 */
public class JugadorMP extends Jugador{
    private InetAddress direccionIP; //Direccion IP del jugador
    private boolean estaListo; //Si esta listo para jugar o no
    private int puerto; //Puerto en donde se mantendra en comunicacion con el servidor

    public JugadorMP(String usuario, int numero, int posX, int posY, String direccion, int skin){
        super(usuario, numero, posX, posY, direccion, skin);
    }

    public JugadorMP(String usuario, int numero, InetAddress direccionIP, int puerto){
        super(usuario, numero);
        this.direccionIP = direccionIP;
        this.puerto = puerto;
        estaListo = false;
    }

    public void setDireccionIP(InetAddress direccionIP){
        this.direccionIP = direccionIP;
    }

    public void setPuerto(int puerto){
        this.puerto = puerto;
    }

    public void alistaJugador(boolean estaListo){
        this.estaListo = estaListo;
    }

    public InetAddress getDireccionIP(){
        return direccionIP;
    }

    public int getPuerto(){
        return puerto;
    }
    
    public boolean getEstaListo(){
        return estaListo;
    }
}
