/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

import snake2.Conexion.Cliente;
import snake2.Conexion.Server;

public class Paquete01Desconectar extends Paquete{ 
    private String username;

    public Paquete01Desconectar(byte[] data){
        super(01);
        this.username = leerData(data);
    }

    public Paquete01Desconectar(String username){
        super(01);
        this.username = username;
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
        return ("01" + this.username).getBytes();
    }

    public String getUsername(){
        return username;
    }

}
    

