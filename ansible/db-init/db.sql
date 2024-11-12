\c utilizadores;

CREATE TABLE IF NOT EXISTS loja (
idloja integer PRIMARY KEY,
localizacao character varying(255),
marca character varying(255),
nome character varying(255)
);

INSERT INTO loja(
idloja, localizacao, marca, nome)
VALUES (1, 'Braga', 'Zara', 'Zara');

