/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

import java.lang.Integer;
import snake2.Conexion.Cliente;
import snake2.Conexion.Server;

/**
 * Clase padre que se representa los paquetes que seran utilizados para la comunicacion
 * entre cliente y servidor
 * 
 * @author IVANNA
 * @version 1.2
 */
public abstract class Paquete{
    /**
     * Enumerador para identificar los diferentes tipos de paquetes que puede haber en el programa
     * 
     * @version 1.2
     */
    public static enum TiposPaquetes{
        INVALID(-1), LOGIN(00), DISCONNECT(01), PLAY(02), SHOW(03), 
        PLAYER(04), UPDATE(05), FOOD(06), MOVE(07), EFFECT(10),
        COLLISION(11), WINDOW(12);
        
        private int packetId;

        private TiposPaquetes(int packetId){
          this.packetId = packetId;
        }
        
        public int getId(){
            return packetId;
        }
    }
    public byte packetId;

    /**
     * Constructor de la clase
     * 
     * @param packetId identificador para saber que tipo de paquete instanciar
     */
    public Paquete(int packetId){
        this.packetId = (byte) packetId;
    }
    
    /**
     * Toma las primeras dos letras del contenido del paquete, esto representa su identificador
     * 
     * @param data Contenido del paquete
     * @return identificador del paquete
     */
    public String leerData(byte[] data){
        String message = new String(data).trim();
        return message.substring(2);
    }

    /**
     * Identifica que tipo de paquete que fue recibido por el servidor o cliente
     * 
     * @param packetId identificador del paquete en forma de string
     * @return tipo de paquete que fue recibido por el servidor o cliente
     * @version 1.2
     */
    public static TiposPaquetes buscarPaquete(String packetId){
        try{
            return buscarPaquete(Integer.parseInt(packetId));
            
        }catch(NumberFormatException e){
            return TiposPaquetes.INVALID;
        }
    }
    
    /**
     * Localiza el tipo de paquete que fue recibido
     * 
     * @param id identificador del paquete en forma de entero
     * @return tipo de paquete que fue recibido
     * @version 1.2
     */
    public static TiposPaquetes buscarPaquete(int id){
        for(TiposPaquetes p : TiposPaquetes.values()){
            if(p.getId() == id){
                return p;
            }
        }
        return TiposPaquetes.INVALID;
    }
    
    /**
     * Envia el paquete al servidor
     * 
     * @param client Cliente que esta enviando el paquete
     * @version 1.2
     */
    public void enviarData(Cliente client){
        client.enviarPaquete(getData());
    }

    /**
     * Envia el paquete a todos los clientes
     * 
     * @param server Servidor que esta enviando el paquete
     * @version 1.2
     */
    public void enviarData(Server server){
        server.enviarAtodosLosClientes(getData());
    }
    /**
     * Transcribe la data para que pueda ser enviada en forma de paquete
     * 
     * @return Los datos del paquete en forma de bytes
     * @version 1.2
     */
    public abstract byte[] getData();
}
