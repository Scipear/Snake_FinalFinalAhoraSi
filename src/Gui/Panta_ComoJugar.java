package Gui;

import javax.swing.ImageIcon;

public class Panta_ComoJugar extends javax.swing.JFrame {

    /**
     * Creates new form Panta_ComoJugar
     * @param ruta
     */
    public Panta_ComoJugar() {

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
        jButton1 = new javax.swing.JButton();
        btn_regla1 = new javax.swing.JButton();
        btn_regla2 = new javax.swing.JButton();
        lbl_reglas1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 700));
        setSize(new java.awt.Dimension(700, 700));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(60, 137, 81));
        jButton1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 103, 220));
        jButton1.setText("<-- VOLVER");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 110, -1));

        btn_regla1.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        btn_regla1.setText("<-ANTERIOR");
        btn_regla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regla1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_regla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 140, 30));

        btn_regla2.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        btn_regla2.setText("SIGUIENTE->");
        btn_regla2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regla2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn_regla2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 150, 30));

        lbl_reglas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Reglas1.jpg"))); // NOI18N
        jPanel1.add(lbl_reglas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        controladores.Controlador_ComoJugar.eventVolverMenu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_regla2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regla2ActionPerformed
        controladores.Controlador_ComoJugar.eventMostrarRegla2();
    }//GEN-LAST:event_btn_regla2ActionPerformed

    private void btn_regla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regla1ActionPerformed
        controladores.Controlador_ComoJugar.eventMostrarRegla1();
    }//GEN-LAST:event_btn_regla1ActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_regla1;
    private javax.swing.JButton btn_regla2;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_reglas1;
    // End of variables declaration//GEN-END:variables
}
