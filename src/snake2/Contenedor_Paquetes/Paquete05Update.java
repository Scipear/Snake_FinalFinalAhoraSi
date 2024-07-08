/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

import java.lang.Integer;

/**
 * Clase que representa a un paquete para conocer los datos de un personaje
 * 
 * @version 1.2
 */
public class Paquete05Update extends Paquete{
    private int indiceP;
    private int indiceC;
    private String tipo;
    private int x;
    private int y;
    private String direccion;

    public Paquete05Update(byte[] data){
        super(05);
        String[] dataArray = leerData(data).split(",");
        indiceP = Integer.parseInt(dataArray[0]);
        indiceC = Integer.parseInt(dataArray[1]);
        tipo = dataArray[2];
        x = Integer.parseInt(dataArray[3]);
        y = Integer.parseInt(dataArray[4]);
        direccion = dataArray[5];
    }

    public Paquete05Update(int indiceP, int indiceC, String tipo, int x, int y, String direccion){
        super(05);
        this.indiceP = indiceP;
        this.indiceC = indiceC;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        this.direccion = direccion;
    }

    @Override
    public byte[] getData() {
        return ("05" + indiceP + "," + indiceC + "," + tipo + "," + x + "," + y + "," + direccion).getBytes();
    }

    public int getIndiceP(){
        return indiceP;
    }

    public int getIndiceC(){
        return indiceC;
    }

    public String getTipo(){
        return tipo;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setIndiceP(int indiceP){
        this.indiceP = indiceP;
    }
}
    

