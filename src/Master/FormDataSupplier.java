package Master;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import MenuUtama.MenuUtama;

public class FormDataSupplier extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private MenuUtama menuUtamaAsal;

    public FormDataSupplier() {
        initComponents();
        setLocationRelativeTo(null);
        kosong();
        aktif();
        datatable();
        autonumberIdSupplier();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

   public FormDataSupplier(MenuUtama pemanggilMenu) {
    initComponents();
    this.menuUtamaAsal = pemanggilMenu;
    setLocationRelativeTo(pemanggilMenu);
    kosong();
    aktif();
    datatable();
    autonumberIdSupplier();
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    protected void aktif() {
        txtNamaSupplier.requestFocus();
    }
    
    protected void kosong() {
        autonumberIdSupplier();
        txtNamaSupplier.setText("");
        txtAlamatSupplier.setText("");
        txtNoTelepon.setText("");
        txtCari.setText("");
    }
    
    protected void datatable() {
    Object[] Baris = {"ID Supplier", "Nama Supplier", "Alamat", "No Telepon"};
    tabmode = new DefaultTableModel(null, Baris);
    tableSupplier.setModel(tabmode);
    String cariItem = txtCari.getText();  
    try {
        String sql = "SELECT * FROM supplier WHERE id_supplier LIKE ? OR nama_supplier LIKE ? ORDER BY id_supplier ASC";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, "%" + cariItem + "%");
        stat.setString(2, "%" + cariItem + "%");
        ResultSet hasil = stat.executeQuery();
        while (hasil.next()) {
            tabmode.addRow(new Object[]{
                hasil.getString("id_supplier"),
                hasil.getString("nama_supplier"),
                hasil.getString("alamat"),
                hasil.getString("no_telp")
            });
        }      
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal dipanggil: " + e);
        e.printStackTrace();
    }
}
    
    protected void autonumberIdSupplier() {
    try {
        String sql = "SELECT id_supplier FROM supplier ORDER BY id_supplier DESC LIMIT 1";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        txtIdSupplier.setText("SUP001");  
        if (rs.next()) {
            String id_supplier_terakhir = rs.getString("id_supplier").substring(3); 
            int AN = Integer.parseInt(id_supplier_terakhir) + 1;
            String Nol = "";
            if (AN < 10) { Nol = "00"; }
            else if (AN < 100) { Nol = "0"; }
            txtIdSupplier.setText("SUP" + Nol + AN);
        }
    } catch (Exception e) { 
        JOptionPane.showMessageDialog(null, "Auto Number ID Supplier Gagal: " + e);
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
        txtIdSupplier = new javax.swing.JTextField();
        txtNamaSupplier = new javax.swing.JTextField();
        txtAlamatSupplier = new javax.swing.JTextField();
        txtNoTelepon = new javax.swing.JTextField();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSupplier = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnHomeDariMainan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnSimpan1 = new javax.swing.JButton();
        btnEdit1 = new javax.swing.JButton();
        btnHapus1 = new javax.swing.JButton();
        btnBatal1 = new javax.swing.JButton();
        btnKeluar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdSupplier.setEditable(false);
        jPanel1.add(txtIdSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 210, 30));
        jPanel1.add(txtNamaSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 182, 210, 30));
        jPanel1.add(txtAlamatSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 210, 30));
        jPanel1.add(txtNoTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 210, 30));

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 170, 30));

        tableSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSupplier.setGridColor(new java.awt.Color(0, 0, 0));
        tableSupplier.setShowGrid(true);
        tableSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSupplier);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 1170, 110));

        jPanel2.setBackground(new java.awt.Color(33, 111, 134));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("DATA SUPPLIER");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 270, 90));

        btnHomeDariMainan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHomeDariMainan.setForeground(new java.awt.Color(255, 255, 255));
        btnHomeDariMainan.setText("HOME");
        btnHomeDariMainan.setContentAreaFilled(false);
        btnHomeDariMainan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeDariMainanActionPerformed(evt);
            }
        });
        jPanel2.add(btnHomeDariMainan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, -1, 70));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/home-button.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 40, 90));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 90));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 111, 134));
        jLabel8.setText("ID SUPPLIER");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(33, 111, 134));
        jLabel9.setText("NAMA SUPPLIER");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(33, 111, 134));
        jLabel11.setText("ALAMAT");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(33, 111, 134));
        jLabel12.setText("TELEPON");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("CARI SUPPLIER");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, 30));

        btnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Desain tanpa judul.png"))); // NOI18N
        btnSimpan1.setBorderPainted(false);
        btnSimpan1.setContentAreaFilled(false);
        btnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 110, 60));

        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonEdit.png"))); // NOI18N
        btnEdit1.setBorderPainted(false);
        btnEdit1.setContentAreaFilled(false);
        btnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, 120, 60));

        btnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonDelete.png"))); // NOI18N
        btnHapus1.setBorderPainted(false);
        btnHapus1.setContentAreaFilled(false);
        btnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 450, 120, 60));

        btnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonClear.png"))); // NOI18N
        btnBatal1.setBorderPainted(false);
        btnBatal1.setContentAreaFilled(false);
        btnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatal1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, 120, 60));

        btnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonKeluar.png"))); // NOI18N
        btnKeluar1.setBorderPainted(false);
        btnKeluar1.setContentAreaFilled(false);
        btnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnKeluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 450, 120, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeDariMainanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeDariMainanActionPerformed
        this.dispose();
        MenuUtama menu = new MenuUtama();
        menu.setVisible(true);
    }//GEN-LAST:event_btnHomeDariMainanActionPerformed

    private void tableSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSupplierMouseClicked
        int bar = tableSupplier.getSelectedRow();
        txtIdSupplier.setText(tabmode.getValueAt(bar, 0).toString());
        txtNamaSupplier.setText(tabmode.getValueAt(bar, 1).toString());
        txtAlamatSupplier.setText(tabmode.getValueAt(bar, 2).toString());
        txtNoTelepon.setText(tabmode.getValueAt(bar, 3).toString());

    }//GEN-LAST:event_tableSupplierMouseClicked

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        datatable();
    }//GEN-LAST:event_txtCariKeyReleased

    private void btnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan1ActionPerformed
        try {
            String sql = "INSERT INTO supplier (id_supplier, nama_supplier, alamat, no_telp) VALUES (?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIdSupplier.getText());
            stat.setString(2, txtNamaSupplier.getText());
            stat.setString(3, txtAlamatSupplier.getText());
            stat.setString(4, txtNoTelepon.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            kosong();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan: " + e);
        }
    }//GEN-LAST:event_btnSimpan1ActionPerformed

    private void btnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit1ActionPerformed
        try {
            String sql = "UPDATE supplier SET nama_supplier=?, alamat=?, no_telp=? WHERE id_supplier=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtNamaSupplier.getText());
            stat.setString(2, txtAlamatSupplier.getText());
            stat.setString(3, txtNoTelepon.getText());
            stat.setString(4, txtIdSupplier.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diedit");
            kosong();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diedit: " + e);
        }
    }//GEN-LAST:event_btnEdit1ActionPerformed

    private void btnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus1ActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM supplier WHERE id_supplier=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtIdSupplier.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus: " + e);
            }
        }
    }//GEN-LAST:event_btnHapus1ActionPerformed

    private void btnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatal1ActionPerformed
        kosong();
    }//GEN-LAST:event_btnBatal1ActionPerformed

    private void btnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluar1ActionPerformed
        this.dispose();
        if (menuUtamaAsal != null) {
            menuUtamaAsal.tampilkanPanel("Master");
            menuUtamaAsal.setVisible(true);
        } else {
            new MenuUtama().setVisible(true);
        }
    }//GEN-LAST:event_btnKeluar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataSupplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal1;
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton btnHapus1;
    private javax.swing.JButton btnHomeDariMainan;
    private javax.swing.JButton btnKeluar1;
    private javax.swing.JButton btnSimpan1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSupplier;
    private javax.swing.JTextField txtAlamatSupplier;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdSupplier;
    private javax.swing.JTextField txtNamaSupplier;
    private javax.swing.JTextField txtNoTelepon;
    // End of variables declaration//GEN-END:variables
}
