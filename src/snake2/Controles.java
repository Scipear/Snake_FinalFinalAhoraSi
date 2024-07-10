package snake2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controladores.Controlador_Cliente;
import controladores.Controlador_Host;
import controladores.Controlador_PreConeccion;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete07Move;
import snake2.Contenedor_Paquetes.Paquete11Board;

/**
 * Clase parte del back. Configura los controles para un personaje especifico en la partida
 * 
 * @version 1.0.4
 */
public class Controles implements KeyListener, Comunicacion{
    private Paquete07Move mover; //Paquete para informar al servidor si un cliente ha movido a su personaje
    private Paquete11Board board; //Paquete para informar al servidor si un cliente ha hecho alguna actualizacion en su tablero
    private Personaje personaje; //Personaje al que se le asigna los controles
    private Tablero tablero; //Tablero en donde estara dicho personaje
    private int indice; //Indice para identificar al jugador y su personaje

    public Controles(Tablero tablero, Personaje personaje, int indice){
        this.personaje = personaje;
        this.tablero = tablero;
        this.indice = indice;
    }

    @Override
    public void keyTyped(KeyEvent e){
        String direccion = null;
        if(personaje.getEstado()){
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
    
                case 'q':{
                    personaje.setEstado(false);
                }
            }
    
            if(direccion != null){
                personaje.getCuerpo(0).setDireccion(direccion);
                mover = new Paquete07Move(indice, direccion);
                enviarServidor(mover);
            }
    
            if(!personaje.getEstado()){
                board = new Paquete11Board(indice, 0);
                enviarServidor(board);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        String direccion = null;
        if(personaje.getEstado()){
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
                    board = new Paquete11Board(indice, 1);
    
                }else{
                    tablero.setPausa(true);
                    board = new Paquete11Board(indice, 2);
                }
                enviarServidor(board);
            }
    
            if(direccion != null){
                personaje.getCuerpo(0).setDireccion(direccion);
                mover = new Paquete07Move(indice, direccion);
                enviarServidor(mover);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void enviarServidor(Paquete paquete){
        if(Controlador_Cliente.modo == 3 || Controlador_Host.modo == 2){
            paquete.enviarData(Controlador_PreConeccion.cliente);
        }
    }

}
