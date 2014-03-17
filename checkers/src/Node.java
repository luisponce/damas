
import java.util.ArrayList;

/**
 *
 * @author luisponcedeleon
 */
public class Node {

    private int alpha;
    private int beta;
    private int val;
    private Tablero board;
    private ArrayList<Node> hijos;
    private Node padre;
    private String moveMade;

    public Node(Tablero board) {
        this.board = board;
        hijos = new ArrayList<>();
        moveMade = "";
    }

    public void addMove(String str) {
        moveMade += str;
    }

    public Node Minimax(int lvl, boolean esTurnoAI) {
        if (lvl == 0 || this.hijos.isEmpty()) {
            this.val = board.EvaluarEstado();
//            System.out.println("val: " + board.EvaluarEstado());
//            board.PrintTablero();
            return this;
        }

        if (esTurnoAI) {
            int bestVal = Integer.MIN_VALUE + 1;
            Node bestNode = null;
            for (Node node : hijos) {
                Node cur = node.Minimax(lvl - 1, false);
                if (cur.val >= bestVal) {
                    bestNode = cur;
                    bestVal = cur.val;
                }
            }
            this.val = bestVal;
//            System.out.println("val: " + this.val);
            return this;
        } else {
            int bestVal = Integer.MAX_VALUE - 1;
            Node bestNode = null;
            for (Node node : hijos) {
                Node cur = node.Minimax(lvl - 1, true);
                if (cur.val <= bestVal) {
                    bestNode = cur;
                    bestVal = cur.val;
                }
            }
            this.val = bestVal;
//            System.out.println("val: " + this.val);
            return this;
        }
    }

    public Tablero getBoard() {
        return board;
    }

    public void ConstruirArbol(int lvl, boolean esTurnoAI) {
        if (lvl == 0) { //si es hoja
        } else {//si no es hoja
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Tablero[] moves;
                    moves = board.PosiblesTablerosFicha(board, new Pos(i, j),
                            board.PosiblesMovimientosFicha(board, i, j, esTurnoAI));
                    if (moves.length == 0 || moves[0] == null) {
                        continue;
                    }
                    for (Tablero tablero : moves) {
                        Node cur = new Node(tablero);
                        cur.addMove(findMove(this, cur));
                        cur.ConstruirArbol(lvl - 1, !esTurnoAI);
                        hijos.add(cur);
                    }
                }
            }
        }
    }

    public void ConstruirArbolComido(int lvl, boolean esTurnoAI, int x, int y) {
        if (lvl == 0) { //si es hoja
        } else {//si no es hoja

            Tablero[] moves;
            moves = board.PosiblesTablerosFicha(board, new Pos(x, y),
                    board.PosiblesMovimientosFichaComido(board, x, y, esTurnoAI));
            if (moves.length == 0 || moves[0] == null) {
                return;
            }
            for (Tablero tablero : moves) {
                Node cur = new Node(tablero);
                cur.addMove(findMove(this, cur));
                cur.ConstruirArbol(lvl - 1, !esTurnoAI);
                hijos.add(cur);
            }

        }
    }

    public void printNodo() {
        board.PrintTablero();
    }

    //TODO metodo que con dos nodos me da un string del movimiento
    public String findMove(Node start, Node target) {
        String str = "";
        Pos positions[] = new Pos[3];
        int cont = 0;
        Tablero b1 = start.getBoard(),
                b2 = target.getBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b1.getPosInBoard(j, i) != b2.getPosInBoard(j, i)) {
                    positions[cont] = new Pos(i, j);
                    cont++;
                }
            }
        }

        if (cont > 2) {//si come
            str = "C ";
            int posEmpty = -1;
            for (int i = 0; i < 3; i++) {
                int posx, posy;
                posx = positions[i].getY();
                posy = positions[i].getX();
                if ((b1.getPosInBoard(posx, posy) == Casilla.BLACK
                        || b1.getPosInBoard(posx, posy) == Casilla.BLACKQUEEN)
                        && b2.getPosInBoard(posx, posy) == Casilla.EMPTY) {
                    posEmpty = i;
                }
                if (posEmpty != 2) {
                    if (posEmpty == 0) {
                        positions[0] = positions[2];
                    } else if (posEmpty == 1) {
                        positions[1] = positions[2];
                    }
                }
            }
        }

        Pos origin, end;
        if (b1.getPosInBoard(positions[0].getY(), positions[0].getX()) == Casilla.EMPTY) {//es la pos final
            origin = positions[1];
            end = positions[0];
        } else {//es la pos inicial
            origin = positions[0];
            end = positions[1];
        }
        str += origin.toString();
        str += " " + end.toString();

        if ((b2.getPosInBoard(end.getY(), end.getX()) == Casilla.WHITEQUEEN)
                && (b1.getPosInBoard(origin.getY(), origin.getX()) != b2.getPosInBoard(end.getY(), end.getX()))) {
            str += " R";
        }

        return str;
    }

    public ArrayList<Node> getHijos() {
        return hijos;
    }

    public int getVal() {
        return val;
    }

    public String getMoveMade() {
        return moveMade;
    }
}
