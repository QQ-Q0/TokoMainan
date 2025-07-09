-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Jul 2025 pada 08.20
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_mainan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pembelian`
--

CREATE TABLE `detail_pembelian` (
  `id_detail` varchar(10) NOT NULL,
  `id_pembelian` varchar(10) DEFAULT NULL,
  `id_mainan` varchar(10) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `harga` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_pembelian`
--

INSERT INTO `detail_pembelian` (`id_detail`, `id_pembelian`, `id_mainan`, `jumlah`, `harga`) VALUES
('DT0001', 'PB0001', 'MN001', 30, 28000),
('DT0002', 'PB0001', 'MN004', 50, 35000),
('DT0003', 'PB0001', 'MN005', 80, 75000),
('DT0004', 'PB0001', 'MN002', 1, 20000),
('DT0005', 'PB0002', 'MN003', 30, 45000),
('DT0006', 'PB0002', 'MN004', 60, 35000),
('DT0007', 'PB0003', 'MN003', 40, 45000),
('DT0008', 'PB0004', 'MN001', 10, 28000),
('DT0009', 'PB0004', 'MN003', 10, 45000),
('DT0010', 'PB0004', 'MN004', 20, 35000),
('DT0011', 'PB0006', 'MN004', 12, 35000),
('DT0012', 'PB0006', 'MN005', 20, 75000),
('DT0013', 'PB0007', 'MN006', 1, 100000),
('DT0014', 'PB0007', 'MN005', 10, 75000),
('DT0015', 'PB0007', 'MN004', 100, 35000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id_detail` varchar(10) NOT NULL,
  `id_penjualan` varchar(10) DEFAULT NULL,
  `id_mainan` varchar(10) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `harga` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id_detail`, `id_penjualan`, `id_mainan`, `jumlah`, `harga`) VALUES
