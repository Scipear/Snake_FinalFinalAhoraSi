package snake2;

import controladores.*;
import ost.ReproductorSonidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import snake2.Front.PantallaJuego;
import snake2.Jugador;
import snake2.Tablero;

public class Game implements Runnable, ActionListener {

    private volatile boolean iniciado = true;
    protected static Timer timer;
    private PantallaJuego pantalla;
    private Thread thread;
    protected Jugador jugador;
    protected Tablero tablero;
    private String usuario;
    private int skin;
    protected static boolean gameOver;
    private boolean partidaIniciada;
    private ReproductorSonidos reproductorSonidos = new ReproductorSonidos();

    public Game() {
        
        gameOver = false;
        usuario = Login_Controlador.getNombreUsuario();
        skin = 0;
        int skinOpcion = Controlador_MenuPrinc.getModoJuego();
        switch (skinOpcion) {
            case 1 -> skin = Lobbie_Controlador.getSkinSeleccionada();
            case 2 -> skin = HostLobbie_Controlador.getSkinSeleccionada();
            case 3 -> skin = ClienteLobbie_Controlador.getSkinSeleccionada();
        }
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
        tablero = new Tablero(jugador.getPersonaje(), 0);
        pantalla = new PantallaJuego(tablero, jugador);
        partidaIniciada = true;
        timer = new Timer(175, this);
        timer.start();
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
