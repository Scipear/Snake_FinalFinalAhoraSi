package snake2;
/**
 * Clase parte del back
 * 
 * @version 1.1
 */
public class Comida {
    private int posX;
    private int posY;

    public Comida(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void hacerEfecto(Personaje personaje){
        personaje.setLongitud(personaje.getLongitud() + 1);
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }


}
