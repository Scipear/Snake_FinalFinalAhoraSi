package snake2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase parte del back. Configura los controles para cuando se esta dentro de una partida
 * 
 * @version 1.0.4
 */
public class Controles implements KeyListener{
    private Personaje personaje;
    private Tablero tablero;

    public Controles(Tablero tablero, Personaje personaje){
        this.personaje = personaje;
        this.tablero = tablero;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'd':{
                if(personaje.getCuerpo(0).getDireccion() == "Abajo" || personaje.getCuerpo(0).getDireccion() == "Arriba"){
                    personaje.getCuerpo(0).setDireccion("Derecha");
                }
                break;
            }
            
            case 's':{
                if(personaje.getCuerpo(0).getDireccion() == "Derecha" || personaje.getCuerpo(0).getDireccion() == "Izquierda"){
                    personaje.getCuerpo(0).setDireccion("Abajo");
                }
                break;
            }

            case 'a':{
                if(personaje.getCuerpo(0).getDireccion() == "Abajo" || personaje.getCuerpo(0).getDireccion() == "Arriba"){
                    personaje.getCuerpo(0).setDireccion("Izquierda");
                }
                break;
            }

            case 'w':{
                if(personaje.getCuerpo(0).getDireccion() == "Derecha" || personaje.getCuerpo(0).getDireccion() == "Izquierda"){
                    personaje.getCuerpo(0).setDireccion("Arriba");
                }
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        if(tecla == KeyEvent.VK_RIGHT){
            if(personaje.getCuerpo(0).getDireccion() == "Abajo" || personaje.getCuerpo(0).getDireccion() == "Arriba"){
                personaje.getCuerpo(0).setDireccion("Derecha");
            }
        
        }else if(tecla == KeyEvent.VK_DOWN){
            if(personaje.getCuerpo(0).getDireccion() == "Derecha" || personaje.getCuerpo(0).getDireccion() == "Izquierda"){
                personaje.getCuerpo(0).setDireccion("Abajo");
            }

        }else if(tecla == KeyEvent.VK_LEFT){
            if(personaje.getCuerpo(0).getDireccion() == "Abajo" || personaje.getCuerpo(0).getDireccion() == "Arriba"){
                personaje.getCuerpo(0).setDireccion("Izquierda");
            }

        }else if(tecla == KeyEvent.VK_UP){
            if(personaje.getCuerpo(0).getDireccion() == "Derecha" || personaje.getCuerpo(0).getDireccion() == "Izquierda"){
                personaje.getCuerpo(0).setDireccion("Arriba");
            }
        }else if(tecla == KeyEvent.VK_SPACE){
            if(tablero.getPausa()){
                tablero.setPausa(false);

            }else{
                tablero.setPausa(true);

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
