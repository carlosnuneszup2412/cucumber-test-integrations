create table CONTA
(
    ID    bigint GENERATED ALWAYS AS IDENTITY,
    STATUS integer,
    TIPO   integer,
    primary key(ID)
);

create table CLIENTE
(
    ID    bigint GENERATED ALWAYS AS IDENTITY,
    CPF   varchar(255),
    EMAIL    varchar(255),
    NOME     varchar(255),
    CONTA_ID bigint,
    UNIQUE(CPF),
    primary key(ID),
    CONSTRAINT CONTA_ID FOREIGN KEY(ID) REFERENCES CONTA

);

--INSERT INTO CONTA( status, tipo)
--VALUES(0,0);

--INSERT INTO CLIENTE(cpf, email, nome, conta_id)
--VALUES ('98098120061','carlos@test.com', 'Carlos', 1);
