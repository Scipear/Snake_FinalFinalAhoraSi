package snake2.Front;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import snake2.ComidaCongelada;
import snake2.ComidaDoble;
import snake2.ComidaPicante;
import snake2.ComidaRocosa;
import snake2.Cuerpo;
import snake2.Tablero;

/**
 * Clase parte del GUI. Esta clase se encarga de mantener los graficos del tablero y a su vez 
 * dibujarlo en pantalla junto a todos los componentes contenidos en el, tales como los personajes o comida
 * 
 * @version 1.0.1
 */
public class GraficoTablero extends Grafico{
    private Tablero tablero; //Tablero que sera dibujado
    private GraficoPersonaje graficoPersonaje; //Grafico que contiene las imagenes del personaje
    private GraficoComida graficoComida;
    private Image pared;
    private Image piso;
    private final int celdaSize = 28; //Tamanio predeterminado de cada celda (pared o piso)

    /**
     * Constructor de la clase
     * 
     * @param tablero Tablero en el que se va a realizar la partida
     */
    public GraficoTablero(Tablero tablero){
        this.tablero = tablero;
        graficoPersonaje = new GraficoPersonaje(tablero.getPersonaje());
        graficoComida = new GraficoComida();
        pared = ajustarImagen(new ImageIcon("newPared.png").getImage());
        piso = ajustarImagen(new ImageIcon("newPiso.png").getImage());
        setBounds(35, 25, 616, 616);
        setLayout(null);
    }
    /**
     * Metodo que viene en la libreria de JComponent, se encarga de pintar los graficos en la pantalla.
     * Es ejecutada automaticamente por lo que no es necesario que sea llamada. Hace falta de las librerias
     * Graphics y Graphics2D para su funcionamiento
     * 
     * @version 1.0.1
     */
    
     @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g; //Casteo del parametro g
        for(int i = 0; i < tablero.getAlto(); i++){
            for(int j = 0; j < tablero.getAncho(); j++){
                if(tablero.getCelda(i, j) == "Pared"){
                    g2d.drawImage(pared, j * celdaSize, i * celdaSize, this); //Dibuja la pared en las coordenadas dadas
                }else{
                    g2d.drawImage(piso, j * celdaSize, i * celdaSize, this); //Dibuja el piso en las coordenadas dadas
                }
            }
        }
        pintarPersonaje(g2d);
        pintarComida(g2d);
        
    }
    
    /**
     * Dibuja al personaje en el tablero.
     * 
     * @param g Dibuja graficos en la pantalla
     * @version 1.0.2
     */
    public void pintarPersonaje(Graphics2D g){
        for(int i = 0; i < tablero.getPersonaje().getLongitud(); i++){ /*Como el cuerpo del personaje
            se trata de una lista, la recorre*/
            Cuerpo body = tablero.getPersonaje().getCuerpo(i); //Toma el valor de cada nodo de la lista

            switch(body.getTipo()){
                case "Cabeza":{
                    if(body.getDireccion() == "Arriba"){
                        g.drawImage(graficoPersonaje.getCabeza(0), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Derecha"){
                        g.drawImage(graficoPersonaje.getCabeza(1), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Abajo"){
                        g.drawImage(graficoPersonaje.getCabeza(2), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Izquierda"){
                        g.drawImage(graficoPersonaje.getCabeza(3), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }
                    break;
                }
                
                case "Cuerpo":{
                    if(body.getDireccion() == "Arriba"){
                        g.drawImage(graficoPersonaje.getCuerpo(0), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Derecha"){
                        g.drawImage(graficoPersonaje.getCuerpo(1), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Abajo"){
                        g.drawImage(graficoPersonaje.getCuerpo(2), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Izquierda"){
                        g.drawImage(graficoPersonaje.getCuerpo(3), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }
                    break;
                }

                case "Curva":{
                    if(body.getDireccion() == "LtoD"){
                        g.drawImage(graficoPersonaje.getCurva(0), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "LtoU"){
                        g.drawImage(graficoPersonaje.getCurva(1), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "RtoD"){
                        g.drawImage(graficoPersonaje.getCurva(2), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "RtoU"){
                        g.drawImage(graficoPersonaje.getCurva(3), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }
                    break;
                }
                
                case "Cola":{
                    if(body.getDireccion() == "Arriba"){
                        g.drawImage(graficoPersonaje.getCola(0), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Derecha"){
                        g.drawImage(graficoPersonaje.getCola(1), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Abajo"){
                        g.drawImage(graficoPersonaje.getCola(2), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }else if(body.getDireccion() == "Izquierda"){
                        g.drawImage(graficoPersonaje.getCola(3), body.getPosX() * celdaSize, body.getPosY() * celdaSize, this);
                    }
                    break;
                }
            }/*Dependiendo de que parte del cuerpo de la serpiente se trate y la
            direccion a la que este mirando, se dibuja su respectiva imagen en su respectiva posicion */
        }
    }
    /**
     * Dibuja la comida en el tablero
     * 
     * @param g Dibuja graficos en la pantalla
     * @version 1.1
     */
    void pintarComida(Graphics g){
        if(tablero.hayComida()){
            g.drawImage(graficoComida.getComidaRegular(), tablero.getComida().getPosX() * celdaSize, tablero.getComida().getPosY() * celdaSize, this);
        }

        if(tablero.hayComidaEspecial()){
            if(tablero.getComidaEspecial() instanceof ComidaDoble){
                g.drawImage(graficoComida.getComidaDoble(), tablero.getComidaEspecial().getPosX() * celdaSize, tablero.getComidaEspecial().getPosY() * celdaSize, this);
            
            }else if(tablero.getComidaEspecial() instanceof ComidaPicante){
                g.drawImage(graficoComida.getComidaPicante(), tablero.getComidaEspecial().getPosX() * celdaSize, tablero.getComidaEspecial().getPosY() * celdaSize, this);
            
            }else if(tablero.getComidaEspecial() instanceof ComidaCongelada){
                g.drawImage(graficoComida.getComidaCongelada(), tablero.getComidaEspecial().getPosX() * celdaSize, tablero.getComidaEspecial().getPosY() * celdaSize, this);
            
            }else if(tablero.getComidaEspecial() instanceof ComidaRocosa){
                g.drawImage(graficoComida.getComidaRocosa(), tablero.getComidaEspecial().getPosX() * celdaSize, tablero.getComidaEspecial().getPosY() * celdaSize, this);
            }
        }
    }

}
