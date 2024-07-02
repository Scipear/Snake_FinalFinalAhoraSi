package ost;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class ReproductorSonidos {

    Clip clip;
    //Cargar sonidos

    public void musicaDeFondo(String rutaSonido) {
        
        if (clip != null && clip.isRunning()) {
            return; // Si la música ya está reproduciéndose, no hacer nada
        }
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(rutaSonido));
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Para que la música se repita continuamente
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
