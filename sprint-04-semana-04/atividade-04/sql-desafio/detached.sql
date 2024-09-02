DROP DATABASE sistemaGestao;
CREATE DATABASE sistemaGestao;
USE sistemaGestao;

CREATE TABLE Detached(
	plateDetached VARCHAR(7) NOT NULL PRIMARY KEY,
    amountToPayDetached DOUBLE NOT NULL,
    entryTime DATETIME NOT NULL,
    departureTime DATETIME NOT NULL
);

-- DETACHED
INSERT INTO Detached (plateDetached, amountToPayDetached, entryTime, departureTime) VALUES
("ABCD321", 2000.00, "2024-08-14 14:30:20", "2024-08-14 15:30:40");

SELECT * FROM Detached;
SELECT * FROM Detached WHERE plateDetached = "ABCD321";

UPDATE Detached SET amountToPayDetached = 399.00, entryTime = "2024-09-14 14:00:20", departureTime = "2024-09-14 15:30:00"
WHERE plateDetached = "ABCD321";
DELETE FROM Detached WHERE plateDetached = "ABCD321"; 
-- DETACHED