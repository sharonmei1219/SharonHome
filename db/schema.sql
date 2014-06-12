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


CREATE TABLE lastcslcr(
	level varchar(10) NOT NULL PRIMARY KEY,
	lastcslcrnumber int
);

INSERT INTO lastcslcr(level, lastcslcrnumber) VALUES ('easy', 0);
INSERT INTO lastcslcr(level, lastcslcrnumber) VALUES ('normal', 0);
INSERT INTO lastcslcr(level, lastcslcrnumber) VALUES ('hard', 0);
INSERT INTO lastcslcr(level, lastcslcrnumber) VALUES ('evil', 0);