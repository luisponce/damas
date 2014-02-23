/**
 * Clase para representar un tablero de damas.
 */
public class Tablero {
    /**
     * Tablero de juego, de 8 por 8, con el estado de cada casilla.
     */
    private Casilla[][] board = new Casilla[8][8];
    
    public Tablero(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = Casilla.EMPTY;
            }
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = i%2; j < 8; j+=2) {
                board[i][j] = Casilla.WHITE;
            }
        }
        
        for (int i = 5; i < 8; i++) {
            for (int j = i%2; j < 8; j+=2) {
                board[i][j] = Casilla.BLACK;
            }
        }
    }
    
    /**
     * Metodo para mover una ficha de la posicion Inical a la posicion Final.
     * No valida si el movimiento es valido, solo realiza el movimiento.
     * 
     * @param posI Posicion Inicial de la ficha.
     * @param posF Posicion Final de la ficha.
     */
    public void Mover(Pos posI, Pos posF){
        if(posF.getY() == 0 || posF.getY() == 8){ //si corono la ficha
            if (board[posI.getY()][posI.getX()]==Casilla.BLACK) {
                board[posF.getY()][posF.getX()]=Casilla.BLACKQUEEN;
            } else {
                board[posF.getY()][posF.getX()]=Casilla.WHITEQUEEN;
            }
            
            GameMaster.getInstance().EndLogR();
        } else {
            board[posF.getY()][posF.getX()] = board[posI.getY()][posI.getX()];
        }
        
        board[posI.getY()][posI.getX()] = Casilla.EMPTY;
        
        String str = posI.toString() + " " + posF.toString();
        if(Math.abs(posI.getX()-posF.getX()) > 1){ //si capturo una ficha
            str = "C " + str;
            GameMaster.getInstance().AddLog(str);
        } else {
            GameMaster.getInstance().AddLog(str);
            GameMaster.getInstance().TerminarTurno();
        }
    }
    
    /**
     * Metodo para validar un movimiento desde posicion inicial a posicion final
     * 
     * @param Posicion Inicial de la ficha.
     * @param posF Posicion Final de la ficha.
     * @param esTurnoAI True si es el turno de la AI, false de lo contrario.
     * @return True si es valido, False si no lo es.
     */
//    public boolean Validar(Pos posI, Pos posF, boolean esTurnoAI){
//        if (esTurnoAI) {
//            Casilla ficha = board[posI.getY()][posI.getX()];
//            if(ficha == Casilla.BLACK || ficha == Casilla.BLACKQUEEN){
//                return
//            }
//        }
//        
//        
//        return true;
//    }
    
    /**
     * Metodo para evalura heuristicamente el estado del tablero.
     * 
     * @return Un numero positivo si la AI gana, negativo si el jugador gana.
     */
    public int EvaluarEstado() {
        
        return 0;
    }
    
    
    //DEBUG!!!!!!
//    public void print(){
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            
//            System.out.print("\n");
//        }
//        System.out.print("\n");
//    }
}
