CREATE DATABASE projetoInd;

USE projetoInd;

CREATE TABLE usuario(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    email VARCHAR(45),
    senha VARCHAR(45),
    sistemaFav VARCHAR(45),
    freq VARCHAR(45)
);

CREATE TABLE sistema(
	idSistema INT PRIMARY KEY AUTO_INCREMENT,
    primeiraOpcao VARCHAR(45),
    segundaOpcao VARCHAR(45),
    fkUsuario INT,
    foreign key (fkUsuario) references usuario(idUsuario)
);

CREATE TABLE apendise(
	idapendise INT PRIMARY KEY AUTO_INCREMENT,
    raca VARCHAR(45),
    classe VARCHAR(45),
    valorURL VARCHAR(200)
);

CREATE TABLE personagem (
	idPersonagem INT PRIMARY KEY AUTO_INCREMENT,
    nomePersonagem VARCHAR(45),
    historiaPersonagem VARCHAR(300),
    fkApendise INT, 
    foreign key (fkApendise) references apendise (idapendise),
    fkUsuario INT,
	foreign key (fkUsuario) references usuario (idUsuario)
);

insert into apendise values 
(null, 'Anão', 'Arqueiro','../assets/classes e raças/medieval/anaoArqueiro.jpg'),
(null, 'Anão', 'Barbaro','../assets/classes e raças/medieval/anaoBarbaro.jpg'),
(null, 'Anão', 'Bardo','../assets/classes e raças/medieval/anaoBarbo.jpg'),
(null, 'Anão', 'Bruxo','../assets/classes e raças/medieval/anaoBruxo.jpg'),
(null, 'Anão', 'Druida','../assets/classes e raças/medieval/anaoDruida.jpg'),
(null, 'Anão', 'Guerreiro','../assets/classes e raças/medieval/anaoGuerreiro.jpg');

insert into apendise values 
(null, 'Elfo', 'Arqueiro','../assets/classes e raças/medieval/elfoArqueiro.png'),
(null, 'Elfo', 'Barbaro','../assets/classes e raças/medieval/elfoBarbaro.png'),
(null, 'Elfo', 'Bardo','../assets/classes e raças/medieval/elfobardo.webp'),
(null, 'Elfo', 'Bruxo','../assets/classes e raças/medieval/elfoBruxo.jpg'),
(null, 'Elfo', 'Druida','../assets/classes e raças/medieval/elfoDruida.jpg'),
(null, 'Elfo', 'Guerreiro','../assets/classes e raças/medieval/elfoGuerreiro.jpg');

insert into apendise values 
(null, 'Humano', 'Arqueiro','../assets/classes e raças/medieval/humanoArqueiro.jpg'),
(null, 'Humano', 'Barbaro','../assets/classes e raças/medieval/humanoBarbaro.webp'),
(null, 'Humano', 'Bardo','../assets/classes e raças/medieval/humanoBardo.webp'),
(null, 'Humano', 'Bruxo','../assets/classes e raças/medieval/humanoBruxo.jpg'),
(null, 'Humano', 'Druida','../assets/classes e raças/medieval/humanoDruida.jpg'),
(null, 'Humano', 'Guerreiro','../assets/classes e raças/medieval/humanoGuerreiro.jpg');

insert into apendise values 
(null, 'Orc', 'Arqueiro','../assets/classes e raças/medieval/arqueiroOrc.jpg'),
(null, 'Orc', 'Barbaro','../assets/classes e raças/medieval/orcBarbaro.jpg'),
(null, 'Orc', 'Bardo','../assets/classes e raças/medieval/orcBardo.jpg'),
(null, 'Orc', 'Bruxo','../assets/classes e raças/medieval/orcBruxo.jpg'),
(null, 'Orc', 'Druida','../assets/classes e raças/medieval/orcDruida.jpg'),
(null, 'Orc', 'Guerreiro','../assets/classes e raças/medieval/orcGuerreiro.jpg');