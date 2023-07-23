CREATE TABLE alimentos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(80) UNIQUE,
    tipo VARCHAR(80),
    diabete TINYINT,
    colesterol TINYINT,
    hipertensao TINYINT,
    proteina DECIMAL(10,2),
    carboidrato DECIMAL(10,2),
    gordura_total DECIMAL(10,2),
    calorias DECIMAL(10,2)
);
