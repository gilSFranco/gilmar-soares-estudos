-- CRIAR E USAR O BANCO
CREATE DATABASE reaprendendoMYSQL;
USE reaprendendoMYSQL;

SET SQL_SAFE_UPDATES = 0;

-- DROP
DROP DATABASE reaprendendoMYSQL;
DROP TABLE tabela;

-- CRIAR TABELA
CREATE TABLE tabela(
	codigoTabela INT(8) NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	nomeTabela VARCHAR(60) NOT NULL,
    numeroTabela INT(4) NOT NULL,
    dataTabela DATE NOT NULL
);

CREATE TABLE outraTabela(
	codigoOutraTabela INT(8) NOT NULL PRIMARY KEY AUTO_INCREMENT, 
	nomeOutraTabela VARCHAR(60) NOT NULL,
    codigoTabela INT NOT NULL,
    FOREIGN KEY (codigoTabela) REFERENCES tabela (codigoTabela)
);

-- ALTERAR TABELA
ALTER TABLE tabela ADD COLUMN sobrenomeTabela VARCHAR(120);

-- VISUALIZAR TABELA
SELECT * FROM tabela;
SELECT * FROM outraTabela;
SELECT * FROM tabela 
WHERE nomeTabela = "Pessoas";

-- JOIN's
SELECT tabela.*, outraTabela.nomeOutraTabela FROM tabela
JOIN outraTabela
ON tabela.codigoTabela = outraTabela.codigoTabela;

SELECT tabela.*, outraTabela.nomeOutraTabela FROM tabela
INNER JOIN outraTabela
ON tabela.codigoTabela = outraTabela.codigoTabela;

SELECT tabela.*, outraTabela.nomeOutraTabela FROM tabela
LEFT JOIN outraTabela
ON tabela.codigoTabela = outraTabela.codigoTabela;

SELECT tabela.*, outraTabela.nomeOutraTabela FROM tabela
RIGHT JOIN outraTabela
ON tabela.codigoTabela = outraTabela.codigoTabela;

-- INSERIR NA TABELA
INSERT INTO tabela (nomeTabela, numeroTabela, dataTabela) 
VALUES ("Funcionario", 1234, "2024-08-14");

INSERT INTO outraTabela (nomeOutraTabela, codigoTabela) 
VALUES ("Endereco", 1);

-- UPDATE NA TABELA
UPDATE tabela SET nomeTabela = "Usuario" WHERE nomeTabela = "Funcionario";

-- DELETE NA TABELA
DELETE FROM tabela WHERE codigoTabela = 4;

-- AGGREGATION FUNCTIONS E ALIAS
SELECT SUM(numeroTabela) AS SOMA_DOS_NUMEROS FROM tabela;
SELECT COUNT(*) AS QUANTIDADE_TABELAS FROM tabela;
SELECT COUNT(*) AS QUANTIDADE_TABELAS_USUARIO FROM tabela WHERE nomeTabela = "Usuario";
SELECT nomeTabela AS NOME_TABELA, COUNT(*) AS QUANTIDADE_DE_TABELAS FROM tabela GROUP BY nomeTabela;
SELECT CONCAT("O nome da tabela é: " , nomeTabela) AS DESCRICAO FROM tabela;
SELECT
DAY(dataTabela) AS DIA,
MONTH(dataTabela) AS MES,
YEAR(dataTabela) AS ANO
FROM tabela;