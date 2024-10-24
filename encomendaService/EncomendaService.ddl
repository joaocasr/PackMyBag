CREATE TABLE Encomenda (IDEncomenda SERIAL NOT NULL, LojaIDLoja int4 NOT NULL, ClienteIDCliente int4 NOT NULL, CodigoEncomenda varchar(255), DataEntrega varchar(255), Devolucao varchar(255), LocalEntrega varchar(255), Status varchar(255), PRIMARY KEY (IDEncomenda));
CREATE TABLE Cliente (IDCliente SERIAL NOT NULL, Nome varchar(255), Username varchar(255), Email varchar(255), PRIMARY KEY (IDCliente));
CREATE TABLE Loja (IDLoja SERIAL NOT NULL, Nome varchar(255), Marca varchar(255), Localizacao varchar(255), PRIMARY KEY (IDLoja));
CREATE TABLE Item (IDItem SERIAL NOT NULL, EncomendaIDEncomenda int4 NOT NULL, Codigo varchar(255), Designacao varchar(255), Preco float8, Nraquisicoes int4 NOT NULL, Estilo varchar(255), Cor varchar(255), Tamanho varchar(255), Genero varchar(255), PRIMARY KEY (IDItem));
ALTER TABLE Encomenda ADD CONSTRAINT FKEncomenda932418 FOREIGN KEY (ClienteIDCliente) REFERENCES Cliente (IDCliente);
ALTER TABLE Encomenda ADD CONSTRAINT FKEncomenda214548 FOREIGN KEY (LojaIDLoja) REFERENCES Loja (IDLoja);
ALTER TABLE Item ADD CONSTRAINT FKItem368403 FOREIGN KEY (EncomendaIDEncomenda) REFERENCES Encomenda (IDEncomenda);
