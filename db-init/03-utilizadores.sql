\c utilizadores;

CREATE TABLE IF NOT EXISTS Cliente (
    IDCliente integer PRIMARY KEY,
    nome character varying(255),
    username character varying(255),
    email character varying(255),
    password character varying(255),
    profileImage character varying(255),
    morada character varying(255),
    cartaoCredito character varying(255),
    nrTelemovel character varying(255),
    genero character varying(255)
);


INSERT INTO Cliente (IDCliente, nome, username, email, password, profileImage, morada, cartaoCredito, nrTelemovel, genero)
VALUES
(1, 'Afonso Marques', 'axcm', 'axcm@mail.com', 'axcmpass', ' ', 'Rua Magnifica, 69, Bragalandia', '1234', '975867934', 'Male');
(2, 'Joao Castro', 'jony', 'jony@mail.com', 'jonypass', ' ', 'Rua Maravilhosa, 420, Portolandia', '5678', '967532143', 'Male');


CREATE TABLE IF NOT EXISTS Estilista(
    IDCliente integer PRIMARY KEY,
    nome character varying(255),
    username character varying(255),
    email character varying(255),
    password character varying(255),
    profileImage character varying(255),
    rating integer,
    genero character varying(255),
    Biografia character varying(355)
);


INSERT INTO estilista (IDCliente, nome, username, email, password, profileImage, rating, genero, bio)
VALUES
(3, 'Gonçalo Vale', 'gv', 'gv@mail.com', 'gvpass', ' ', 4, 'Male', 'Estilista incrivel e habilidoso'),
(4, 'Orlando Belo', 'ob', 'ob@mail.com', 'obpass', ' ', 3, 'Male', 'Estilista com muita experiencia no ramo');


\c recomendacoes;


CREATE TABLE IF NOT EXISTS pedido(
    IDPedido integer PRIMARY KEY,
    cliente character varying(255),
    estilos character varying(255),
    cores character varying(255),
    nrOutfits integer,
    orcamento real,
    peçasExcluidas character varying(255),
    fabricsPreferences character varying(255),
    occasions character varying(255)
);

