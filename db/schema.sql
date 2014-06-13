CREATE TABLE easypuzzle(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE normalpuzzle(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE hardpuzzle(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE evilpuzzle(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

-- for puzzle generation process

CREATE TABLE puzzletemplates(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600),
	solution varchar(600),
	nrOfHoles int
);