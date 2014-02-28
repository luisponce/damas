
public class GameMaster {
    private static GameMaster instance = null;
    private Tablero board;
    private String log;
    private AI player2;
    private boolean corono;
    private boolean esTurnoAI;
    private GUI gui;
    private int white = 12;
    private int black = 12;
    private Pos ultimaPos;
    private int iteracion = 0;
    
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
            AddLog(" R");
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
                System.out.println("ultimo es: " + ultimaPos); 
                System.out.println(board.EvaluarEstado());
            }
            else {
                gui.agregarNotificacion("Movimiento invalido");
            }
        gui.actualizarBoard(board);
    }
    
    /**
     * Metodo para mover luego de comer ficha
     * @param posI
     * @param PosF 
     */
    public void realizarMovimientoComido(Pos posI, Pos PosF) {
         if(board.ValidarComido(posI, PosF, esTurnoAI)){
                board.Mover(posI, PosF);
                System.out.println("ultimo es: " + ultimaPos); 
                System.out.println(board.EvaluarEstado());
            }
            else {
                gui.agregarNotificacion("Movimiento invalido");
            }
         gui.actualizarBoard(board);
    }
        
    //Metodo para probar los posibles movimientos de una ficha
    public void printPosibleMoves(Pos pos){
        System.out.println("Moves " + pos.toString() + ":");
        Pos[] moves = board.PosiblesMovimientosFicha(board, pos.getX(), pos.getY(), esTurnoAI);
        for (Pos pos1 : moves) {
            System.out.print(pos1.toString() + " / ");
        }
        System.out.println("");
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
    
    public void setBlack() {
        black = black - 1;
    }
    
    public void setWhite() {
        white = white - 1;
    }
    
    public int getBlack() {
        return black;
    }
    
    public int getWhite() {
        return white;
    }    
    
    public void setIteracion() {
        iteracion++;
    }
    public void setUltimaPos(Pos ultimaP) {
        ultimaPos = ultimaP;
    }
    public void iteracionCero() {
        iteracion = 0;
    }
    
    public Pos getUltimaPos() {
        return ultimaPos;
    }
    
    public int getIteracion() {
        return iteracion;
    }
    
    public void ultimoNull() {
        ultimaPos = null; 
    }
}
