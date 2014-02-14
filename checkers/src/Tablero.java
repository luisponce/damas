
/**
 * Clase para representar un tablero de damas.
 */
public class Tablero {
    /**
     * Tablero de juego, de 8 por 8, con el estado de cada casilla.
     */
    private Casilla[][] board = new Casilla[8][8];
    
    /**
     * Metodo para duplicar el tablero.
     * 
     * @return Una copia del tablero.
     */
    public Tablero Duplicar(){
        
        return null;
    }
    
    /**
     * Metodo para mover una ficha de la posicion Inical a la posicion Final.
     * No valida si el movimiento es valido, solo realiza el movimiento.
     * 
     * @param posI Posicion Inicial de la ficha.
     * @param posF Posicion Final de la ficha.
     */
    public void Mover(Pos posI, Pos posF){
        
    }
    
    /**
     * Metodo para validar un movimiento desde posicion inicial a posicion final
     * 
     * @param Posicion Inicial de la ficha.
     * @param posF Posicion Final de la ficha.
     * @param esTurnoAI True si es el turno de la AI, false de lo contrario.
     * @return True si es valido, False si no lo es.
     */
    public boolean Validar(Pos posI, Pos posF, boolean esTurnoAI){
        
        return true;
    }
    
    /**
     * Metodo para evalura heuristicamente el estado del tablero.
     * 
     * @return Un numero positivo si la AI gana, negativo si el jugador gana.
     */
    private int EvaluarEstado() {
        
        return 0;
    }
}
