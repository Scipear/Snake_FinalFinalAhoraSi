package snake2.Front;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snake2.Controles;
import snake2.Jugador;
import snake2.Tablero;

/**
* Clase parte del GUI. Hereda de la clase Pantalla y 
* es la ventana en donde se muestra la partida del juego
* 
* @version 1.0
*/

public class PantallaJuego extends Pantalla {
    private GraficoTablero mapa; //Grafico que se encarga de dibujar el tablero y todo lo que tiene encima
    private Controles controles; //Configuracion del teclado (En esta ventana) para la manipulacion del personaje
    private JLayeredPane multiPanel;
    private JPanel fondo, recuadro;
    private JLabel fondoImagen, recuadroImagen;

    /**
    *
    * @param tablero Tablero que sera dibujado en la ventana, por lo tanto, en el cual se va a realizar
    * la partida
    * @param jugador Jugador que esta jugando en el tablero
    */
    public PantallaJuego(Tablero tablero, Jugador jugador){
        super();
        controles = new Controles(tablero.getJugador().getPersonaje());
        mapa = new GraficoTablero(tablero);
        multiPanel = new JLayeredPane();
        fondo = new JPanel();
        fondoImagen = new JLabel(new ImageIcon(getClass().getResource("/Recursos/FondoTablero.png")));
        recuadro = new JPanel();
        recuadroImagen = new JLabel(new ImageIcon(getClass().getResource("/Recursos/Boton.png")));
        inicializar(jugador);
        add(multiPanel);
        addKeyListener(controles);
        setFocusable(true);
    }

    /**
     * Inicializa los valores iniciales de los componentes que van en la pantalla
     * 
     * @param jugador Jugador que esta jugando en el tablero
     * @version 1.1.3
     */
    public void inicializar(Jugador jugador){
        fondoImagen.setBounds(0, 0, 685, 662);        
        fondo.add(fondoImagen);        
        fondo.setBounds(0, 0, ancho, alto);
        recuadroImagen.setBounds(0, 0, 100, 15);   //0, 0, 100, 15
        recuadroImagen.setText(jugador.getUsuario() + ": " + Integer.toString(jugador.getPuntaje()));
        recuadroImagen.setHorizontalTextPosition(JLabel.CENTER);
        recuadro.setBounds(15, 10, 50, 15);     //15, 5, 100, 15
        recuadro.add(recuadroImagen);   
        multiPanel.setBounds(0, 0, ancho, alto);
        multiPanel.add(fondo, Integer.valueOf(0));
        multiPanel.add(mapa, Integer.valueOf(1));
        multiPanel.add(recuadro, Integer.valueOf(2));
        
    }

    /**
    * Actualiza el mapa, lo redibuja, para que cada cambio que haya en el pueda ser mostrado en pantalla
    * 
    * @version 1.0.4
    */
    public void actualizaMapa(){
        repaint();
    }

    /**
     * Actualiza en pantalla el puntaje actual del jugador
     * 
     * @param jugador Jugador al cual se le va a actualizar el punjate
     * @version 1.1.3
     */
    public void actualizaPuntaje(Jugador jugador){
        recuadro.remove(recuadroImagen);
        recuadroImagen.setText(jugador.getUsuario() + ": " + Integer.toString(jugador.getPuntaje()));
        recuadro.add(recuadroImagen);
    }

    public GraficoTablero getMapa(){
        return mapa;
    }
}
