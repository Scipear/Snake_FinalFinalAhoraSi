package snake2;

import controladores.*;
import ost.ReproductorSonidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import snake2.Front.PantallaJuego;

public class Game implements Runnable, ActionListener {

    private volatile boolean iniciado = true;
    private static Timer timer;
    private PantallaJuego pantalla;
    private Thread thread;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private int indiceJugadores = -1;
    protected Tablero tablero;
    private int mapa; // Guarda el mapa seleccionado por el usuario
    private static boolean gameOver;
    private boolean partidaIniciada;
    private ReproductorSonidos reproductorSonidos = new ReproductorSonidos();

    public Game(){
        gameOver = false;
        /*int modoOpcion = Controlador_MenuPrinc.getModoJuego();
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
        }*/
        //iniciarJuego();
    }

    public void iniciarSinglePlayer(){
        String usuario = Login_Controlador.getNombreUsuario();
        int skin = Lobbie_Controlador.getSkinSeleccionada();
        mapa = Lobbie_Controlador.getMapaSeleccionada();
        jugadores.add(new Jugador(usuario, 0, 5, 1, "Derecha", skin));
        indiceJugadores++;
        tablero = new Tablero(jugadores, mapa);
        tablero.generarComida();
        pantalla = new PantallaJuego(tablero, jugadores, 0);
        pantalla.setVisible(true);
        iniciarJuego();
    }

    public void iniciarMultiplayer(ArrayList<JugadorMP> jugadores){
        for(int i = 0; i < jugadores.size(); i++){
            this.jugadores.add(jugadores.get(i));
            indiceJugadores++;
        }
        mapa = HostLobbie_Controlador.getMapaSeleccionada();
        tablero = new Tablero(this.jugadores, mapa);
        tablero.generarComida();
        pantalla = new PantallaJuego(tablero, this.jugadores);
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

    public int getMapa(){
        return mapa;
    }

    public PantallaJuego getPantalla(){
        return pantalla;
    }    

    public Tablero getTablero(){
        return tablero;
    }

    public static void setDelay(int delay) {
        timer.setDelay(delay);
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
    } //Realmente no se para que sirve este hilo, practicamente no hace nada

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!tablero.getPausa()){
            tablero.chequeaPersonajes();
            tablero.personajeSobreComida(jugadores);

            tablero.conteoComidaEspecial();
            pantalla.actualizaMapa(jugadores, jugadores.size());
            /*
            if(!jugador.getPersonaje().getEstado()){
                detenerJuego();
                reproductorSonidos.detener();
                timer.stop();
                gameOver = true;
                pantalla.setVisible(false);
                pantalla.detenerMusica();
                FinalPartida_Controlador.mostrar();
            }*/
        }
        pantalla.muestraPausa(tablero);
    }
}