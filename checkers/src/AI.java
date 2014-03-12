
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 */
public class AI {
    private Node root;
    private int minimaxLvl = 4;
    
    public void Play(){
        GameMaster gm = GameMaster.getInstance();
        
        buildArbol(minimaxLvl);
        
        System.out.println("arbol construido");
        
        Node move = MinimaxArbol(minimaxLvl);
        
        Tablero next = move.getBoard();
        gm.AddLog(move.getMoveMade());
        
        next.PrintTablero();
//        root.getHijos().get(0).getHijos().get(0).printNodo();
//        System.out.println(root.getHijos().get(0).getBoard().EvaluarEstado());
//        System.out.println("h_root: " + root.getHijos().get(0).getHijos().size());
        
//        debugArbol(root);
        
        gm.setBoard(next);
        
        System.out.println("minimax done");
        
        gm.TerminarTurno();
    }
    
    public Node MinimaxArbol(int lvl){
        GameMaster gm = GameMaster.getInstance();
        if (gm.isEsTurnoAI()){
            int bestVal = Integer.MIN_VALUE+1;
            Node bestNode = null;
            for (Node node : root.getHijos()) {
                Node cur = node.Minimax(lvl - 1, false);
                if(cur.getVal() >= bestVal){
                    bestNode = cur;
                    bestVal = cur.getVal();
                }
            }
            return bestNode;
        } else {
            int bestVal = Integer.MAX_VALUE-1;
            Node bestNode = null;
            for (Node node : root.getHijos()) {
                Node cur = node.Minimax(lvl - 1, true);
                if(cur.getVal() <= bestVal){
                    bestNode = cur;
                    bestVal = cur.getVal();
                }
            }
            return bestNode;
        }
    }
    
    public void debugArbol(Node cur){
        if (cur.getHijos().isEmpty()) {
            System.out.println("hoja: " + cur.getBoard().EvaluarEstado());
        } else {
            for (Node n : cur.getHijos()) {
//                System.out.println("startHijos");
                debugArbol(n);
//                System.out.println("endHijos: " + cur.getBoard().EvaluarEstado());
            }
        }
    }
    
    public void buildArbol(int lvl){
        root = new Node(GameMaster.getInstance().getBoard());
        root.ConstrirArbol(lvl, GameMaster.getInstance().isEsTurnoAI());
    }
    
    public void buildAndPrintArbol(int lvl){
        buildArbol(lvl);
        try {
            printArbol();
        } catch (Exception ex) {
            Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printArbol() throws Exception{
        if(root == null) throw new Exception("root not defined");
        
        Node cur = root;
        
        for (int i = 0; i < 4; i++) {
            cur.printNodo();
            cur = cur.getHijos().get(0);
        }
    }
}
