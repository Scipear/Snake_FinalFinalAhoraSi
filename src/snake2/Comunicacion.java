package snake2;

import snake2.Contenedor_Paquetes.Paquete;
/**
 * Interfaz personalizada parte del programa
 * 
 * @version 1.2.1
 */
public interface Comunicacion{
    /**
     * Metodo destinado para que clases pertenecientes a la partida del juego puedan comunicarse
     * entre servidor y clientes
     * 
     * @param paquete El paquete que se enviara
     * @version 1.2.1
     */
    void enviarServidor(Paquete paquete);
}
