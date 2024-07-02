package snake2.Front;

import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Clase parte del front. Se encarga de almacenar todas las imagenes relacionadas a la comida del juego
 * 
 * @version 1.1
 */
public class GraficoComida extends Grafico{
    private Image comidaRegular;

    public GraficoComida(){
        comidaRegular = ajustarImagen(new ImageIcon(getClass().getResource("/Recursos/ComidaRegular.png")).getImage());
    }

    public Image getComidaRegular(){
        return comidaRegular;
    }
}
