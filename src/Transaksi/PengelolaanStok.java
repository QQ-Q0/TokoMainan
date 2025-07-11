package Transaksi;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import koneksi.koneksi;
import MenuUtama.MenuUtama;
import Login.UserID;

public class PengelolaanStok extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    public String idMainanTerpilihStok;
    public String namaMainanTerpilihStok;
    public int stokAwalMainanTerpilih;
    private MenuUtama menuUtamaAsal;

    public PengelolaanStok() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        kosong();
        aktif(); 
        autonumberIdPenyesuaian();
        jspTanggalPenyesuaian.setEditor(new JSpinner.DateEditor(jspTanggalPenyesuaian, "yyyy-MM-dd"));
        jspTanggalPenyesuaian.setValue(new Date()); 
        cmbJenisPenyesuaian.removeAllItems();
        cmbJenisPenyesuaian.addItem("Pilih Jenis Penyesuaian");
        cmbJenisPenyesuaian.addItem("Stok Masuk Tambahan");
        cmbJenisPenyesuaian.addItem("Stok Keluar (Rusak/Hilang)");
        cmbJenisPenyesuaian.addItem("Hasil Stok Opname");
    }

    public PengelolaanStok(MenuUtama pemanggilMenu) {
    initComponents();
    this.menuUtamaAsal = pemanggilMenu;
    setLocationRelativeTo(pemanggilMenu);
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    kosong(); 
    aktif(); 
    autonumberIdPenyesuaian();
    jspTanggalPenyesuaian.setEditor(new JSpinner.DateEditor(jspTanggalPenyesuaian, "yyyy-MM-dd"));
    jspTanggalPenyesuaian.setValue(new Date()); 
    cmbJenisPenyesuaian.removeAllItems();
    cmbJenisPenyesuaian.addItem("Pilih Jenis Penyesuaian");
    cmbJenisPenyesuaian.addItem("Stok Masuk Tambahan");
    cmbJenisPenyesuaian.addItem("Stok Keluar (Rusak/Hilang)");
    cmbJenisPenyesuaian.addItem("Hasil Stok Opname");
    }
    
     protected void aktif() {
        btnCariMainan.requestFocus(); 
        txtIdPenyesuaian.setEnabled(false);
        txtIdMainanPenyesuaian.setEnabled(false);
        txtNamaMainanPenyesuaian.setEnabled(false);
        txtStokSaatIni.setEnabled(false);
        lblPetunjukJumlah.setText("Pilih jenis penyesuaian untuk melihat petunjuk.");
    }
     
     protected void kosong() {
        autonumberIdPenyesuaian();
        jspTanggalPenyesuaian.setValue(new Date());
        txtIdMainanPenyesuaian.setText("");
        txtNamaMainanPenyesuaian.setText("");
        txtStokSaatIni.setText("0");
        cmbJenisPenyesuaian.setSelectedIndex(0);
        txtJumlah.setText("0"); 
        txtAlasanPenyesuaian.setText("");
        lblPetunjukJumlah.setText("Pilih jenis penyesuaian untuk melihat petunjuk.");
        btnCariMainan.requestFocus();
    }
     
     protected void autonumberIdPenyesuaian() {
        try {
            String sql = "SELECT id_penyesuaian FROM penyesuaian_stok ORDER BY id_penyesuaian DESC LIMIT 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtIdPenyesuaian.setText("PS0001");
            if (rs.next()) {
                String id_terakhir = rs.getString("id_penyesuaian").substring(2);
                int AN = Integer.parseInt(id_terakhir) + 1;
                String Nol = "";
                if (AN < 10) { Nol = "000"; }
                else if (AN < 100) { Nol = "00"; }
                else if (AN < 1000) { Nol = "0"; }
                txtIdPenyesuaian.setText("PS" + Nol + AN);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Auto Number ID Penyesuaian Gagal: " + e);
            e.printStackTrace();
        }
    }

    public void mainanUntukPenyesuaianTerpilih() {
        txtIdMainanPenyesuaian.setText(idMainanTerpilihStok);
        txtNamaMainanPenyesuaian.setText(namaMainanTerpilihStok);
        txtStokSaatIni.setText(String.valueOf(stokAwalMainanTerpilih));
        cmbJenisPenyesuaian.requestFocus();
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
        txtIdPenyesuaian = new javax.swing.JTextField();
        jspTanggalPenyesuaian = new javax.swing.JSpinner();
        txtIdMainanPenyesuaian = new javax.swing.JTextField();
        txtNamaMainanPenyesuaian = new javax.swing.JTextField();
        txtStokSaatIni = new javax.swing.JTextField();
        btnCariMainan = new javax.swing.JButton();
        cmbJenisPenyesuaian = new javax.swing.JComboBox<>();
        txtJumlah = new javax.swing.JTextField();
        lblPetunjukJumlah = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlasanPenyesuaian = new javax.swing.JTextArea();
        btnSimpanPenyesuaian = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHomeDariMainan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdPenyesuaian.setEditable(false);
        jPanel1.add(txtIdPenyesuaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 210, 30));

        jspTanggalPenyesuaian.setModel(new javax.swing.SpinnerDateModel());
        jPanel1.add(jspTanggalPenyesuaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 210, 30));

        txtIdMainanPenyesuaian.setEditable(false);
        jPanel1.add(txtIdMainanPenyesuaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 170, 30));

        txtNamaMainanPenyesuaian.setEditable(false);
        jPanel1.add(txtNamaMainanPenyesuaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 170, 30));

        txtStokSaatIni.setEditable(false);
        jPanel1.add(txtStokSaatIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 170, 30));

        btnCariMainan.setText("Cari Mainan");
        btnCariMainan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMainanActionPerformed(evt);
            }
        });
        jPanel1.add(btnCariMainan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, -1, -1));

        cmbJenisPenyesuaian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Stok Masuk Tambahan", "Stok Keluar (Rusak/Hilang)", "Hasil Stok Opname" }));
        cmbJenisPenyesuaian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJenisPenyesuaianItemStateChanged(evt);
            }
        });
        cmbJenisPenyesuaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisPenyesuaianActionPerformed(evt);
            }
        });
        jPanel1.add(cmbJenisPenyesuaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 130, 170, 30));
        jPanel1.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 180, 170, 30));
        jPanel1.add(lblPetunjukJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 220, 200, 20));

        txtAlasanPenyesuaian.setColumns(20);
        txtAlasanPenyesuaian.setRows(5);
        jScrollPane1.setViewportView(txtAlasanPenyesuaian);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 250, 170, 100));

        btnSimpanPenyesuaian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Desain tanpa judul.png"))); // NOI18N
        btnSimpanPenyesuaian.setBorderPainted(false);
        btnSimpanPenyesuaian.setContentAreaFilled(false);
        btnSimpanPenyesuaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPenyesuaianActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpanPenyesuaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 120, 60));

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonClear.png"))); // NOI18N
        btnBatal.setBorderPainted(false);
        btnBatal.setContentAreaFilled(false);
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 580, 140, 60));

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonKeluar.png"))); // NOI18N
        btnKeluar.setBorderPainted(false);
        btnKeluar.setContentAreaFilled(false);
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        jPanel1.add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 580, 140, 60));

        jPanel2.setBackground(new java.awt.Color(33, 111, 134));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("PENGELOLAAN STOK");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 290, 90));

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

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/home-button.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 40, 90));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 90));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(33, 111, 134));
        jLabel13.setText("ID PENYESUAIAN");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(33, 111, 134));
        jLabel14.setText("TGL PENYESUAIAN");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(33, 111, 134));
        jLabel22.setText("ID MAINAN");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 111, 134));
        jLabel23.setText("NAMA MAINAN");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(33, 111, 134));
        jLabel24.setText("STOK SAAT INI");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, -1, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(33, 111, 134));
        jLabel25.setText("JENIS PENYESUAIAN");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, -1, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(33, 111, 134));
        jLabel26.setText("JUMLAH");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, -1, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(33, 111, 134));
        jLabel27.setText("ALASAN");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbJenisPenyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisPenyesuaianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJenisPenyesuaianActionPerformed

    private void btnSimpanPenyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPenyesuaianActionPerformed
        if (txtIdMainanPenyesuaian.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mainan belum dipilih!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            btnCariMainan.requestFocus();
            return;
        }
        if (cmbJenisPenyesuaian.getSelectedIndex() == 0 || cmbJenisPenyesuaian.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Jenis penyesuaian belum dipilih!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            cmbJenisPenyesuaian.requestFocus();
            return;
        }
        try {
            int jumlahInput = Integer.parseInt(txtJumlah.getText());
            if (jumlahInput < 0) {
                JOptionPane.showMessageDialog(this, "Jumlah tidak boleh negatif!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                txtJumlah.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka yang valid!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            txtJumlah.requestFocus();
            return;
        }
         if (txtAlasanPenyesuaian.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Alasan penyesuaian harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            txtAlasanPenyesuaian.requestFocus();
            return;
        }
        String idPenyesuaian = txtIdPenyesuaian.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalPenyesuaian = sdf.format(jspTanggalPenyesuaian.getValue());
        String idMainan = txtIdMainanPenyesuaian.getText();
        String jenisPenyesuaian = cmbJenisPenyesuaian.getSelectedItem().toString();
        String alasan = txtAlasanPenyesuaian.getText();
        int stokSebelum = Integer.parseInt(txtStokSaatIni.getText());
        int jumlahInputDariField = Integer.parseInt(txtJumlah.getText());
        int jumlahPenyesuaianUntukDb;
        int stokSetelahUntukDb;
        if (jenisPenyesuaian.equals("Hasil Stok Opname")) {
            stokSetelahUntukDb = jumlahInputDariField;
            jumlahPenyesuaianUntukDb = stokSetelahUntukDb - stokSebelum;
        } else if (jenisPenyesuaian.equals("Stok Masuk Tambahan")) {
            jumlahPenyesuaianUntukDb = jumlahInputDariField;
            stokSetelahUntukDb = stokSebelum + jumlahPenyesuaianUntukDb;
        } else if (jenisPenyesuaian.equals("Stok Keluar (Rusak/Hilang)")) {
            jumlahPenyesuaianUntukDb = -jumlahInputDariField;
            stokSetelahUntukDb = stokSebelum + jumlahPenyesuaianUntukDb;
            if (stokSetelahUntukDb < 0) {
                JOptionPane.showMessageDialog(this, "Stok tidak bisa menjadi negatif setelah penyesuaian ("+stokSetelahUntukDb+"). Jumlah keluar terlalu besar.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                txtJumlah.requestFocus();
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Jenis penyesuaian tidak dikenal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            conn.setAutoCommit(false);
            String sqlPenyesuaian = "INSERT INTO penyesuaian_stok "
                + "(id_penyesuaian, tanggal_penyesuaian, id_mainan, jenis_penyesuaian, jumlah_sebelum, jumlah_penyesuaian, jumlah_setelah, alasan, id_user) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statPenyesuaian = conn.prepareStatement(sqlPenyesuaian);
            statPenyesuaian.setString(1, idPenyesuaian);
            statPenyesuaian.setString(2, tanggalPenyesuaian);
            statPenyesuaian.setString(3, idMainan);
            statPenyesuaian.setString(4, jenisPenyesuaian);
            statPenyesuaian.setInt(5, stokSebelum);
            statPenyesuaian.setInt(6, jumlahPenyesuaianUntukDb);
            statPenyesuaian.setInt(7, stokSetelahUntukDb);
            statPenyesuaian.setString(8, alasan);
            statPenyesuaian.setString(9, UserID.getIdKasir());
            statPenyesuaian.executeUpdate();
            String sqlUpdateStok = "UPDATE mainan SET stok = ? WHERE id_mainan = ?";
            PreparedStatement statUpdateStok = conn.prepareStatement(sqlUpdateStok);
            statUpdateStok.setInt(1, stokSetelahUntukDb);
            statUpdateStok.setString(2, idMainan);
            statUpdateStok.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(this, "Penyesuaian stok berhasil disimpan!");
            kosong();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback gagal: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Gagal menyimpan penyesuaian stok: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                System.err.println("Set AutoCommit(true) gagal: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSimpanPenyesuaianActionPerformed

    private void btnCariMainanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMainanActionPerformed
        PopUpPilihMainanStok popMainan = new PopUpPilihMainanStok();
        popMainan.initData(this); 
        popMainan.setVisible(true);
    }//GEN-LAST:event_btnCariMainanActionPerformed

    private void cmbJenisPenyesuaianItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJenisPenyesuaianItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String pilihan = "";
        if (cmbJenisPenyesuaian.getSelectedItem() != null) {
            pilihan = cmbJenisPenyesuaian.getSelectedItem().toString();
        }
        if (pilihan.equals("Hasil Stok Opname")) {
            lblPetunjukJumlah.setText("Isi dengan stok fisik baru."); 
        } else if (pilihan.equals("Stok Masuk Tambahan")) {
            lblPetunjukJumlah.setText("Isi dengan jumlah yang ditambah.");
        } else if (pilihan.equals("Stok Keluar (Rusak/Hilang)")) {
            lblPetunjukJumlah.setText("Isi dengan jumlah yang dikurangi.");
        } else {
            lblPetunjukJumlah.setText("Pilih jenis penyesuaian di atas.");
        }
        txtJumlah.setText("0"); 
        txtJumlah.requestFocus();
    }
    }//GEN-LAST:event_cmbJenisPenyesuaianItemStateChanged

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        kosong();
        aktif(); 
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        this.dispose();
    if (menuUtamaAsal != null) {
        menuUtamaAsal.tampilkanPanel("Transaksi"); 
        menuUtamaAsal.setVisible(true); 
    } else {
        new MenuUtama().setVisible(true); 
    }
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnHomeDariMainanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeDariMainanActionPerformed
        this.dispose();
        MenuUtama menu = new MenuUtama();
        menu.setVisible(true);
    }//GEN-LAST:event_btnHomeDariMainanActionPerformed

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
            java.util.logging.Logger.getLogger(PengelolaanStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PengelolaanStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PengelolaanStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PengelolaanStok.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PengelolaanStok().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariMainan;
    private javax.swing.JButton btnHomeDariMainan;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpanPenyesuaian;
    private javax.swing.JComboBox<String> cmbJenisPenyesuaian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jspTanggalPenyesuaian;
    private javax.swing.JLabel lblPetunjukJumlah;
    private javax.swing.JTextArea txtAlasanPenyesuaian;
    private javax.swing.JTextField txtIdMainanPenyesuaian;
    private javax.swing.JTextField txtIdPenyesuaian;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNamaMainanPenyesuaian;
    private javax.swing.JTextField txtStokSaatIni;
    // End of variables declaration//GEN-END:variables
}
