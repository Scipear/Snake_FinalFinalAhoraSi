/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake2.Chat;

import java.io.Serializable;

/**
 * Clase que representa los datos que seran intercambiados durante la ejecucion del chat
 * 
 * @version 1.2.1
 */
public class ChatDatos implements Serializable{
   private String ip, name, mensaje; 

    public ChatDatos(String ip, String name, String mensaje) {
        this.ip = ip;
        this.name = name;
        this.mensaje = mensaje;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ChatDatos() {
    }
}
