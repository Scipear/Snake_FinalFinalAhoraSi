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
    private JLabel fondoImagen, recuadroImagen, pausa;
    private boolean estaEnPausa = false;
    private boolean estaEnJuego;

    /**
    *
    * @param tablero Tablero que sera dibujado en la ventana, por lo tanto, en el cual se va a realizar
    * la partida
    * @param jugador Jugador que esta jugando en el tablero
    */
    public PantallaJuego(Tablero tablero, Jugador jugador){
        super();
        setVisible(true);
        controles = new Controles(tablero, tablero.getPersonaje());
        mapa = new GraficoTablero(tablero);
        multiPanel = new JLayeredPane();
        fondo = new JPanel();
        recuadro = new JPanel();
        fondoImagen = new JLabel(new ImageIcon("FondoTablero.png"));
        recuadroImagen = new JLabel(new ImageIcon("Boton.png"));
        pausa = new JLabel(new ImageIcon("Pausa8.png"));
        estaEnJuego = true;
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
        fondo.setBounds(0, 0, ancho, alto);
        fondo.add(fondoImagen);        
        recuadroImagen.setBounds(0, 0, 100, 15);
        recuadroImagen.setText(jugador.getUsuario() + ": " + Integer.toString(jugador.getPuntaje()));
        recuadroImagen.setHorizontalTextPosition(JLabel.CENTER);
        recuadro.setBounds(15, 5, 100, 15);
        recuadro.add(recuadroImagen); 
        pausa.setBounds(80, 260, 600, 150);
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
    
    /**
     * Muestra un mensaje de pausa en la ventana
     * 
     * @param tablero Tablero con el cual se verifica si el juego se encuentra o no en pausa
     * @version 1.1.3
     */
    public void muestraPausa(Tablero tablero){
        if(tablero.getPausa() && !estaEnPausa){
            multiPanel.add(pausa, Integer.valueOf(3));

        }else{
            multiPanel.remove(pausa);
        }
    }

    public boolean getEnJuego(){
        return estaEnJuego;
    }

    public void setEnJuego(boolean estaEnJuego){
        this.estaEnJuego = estaEnJuego;
    }

    public Controles getControles(){
        return controles;
    }

    public GraficoTablero getMapa(){
        return mapa;
    }
}
