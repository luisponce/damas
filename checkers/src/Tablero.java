
import javax.swing.JOptionPane;


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
    
    public Tablero(Casilla[][] board){
        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, 8);
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
        if(posF.getY() == 0 || posF.getY() == 7){ //si corono la ficha
            if (board[posI.getY()][posI.getX()]==Casilla.BLACK) {
                board[posF.getY()][posF.getX()]=Casilla.BLACKQUEEN;
            } else {
                board[posF.getY()][posF.getX()]=Casilla.WHITEQUEEN;
            }
            
            GameMaster.getInstance().EndLogR();
        } else {
            board[posF.getY()][posF.getX()] = board[posI.getY()][posI.getX()];
//            System.out.println("entre");
        }
        
        board[posI.getY()][posI.getX()] = Casilla.EMPTY;
        
//        String pI = posI.toString();
//        String pF = posF.toString();
//        int pi = Integer.parseInt(pI) + 11;
//        int pf = Integer.parseInt(pF) + 11;
//        pI = ""+pi;
//        pF = ""+pf;
        String str = posI.toString() + " " + posF.toString();
        if(Math.abs(posI.getX()-posF.getX()) > 1){ //si capturo una ficha
            int dirX = posF.getX()-posI.getX();
            int dirY = posF.getY()-posI.getY();
            //board[posI.getY()][posI.getX()] = Casilla.EMPTY;
            //board[posF.getY()-posI.getY()][posF.getX()-posI.getX()] = Casilla.EMPTY; // la que se comio
            //board[posI.getY()+dirY][posI.getX()+dirX] = Casilla.EMPTY;
            str = "C " + str + " ";
            GameMaster.getInstance().AddLog(str);
            GameMaster.getInstance().setIteracion();
            GameMaster.getInstance().setUltimaPos(posF);
            
            if (dirX>0){//derecha
                if(dirY<0){//derecha-arriba
                    board[posI.getY()-1][posI.getX()+1] = Casilla.EMPTY;
                } else { //derecha-abajo
                    board[posI.getY()+1][posI.getX()+1] = Casilla.EMPTY;
                }
            } else {//izquierda
                if(dirY<0){//izquierda-arriba
                    board[posI.getY()-1][posI.getX()-1] = Casilla.EMPTY;
                } else { //izquierda-abajo
                    board[posI.getY()+1][posI.getX()-1] = Casilla.EMPTY;
                }
            }
            if (GameMaster.getInstance().isEsTurnoAI()) {
                GameMaster.getInstance().setBlack(); 

            } else {
                GameMaster.getInstance().setWhite();

            }
            //TODO
        } else {
            GameMaster.getInstance().AddLog(str);
            GameMaster.getInstance().iteracionCero();
            GameMaster.getInstance().ultimoNull();
            GameMaster.getInstance().TerminarTurno();

        }
        GameMaster.getInstance().getGUI().actualizarBoard(this); //actualiza panel para mostrar
    }
    
    
    
    public void MoverVirtual(Pos posI, Pos posF){
        if(posF.getY() == 0 || posF.getY() == 7){ //si corono la ficha
            if (board[posI.getY()][posI.getX()]==Casilla.BLACK) {
                board[posF.getY()][posF.getX()]=Casilla.BLACKQUEEN;
            } else {
                board[posF.getY()][posF.getX()]=Casilla.WHITEQUEEN;
            }
        } else {
            board[posF.getY()][posF.getX()] = board[posI.getY()][posI.getX()];
        }
        
        board[posI.getY()][posI.getX()] = Casilla.EMPTY;
        
        if(Math.abs(posI.getX()-posF.getX()) > 1){ 
            int dirX = posF.getX()-posI.getX();
            int dirY = posF.getY()-posI.getY();
            
            if (dirX>0){//derecha
                if(dirY<0){//derecha-arriba
                    board[posI.getY()-1][posI.getX()+1] = Casilla.EMPTY;
                } else { //derecha-abajo
                    board[posI.getY()+1][posI.getX()+1] = Casilla.EMPTY;
                }
            } else {//izquierda
                if(dirY<0){//izquierda-arriba
                    board[posI.getY()-1][posI.getX()-1] = Casilla.EMPTY;
                } else { //izquierda-abajo
                    board[posI.getY()+1][posI.getX()-1] = Casilla.EMPTY;
                }
            }
        } else {
            
        }
    }
    
    public boolean ValidarComido (Pos posI, Pos posF, boolean esTurnoAI) {
        if(board[posF.getY()][posF.getX()]!=Casilla.EMPTY) return false; // si hay ficha en la posicion final, retorna falso     
        Casilla ficha = board[posI.getY()][posI.getX()];        
        if (ficha==Casilla.EMPTY) return false; //si no se selecciono ninguna ficha            
            
        //si no es mi ficha o no va en la direccion adecuada
        if (esTurnoAI) {
            if(ficha == Casilla.BLACK || ficha == Casilla.BLACKQUEEN){
                return false;
            } else {
                if(ficha == Casilla.WHITE && posI.getY()>=posF.getY()){ 
                    return false;
                }
            }
        } else { // Si es el turno de jugador1
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
        if(diffY != 2){ 
            return false;
        } else {
            if (diffX != 2 || diffY != 2) { //tiene que ser siempre 2
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
                        mid = board[posI.getY()-1][posI.getX()-1];
                    } else { //izq-abajo
                        mid = board[posI.getY()+1][posI.getX()-1];
                    }
                }
                if (mid == board[posI.getY()][posI.getX()]) return false;
                if(mid == Casilla.EMPTY) {
                  return false; // si "comio" vacio   
                } else { //borra la que se comio y la resta
                    if (dirX>0){//derecha
                        if(dirY<0){//derecha-arriba
                            board[posI.getY()-1][posI.getX()+1] = Casilla.EMPTY;
                        } else { //derecha-abajo
                            board[posI.getY()+1][posI.getX()+1] = Casilla.EMPTY;
                        }
                    } else {//izquierda
                        if(dirY<0){//izquierda-arriba
                            board[posI.getY()-1][posI.getX()-1] = Casilla.EMPTY;
                        } else { //izquierda-abajo
                            board[posI.getY()+1][posI.getX()-1] = Casilla.EMPTY;
                        }
                    }
                    if (esTurnoAI) {
                        GameMaster.getInstance().setBlack(); 
                        if (GameMaster.getInstance().getBlack() == 0) JOptionPane.showMessageDialog(null, " PERDISTE :( ");
                    } else {
                        GameMaster.getInstance().setWhite();
                        if (GameMaster.getInstance().getWhite() == 0) JOptionPane.showMessageDialog(null, "¡ GANASTE !");
                        
                    }
                }
        }
        return true;
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
        if (posF.getX()>7 || posF.getX()<0 || posF.getY()>7 || posF.getY()<0) {
            return false;
        }
        if (posI.getX()>7 || posI.getX()<0 || posI.getY()>7 || posI.getY()<0) {
            return false;
        }
        
        if (posI == posF || (posI.getX()==posF.getX() && posI.getY()==posF.getY())) {
            return false;
        }
        
        if(board[posF.getY()][posF.getX()]!=Casilla.EMPTY) return false; // si hay ficha en la posicion final, retorna falso     
        Casilla ficha = board[posI.getY()][posI.getX()];        
        if (ficha==Casilla.EMPTY) return false; //si no se selecciono ninguna ficha            
            
        //si no es mi ficha o no va en la direccion adecuada
        if (esTurnoAI) {
            if(ficha == Casilla.BLACK || ficha == Casilla.BLACKQUEEN){
                return false;
            } else {
                if(ficha == Casilla.WHITE && posI.getY()>=posF.getY()){ 
                    return false;
                }
            }
        } else { // Si es el turno de jugador1
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
                if (diffX != 2 || diffY != 2) { //tiene que ser siempre 2
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
                        mid = board[posI.getY()-1][posI.getX()-1];
                    } else { //izq-abajo
                        mid = board[posI.getY()+1][posI.getX()-1];
                    }
                }
                if (mid == board[posI.getY()][posI.getX()]) return false;
                if(mid == Casilla.EMPTY) {
                  return false; // si "comio" vacio   
                }                 
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
        int ans = 0;
        
        //sumar los bordes de *4
        for (int i = 0; i < 8; i++) { //verticales
            ans+= board[i][0].getVal()*4;
            ans+= board[i][7].getVal()*4;
        }
        for (int i = 1; i < 7; i++) {//horizontales
            ans+= board[0][i].getVal()*4;
            ans+= board[7][i].getVal()*4;
        }
        
        //sumar los bordes de *3
        for (int i = 1; i < 7; i++) {//verticales
            ans+= board[i][1].getVal()*3;
            ans+= board[i][6].getVal()*3;
        }
        for (int i = 2; i < 6; i++) {//horizontales
            ans+= board[1][i].getVal()*3;
            ans+= board[6][i].getVal()*3;
        }
        
        //sumar los bordes de *2
        for (int i = 2; i < 6; i++) {//verticales
            ans+= board[i][2].getVal()*2;
            ans+= board[i][5].getVal()*2;
        }
        for (int i = 3; i < 5; i++) {//horizontales
            ans+= board[2][i].getVal()*2;
            ans+= board[5][i].getVal()*2;
        }
        
        //sumar el centro con *1
        for (int i = 3; i < 5; i++) {
            ans+= board[i][3].getVal();
            ans+= board[i][4].getVal();
        }
        
        return ans;
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
    public Casilla getPosInBoard(int i, int j) {
        return board[i][j];
    }
    
    /**
     * Encuentra los posibles movimientos de una ficha del tablero
     * 
     * @param start tablero del que se buscae el movimiento
     * @param posX componente x de la posicion
     * @param posY componente y de la posicion
     * @param esTurnoAI si es el turno del PC
     * @return arreglo con las posiciones finales de los movimientos posibles
     */
    public Pos[] PosiblesMovimientosFicha(Tablero start, int posX, int posY, boolean esTurnoAI){
        Pos[] ans = new Pos[8];
        int count = 0;
        Pos curPos = new Pos(posX,posY);
        for (int i = posX-2; i <= posX+2; i++) {
            for (int j = posY-2; j <= posY+2; j++) {
                Pos nextPos = new Pos(i, j);
                if (Validar(curPos, nextPos, esTurnoAI)) {
                    ans[count] = nextPos;
                    count++;
                }
            }
        }
        Pos[] realAns;
        int i;
        for (i = 0; i < ans.length && ans[i]!=null; i++){}
        realAns = new Pos[i];
        System.arraycopy(ans, 0, realAns, 0, realAns.length);//copiar el arreglo ans a realAns
        return realAns;
    }
    
    /**
     * Dado un tablero, una posicion y unos movimientos retorna el arreglo de
     * tableros que derivan del tablero dado.
     * @param t tablero Inicial
     * @param start Posicion de la ficha a mover
     * @param moves Arreglo de posiciones finales de los movimientos
     * @return Los posibles tableros de los movimientos
     */
    public Tablero[] PosiblesTablerosFicha(Tablero t, Pos start, Pos[] moves){
        Tablero[] ans = new Tablero[moves.length];
        
        for (int i = 0; i < moves.length; i++) {
            ans[i] = new Tablero(board);
            ans[i].MoverVirtual(start, moves[i]);
        }
        
        return ans;
    }
    
    public void PrintTablero(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}

