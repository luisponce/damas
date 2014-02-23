
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase tipo de matriz GUI
 */

public class Panel extends JPanel{
    private int valorX; // esta por el momento que indica la posicion en el panel x
    private int valorY; // esta por el momento que indica la posicion en el panel y
    private Casilla ficha;
    private BufferedImage imagen;
    
    private static BufferedImage BLANCA_IMAGE;
    private static BufferedImage NEGRA_IMAGE;
    
    public Panel() {
        if(BLANCA_IMAGE == null || NEGRA_IMAGE == null){
            try {                
                BLANCA_IMAGE = ImageIO.read(new File("blanca.jpg"));
                NEGRA_IMAGE = ImageIO.read(new File("negra.jpg"));
             } catch (Exception ex) {
                  
             }
        }
    }
    
    public void changeImg(Casilla target){
        ficha = target;
        
        switch (ficha){
            case BLACK:
                imagen = NEGRA_IMAGE;
                break;
            case WHITE:
                imagen = BLANCA_IMAGE;
                break;
            default:
                break;
        }
        
//        ImageIcon imagen;
//        JLabel background;
//        switch (target){
//            case BLACK:
//                imagen = new ImageIcon ("negra.jpg");
//                background  = new JLabel();            
//                background.setIcon(imagen);
//                add(background); 
//                break;
//            case WHITE:
//                imagen = new ImageIcon ("blanca.jpg");
//                background  = new JLabel();            
//                background.setIcon(imagen);
//                add(background);
//                break;
//                /*
//            case BLACKQUEEN:
//                imagen = new ImageIcon("");
//                background  = new JLabel();            
//                background.setIcon(imagen);
//                add(background);
//                break;
//            case WHITEQUEEN: 
//                imagen = new ImageIcon("");
//                background  = new JLabel();            
//                background.setIcon(imagen);
//                add(background);
//                break;
//                */
//            default:
//                break;
//        }
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
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getSize().width, getSize().height, null);            
    }
    
}

