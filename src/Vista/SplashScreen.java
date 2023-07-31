package Vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class SplashScreen extends javax.swing.JFrame {

    private final Color mTransaparent;

    public SplashScreen() {
        mTransaparent = new Color(0, 0, 0, 0);
        setUndecorated(true);
        initComponents();
        setBackground(mTransaparent);
        // Iniciar el progress bar
        ProgressBarInicado();
        //Establecer el icono de la ventana
        this.setIconImage(getIconImage());
    }

    private void ProgressBarInicado() {
        // Iniciar el progress bar a partir de un Timer
        Timer mTimer = new Timer(20, (ActionEvent e) -> {
            pbCarga.setValue(pbCarga.getValue() + 1); // Valor del progess bar
            pbCarga.setBackground(Color.orange); // Fondo del progress bar que se irá rellenando
            pbCarga.setStringPainted(true); // Activar texto dentro del Progress bar
//            pbCarga.setString("Cargando... " + pbCarga.getValue() + "%"); // Texto que irá en el progress bar
        });
        mTimer.start();
    }

    // Establecer icono de programa
    public Image getIconImage() {
        Image icono;
        icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Iconos/icono.png"));  //obtener el objeto de imagen 
        return icono;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new gamm_PanelShadow.PanelShadow();
        pbCarga = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelShadow1.setBackground(new java.awt.Color(255, 255, 153));
        panelShadow1.setColorGradient(new java.awt.Color(225, 245, 196));
        panelShadow1.setGradientType(gamm_PanelShadow.PanelShadow.GradientType.VERTICAL);
        panelShadow1.setShadowOpacity(0.8F);
        panelShadow1.setShadowSize(15);
        panelShadow1.setShadowType(gamm_PanelShadow.PanelShadow.ShadowType.CENTER);

        pbCarga.setBackground(new java.awt.Color(255, 255, 255));
        pbCarga.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        pbCarga.setForeground(new java.awt.Color(255, 153, 0));
        pbCarga.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 0), 1, true));
        pbCarga.setBorderPainted(false);
        pbCarga.setMinimumSize(new java.awt.Dimension(10, 15));
        pbCarga.setPreferredSize(new java.awt.Dimension(146, 15));
        pbCarga.setString("");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icono.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Kristen ITC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(246, 16, 41));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Conversor de unidades");

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 141, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pbCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SplashScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private gamm_PanelShadow.PanelShadow panelShadow1;
    private javax.swing.JProgressBar pbCarga;
    // End of variables declaration//GEN-END:variables
}
