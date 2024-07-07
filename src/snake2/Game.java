package snake2;

import controladores.*;
import ost.ReproductorSonidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javazoom.jl.decoder.JavaLayerException;
import snake2.Front.PantallaJuego;

public class Game implements Runnable, ActionListener {

    private volatile boolean iniciado = true;
    protected static Timer timer;
    private PantallaJuego pantalla;
    private Thread thread;
    protected Jugador jugador;
    protected Tablero tablero;
    private String usuario;
    private int skin;
    private int mapa; // Guarda el mapa seleccionado por el usuario
    private static boolean gameOver;
    private boolean partidaIniciada;
    private ReproductorSonidos reproductorSonidos = new ReproductorSonidos();

    public Game() {
        gameOver = false;
        usuario = Login_Controlador.getNombreUsuario();
        int modoOpcion = Controlador_MenuPrinc.getModoJuego();
        switch (modoOpcion) {
            case 1:{
                skin = Lobbie_Controlador.getSkinSeleccionada();
                mapa = Lobbie_Controlador.getMapaSeleccionada();
                break;
            }
            case 2:{
                skin = HostLobbie_Controlador.getSkinSeleccionada();
                mapa = HostLobbie_Controlador.getMapaSeleccionada();
                break;
            }
        }
        iniciarJuego();
    }

    public synchronized void iniciarJuego() {
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void detenerJuego() {
        iniciado = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void iniciarPartida(){
        //Jugador j2 = new Jugador("pacheco", 1, 16, 20, "Izquierda", 3);
        //Jugador j3= new Jugador("jejex", 2, 1, 16, "Arriba", 5);
        //Jugador j4= new Jugador("Yippiii", 3, 20, 5, "Abajo", 6);
        jugador = new Jugador(usuario, 0, 5, 1, "Derecha", skin);
        tablero = new Tablero(jugador.getPersonaje(), mapa); //Problema para seleccionar el mapa
        //tablero.setPersonaje(j2.getPersonaje());
        //tablero.setPersonaje(j3.getPersonaje());
        //tablero.setPersonaje(j4.getPersonaje());
        pantalla = new PantallaJuego(tablero, jugador);
    }

    public static void setDelay(int delay) {
        timer.setDelay(delay);
    }

    @Override
    public void run() {
        while(iniciado){
            if(!partidaIniciada){
                iniciarPartida();
                pantalla.actualizaMapa(jugador);
                try {
                    Thread.sleep(3000);
                    partidaIniciada = true;
                    timer = new Timer(175, this);
                    timer.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
            }
        }
    } //Realmente no se para que sirve este hilo, practicamente no hace nada

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!tablero.getPausa()){
            tablero.chequeaPersonajes();

            if(tablero.personajeSobreComida()){
                jugador.aumentaPuntaje();
            }

            try {
                tablero.conteoComidaEspecial();
            } catch (JavaLayerException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            pantalla.actualizaMapa(jugador);
            
            if(!jugador.getPersonaje().getEstado()){
                detenerJuego();
                reproductorSonidos.detener();
                timer.stop();
                gameOver = true;
                pantalla.setVisible(false);
                pantalla.detenerMusica();
                FinalPartida_Controlador.mostrar();
            }
        }
        pantalla.muestraPausa(tablero);
    }
}