('DJP0001', 'PJ0001', 'MN001', 5, 38000),
('DJP0002', 'PJ0001', 'MN005', 7, 100000),
('DJP0003', 'PJ0002', 'MN002', 22, 30000),
('DJP0004', 'PJ0003', 'MN003', 1, 70000),
('DJP0005', 'PJ0004', 'MN002', 2, 30000),
('DJP0006', 'PJ0005', 'MN001', 2, 38000),
('DJP0007', 'PJ0005', 'MN003', 2, 70000),
('DJP0008', 'PJ0006', 'MN005', 1, 120000),
('DJP0009', 'PJ0006', 'MN002', 1, 30000),
('DJP0010', 'PJ0008', 'MN002', 13, 30000),
('DJP0011', 'PJ0008', 'MN004', 15, 60000),
('DJP0012', 'PJ0009', 'MN005', 10, 120000),
('DJP0013', 'PJ0009', 'MN006', 10, 160000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kasir`
--

CREATE TABLE `kasir` (
  `id_kasir` varchar(10) NOT NULL,
  `nama_kasir` varchar(100) NOT NULL,
  `password_kasir` varchar(255) NOT NULL,
  `level_kasir` varchar(20) NOT NULL COMMENT 'Contoh: Admin, Kasir',
  `status_aktif` tinyint(1) DEFAULT 1 COMMENT 'TRUE jika aktif, FALSE jika tidak aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kasir`
--

INSERT INTO `kasir` (`id_kasir`, `nama_kasir`, `password_kasir`, `level_kasir`, `status_aktif`) VALUES
('KSR001', 'SYAM', 'kelompok4', 'Admin', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_mainan`
--

CREATE TABLE `kategori_mainan` (
  `id_kategori` varchar(10) NOT NULL,
  `nama_kategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kategori_mainan`
--

INSERT INTO `kategori_mainan` (`id_kategori`, `nama_kategori`) VALUES
('KT001', 'Action Figure'),
('KT002', 'Boneka'),
('KT003', 'Mobil-Mobilan'),
('KT004', 'Puzzle'),
('KT005', 'Mainan Bayi'),
('KT006', 'Mainan Outdoor'),
('KT007', 'mainan lego');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mainan`
--

CREATE TABLE `mainan` (
  `id_mainan` varchar(10) NOT NULL,
  `nama_mainan` varchar(100) NOT NULL,
  `id_kategori` varchar(100) DEFAULT NULL,
  `id_supplier` varchar(100) DEFAULT NULL,
  `harga_beli` double DEFAULT NULL,
  `harga_jual` double DEFAULT NULL,
  `stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mainan`
--

INSERT INTO `mainan` (`id_mainan`, `nama_mainan`, `id_kategori`, `id_supplier`, `harga_beli`, `harga_jual`, `stok`) VALUES
('MN001', 'Ultraman Mebius Figure', 'KT003', 'SUP001', 28000, 38000, 36),
('MN002', 'Boneka Chucky', 'KT002', 'SUP002', 20000, 30000, 5),
('MN003', 'Mobil Remot', 'KT003', 'SUP001', 45000, 70000, 85),
('MN004', 'Puzzle Anak 100pcs', 'KT004', 'SUP003', 35000, 60000, 237),
('MN005', 'Robot Ochobot', 'KT005', 'SUP002', 75000, 120000, 116),
('MN006', 'Lego', 'KT007', 'SUP004', 100000, 160000, 68);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `alamat` text DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `jenis_kelamin` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `alamat`, `no_telp`, `jenis_kelamin`) VALUES
('P001', 'Hisyam', 'Pulo Jahe', '081223745165', 'Laki-laki'),
('P002', 'Lukman', 'Kp Pedurenan', '089636205821', 'Laki-laki'),
('P003', 'Nebula', 'Kelapa Gading', '081345678822', 'Perempuan'),
('P004', 'Fadil', 'Kp Golay', '088291835488', 'Laki-laki');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembelian`
--

CREATE TABLE `pembelian` (
  `id_pembelian` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `id_supplier` varchar(10) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pembelian`
--

INSERT INTO `pembelian` (`id_pembelian`, `tanggal`, `id_supplier`, `total`) VALUES
('PB0001', '2025-05-30', 'SUP002', 8610000),
('PB0002', '2025-05-30', 'SUP003', 3450000),
('PB0003', '2025-06-05', 'SUP001', 1800000),
('PB0004', '2025-06-07', 'SUP003', 1430000),
('PB0005', '2025-06-19', 'SUP002', 550000),
('PB0006', '2025-06-19', 'SUP002', 1920000),
('PB0007', '2025-06-20', 'SUP003', 4350000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `id_kasir` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `tanggal`, `id_pelanggan`, `total`, `id_kasir`) VALUES
('PJ0001', '2025-05-31', 'P001', 890000, NULL),
('PJ0002', '2025-05-31', 'P002', 660000, NULL),
('PJ0003', '2025-06-05', 'P001', 70000, NULL),
('PJ0004', '2025-06-07', 'P003', 60000, NULL),
('PJ0005', '2025-06-07', 'P001', 216000, NULL),
('PJ0006', '2025-06-07', 'P002', 150000, NULL),
('PJ0007', '2025-06-19', 'P001', 290000, NULL),
('PJ0008', '2025-06-19', 'P004', 1290000, 'KSR001'),
('PJ0009', '2025-06-20', 'P004', 2800000, 'KSR001');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penyesuaian_stok`
--

CREATE TABLE `penyesuaian_stok` (
  `id_penyesuaian` varchar(15) NOT NULL,
  `tanggal_penyesuaian` date NOT NULL,
  `id_mainan` varchar(10) NOT NULL,
  `jenis_penyesuaian` varchar(50) NOT NULL,
  `jumlah_sebelum` int(11) NOT NULL,
  `jumlah_penyesuaian` int(11) NOT NULL,
  `jumlah_setelah` int(11) NOT NULL,
  `alasan` varchar(255) DEFAULT NULL,
  `id_user` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `penyesuaian_stok`
--

INSERT INTO `penyesuaian_stok` (`id_penyesuaian`, `tanggal_penyesuaian`, `id_mainan`, `jenis_penyesuaian`, `jumlah_sebelum`, `jumlah_penyesuaian`, `jumlah_setelah`, `alasan`, `id_user`) VALUES
('PS0001', '2025-06-01', 'MN001', 'Stok Keluar (Rusak/Hilang)', 29, -5, 24, '5 barang hilang', 'ADMIN'),
('PS0002', '2025-06-01', 'MN003', 'Stok Masuk Tambahan', 38, 2, 40, 'Barang Di temukan\ndigudang total 2 barang', 'ADMIN'),
('PS0003', '2025-06-01', 'MN005', 'Hasil Stok Opname', 108, -3, 105, 'Hasl Hitungan fisik di gudang totalnya 105', 'ADMIN'),
('PS0004', '2025-06-01', 'MN004', 'Hasil Stok Opname', 113, 2, 115, 'Hasil Dari hitungan fisik di gudang totalnya 115', 'ADMIN'),
('PS0005', '2025-06-05', 'MN005', 'Stok Keluar (Rusak/Hilang)', 105, -5, 100, 'Hilang', 'ADMIN'),
('PS0006', '2025-06-07', 'MN001', 'Stok Masuk Tambahan', 32, 4, 36, 'DiGudang ditemukan ada 4', 'ADMIN'),
('PS0007', '2025-06-07', 'MN005', 'Stok Keluar (Rusak/Hilang)', 99, -3, 96, 'Hilang 3', 'ADMIN'),
('PS0008', '2025-06-19', 'MN002', 'Stok Masuk Tambahan', 9, 10, 19, 'Barang yang belum terhitung atau barang retur', 'KSR001'),
('PS0009', '2025-06-19', 'MN002', 'Stok Keluar (Rusak/Hilang)', 19, -10, 9, 'brang yang rusak dan barang yang hilang', 'KSR001'),
('PS0010', '2025-06-19', 'MN002', 'Hasil Stok Opname', 9, -4, 5, 'Ternyata setelah dihitung kembali barang yang ada digudang ada 5 buah', 'KSR001');

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(10) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `alamat` text DEFAULT NULL,
  `no_telp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `alamat`, `no_telp`) VALUES
('SUP001', 'PT.Mainan Nusantara', 'Jl. Raya Mainan No. 123', '081234567890'),
('SUP002', 'CN. Dunia Anak', 'Jl. Kenangan Indah No. 45', '082112334453'),
('SUP003', 'Toko Grosir Mainan', 'Jl. Pahlawan No. 10, Jakarta', '085677483322'),
('SUP004', 'PT Mainan Indah', 'Pasar minggu', '08888118744');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_pembelian` (`id_pembelian`),
  ADD KEY `id_mainan` (`id_mainan`);

--
-- Indeks untuk tabel `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_penjualan` (`id_penjualan`),
  ADD KEY `id_mainan` (`id_mainan`);

--
-- Indeks untuk tabel `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id_kasir`);

--
-- Indeks untuk tabel `kategori_mainan`
--
ALTER TABLE `kategori_mainan`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `mainan`
--
ALTER TABLE `mainan`
  ADD PRIMARY KEY (`id_mainan`),
  ADD KEY `fk_mainan_kategori` (`id_kategori`),
  ADD KEY `fk_mainan_supplier` (`id_supplier`);

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`id_pembelian`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `fk_penjualan_kasir` (`id_kasir`);

--
-- Indeks untuk tabel `penyesuaian_stok`
--
ALTER TABLE `penyesuaian_stok`
  ADD PRIMARY KEY (`id_penyesuaian`),
  ADD KEY `fk_penyesuaian_mainan` (`id_mainan`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD CONSTRAINT `detail_pembelian_ibfk_1` FOREIGN KEY (`id_pembelian`) REFERENCES `pembelian` (`id_pembelian`),
  ADD CONSTRAINT `detail_pembelian_ibfk_2` FOREIGN KEY (`id_mainan`) REFERENCES `mainan` (`id_mainan`);

--
-- Ketidakleluasaan untuk tabel `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id_penjualan`),
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`id_mainan`) REFERENCES `mainan` (`id_mainan`);

--
-- Ketidakleluasaan untuk tabel `mainan`
--
ALTER TABLE `mainan`
  ADD CONSTRAINT `fk_mainan_kategori` FOREIGN KEY (`id_kategori`) REFERENCES `kategori_mainan` (`id_kategori`),
  ADD CONSTRAINT `fk_mainan_supplier` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`);

--
-- Ketidakleluasaan untuk tabel `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`);

--
-- Ketidakleluasaan untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `fk_penjualan_kasir` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`);

--
-- Ketidakleluasaan untuk tabel `penyesuaian_stok`
--
ALTER TABLE `penyesuaian_stok`
  ADD CONSTRAINT `fk_penyesuaian_mainan` FOREIGN KEY (`id_mainan`) REFERENCES `mainan` (`id_mainan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
