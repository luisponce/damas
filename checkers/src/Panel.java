
import javax.swing.JPanel;

/**
 * Clase tipo de matriz GUI
 * @author ALICIA PRATS
 */

public class Panel extends JPanel{
    private String valorArreglo; // concatenado la posicion xy en la que se ubico en la matriz
    public int valorX; // esta por el momento que indica la posicion en el panel x
    public int valorY; // esta por el momento que indica la posicion en el panel y
    
    public void setValorArreglo(String valorArreglo){
        this.valorArreglo = valorArreglo;
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
    public String getValorArreglo() {
        return valorArreglo;
    }
    
}

