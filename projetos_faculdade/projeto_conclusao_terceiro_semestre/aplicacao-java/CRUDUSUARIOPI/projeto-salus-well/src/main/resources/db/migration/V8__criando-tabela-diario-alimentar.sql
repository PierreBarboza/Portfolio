CREATE TABLE diario_alimentar (
    id INT IDENTITY(1,1) PRIMARY KEY,
    descricao VARCHAR(80),
    qtd_calorias DECIMAL(10,2),
    periodo VARCHAR(80),
    data_criada DATE,
    data_consumir DATE,
    data_consumida DATETIME,
    consumido TINYINT,
    alimento_id INT,
    nutricionista_id INT,
    cliente_id INT,
    FOREIGN KEY (alimento_id) REFERENCES alimentos(id),
    FOREIGN KEY (nutricionista_id) REFERENCES nutricionistas(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);
