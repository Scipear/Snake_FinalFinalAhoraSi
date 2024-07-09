package snake2.Contenedor_Paquetes;
/**
 * Clase que representa a un paquete para conocer actualizaciones sobre
 * que ventanas mostrar
 * 
 * @version 1.2.3
 */
public class Paquete12Window extends Paquete{
    private int tipo;
    
    public Paquete12Window(byte[] data){        
        super(12);
        String[] dataArray = leerData(data).split(",");
        this.tipo = Integer.parseInt(dataArray[0]);              
    }

    public Paquete12Window(int tipo){
        super(12);
        this.tipo = tipo;
    }

    @Override
    public byte[] getData(){
        return ("12" + this.tipo).getBytes();    
    }
    
    public int getTipo(){
        return tipo;
    }
}
