DROP TABLE IF EXISTS `dinosaurs`;

CREATE TABLE dinosaurs(
id long AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
type VARCHAR(100) NOT NULL,
height INT NOT NULL,
length INT NOT NULL,
carnivore boolean NOT NULL,
herbivore boolean NOT NULL,
omnivore boolean NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO `dinosaurs` (`name`, `type`, `height`, `length`, `carnivore`, `herbivore`, `omnivore`) VALUES ('Tyrannosaurus', 'Theropod', 12, 31, true, false, false);
INSERT INTO `dinosaurs` (`name`, `type`, `height`, `length`, `carnivore`, `herbivore`, `omnivore`) VALUES ('Velociraptor', 'Theropod', 5, 6, true, false, false);

