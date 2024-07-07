package ost;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReproductorSonidos {

    private Player player;
    private boolean loop = false;
    private boolean isPlaying = false;
    private String rutaSonidoActual;
    FileInputStream fis;

    // Cargar y reproducir sonidos
    public void musicaDeFondo(String rutaSonido) {
        if (isPlaying) {
            return; // Si la música ya está reproduciéndose, no hacer nada
        }
        loop = true;
        rutaSonidoActual = rutaSonido;
        reproducir();
    }

    public void reproducirSFX(String rutaSonido) {
        
        rutaSonidoActual = rutaSonido;
        reproducir();

    }

    private void reproducir() {
        if (player != null) {
            player.close(); // Detener cualquier reproducción anterior
        }
        try {
            // Obtener la ruta del directorio de trabajo actual
            String rutaProyecto = System.getProperty("user.dir");
            // Construir la ruta completa del archivo de audio
            String rutaCompleta = rutaProyecto + rutaSonidoActual;

            fis = new FileInputStream(rutaCompleta);
            player = new Player(fis);
            isPlaying = true;
            new Thread(() -> {
                try {
                    do {
                        player.play();
                        if (loop) {
                            fis.close(); // Cerrar el stream actual
                            fis = new FileInputStream(rutaCompleta); // Abrir un nuevo stream
                            player = new Player(fis);
                        }
                    } while (loop);
                    System.out.println("Reproducción iniciada");
                } catch (JavaLayerException | IOException e) {
                    System.out.println("No se puede reproducir audio");
                    e.printStackTrace();
                } finally {
                    isPlaying = false;
                }
            }).start();
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("No se puede reproducir audio");
            e.printStackTrace();
        }
    }

    public void detener() {
        loop = false;
        if (player != null) {
            player.close();
        }
        isPlaying = false;
    }
}
