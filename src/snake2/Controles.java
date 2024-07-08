package snake2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controladores.Controlador_PreConeccion;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete07Move;

/**
 * Clase parte del back. Configura los controles para un personaje especifico en la partida
 * 
 * @version 1.0.4
 */
public class Controles implements KeyListener, Comunicacion{
    private Paquete07Move mover;
    private Personaje personaje;
    private Tablero tablero;
    private int indice;

    public Controles(Tablero tablero, Personaje personaje, int indice){
        this.personaje = personaje;
        this.tablero = tablero;
        this.indice = indice;
    }

    @Override
    public void keyTyped(KeyEvent e){
        String direccion = null;
        switch(e.getKeyChar()){
            case 'd':{
                if(personaje.getCuerpo(0).getDireccion().equals("Abajo") || personaje.getCuerpo(0).getDireccion().equals("Arriba")){
                    direccion = "Derecha";
                }
                break;
            }
            
            case 's':{
                if(personaje.getCuerpo(0).getDireccion().equals("Derecha") || personaje.getCuerpo(0).getDireccion().equals("Izquierda")){
                    direccion = "Abajo";
                }
                break;
            }

            case 'a':{
                if(personaje.getCuerpo(0).getDireccion().equals("Abajo") || personaje.getCuerpo(0).getDireccion().equals("Arriba")){
                    direccion = "Izquierda";
                }
                break;
            }

            case 'w':{
                if(personaje.getCuerpo(0).getDireccion().equals("Derecha") || personaje.getCuerpo(0).getDireccion().equals("Izquierda")){
                    direccion = "Arriba";
                }
                break;
            }
        }
        if(direccion != null){
            personaje.getCuerpo(0).setDireccion(direccion);
            mover = new Paquete07Move(indice, direccion);
            enviarServidor(mover);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        String direccion = null;

        if(tecla == KeyEvent.VK_RIGHT){
            if(personaje.getCuerpo(0).getDireccion().equals("Abajo") || personaje.getCuerpo(0).getDireccion().equals("Arriba")){
                direccion = "Derecha";
            }
        
        }else if(tecla == KeyEvent.VK_DOWN){
            if(personaje.getCuerpo(0).getDireccion().equals("Derecha") || personaje.getCuerpo(0).getDireccion().equals("Izquierda")){
                direccion = "Abajo";
            }

        }else if(tecla == KeyEvent.VK_LEFT){
            if(personaje.getCuerpo(0).getDireccion().equals("Abajo") || personaje.getCuerpo(0).getDireccion().equals("Arriba")){
                direccion = "Izquierda";
            }

        }else if(tecla == KeyEvent.VK_UP){
            if(personaje.getCuerpo(0).getDireccion().equals("Derecha") || personaje.getCuerpo(0).getDireccion().equals("Izquierda")){
                direccion = "Arriba";
            }
            
        }else if(tecla == KeyEvent.VK_SPACE){
            if(tablero.getPausa()){
                tablero.setPausa(false);

            }else{
                tablero.setPausa(true);

            }
        }
        if(direccion != null){
            personaje.getCuerpo(0).setDireccion(direccion);
            mover = new Paquete07Move(indice, direccion);
            enviarServidor(mover);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void enviarServidor(Paquete paquete){
        if(Controlador_PreConeccion.cliente != null){
            paquete.enviarData(Controlador_PreConeccion.cliente);
        }
    }

}
