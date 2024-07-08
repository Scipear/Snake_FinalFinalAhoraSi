package snake2;
/**
 * Clase que representa las celdas o casillas del mapa
 * 
 * version 1.0.1
 */
public class Celda {
    private String tipo;

    public Celda(String tipo){
        this.tipo = tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }
}
