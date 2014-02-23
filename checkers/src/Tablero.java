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
            int dirX = posF.getX()-posI.getX();
            int dirY = posF.getY()-posI.getY();
            board[posI.getY()+dirY][posI.getX()+dirX] = Casilla.EMPTY;
            str = "C " + str;
            GameMaster.getInstance().AddLog(str);
        } else {
            GameMaster.getInstance().AddLog(str);
            GameMaster.getInstance().TerminarTurno();
        }
        GameMaster.getInstance().getGUI().actualizarBoard(GameMaster.getInstance().getBoard()); //actualiza panel para mostrar
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
        if(board[posF.getY()][posF.getX()]!=Casilla.EMPTY) return false;
        
        Casilla ficha = board[posI.getY()][posI.getX()];
        
        //si no es mi ficha o no va en la direccion adecuada
        if (esTurnoAI) {
            if(ficha == Casilla.BLACK || ficha == Casilla.BLACKQUEEN){
                return false;
            } else {
                if(ficha == Casilla.WHITE && posI.getY()>=posF.getY()){
                    return false;
                }
            }
        } else {
            if(ficha == Casilla.WHITE || ficha == Casilla.WHITEQUEEN){
                return false;
            } else {
                if(ficha == Casilla.BLACK && posI.getY()<=posF.getY()){
                    return false;
                }
            }
        }
        
        if(posF.getY() == posI.getY() || posF.getX()==posI.getX()){
            return false;
        }
        
        //validar si se movio o si comio
        int diffY = Math.abs(posF.getY()-posI.getY());
        int diffX = Math.abs(posF.getX()-posI.getX());
        if(diffY > 2){
            return false;
        } else {
            if (diffY == 2) { //si comio
                if (diffX != 2 || diffY != 2) {
                    return false;
                }
                
                int dirX = posF.getX()-posI.getX();
                int dirY = posF.getY()-posI.getY();
                Casilla mid;
                if (dirX>0){//derecha
                    if(dirY<0){//derecha-arriba
                        mid = board[posI.getY()-1][posI.getX()+1];
                    } else { //derecha-abajo
                        mid = board[posI.getY()+1][posI.getX()+1];
                    }
                } else {//izq
                    if(dirY<0){//izq-arriba
                        mid = board[posI.getY()+1][posI.getX()+1];
                    } else { //izq-abajo
                        mid = board[posI.getY()-1][posI.getX()-1];
                    }
                }
                if (mid == board[posI.getY()][posI.getX()]) return false;
                if(mid == Casilla.EMPTY) return false;
            } else {//si movio
                if (diffX != 1 || diffY != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
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
    public Casilla getBoard(int i, int j) {
        return board[i][j];
    }
}
