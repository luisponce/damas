
/**
 * Clase para almacenar posiciones en el tablero.
 * 
 * @author luisponcedeleon
 */
public class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        int sy = (7-y)+1;
        return "" + (x+1) + "" + sy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
