/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2;
import java.net.InetAddress;
/**
 *
 * @author IVANNA
 */
public class JugadorMP extends Jugador{
    private InetAddress direccionIP;
    private int puerto;

    public JugadorMP(String usuario, int numero, int posX, int posY, String direccion, int skin){
        super(usuario, numero, posX, posY, direccion, skin);
    }

    public JugadorMP(String usuario, int numero, InetAddress direccionIP, int puerto){
        super(usuario, numero);
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }

    public JugadorMP(String usuario, int numero, int posX, int posY, String direccion, int skin, InetAddress direccionIP, int puerto){
        super(usuario, numero, posX, posY, direccion, skin);
        this.direccionIP = direccionIP;
        this.puerto = puerto;
    }

    public void setDireccionIP(InetAddress direccionIP){
        this.direccionIP = direccionIP;
    }

    public void setPuerto(int puerto){
        this.puerto = puerto;
    }

    public InetAddress getDireccionIP(){
        return direccionIP;
    }

    public int getPuerto(){
        return puerto;
    }    
}
