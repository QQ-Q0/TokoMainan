package Transaksi;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class PopUpCariPelanggan extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    public PenjualanMainan frmPenjualan = null; 

    public PopUpCariPelanggan() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void initData(PenjualanMainan framePenjualan) {
        this.frmPenjualan = framePenjualan;
        setLocationRelativeTo(framePenjualan);
        datatable();
    }
    
    protected void datatable() {
        Object[] Baris = {"ID Pelanggan", "Nama Pelanggan", "No Telepon", "Alamat"};
        tabmode = new DefaultTableModel(null, Baris);
        tblPelangganPopUp.setModel(tabmode);
        String cariitem = txtCariPelangganPopUp.getText();
        try {
            String sql = "SELECT id_pelanggan, nama_pelanggan, no_telp, alamat FROM pelanggan "
                       + "WHERE id_pelanggan LIKE ? OR nama_pelanggan LIKE ? ORDER BY id_pelanggan ASC";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, "%" + cariitem + "%");
            stat.setString(2, "%" + cariitem + "%");
            ResultSet hasil = stat.executeQuery();
            while (hasil.next()) {
                tabmode.addRow(new Object[]{
                    hasil.getString("id_pelanggan"),
                    hasil.getString("nama_pelanggan"),
                    hasil.getString("no_telp"),
                    hasil.getString("alamat")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data pelanggan gagal ditampilkan: " + e);
            e.printStackTrace();
        }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCariPelangganPopUp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPelangganPopUp = new javax.swing.JTable();
        btnPilihPelangganPopUp = new javax.swing.JButton();
        btnBatalPelangganPopUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Cari Data Pelanggan");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jLabel2.setText("Cari Pelanggan");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        txtCariPelangganPopUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariPelangganPopUpKeyReleased(evt);
            }
        });
        jPanel1.add(txtCariPelangganPopUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 130, -1));

        tblPelangganPopUp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPelangganPopUp.setGridColor(new java.awt.Color(0, 0, 0));
        tblPelangganPopUp.setShowGrid(true);
        jScrollPane1.setViewportView(tblPelangganPopUp);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 500, 110));

        btnPilihPelangganPopUp.setText("Pilih");
        btnPilihPelangganPopUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihPelangganPopUpActionPerformed(evt);
            }
        });
        jPanel1.add(btnPilihPelangganPopUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 140, -1));

        btnBatalPelangganPopUp.setText("Batal");
        btnBatalPelangganPopUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalPelangganPopUpActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatalPelangganPopUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 140, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariPelangganPopUpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariPelangganPopUpKeyReleased
        datatable();
    }//GEN-LAST:event_txtCariPelangganPopUpKeyReleased

    private void btnPilihPelangganPopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihPelangganPopUpActionPerformed
    int barisTerpilih = tblPelangganPopUp.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih pelanggan terlebih dahulu.");
            return;
        } 
        if (frmPenjualan != null) {
            frmPenjualan.idPelangganTerpilih = tblPelangganPopUp.getValueAt(barisTerpilih, 0).toString();
            frmPenjualan.namaPelangganTerpilih = tblPelangganPopUp.getValueAt(barisTerpilih, 1).toString();
            frmPenjualan.noTelpPelangganTerpilih = tblPelangganPopUp.getValueAt(barisTerpilih, 2).toString();
            if (tblPelangganPopUp.getColumnCount() > 3) {
                 frmPenjualan.alamatPelangganTerpilih = tblPelangganPopUp.getValueAt(barisTerpilih, 3) != null ? tblPelangganPopUp.getValueAt(barisTerpilih, 3).toString() : "";
            } else {
                 frmPenjualan.alamatPelangganTerpilih = "";
            }
            frmPenjualan.pelangganTerpilih();
            this.dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error: Referensi ke Form Penjualan tidak valid!");
        }
    }//GEN-LAST:event_btnPilihPelangganPopUpActionPerformed

    private void btnBatalPelangganPopUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalPelangganPopUpActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalPelangganPopUpActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(PopUpCariPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpCariPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpCariPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpCariPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpCariPelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatalPelangganPopUp;
    private javax.swing.JButton btnPilihPelangganPopUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPelangganPopUp;
    private javax.swing.JTextField txtCariPelangganPopUp;
    // End of variables declaration//GEN-END:variables
}
