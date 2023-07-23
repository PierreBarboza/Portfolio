CREATE TABLE clientes (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(80),
    email VARCHAR(80) UNIQUE,
    senha VARCHAR(80),
    avaliacao DECIMAL(10,2),
    avatar INT,
    genero VARCHAR(15),
    endereco VARCHAR(80),
    contagem_avaliacao INT,
    ativo TINYINT,
    comorbidade TINYINT
);
