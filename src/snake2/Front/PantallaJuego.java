package snake2.Front;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;

import snake2.Controles;
import snake2.Jugador;
import snake2.Tablero;
import ost.ReproductorSonidos;

/**
* Clase parte del GUI. Hereda de la clase Pantalla y 
* es la ventana en donde se muestra la partida del juego
* 
* @version 1.0
*/

public class PantallaJuego extends Pantalla {
    private static ReproductorSonidos fondoOst;
    private static final String gameOstRuta = "\\src\\ost\\ARRR.mp3";
    private boolean estaEnPausa = false;
    private boolean estaEnJuego;
    private GraficoTablero mapa; //Grafico que se encarga de dibujar el tablero y todo lo que tiene encima
    private Controles controles; //Configuracion del teclado (En esta ventana) para la manipulacion del personaje
    private JLayeredPane multiPanel;
    private JPanel fondo;
    private JLabel fondoImagen, pausa;
    private ArrayList<JLabel> recuadrosImagen = new ArrayList<>();
    private ArrayList<JPanel> recuadros = new ArrayList<>();

    /**
     * Constructor
     * 
     * @param tablero Tablero que sera dibujado en la ventana, por lo tanto, en el cual se va a realizar
     * la partida
     * @param jugadores Jugadores que pertenecen a la partida
     * @param indice Numero identificador del jugador
     */
    public PantallaJuego(Tablero tablero, ArrayList<Jugador> jugadores, int indice){
        inicializar(this);
        setVisible(false);
        controles = new Controles(tablero, jugadores.get(indice).getPersonaje(), indice);
        mapa = new GraficoTablero(tablero);
        multiPanel = new JLayeredPane();
        fondo = new JPanel();
        fondoImagen = new JLabel(new ImageIcon(getClass().getResource("/Recursos/FondoTablero.png")));
        // recuadroImagen = new JLabel(new ImageIcon(getClass().getResource("/Recursos/Boton.png")));
        pausa = new JLabel(new ImageIcon(getClass().getResource("/Recursos/Pausa8.png")));
        fondoOst = new ReproductorSonidos();
        estaEnJuego = true;
        iniciarPaneles(jugadores);
        add(multiPanel);
        addKeyListener(controles);
        setFocusable(true);
        fondoOst.musicaDeFondo(gameOstRuta);
    }

    /**
     * Inicializa los valores iniciales de los componentes que van en la pantalla
     * 
     * @param jugadores Jugadores que esta jugando en el tablero
     * @version 1.1.3
     */
    public void iniciarPaneles(ArrayList<Jugador> jugadores){
        fondoImagen.setBounds(0, 0, 685, 662);        
        fondo.setBounds(0, 0, ancho, alto);
        fondo.setLayout(null);
        fondo.add(fondoImagen);
        iniciarPuntaje(jugadores);
        pausa.setBounds(80, 260, 600, 150);
        multiPanel.setBounds(0, 0, ancho, alto);
        multiPanel.add(fondo, Integer.valueOf(0));
        multiPanel.add(mapa, Integer.valueOf(1));
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
     * Actualiza los puntos de los jugadores mostrados en pantalla
     * 
     * @param jugadores Jugadores que pertenecen a la partida
     * @version 1.2
     */
    public void actualizaPuntaje(ArrayList<Jugador> jugadores){
        for(int i = 0; i < jugadores.size(); i++){
            recuadros.get(i).remove(recuadrosImagen.get(i));
            recuadrosImagen.get(i).setText(jugadores.get(i).getUsuario() + ": " + Integer.toString(jugadores.get(i).getPuntaje()));
            recuadros.get(i).add(recuadrosImagen.get(i));
        }
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

    /**
     * Inicializa los componentes que se usan para mostrar el puntaje
     * 
     * @param jugadores Jugadores pertenecientes a la partida
     * @version 1.2
     */
    public void iniciarPuntaje(ArrayList<Jugador> jugadores){
        for(int i = 0; i < jugadores.size(); i++){
            recuadros.add(new JPanel());
            recuadrosImagen.add(new JLabel(new ImageIcon(getClass().getResource("/Recursos/Boton.png"))));
            recuadrosImagen.get(i).setBounds(0, 0, 100, 15);
            recuadrosImagen.get(i).setText(jugadores.get(i).getUsuario() + ": " + Integer.toString(jugadores.get(i).getPuntaje()));
            recuadrosImagen.get(i).setHorizontalTextPosition(JLabel.CENTER);

            switch(jugadores.get(i).getNumero()){
                case 0:
                    recuadros.get(i).setBounds(15, 5, 100, 15);
                    break;

                case 1:
                    recuadros.get(i).setBounds(570,645, 100, 15);
                    break;

                case 2:
                    recuadros.get(i).setBounds(15, 645, 100, 15);
                    break;

                case 3:
                    recuadros.get(i).setBounds(570, 5, 100, 15);
                    break;       
            }
            recuadros.get(i).setLayout(null);
            recuadros.get(i).add(recuadrosImagen.get(i));
            multiPanel.add(recuadros.get(i), Integer.valueOf(2));
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

    public void detenerMusica(){
        fondoOst.detener();
    }
}
