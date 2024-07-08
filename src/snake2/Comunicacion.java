package snake2;

import snake2.Contenedor_Paquetes.Paquete;
/**
 * Interfaz personalizada parte del programa
 * 
 * @version 1.2.1
 */
public interface Comunicacion{
    /**
     * Metodo destinado para que clases pertenecientes a la partida del juego puedan enviarle
     * paquetes al servidor
     * 
     * @param paquete El paquete que se enviara al servidor
     * @version 1.2.1
     */
    void enviarServidor(Paquete paquete);
}
