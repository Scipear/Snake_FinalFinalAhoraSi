package snake2;

import controladores.Controlador_Host;
import controladores.Controlador_Lobby;
import controladores.Controlador_PreConeccion;
import ost.ReproductorSonidos;

/**
 * Clase que representa el tipo de comida especial Comida Picante Hereda de la clase Comida
 *
 * @version 1.1.4
 */
public class ComidaPicante extends Comida {

    private final ReproductorSonidos ostSFX;
    private final String sfxOstRuta;

    public ComidaPicante(int posX, int posY) {
        super(posX, posY);
        
        this.sfxOstRuta = "\\src\\ost\\ComidaPicante_SFX.mp3";
        ostSFX = new ReproductorSonidos();
        ostSFX.reproducirSFX(sfxOstRuta);
    }

    public void hacerEfecto(Personaje personaje) {
        if(Controlador_Lobby.modo == 1 || Controlador_Host.modo == 2){
            Game.setDelay(100);
            Tablero.setRapidez(true);
        }
        super.hacerEfecto(personaje);
    }
}
