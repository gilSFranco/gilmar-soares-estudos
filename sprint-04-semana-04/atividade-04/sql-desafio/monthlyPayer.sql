USE sistemaGestao;

CREATE TABLE MonthlyPayer(
	codeMonthlyPayer INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    monthlyFeeMonthlyPayer DOUBLE NOT NULL,
    currentMonthMonthlyPayer DATE NOT NULL,
    monthlyFeePaidMonthlyPayer BOOLEAN NOT NULL
);

-- MONTHLYPAYER
INSERT INTO MonthlyPayer (monthlyFeeMonthlyPayer, currentMonthMonthlyPayer, monthlyFeePaidMonthlyPayer) VALUES
(250.00, "2024-08-14", true);

SELECT * FROM MonthlyPayer;
SELECT * FROM MonthlyPayer WHERE codeMonthlyPayer = 1;

UPDATE MonthlyPayer SET monthlyFeeMonthlyPayer = 450.00, currentMonthMonthlyPayer = "2024-04-18", monthlyFeePaidMonthlyPayer = false
WHERE codeMonthlyPayer = 1;
DELETE FROM MonthlyPayer WHERE codeMonthlyPayer = 1;
-- MONTHLYPAYER