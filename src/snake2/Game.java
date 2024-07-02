package snake2;

import controladores.*;
import controladores.FinalPartida_Controlador;
import controladores.HostLobbie_Controlador;
import controladores.Lobbie_Controlador;
import controladores.Login_Controlador;
import ost.ReproductorSonidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import snake2.Front.PantallaJuego;
import snake2.Jugador;
import snake2.Tablero;

public class Game implements Runnable, ActionListener {

    private volatile boolean iniciado = true;
    protected Timer timer;
    private PantallaJuego pantalla;
    private Thread thread;
    protected Jugador jugador;
    protected Tablero tablero;
    protected static boolean gameOver;
    private ReproductorSonidos reproductorSonidos = new ReproductorSonidos();

    public Game() {
        
        gameOver = false;
        String usuario = Login_Controlador.getNombreUsuario();
        int skin = 0;
        int skinOpcion = Controlador_MenuPrinc.getModoJuego();
        switch (skinOpcion) {
            case 1 -> skin = Lobbie_Controlador.getSkinSeleccionada();
            case 2 -> skin = HostLobbie_Controlador.getSkinSeleccionada();
            case 3 -> skin = ClienteLobbie_Controlador.getSkinSeleccionada();
        }
   
        jugador = new Jugador(usuario, 5, 1, "Derecha", skin); //El jugador debe iniciar en el login
        tablero = new Tablero(jugador);
        pantalla = new PantallaJuego(tablero, jugador);
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

    @Override
    public void run() {
        timer = new Timer(175, this);
        timer.start();
        while (iniciado) {

            if (gameOver == true) {
                FinalPartida_Controlador.mostrar();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jugador.getPersonaje().movimiento();
        if (tablero.personajeSobreComida()) {
            pantalla.actualizaPuntaje(jugador);
        }
        tablero.chocaConPared();
        jugador.getPersonaje().chocaConCuerpo();
        pantalla.actualizaMapa();
        if (!jugador.getPersonaje().getEstado()) {
            detenerJuego();
            reproductorSonidos.detener();
            timer.stop();
            gameOver = true;
            pantalla.setVisible(false);
            FinalPartida_Controlador.mostrar();
        }
    }
}
