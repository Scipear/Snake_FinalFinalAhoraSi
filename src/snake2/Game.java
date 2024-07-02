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
    protected static boolean gameOver;
    private boolean partidaIniciada;
    private ReproductorSonidos reproductorSonidos = new ReproductorSonidos();

    public Game() {
        
        gameOver = false;
        usuario = Login_Controlador.getNombreUsuario();
        int modoOpcion = Controlador_MenuPrinc.getModoJuego();
        switch (modoOpcion) {
            case 1 -> skin = Lobbie_Controlador.getSkinSeleccionada();
            case 2 -> skin = HostLobbie_Controlador.getSkinSeleccionada();
            case 3 -> skin = ClienteLobbie_Controlador.getSkinSeleccionada();
        }
        iniciarPartida();
        /*mapa = Lobbie_Controlador.getMapaSeleccionado(); Se haria algo de esta manera, creo
        que podria ser dentro del switch para el caso que se eliga el modo de juego en solitario
        o sea host del lobby (o sea, que modoOpcion sea 1 o 2)
        */
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
        jugador = new Jugador(usuario, 5, 1, "Derecha", skin);
        tablero = new Tablero(jugador.getPersonaje(), 1);
        pantalla = new PantallaJuego(tablero, jugador);
        partidaIniciada = true;
        timer = new Timer(175, this);
        timer.start();
        iniciarJuego();
    }

    public static void setDelay(int delay){
        timer.setDelay(delay);
    }

    @Override
    public void run() {
        while (iniciado) {
            if (gameOver == true) {
                FinalPartida_Controlador.mostrar();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!tablero.getPausa()){
            jugador.getPersonaje().movimiento();

            if(tablero.personajeSobreComida()){
                jugador.aumentaPuntaje();
                pantalla.actualizaPuntaje(jugador);
            }

            tablero.chocaConPared();
            jugador.getPersonaje().chocaConCuerpo();
            pantalla.actualizaMapa();
            
            if(!jugador.getPersonaje().getEstado()){
                detenerJuego();
                reproductorSonidos.detener();
                timer.stop();
                gameOver = true;
                pantalla.setVisible(false);
                pantalla.detenerMusica();
                FinalPartida_Controlador.mostrar();
            }

            if(tablero.getTiempo() != 0){
                tablero.disminuyeTiempoEspecial();

            }else{
                if(!tablero.hayComidaEspecial()){
                    tablero.generarComidaEspecial();
                    tablero.desactivaRapidez();
                    jugador.getPersonaje().descongelar();
                }
                
                tablero.getComidaEspecial().actualizaTiempo();
                
                if(tablero.getComidaEspecial().getTiempoVisible() == 0){
                    tablero.borraComidaEspecial();
                }
            }
        }
        pantalla.muestraPausa(tablero);
    }
}
