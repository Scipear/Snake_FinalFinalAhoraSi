package snake2.Front;

import java.awt.Image;
import javax.swing.ImageIcon;
import snake2.Personaje;

/**
 * Clase parte del GUI. Esta clase se encarga de mantener las imagenes correspondientes de un personaje
 * 
 * @version 1.0.2
 */
public class GraficoPersonaje extends Grafico{
    private Image cabeza[]; /*Arreglo que guarda la imagen de cada direccion a la que puede mirar la cabeza
    de la serpiente*/
    private Image cuerpo[];/*Arreglo que guarda la imagen de cada direccion a la que puede mirar el cuerpo
    de la serpiente*/
    private Image cola[];/*Arreglo que guarda la imagen de cada direccion a la que puede mirar la cola
    de la serpiente*/
    /*Cada posicion de estos arreglos guarda una imagen de una respectiva direccion
    posicion 0: arriba, posicion 1: derecha, posicion 2: abajo, posicion 3: izquierda */
    private Image curva[];/*Arreglo que guarda la imagen cada tipo de curva o doblez de la serpiente
    posicion 0: Cuando la serpiente va de derecha para abajo o de arriba para la izquierda
    posicion 1: Cuando la serpiente va de derecha para arriba o de abajo para la izquierda
    posicion 2: Cuando la serpiente va de izquierda para abajo o de arriba para la derecha
    posicion 3: Cuando la serpiente va de izquierda para arriba o de abajo para la derecha
    */

    /**
     * Constructor de la clase
     * 
     * @param personaje Personaje al que se le va asignar las imagenes
     */

    public GraficoPersonaje(Personaje personaje){
        cabeza = new Image[4];
        cuerpo = new Image[4];
        cola = new Image[4];
        curva = new Image[4];
        asignarSkin(personaje.getSkin());
    }

    public Image getCurva(int i){
        return curva[i];
    }
    
    public Image getCabeza(int i){
        return cabeza[i];
    }
    
    public Image getCuerpo(int i){
        return cuerpo[i];
    }

    public Image getCola(int i){
        return cola[i];
    }

