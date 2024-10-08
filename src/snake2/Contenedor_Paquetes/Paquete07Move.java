package snake2.Contenedor_Paquetes;

/**
 * Clase que representa a un paquete para conocer la direccion a la que se ha movido un personaje
 * 
 * @version 1.2
 */
public class Paquete07Move extends Paquete{
    private int indice;
    private String direccion;

    public Paquete07Move(byte[] data){
        super(07);
        String[] dataArray = leerData(data).split(",");
        this.indice = Integer.parseInt(dataArray[0]);
        this.direccion = dataArray[1];
    }

    public Paquete07Move(int indice, String direccion){
        super(07);
        this.indice = indice;
        this.direccion = direccion;
    }

    @Override
    public byte[] getData() {
        return ("07" + indice + "," + direccion).getBytes();
    }

    public int getIndice(){
        return indice;
    }
    
    public String getDireccion(){
        return direccion;
    }
}
