
import java.util.ArrayList;


/**
 *
 * @author luisponcedeleon
 */
public class Node {
    private int alpha;
    private int beta;
    private int val;
    private Tablero board;
    private ArrayList<Node> hijos;
    private Node padre;

    public Node(Tablero board) {
        this.board = board;
        hijos = new ArrayList<>();
    }
    
    
    
    public Node Minimax(int lvl, boolean esTurnoAI){
        if (lvl==0 || this.hijos.isEmpty()){
            this.val = board.EvaluarEstado();
//            System.out.println("val: " + board.EvaluarEstado());
//            board.PrintTablero();
            return this;
        }
        
        if (esTurnoAI){
            int bestVal = Integer.MIN_VALUE+1;
            Node bestNode = null;
            for (Node node : hijos) {
                Node cur = node.Minimax(lvl - 1, false);
                if(cur.val >= bestVal){
                    bestNode = cur;
                    bestVal = cur.val;
                }
            }
            this.val = bestVal;
//            System.out.println("val: " + this.val);
            return this;
        } else {
            int bestVal = Integer.MAX_VALUE-1;
            Node bestNode = null;
            for (Node node : hijos) {
                Node cur = node.Minimax(lvl - 1, true);
                if(cur.val <= bestVal){
                    bestNode = cur;
                    bestVal = cur.val;
                }
            }
            this.val = bestVal;
//            System.out.println("val: " + this.val);
            return this;
        }
    }

    public Tablero getBoard() {
        return board;
    }
    
    public void ConstrirArbol(int lvl, boolean esTurnoAI){
        if(lvl == 0) { //si es hoja
            
        } else {//si no es hoja
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Tablero[] moves;
                    moves = board.PosiblesTablerosFicha(board, new Pos(i, j), 
                            board.PosiblesMovimientosFicha(board, i, j, esTurnoAI));
                    if (moves.length == 0 || moves[0] == null) {
                        continue;
                    }
                    for (Tablero tablero : moves) {
                        Node cur = new Node(tablero);
                        cur.ConstrirArbol(lvl-1, !esTurnoAI);
                        hijos.add(cur);
                    }
                }
            }
        }
    }
    
    public void printNodo(){
        board.PrintTablero();
    }

    public ArrayList<Node> getHijos() {
        return hijos;
    }

    public int getVal() {
        return val;
    }
    
    
}
