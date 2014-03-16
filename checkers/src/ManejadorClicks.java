
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
            Panel p = (Panel) evento.getSource();            
            if (!selected) {
                selected = true;
                p.setSelected(true);
                selectedPos = new Pos(p.getValorX(), p.getValorY()); 
                
                //debug
//                GameMaster.getInstance().printPosibleMoves(selectedPos);
            } else {
                GameMaster.getInstance().getGUI().panel[selectedPos.getY()][selectedPos.getX()].setSelected(false);
                if (p.getValorX() != selectedPos.getX() || p.getValorY() != selectedPos.getY()) {
                    if (GameMaster.getInstance().getUltimaPos()== null) {
                        GameMaster.getInstance().realizarMovimiento(selectedPos, new Pos(p.getValorX(), p.getValorY()));
                    } else {
                            Pos nueva = new Pos (p.getX(), p.getY());
                        if (GameMaster.getInstance().getUltimaPos().getX() == selectedPos.getX() && GameMaster.getInstance().getUltimaPos().getY() == selectedPos.getY() ) {
                             GameMaster.getInstance().realizarMovimientoComido(selectedPos, new Pos(p.getValorX(), p.getValorY()));
                        } else {
                            String str = "Movimiento inválido";
                            interfaz.agregarNotificacion(str);
                        }
                }
                }
                selected = false;
                selectedPos = null;
                p.setSelected(false);
            }
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
