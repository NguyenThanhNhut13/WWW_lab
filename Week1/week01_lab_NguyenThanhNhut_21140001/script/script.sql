-- Dumping database structure for mydb
CREATE DATABASE IF NOT EXISTS `week1` /*!40100 DEFAULT CHARACTER SET latin1
COLLATE latin1_swedish_ci */;
USE `week1`;
-- Dumping structure for table mydb.account
CREATE TABLE IF NOT EXISTS `account` (
`account_id` varchar(50) NOT NULL,
`full_name` varchar(50) NOT NULL,
`password` varchar(50) NOT NULL,
`email` varchar(50) DEFAULT NULL,
`phone` varchar(50) DEFAULT NULL,
`status` tinyint(4) NOT NULL DEFAULT 1,
PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table mydb.log: ~0 rows (approximately)
-- Dumping structure for table mydb.role
CREATE TABLE IF NOT EXISTS `role` (
`role_id` varchar(50) NOT NULL,
`role_name` varchar(50) NOT NULL,
`description` varchar(50) DEFAULT NULL,
`status` tinyint(4) NOT NULL,
PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=LATIN1_SWEDISH_CI;

-- Dumping structure for table mydb.grant_access
CREATE TABLE IF NOT EXISTS `grant_access` (
`role_id` varchar(50) NOT NULL,
`account_id` varchar(50) NOT NULL,
`is_grant` bit(1) NOT NULL DEFAULT b'1',
`note` varchar(250) DEFAULT '',
PRIMARY KEY (`role_id`,`account_id`),
KEY `account_grant` (`account_id`),
CONSTRAINT `account_grant` FOREIGN KEY (`account_id`) REFERENCES `account`
(`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `role_grant` FOREIGN KEY (`role_id`) REFERENCES `role`
(`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=LATIN1_SWEDISH_CI;

-- Dumping structure for table mydb.log
CREATE TABLE IF NOT EXISTS `log` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`account_id` varchar(50) NOT NULL,
`login_time` datetime NOT NULL DEFAULT current_timestamp(),
`logout_time` datetime NOT NULL DEFAULT current_timestamp(),
`notes` varchar(250) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci COMMENT='ghi logs';
-- Dumping data for table mydb.account: ~2 rows (approximately)
INSERT INTO `account` (`account_id`, `full_name`, `password`, `email`,
`phone`, `status`) VALUES
('met', 'Tran Thi Met', '123', 'met@gmail.com', '0904567890', 1),
('teo', 'NGuyen Van Teo', '123', 'teo@gmail.com', '0903123456', 1);
-- Dumping data for table mydb.role: ~2 rows (approximately)
INSERT INTO `role` (`role_id`, `role_name`, `description`, `status`) VALUES
('admin', 'administrator', 'admin role', 1),
('user', 'user', 'user role', 1);
-- Dumping data for table mydb.grant_access: ~2 rows (approximately)
INSERT INTO `grant_access` (`role_id`, `account_id`, `is_grant`, `note`)
VALUES
('admin', 'teo', b'1', ''),
('user', 'met', b'1', '');

