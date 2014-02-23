

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
    private GUI gui;
    
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
        
        corono = false;
        
        esTurnoAI = !esTurnoAI;
        if(esTurnoAI) gui.agregarNotificacion("Turno del Jugador2");
        else gui.agregarNotificacion("Turno del Jugador1");
    }
    
    public void IniciarJuego(GUI gui){
        board = new Tablero();
        esTurnoAI = false;
        log = "";
        this.gui = gui;
        gui.agregarNotificacion("Partida Iniciada");
        gui.agregarNotificacion("Turno del Jugador1");
    }
    
    public void EndLogR(){
        corono=true;
    }
}
