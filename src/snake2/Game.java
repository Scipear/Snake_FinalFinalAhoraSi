package snake2;

import controladores.*;
import ost.ReproductorSonidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import Gui.Panta_FinalPartida_Online;
import snake2.Contenedor_Paquetes.Paquete;
import snake2.Contenedor_Paquetes.Paquete12Window;
import snake2.Front.PantallaJuego;

/**
 * Clase que representa la partida
 * 
 * @version 1.1.4
 */
public class Game implements Runnable, ActionListener, Comunicacion {
    private volatile boolean iniciado = false; // Para controlar el hilo
    private static boolean gameOver; // Si ya acabo la partida
    private static Timer timer; // Permite que la pantalla se actualice constantemente
    private Paquete12Window window;
    private ReproductorSonidos reproductorSonidos = new ReproductorSonidos();
    private PantallaJuego pantalla; // Pantalla en donde ocurrira la partida
    private Thread thread;
    protected Tablero tablero; // Tablero en donde ocurrira la partida
    private ArrayList<Jugador> jugadores = new ArrayList<>(); // Jugadores que seran parte de la partida
    private boolean partidaIniciada; // Si la partida ya ha sido iniciada
    private int mapa; // Guarda el mapa seleccionado por el usuario
    // private int indiceJugadores = -1; Para tener control del indice de cada jugador

    public Game(){
        gameOver = false;
    }

    /**
     * Inicia una partida en caso de que el modo de juego sea para un solo jugador
     * 
     * @version 1.2
     */
    public void iniciarSinglePlayer(){
        String usuario = Controlador_Login.nombreUsuario;
        int skin = Controlador_Lobby.getSkinSeleccionada();
        mapa = Controlador_Lobby.getMapaSeleccionada();
        jugadores.add(new Jugador(usuario, 0, 5, 1, "Derecha", skin));
        // indiceJugadores++;
        tablero = new Tablero(jugadores, mapa);
        tablero.generarComida();
        pantalla = new PantallaJuego(tablero, jugadores, 0);
        pantalla.setVisible(true);
        iniciarJuego();
    }

    /**
     * Inicia una partida en caso que el modo de juego sea multijugador
     * 
     * @param jugadores Quienes seran parte de la partida
     * @version 1.2
     */
    public void iniciarMultiplayer(ArrayList<JugadorMP> jugadores){
        for(int i = 0; i < jugadores.size(); i++){
            this.jugadores.add(jugadores.get(i));
            // indiceJugadores++;
        }
        mapa = Controlador_Host.getMapaSeleccionada();
        tablero = new Tablero(this.jugadores, mapa);
        tablero.generarComida();
        pantalla = new PantallaJuego(tablero, this.jugadores, 0);
        pantalla.detenerMusica();
        iniciarJuego();
    }

    /**
     * Inicia el hilo de la clase, lo que hace que se ejecute el metodo run automaticamente
     * 
     * @version 1.1.4
     */
    public synchronized void iniciarJuego() {
        thread = new Thread(this);
        thread.start();
        iniciado = true;
    }

    /**
     * Cierra el hilo de la clase, funciona para terminar la partida
     * 
     * @version 1.1.4
     */
    public synchronized void detenerJuego() {
        iniciado = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        //pantalla.setVisible(true);
        while(iniciado){
            if(!partidaIniciada){
                //pantalla.actualizaMapa(jugadores, jugadores.size());
                try{
                    Thread.sleep(3000);
                    partidaIniciada = true;
                    timer = new Timer(175, this);
                    timer.start();
                }catch(InterruptedException e){
                    e.printStackTrace();
                } 
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!tablero.getPausa()){
            tablero.chequeaPersonajes();
            if(tablero.personajeSobreComida(jugadores)){
                pantalla.actualizaPuntaje(jugadores);
            }

            tablero.conteoComidaEspecial();
            pantalla.actualizaMapa();
            
            if(tablero.personajesVivos() == 0){
                detenerJuego();
                reproductorSonidos.detener();
                timer.stop();
                gameOver = true;
                pantalla.detenerMusica();
                pantalla.dispose();
                if(Controlador_Lobby.modo == 1){
                    Controlador_FinalPartida.ventana = new Panta_FinalPartida_Online(jugadores);
                    Controlador_FinalPartida.inicializar(Controlador_FinalPartida.ventana);
                }else{
                    Controlador_PreConeccion.server.acabarPartida();
                    window = new Paquete12Window(1);
                    enviarServidor(window);
                }
            }
        }
        pantalla.muestraPausa(tablero);
    }

    public static void setDelay(int delay) {
        timer.setDelay(delay);
    }

    public int getMapa(){
        return mapa;
    }

    public PantallaJuego getPantalla(){
        return pantalla;
    }    

    public Tablero getTablero(){
        return tablero;
    }

    @Override
    public void enviarServidor(Paquete paquete) {
        paquete.enviarData(Controlador_PreConeccion.server);
    }
}