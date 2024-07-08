package snake2.Contenedor_Paquetes;

/**
 * Clase que representa a un paquete para conocer los datos iniciales de una partida recien iniciada
 * 
 * @version 1.2
 */
public class Paquete03Show extends Paquete{
    private int cantidad;
    private int mapa;


    public Paquete03Show(byte[] data){
        super(03);
        String[] dataArray = leerData(data).split(",");
        cantidad = Integer.parseInt(dataArray[0]);
        mapa = Integer.parseInt(dataArray[1]);
    }
    
    public Paquete03Show(int cantidad, int mapa){
        super(03);
        this.cantidad = cantidad;
        this.mapa = mapa;
    }

    @Override
    public byte[] getData(){
         return ("03" + cantidad + "," + mapa).getBytes();
    
    }

    public int getCantidad(){
        return cantidad;
    }

    public int getMapa(){
        return mapa;
    }
}
