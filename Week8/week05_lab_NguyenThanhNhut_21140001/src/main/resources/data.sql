-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.5.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for works
CREATE DATABASE IF NOT EXISTS `works` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `works`;

-- Dumping structure for table works.address
CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `street` varchar(150) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` enum('AC','AD','AE','AF','AG','AI','AL','AM','AN','AO','AQ','AR','AS','AT','AU','AW','AX','AZ','BA','BB','BD','BE','BF','BG','BH','BI','BJ','BL','BM','BN','BO','BQ','BR','BS','BT','BU','BV','BW','BY','BZ','CA','CC','CD','CF','CG','CH','CI','CK','CL','CM','CN','CO','CP','CR','CS','CU','CV','CW','CX','CY','CZ','DE','DG','DJ','DK','DM','DO','DZ','EA','EC','EE','EG','EH','ER','ES','ET','EU','EZ','FI','FJ','FK','FM','FO','FR','FX','GA','GB','GD','GE','GF','GG','GH','GI','GL','GM','GN','GP','GQ','GR','GS','GT','GU','GW','GY','HK','HM','HN','HR','HT','HU','IC','ID','IE','IL','IM','IN','IO','IQ','IR','IS','IT','JE','JM','JO','JP','KE','KG','KH','KI','KM','KN','KP','KR','KW','KY','KZ','LA','LB','LC','LI','LK','LR','LS','LT','LU','LV','LY','MA','MC','MD','ME','MF','MG','MH','MK','ML','MM','MN','MO','MP','MQ','MR','MS','MT','MU','MV','MW','MX','MY','MZ','NA','NC','NE','NF','NG','NI','NL','NO','NP','NR','NT','NU','NZ','OM','PA','PE','PF','PG','PH','PK','PL','PM','PN','PR','PS','PT','PW','PY','QA','RE','RO','RS','RU','RW','SA','SB','SC','SD','SE','SF','SG','SH','SI','SJ','SK','SL','SM','SN','SO','SR','SS','ST','SU','SV','SX','SY','SZ','TA','TC','TD','TF','TG','TH','TJ','TK','TL','TM','TN','TO','TP','TR','TT','TV','TW','TZ','UA','UG','UK','UM','UNDEFINED','US','UY','UZ','VA','VC','VE','VG','VI','VN','VU','WF','WS','XI','XK','XU','YE','YT','YU','ZA','ZM','ZR','ZW') DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `zipcode` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1050 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.address: ~53 rows (approximately)
INSERT INTO `address` (`id`, `street`, `city`, `country`, `number`, `zipcode`) VALUES
	(1, 'Hoàng Văn Thái', 'Hồ Chí Minh', 'VN', '8', NULL),
	(1035, 'Hoàng Văn Thái', 'Hồ Chí Minh', 'VN', '8', '70000'),
	(1041, 'Hoàng Văn Thái', 'Hồ Chí Minh', 'VN', '8', '70000'),
	(1046, 'Lê Đức Thọ', 'Hồ Chí Minh', 'VN', '114', '696969'),
	(1048, 'Nguyễn Văn Bảo', 'Hồ Chí Minh', 'VN', '12', '123456'),
	(1049, 'Lê Đức Thọ', 'Hồ Chí Minh', 'VN', '123', '696969'),
	(1050, 'Nguyễn Thái Bình', 'Hồ Chí Minh', 'VN', '12A', '70002'),
	(1051, 'Điện Biên Phủ', 'Hà Nội', 'VN', '32B', '10001'),
	(1052, 'Hàm Nghi', 'Hồ Chí Minh', 'VN', '45', '71003'),
	(1053, 'Ngô Gia Tự', 'Đà Nẵng', 'VN', '17', '55001'),
	(1054, 'Phạm Văn Đồng', 'Hải Phòng', 'VN', '21', '18001'),
	(1055, 'Trần Hưng Đạo', 'Cần Thơ', 'VN', '18', '90000'),
	(1056, 'Cách Mạng Tháng 8', 'Hồ Chí Minh', 'VN', '101', '70003'),
	(1057, 'Lê Thánh Tôn', 'Nha Trang', 'VN', '20', '65000'),
	(1058, 'Nguyễn Văn Linh', 'Hà Nội', 'VN', '50', '10002'),
	(1059, 'Bạch Đằng', 'Đà Nẵng', 'VN', '7A', '55002'),
	(1060, 'Lý Thái Tổ', 'Hải Phòng', 'VN', '12B', '18002'),
	(1061, 'Trường Chinh', 'Hồ Chí Minh', 'VN', '23C', '70004'),
	(1062, 'Phan Đăng Lưu', 'Vũng Tàu', 'VN', '9', '78000'),
	(1063, 'Hồng Bàng', 'Hải Phòng', 'VN', '67', '18003'),
	(1064, 'Phan Văn Trị', 'Hồ Chí Minh', 'VN', '88A', '70005'),
	(1065, 'Huỳnh Tấn Phát', 'Hồ Chí Minh', 'VN', '101', '70006'),
	(1066, 'Tôn Đức Thắng', 'Hà Nội', 'VN', '77', '10003'),
	(1067, 'Trần Phú', 'Đà Lạt', 'VN', '4', '67000'),
	(1068, 'Quang Trung', 'Quảng Ngãi', 'VN', '99', '51000'),
	(1069, 'Nguyễn Trãi', 'Hồ Chí Minh', 'VN', '34', '70007'),
	(1070, 'Hùng Vương', 'Hà Nội', 'VN', '21B', '10004'),
	(1071, 'Lê Lợi', 'Huế', 'VN', '11', '53000'),
	(1072, 'Phạm Ngũ Lão', 'Hồ Chí Minh', 'VN', '72', '70008'),
	(1073, 'Trần Quang Khải', 'Cần Thơ', 'VN', '10', '90001'),
	(1074, 'Võ Thị Sáu', 'Hồ Chí Minh', 'VN', '8C', '70009'),
	(1075, 'Nguyễn Đình Chiểu', 'Nha Trang', 'VN', '16', '65001'),
	(1076, 'Hoàng Sa', 'Đà Nẵng', 'VN', '3', '55003'),
	(1077, 'Trường Sơn', 'Hồ Chí Minh', 'VN', '101A', '70010'),
	(1078, 'Đặng Văn Ngữ', 'Hồ Chí Minh', 'VN', '41', '70011'),
	(1079, 'Phan Xích Long', 'Hồ Chí Minh', 'VN', '27', '70012'),
	(1080, 'Nguyễn Đình Chiểu', 'Hà Nội', 'VN', '11A', '10005'),
	(1081, 'Đường số 1', 'Bình Dương', 'VN', '5', '82000'),
	(1082, 'Đường số 2', 'Đồng Nai', 'VN', '12', '81000'),
	(1083, 'Đường số 3', 'Hồ Chí Minh', 'VN', '8', '70013'),
	(1084, 'Nguyễn Khuyến', 'Hồ Chí Minh', 'VN', '102', '70014'),
	(1085, 'Phạm Hùng', 'Hà Nội', 'VN', '30', '10006'),
	(1086, 'Trần Cao Vân', 'Đà Nẵng', 'VN', '15', '55004'),
	(1087, 'Nguyễn Cư Trinh', 'Hồ Chí Minh', 'VN', '99', '70015'),
	(1088, 'Nam Kỳ Khởi Nghĩa', 'Hồ Chí Minh', 'VN', '88B', '70016'),
	(1089, 'Nguyễn Văn Cừ', 'Hà Nội', 'VN', '22', '10007'),
	(1090, 'Võ Nguyên Giáp', 'Đà Nẵng', 'VN', '9', '55005'),
	(1091, 'Lê Hồng Phong', 'Hải Phòng', 'VN', '25', '18004'),
	(1092, 'Nguyễn Ái Quốc', 'Hồ Chí Minh', 'VN', '120', '70017'),
	(1093, 'Bùi Thị Xuân', 'Đà Lạt', 'VN', '11', '67001'),
	(1094, 'Đông Kinh Nghĩa Thục', 'Hà Nội', 'VN', '17A', '10008'),
	(1095, 'Hàm Long', 'Hà Nội', 'VN', '6B', '10009'),
	(1096, 'Lê Đức Thọ', 'Hồ Chí Minh', 'VN', '244', '123123'),
	(1097, 'Lê Đức Thọ', 'Hồ Chí Minh', 'VN', '1', '696969'),
	(1098, 'Điện Biên Phủ', 'Hồ Chí Minh', 'VN', '614', '123321');

-- Dumping structure for table works.candidate
CREATE TABLE IF NOT EXISTS `candidate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qfut8ruekode092nlkipgl09g` (`email`),
  UNIQUE KEY `UK_9i5yt1gvm0chg5e10qkns7tll` (`phone`),
  UNIQUE KEY `UK_m8qhlm4wu215gr34dhxp0dour` (`address`),
  UNIQUE KEY `UK67uyxu00tx9l55fptjvodc0gl` (`user_id`),
  CONSTRAINT `FKa8gnyyhbb2qnhp94grci1n0o9` FOREIGN KEY (`address`) REFERENCES `address` (`id`),
  CONSTRAINT `FKj9h7beyp5gsdtdb20km82b4fl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1023 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.candidate: ~35 rows (approximately)
