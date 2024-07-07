/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

import java.lang.Integer;
import snake2.Jugador;
import snake2.Conexion.Cliente;
import snake2.Conexion.Server;

/**
 *
 * @author IVANNA
 */
public class Paquete00Login extends Paquete{
      private String username;

    public Paquete00Login(byte[] data){
        super(00);
        String[] dataArray = leerData(data).split(",");
        this.username = dataArray[0];
        //this.x = Integer.parseInt(dataArray[1]);
        //this.y = Integer.parseInt(dataArray[2]);
    }
    
    public Paquete00Login(String username/*, int x, int y*/){
        super(00);
        this.username = username;
        //this.x = x;
        //this.y = y;
    }
    

    @Override
    public void enviarData(Cliente client){
       client.enviarPaquete(getData());
    }    

    @Override
    public void enviarData(Server server){
       server.enviarAtodosLosClientes(getData());
    }

    @Override
    public byte[] getData(){
         return ("00" + this.username /*+ "," + getX() + "," + getY()*/).getBytes();
    
    }
    
     public String getUsername(){
        return username;
    }
    /*
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    */
}