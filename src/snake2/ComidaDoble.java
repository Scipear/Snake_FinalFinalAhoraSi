package snake2;

/**
 * Clase que representa el tipo de comida especial Comida Doble. Hereda de la clase Comida
 * 
 * @version 1.1.4
 */
public class ComidaDoble extends Comida{
    public ComidaDoble(int posX, int posY){
        super(posX, posY);
    }

    public void hacerEfecto(Personaje personaje){
        personaje.aumentaLongitud();
        super.hacerEfecto(personaje);
    }
}