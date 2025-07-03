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
import Login.UserID;

public class PenjualanMainan extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmodeDetailPenjualan;
    private DecimalFormat df = new DecimalFormat("###0");
    public String idPelangganTerpilih;
    public String namaPelangganTerpilih;
    public String noTelpPelangganTerpilih;
    public String alamatPelangganTerpilih;
    public String idMainanJualTerpilih;
    public String namaMainanJualTerpilih;
    public double hargaJualTerpilih;
    public int stokSaatIniTerpilih;
    private MenuUtama menuUtamaAsal;
    
    
    public PenjualanMainan() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        kosong();
        aktif();
        autonumberIdPenjualan();
        jspTanggalJual.setEditor(new JSpinner.DateEditor(jspTanggalJual, "yyyy-MM-dd"));
        jspTanggalJual.setValue(new Date()); 
        Object[] Baris = {"ID Mainan", "Nama Mainan", "Harga Jual", "Jumlah", "Subtotal"};
        tabmodeDetailPenjualan = new DefaultTableModel(null, Baris);
        tblDetailPenjualan.setModel(tabmodeDetailPenjualan); 
    }

    public PenjualanMainan(MenuUtama pemanggilMenu) {
    initComponents();                         
    this.menuUtamaAsal = pemanggilMenu;
    setLocationRelativeTo(pemanggilMenu);
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    kosong(); 
    aktif(); 
    autonumberIdPenjualan();
    jspTanggalJual.setEditor(new JSpinner.DateEditor(jspTanggalJual, "yyyy-MM-dd"));
    jspTanggalJual.setValue(new Date()); 
    Object[] Baris = {"ID Mainan", "Nama Mainan", "Harga Jual", "Jumlah", "Subtotal"};
    tabmodeDetailPenjualan = new DefaultTableModel(null, Baris);
    tblDetailPenjualan.setModel(tabmodeDetailPenjualan);
    }

    protected void aktif() {
        btnCariPelanggan.requestFocus(); 
        lblIdKasirValue.setText(UserID.getIdKasir());
        lblNamaKasirValue.setText(UserID.getNamaKasir());
    }
    
    protected void kosong() {
        autonumberIdPenjualan();
        jspTanggalJual.setValue(new Date());
        txtIdPelangganJual.setText("");
        txtNamaPelangganJual.setText("");
        txtNoTeleponPelangganJual.setText("");
        txtAlamatPelangganJual.setText("");
        bersihListItemJual();
        if (tabmodeDetailPenjualan != null) {
            tabmodeDetailPenjualan.setRowCount(0);
        }
        txtTotalPenjualan.setText("0");
        btnCariPelanggan.requestFocus();
    }
    
    protected void bersihListItemJual(){
        txtIdMainanJual.setText("");
        txtNamaMainanJual.setText("");
        txtStokTersediaJual.setText("0");
        txtHargaJualSatuan.setText("0");
        txtJumlahJual.setText("0");
        txtSubtotalItemJual.setText("0");
    }
    
    protected void autonumberIdPenjualan() {
        try {
            String sql = "SELECT id_penjualan FROM penjualan ORDER BY id_penjualan DESC LIMIT 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            txtIdPenjualan.setText("PJ0001"); 
            if (rs.next()) {
                String id_penjualan_terakhir = rs.getString("id_penjualan").substring(2);
                int AN = Integer.parseInt(id_penjualan_terakhir) + 1;
                String Nol = "";
                if (AN < 10) { Nol = "000"; }
                else if (AN < 100) { Nol = "00"; }
                else if (AN < 1000) { Nol = "0"; }
                txtIdPenjualan.setText("PJ" + Nol + AN);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Auto Number ID Penjualan Gagal: " + e);
            e.printStackTrace();
        }
    }
    
    public void pelangganTerpilih() {
        txtIdPelangganJual.setText(idPelangganTerpilih);
        txtNamaPelangganJual.setText(namaPelangganTerpilih);
        txtNoTeleponPelangganJual.setText(noTelpPelangganTerpilih);
        txtAlamatPelangganJual.setText(alamatPelangganTerpilih);
        btnCariMainanJual.requestFocus();
    }
    
    public void mainanJualTerpilih() {
        txtIdMainanJual.setText(idMainanJualTerpilih);
        txtNamaMainanJual.setText(namaMainanJualTerpilih);
        txtHargaJualSatuan.setText(df.format(hargaJualTerpilih));
        txtStokTersediaJual.setText(String.valueOf(stokSaatIniTerpilih));
        txtJumlahJual.setText("1");
        hitungSubtotalItemJual(); 
        txtJumlahJual.requestFocus();
    }
    
    private void hitungSubtotalItemJual() {
        try {
            double harga = df.parse(txtHargaJualSatuan.getText()).doubleValue();
            int jumlah = Integer.parseInt(txtJumlahJual.getText());
            if (jumlah < 0) jumlah = 0;
            double subtotal = harga * jumlah;
            txtSubtotalItemJual.setText(df.format(subtotal));
        } catch (Exception e) {
            txtSubtotalItemJual.setText("0");
        }
    }
    
    private void hitungTotalKeseluruhanJual() {
        double total = 0;
        for (int i = 0; i < tblDetailPenjualan.getRowCount(); i++) {
            try {
                Number parsedNumber = df.parse(tblDetailPenjualan.getValueAt(i, 4).toString());
                total += parsedNumber.doubleValue();
            } catch (Exception e) {
                System.out.println("Error parsing subtotal di tabel penjualan: " + tblDetailPenjualan.getValueAt(i, 4).toString() + " | " + e.getMessage());
            }
        }
        txtTotalPenjualan.setText(df.format(total));
    }
    
    protected String generateIdDetailPenjualan() {
    String idDetailPenjualan = "DJP0001";
    try {
        String sql = "SELECT id_detail FROM detail_penjualan ORDER BY id_detail DESC LIMIT 1";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            String id_detail_terakhir = rs.getString("id_detail").substring(3);
            int AN = Integer.parseInt(id_detail_terakhir) + 1;
            String Nol = "";
            if (AN < 10) { Nol = "000"; }
            else if (AN < 100) { Nol = "00"; }
            else if (AN < 1000) { Nol = "0"; }
            idDetailPenjualan = "DJP" + Nol + AN;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal generate ID Detail Penjualan: " + e);
        e.printStackTrace();
        return null;
    }
    return idDetailPenjualan;
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdPenjualan = new javax.swing.JTextField();
        jspTanggalJual = new javax.swing.JSpinner();
        lblIdKasirValue = new javax.swing.JLabel();
        lblNamaKasirValue = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIdPelangganJual = new javax.swing.JTextField();
        txtNamaPelangganJual = new javax.swing.JTextField();
        txtNoTeleponPelangganJual = new javax.swing.JTextField();
        txtAlamatPelangganJual = new javax.swing.JTextField();
        btnCariPelanggan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtIdMainanJual = new javax.swing.JTextField();
        txtNamaMainanJual = new javax.swing.JTextField();
        txtStokTersediaJual = new javax.swing.JTextField();
        txtHargaJualSatuan = new javax.swing.JTextField();
        txtJumlahJual = new javax.swing.JTextField();
        txtSubtotalItemJual = new javax.swing.JTextField();
        btnTambahItemJual = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetailPenjualan = new javax.swing.JTable();
        btnHapusItemJual = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtTotalPenjualan = new javax.swing.JTextField();
        btnSimpanPenjualan = new javax.swing.JButton();
        btnCariMainanJual = new javax.swing.JButton();
        btnBatalPenjualan = new javax.swing.JButton();
        btnKeluarPenjualan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHomeDariMainan = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(33, 111, 134));
        jLabel4.setText("ID Kasir");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(33, 111, 134));
        jLabel5.setText("NAMA KASIR");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 30));

        txtIdPenjualan.setEditable(false);
        jPanel1.add(txtIdPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 210, 30));

        jspTanggalJual.setModel(new javax.swing.SpinnerDateModel());
        jPanel1.add(jspTanggalJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 210, 30));

        lblIdKasirValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblIdKasirValue.setText("idkasir");
        jPanel1.add(lblIdKasirValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 210, 30));

        lblNamaKasirValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNamaKasirValue.setText("namakasir");
        jPanel1.add(lblNamaKasirValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 220, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(33, 111, 134));
        jLabel8.setText("ALAMAT");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, 30));

        txtIdPelangganJual.setEditable(false);
        jPanel1.add(txtIdPelangganJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 170, 30));

        txtNamaPelangganJual.setEditable(false);
        jPanel1.add(txtNamaPelangganJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 170, 30));

        txtNoTeleponPelangganJual.setEditable(false);
        txtNoTeleponPelangganJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTeleponPelangganJualActionPerformed(evt);
            }
        });
        jPanel1.add(txtNoTeleponPelangganJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 170, 30));
        jPanel1.add(txtAlamatPelangganJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 170, 30));

        btnCariPelanggan.setText("Cari Pelanggan");
        btnCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPelangganActionPerformed(evt);
            }
        });
        jPanel1.add(btnCariPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(33, 111, 134));
        jLabel12.setText("SUBTOTAL");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 410, -1, 30));

        txtIdMainanJual.setEditable(false);
        jPanel1.add(txtIdMainanJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 130, 170, 30));

        txtNamaMainanJual.setEditable(false);
        jPanel1.add(txtNamaMainanJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 170, 30));

        txtStokTersediaJual.setEditable(false);
        jPanel1.add(txtStokTersediaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 260, 170, 30));

        txtHargaJualSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHargaJualSatuanKeyReleased(evt);
            }
        });
        jPanel1.add(txtHargaJualSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 310, 170, 30));

        txtJumlahJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahJualActionPerformed(evt);
            }
        });
        txtJumlahJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahJualKeyReleased(evt);
            }
        });
        jPanel1.add(txtJumlahJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 360, 170, 30));

        txtSubtotalItemJual.setEditable(false);
        jPanel1.add(txtSubtotalItemJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 410, 170, 30));

        btnTambahItemJual.setText("Tambah");
        btnTambahItemJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahItemJualActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambahItemJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 450, 350, -1));

        tblDetailPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetailPenjualan.setGridColor(new java.awt.Color(0, 0, 0));
        tblDetailPenjualan.setShowGrid(true);
        jScrollPane1.setViewportView(tblDetailPenjualan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 790, 140));

        btnHapusItemJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/DeleteKecil.png"))); // NOI18N
        btnHapusItemJual.setBorderPainted(false);
        btnHapusItemJual.setContentAreaFilled(false);
        btnHapusItemJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusItemJualActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapusItemJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 540, 80, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("TOTAL KESELURUHAN");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 530, -1, 30));

        txtTotalPenjualan.setEditable(false);
        jPanel1.add(txtTotalPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 530, 160, 30));

        btnSimpanPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Desain tanpa judul.png"))); // NOI18N
        btnSimpanPenjualan.setBorderPainted(false);
        btnSimpanPenjualan.setContentAreaFilled(false);
        btnSimpanPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPenjualanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpanPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 120, 60));

        btnCariMainanJual.setText("Cari Mainan");
        btnCariMainanJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariMainanJualActionPerformed(evt);
            }
        });
        jPanel1.add(btnCariMainanJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 170, -1, -1));

        btnBatalPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonClear.png"))); // NOI18N
        btnBatalPenjualan.setBorderPainted(false);
        btnBatalPenjualan.setContentAreaFilled(false);
        btnBatalPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalPenjualanActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatalPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 590, 130, 60));

        btnKeluarPenjualan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ButtonKeluar.png"))); // NOI18N
        btnKeluarPenjualan.setBorderPainted(false);
        btnKeluarPenjualan.setContentAreaFilled(false);
        btnKeluarPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarPenjualanActionPerformed(evt);
            }
        });
        jPanel1.add(btnKeluarPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 590, 120, 60));

        jPanel2.setBackground(new java.awt.Color(33, 111, 134));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("PENJUALAN MAINAN");
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

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/home-button.png"))); // NOI18N
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 40, 90));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 90));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(33, 111, 134));
        jLabel20.setText("ID PENJUALAN");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(33, 111, 134));
        jLabel21.setText("TGL. JUAL");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(33, 111, 134));
        jLabel22.setText("ID PELANGGAN");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 111, 134));
        jLabel23.setText("NAMA PELANGGAN");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, -1, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(33, 111, 134));
        jLabel24.setText("NO. TELEPON");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, -1, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(33, 111, 134));
        jLabel25.setText("ID MAINAN");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, -1, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(33, 111, 134));
        jLabel26.setText("NAMA MAINAN");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, -1, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(33, 111, 134));
        jLabel27.setText("STOK TERSEDIA");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(33, 111, 134));
        jLabel13.setText("HARGA JUAL");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, -1, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(33, 111, 134));
        jLabel28.setText("JUMLAH JUAL (QTY)");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 360, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoTeleponPelangganJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTeleponPelangganJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTeleponPelangganJualActionPerformed

    private void btnCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPelangganActionPerformed
        PopUpCariPelanggan popPelanggan = new PopUpCariPelanggan();
        popPelanggan.initData(this);
        popPelanggan.setVisible(true);
    }//GEN-LAST:event_btnCariPelangganActionPerformed

    private void btnCariMainanJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariMainanJualActionPerformed
        PopUpCariMainanJual popMainan = new PopUpCariMainanJual();
        popMainan.initData(this);
        popMainan.setVisible(true);
    }//GEN-LAST:event_btnCariMainanJualActionPerformed

    private void txtJumlahJualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahJualKeyReleased
        hitungSubtotalItemJual();
    }//GEN-LAST:event_txtJumlahJualKeyReleased

    private void txtJumlahJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahJualActionPerformed
        btnTambahItemJual.requestFocus();
    }//GEN-LAST:event_txtJumlahJualActionPerformed

    private void txtHargaJualSatuanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaJualSatuanKeyReleased
        hitungSubtotalItemJual();
    }//GEN-LAST:event_txtHargaJualSatuanKeyReleased

    private void btnTambahItemJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahItemJualActionPerformed
        if (txtIdMainanJual.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih mainan terlebih dahulu.");
            btnCariMainanJual.requestFocus();
            return;
        }
        try {
            String idMainan = txtIdMainanJual.getText();
            String namaMainan = txtNamaMainanJual.getText();
            double hargaJual = df.parse(txtHargaJualSatuan.getText()).doubleValue(); 
            int jumlahJual = Integer.parseInt(txtJumlahJual.getText());
            int stokTersedia = Integer.parseInt(txtStokTersediaJual.getText());
            double subtotal = df.parse(txtSubtotalItemJual.getText()).doubleValue();
            if (jumlahJual <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah jual harus lebih dari 0.");
                txtJumlahJual.requestFocus();
                return;
            }
            if (jumlahJual > stokTersedia) {
                JOptionPane.showMessageDialog(this, "Jumlah jual melebihi stok yang tersedia (" + stokTersedia + ").");
                txtJumlahJual.requestFocus();
                return;
            }
            boolean itemDitemukan = false;
            for(int i=0; i < tabmodeDetailPenjualan.getRowCount(); i++){
                if(idMainan.equals(tabmodeDetailPenjualan.getValueAt(i, 0).toString())){
                    int jumlahLamaDiKeranjang = Integer.parseInt(tabmodeDetailPenjualan.getValueAt(i, 3).toString());
                    double hargaLamaDiKeranjang = df.parse(tabmodeDetailPenjualan.getValueAt(i, 2).toString()).doubleValue();
                    int jumlahTotalDiminta = jumlahLamaDiKeranjang + jumlahJual;
                    if (jumlahTotalDiminta > stokTersedia) {
                        JOptionPane.showMessageDialog(this, "Stok tidak mencukupi. Anda sudah menambahkan " + jumlahLamaDiKeranjang + 
                                                      ", ingin menambah " + jumlahJual + " lagi. Stok tersedia: " + stokTersedia);
                        txtJumlahJual.requestFocus();
                        return;
                    }
                    double subtotalBaru = hargaLamaDiKeranjang * jumlahTotalDiminta; 
                    tabmodeDetailPenjualan.setValueAt(jumlahTotalDiminta, i, 3);
                    tabmodeDetailPenjualan.setValueAt(df.format(subtotalBaru), i, 4);
                    itemDitemukan = true;
                    break;
                }
            }
            if(!itemDitemukan){
                 tabmodeDetailPenjualan.addRow(new Object[]{
                    idMainan,
                    namaMainan,
                    df.format(hargaJual),
                    jumlahJual,
                    df.format(subtotal)
                });
            }
            bersihListItemJual();
            hitungTotalKeseluruhanJual();
            btnCariMainanJual.requestFocus(); 
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Gagal menambah item ke keranjang: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTambahItemJualActionPerformed

    private void btnHapusItemJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusItemJualActionPerformed
        int barisTerpilih = tblDetailPenjualan.getSelectedRow();
        if (barisTerpilih == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item yang akan dihapus dari daftar.");
            return;
        }
        tabmodeDetailPenjualan.removeRow(barisTerpilih);
        hitungTotalKeseluruhanJual();
    }//GEN-LAST:event_btnHapusItemJualActionPerformed

    private void btnSimpanPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPenjualanActionPerformed
        if (txtIdPelangganJual.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pelanggan belum dipilih!");
            btnCariPelanggan.requestFocus();
            return;
        }
        if (tblDetailPenjualan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada item mainan yang ditambahkan!");
            btnCariMainanJual.requestFocus();
            return;
        }
        String idPenjualan = txtIdPenjualan.getText();
        String idPelanggan = txtIdPelangganJual.getText();
        String idKasir = lblIdKasirValue.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalJual = sdf.format(jspTanggalJual.getValue());
        double totalPenjualan;
        try {
            totalPenjualan = df.parse(txtTotalPenjualan.getText()).doubleValue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Format total penjualan tidak valid.");
            return;
        }
        try {
    conn.setAutoCommit(false); 
    String sqlPenjualan = "INSERT INTO penjualan (id_penjualan, tanggal, id_pelanggan, total, id_kasir) VALUES (?, ?, ?, ?, ?)"; 
    PreparedStatement statPenjualan = conn.prepareStatement(sqlPenjualan);
    statPenjualan.setString(1, idPenjualan);
    statPenjualan.setString(2, tanggalJual);
    statPenjualan.setString(3, idPelanggan);
    statPenjualan.setDouble(4, totalPenjualan);
    statPenjualan.setString(5, idKasir);
    statPenjualan.executeUpdate();
    String sqlDetail = "INSERT INTO detail_penjualan (id_detail, id_penjualan, id_mainan, jumlah, harga) VALUES (?, ?, ?, ?, ?)";
    String sqlUpdateStok = "UPDATE mainan SET stok = stok - ? WHERE id_mainan = ?";
    PreparedStatement statDetail = conn.prepareStatement(sqlDetail);
    PreparedStatement statUpdateStok = conn.prepareStatement(sqlUpdateStok);

    for (int i = 0; i < tblDetailPenjualan.getRowCount(); i++) {
        String newIdDetailJual = generateIdDetailPenjualan(); 
        if (newIdDetailJual == null) { 
            throw new SQLException("Gagal membuat ID Detail Penjualan untuk item ke-" + (i+1));
        }
        String idMainan = tblDetailPenjualan.getValueAt(i, 0).toString();
        int jumlah = Integer.parseInt(tblDetailPenjualan.getValueAt(i, 3).toString());
        double hargaJualItem = df.parse(tblDetailPenjualan.getValueAt(i, 2).toString()).doubleValue();
        statDetail.setString(1, newIdDetailJual);    
        statDetail.setString(2, idPenjualan);        
        statDetail.setString(3, idMainan);           
        statDetail.setInt(4, jumlah);              
        statDetail.setDouble(5, hargaJualItem);
        statDetail.executeUpdate();
        statUpdateStok.setInt(1, jumlah); 
        statUpdateStok.setString(2, idMainan);
        statUpdateStok.executeUpdate();
    }
    conn.commit(); 
    JOptionPane.showMessageDialog(this, "Transaksi penjualan berhasil disimpan!");
    kosong(); 
} catch (Exception e) {
    try {
        conn.rollback(); 
    } catch (SQLException ex) {
        System.out.println("Rollback gagal: " + ex);
    }
    JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi penjualan: " + e.getMessage());
    e.printStackTrace();
} finally {
    try {
        conn.setAutoCommit(true); 
    } catch (SQLException ex) {
        System.out.println("Set AutoCommit true gagal: " + ex);
    }
        }
    }//GEN-LAST:event_btnSimpanPenjualanActionPerformed

    private void btnBatalPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalPenjualanActionPerformed
    kosong();
    aktif();
    }//GEN-LAST:event_btnBatalPenjualanActionPerformed

    private void btnKeluarPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarPenjualanActionPerformed
        this.dispose();
    if (menuUtamaAsal != null) {
        menuUtamaAsal.tampilkanPanel("Transaksi"); 
        menuUtamaAsal.setVisible(true); 
    } else {
        new MenuUtama().setVisible(true); 
    }
    }//GEN-LAST:event_btnKeluarPenjualanActionPerformed

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
            java.util.logging.Logger.getLogger(PenjualanMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenjualanMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenjualanMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenjualanMainan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenjualanMainan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatalPenjualan;
    private javax.swing.JButton btnCariMainanJual;
    private javax.swing.JButton btnCariPelanggan;
    private javax.swing.JButton btnHapusItemJual;
    private javax.swing.JButton btnHomeDariMainan;
    private javax.swing.JButton btnKeluarPenjualan;
    private javax.swing.JButton btnSimpanPenjualan;
    private javax.swing.JButton btnTambahItemJual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jspTanggalJual;
    private javax.swing.JLabel lblIdKasirValue;
    private javax.swing.JLabel lblNamaKasirValue;
    private javax.swing.JTable tblDetailPenjualan;
    private javax.swing.JTextField txtAlamatPelangganJual;
    private javax.swing.JTextField txtHargaJualSatuan;
    private javax.swing.JTextField txtIdMainanJual;
    private javax.swing.JTextField txtIdPelangganJual;
    private javax.swing.JTextField txtIdPenjualan;
    private javax.swing.JTextField txtJumlahJual;
    private javax.swing.JTextField txtNamaMainanJual;
    private javax.swing.JTextField txtNamaPelangganJual;
    private javax.swing.JTextField txtNoTeleponPelangganJual;
    private javax.swing.JTextField txtStokTersediaJual;
    private javax.swing.JTextField txtSubtotalItemJual;
    private javax.swing.JTextField txtTotalPenjualan;
    // End of variables declaration//GEN-END:variables
}
