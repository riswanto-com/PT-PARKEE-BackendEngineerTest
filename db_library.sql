-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2023 at 12:54 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_library`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `id` int(11) NOT NULL,
  `anggota_alamat` varchar(255) DEFAULT NULL,
  `anggota_email` varchar(100) DEFAULT NULL,
  `anggota_nama` varchar(150) DEFAULT NULL,
  `anggota_telepon` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id`, `anggota_alamat`, `anggota_email`, `anggota_nama`, `anggota_telepon`) VALUES
(1, 'anggotaAlamat', 'anggotaEmails@gmail.com', 'anggotaNama', '089634922954'),
(2, 'anggotaAlamat', 'anggotaEmail@gmail.com', 'anggotaNama', '089634922952');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` int(11) NOT NULL,
  `buku_diskripsi` longtext DEFAULT NULL,
  `buku_judul` varchar(100) DEFAULT NULL,
  `buku_penulis` varchar(150) DEFAULT NULL,
  `buku_tahun` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `buku_diskripsi`, `buku_judul`, `buku_penulis`, `buku_tahun`) VALUES
(1, 'xxxx', 'yoyos', 'xx', '2023-01-02'),
(2, 'xxxx', 'cccc', 'ccccc', '2023-01-02'),
(3, 'xxxx', 'cccc', 'ccccc', '2023-01-02'),
(4, 'xxxx', 'ccccs', 'ccccc', '2023-01-02'),
(5, 'xxxx', '1ccccs', 'ccccc', '2023-01-02');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `peminjaman_id` bigint(20) NOT NULL,
  `peminjaman_lama` int(11) DEFAULT NULL,
  `peminjaman_status` varchar(15) DEFAULT NULL,
  `peminjaman_tanggal_kembali` date DEFAULT NULL,
  `peminjaman_tanggal_pinjam` date DEFAULT NULL,
  `anggota_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`peminjaman_id`, `peminjaman_lama`, `peminjaman_status`, `peminjaman_tanggal_kembali`, `peminjaman_tanggal_pinjam`, `anggota_id`) VALUES
(1, 1, NULL, NULL, '2023-03-04', 1),
(2, 6, NULL, NULL, '2023-03-04', 1);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman_detail`
--

CREATE TABLE `peminjaman_detail` (
  `det_pinjam_id_peminjaman` bigint(20) NOT NULL,
  `det_pinjam_id_buku` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`peminjaman_id`),
  ADD KEY `FKahxohspfmbkmloa7xuvhiocr3` (`anggota_id`);

--
-- Indexes for table `peminjaman_detail`
--
ALTER TABLE `peminjaman_detail`
  ADD PRIMARY KEY (`det_pinjam_id_peminjaman`,`det_pinjam_id_buku`),
  ADD KEY `FK27fgwpqfoxlp4y9xegkv2gohe` (`det_pinjam_id_buku`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anggota`
--
ALTER TABLE `anggota`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `peminjaman_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `FKahxohspfmbkmloa7xuvhiocr3` FOREIGN KEY (`anggota_id`) REFERENCES `anggota` (`id`);

--
-- Constraints for table `peminjaman_detail`
--
ALTER TABLE `peminjaman_detail`
  ADD CONSTRAINT `FK27fgwpqfoxlp4y9xegkv2gohe` FOREIGN KEY (`det_pinjam_id_buku`) REFERENCES `buku` (`id`),
  ADD CONSTRAINT `FKd45qtgoindeokx397iiq76m0w` FOREIGN KEY (`det_pinjam_id_peminjaman`) REFERENCES `peminjaman` (`peminjaman_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
