/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

/**
 * Clase que representa a un paquete para conocer si un jugador se ha desconectado
 * 
 * @version 1.2
 */
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
    public byte[] getData(){
        return ("01" + this.username).getBytes();
    }

    public String getUsername(){
        return username;
    }

}
    

