
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase tipo de matriz GUI
 */

public class Panel extends JPanel{
    public int valorX; // esta por el momento que indica la posicion en el panel x
    public int valorY; // esta por el momento que indica la posicion en el panel y
    
    public void changeImg(Casilla target){
        ImageIcon imagen;
        JLabel background;
        switch (target){
            case BLACK:
                imagen = new ImageIcon ("negra.jpg");
                background  = new JLabel();            
                background.setIcon(imagen);
                add(background); 
                break;
            case WHITE:
                imagen = new ImageIcon ("blanca.jpg");
                background  = new JLabel();            
                background.setIcon(imagen);
                add(background);
                break;
                /*
            case BLACKQUEEN:
                imagen = new ImageIcon("");
                background  = new JLabel();            
                background.setIcon(imagen);
                add(background);
                break;
            case WHITEQUEEN: 
                imagen = new ImageIcon("");
                background  = new JLabel();            
                background.setIcon(imagen);
                add(background);
                break;
                */
            default:
                break;
        }
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
        return "" + getValorX() + "" + getValorY();
    }
    
}

