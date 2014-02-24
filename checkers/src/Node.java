
/**
 *
 * @author luisponcedeleon
 */
public class Node {
    private int alpha;
    private int beta;
    private int val;
    private Tablero board;
    private Node[] hijos;
    private Node padre;
    
    public Node Minimax(Tablero board, int lvl, boolean esTurnoAI){
        if (lvl==0 || this.hijos.length == 0){
            this.val = board.EvaluarEstado();
            return this;
        }
        
        if (esTurnoAI){
            int bestVal = Integer.MIN_VALUE;
            Node bestNode = null;
            for (Node node : hijos) {
                Node cur = Minimax(node.board, lvl - 1, false);
                if(node.val >= bestVal){
                    bestNode = cur;
                    bestVal = node.val;
                }
            }
            return bestNode;
        } else {
            int bestVal = Integer.MAX_VALUE;
            Node bestNode = null;
            for (Node node : hijos) {
                Node cur = Minimax(node.board, lvl - 1, true);
                if(node.val <= bestVal){
                    bestNode = cur;
                    bestVal = node.val;
                }
            }
            return bestNode;
        }
    }

    public Tablero getBoard() {
        return board;
    }
    
    
}
