
import javax.swing.JPanel;

/**
 * Clase tipo de matriz GUI
 * @author ALICIA PRATS
 */

public class Panel {
    public JPanel panel;
    private int valorArreglo; // concatenado la posicion xy en la que se ubico en la matriz
    public int valorX; // esta por el momento que indica la posicion en el panel x
    public int valorY; // esta por el momento que indica la posicion en el panel y
    
    public void setValorArreglo(int valorArreglo){
        this.valorArreglo = valorArreglo;
    }
    public void setJPanel (JPanel panel) {
        this.panel = panel;
    }    
    public void setValorX(int x) {
        valorX = x;
    }
    public void setValorY(int y) {
        valorY = y;
    }
    public int getValorX() {
        return valorX;
    }
    public int getValorY() {
        return valorY;
    }
    public int getValorArreglo() {
        return valorArreglo;
    }
    
}

