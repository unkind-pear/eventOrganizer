create database eventos_db;
use eventos_db;

create table organizador(
	id int auto_increment,
    CNPJ varchar(14) unique,
    nome varchar(100),
    email varchar(100),
    constraint id_pk primary key(id)
);

create table evento(
    id int auto_increment,
    nome varchar(100),
    descricao text,
    data date,
    capacidade_maxima int,
    id_organizador int,
    constraint id_PK primary key(id),
    constraint organizador_FK foreign key(id_organizador) references organizador(id)
);

create table sala(
	id int auto_increment,
    id_evento int,
    numero int,
    andar int,
    capacidade_maxima int,
    constraint id_sala_pk primary key(id),
    constraint fk_evento foreign key(id_evento) references evento(id)
);

create table conta(
    id int auto_increment,
    nome varchar(60) unique,
    senha varchar(10),
    saldo double,
    data_nascimento date,
    idade int,
    nome_cartao varchar(60),
    numero_cartao bigint,
    numero_seguranca_cartao int(3),
    data_validade_cartao date,
    constraint id_PK primary key(id)
);

create table tipo_ingresso(
    id int auto_increment,
    tipo varchar(60),
    preco double,
    id_evento int,
    constraint evento_FK foreign key(id_evento) references evento(id),
    constraint id_PK primary key(id)
);

create table compra(
    id int auto_increment,
    data datetime,
    valor_total double,
    id_conta int,
    id_tipo_ingresso int,
    id_evento int,
    constraint conta_FK foreign key(id_conta) references conta(id),
    constraint tipo_ingresso_FK foreign key(id_tipo_ingresso) references tipo_ingresso(id),
    constraint fkEvento foreign key(id_evento) references evento(id),
    constraint id_PK primary key(id)
);

create table telefone_organizador(
    id int auto_increment,
    id_organizador int,
    telefone bigint,
    constraint id_PK primary key(id),
    constraint id_organizador_fk foreign key(id_organizador) references organizador(id)
);

-- Inserir dados na tabela 'organizador'
INSERT INTO organizador (CNPJ, nome, email)
VALUES
    ('1234567890123', 'Empresa Organizadora 1', 'organizador1@email.com'),
    ('9876543210987', 'Empresa Organizadora 2', 'organizador2@email.com'),
    ('5555555555555', 'Empresa Organizadora 3', 'organizador3@email.com'),
    ('7777777777777', 'Empresa Organizadora 4', 'organizador4@email.com'),
    ('9999999999999', 'Empresa Organizadora 5', 'organizador5@email.com');

-- Inserir dados na tabela 'conta'
INSERT INTO conta (nome, senha, saldo, data_nascimento, idade, nome_cartao, numero_cartao, numero_seguranca_cartao, data_validade_cartao)
VALUES
    ('Usuario 1', 'senha1', 100.0, '1990-05-15', 33, 'Titular Cartão 1', 1234567890123456, 123, '2025-12-01'),
    ('Usuario 2', 'senha2', 50.0, '1985-10-20', 38, 'Titular Cartão 2', 9876543210987654, 456, '2024-08-01'),
    ('Usuario 3', 'senha3', 200.0, '1995-03-02', 28, 'Titular Cartão 3', 5555555555555555, 789, '2023-06-01'),
    ('Usuario 4', 'senha4', 75.0, '1980-12-10', 42, 'Titular Cartão 4', 7777777777777777, 234, '2026-10-01'),
    ('Usuario 5', 'senha5', 300.0, '1992-08-30', 31, 'Titular Cartão 5', 9999999999999999, 567, '2024-11-01');
    
-- Inserir dados na tabela 'evento'
INSERT INTO evento (nome, descricao, data, capacidade_maxima, id_organizador)
VALUES
    ('Evento 1', 'Descrição do Evento 1', '2023-10-25', 500, 1),
    ('Evento 2', 'Descrição do Evento 2', '2023-11-15', 300, 2),
    ('Evento 3', 'Descrição do Evento 3', '2024-01-05', 800, 1),
    ('Evento 4', 'Descrição do Evento 4', '2024-03-20', 400, 3),
    ('Evento 5', 'Descrição do Evento 5', '2024-05-10', 600, 2);
    
-- Inserir dados na tabela 'sala'
INSERT INTO sala (id_evento, numero, andar, capacidade_maxima)
VALUES
    (1, 101, 1, 100),
    (1, 102, 1, 150),
    (2, 201, 2, 80),
    (3, 301, 3, 200),
    (4, 401, 4, 120);

-- Inserir dados na tabela 'tipo_ingresso'
INSERT INTO tipo_ingresso (tipo, preco, id_evento)
VALUES
    ('VIP', 100.0, 1),
    ('Padrão', 50.0, 1),
    ('Premium', 120.0, 2),
    ('Acessibilidade', 30.0, 3),
    ('Estudante', 40.0, 4);

-- Inserir dados na tabela 'compra'
INSERT INTO compra (data, valor_total, id_conta, id_tipo_ingresso, id_evento)
VALUES
    ('2023-10-18 08:00:00', 100.0, 1, 1, 1),
    ('2023-10-18 09:30:00', 150.0, 2, 3, 2),
    ('2023-10-18 10:45:00', 60.0, 3, 4, 3),
    ('2023-10-18 11:15:00', 40.0, 4, 2, 4),
    ('2023-10-18 12:30:00', 200.0, 5, 1, 5);

-- Inserir dados na tabela 'telefone_organizador'
INSERT INTO telefone_organizador (id_organizador, telefone)
VALUES
    (1, 123456789012),
    (2, 987654321098),
    (3, 555555555555),
    (4, 777777777777),
    (5, 999999999999);
