
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Clase tipo de matriz GUI
 */
public class Panel extends JPanel {

    private int valorX;
    private int valorY;
    private Casilla ficha;
    private BufferedImage imagen;
    private boolean selected = false;

    private static BufferedImage BLANCA_IMAGE;
    private static BufferedImage NEGRA_IMAGE;
    private static BufferedImage BLANCA_QUEEN;
    private static BufferedImage NEGRA_QUEEN;

    public Panel() {
        if (BLANCA_IMAGE == null || NEGRA_IMAGE == null) {
            try {
                BLANCA_IMAGE = ImageIO.read(new File("blanca.png"));
                NEGRA_IMAGE = ImageIO.read(new File("negra.png"));
                BLANCA_QUEEN = ImageIO.read(new File("blancaCoronada.jpg"));
                NEGRA_QUEEN = ImageIO.read(new File("negraCoronada.png"));
            } catch (Exception ex) {

            }
        }
    }

    public void changeImg(Casilla target) {
        ficha = target;

        switch (ficha) {
            case BLACK:
                imagen = NEGRA_IMAGE;
                break;
            case WHITE:
                imagen = BLANCA_IMAGE;
                break;
            case BLACKQUEEN:
                imagen = NEGRA_QUEEN;
                break;
            case WHITEQUEEN:
                imagen = BLANCA_QUEEN;
                break;
            default:
                imagen = null;
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

    public void setSelected(boolean selected) {
        this.selected = selected;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) { // borde al seleccionado
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getSize().width, getSize().height, null);
        if (selected) {
            setBackground(Color.GREEN);
        } else {
            setBorder(null);
            setBackground(Color.WHITE);
        }
    }
}
