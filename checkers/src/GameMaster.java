
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
        player2 = new AI();
    }
    
    public void AddLog(String play){
        log += play;
        gui.actualizarLog(log);
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
    
    public void realizarMovimiento(Pos posI, Pos PosF){
        if(board.Validar(posI, PosF, esTurnoAI)){
            board.Mover(posI, PosF);
            Casilla bo = board.getBoard( posI.getY(), PosF.getX());
            bo = Casilla.EMPTY;
        } else {
            gui.agregarNotificacion("Movimiento invalido");
        }
    }
    
    public void EndLogR(){
        corono=true;
    }
    
    public Tablero getBoard() {
        return board;
    }
    
    public GUI getGUI() {
        return gui;
    }

    public void setBoard(Tablero board) {
        this.board = board;
    }

    public boolean isEsTurnoAI() {
        return esTurnoAI;
    }
    
}