    /**
     * Llena todos los valores de la matriz con su respectiva imagen
     * 
     * @param choise Skin que eligio el jugador para su personaje
     * @version 1.0.2
     */
    public void asignarSkin(int choise){
        switch(choise){
            case 1:{
                cabeza[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenHeadUP.png")).getImage());
                cabeza[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenHeadRIGHT.png")).getImage());
                cabeza[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenHeadDOWN.png")).getImage());
                cabeza[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenHeadLEFT.png")).getImage());
                cuerpo[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenBodyUP.png")).getImage());
                cuerpo[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenBodyRIGHT.png")).getImage());
                cuerpo[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenBodyDOWN.png")).getImage());
                cuerpo[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenBodyLEFT.png")).getImage());
                cola[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenTailUP.png")).getImage());
                cola[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenTailRIGHT.png")).getImage());
                cola[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenTailDOWN.png")).getImage());
                cola[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenTailLEFT.png")).getImage());
                curva[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenCurvaLEFT-DOWN.png")).getImage());
                curva[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenCurvaLEFT-UP.png")).getImage());
                curva[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenCurvaRIGHT-DOWN.png")).getImage());
                curva[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GreenCurvaRIGHT-UP.png")).getImage());
                break;
            }

            case 2:{
                cabeza[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkHeadUP.png")).getImage());
                cabeza[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkHeadRIGHT.png")).getImage());
                cabeza[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkHeadDOWN.png")).getImage());
                cabeza[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkHeadLEFT.png")).getImage());
                cuerpo[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkBodyUP.png")).getImage());
                cuerpo[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkBodyRIGHT.png")).getImage());
                cuerpo[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkBodyDOWN.png")).getImage());
                cuerpo[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkBodyLEFT.png")).getImage());
                cola[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkTailUP.png")).getImage());
                cola[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkTailRIGHT.png")).getImage());
                cola[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkTailDOWN.png")).getImage());
                cola[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkTailLEFT.png")).getImage());
                curva[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkCurvaLEFT-DOWN.png")).getImage());
                curva[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkCurvaLEFT-UP.png")).getImage());
                curva[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkCurvaRIGHT-DOWN.png")).getImage());
                curva[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/PinkCurvaRIGHT-UP.png")).getImage());
                break;
            }

            case 3:{
                cabeza[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowHeadUP.png")).getImage());
                cabeza[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowHeadRIGHT.png")).getImage());
                cabeza[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowHeadDOWN.png")).getImage());
                cabeza[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowHeadLEFT.png")).getImage());
                cuerpo[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowBodyUP.png")).getImage());
                cuerpo[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowBodyRIGHT.png")).getImage());
                cuerpo[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowBodyDOWN.png")).getImage());
                cuerpo[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowBodyLEFT.png")).getImage());
                cola[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowTailUP.png")).getImage());
                cola[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowTailRIGHT.png")).getImage());
                cola[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowTailDOWN.png")).getImage());
                cola[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowTailLEFT.png")).getImage());
                curva[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowCurvaLEFT-DOWN.png")).getImage());
                curva[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowCurvaLEFT-UP.png")).getImage());
                curva[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowCurvaRIGHT-DOWN.png")).getImage());
                curva[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/YellowCurvaRIGHT-UP.png")).getImage());
                break;
            }

            case 4:{
                cabeza[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueHeadUP.png")).getImage());
                cabeza[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueHeadRIGHT.png")).getImage());
                cabeza[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueHeadDOWN.png")).getImage());
                cabeza[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueHeadLEFT.png")).getImage());
                cuerpo[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueBodyUP.png")).getImage());
                cuerpo[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueBodyRIGHT.png")).getImage());
                cuerpo[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueBodyDOWN.png")).getImage());
                cuerpo[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueBodyLEFT.png")).getImage());
                cola[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueTailUP.png")).getImage());
                cola[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueTailRIGHT.png")).getImage());
                cola[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueTailDOWN.png")).getImage());
                cola[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueTailLEFT.png")).getImage());
                curva[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueCurvaLEFT-DOWN.png")).getImage());
                curva[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueCurvaLEFT-UP.png")).getImage());
                curva[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueCurvaRIGHT-DOWN.png")).getImage());
                curva[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/BlueCurvaRIGHT-UP.png")).getImage());
                break;
            }

            case 5:{
                cabeza[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthHeadUP.png")).getImage());
                cabeza[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthHeadRIGHT.png")).getImage());
                cabeza[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthHeadDOWN.png")).getImage());
                cabeza[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthHeadLEFT.png")).getImage());
                cuerpo[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthBodyUP.png")).getImage());
                cuerpo[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthBodyRIGHT.png")).getImage());
                cuerpo[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthBodyDOWN.png")).getImage());
                cuerpo[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthBodyLEFT.png")).getImage());
                cola[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthTailUP.png")).getImage());
                cola[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthTailRIGHT.png")).getImage());
                cola[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthTailDOWN.png")).getImage());
                cola[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthTailLEFT.png")).getImage());
                curva[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthCurvaLEFT-DOWN.png")).getImage());
                curva[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthCurvaLEFT-UP.png")).getImage());
                curva[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthCurvaRIGHT-DOWN.png")).getImage());
                curva[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/EarthCurvaRIGHT-UP.png")).getImage());
                break;
            }

            case 6:{
                cabeza[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedHeadUP.png")).getImage());
                cabeza[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedHeadRIGHT.png")).getImage());
                cabeza[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedHeadDOWN.png")).getImage());
                cabeza[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedHeadLEFT.png")).getImage());
                cuerpo[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedBodyUP.png")).getImage());
                cuerpo[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedBodyRIGHT.png")).getImage());
                cuerpo[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedBodyDOWN.png")).getImage());
                cuerpo[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedBodyLEFT.png")).getImage());
                cola[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedTailUP.png")).getImage());
                cola[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedTailRIGHT.png")).getImage());
                cola[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedTailDOWN.png")).getImage());
                cola[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedTailLEFT.png")).getImage());
                curva[0] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedCurvaLEFT-DOWN.png")).getImage());
                curva[1] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedCurvaLEFT-UP.png")).getImage());
                curva[2] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedCurvaRIGHT-DOWN.png")).getImage());
                curva[3] = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/GraduatedCurvaRIGHT-UP.png")).getImage());
                break;
            }
        }
    }
}
