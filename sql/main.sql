create database eventos_db;
use eventos_db;

create table organizador(
	id int auto_increment,
    CNPJ varchar(14) unique,
    nome varchar(100),
    email varchar(100),
    constraint id_pk primary key(id)
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

create table conta(
    id int auto_increment,
    nome varchar(60) unique,
    senha varchar(10),
    saldo double,
    data_nascimento date,
    idade int,
    nome_cartao varchar(60),
    numero_cartao int(16),
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
    telefone int(12),
    constraint id_PK primary key(id)
);

alter table telefone_organizador add constraint id_organizador_fk foreign key(id_organizador) references organizador(id);

insert into organizador(cnpj,nome,email) values ('202831/01','Arrocha eventos','arrochaeventos@gmail.com');
insert into organizador(cnpj,nome,email) values ('982731/02','Brothers eventos','brotherseventos1223@gmail.com');
insert into organizador(cnpj,nome,email) values ('934787/12','Oasis eventos','oasisevento12323@gmail.com');
insert into organizador(cnpj,nome,email) values ('372362/28','Brabos eventos','braboseventos112@gmail.com');
insert into organizador(cnpj,nome,email) values ('28827/32','Banana Brasil','bananabrasil1821@gmail.com');

insert into evento values ('Game Awards', 'Premiação dos melhores jogos do ano', '2023-07-12', 229000, 1);
insert into evento values ('Gamescon', 'Apresentação dos mais novos jogos do mercado', '2022-08-26', 456000, 1);
insert into evento values ('BGS', 'Encontro entre celebridades da internet e seus fãns', '2025-09-01', 867000, 2);
insert into evento values ('Casamento Fernanda', 'Nos reunimos para celebrar a união de Paulo e Fernanda, na Igreja de São Paulo', '2023-03-23', 125, 3);
insert into evento values ('Show do Iron Maiden', 'Venha conhecer a banda!', '2023-12-01', 98723492, 4);
insert into evento values ('Festa Junina', 'Comida e bebida à vontade!', '2023-06-24', 1000, 5);

insert into sala values (1, 230, 2, 100);
insert into sala values (1, 140, 1, 200);
insert into sala values (2, 150, 1, 150);
insert into sala values (3, 220, 2, 100);
insert into sala values (4, 180, 1, 100);
insert into sala values (5, 270, 2, 160);
insert into sala values (4, 190, 1, 100);
insert into sala values (5, 210, 2, 200);