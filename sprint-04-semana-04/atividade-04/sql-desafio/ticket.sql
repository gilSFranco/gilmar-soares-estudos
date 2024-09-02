USE sistemaGestao;

CREATE TABLE Ticket(
	codeTicket INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	entranceCancelTicket INT NOT NULL,
    exitCancelTicket INT NOT NULL,
    vacancyTicket INT NOT NULL,
    plateDetached VARCHAR(7) NOT NULL,
    CONSTRAINT FK_Ticket FOREIGN KEY (plateDetached) REFERENCES Detached (plateDetached)
);

-- TICKET
INSERT INTO Ticket (entranceCancelTicket, exitCancelTicket, vacancyTicket, plateDetached) VALUES
(2, 2, 2, "ABCD321");

SELECT * FROM Ticket;
SELECT * FROM Ticket WHERE codeTicket = 1;

-- INNER JOIN
SELECT Ticket.*, Detached.amountToPayDetached, Detached.entryTime, Detached.departureTime FROM
Ticket
INNER JOIN
Detached
ON
Ticket.plateDetached = Detached.plateDetached
WHERE Ticket.codeTicket = 1
ORDER BY Ticket.codeTicket;

SELECT Ticket.*, Detached.amountToPayDetached, Detached.entryTime, Detached.departureTime FROM
Ticket
INNER JOIN
Detached
ON
Ticket.plateDetached = Detached.plateDetached
WHERE Detached.plateDetached = "ABCD321"
ORDER BY Ticket.codeTicket;
-- INNER JOIN

UPDATE Ticket SET entranceCancelTicket = 4, exitCancelTicket = 8, vacancyTicket = 2, plateDetached = "ABCD321" WHERE codeTicket = 1;
DELETE FROM Ticket WHERE codeTicket = 1;
-- TICKET