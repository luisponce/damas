
/**
 * 
 */
public class AI {
    private Node root;
    
    public void Play(){
        GameMaster gm = GameMaster.getInstance();
        gm.setBoard(root.Minimax(gm.getBoard(), 3, gm.isEsTurnoAI()).getBoard());
    }
}
