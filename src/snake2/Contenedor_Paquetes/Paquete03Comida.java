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
public class Paquete03Comida extends Paquete{

    public Paquete03Comida(){
        super(03);
    }

    @Override
    public void enviarData(Cliente client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enviarData'");
    }

    @Override
    public void enviarData(Server server) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enviarData'");
    }

    @Override
    public byte[] getData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }  
}
