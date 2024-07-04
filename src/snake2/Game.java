package snake2;

import controladores.*;
import ost.ReproductorSonidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
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
        iniciarPartida();
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

    public void iniciarPartida() {
        jugador = new Jugador(usuario, 5, 1, "Derecha", skin);
        tablero = new Tablero(jugador.getPersonaje(), mapa);    //Problema para seleccionar el mapa
        pantalla = new PantallaJuego(tablero, jugador);
        partidaIniciada = true;
        timer = new Timer(175, this);
        timer.start();
        iniciarJuego();
    }

    public static void setDelay(int delay) {
        timer.setDelay(delay);
    }

    @Override
    public void run() {
        while(iniciado){
            if(gameOver == true){
                FinalPartida_Controlador.mostrar();
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

            tablero.conteoComidaEspecial();
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
