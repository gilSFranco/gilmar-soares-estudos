INSERT INTO USUARIOS (id, username, password, role) VALUES (100, 'ana@email.com', '$2a$12$Md/si7CF9Lnz/VW4C0qMG.Y1yqIfSt0UDeAxp0O.BqktnVr80zbk2', 'ROLE_ADMIN');
INSERT INTO USUARIOS (id, username, password, role) VALUES (101, 'bia@email.com', '$2a$12$Md/si7CF9Lnz/VW4C0qMG.Y1yqIfSt0UDeAxp0O.BqktnVr80zbk2', 'ROLE_CLIENTE');
INSERT INTO USUARIOS (id, username, password, role) VALUES (102, 'bob@email.com', '$2a$12$Md/si7CF9Lnz/VW4C0qMG.Y1yqIfSt0UDeAxp0O.BqktnVr80zbk2', 'ROLE_CLIENTE');
INSERT INTO USUARIOS (id, username, password, role) VALUES (103, 'toby@email.com', '$2a$12$Md/si7CF9Lnz/VW4C0qMG.Y1yqIfSt0UDeAxp0O.BqktnVr80zbk2', 'ROLE_CLIENTE');

INSERT INTO CLIENTES (id, nome, cpf, id_usuario) VALUES (10, 'Bianca Silva', '01606396030', 101);
INSERT INTO CLIENTES (id, nome, cpf, id_usuario) VALUES (20, 'Roberto Gomes', '54837862039', 102);