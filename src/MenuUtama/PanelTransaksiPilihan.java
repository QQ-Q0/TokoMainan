package MenuUtama;
import Transaksi.PembelianMainan;
import Transaksi.PenjualanMainan;
import Transaksi.PengelolaanStok;
import javax.swing.JOptionPane;

public class PanelTransaksiPilihan extends javax.swing.JPanel {
    private MenuUtama frameMenuUtama;
    public PanelTransaksiPilihan(MenuUtama frameUtamaDariPemanggil) {
    initComponents();
    this.frameMenuUtama = frameUtamaDariPemanggil;
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelKontenUtama = new javax.swing.JPanel();
        btnBukaPembelian = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnBukaPenjualan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnBukaPengelolaanStok = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        panelKontenUtama.setBackground(new java.awt.Color(0, 77, 100));
        panelKontenUtama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBukaPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/PembelianMainan.png"))); // NOI18N
        btnBukaPembelian.setBorderPainted(false);
        btnBukaPembelian.setContentAreaFilled(false);
        btnBukaPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBukaPembelianActionPerformed(evt);
            }
        });
        panelKontenUtama.add(btnBukaPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PEMBELIAN MAINAN");
        panelKontenUtama.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 230, 40));

        btnBukaPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/PenjualanMainan.png"))); // NOI18N
        btnBukaPenjualan.setBorderPainted(false);
        btnBukaPenjualan.setContentAreaFilled(false);
        btnBukaPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBukaPenjualanActionPerformed(evt);
            }
        });
        panelKontenUtama.add(btnBukaPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 200, 200));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PENJUALAN MAINAN");
        panelKontenUtama.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 230, 230, -1));

        btnBukaPengelolaanStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/PengelolaanStok.png"))); // NOI18N
        btnBukaPengelolaanStok.setBorderPainted(false);
        btnBukaPengelolaanStok.setContentAreaFilled(false);
        btnBukaPengelolaanStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBukaPengelolaanStokActionPerformed(evt);
            }
        });
        panelKontenUtama.add(btnBukaPengelolaanStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 200, 200));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PENGELOLAAN STOK");
        panelKontenUtama.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelKontenUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelKontenUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBukaPengelolaanStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBukaPengelolaanStokActionPerformed
    if (this.frameMenuUtama != null) {
        PengelolaanStok frmKelolaStok = new PengelolaanStok(this.frameMenuUtama); 
        frmKelolaStok.setVisible(true);
        this.frameMenuUtama.setVisible(false);
    }
    }//GEN-LAST:event_btnBukaPengelolaanStokActionPerformed

    private void btnBukaPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBukaPenjualanActionPerformed
        if (this.frameMenuUtama != null) {
        PenjualanMainan frmJual = new PenjualanMainan(this.frameMenuUtama); 
        frmJual.setVisible(true);
        this.frameMenuUtama.setVisible(false);
    }
    }//GEN-LAST:event_btnBukaPenjualanActionPerformed

    private void btnBukaPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBukaPembelianActionPerformed
        if (this.frameMenuUtama != null) {
        Transaksi.PembelianMainan frmBeli = new Transaksi.PembelianMainan(this.frameMenuUtama); 
        frmBeli.setVisible(true);
        this.frameMenuUtama.setVisible(false); 
    }
    }//GEN-LAST:event_btnBukaPembelianActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBukaPembelian;
    private javax.swing.JButton btnBukaPengelolaanStok;
    private javax.swing.JButton btnBukaPenjualan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelKontenUtama;
    // End of variables declaration//GEN-END:variables
}
