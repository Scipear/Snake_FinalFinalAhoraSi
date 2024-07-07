/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

import snake2.Conexion.Cliente;
import snake2.Conexion.Server;

/**
 *
 * @author IVANNA
 */
public class Paquete00Login extends Paquete{
      private String username;
      private int numCliente;

    public Paquete00Login(byte[] data){
        super(00);
        String[] dataArray = leerData(data).split(",");
        this.username = dataArray[0];
        this.numCliente = Integer.parseInt(dataArray[1]);
    }
    
    public Paquete00Login(String username, int numCliente){
        super(00);
        this.username = username;
        this.numCliente = numCliente;
    }
    
    @Override
    public void enviarData(Cliente client){
       client.enviarPaquete(getData());
    }    

    @Override
    public void enviarData(Server server){
       server.enviarAtodosLosClientes(getData());
    }

    public void setNumCliente(int numCliente){
        this.numCliente = numCliente;
    }

    @Override
    public byte[] getData(){
         return ("00" + this.username + "," + this.numCliente).getBytes();
    }
    
     public String getUsername(){
        return username;
    }

    public int getNumCliente() {
        return numCliente;
    }
}