INSERT INTO `candidate` (`id`, `dob`, `email`, `full_name`, `phone`, `address`, `user_id`) VALUES
	(1018, '2000-01-01', 'momo@gmail.com', 'Nguyễn Văn A', '0987654321', 1046, 1),
	(1020, '1998-08-15', 'tranthimet@gmail.com', 'Trần Thị Mẹt', '0808150888', 1048, 4),
	(1021, '0000-00-00', 'nguyenthimen@gmail.com', 'Nguyễn Thị Mén', '0987687686', 1, 5),
	(1023, '1990-01-15', 'nguyenvana1050@gmail.com', 'Nguyễn Văn A', '0901000001', 1050, 10),
	(1024, '1992-02-25', 'tranthib1051@gmail.com', 'Trần Thị B', '0902000002', 1051, 11),
	(1025, '1994-03-30', 'phamvanh1052@gmail.com', 'Phạm Văn H', '0903000003', 1052, 12),
	(1026, '1996-04-18', 'lethic1053@gmail.com', 'Lê Thị C', '0904000004', 1053, 13),
	(1027, '1998-05-20', 'dangvand1054@gmail.com', 'Đặng Văn D', '0905000005', 1054, 14),
	(1028, '2000-06-10', 'huynhtie1055@gmail.com', 'Huỳnh Tiến E', '0906000006', 1055, 15),
	(1029, '2001-07-01', 'vuongminh1056@gmail.com', 'Vương Minh F', '0907000007', 1056, 16),
	(1030, '1995-08-12', 'ngothig1057@gmail.com', 'Ngô Thị G', '0908000008', 1057, 17),
	(1031, '1989-09-22', 'buituanh1058@gmail.com', 'Bùi Tuấn H', '0909000009', 1058, 18),
	(1032, '1993-10-03', 'lyminhl1059@gmail.com', 'Lý Minh L', '0910000010', 1059, 19),
	(1033, '1997-11-15', 'nguyenduy1060@gmail.com', 'Nguyễn Duy M', '0911000011', 1060, 20),
	(1034, '1999-12-25', 'phanlanh1061@gmail.com', 'Phan Lan H', '0912000012', 1061, 21),
	(1035, '2000-01-01', 'dangthuytien1062@gmail.com', 'Đặng Thúy Tiên', '0913000013', 1062, 22),
	(1036, '1990-07-04', 'hoangthanh1063@gmail.com', 'Hoàng Thành', '0914000014', 1063, 23),
	(1037, '1985-06-20', 'dinhquanghuy1064@gmail.com', 'Đinh Quang Huy', '0915000015', 1064, 24),
	(1038, '2001-09-19', 'trieuminhanh1065@gmail.com', 'Triệu Minh Anh', '0916000016', 1065, 25),
	(1039, '1998-03-07', 'nguyenbaoquoc1066@gmail.com', 'Nguyễn Bảo Quốc', '0917000017', 1066, 26),
	(1040, '1992-11-11', 'tranhuongly1067@gmail.com', 'Trần Hương Ly', '0918000018', 1067, 27),
	(1041, '1995-05-21', 'phamthithanhha1068@gmail.com', 'Phạm Thị Thanh Hà', '0919000019', 1068, 28),
	(1042, '1999-12-10', 'levanson1069@gmail.com', 'Lê Văn Sơn', '0920000020', 1069, 29),
	(1043, '1991-08-13', 'huynhtieuyet1070@gmail.com', 'Huỳnh Tiểu Uyên', '0921000021', 1070, 30),
	(1044, '2003-02-24', 'dangquynhan1071@gmail.com', 'Đặng Quỳnh An', '0922000022', 1071, 31),
	(1045, '1996-01-29', 'vuongthienkim1072@gmail.com', 'Vương Thiện Kim', '0923000023', 1072, 32),
	(1046, '1990-10-18', 'nguyencongson1073@gmail.com', 'Nguyễn Công Sơn', '0924000024', 1073, 33),
	(1047, '1997-04-09', 'tranthanhvan1074@gmail.com', 'Trần Thanh Vân', '0925000025', 1074, 34),
	(1048, '2002-06-12', 'phamminhtu1075@gmail.com', 'Phạm Minh Tú', '0926000026', 1075, 35),
	(1049, '2000-05-30', 'lequochung1076@gmail.com', 'Lê Quốc Hưng', '0927000027', 1076, 36),
	(1050, '1988-12-01', 'dangngocthuy1077@gmail.com', 'Đặng Ngọc Thúy', '0928000028', 1077, 37),
	(1051, '2004-01-25', 'nguyenthituyet1078@gmail.com', 'Nguyễn Thị Tuyết', '0929000029', 1078, 38),
	(1052, '1995-07-05', 'trankhanhly1079@gmail.com', 'Trần Khánh Ly', '0930000030', 1079, 39),
	(1053, '1998-02-14', 'phamthithuthao1080@gmail.com', 'Phạm Thị Thu Thảo', '0931000031', 1080, 40),
	(1070, '2003-10-13', 'thanhnhutcu@gmail.com', 'Nguyễn Thanh Nhứt', '0901407421', 1097, 42);

