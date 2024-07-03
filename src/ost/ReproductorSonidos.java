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

            // Obtener la ruta del directorio de trabajo actual
            String rutaProyecto = System.getProperty("user.dir");
            // Construir la ruta completa del archivo de audio
            String rutaCompleta = rutaProyecto + rutaSonido;

            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(rutaCompleta).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Para que la música se repita continuamente
            clip.start();
            System.out.println("Reproduccion iniciada");
        } catch (Exception e) {
            System.out.println("No se puede reproducir audio");
            e.printStackTrace();
        }
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
