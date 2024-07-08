package snake2;

import javax.swing.JOptionPane;

/**
 * Excepcion creada para mostrar alertas o errores en forma de ventanas
 * 
 * @version 1.2.1
 */
public class AlertException extends Exception {
    private String message;

    /**
     * Constructor de la excepcion
     * @param message Lo que sera mostrado en la alerta
     */
    public AlertException(String message) {
        this.message = message;
    }

    /**
     * Muestra el mensaje de alerta
     * @version 1.2.1
     */
    public void mostrarAlerta(){
        JOptionPane.showMessageDialog(null, message);
    }
}
