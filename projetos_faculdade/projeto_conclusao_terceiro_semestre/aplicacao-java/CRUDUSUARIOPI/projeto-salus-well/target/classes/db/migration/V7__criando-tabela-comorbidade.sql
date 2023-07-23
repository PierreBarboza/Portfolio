CREATE TABLE comorbidade (
    id INT IDENTITY(1,1) PRIMARY KEY,
    diabete TINYINT,
    colesterol TINYINT,
    hipertensao TINYINT,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);
