package snake2;

import ost.ReproductorSonidos;

/**
 * Clase que representa el tipo de comida especial Comida Congelada. Hereda de la clase Comida
 *
 * @version 1.1.4
 */
public class ComidaCongelada extends Comida{
    
    private final ReproductorSonidos ostSFX;
    private final String sfxOstRuta;

    public ComidaCongelada(int posX, int posY){
        super(posX, posY);

        this.sfxOstRuta = "\\src\\ost\\ComidaCongelada_SFX.mp3";
        ostSFX = new ReproductorSonidos();
        ostSFX.reproducirSFX(sfxOstRuta);
    }

    public void hacerEfecto(Personaje personaje) {
        personaje.setCongelado(true);
        super.hacerEfecto(personaje);
    }
}
