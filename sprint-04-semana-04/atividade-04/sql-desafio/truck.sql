USE sistemaGestao;

CREATE TABLE Truck(
	plateTruck VARCHAR(7) NOT NULL PRIMARY KEY
);

-- TRUCK
INSERT INTO Truck (plateTruck) VALUES ("IJKL987");

SELECT * FROM Truck;

SELECT * FROM Truck WHERE plateTruck = "IJKL987";

-- INNER JOIN TRUCK
SELECT Registration.codeRegistration, Registration.entranceCancelRegistration, Registration.exitCancelRegistration, Registration.vacancyRegistration, Truck.* FROM 
Registration
INNER JOIN
Truck
ON
Registration.plateTruck = Truck.plateTruck
WHERE Truck.plateTruck = "IJKL987";
-- INNER JOIN TRUCK

DELETE FROM Truck WHERE plateTruck = "IJKL987";
-- TRUCK