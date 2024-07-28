insert into USUARIOS (id, username, password, role)
values (100, 'antonio@gmail.com', '$2y$10$xialBtoAW10LFOhL8yshIeR4pp3eeAGdQxL2Wu8yj8LlSrm/pe32.', 'ROLE_ADMIN');
insert into USUARIOS (id, username, password, role)
values (101, 'giuli@gmail.com', '$2y$10$xialBtoAW10LFOhL8yshIeR4pp3eeAGdQxL2Wu8yj8LlSrm/pe32.', 'ROLE_USER');
insert into USUARIOS (id, username, password, role)
values (102, 'toin@gmail.com', '$2y$10$xialBtoAW10LFOhL8yshIeR4pp3eeAGdQxL2Wu8yj8LlSrm/pe32.', 'ROLE_USER');

insert into CLIENTES (id, nome, cpf, id_usuario)
values (21, 'Giuli', '09191773016', 101);
insert into CLIENTES (id, nome, cpf, id_usuario)
values (22, 'Toin', '98401203015', 102);

insert into vagas (id, codigo, status)
values (100, 'A-01', 'OCUPADA');
insert into vagas (id, codigo, status)
values (200, 'A-02', 'OCUPADA');
insert into vagas (id, codigo, status)
values (300, 'A-03', 'OCUPADA');
insert into vagas (id, codigo, status)
values (400, 'A-04', 'OCUPADA');
insert into vagas (id, codigo, status)
values (500, 'A-05', 'OCUPADA');

insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20230313-101300', 'FIT-1020', 'FIAT', 'PALIO', 'VERDE', '2023-03-13 10:15:00', 22, 100);
insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20230314-101400', 'SIE-1020', 'FIAT', 'SIENA', 'BRANCO', '2023-03-14 10:15:00', 21, 200);
insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20230315-101500', 'FIT-1030', 'FIAT', 'PALIO', 'VERDE', '2023-03-15 10:15:00', 22, 300);
insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20230316-101600', 'FIT-1040', 'FIAT', 'PALIO', 'VERDE', '2023-03-16 10:15:00', 21, 400);
insert into clientes_tem_vagas (numero_recibo, placa, marca, modelo, cor, data_entrada, id_cliente, id_vaga)
values ('20230317-101700', 'FIT-1050', 'FIAT', 'PALIO', 'VERDE', '2023-03-17 10:15:00', 22, 500);