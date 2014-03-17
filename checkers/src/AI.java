
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 */
public class AI {
    private Node root;
    private int minimaxLvl = 4;
    
    public void Play(boolean comiendo){
        GameMaster gm = GameMaster.getInstance();
        buildArbol(minimaxLvl,comiendo);
        
        if (!comiendo) {
//            System.out.println("arbol construido");

            Node move = MinimaxArbol(minimaxLvl);

            Tablero next = move.getBoard();
            gm.AddLog(move.getMoveMade());

//            next.PrintTablero();
    //        root.getHijos().get(0).getHijos().get(0).printNodo();
    //        System.out.println(root.getHijos().get(0).getBoard().EvaluarEstado());
    //        System.out.println("h_root: " + root.getHijos().get(0).getHijos().size());

    //        debugArbol(root);

            gm.setBoard(next);

//            System.out.println("minimax done");

            //TODO: if move.getmovemade empieza con C vuelva a jugar
            if (move.getMoveMade().charAt(0) == 'C') {
                Play(true);
            } else {
                gm.TerminarTurno();
            }

            
        } else {
            //esta comiendo varios en un turno
            System.out.println("comi!");
            GameMaster.getInstance().eatBlack();
            
            
            String move = GameMaster.getInstance().getLog();
            move = move.substring(move.length()-2, move.length());
            System.out.println(move);
            if (gm.getBoard().PosiblesMovimientosFichaComido(gm.getBoard(), 
                move.charAt(0) - '0', move.charAt(1) - '0', gm.isEsTurnoAI()).length == 0) {
                gm.TerminarTurno();
            } else {
                Play(true);
            }
//            gm.TerminarTurno();
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
//                System.out.println("startHijos");
                debugArbol(n);
//                System.out.println("endHijos: " + cur.getBoard().EvaluarEstado());
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
            root.ConstruirArbolComido(lvl, GameMaster.getInstance().isEsTurnoAI(),  move.charAt(0) - '0', move.charAt(1) - '0');
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
