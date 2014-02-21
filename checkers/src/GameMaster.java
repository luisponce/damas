

/**
 * 
 */
public class GameMaster {
    private static GameMaster instance = null;
    private Tablero board;
    private String log;
    private AI player2;
    private boolean corono;
    private boolean esTurnoAI;
    
    public static GameMaster getInstance(){
        if (instance==null) {
            instance = new GameMaster();
        }
        return instance;
    } 
    
    private GameMaster(){
        
    }
    
    public void AddLog(String play){
        log += play;
        
    }
    
    public void TerminarTurno(){
        if (corono) {
            AddLog("R");
        }
        AddLog("\n");
        
        esTurnoAI = false;
    }
    
    public void IniciarJuego(){
        
    }
    
    public void EndLogR(){
        corono=true;
    }
}
