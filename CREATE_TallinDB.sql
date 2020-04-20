DROP DATABASE IF EXISTS tallinnbusdb;
		CREATE DATABASE tallinnbusdb;
                    USE	tallinnbusdb;

CREATE TABLE drivers (
	id 			BIGINT(30) 		AUTO_INCREMENT,
	fullName	VARCHAR(20) NOT NULL,
	address		VARCHAR(50) NOT NULL,
	phone		VARCHAR(30) NOT NULL,
	age			INT(3)		NOT NULL,
	height		DECIMAL(19,2)	NOT NULL,
	createdOn	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP, 
	PRIMARY KEY (id)
);

CREATE TABLE buses (
	id			BIGINT(30)			AUTO_INCREMENT,
	busNumber	VARCHAR(10) NOT NULL,
	driverId	BIGINT(30)			NOT NULL,
	fuel 		DECIMAL(19,2)	NOT NULL,
	createdOn	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP, 
	purchasedOn DATE		NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE locations (
	id			BIGINT(30)			AUTO_INCREMENT,
	stopName 	VARCHAR(30) NOT NULL,
	createdOn	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP, 
	PRIMARY KEY (id)
);

CREATE TABLE passengers (
	id 				BIGINT(30)			AUTO_INCREMENT,
	fullName		VARCHAR(30) NOT NULL,
	email			VARCHAR(30) NOT NULL,
	phoneNumber		VARCHAR(30) NOT NULL,
	age 			INT(3)		NOT NULL,
	startLocationId BIGINT(30) 		NOT NULL,
	stopLocationId	BIGINT(30) 		NOT NULL,
	busId 			BIGINT(30) 		NOT NULL,
	createdOn		TIMESTAMP 	DEFAULT CURRENT_TIMESTAMP, 
	PRIMARY KEY (id)
);

CREATE TABLE timetable (
	id			BIGINT(30)			AUTO_INCREMENT,
	weekday 	VARCHAR(30) NOT NULL,
	arrivalTime TIME        NOT NULL,
	locationId  BIGINT(30)         NOT NULL,
	busId       BIGINT(30)         NOT NULL,
	createdOn	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

ALTER TABLE buses 
  ADD CONSTRAINT FK_DriversBuses FOREIGN KEY (driverId) REFERENCES drivers(id) 
  ON DELETE CASCADE;

ALTER TABLE timetable
  ADD CONSTRAINT FK_TimetableBuses FOREIGN KEY (busId) REFERENCES buses(id)
  ON DELETE CASCADE;
  
  ALTER TABLE timetable
  ADD CONSTRAINT FK_TimetableLocations FOREIGN KEY (locationId) REFERENCES locations(id)
  ON DELETE CASCADE;
  
  ALTER TABLE passengers
  ADD CONSTRAINT FK_PassengersBuses FOREIGN KEY (busId) REFERENCES buses(id)
  ON DELETE CASCADE;
  
ALTER TABLE passengers
  ADD CONSTRAINT FK_PassengersStartLocations FOREIGN KEY (startLocationId) REFERENCES locations(id)
  ON DELETE CASCADE;
  
  ALTER TABLE passengers
  ADD CONSTRAINT FK_PassengersStopLocations FOREIGN KEY (stopLocationId) REFERENCES locations(id)
  ON DELETE CASCADE;

INSERT INTO drivers
			(fullName,				address,		phone,			age, 	height)
	 VALUES	('John Smith',			'Paepargi 10', 	'+372555555',	51,		176.2),
			('Stanislav Ratsinski',	'Pikk 10', 		'+3725566', 	32,		172),
			('Leonardo DaVinci',	'Tallinn',		'+3724222226',	40,		171),
			('Micelangelo',			'Tartu', 		'+3724333336', 	30,		165),
			('Bradd Pitt', 			'Punane 10', 	'+14000000', 	50,		185),
			('Donald Trump',		'White House',	'+10000000',	74,		180.1);

INSERT INTO buses 
			(busNumber,	driverId,	fuel,	purchasedOn)
	 VALUES	('20A',	 	2,			3.2,	'2019-03-21'),
			('16A',	 	1, 			5.2,	'2019-11-01'),
			('102A', 	3,			1.2,	'2019-05-11'),
			('20A',	 	5, 			1.2,	'2018-12-30'),
			('222A',	4, 			1.2,	'2016-10-05');
    
INSERT INTO locations
			(stopName)
	 VALUES	('Pihlaka'),
			('P. Kerese'),
			('Karusmarja'),
			('Värava'),
			('Liiva jaam'),
			('Risti'),
			('Kosmos');

INSERT INTO passengers
			(fullName,			email, 					phoneNumber, 	age, startLocationId,	stopLocationId, busId)
	 VALUES	('Magnus Carlsen',	'magnus@hotmail.com',	'+1500025',		31,	 2,					3, 				1),
			('Anatoly Karpov', 	'karpov@hotmail.com',	'+7560254',		65,	 4, 				5, 				2),
			('Maria II', 		'maria@hotmail.com',	'+372457865',	45,	 2, 				6, 				3),
			('Juri Ratas',		'ratas@hotmail.com', 	'+372457865',	45,	 5, 				6, 				3);

INSERT INTO timetable
			(busId,	locationId, weekday, 	arrivalTime)
	 VALUES	(1,	    2,	        'Monday',   '08:00:00'),
			(2, 	5,	        'Tuesday',	'10:00:00'),
			(3, 	4,	        'Friday',	'13:00:00'),
			(4,		3, 	        'Sunday',	'23:00:00');
