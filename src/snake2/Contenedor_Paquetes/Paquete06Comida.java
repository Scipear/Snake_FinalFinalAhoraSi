/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

/**
 * Clase que representa a un paquete para conocer los datos de una comida
 * 
 * @author IVANNA
 * @version 1.2
 */
public class Paquete06Comida extends Paquete{
    private int tipo_comida;  
    private int X;
    private int Y;

    public Paquete06Comida(byte[] data){        
        super(06);
        String[] dataArray = leerData(data).split(",");
        this.tipo_comida = Integer.parseInt(dataArray[0]);
        this.X = Integer.parseInt(dataArray[1]);        
        this.Y = Integer.parseInt(dataArray[2]);
                
    }
    public Paquete06Comida(int x, int y, int tipo_comida){        
        super(06);
        this.X = x;        
        this.Y = y;
        this.tipo_comida = tipo_comida;   
    }

    @Override
    public byte[] getData(){
        return ("06" + this.tipo_comida + "," + this.X + "," + this.Y).getBytes();    
    }
    public int getX(){
        return X;    
    }
    public int getY(){
        return Y;    
    }

    public int getTipo(){
        return tipo_comida;
    }
     
}
