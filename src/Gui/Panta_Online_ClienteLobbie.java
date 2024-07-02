
package Gui;

public class Panta_Online_ClienteLobbie extends javax.swing.JFrame {

    /**
     * Creates new form Panta_Online_ClienteLobbie
     */
    public Panta_Online_ClienteLobbie() {
        initComponents();
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
        btnSkinRosa = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnSkinNaranja = new javax.swing.JButton();
        btnSkinAzul = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnSkinVerde = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(13, 13, 32));

        btnSkinRosa.setBackground(new java.awt.Color(13, 13, 60));
        btnSkinRosa.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        btnSkinRosa.setForeground(new java.awt.Color(16, 16, 32));
        btnSkinRosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/PinkHeadRIGHT.png"))); // NOI18N
        btnSkinRosa.setBorder(null);
        btnSkinRosa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSkinRosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkinRosaActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(126, 45, 253));
        jButton2.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(230, 255, 79));
        jButton2.setText("INICIAR");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnSkinNaranja.setBackground(new java.awt.Color(13, 13, 60));
        btnSkinNaranja.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        btnSkinNaranja.setForeground(new java.awt.Color(16, 16, 32));
        btnSkinNaranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/YellowHeadLEFT.png"))); // NOI18N
        btnSkinNaranja.setBorder(null);
        btnSkinNaranja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSkinNaranja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkinNaranjaActionPerformed(evt);
            }
        });

        btnSkinAzul.setBackground(new java.awt.Color(13, 13, 60));
        btnSkinAzul.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        btnSkinAzul.setForeground(new java.awt.Color(16, 16, 60));
        btnSkinAzul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/BlueHeadLEFT.png"))); // NOI18N
        btnSkinAzul.setBorder(null);
        btnSkinAzul.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSkinAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkinAzulActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(230, 255, 79));
        jLabel2.setText("Seleccion de personaje");

        btnSkinVerde.setBackground(new java.awt.Color(13, 13, 60));
        btnSkinVerde.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        btnSkinVerde.setForeground(new java.awt.Color(16, 16, 32));
        btnSkinVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/GreenHeadRIGHT.png"))); // NOI18N
        btnSkinVerde.setBorder(null);
        btnSkinVerde.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSkinVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkinVerdeActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(54, 54, 133));
        jButton6.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(237, 17, 101));
        jButton6.setText("<- Atras");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(230, 255, 79));
        jLabel4.setText("Elija una skin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSkinRosa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSkinVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSkinNaranja, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSkinAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(jLabel4)))))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton6)
                .addGap(78, 78, 78)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(btnSkinVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnSkinRosa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnSkinAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSkinNaranja, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSkinRosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkinRosaActionPerformed
        controladores.ClienteLobbie_Controlador.selec_Skin2();
    }//GEN-LAST:event_btnSkinRosaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        controladores.ClienteLobbie_Controlador.eventIniciar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSkinNaranjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkinNaranjaActionPerformed
        controladores.ClienteLobbie_Controlador.selec_Skin3();
    }//GEN-LAST:event_btnSkinNaranjaActionPerformed

    private void btnSkinAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkinAzulActionPerformed
        controladores.ClienteLobbie_Controlador.selec_Skin4();
    }//GEN-LAST:event_btnSkinAzulActionPerformed

    private void btnSkinVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkinVerdeActionPerformed
        controladores.ClienteLobbie_Controlador.selec_Skin1();
    }//GEN-LAST:event_btnSkinVerdeActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        controladores.ClienteLobbie_Controlador.eventVolverAlMenu();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSkinAzul;
    private javax.swing.JButton btnSkinNaranja;
    private javax.swing.JButton btnSkinRosa;
    private javax.swing.JButton btnSkinVerde;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
