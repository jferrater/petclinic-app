SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- --------------------------------
-- Table structure for pet_profiles
-- --------------------------------
DROP TABLE IF EXISTS `pet_profiles`;
CREATE TABLE `pet_profiles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `owner` varchar(255) NOT NULL,
  `veterinarian` varchar(255) NOT NULL,
  `clinic_location` varchar(255) NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;