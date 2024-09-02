USE sistemaGestao;

CREATE TABLE Registration(
	codeRegistration INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	entranceCancelRegistration INT NOT NULL,
    exitCancelRegistration INT NOT NULL,
    vacancyRegistration INT NOT NULL,
    plateVehicle VARCHAR(7) NOT NULL,
    CONSTRAINT FK_Vehicle FOREIGN KEY (plateVehicle) REFERENCES Vehicle (plateVehicle)
);

-- REGISTRATION
INSERT INTO Registration (entranceCancelRegistration, exitCancelRegistration, vacancyRegistration, plateVehicle) VALUES
(2, 2, 2, "EFGH654");

SELECT * FROM Registration;
SELECT * FROM Registration WHERE codeRegistration = 1;

-- INNER JOIN
SELECT
Registration.*, Vehicle.exemptedVehicle,
Category.nameCategory, MonthlyPayer.monthlyFeeMonthlyPayer,
MonthlyPayer.currentMonthMonthlyPayer, MonthlyPayer.monthlyFeePaidMonthlyPayer
FROM
Registration
INNER JOIN
Vehicle
ON
Registration.plateVehicle = Vehicle.plateVehicle
INNER JOIN
Category
ON
Vehicle.codeCategory = Category.codeCategory
INNER JOIN
MonthlyPayer
ON
Vehicle.codeMonthlyPayer = MonthlyPayer.codeMonthlyPayer
ORDER BY codeRegistration;
-- INNER JOIN

SELECT * FROM Registration;
SELECT * FROM Registration WHERE codeRegistration = 3;

UPDATE Registration SET entranceCancelRegistration = 4, exitCancelRegistration = 8, vacancyRegistration = 2, plateVehicle = "EFGH654" WHERE codeRegistration = 1;
DELETE FROM Registration WHERE codeRegistration = 1;
-- REGISTRATION