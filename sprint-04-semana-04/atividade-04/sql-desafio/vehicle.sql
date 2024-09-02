USE sistemaGestao;

CREATE TABLE Vehicle(
	plateVehicle VARCHAR(7) NOT NULL PRIMARY KEY,
    exemptedVehicle BOOLEAN NOT NULL,
    codeCategory INT NOT NULL,
    codeMonthlyPayer INT,
    CONSTRAINT FK_Category FOREIGN KEY (codeCategory) REFERENCES Category (codeCategory),
    CONSTRAINT FK_MonthlyPayer FOREIGN KEY (codeMonthlyPayer) REFERENCES MonthlyPayer (codeMonthlyPayer)
);

-- VEHICLE
INSERT INTO Vehicle (plateVehicle, exemptedVehicle, codeCategory, codeMonthlyPayer) VALUES
("EFGH654", false, 1, 1);

SELECT * FROM Vehicle;
SELECT * FROM Vehicle WHERE plateVehicle = "EFGH654";

-- INNER JOIN
SELECT 
Vehicle.plateVehicle, Vehicle.exemptedVehicle,
Category.nameCategory, MonthlyPayer.monthlyFeeMonthlyPayer,
MonthlyPayer.currentMonthMonthlyPayer, MonthlyPayer.monthlyFeePaidMonthlyPayer
FROM
Vehicle
INNER JOIN
Category
ON
Vehicle.codeCategory = Category.codeCategory
INNER JOIN
MonthlyPayer
ON
Vehicle.codeMonthlyPayer = MonthlyPayer.codeMonthlyPayer
ORDER BY MonthlyPayer.currentMonthMonthlyPayer;
-- INNER JOIN

UPDATE Vehicle SET exemptedVehicle = false, codeCategory = 1, codeMonthlyPayer = 1
WHERE plateVehicle = "EFGH654";
DELETE FROM Vehicle WHERE plateVehicle = "EFGH654";
-- VEHICLE