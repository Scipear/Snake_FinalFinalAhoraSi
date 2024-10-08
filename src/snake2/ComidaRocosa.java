package snake2;

import ost.ReproductorSonidos;

/**
 * Clase que representa el tipo de comida especial Roca Espacial. Hereda de la clase Comida
 *
 * @version 1.1.4
 */
public class ComidaRocosa extends Comida {  

    private final ReproductorSonidos ostSFX;
    private final String sfxOstRuta;

    public ComidaRocosa(int posX, int posY){
        super(posX, posY);

        this.sfxOstRuta = "\\src\\ost\\ComidaPiedra_SFX.mp3";
        ostSFX = new ReproductorSonidos();
        System.out.println(System.getProperty("user.dir"));
        ostSFX.reproducirSFX(sfxOstRuta);
    }

    /**
     * Aplica un efecto en el juego o al personaje cuando se es comido
     *
     * @param personaje Personaje al que se le va a aplicar el efecto de la
     * comida cuando este la coma
     * @version 1.1.4
     */
    public void hacerEfecto(Personaje personaje) {
        personaje.disminuyeCuerpo();
    }
}
