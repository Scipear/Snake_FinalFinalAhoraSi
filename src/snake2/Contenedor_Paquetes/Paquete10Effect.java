package snake2.Contenedor_Paquetes;

public class Paquete10Effect extends Paquete{
    private int tipo_comida_especial;  
    private int indiceP;
    private int X;
    private int Y;

    public Paquete10Effect(byte[] data){        
        super(10);
        String[] dataArray = leerData(data).split(",");
        this.tipo_comida_especial = Integer.parseInt(dataArray[0]);
        this.indiceP = Integer.parseInt(dataArray[1]);
        this.X = Integer.parseInt(dataArray[2]);        
        this.Y = Integer.parseInt(dataArray[3]);
                
    }

    public Paquete10Effect(int tipo_comida, int indiceP, int X, int Y){
        super(10);
        this.tipo_comida_especial = tipo_comida;
        this.indiceP = indiceP;
        this.X = X;
        this.Y = Y;
    }
    
    @Override
    public byte[] getData(){
        return ("10" + this.tipo_comida_especial + "," + indiceP + "," + this.X + "," + this.Y).getBytes();    
    }
    public int getX(){
        return X;    
    }
    public int getY(){
        return Y;    
    }

    public int getTipo(){
        return tipo_comida_especial;
    }

    public int getIndiceP() {
        return indiceP;
    }
}
