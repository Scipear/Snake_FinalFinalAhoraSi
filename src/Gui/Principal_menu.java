
package Gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal_menu extends JFrame {
    
    
    /**
     * Creates new form Principal_menu
     */
    public Principal_menu() {
        
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/Recursos/snakeicon.png")).getImage());
        
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_comenzar = new javax.swing.JButton();
        btn_conectar = new javax.swing.JButton();
        btn_reglas = new javax.swing.JButton();
        btn_credits = new javax.swing.JButton();
        btn_estadis = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setBackground(new java.awt.Color(16, 16, 32));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 700));

        jPanel1.setBackground(new java.awt.Color(13, 13, 32));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_comenzar.setBackground(new java.awt.Color(145, 146, 228));
        btn_comenzar.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        btn_comenzar.setForeground(new java.awt.Color(16, 16, 32));
        btn_comenzar.setText("Jugar");
        btn_comenzar.setBorder(null);
        btn_comenzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_comenzar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_comenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_comenzarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_comenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 220, 50));

        btn_conectar.setBackground(new java.awt.Color(237, 17, 101));
        btn_conectar.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        btn_conectar.setForeground(new java.awt.Color(16, 16, 32));
        btn_conectar.setText("Jugar Online");
        btn_conectar.setBorder(null);
        btn_conectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conectarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_conectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 170, 60));

        btn_reglas.setBackground(new java.awt.Color(145, 146, 228));
        btn_reglas.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        btn_reglas.setForeground(new java.awt.Color(16, 16, 32));
        btn_reglas.setText("Como Jugar");
        btn_reglas.setBorder(null);
        btn_reglas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_reglas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reglasActionPerformed(evt);
            }
        });
        jPanel1.add(btn_reglas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 160, 54));

        btn_credits.setBackground(new java.awt.Color(237, 17, 101));
        btn_credits.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        btn_credits.setForeground(new java.awt.Color(16, 16, 32));
        btn_credits.setText("Creditos");
        btn_credits.setBorder(null);
        btn_credits.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_credits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_creditsActionPerformed(evt);
            }
        });
        jPanel1.add(btn_credits, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 450, 120, 56));

        btn_estadis.setBackground(new java.awt.Color(145, 146, 228));
        btn_estadis.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 18)); // NOI18N
        btn_estadis.setForeground(new java.awt.Color(16, 16, 32));
        btn_estadis.setText("Puntajes");
        btn_estadis.setBorder(null);
        btn_estadis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_estadis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_estadisActionPerformed(evt);
            }
        });
        jPanel1.add(btn_estadis, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 120, 56));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Fondo_MenuPrincipal.jpeg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -225, 760, 1110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_comenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_comenzarActionPerformed
        controladores.Controlador_MenuPrincipal.eventLobbie();
    }//GEN-LAST:event_btn_comenzarActionPerformed

    private void btn_reglasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reglasActionPerformed
        controladores.Controlador_MenuPrincipal.eventComoJugar();
    }//GEN-LAST:event_btn_reglasActionPerformed

    private void btn_creditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_creditsActionPerformed
        controladores.Controlador_MenuPrincipal.eventCreditos();
    }//GEN-LAST:event_btn_creditsActionPerformed

    private void btn_estadisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_estadisActionPerformed
        controladores.Controlador_MenuPrincipal.eventPuntajes();
    }//GEN-LAST:event_btn_estadisActionPerformed
    
    private void btn_conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conectarActionPerformed
        controladores.Controlador_MenuPrincipal.eventConectarse();
    }//GEN-LAST:event_btn_conectarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_comenzar;
    private javax.swing.JButton btn_conectar;
    private javax.swing.JButton btn_credits;
    private javax.swing.JButton btn_estadis;
    private javax.swing.JButton btn_reglas;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables


}


