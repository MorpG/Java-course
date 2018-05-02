CREATE TABLE Transmission (
  id         INTEGER PRIMARY KEY AUTOINCREMENT,
  trans_name VARCHAR(50) NOT NULL
);

CREATE TABLE Engine (
  id       INTEGER PRIMARY KEY AUTOINCREMENT,
  eng_name VARCHAR(50) NOT NULL
);

CREATE TABLE Gear_box (
  id       INTEGER PRIMARY KEY AUTOINCREMENT,
  box_name VARCHAR(50) NOT NULL
);

CREATE TABLE Car (
  id          INTEGER PRIMARY KEY AUTOINCREMENT,
  car_name    VARCHAR(50) NOT NULL,
  trans_id    INTEGER REFERENCES Transmission (id),
  engine_id   INTEGER REFERENCES Engine (id),
  gear_box_id INTEGER REFERENCES Gear_box (id)
);

INSERT INTO Transmission (trans_name) VALUES ('Race');
INSERT INTO Transmission (trans_name) VALUES ('Road');
INSERT INTO Transmission (trans_name) VALUES ('Off-road');
INSERT INTO Transmission (trans_name) VALUES ('Golf');

INSERT INTO Engine (eng_name) VALUES ('Gasoline');
INSERT INTO Engine (eng_name) VALUES ('Diesel');
INSERT INTO Engine (eng_name) VALUES ('Electric');
INSERT INTO Engine (eng_name) VALUES ('Sun');

INSERT INTO Gear_box (box_name) VALUES ('Automatic');
INSERT INTO Gear_box (box_name) VALUES ('Manual');
INSERT INTO Gear_box (box_name) VALUES ('Robot');
INSERT INTO Gear_box (box_name) VALUES ('Driver');

INSERT INTO Car (car_name, trans_id, engine_id, gear_box_id) VALUES ('Race Car', 1, 1, 2);
INSERT INTO Car (car_name, trans_id, engine_id, gear_box_id) VALUES ('Road Car', 2, 2, 1);
INSERT INTO Car (car_name, trans_id, engine_id, gear_box_id) VALUES ('Sport Car', 1, 1, 3);


SELECT
  table_car.car_name,
  trans_table.trans_name,
  engine_table.eng_name,
  box_table.box_name
FROM Car AS table_car
  LEFT OUTER JOIN Transmission AS trans_table ON table_car.trans_id = trans_table.id
  LEFT OUTER JOIN Engine AS engine_table ON table_car.engine_id = engine_table.id
  LEFT OUTER JOIN Gear_box AS box_table ON table_car.gear_box_id = box_table.id;


SELECT
  trans_name,
  'Transmission' AS 'PART'
FROM Transmission
  LEFT OUTER JOIN Car ON Transmission.id = Car.trans_id
WHERE Car.id ISNULL

UNION

SELECT
  eng_name,
  'Engine' AS 'PART'
FROM Engine
  LEFT OUTER JOIN Car ON Engine.id = Car.trans_id
WHERE Car.id ISNULL

UNION
SELECT
  box_name,
  'Gear Box' AS 'PART'
FROM Gear_box
  LEFT OUTER JOIN Car ON Gear_box.id = Car.gear_box_id
WHERE Car.id ISNULL;