package snake2;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

/**
 * Clase que representa las estadisticas de los puntos que los jugadores han acumulado durante
 * sus partidas
 * 
 * @version 1.3
 */
public class Estadisticas {
    private static ArrayList<Jugador> jugadores = new ArrayList<>(); //Lista que guarda los jugadores con los que el usuario ha estado en una partida
    private String usuario; //Nombre de usuario del jugador

    public Estadisticas(String usuario){
        this.usuario = usuario;
    }

    /**
     * Aniade los puntajes de una partida a la lista
     * 
     * @param nuevosJugadores usuarios que jugaron una partida
     * @version 1.3
     */
    public static void agregarPuntajes(ArrayList<Jugador> nuevosJugadores){
        for(Jugador nuevoJugador : nuevosJugadores){
            boolean jugadorExistente = false;
            for(Jugador jugador : jugadores){
                if(jugador.getUsuario().equals(nuevoJugador.getUsuario())){
                    jugador.setPuntaje(jugador.getPuntaje() + nuevoJugador.getPuntaje());
                    jugadorExistente = true;
                    break;
                }
            }
            if(!jugadorExistente){
                jugadores.add(nuevoJugador);
            }
        }
    }

    /**
     * Ordena la lista de acuerdo al puntaje de los jugadores y las convierte
     * a una cadena de texto
     * 
     * @return Los nombres y puntajes de todos los jugadores contenidos en la lista
     * @version 1.3
     */
    public String obtenerPuntajes(){
        Collections.sort(jugadores);
        StringBuilder sb = new StringBuilder();
        String puntajes;
        sb.append("Tu usuario: " + usuario + "\n\n");

        for(Jugador jugador : jugadores){
            String user = "Usuario: " + jugador.getUsuario() + "   Puntos acumulados: " + jugador.getPuntaje() + "\n";
            sb.append(user);
        }

        puntajes = sb.toString();
        return puntajes;
    }

    /**
     * Muestra un ventana con los puntajes guardados
     * 
     * @version 1.3
     */
    public void mostrarPuntajes() throws AlertException{
        if(jugadores.isEmpty()){
            throw new AlertException("No se han jugado partidas con otros jugadores");
        }
        JOptionPane.showMessageDialog(null, obtenerPuntajes());    
    }


}
