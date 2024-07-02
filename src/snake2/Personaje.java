package snake2;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase parte del back
 * 
 * @version 1.0.1
 */
public class Personaje{
    private List<Cuerpo> serpiente = new ArrayList<>(); //Representa todo el cuerpo de la serpiente
    private int velocidad;
    private int longitud;
    private int skin;
    private boolean estado;

    /**
     * Constructor de la clase
     * 
     * 
     * @param posX Posicion en X de en que parte del tablero aparecera el personaje
     * @param posY Posicion en X de en que parte del tablero aparecera el personaje
     * @param direccion Direccion a la que estara mirando el personaje
     * @param skin Skin elegida por el jugador para su personaje
     */
    public Personaje(int posX, int posY, String direccion, int skin){
        serpiente.add(new Cuerpo(posX, posY, "Cabeza", direccion));
        serpiente.add(new Cuerpo(posX - 1, posY, "Cuerpo", direccion));
        serpiente.add(new Cuerpo(posX - 2, posY, "Cola", direccion));
        this.skin = skin;
        velocidad = 1;
        longitud = serpiente.size();
        estado = true;
    }
    /**
     * Cambia la posicion de la parte del cuerpo de la serpiente dependiendo de la direccion a la
     * que mire
     * 
     * @param i Posicion para poder acceder al nodo en dicha posicion en la lista
     * @version 1.0.3
     */
    public void avanzar(int i){
        switch(serpiente.get(i).getDireccion()){
            case "Derecha":{
                serpiente.get(i).setPosX(serpiente.get(i).getPosX() + velocidad);
                break;
            }

            case "Abajo":{
                serpiente.get(i).setPosY(serpiente.get(i).getPosY() + velocidad);
                break;
            }

            case "Izquierda":{
                serpiente.get(i).setPosX(serpiente.get(i).getPosX() - velocidad);
                break;
            }

            case "Arriba":{
                serpiente.get(i).setPosY(serpiente.get(i).getPosY() - velocidad);
                break;
            }
        }
    }
 
    /**
     * Recorre las partes del cuerpo de la serpiente y evalua sus estados
     * 
     * @version 1.0.4
     */
    public void movimiento(){
        for(int i = longitud-1; i >= 0; i--){
            if(serpiente.get(i).esCurva()){
                serpiente.get(i).cambioACuerpo(serpiente.get(i-1));
            }

            avanzar(i);
            
            if(i != 0 && serpiente.get(i-1).esCabeza()){
                serpiente.get(i).cambioACurva(serpiente.get(i-1).getDireccion());
            }
            
            if(i != 0 && !serpiente.get(i).esCola() && !serpiente.get(i-1).esCabeza()){
                serpiente.get(i).setTipo(serpiente.get(i-1).getTipo());
                serpiente.get(i).setDireccion(serpiente.get(i-1).getDireccion());
            }

            if(serpiente.get(i).esCola() && !serpiente.get(i).mismaDireccion(serpiente.get(i-1).getDireccion())){
                serpiente.get(i).cambioCola(serpiente.get(i-1).getDireccion());
            }
        }
    }

    /**
     * Hace que la serpiente crezca de tamanio
     * 
     * @version 1.1.1
     */
    public void creceCuerpo(){
        Cuerpo aux = serpiente.get(longitud-1);
        serpiente.get(longitud-1).setTipo("Cuerpo");
        
        if(aux.getDireccion() == "Arriba"){
            serpiente.add(new Cuerpo(aux.getPosX(), aux.getPosY()+velocidad, "Cola", "Arriba"));
            //PONER SONDIDO DE COMER 
        }else if(aux.getDireccion() == "Derecha"){
            serpiente.add(new Cuerpo(aux.getPosX()-velocidad, aux.getPosY(), "Cola", "Derecha"));
            //PONER SONDIDO DE COMER 
        }else if(aux.getDireccion() == "Abajo"){
            serpiente.add(new Cuerpo(aux.getPosX(), aux.getPosY()-velocidad, "Cola", "Abajo"));
            //PONER SONDIDO DE COMER 
        }else if(aux.getDireccion() == "Izquierda"){
            serpiente.add(new Cuerpo(aux.getPosX()+velocidad, aux.getPosY(), "Cola", "Izquierda"));
            //PONER SONDIDO DE COMER 
        }     
    }

    /**
     * Verifica si la serpienta ha chocado contra si misma
     * 
     * @version 1.1.2
     */
    public void chocaConCuerpo(){
        for(int i = 1; i < longitud-1; i++){
            if(serpiente.get(0).getPosX() == serpiente.get(i).getPosX() && serpiente.get(0).getPosY() == serpiente.get(i).getPosY()){
                estado = false;
                
                //PONERLE SONIDO DE MUERTE
            }
        }
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public void setLongitud(int longitud){
        this.longitud = longitud;
    }

    public boolean getEstado(){
        return estado;
    }

    public Cuerpo getCuerpo(int i){
        return serpiente.get(i);
    }

    public int getLongitud(){
        return longitud;
    }

    public int getSkin(){
        return skin;
    }

    public void setSkin(int skin) {
        this.skin = skin;
    }
}
