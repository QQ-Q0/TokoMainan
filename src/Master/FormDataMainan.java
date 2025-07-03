package Master;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import koneksi.koneksi;
import MenuUtama.MenuUtama;

public class FormDataMainan extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private DecimalFormat df = new DecimalFormat("###0");
    private MenuUtama menuUtamaAsal;

    public FormDataMainan() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        kosong();
        aktif();
        datatable();
        autonumberIdMainan();
        loadComboKategori();
        loadComboSupplier();
    }

    public FormDataMainan(MenuUtama pemanggilMenu) {
        initComponents();
        setLocationRelativeTo(null); 
        this.menuUtamaAsal = pemanggilMenu;
        kosong();
        aktif();
        datatable();
        autonumberIdMainan();
        loadComboKategori();
        loadComboSupplier();
    }
    
    protected void aktif() {
        txtNamaMainan.requestFocus();
    }
    
    protected void kosong() {
        autonumberIdMainan();
        txtNamaMainan.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtStok.setText("");
        comboKategori.setSelectedIndex(0);
        comboSupplier.setSelectedIndex(0);
    }

    protected void datatable() {
        Object[] Baris = {"ID Mainan", "Nama Mainan", "Kategori", "Harga Beli", "Harga Jual", "Stok", "Supplier"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelMainan.setModel(tabmode);
        String cariitem = txtCari.getText();
        String sql = "SELECT m.id_mainan, m.nama_mainan, k.nama_kategori, m.harga_beli, m.harga_jual, m.stok, s.nama_supplier " +
                     "FROM mainan m " +
                     "LEFT JOIN kategori_mainan k ON m.id_kategori = k.id_kategori " +
                     "LEFT JOIN supplier s ON m.id_supplier = s.id_supplier " +
                      "WHERE m.id_mainan LIKE ? OR m.nama_mainan LIKE ? ORDER BY m.id_mainan ASC";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, "%" + cariitem + "%");
            stat.setString(2, "%" + cariitem + "%");
            ResultSet hasil = stat.executeQuery();
            while (hasil.next()) {
                tabmode.addRow(new Object[]{
                    hasil.getString("id_mainan"),
                    hasil.getString("nama_mainan"),
                    hasil.getString("nama_kategori"),
                    df.format(hasil.getDouble("harga_beli")),
                    df.format(hasil.getDouble("harga_jual")),
                    hasil.getInt("stok"),
                    hasil.getString("nama_supplier")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditampilkan: " + e);
        }
    }
    
    private void loadComboKategori() {
        try {
            String sql = "SELECT id_kategori, nama_kategori FROM kategori_mainan ORDER BY id_kategori ASC";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            comboKategori.removeAllItems();
            comboKategori.addItem("Pilih Kategori");
            while (hasil.next()) {
                String id = hasil.getString("id_kategori");
                String nama = hasil.getString("nama_kategori");
                comboKategori.addItem(id + " - " + nama);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal load kategori: " + e);
        }
    }

    private void loadComboSupplier() {
        try {
            String sql = "SELECT id_supplier, nama_supplier FROM supplier ORDER BY id_supplier ASC";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            comboSupplier.removeAllItems();
            comboSupplier.addItem("Pilih Supplier");
            while (hasil.next()) {
                String id = hasil.getString("id_supplier");
                String nama = hasil.getString("nama_supplier");
                comboSupplier.addItem(id + " - " + nama);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal load supplier: " + e);
        }
    }
    
    protected void autonumberIdMainan() {
    try {
        String sql = "SELECT id_mainan FROM mainan ORDER BY id_mainan DESC LIMIT 1";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        txtIdMainan.setText("MN001");     
        if (rs.next()) {
            String id_mainan_terakhir = rs.getString("id_mainan").substring(2); 
            int AN = Integer.parseInt(id_mainan_terakhir) + 1;
            String Nol = "";
            if (AN < 10) { Nol = "00"; }
            else if (AN < 100) { Nol = "0"; }
            txtIdMainan.setText("MN" + Nol + AN);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Auto Number ID Mainan Gagal: " + e);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        txtIdMainan = new javax.swing.JTextField();
        txtNamaMainan = new javax.swing.JTextField();
        txtHargaBeli = new javax.swing.JTextField();
        txtHargaJual = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        comboKategori = new javax.swing.JComboBox<>();
        comboSupplier = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelMainan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHomeDariMainan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(33, 111, 134));
        jLabel2.setText("ID MAINAN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(33, 111, 134));
        jLabel3.setText("NAMA MAINAN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(33, 111, 134));
        jLabel4.setText("KATEGORI");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(33, 111, 134));
        jLabel5.setText("HARGA BELI");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(33, 111, 134));
        jLabel6.setText("HARGA JUAL");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(33, 111, 134));
        jLabel7.setText("STOK");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 111, 134));
        jLabel8.setText("SUPPLIER");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Desain tanpa judul.png"))); // NOI18N
        btnSimpan.setBorderPainted(false);
        btnSimpan.setContentAreaFilled(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 110, 60));

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonEdit.png"))); // NOI18N
        btnEdit.setBorderPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, 120, 60));

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonDelete.png"))); // NOI18N
        btnHapus.setBorderPainted(false);
        btnHapus.setContentAreaFilled(false);
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 450, 120, 60));

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonClear.png"))); // NOI18N
        btnBatal.setBorderPainted(false);
        btnBatal.setContentAreaFilled(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, 120, 60));

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonKeluar.png"))); // NOI18N
        btnKeluar.setBorderPainted(false);
        btnKeluar.setContentAreaFilled(false);
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        jPanel1.add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 450, 120, 60));

        txtIdMainan.setEditable(false);
        jPanel1.add(txtIdMainan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 210, 30));
        jPanel1.add(txtNamaMainan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 210, 30));

        txtHargaBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaBeliActionPerformed(evt);
            }
        });
        jPanel1.add(txtHargaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 210, 30));
        jPanel1.add(txtHargaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 210, 30));
        jPanel1.add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 210, 30));

        comboKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 210, 30));

        comboSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 210, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("CARI DATA MAINAN");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, 30));

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        jPanel1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 190, 30));

        tabelMainan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelMainan.setGridColor(new java.awt.Color(0, 0, 0));
        tabelMainan.setShowGrid(true);
        tabelMainan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMainanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelMainan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 1170, 110));

        jPanel2.setBackground(new java.awt.Color(33, 111, 134));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("DATA MAINAN");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 270, 90));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHargaBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaBeliActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
    String sql = "UPDATE mainan SET nama_mainan=?, id_kategori=?, harga_beli=?, harga_jual=?, stok=?, id_supplier=? WHERE id_mainan=?";
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, txtNamaMainan.getText());
        String selectedKategoriItem = comboKategori.getSelectedItem().toString();
        String idKategori = selectedKategoriItem.split(" - ")[0];
        stat.setString(2, idKategori);
        stat.setDouble(3, Double.parseDouble(txtHargaBeli.getText()));
        stat.setDouble(4, Double.parseDouble(txtHargaJual.getText()));
        stat.setInt(5, Integer.parseInt(txtStok.getText()));   
        String selectedSupplierItem = comboSupplier.getSelectedItem().toString();
        String idSupplier = selectedSupplierItem.split(" - ")[0];
        stat.setString(6, idSupplier);
        stat.setString(7, txtIdMainan.getText());
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        kosong();
        txtIdMainan.requestFocus();
        datatable();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal mengedit data: " + e);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Harga dan Stok harus berupa angka yang valid.");
    } catch (ArrayIndexOutOfBoundsException e) {
        JOptionPane.showMessageDialog(null, "Harap pilih Kategori dan Supplier yang valid.");
    }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        this.dispose();
        if (menuUtamaAsal != null) {
            menuUtamaAsal.tampilkanPanel("Master");
            menuUtamaAsal.setVisible(true);
        } else {
            new MenuUtama().setVisible(true);
        }
    
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        datatable();
    }//GEN-LAST:event_txtCariKeyReleased

    private void tabelMainanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMainanMouseClicked
        int bar = tabelMainan.getSelectedRow();
        String id = tabelMainan.getValueAt(bar, 0).toString();
        String nama = tabelMainan.getValueAt(bar, 1).toString();
        String namaKategori = tabelMainan.getValueAt(bar, 2).toString();
        String hargaBeli = tabelMainan.getValueAt(bar, 3).toString();
        String hargaJual = tabelMainan.getValueAt(bar, 4).toString();
        String stok = tabelMainan.getValueAt(bar, 5).toString();
        String namaSupplier = tabelMainan.getValueAt(bar, 6).toString();
        txtIdMainan.setText(id);
        txtNamaMainan.setText(nama);
        txtHargaBeli.setText(hargaBeli);
        txtHargaJual.setText(hargaJual);
        txtStok.setText(stok);
        for (int i = 0; i < comboKategori.getItemCount(); i++) {
            if (comboKategori.getItemAt(i).toString().contains(namaKategori)) {
                comboKategori.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < comboSupplier.getItemCount(); i++) {
            if (comboSupplier.getItemAt(i).toString().contains(namaSupplier)) {
                comboSupplier.setSelectedIndex(i);
                break;
            }
        }
    }//GEN-LAST:event_tabelMainanMouseClicked

    private void btnHomeDariMainanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeDariMainanActionPerformed
    this.dispose();
    MenuUtama menu = new MenuUtama();
    menu.setVisible(true);
    }//GEN-LAST:event_btnHomeDariMainanActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String sql = "INSERT INTO mainan (id_mainan, nama_mainan, id_kategori, harga_beli, harga_jual, stok, id_supplier) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIdMainan.getText());
            stat.setString(2, txtNamaMainan.getText());
            String selectedKategoriItem = comboKategori.getSelectedItem().toString();
            String idKategori = selectedKategoriItem.split(" - ")[0];
            stat.setString(3, idKategori);
            String selectedSupplierItem = comboSupplier.getSelectedItem().toString();
            String idSupplier = selectedSupplierItem.split(" - ")[0];
            stat.setDouble(4, Double.parseDouble(txtHargaBeli.getText()));
            stat.setDouble(5, Double.parseDouble(txtHargaJual.getText()));
            stat.setInt(6, Integer.parseInt(txtStok.getText()));
            stat.setString(7, idSupplier);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            kosong();
            txtIdMainan.requestFocus();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Harga dan Stok harus berupa angka yang valid.");
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Harap pilih Kategori dan Supplier yang valid.");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM mainan WHERE id_mainan=?";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtIdMainan.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                txtIdMainan.requestFocus();
                datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        kosong();
    }//GEN-LAST:event_btnBatalActionPerformed

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
            java.util.logging.Logger.getLogger(FormDataMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataMainan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHomeDariMainan;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> comboKategori;
    private javax.swing.JComboBox<String> comboSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelMainan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtIdMainan;
    private javax.swing.JTextField txtNamaMainan;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
