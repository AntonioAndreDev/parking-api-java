insert into USUARIOS (id, username, password, role)
values (100, 'antonio@gmail.com', '$2y$10$xialBtoAW10LFOhL8yshIeR4pp3eeAGdQxL2Wu8yj8LlSrm/pe32.', 'ROLE_ADMIN');
insert into USUARIOS (id, username, password, role)
values (101, 'giuli@gmail.com', '$2y$10$xialBtoAW10LFOhL8yshIeR4pp3eeAGdQxL2Wu8yj8LlSrm/pe32.', 'ROLE_USER');
insert into USUARIOS (id, username, password, role)
values (102, 'toin@gmail.com', '$2y$10$xialBtoAW10LFOhL8yshIeR4pp3eeAGdQxL2Wu8yj8LlSrm/pe32.', 'ROLE_USER');
insert into USUARIOS (id, username, password, role)
values (103, 'toby@gmail.com', '$2y$10$xialBtoAW10LFOhL8yshIeR4pp3eeAGdQxL2Wu8yj8LlSrm/pe32.', 'ROLE_USER');

insert into CLIENTES (id, nome, cpf, id_usuario)
values (11, 'Giuli', '23265409058', 101);
insert into CLIENTES (id, nome, cpf, id_usuario)
values (12, 'Toin', '61385517026', 102);