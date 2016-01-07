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

CREATE TABLE easy4Times4(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE normal4Times4(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE hard4Times4(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE easy5Times5(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE normal5Times5(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

CREATE TABLE hard5Times5(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600)
);

-- for puzzle generation process

CREATE TABLE puzzletemplates(
	id int NOT NULL PRIMARY KEY,
	puzzle varchar(600),
	solution varchar(600),
	holecount int
);