-- Dumping structure for table works.candidate_skill
CREATE TABLE IF NOT EXISTS `candidate_skill` (
  `applied_date` date DEFAULT NULL,
  `more_infos` varchar(1000) DEFAULT NULL,
  `skill_level` enum('ADVANCED','BEGINNER','INTERMEDIATE','MASTER','PROFESSIONAL') NOT NULL,
  `candidate_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`candidate_id`,`skill_id`),
  KEY `FKb7cxhiqhcah7c20a2cdlvr1f8` (`skill_id`),
  CONSTRAINT `FKb7cxhiqhcah7c20a2cdlvr1f8` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`skill_id`),
  CONSTRAINT `FKijjf42p0sh2c2na28g5aalx2p` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.candidate_skill: ~11 rows (approximately)
INSERT INTO `candidate_skill` (`applied_date`, `more_infos`, `skill_level`, `candidate_id`, `skill_id`) VALUES
	('2024-11-29', NULL, 'ADVANCED', 1018, 12),
	('2024-11-30', NULL, 'ADVANCED', 1018, 14),
	('2024-12-02', NULL, 'INTERMEDIATE', 1020, 17),
	('2024-12-06', NULL, 'ADVANCED', 1021, 20),
	('2024-12-06', NULL, 'INTERMEDIATE', 1021, 21),
	('2024-12-06', NULL, 'BEGINNER', 1021, 22),
	('2024-12-14', NULL, 'ADVANCED', 1023, 25),
	('2024-12-14', NULL, 'PROFESSIONAL', 1024, 26),
	('2024-12-14', NULL, 'BEGINNER', 1024, 27),
	('2024-12-02', NULL, 'ADVANCED', 1066, 18),
	('2024-12-14', NULL, 'ADVANCED', 1066, 22),
	('2024-12-14', NULL, 'ADVANCED', 1070, 28),
	('2024-12-14', NULL, 'INTERMEDIATE', 1070, 29),
	('2024-12-14', NULL, 'BEGINNER', 1070, 32);

-- Dumping structure for table works.company
CREATE TABLE IF NOT EXISTS `company` (
  `comp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about` text DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `comp_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `web_url` varchar(255) DEFAULT NULL,
  `address` bigint(20) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comp_id`),
  UNIQUE KEY `UK_rvp2hunsq4sgmpxe3a7i1ym3m` (`address`),
  UNIQUE KEY `UKg82ixrst2tc542u5s214ggpdf` (`user_id`),
  CONSTRAINT `FKd5occp4cjwihejbxdbpvkp5tv` FOREIGN KEY (`address`) REFERENCES `address` (`id`),
  CONSTRAINT `FKdy4v2yb46hefqicjpyj7b7e4s` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.company: ~4 rows (approximately)
INSERT INTO `company` (`comp_id`, `about`, `email`, `comp_name`, `phone`, `web_url`, `address`, `logo`, `user_id`) VALUES
	(1, 'Công ty ', 'congtya@gmail.com', 'Công ty TNHH Eight One International', '0987654321', 'https://www.topcv.vn/cong-ty/cong-ty-tnhh-eight-one-international/150848.html', 1049, 'https://cdn-new.topcv.vn/unsafe/140x/https://static.topcv.vn/company_logos/O8O6w0f8nPS5gHFy0fywrjOTIaVyRnTE_1691486170____b0b1161dbd47daeb25f957967fc480f6.png', NULL),
	(2, 'A subsidiary of the FPT Group, FPT Software is known as a leading global information technology service provider headquartered in Vietnam. With over 30,000 employees working in 83 offices across 30 countries on five continents, FPT Software consistently delivers the best solutions to more than 1000 clients, including 100 Fortune 500 companies. Placing human resources as the cornerstone of its achievements, employee experience is our top priority in continually creating an innovative, open, and enjoyable work environment for every member.', 'fptsoftware@gmail.com', 'FPT Software', '0909090909', 'https://www.topcv.vn/cong-ty/fpt-software/3.html', 1041, 'https://cdn-new.topcv.vn/unsafe/150x/https://static.topcv.vn/company_logos/fpt-software-6073b38a10cb4.jpg', 41),
	(3, 'BioTuring is an innovative bioinformatics company that develops algorithms, and software to serve scientists from hundreds of pharmaceutical companies and leading research institutions, enabling discoveries about human diseases.', 'bioturing@gmail.com', 'BioTurning', '0912345678', 'https://bioturing.com/', 1, 'https://i.ibb.co/xh9kR6z/Bio-Turing-2.webp', NULL),
	(23, 'MoMo là siêu ứng dụng hàng đầu và là một trong những Fintech phát triển nhanh nhất tại Việt Nam với nhiều giải thưởng trong nước và quốc tế. ', 'momo@gmail.com', 'CÔNG TY CỔ PHẦN DỊCH VỤ DI ĐỘNG TRỰC TUYẾN (VÍ MOMO)', '0987654321', 'https://momo.vn/', 1035, 'https://cdn-new.topcv.vn/unsafe/80x/https://static.topcv.vn/company_logos/cong-ty-co-phan-dich-vu-di-dong-truc-tuyen-vi-momo-61a741d87b682.jpg', 1),
	(27, 'Tập đoàn Viễn thông Quân đội Viettel - giữ vững vị thế Nơi làm việc tốt nhất Việt Nam trong 06 năm liên tiếp trong ngành Viễn thông, Hạ tầng, IT (theo khảo sát Anphabe) là nhà cung cấp dịch vụ số toàn cầu, luôn đi đầu trong đổi mới sáng tạo và luôn lắng nghe, thấu hiểu để đem tới những dịch vụ tốt nhất cho khách hàng.', 'viettel@gmail.com', 'TẬP ĐOÀN CÔNG NGHIỆP - VIỄN THÔNG QUÂN ĐỘI', '0987654321', 'https://viettel.com.vn', 1098, 'https://cdn-new.topcv.vn/unsafe/140x/https://static.topcv.vn/company_logos/vnksUvAUJEzIxB7un4tTZrQ0cNWK6MAt_1719996933____3c701bdf079634d0e15cebed0655ae9d.png', 43);

-- Dumping structure for table works.experience
CREATE TABLE IF NOT EXISTS `experience` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `work_description` varchar(2000) DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsmv65lqovssalk12ti3cqkpjf` (`candidate_id`),
  CONSTRAINT `FKsmv65lqovssalk12ti3cqkpjf` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.experience: ~7 rows (approximately)
INSERT INTO `experience` (`id`, `company_name`, `from_date`, `role`, `to_date`, `work_description`, `candidate_id`) VALUES
	(7, 'FPT', '2021-01-01', 'Technical Lead', '2023-01-01', 'Technical Lead ', 1018),
	(8, 'IUH', '2015-01-01', 'Student', '2019-01-01', 'I am a Student in there', 1020),
	(9, 'Amazon', '2020-01-01', 'Intern', '2023-01-01', 'Intern Java', 1020),
	(10, 'IUH', '2021-01-01', 'Student', '2024-12-06', 'I am a Student there', 1021),
	(11, 'Momo', '2024-12-06', 'Intern', '2025-02-01', 'Intern ', 1021),
	(12, 'Amazon', '1994-01-01', 'Leader', '2003-01-01', 'Leader', 1023),
	(13, 'IUH', '2025-01-01', 'Student', '2029-01-01', 'Student', 1024),
	(14, 'IUH', '2021-01-01', 'Student', '2024-12-14', 'Student', 1070),
	(15, 'Amazon', '2024-01-01', 'Intern', '2024-12-14', 'Intern Java', 1070);

-- Dumping structure for table works.job
CREATE TABLE IF NOT EXISTS `job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_desc` varchar(2000) NOT NULL,
  `job_name` varchar(255) NOT NULL,
  `company` bigint(20) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`job_id`),
  KEY `FKbaqlvluu78phmo9ld89um7wnm` (`company`),
  CONSTRAINT `FKbaqlvluu78phmo9ld89um7wnm` FOREIGN KEY (`company`) REFERENCES `company` (`comp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.job: ~7 rows (approximately)
INSERT INTO `job` (`job_id`, `job_desc`, `job_name`, `company`, `salary`, `status`) VALUES
	(1, 'Design, develop, and maintain secure and scalable Java-based applications for the banking domain, ensuring compliance with industry standards and regulations.', 'Java Developer', 1, '$1000', 1),
	(2, '5+ years of experience required in related field (i.e. Computer Science, Computer Engineering, and Engineering);', 'Senior Java Engineer', 2, '$3000', 1),
	(3, 'Design efficient data structure focusing on scalability & performance for extremely large biological data on GPU via CUDA.', 'Algorithm Engineer (Python, C, Algorithm)', 3, '$600', 1),
	(17, 'Develop software applications', 'Software Developer', 23, '$1500', 1),
	(18, 'Consulting technical solution/ system architect on Linux OS;\r\nPlan, design, install, configure, troubleshoot, and manage our VMWare / cloud-based Linux servers;\r\nEnsuring the system and server operating smoothly;\r\nBuilding and maintaining stable operation for server system;\r\nMonitoring and maintaining system hardware and networks;\r\nEnsuring safety and security for system and network;\r\nPerforming others technical assigned task.', 'Software Engineer', 23, 'Thỏa thuận', 1),
	(19, 'Mô tả công việc\r\nTham gia phát triển các ứng dụng trên các nền tảng\r\nTham gia phân tích nghiệp vụ, yêu cầu từ khách hàng\r\nKiểm tra, fix bug sản phẩm\r\nNghiên cứu các công nghệ, giải pháp mới.', 'Thực Tập Sinh Lập Trình (C#, Java, .NET, ABAP,...)', 1, 'Thỏa thuận', 1),
	(21, '- Lập trình mã nguồn nghiệp vụ theo yêu cầu người dùng, yêu cầu hệ thống, thiết kế chi tiết ở mức tính năng.\r\n\r\n- Lập trình giao diện theo thiết kế giao diện có sẵn.\r\n\r\n- Phối hợp các thành viên khác trong nhóm tích hợp các tính năng hệ thống.\r\n\r\n- Phối hợp với bộ phận quản lý chất lượng đảm bảo chất lượng hệ thống.', 'Kỹ Sư Phát Triển Phần Mềm (C/C++/Java/Golang/Android/IOS)', 27, 'Thỏa thuận', 1);

-- Dumping structure for table works.job_application
CREATE TABLE IF NOT EXISTS `job_application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apply_at` date NOT NULL,
  `match_percentage` double NOT NULL,
  `status` int(11) NOT NULL,
  `candidate_id` bigint(20) NOT NULL,
  `job_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgq7rbq1q2388owxysfur67tfh` (`candidate_id`),
  KEY `FKdepcvxeq3gyb4438ws0qjycc7` (`job_id`),
  CONSTRAINT `FKdepcvxeq3gyb4438ws0qjycc7` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `FKgq7rbq1q2388owxysfur67tfh` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.job_application: ~7 rows (approximately)
INSERT INTO `job_application` (`id`, `apply_at`, `match_percentage`, `status`, `candidate_id`, `job_id`) VALUES
	(17, '2024-12-14', 16.666666666666664, 0, 1020, 18),
	(18, '2024-12-06', 33.33333333333333, 0, 1021, 18),
	(19, '2024-12-06', 50, 1, 1021, 17),
	(23, '2024-12-14', 25, 0, 1023, 2),
	(27, '2024-12-14', 0, 0, 1070, 2),
	(29, '2024-12-14', 50, 1, 1070, 21),
	(30, '2024-12-14', 0, 0, 1020, 21);

-- Dumping structure for table works.job_skill
CREATE TABLE IF NOT EXISTS `job_skill` (
  `more_infos` varchar(1000) DEFAULT NULL,
  `skill_level` enum('ADVANCED','BEGINNER','INTERMEDIATE','MASTER','PROFESSIONAL') NOT NULL,
  `job_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`job_id`,`skill_id`),
  KEY `FKj33qbbf3vk1lvhqpcosnh54u1` (`skill_id`),
  CONSTRAINT `FK9ix4wg520ii2gu2felxdhdup6` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `FKj33qbbf3vk1lvhqpcosnh54u1` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.job_skill: ~5 rows (approximately)
