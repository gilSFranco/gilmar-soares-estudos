INSERT INTO USUARIOS (id, username, password, role) VALUES (100, 'ana@email.com.br', '$2a$12$Md/si7CF9Lnz/VW4C0qMG.Y1yqIfSt0UDeAxp0O.BqktnVr80zbk2', 'ROLE_ADMIN');
INSERT INTO USUARIOS (id, username, password, role) VALUES (101, 'bia@email.com.br', '$2a$12$Md/si7CF9Lnz/VW4C0qMG.Y1yqIfSt0UDeAxp0O.BqktnVr80zbk2', 'ROLE_CLIENTE');
INSERT INTO USUARIOS (id, username, password, role) VALUES (102, 'bob@email.com.br', '$2a$12$Md/si7CF9Lnz/VW4C0qMG.Y1yqIfSt0UDeAxp0O.BqktnVr80zbk2', 'ROLE_CLIENTE');

INSERT INTO CLIENTES (id, nome, cpf, id_usuario) VALUES (21, 'Beatriz Rodrigues', '22113796058', 101);
INSERT INTO CLIENTES (id, nome, cpf, id_usuario) VALUES (22, 'Rodrigo Silva', '43158940019', 102);

INSERT INTO VAGAS (id, codigo, status) VALUES (100, 'A-01', 'OCUPADA');
INSERT INTO VAGAS (id, codigo, status) VALUES (200, 'A-02', 'OCUPADA');
INSERT INTO VAGAS (id, codigo, status) VALUES (300, 'A-03', 'OCUPADA');
INSERT INTO VAGAS (id, codigo, status) VALUES (400, 'A-04', 'OCUPADA');
INSERT INTO VAGAS (id, codigo, status) VALUES (500, 'A-05', 'OCUPADA');

INSERT INTO CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    VALUES ('20240916-112000', 'FIT-1020', 'FIAT', 'PALIO', 'VERDE', '2024-09-14 11:20:00', 22, 100);

INSERT INTO CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    VALUES ('20240917-113000', 'SIE-1020', 'HONDA', 'SIENA', 'BRANCO', '2024-09-14 11:20:00', 21, 200);

INSERT INTO CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    VALUES ('20240918-114000', 'AIY-1020', 'UNO', 'SAVERO', 'AZUL', '2024-09-14 11:20:00', 22, 300);

INSERT INTO CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    VALUES ('20240919-115000', 'TSH-1020', 'HONDA', 'SIENA', 'AMARELO', '2024-09-14 11:20:00', 21, 400);

INSERT INTO CLIENTES_TEM_VAGAS (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
    VALUES ('20240920-116000', 'ALM-1020', 'UNO', 'SAVERO', 'VERMELHO', '2024-09-14 11:20:00', 22, 500);