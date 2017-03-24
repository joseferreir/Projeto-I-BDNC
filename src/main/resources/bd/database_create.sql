CREATE EXTENSION postgis;
CREATE EXTENSION postgis_topology;

CREATE TABLE Usuario(
       id SERIAL,
       nome  VARCHAR(62) NOT NULL,
       local GEOMETRY(Point, 26910) NOT NULL,
       idade VARCHAR(25) NOT NULL,
       renda VARCHAR(25) NOT NULL,
       escolaridade VARCHAR(25) NOT NULL,
       email VARCHAR(60) NOT NULL UNIQUE,
       senha VARCHAR(18) NOT NULL UNIQUE, --email e senha deve ser not null e unique
       sexo VARCHAR(10) NOT NULL,
       eh_admin BOOLEAN DEFAULT 'FALSE',
       PRIMARY KEY(id)
);

CREATE TABLE Enquete(
        id SERIAL,
        pergunta VARCHAR(150) NOT NULL UNIQUE,
        PRIMARY KEY(id)
);

CREATE TABLE Voto(
        id SERIAL,
        id_usuario INT,
        id_enquete INT,
        resposta INT NOT NULL,
        FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE SET NULL,
        FOREIGN KEY (id_enquete) REFERENCES Enquete(id) ON DELETE  CASCADE,
        UNIQUE(id_usuario,id_enquete),
        PRIMARY KEY(id)
)

INSERT INTO usuario (nome,local, idade, renda,  escolaridade,email,senha,sexo)
             VALUES ('admin admin',ST_GeomFromText('POINT(-6.8912042 -38.37587320000001)', 26910),'MAIOR_DE_60_ANOS','ENTRE_1_E_2_SALARIOS','FUNDAMENTAL_INCOMPLETO','Admin@gmail.com','adm1234','MASCULINO');