INSERT INTO `job_skill` (`more_infos`, `skill_level`, `job_id`, `skill_id`) VALUES
	('Experience with Java SE and EE', 'ADVANCED', 17, 1),
	('Experience leading project teams', 'INTERMEDIATE', 17, 2),
	('3 năm kinh nghiệm', 'ADVANCED', 18, 1),
	('', 'BEGINNER', 18, 3),
	('', 'ADVANCED', 18, 4),
	('3 năm kinh nghiệm', 'ADVANCED', 21, 28),
	('Tốt', 'INTERMEDIATE', 21, 30),
	('Tốt', 'ADVANCED', 21, 31);

-- Dumping structure for table works.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.role: ~5 rows (approximately)
INSERT INTO `role` (`role_id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'USER'),
	(3, 'COMPANY'),
	(4, 'CANDIDATE'),
	(9, 'COMPANY');

-- Dumping structure for table works.skill
CREATE TABLE IF NOT EXISTS `skill` (
  `skill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `skill_description` varchar(255) DEFAULT NULL,
  `skill_name` varchar(255) DEFAULT NULL,
  `type` enum('SOFT_SKILL','TECHNICAL_SKILL','UNSPECIFIED') DEFAULT NULL,
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.skill: ~18 rows (approximately)
INSERT INTO `skill` (`skill_id`, `skill_description`, `skill_name`, `type`) VALUES
	(1, 'Core programming language', 'Java', 'TECHNICAL_SKILL'),
	(2, 'Manage projects from start to finish', 'Project Management', NULL),
	(3, 'Have good Linux knowledge and experience managing large number of Linux servers, applications;', 'Linux ', NULL),
	(4, 'Problem solving skills', 'Problem solving skills', NULL),
	(12, NULL, 'Java', 'TECHNICAL_SKILL'),
	(13, NULL, 'SQL', 'TECHNICAL_SKILL'),
	(14, NULL, 'Python', 'TECHNICAL_SKILL'),
	(17, NULL, 'Java', 'TECHNICAL_SKILL'),
	(18, NULL, 'SQL', 'TECHNICAL_SKILL'),
	(20, NULL, 'Java', 'TECHNICAL_SKILL'),
	(21, NULL, 'SQL', 'TECHNICAL_SKILL'),
	(22, NULL, 'Python', 'TECHNICAL_SKILL'),
	(24, NULL, 'JavaScript', 'TECHNICAL_SKILL'),
	(25, NULL, 'Java', 'TECHNICAL_SKILL'),
	(26, NULL, 'Java', 'TECHNICAL_SKILL'),
	(27, NULL, 'Javascript', 'TECHNICAL_SKILL'),
	(28, NULL, 'Java', 'TECHNICAL_SKILL'),
	(29, NULL, 'SQL', 'TECHNICAL_SKILL'),
	(30, '', 'Golang', 'TECHNICAL_SKILL'),
	(31, '', 'Python', 'TECHNICAL_SKILL'),
	(32, NULL, 'Python', 'TECHNICAL_SKILL');

-- Dumping structure for table works.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.user: ~38 rows (approximately)
INSERT INTO `user` (`user_id`, `password`, `username`) VALUES
	(1, '$2a$12$RjIAy5vZfXaSfMcW.6S7z.D6qjhUsp83ssn.BhkngIRIWpXxa0aCK', 'admin'),
	(2, '$2a$12$pzHDzkAV/1Lrp/uRFMzlqe.Gjw8N/Ochcl0PWq5LE4c5suqb9RITC', 'demo'),
	(4, '$2a$12$2m00/mLBd9fW4n5LDO3GB.GolG0Y.jiGGORT.dEFjIgNJc5xL.pxC', 'met'),
	(5, '$2a$12$.iOaXZ53YN539KTFtwjzUOdj93F43wL8Wlz.7CQ4Dz9106zb7CZ0e', 'men'),
	(10, '$2a$12$.iOaXZ53YN539KTFtwjzUOdj93F43wL8Wlz.7CQ4Dz9106zb7CZ0e', 'nguyenvana1050'),
	(11, '$2a$12$.iOaXZ53YN539KTFtwjzUOdj93F43wL8Wlz.7CQ4Dz9106zb7CZ0e', 'tranthib1051'),
	(12, '$2a$12$.iOaXZ53YN539KTFtwjzUOdj93F43wL8Wlz.7CQ4Dz9106zb7CZ0e', 'phamvanh1052'),
	(13, '$2a$12$.iOaXZ53YN539KTFtwjzUOdj93F43wL8Wlz.7CQ4Dz9106zb7CZ0e', 'lethic1053'),
	(14, '$2a$12$.iOaXZ53YN539KTFtwjzUOdj93F43wL8Wlz.7CQ4Dz9106zb7CZ0e', 'dangvand1054'),
	(15, '$2a$12$.iOaXZ53YN539KTFtwjzUOdj93F43wL8Wlz.7CQ4Dz9106zb7CZ0e', 'huynhtie1055'),
	(16, '$2a$12$MWXTahKLQsCStOmCVNS/5O.x7', 'vuongminh1056'),
	(17, '$2a$12$TMTahKLQsCStOmCVNS/5O.x8', 'ngothig1057'),
	(18, '$2a$12$NMXTahKLQsCStOmCVNS/5O.x9', 'buituanh1058'),
	(19, '$2a$12$LMXTahKLQsCStOmCVNS/5O.x0', 'lyminhl1059'),
	(20, '$2a$12$PMXTahKLQsCStOmCVNS/5O.xA', 'nguyenduy1060'),
	(21, '$2a$12$EMXTahKLQsCStOmCVNS/5O.xB', 'phanlanh1061'),
	(22, '$2a$12$FMXTahKLQsCStOmCVNS/5O.xC', 'dangthuytien1062'),
	(23, '$2a$12$GQXTahKLQsCStOmCVNS/5O.xD', 'hoangthanh1063'),
	(24, '$2a$12$HQXTahKLQsCStOmCVNS/5O.xE', 'dinhquanghuy1064'),
	(25, '$2a$12$IMXTahKLQsCStOmCVNS/5O.xF', 'trieuminhanh1065'),
	(26, '$2a$12$JQXTahKLQsCStOmCVNS/5O.xG', 'nguyenbaoquoc1066'),
	(27, '$2a$12$KMXTahKLQsCStOmCVNS/5O.xH', 'tranhuongly1067'),
	(28, '$2a$12$LMXTahKLQsCStOmCVNS/5O.xI', 'phamthithanhha1068'),
	(29, '$2a$12$MMXTahKLQsCStOmCVNS/5O.xJ', 'levanson1069'),
	(30, '$2a$12$NQXTahKLQsCStOmCVNS/5O.xK', 'huynhtieuyet1070'),
	(31, '$2a$12$OMXTahKLQsCStOmCVNS/5O.xL', 'dangquynhan1071'),
	(32, '$2a$12$PMXTahKLQsCStOmCVNS/5O.xM', 'vuongthienkim1072'),
	(33, '$2a$12$QMXTahKLQsCStOmCVNS/5O.xN', 'nguyencongson1073'),
	(34, '$2a$12$RMXTahKLQsCStOmCVNS/5O.xO', 'tranthanhvan1074'),
	(35, '$2a$12$SMXTahKLQsCStOmCVNS/5O.xP', 'phamminhtu1075'),
	(36, '$2a$12$TMXTahKLQsCStOmCVNS/5O.xQ', 'lequochung1076'),
	(37, '$2a$12$UMXTahKLQsCStOmCVNS/5O.xR', 'dangngocthuy1077'),
	(38, '$2a$12$VMXTahKLQsCStOmCVNS/5O.xS', 'nguyenthituyet1078'),
	(39, '$2a$12$WMXTahKLQsCStOmCVNS/5O.xT', 'trankhanhly1079'),
	(40, '$2a$12$XMXTahKLQsCStOmCVNS/5O.xU', 'phamthithuthao1080'),
	(41, '$2a$12$flyQkjXE19X4j7XtTmjNBubyD2XXCfIqV/iB/3WX0zsyUsVvLOv.q', 'nhut123'),
	(42, '$2a$12$flyQkjXE19X4j7XtTmjNBubyD2XXCfIqV/iB/3WX0zsyUsVvLOv.q', 'nhut'),
	(43, '$2a$12$XBqZeyTi8toPizDYOgJjcuYW8LkXa0348ijAdb/Gys9gkwcRIOlDG', 'company');

-- Dumping structure for table works.users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table works.users_roles: ~40 rows (approximately)
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(43, 2),
	(1, 3),
	(43, 3),
	(2, 4),
	(4, 4),
	(5, 4),
	(10, 4),
	(11, 4),
	(12, 4),
	(13, 4),
	(14, 4),
	(15, 4),
	(16, 4),
	(17, 4),
	(18, 4),
	(19, 4),
	(20, 4),
	(21, 4),
	(22, 4),
	(23, 4),
	(24, 4),
	(25, 4),
	(26, 4),
	(27, 4),
	(28, 4),
	(29, 4),
	(30, 4),
	(31, 4),
	(32, 4),
	(33, 4),
	(34, 4),
	(35, 4),
	(36, 4),
	(37, 4),
	(38, 4),
	(39, 4),
	(40, 4),
	(41, 4),
	(42, 4);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
