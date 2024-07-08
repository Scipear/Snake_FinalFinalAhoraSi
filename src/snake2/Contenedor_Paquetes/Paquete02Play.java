package snake2.Contenedor_Paquetes;

import java.lang.Integer;
/**
 *  Clase que representa a un paquete para conocer si un jugador esta listo para jugar
 * 
 * @author IVANNA
 * @version 1.2
 */
public class Paquete02Play extends Paquete{
    private String usuario;
    private int skin;

    public Paquete02Play(byte[] data){
        super(02);
        String[] dataArray = leerData(data).split(",");
        usuario = dataArray[0];
        skin = Integer.parseInt(dataArray[1]);
    }
    
    public Paquete02Play(String usuario, int skin){
        super(02);
        this.usuario = usuario;
        this.skin = skin;
    }

    @Override
    public byte[] getData(){
         return ("02" + usuario + "," + skin).getBytes();
    
    }
    
     public String getUsuario(){
        return usuario;
    }
    
    public int getSkin(){
        return skin;
    }
}