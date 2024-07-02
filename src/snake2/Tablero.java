package snake2;
/**
 * Clase parte del back
 * 
 * @version 1.0.1
 */
public class Tablero {
    private int ancho;
    private int alto;
    private Celda celdas[][];
    private Comida comida;
    private Jugador jugador;
    
    /**
     * Constructor de la clase
     * 
     * @param jugador Persona, jugador o usuario que va a jugar en el tablero
     */
    public Tablero(Jugador jugador){
        ancho = 22;
        alto = 22;
        this.jugador = jugador;
        celdas = new Celda[alto][ancho];
        for(int i = 0; i < alto; i++){
            for(int j = 0; j < ancho; j++){ //Inicializa los valores de cada celda
                if(i == 0 || i == alto-1 || j == 0 || j == ancho-1){ //Si esta en el borde, es una pared
                    celdas[i][j] = new Celda("Pared");
                    
                }else{
                    celdas[i][j] = new Celda("Piso");
                    
                }
            }
        
        }
        generarComida();
    }

    /**
     * Genera comida aleatoriamente en el tablero
     * 
     * @version 1.1
     */
    public void generarComida(){
        if(!hayComida()){
            int posX;
            int posY;
            do{
                posX = (int) (Math.random() * ((ancho - 2) - 1) + 1) + 1;
                posY = (int) (Math.random() * ((alto - 2) - 1) + 1) + 1;
            }while(!casillaLibre(posX, posY));

            comida = new Comida(posX, posY);
        }
    }

    /**
     * Confirma que sobre alguna casilla no este ningun personaje
     * 
     * @param x Posicion en x de la casilla
     * @param y Posicion en y de la casilla
     * @return true si en la casilla indicada no hay ningun personaje
     * @version 1.1
     */
    public boolean casillaLibre(int x, int y){
        Cuerpo cuerpo;

        for(int i = 0; i < jugador.getPersonaje().getLongitud(); i++){
            cuerpo = jugador.getPersonaje().getCuerpo(i);
            if(cuerpo.getPosX() == x && cuerpo.getPosY() == y){
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si el personaje se encuentra sobre una comida
     * 
     * @version 1.1.1
     */
    public boolean personajeSobreComida(){
        if(jugador.getPersonaje().getCuerpo(0).getPosX() == comida.getPosX() && jugador.getPersonaje().getCuerpo(0).getPosY() == comida.getPosY()){
            jugador.getPersonaje().creceCuerpo();
            jugador.setPuntaje(jugador.getPuntaje() + 1);
            comida.hacerEfecto(jugador.getPersonaje());
            comida = null;
            generarComida();
            return true;
        }
        return false;
    }
    /**
     * Verifica si la serpiente ha chocado con alguna pared
     * 
     * @version 1.1.2
     */

    public void chocaConPared(){
        if(celdas[jugador.getPersonaje().getCuerpo(0).getPosY()][jugador.getPersonaje().getCuerpo(0).getPosX()].getCelda() == "Pared"){
            jugador.getPersonaje().setEstado(false);
        }
    }

    /**
     * Verifica si hay comida regular en el tablero
     * 
     * @return true si ya hay comida regular en el tablero
     * @version 1.1
     */

    public boolean hayComida(){
        return comida != null;
    }

    public Jugador getJugador(){
        return jugador;
    }

    public int getAlto(){
        return alto;
    }

    public int getAncho(){
        return ancho;
    }
    
    public String getCelda(int fila, int columna){
        return celdas[fila][columna].getCelda();
    }

    public Comida getComida(){
        return comida;
    }
    
}
