
import javax.swing.JOptionPane;
//import javax.vecmath.GMatrix;


public class GameMaster {
    private static GameMaster instance = null;
    private Tablero board;
    private String log;
    private AI player2;
    private boolean corono;
    private boolean esTurnoAI;
    private GUI gui;
    private int white;
    private int black;
    private Pos ultimaPos;
    private int iteracion;
    
    /*
     * Metodo que permite tener solo una instancia de GameMaster y mediante el cual todos pueden acceder a ella
     */
    public static GameMaster getInstance(){
        if (instance==null) {
            instance = new GameMaster();
        }
        return instance;
    } 
    
    /*
     * Constructor de GameMaster, privado para que solo se pueda acceder mediante getInstance()
     */
    private GameMaster(){
        player2 = new AI();
    }
    
    /*
     * Añade evento al log
     */
    public void AddLog(String play){
        log += play;
        gui.actualizarLog(log);
    }
    
    /*
     * Finaliza el turno actual y añade los eventos necesarios en Log y Notificaciones
     */
    public void TerminarTurno(){
        ultimoNull();
        if (corono) {
            AddLog(" R");
        }
        AddLog("\n");
        
        corono = false;
        esTurnoAI = !esTurnoAI;
        if (board.PosiblesTablerosTablero(board, esTurnoAI).length == 0) {
            if (esTurnoAI) {
                MostrarGanar();
            } else {
                MostrarPerder();
            }
//            reiniciar();
        }
        if(esTurnoAI) {
            gui.agregarNotificacion("Turno del Jugador2 - Blancas");
            player2.Play(false);
        }
        else gui.agregarNotificacion("Turno del Jugador1 - Negras");
        
        
    }
    
    /*
     * Permite Iniciar el juego desde "ceros", con todos los valores en el estado inicial necesario
     */
    public void IniciarJuego(GUI gui){ 
        this.gui = gui;
        gui.setInstancia(gui);
        board = new Tablero();
        gui.getTxtHistorial().setText("");
        gui.getTxtNotificaciones().setText("");
        
        white = 12;
        black = 12;
        iteracion = 0;
        ultimaPos = null;
        esTurnoAI = false;
        log = "";
        
        gui.agregarNotificacion("Partida Iniciada");
        gui.agregarNotificacion("Turno del Jugador1");
        gui.pnlTablero.repaint();
    }
    
    /*
     * Permite realizar el movimiento deseado si es válido
     */
    public void realizarMovimiento(Pos posI, Pos PosF){        
            if(board.Validar(posI, PosF, esTurnoAI)){
                board.Mover(posI, PosF);
//                System.out.println("ultimo es: " + ultimaPos); 
//                System.out.println(board.EvaluarEstado());
//                player2.buildAndPrintArbol();
                if (GameMaster.getInstance().getBlack() == 0) {
                    MostrarPerder();
                }
                if (GameMaster.getInstance().getWhite() == 0) {
                    MostrarGanar();
                }
                if (ultimaPos != null) {//si comio
                  if (board.PosiblesMovimientosFichaComido(board, PosF.getX(), PosF.getY(), esTurnoAI).length == 0) {
                       TerminarTurno();                       
                   }
                }
            }
            else {
                gui.agregarNotificacion("Movimiento invalido");
            }
        gui.actualizarBoard(board);      
    }
    
    /**
     * Metodo para mover luego de comer ficha validando primero si este es valido
     * @param posI
     * @param PosF 
     */
    public void realizarMovimientoComido(Pos posI, Pos PosF) { 
        if (board.ValidarComido(posI, PosF, esTurnoAI)) {
            board.Mover(posI, PosF);            
            System.out.println(board.EvaluarEstado());
            if (GameMaster.getInstance().getBlack() == 0) MostrarPerder();
            if (GameMaster.getInstance().getWhite() == 0) MostrarGanar();
            if (ultimaPos != null) {//si comio
                  if (board.PosiblesMovimientosFichaComido(board, PosF.getX(), PosF.getY(), esTurnoAI).length == 0) {
                       TerminarTurno();                       
                   }
                }
        } else {
                gui.agregarNotificacion("Movimiento invalido");
        }
        gui.actualizarBoard(board);
    }
        
    /**
     * Metodo para probar los posibles movimientos de una ficha
     */
    public void printPosibleMoves(Pos pos){
        System.out.println("Moves " + pos.toString() + ":");
        Pos[] moves = board.PosiblesMovimientosFicha(board, pos.getX(), pos.getY(), esTurnoAI);
        for (Pos pos1 : moves) {
            System.out.print(pos1.toString() + " / ");
        }
        System.out.println("");
    }

    public String getLog() {
        return log;
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
        gui.actualizarBoard(board);
    }

    public boolean isEsTurnoAI() {
        return esTurnoAI;
    }
    
    public void eatBlack() {
        black = black - 1;
    }
    
    public void eatWhite() {
        white = white - 1;
    }
    
    public int getBlack() {
        return black;
    }
    
    public int getWhite() {
        return white;
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
    
    public void MostrarPerder() {
        AddLog("PERDISTE");
        JOptionPane.showMessageDialog(null, " PERDISTE :( ");
        reiniciar();
    }
    
    public void MostrarGanar() {
        AddLog("GANASTE");
        JOptionPane.showMessageDialog(null, " ! GANASTE ! ");
        reiniciar();
    }
    
    /*
     * Permite realizar un juevo nuevo
     */
    public void reiniciar() {
        IniciarJuego(gui);
        gui.actualizarBoard(board);
        gui.pnlTablero.repaint();
    }
    
}
