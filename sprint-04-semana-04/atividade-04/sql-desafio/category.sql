USE sistemaGestao;

CREATE TABLE Category(
	codeCategory INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nameCategory VARCHAR(60) NOT NULL
);

-- CATEGORY
INSERT INTO Category (nameCategory) VALUES
("Mensalista"),
("Caminhão de Entrega"),
("Servidor Público");

SELECT * FROM Category;
SELECT * FROM Category WHERE codeCategory = 1;

UPDATE Category SET nameCategory = "Mensalista" WHERE codeCategory = 1;
DELETE FROM Category WHERE codeCategory = 1;
-- CATEGORY