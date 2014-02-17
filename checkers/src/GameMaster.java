

/**
 * 
 */
public class GameMaster {
    private static GameMaster instance = null;
    private Tablero board;
    private String log;
    private AI player2;
    
    public static GameMaster getInstance(){
        if (instance==null) {
            instance = new GameMaster();
        }
        return instance;
    } 
    
    private GameMaster(){
        
    }
    
    public void AddLog(String play){
        
    }
    
    public void TerminarTurno(){
        
    }
    
    public void IniciarJuego(){
        
    }
}
