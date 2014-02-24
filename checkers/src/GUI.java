

import java.awt.Color;

/**
 * Interfaz Grafica
 */
public class GUI extends javax.swing.JFrame {
    
    Panel panel[][] = new Panel [8][8];
    private ManejadorClicks manejadorClicks;
    private GameMaster gm;

    public GUI() {
        initComponents();
        crearMatriz();   
        manejadorClicks = new ManejadorClicks(this);
        
        gm = GameMaster.getInstance();
        
        gm.IniciarJuego(this);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        pnlTablero = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotificaciones = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Historial"));

        txtHistorial.setEditable(false);
        txtHistorial.setColumns(20);
        txtHistorial.setRows(5);
        jScrollPane1.setViewportView(txtHistorial);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlTablero.setBorder(new javax.swing.border.MatteBorder(null));
        pnlTablero.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout pnlTableroLayout = new javax.swing.GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        pnlTableroLayout.setVerticalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Notificaciones"));

        txtNotificaciones.setEditable(false);
        txtNotificaciones.setColumns(20);
        txtNotificaciones.setRows(5);
        jScrollPane2.setViewportView(txtNotificaciones);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("Opciones");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
       
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //DEBUG!!!----------------
        //Tablero t = new Tablero();
      // t.print();
       //t.Mover(new Pos(1, 5), new Pos(0, 4));
//        t.print();
        //END DEBUG---------------
       
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
        
        
    }
    
    private void crearMatriz() { //Metodo para crear los paneles y guardarlos con sus caracteristicas en la matriz de GUI
       pnlTablero.setLayout(null);
       pnlTablero.setBackground(Color.black);
        int dimensionX = pnlTablero.getWidth();
        int dimensionY = pnlTablero.getHeight();
        float sizeX = dimensionX/8;
        float sizeY = dimensionY/8;
        int tamañoX = (int)sizeX;
        int tamañoY = (int)sizeY;
        for (int i = 1; i<=8; i++) { 
            for (int j = 1; j<=8; j++) {
                Panel jPanelNew = new Panel(); // "panel" tipo Panel para guardar en la matriz GUI
                if ((i+j) % 2 != 0) {                    
                    jPanelNew.setBackground(Color.black);             
                    jPanelNew.setEnabled(false);
                    jPanelNew.setBounds((i-1)*tamañoX,(j-1)*tamañoY,tamañoX,tamañoY);//tamañoX = tamaño horizontal; tamañoY = tamaño vertical                
                } else {
                    jPanelNew.setBackground(Color.white);
                    if (j<=3) { //Si es de las primeras 3 filas pone ficha blanca
                        jPanelNew.changeImg(Casilla.WHITE);
                        jPanelNew.setBounds((i-1)*tamañoX, (j-1)*tamañoY, tamañoX, tamañoY);
                    } else if (j>=6) { // si es de las ultimas 3 filas pone ficha negra
                        jPanelNew.changeImg(Casilla.BLACK);
                        jPanelNew.setBounds((i-1)*tamañoX, (j-1)*tamañoY, tamañoX, tamañoY);                        
                    }else { //Si no va con ficha
                        jPanelNew.setBounds((i-1)*tamañoX, (j-1)*tamañoY, tamañoX, tamañoY);
                    }
                    jPanelNew.setValorX((i-1)); //guarda el valor x donde lo ubico
                    jPanelNew.setValorY((j-1)); // guarda el valor y donde lo ubico
                    panel[j-1][i-1] = jPanelNew; //guarda el "panel" en la matriz            
                }
            }
        }
        
    }
    
    public void actualizarLog(String str){ //Agrega el moviemiento realizado
        
        txtHistorial.setText(str);
    }
    
    public void actualizarBoard(Tablero newBoard){
        for (int i = 0; i < 8; i++) {
            System.out.println(newBoard.getBoard(i, 0));
        }
        
        pnlTablero.repaint();
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("_");
                if ((i+j)%2== 0) {
                    System.out.print(newBoard.getBoard(i, j));
                    panel[i][j].changeImg(newBoard.getBoard(i,j)); 
//                    panel[i][j].repaint();
               // panel[i][j].changeImg(GameMaster.getInstance().getBoard().getBoard(i,j));   
                }                
            }
            System.out.println("");
        }
        System.out.println("");
//        mostrarActualizado();
    }
    
    public void mostrarActualizado() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j)%2==0) {
                   pnlTablero.add(panel[i][j]); 
                }
                
            }
        }
    }
    
    public void agregarNotificacion(String str){ //Agrega la nueva notificacion
        txtNotificaciones.setText(txtNotificaciones.getText() + str + "\n");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JPanel pnlTablero;
    private javax.swing.JTextArea txtHistorial;
    private javax.swing.JTextArea txtNotificaciones;
    // End of variables declaration//GEN-END:variables

    }


