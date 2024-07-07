/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Contenedor_Paquetes;

import java.lang.Integer;

import snake2.Jugador;
import snake2.Conexion.Cliente;
import snake2.Conexion.Server;

public class Paquete02Mover extends Paquete{
    
    private String username;
    private int x, y;

    private int numSteps = 0;
    private boolean isMoving;
    private int movingDir = 1;

    public Paquete02Mover(byte[] data)
    {
        super(02);
        String[] dataArray = leerData(data).split(",");
        this.username = dataArray[0];
        this.x = Integer.parseInt(dataArray[1]);
        this.y = Integer.parseInt(dataArray[2]);
        this.numSteps = Integer.parseInt(dataArray[3]);
        this.isMoving = Integer.parseInt(dataArray[4]) == 1;
        this.movingDir = Integer.parseInt(dataArray[5]);
    }

    public Paquete02Mover(String username, int x, int y, int numSteps, boolean isMoving, int movingDir) {
        super(02);
        this.username = username;
        this.x = x;
        this.y = y;
        this.numSteps = numSteps;
        this.isMoving = isMoving;
        this.movingDir = movingDir;
    }

    @Override
    public void enviarData(Cliente client) {
        client.enviarPaquete(getData());
    }

    @Override
    public void enviarData(Server server) {
        server.enviarAtodosLosClientes(getData());
    }

    @Override
    public byte[] getData() {
        return ("02" + this.username + "," + this.x + "," + this.y + "," + this.numSteps + "," + (isMoving ? 1 : 0)
                + "," + this.movingDir).getBytes();

    }

    public String getUsername() {
        return username;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getNumSteps() {
        return numSteps;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public int getMovingDir() {
        return movingDir;
    }
}
    

