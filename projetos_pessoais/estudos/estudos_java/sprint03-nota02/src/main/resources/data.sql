INSERT INTO escritor
    (nome_escritor, data_nasc)
VALUES
    ('J. R. R. Tolkien', '1944-02-01'),
    ('Leonel Caldela', '1974-02-13'),
    ('Rick Riordan', '1966-11-18');

INSERT INTO livro
    (nome_livro, escritor_id, best_seller)
VALUES
    ('Retorno do rei', 1, true),
    ('Duas torres', 1, true),
    ('Sociedade no anel', 1, true),
    ('Ozob', 2, true),
    ('Percy', 3, false);
