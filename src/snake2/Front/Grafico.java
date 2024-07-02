package snake2.Front;

import java.awt.Image;
import javax.swing.JComponent;

/**
* Clase parte del GUI. Esta clase padre es la que se encarga de mantener
* la parte grafica del programa tales como las imagenes de cada entidad que se pueda usar
* 
* @version 1.0.1
*/

public class Grafico extends JComponent{
    /**
    * Reajusta el tamanio o resolucion de las imagenes
    * 
    * @param imagen Imagen cuyo tamanio o resolucion se quiera reajustar
    * @return Devuelve la imagen con su nuevo tamanio
    */   
    protected Image ajustarImagen(Image imagen){
        return imagen.getScaledInstance(28, 28, Image.SCALE_DEFAULT);
    } 
}
