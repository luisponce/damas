
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

    public class ManejadorClicks extends JPanel implements MouseListener {
        private GUI interfaz;
        private boolean selected;
        private Pos selectedPos;
        
        @SuppressWarnings("LeakingThisInConstructor")
        public ManejadorClicks(GUI interfaz) {            
            super();  

            this.interfaz = interfaz;
            this.selected = false;
            
            for (int i = 0; i<8; i++) { // le crea a cada panel de la matriz de GUI el mouseListener y lo añade a pnlTablero para que se vea
                for (int j = i%2; j<8; j+=2) {
                    interfaz.panel[i][j].addMouseListener(this);
                    interfaz.pnlTablero.add(interfaz.panel[i][j]);
               }
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent evento) { //Metodo que se necesita para el MouseListener
//            Object panelUndido = evento.getSource();
//            int enX = evento.getXOnScreen();
//            int x = evento.getX(); //dice el valor x donde se undio
//            int y = evento.getY();//dice el valor y donde se undio
//            System.out.println("undio "+ x + "  "+ y);
//            System.out.println("Este es el clicked");
            
            Panel p = (Panel) evento.getSource();
            
            if (!selected) {
                selected = true;
                selectedPos = new Pos(p.valorX, p.valorY);
            } else {
                if (p.valorX != selectedPos.getX() || p.valorY != selectedPos.getY()) {
                    GameMaster.getInstance().realizarMovimiento(selectedPos, new Pos(p.valorX, p.valorY));
                }
                selected = false;
                selectedPos = null;
            }
            
//            System.out.println(p.getValorArreglo());
        }
        
        @Override        
        public void mouseExited(MouseEvent evento) { //Se requiere para el MouseL
//            System.out.println("Este es el Exited");
        }
        @Override
        public void mousePressed(MouseEvent evento) { //Se requiere para el MouseL
            //evento.paramString();
//            System.out.println("Este es el Pressed");          
            
        }
        @Override
        public void mouseEntered(MouseEvent evento) {//Se requiere para el MouseL
//            System.out.println("Este es el Entered");
            
        }
        @Override
        public void mouseReleased(MouseEvent evento) {//Se require para el MouseL
//            System.out.println("Este es e Released");            
        }
    }
