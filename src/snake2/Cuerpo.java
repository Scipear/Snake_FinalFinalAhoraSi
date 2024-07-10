package snake2;

/**
 * Clase que representa las partes del cuerpo que conforman la serpiente
 * 
 * @version 1.0.2
 */

public class Cuerpo {
    private String direccion; //Direccion a la que esta mirando la parte del cuerpo
    private String tipo; // Que parte de cuerpo es 
    private int posX; //Posicion en X
    private int posY; //Posicion en Y
    
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

        if(anterior.tipo.equals("Cuerpo")){
            direccion = anterior.direccion;

        }else if(anterior.tipo.equals("Cabeza") || anterior.tipo.equals("Curva")){
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

            if((direccion.equals("Derecha") && direccionAnterior.equals("Abajo")) || (direccion.equals("Arriba") && direccionAnterior.equals("Izquierda"))){
                direccion = "LtoD";
                    
            }else if((direccion.equals("Derecha") && direccionAnterior.equals("Arriba")) || (direccion.equals("Abajo") && direccionAnterior.equals("Izquierda"))){
                direccion = "LtoU";
                    
            }else if((direccion.equals("Arriba") && direccionAnterior.equals("Derecha")) || (direccion.equals("Izquierda") && direccionAnterior.equals("Abajo"))){
                direccion = "RtoD";
                    
            }else if((direccion.equals("Abajo") && direccionAnterior.equals("Derecha")) || (direccion.equals("Izquierda") && direccionAnterior.equals("Arriba"))){
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
                if(direccion.equals("Derecha")){
                    direccion = "Abajo";

                }else if(direccion.equals("Arriba")){
                    direccion = "Izquierda";

                }
                break;
            }

            case "LtoU":{
                if(direccion.equals("Derecha")){
                    direccion = "Arriba";

                }else if(direccion.equals("Abajo")){
                    direccion = "Izquierda";

                }
                break;
            }

            case "RtoD":{
                if(direccion.equals("Izquierda")){
                    direccion = "Abajo";

                }else if(direccion.equals("Arriba")){
                    direccion = "Derecha";

                }
                break;
            }

            case "RtoU":{
                if(direccion.equals("Izquierda")){
                    direccion = "Arriba";

                }else if(direccion.equals("Abajo")){
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
        return this.direccion.equals(direccion);
    }
    
    /**
     * Comprueba si la parte del cuerpo es del tipo "Cabeza"
     * 
     * @return true si la parte del cuerpo es una cabeza
     * @version 1.0.4
     */
    public boolean esCabeza(){
        return tipo.equals("Cabeza");
    }

    /**
     * Comprueba si la parte del cuerpo es del tipo "Cuerpo"
     * 
     * @return true si la parte del cuerpo es un cuerpo
     * @version 1.0.4
     */
    public boolean esCuerpo(){
        return tipo.equals("Cuerpo");
    }

    /**
     * Comprueba si la parte del cuerpo es del tipo "Curva"
     * 
     * @return true si la parte del cuerpo es una curva
     * @version 1.0.4
     */
    public boolean esCurva(){
        return tipo.equals("Curva");
    }

    /**
     * Comprueba si la parte del cuerpo es del tipo "Cola"
     * 
     * @return true si la parte del cuerpo es una cola
     * @version 1.0.4
     */
    public boolean esCola(){
        return tipo.equals("Cola");
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
