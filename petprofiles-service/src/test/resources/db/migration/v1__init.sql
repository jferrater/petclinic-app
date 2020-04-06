SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- --------------------------------
-- Table structure for pet_profiles
-- --------------------------------
DROP TABLE IF EXISTS `pet_profiles`;
CREATE TABLE `pet_profiles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `owner` varchar(255) NOT NULL,
  `veterinarian` varchar(255) NOT NULL,
  `clinic_location` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'fluffy', 'dodong', 'alice', 'SOMA', 'brown color, hairy, cute, poodle breed');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'browny', 'bob', 'alice', 'SOMA', 'brown color, st bernard breed');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'chopper', 'joffry', 'alice', 'SOMA', 'dalmatian breed, black and white');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'soba', 'jolly', 'alice', 'SOMA', 'black, hairy, siberian husky breed');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'samsam', 'joffry', 'wella', 'VETE', 'white with black dots, terrier breed');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'chiko', 'mongkoy', 'wella', 'VETE', 'small askal breed, white');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'hatchi', 'alvin', 'wella', 'VETE', 'labrador breed, yellowish');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'kaikai', 'joy', 'wella', 'VETE', 'askal breed, orange color');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'nicole', 'justin', 'wella', 'VETE', 'golden retriever, white');
INSERT INTO `pet_profiles` (`date_created`, `date_updated`, `name`, `owner`, `veterinarian`, `clinic_location`, `description`) VALUES(now(), now(), 'whitey', 'debby', 'alice', 'SOMA', 'beagle breed, brown white and black color');