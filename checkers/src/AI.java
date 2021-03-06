
import java.util.logging.Level;
import java.util.logging.Logger;

public class AI {
    private Node root;
    private int minimaxLvl = 4;
    
    public void Play(boolean comiendo){
        GameMaster gm = GameMaster.getInstance();
        buildArbol(minimaxLvl, comiendo);
        
        
        
        if (!comiendo) {

            Node move = MinimaxArbol(minimaxLvl);

            Tablero next = move.getBoard();
            gm.AddLog(move.getMoveMade());

            gm.setBoard(next);

            if (move.getMoveMade().charAt(0) == 'C') {
                Play(true);
            } else {
                gm.TerminarTurno();
            }
 
        } else {
            //esta comiendo varios en un turno
            GameMaster.getInstance().eatBlack();
            
            String moveString = GameMaster.getInstance().getLog();
            moveString = moveString.substring(moveString.length()-2, moveString.length());
            
            if (gm.getBoard().PosiblesMovimientosFichaComido(gm.getBoard(), 
                moveString.charAt(0) - '0'-1, 8 - (moveString.charAt(1) - '0'), true).length == 0) {
                gm.TerminarTurno();
            } else {
                
                Node move = MinimaxArbol(minimaxLvl);
                
                Tablero next = move.getBoard();
                
                gm.AddLog(move.getMoveMade());
                
                gm.setBoard(next);
                
                Play(true);
            }
        }
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
                debugArbol(n);
            }
        }
    }
    
    public void buildArbol(int lvl, boolean comiendo){
        root = new Node(GameMaster.getInstance().getBoard());
        String move = GameMaster.getInstance().getLog();
        move = move.substring(move.length()-2, move.length());
        if (!comiendo) {
            root.ConstruirArbol(lvl, GameMaster.getInstance().isEsTurnoAI());
        } else {
            root.ConstruirArbolComido(lvl, GameMaster.getInstance().isEsTurnoAI(),  move.charAt(0) - '0'-1, 8-(move.charAt(1) - '0'));
        }
    }
    
    public void buildAndPrintArbol(int lvl){
        buildArbol(lvl, false);
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
