package Transaksi;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import MenuUtama.MenuUtama;

public class PembelianMainan extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmodeDetailPembelian;
    private DecimalFormat df = new DecimalFormat("###0");
    public String idSupplierTerpilih;
    public String namaSupplierTerpilih;
    public String idMainanTerpilih;
    public String namaMainanTerpilih;
    public double hargaBeliTerpilih;
    private MenuUtama menuUtamaAsal; 

    public PembelianMainan() {
        initComponents();
        setLocationRelativeTo(null);
        kosong();
        aktif(); 
        autonumberIdPembelian();
        jspTanggalBeli.setEditor(new JSpinner.DateEditor(jspTanggalBeli, "yyyy-MM-dd"));
        jspTanggalBeli.setValue(new Date());
    }
    
    public PembelianMainan(MenuUtama pemanggilMenu) {
        initComponents();
        this.menuUtamaAsal = pemanggilMenu;
        setLocationRelativeTo(pemanggilMenu);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        kosong();
        aktif(); 
        autonumberIdPembelian();
        jspTanggalBeli.setEditor(new JSpinner.DateEditor(jspTanggalBeli, "yyyy-MM-dd"));
        jspTanggalBeli.setValue(new Date()); 
    }

    protected void aktif() {
        txtJumlahBeli.requestFocus(); 
    }

    protected void kosong() {
        txtIdPembelian.setText("");
        txtIdSupplier.setText("");
        txtNamaSupplier.setText("");
        bersihListItem();
        if (tabmodeDetailPembelian != null) {
            tabmodeDetailPembelian.setRowCount(0);
        }
        txtTotalPembelian.setText("0");
        jspTanggalBeli.setValue(new Date());
    }
    
    protected void bersihListItem(){
        txtIdMainanBeli.setText("");
        txtNamaMainanBeli.setText("");
        txtHargaBeliSatuan.setText("0");
        txtJumlahBeli.setText("0");
        txtSubtotalItem.setText("0");
    }

    protected void autonumberIdPembelian() {
        try {
            String sql = "SELECT id_pembelian FROM pembelian ORDER BY id_pembelian DESC LIMIT 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtIdPembelian.setText("PB0001");
            if (rs.next()) {
                String id_pembelian_terakhir = rs.getString("id_pembelian").substring(2);
                int AN = Integer.parseInt(id_pembelian_terakhir) + 1;
                String Nol = "";
                if (AN < 10) { Nol = "000"; }
                else if (AN < 100) { Nol = "00"; }
                else if (AN < 1000) { Nol = "0"; }
                txtIdPembelian.setText("PB" + Nol + AN);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Auto Number ID Pembelian Gagal: " + e);
        }
    }
    
    public void supplierTerpilih() {
        txtIdSupplier.setText(idSupplierTerpilih);
        txtNamaSupplier.setText(namaSupplierTerpilih);
    }

    public void mainanTerpilih() {
        txtIdMainanBeli.setText(idMainanTerpilih);
        txtNamaMainanBeli.setText(namaMainanTerpilih);
        txtHargaBeliSatuan.setText(df.format(hargaBeliTerpilih));
        txtJumlahBeli.setText("1");
        hitungSubtotalItem();
        txtJumlahBeli.requestFocus();
    }
    
    private void hitungSubtotalItem() {
        try {
            double harga = Double.parseDouble(txtHargaBeliSatuan.getText().replace(",", ""));
            int jumlah = Integer.parseInt(txtJumlahBeli.getText());
            double subtotal = harga * jumlah;
            txtSubtotalItem.setText(df.format(subtotal));
        } catch (NumberFormatException e) {
            txtSubtotalItem.setText("0");
        }
    }

    private void hitungTotalKeseluruhan() {
        double total = 0;
        for (int i = 0; i < tblDetailPembelian.getRowCount(); i++) {
            String subtotalStr = tblDetailPembelian.getValueAt(i, 4).toString();
            try {
                Number parsedNumber = df.parse(subtotalStr);
                total += parsedNumber.doubleValue();
            } catch (Exception e) {
                System.out.println("Error parsing subtotal di tabel: " + subtotalStr + " | " + e.getMessage());
            }
        }
        txtTotalPembelian.setText(df.format(total));
    }
    
    protected String generateIdDetail() {
    String idDetail = "DT0001";
    try {
        String sql = "SELECT id_detail FROM detail_pembelian ORDER BY id_detail DESC LIMIT 1";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            String id_detail_terakhir = rs.getString("id_detail").substring(2);
            int AN = Integer.parseInt(id_detail_terakhir) + 1;
            String Nol = "";
            if (AN < 10) { Nol = "000"; }
            else if (AN < 100) { Nol = "00"; }
            else if (AN < 1000) { Nol = "0"; }
            idDetail = "DT" + Nol + AN;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal generate ID Detail: " + e);
        return null; 
    }
    return idDetail;
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
        txtIdPembelian = new javax.swing.JTextField();
        jspTanggalBeli = new javax.swing.JSpinner();
        txtIdSupplier = new javax.swing.JTextField();
        txtNamaSupplier = new javax.swing.JTextField();
        btnCariSupplier = new javax.swing.JButton();
        txtIdMainanBeli = new javax.swing.JTextField();
        btnCariMainan = new javax.swing.JButton();
        txtNamaMainanBeli = new javax.swing.JTextField();
        txtHargaBeliSatuan = new javax.swing.JTextField();
        txtJumlahBeli = new javax.swing.JTextField();
        btnTambahItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetailPembelian = new javax.swing.JTable();
        btnHapusItem = new javax.swing.JButton();
        btnSimpanPembelian = new javax.swing.JButton();
        btnBatalPembelian = new javax.swing.JButton();
        btnKeluarPembelian = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtTotalPembelian = new javax.swing.JTextField();
        txtSubtotalItem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHomeDariMainan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdPembelian.setEditable(false);
        jPanel1.add(txtIdPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 210, 30));

        jspTanggalBeli.setModel(new javax.swing.SpinnerDateModel());
        jPanel1.add(jspTanggalBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 210, 30));

        txtIdSupplier.setEditable(false);
        jPanel1.add(txtIdSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 210, 30));

        txtNamaSupplier.setEditable(false);
        jPanel1.add(txtNamaSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 210, 30));

        btnCariSupplier.setText("Cari");
        btnCariSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariSupplierActionPerformed(evt);
            }
        });
        jPanel1.add(btnCariSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 60, -1));

        txtIdMainanBeli.setEditable(false);
        jPanel1.add(txtIdMainanBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 210, 30));

        btnCariMainan.setText("Cari Mainan");
        btnCariMainan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMainanActionPerformed(evt);
            }
        });
        jPanel1.add(btnCariMainan, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 100, -1));

        txtNamaMainanBeli.setEditable(false);
        jPanel1.add(txtNamaMainanBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, 210, 30));

        txtHargaBeliSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaBeliSatuanActionPerformed(evt);
            }
        });
        txtHargaBeliSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHargaBeliSatuanKeyReleased(evt);
            }
        });
        jPanel1.add(txtHargaBeliSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 210, 30));

        txtJumlahBeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahBeliKeyReleased(evt);
            }
        });
        jPanel1.add(txtJumlahBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, 210, 30));

        btnTambahItem.setText("Tambah");
        btnTambahItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahItemActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambahItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 400, 390, -1));

        tblDetailPembelian.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetailPembelian.setGridColor(new java.awt.Color(0, 0, 0));
        tblDetailPembelian.setShowGrid(true);
        jScrollPane1.setViewportView(tblDetailPembelian);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 1170, 110));

        btnHapusItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/DeleteKecil.png"))); // NOI18N
        btnHapusItem.setBorderPainted(false);
        btnHapusItem.setContentAreaFilled(false);
        btnHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusItemActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapusItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 570, 90, 40));

        btnSimpanPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Desain tanpa judul.png"))); // NOI18N
        btnSimpanPembelian.setBorderPainted(false);
        btnSimpanPembelian.setContentAreaFilled(false);
        btnSimpanPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPembelianActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpanPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 140, 60));

        btnBatalPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonClear.png"))); // NOI18N
        btnBatalPembelian.setBorderPainted(false);
        btnBatalPembelian.setContentAreaFilled(false);
        btnBatalPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalPembelianActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatalPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 610, 150, 60));

        btnKeluarPembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonKeluar.png"))); // NOI18N
        btnKeluarPembelian.setBorderPainted(false);
        btnKeluarPembelian.setContentAreaFilled(false);
        btnKeluarPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarPembelianActionPerformed(evt);
            }
        });
        jPanel1.add(btnKeluarPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 610, 150, 60));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("TOTAL KESELURUHAN");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 620, -1, 30));

        txtTotalPembelian.setEditable(false);
        jPanel1.add(txtTotalPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 620, 160, 30));

        txtSubtotalItem.setEditable(false);
        jPanel1.add(txtSubtotalItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 360, 210, 30));

        jPanel2.setBackground(new java.awt.Color(33, 111, 134));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("PEMBELIAN MAINAN");
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
        jLabel13.setText("ID PEMBELIAN");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(33, 111, 134));
        jLabel14.setText("TGL PEMBELIAN");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(33, 111, 134));
        jLabel15.setText("ID SUPPLIER");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(33, 111, 134));
        jLabel16.setText("NAMA SUPPLIER");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(33, 111, 134));
        jLabel5.setText("ID MAINAN");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(33, 111, 134));
        jLabel6.setText("NAMA MAINAN");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, -1, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(33, 111, 134));
        jLabel17.setText("SUBTOTAL");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 360, -1, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(33, 111, 134));
        jLabel18.setText("HARGA BELI");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, -1, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(33, 111, 134));
        jLabel19.setText("JUMLAH (QTY)");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariSupplierActionPerformed
        PopUpCariSupplier popSupplier = new PopUpCariSupplier();
        popSupplier.frmPembelian = this;
        popSupplier.setLocationRelativeTo(this);
        popSupplier.setVisible(true);
    }//GEN-LAST:event_btnCariSupplierActionPerformed

    private void btnCariMainanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMainanActionPerformed
         String idSupplierYangDipilih = txtIdSupplier.getText(); 
        if (idSupplierYangDipilih.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Silakan pilih Supplier terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return; 
    }
        
        PopUpCariMainan popMainan = new PopUpCariMainan();
        popMainan.initData(idSupplierYangDipilih, this); 
        popMainan.setVisible(true);
    }//GEN-LAST:event_btnCariMainanActionPerformed

    private void txtJumlahBeliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBeliKeyReleased
         hitungSubtotalItem();
    }//GEN-LAST:event_txtJumlahBeliKeyReleased

    private void txtHargaBeliSatuanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaBeliSatuanKeyReleased
        hitungSubtotalItem();
    }//GEN-LAST:event_txtHargaBeliSatuanKeyReleased

    private void btnTambahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahItemActionPerformed
        if (txtIdMainanBeli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih mainan terlebih dahulu.");
            return;
        }
        try {
            String idMainan = txtIdMainanBeli.getText();
            String namaMainan = txtNamaMainanBeli.getText();
            double hargaBeli = df.parse(txtHargaBeliSatuan.getText()).doubleValue(); 
            int jumlah = Integer.parseInt(txtJumlahBeli.getText());
            double subtotal = df.parse(txtSubtotalItem.getText()).doubleValue();
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah beli harus lebih dari 0.");
                return;
            }
            if (tabmodeDetailPembelian == null) {
                 Object[] Baris = {"ID Mainan", "Nama Mainan", "Harga Beli", "Jumlah", "Subtotal"};
                 tabmodeDetailPembelian = new DefaultTableModel(null, Baris);
                 tblDetailPembelian.setModel(tabmodeDetailPembelian);
            }
            boolean itemDitemukan = false;
            for(int i=0; i < tabmodeDetailPembelian.getRowCount(); i++){
                if(idMainan.equals(tabmodeDetailPembelian.getValueAt(i, 0).toString())){
                    int jumlahLama = Integer.parseInt(tabmodeDetailPembelian.getValueAt(i, 3).toString());
                    double hargaLama = df.parse(tabmodeDetailPembelian.getValueAt(i, 2).toString()).doubleValue();
                    int jumlahBaru = jumlahLama + jumlah;
                    double subtotalBaru = hargaLama * jumlahBaru;
                    tabmodeDetailPembelian.setValueAt(jumlahBaru, i, 3);
                    tabmodeDetailPembelian.setValueAt(df.format(subtotalBaru), i, 4);
                    itemDitemukan = true;
                    break;
                }
            }
            if(!itemDitemukan){
                 tabmodeDetailPembelian.addRow(new Object[]{
                    idMainan,
                    namaMainan,
                    df.format(hargaBeli),
                    jumlah,
                    df.format(subtotal)
                });
            }
            bersihListItem();
            hitungTotalKeseluruhan();
            btnCariMainan.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menambah item: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTambahItemActionPerformed

    private void btnHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusItemActionPerformed
        int barisTerpilih = tblDetailPembelian.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item yang akan dihapus dari daftar.");
            return;
        }
        tabmodeDetailPembelian.removeRow(barisTerpilih);
        hitungTotalKeseluruhan();
    }//GEN-LAST:event_btnHapusItemActionPerformed

    private void btnSimpanPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPembelianActionPerformed
        if (txtIdSupplier.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Supplier belum dipilih!");
            return;
        }
        if (tblDetailPembelian.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada item mainan yang ditambahkan ke daftar pembelian!");
            return;
        }
        String idPembelian = txtIdPembelian.getText();
        String idSupplier = txtIdSupplier.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalBeli = sdf.format(jspTanggalBeli.getValue());
        double totalPembelian;
        try {
            totalPembelian = df.parse(txtTotalPembelian.getText()).doubleValue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Format total pembelian tidak valid.");
            return;
        }
        try {
        conn.setAutoCommit(false);
        String sqlPembelian = "INSERT INTO pembelian (id_pembelian, tanggal, id_supplier, total) VALUES (?, ?, ?, ?)";
        PreparedStatement statPembelian = conn.prepareStatement(sqlPembelian);
        statPembelian.setString(1, idPembelian);
        statPembelian.setString(2, tanggalBeli);
        statPembelian.setString(3, idSupplier);
        statPembelian.setDouble(4, totalPembelian);
        statPembelian.executeUpdate();
        
        String sqlDetail = "INSERT INTO detail_pembelian (id_detail, id_pembelian, id_mainan, jumlah, harga) VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateStok = "UPDATE mainan SET stok = stok + ?, harga_beli = ? WHERE id_mainan = ?";
        PreparedStatement statDetail = conn.prepareStatement(sqlDetail);
        PreparedStatement statUpdateStok = conn.prepareStatement(sqlUpdateStok);
        for (int i = 0; i < tblDetailPembelian.getRowCount(); i++) {
        String newIdDetail = generateIdDetail(); 
        if (newIdDetail == null) {
            throw new SQLException("Gagal membuat ID Detail Pembelian.");
        }
        String idMainan = tblDetailPembelian.getValueAt(i, 0).toString();
        int jumlah = Integer.parseInt(tblDetailPembelian.getValueAt(i, 3).toString());
        double hargaBeliItem = df.parse(tblDetailPembelian.getValueAt(i, 2).toString()).doubleValue();
        statDetail.setString(1, newIdDetail);         
        statDetail.setString(2, idPembelian);       
        statDetail.setString(3, idMainan);         
        statDetail.setInt(4, jumlah);            
        statDetail.setDouble(5, hargaBeliItem);     
        statDetail.executeUpdate();
        statUpdateStok.setInt(1, jumlah); 
        statUpdateStok.setDouble(2, hargaBeliItem); 
        statUpdateStok.setString(3, idMainan);
        statUpdateStok.executeUpdate();
    }
        conn.commit(); 
        JOptionPane.showMessageDialog(this, "Transaksi pembelian berhasil disimpan!");
        kosong();
        aktif();
        autonumberIdPembelian();
    } catch (Exception e) { 
    try {
        conn.rollback(); 
    } catch (SQLException ex) {
        System.out.println("Rollback gagal: " + ex);
    }
    JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi pembelian: " + e.getMessage());
    e.printStackTrace();
} finally {
    try {
        conn.setAutoCommit(true); 
    } catch (SQLException ex) {
        System.out.println("Set AutoCommit true gagal: " + ex);
    }
        }
    }//GEN-LAST:event_btnSimpanPembelianActionPerformed

    private void btnBatalPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalPembelianActionPerformed
        kosong();
        aktif();
        autonumberIdPembelian();
    }//GEN-LAST:event_btnBatalPembelianActionPerformed

    private void btnKeluarPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarPembelianActionPerformed
        this.dispose(); 
    if (menuUtamaAsal != null) {
        menuUtamaAsal.tampilkanPanel("Transaksi"); // Kembali ke panel transaksi di MenuUtama
        menuUtamaAsal.setVisible(true); 
    } else {
        new MenuUtama().setVisible(true); 
    }
    }//GEN-LAST:event_btnKeluarPembelianActionPerformed

    private void btnHomeDariMainanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeDariMainanActionPerformed
        this.dispose();
        MenuUtama menu = new MenuUtama();
        menu.setVisible(true);
    }//GEN-LAST:event_btnHomeDariMainanActionPerformed

    private void txtHargaBeliSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaBeliSatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaBeliSatuanActionPerformed

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
            java.util.logging.Logger.getLogger(PembelianMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PembelianMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PembelianMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PembelianMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PembelianMainan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatalPembelian;
    private javax.swing.JButton btnCariMainan;
    private javax.swing.JButton btnCariSupplier;
    private javax.swing.JButton btnHapusItem;
    private javax.swing.JButton btnHomeDariMainan;
    private javax.swing.JButton btnKeluarPembelian;
    private javax.swing.JButton btnSimpanPembelian;
    private javax.swing.JButton btnTambahItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jspTanggalBeli;
    private javax.swing.JTable tblDetailPembelian;
    private javax.swing.JTextField txtHargaBeliSatuan;
    private javax.swing.JTextField txtIdMainanBeli;
    private javax.swing.JTextField txtIdPembelian;
    private javax.swing.JTextField txtIdSupplier;
    private javax.swing.JTextField txtJumlahBeli;
    private javax.swing.JTextField txtNamaMainanBeli;
    private javax.swing.JTextField txtNamaSupplier;
    private javax.swing.JTextField txtSubtotalItem;
    private javax.swing.JTextField txtTotalPembelian;
    // End of variables declaration//GEN-END:variables
}
