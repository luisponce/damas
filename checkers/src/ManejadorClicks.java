
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

    public class ManejadorClicks extends JPanel implements MouseListener {
        private GUI interfaz;
        @SuppressWarnings("LeakingThisInConstructor")
        public ManejadorClicks(GUI interfaz) {            
            super();  
            this.interfaz = interfaz;
            
            for (int i = 0; i<8; i++) {
                for (int j = 0; j<8; j++) {
                    interfaz.panel[i][j].addMouseListener(this);
                  interfaz.pnlTablero.add(interfaz.panel[i][j]);
               }
            }
        }
        
         @Override
            public void mouseClicked(MouseEvent evento) {
               int x = evento.getX();
               int y = evento.getY();
               System.out.println("undio "+ x + "  "+ y);
               System.out.println("Este es el clicked");
            }
        
        @Override
        public void mouseExited(MouseEvent evento) {
            System.out.println("Este es el Exited");
        }
        @Override
        public void mousePressed(MouseEvent evento) {
            System.out.println("Este es el Pressed");
            
        }
        @Override
        public void mouseEntered(MouseEvent evento) {
            System.out.println("Este es el Entered");
            
        }
        @Override
        public void mouseReleased(MouseEvent evento) {
            System.out.println("Este es e Released");
            
        }
    }
