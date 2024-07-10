/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

/**
 *  Clase que representa a un paquete para conocer ciertas actualizaciones
 *  del tablero y su contenido
 * 
 * @author IVANNA
 * @version 1.2.2
 */
public class Paquete11Board extends Paquete{
    private int indice;
    private int estado;
    
    public Paquete11Board(byte[] data){        
        super(11);
        String[] dataArray = leerData(data).split(",");
        this.estado = Integer.parseInt(dataArray[0]);
        this.indice = Integer.parseInt(dataArray[1]);               
    }

    public Paquete11Board(int indice, int estado){
        super(11);
        this.estado = estado;
        this.indice = indice;
    }

    @Override
    public byte[] getData(){
        return ("11" + this.estado + "," + this.indice).getBytes();    
    }
    
    public int getIndice() {
        return indice;
    }

    public int getEstado() {
        return estado;
    }
}