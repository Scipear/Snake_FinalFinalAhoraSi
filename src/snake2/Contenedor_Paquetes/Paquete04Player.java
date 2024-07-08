package snake2.Contenedor_Paquetes;

/**
 * Clase que representa a un paquete para los datos iniciales de un jugador
 * 
 * @version 1.2
 */
public class Paquete04Player extends Paquete{
    private String usuario;
    private int numero;
    private int x;
    private int y;
    private String direccion;
    private int skin;

    public Paquete04Player(byte[] data){
        super(04);
        String[] dataArray = leerData(data).split(",");
        usuario = dataArray[0];
        numero = Integer.parseInt(dataArray[1]);
        x = Integer.parseInt(dataArray[2]);
        y = Integer.parseInt(dataArray[3]);
        direccion = dataArray[4];
        skin = Integer.parseInt(dataArray[5]);

    }
    
    public Paquete04Player(String usuario, int numero, int x, int y, String direccion, int skin){
        super(04);
        this.usuario = usuario;
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.skin = skin;
    }

    @Override
    public byte[] getData(){
         return ("04" + usuario + "," + numero + "," + x + "," + y + "," + direccion + "," + skin).getBytes();
    
    }
    
     public String getUsuario(){
        return usuario;
    }
    
    public int getNumero(){
        return numero;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getDireccion(){
        return direccion;
    }
    
    public int getSkin(){
        return skin;
    }
}
