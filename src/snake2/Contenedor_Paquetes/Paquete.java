/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

import java.lang.Integer;
import snake2.Conexion.Cliente;
import snake2.Conexion.Server;

/**
 *
 * @author IVANNA
 */
public abstract class Paquete{
    public static enum TiposPaquetes{
        INVALID(-1), LOGIN(00), DISCONNECT(01), PLAY(02), SHOW(03), 
        PLAYER(04), UPDATE(05), FOOD(06), MOVE(07);
        
        private int packetId;

        private TiposPaquetes(int packetId){
          this.packetId = packetId;
        }
        
        public int getId(){
            return packetId;
        }
    }    
    public byte packetId;

    public Paquete(int packetId){
        this.packetId = (byte) packetId;
    }
  
    public String leerData(byte[] data){
        String message = new String(data).trim();
        return message.substring(2);
    }

    public static TiposPaquetes buscarPaquete(String packetId){
        try{
            return buscarPaquete(Integer.parseInt(packetId));
            
        }catch(NumberFormatException e){
            return TiposPaquetes.INVALID;
        }
    }
    
    public static TiposPaquetes buscarPaquete(int id){
        for(TiposPaquetes p : TiposPaquetes.values()){
            if(p.getId() == id){
                return p;
            }
        }
        return TiposPaquetes.INVALID;
    }

    public abstract void enviarData(Cliente client);
    public abstract void enviarData(Server server);
    public abstract byte[] getData();
}
