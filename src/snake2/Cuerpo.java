package snake2;

/**
 * Clase parte del back
 * 
 * @version 1.0.2
 */

public class Cuerpo {
    private int posX;
    private int posY;
    private String tipo;
    private String direccion;
    
    public Cuerpo(int posX, int posY, String tipo, String direccion){
        this.posX = posX;
        this.posY = posY;
        this.tipo = tipo;
        this.direccion = direccion;
    }

    /**
     * Cambia la parte del cuerpo al tipo "Cuerpo" y su direccion
     * 
     * @param anterior Parte del cuerpo que se usara para comparar la parte del cuerpo actual
     * @version 1.0.4
     */
    public void cambioACuerpo(Cuerpo anterior){
        tipo = "Cuerpo";

        if(anterior.tipo == "Cuerpo"){
            direccion = anterior.direccion;

        }else if(anterior.tipo == "Cabeza" || anterior.tipo == "Curva"){
            if(anterior.posX > posX){
                direccion = "Derecha";

            }else if(anterior.posX < posX){
                direccion = "Izquierda";

            }else if(anterior.posY > posY){
                direccion = "Abajo";
                
            }else if(anterior.posY < posY){
                direccion = "Arriba";
                
            }
        }
    }

    /**
     * Cambia la parte del cuerpo al tipo "Curva" y su direccion
     * 
     * @param direccionAnterior Direccion que va a servir para decidir a que direccion colocar la curva
     * @version 1.0.4
     */
    public void cambioACurva(String direccionAnterior){
        if(!mismaDireccion(direccionAnterior)){
            tipo = "Curva";

            if((direccion == "Derecha" && direccionAnterior == "Abajo") || (direccion == "Arriba" && direccionAnterior == "Izquierda")){
                direccion = "LtoD";
                    
            }else if((direccion == "Derecha" && direccionAnterior == "Arriba") || (direccion == "Abajo" && direccionAnterior == "Izquierda")){
                direccion = "LtoU";
                    
            }else if((direccion == "Arriba" && direccionAnterior == "Derecha") || (direccion == "Izquierda" && direccionAnterior == "Abajo")){
                direccion = "RtoD";
                    
            }else if((direccion == "Abajo" && direccionAnterior == "Derecha") || (direccion == "Izquierda" && direccionAnterior == "Arriba")){
                direccion = "RtoU";

            }
        }
    }

    /**
     * Cambia la direccion de la cola
     * 
     * @param direccionAnterior Direccion que va a servir para decidir a que direccion colocar la cola
     * @version 1.0.4
     */
    public void cambioCola(String direccionAnterior){
        switch(direccionAnterior){
            case "LtoD":{
                if(direccion == "Derecha"){
                    direccion = "Abajo";

                }else if(direccion == "Arriba"){
                    direccion = "Izquierda";

                }
                break;
            }

            case "LtoU":{
                if(direccion == "Derecha"){
                    direccion = "Arriba";

                }else if(direccion == "Abajo"){
                    direccion = "Izquierda";

                }
                break;
            }

            case "RtoD":{
                if(direccion == "Izquierda"){
                    direccion = "Abajo";

                }else if(direccion == "Arriba"){
                    direccion = "Derecha";

                }
                break;
            }

            case "RtoU":{
                if(direccion == "Izquierda"){
                    direccion = "Arriba";

                }else if(direccion == "Abajo"){
                    direccion = "Derecha";

                }
                break;
            }
        }
    }

    /**
     * Comprueba si dos direcciones son iguales
     * 
     * @param direccion Direccion con la que se va a comparar
     * @return true si ambas direcciones son iguales
     * @version 1.0.4
     */
    public boolean mismaDireccion(String direccion){
        return this.direccion == direccion;
    }
    
    /**
     * Comprueba si la parte del cuerpo es del tipo "Cabeza"
     * 
     * @return true si la parte del cuerpo es una cabeza
     * @version 1.0.4
     */
    public boolean esCabeza(){
        return tipo == "Cabeza";
    }

    /**
     * Comprueba si la parte del cuerpo es del tipo "Cuerpo"
     * 
     * @return true si la parte del cuerpo es un cuerpo
     * @version 1.0.4
     */
    public boolean esCuerpo(){
        return tipo == "Cuerpo";
    }

    /**
     * Comprueba si la parte del cuerpo es del tipo "Curva"
     * 
     * @return true si la parte del cuerpo es una curva
     * @version 1.0.4
     */
    public boolean esCurva(){
        return tipo == "Curva";
    }

    /**
     * Comprueba si la parte del cuerpo es del tipo "Cola"
     * 
     * @return true si la parte del cuerpo es una cola
     * @version 1.0.4
     */
    public boolean esCola(){
        return tipo == "Cola";
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public String getTipo(){
        return tipo;
    }

    public String getDireccion(){
        return direccion;
    }
}
