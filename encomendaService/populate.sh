docker exec -it postgres psql -U postgres -d encomenda -c "INSERT INTO cliente (IDCliente, Nome, Username, Email) VALUES
(1, 'João Silva', 'joaosilva', 'joao.silva@email.com'),
(2, 'Maria Santos', 'mariasantos', 'maria.santos@email.com'),
(3, 'Pedro Oliveira', 'pedrooliveira', 'pedro.oliveira@email.com'),
(4, 'Ana Costa', 'anacosta', 'ana.costa@email.com'),
(5, 'Luiz Souza', 'luizsouza', 'luiz.souza@email.com');"

docker exec -it postgres psql -U postgres -d encomenda -c "INSERT INTO Loja (IDLoja, Nome, Marca, Localizacao) VALUES
(1, 'Loja A', 'Marca X', 'Lisboa'),
(2, 'Loja B', 'Marca Y', 'Porto'),
(3, 'Loja C', 'Marca Z', 'Braga'),
(4, 'Loja D', 'Marca W', 'Coimbra'),
(5, 'Loja E', 'Marca V', 'Faro');"

# docker exec -it postgres psql -U postgres -d encomenda -c "INSERT INTO Encomenda (IDEncomenda, LojaIDLoja, ClienteIDCliente, Codigo_Encomenda, Data_Entrega, Data_Devolucao, Local_Entrega, Status, Preco) VALUES
# (1, 1, 1, 'ENC001', '2024-10-01', '2024-10-06', 'Rua A, 123', 'Entregue', 100.00),
# (2, 2, 2, 'ENC002', '2024-10-02', '2024-10-07', 'Rua B, 456', 'Devolvido', 200.00),
# (3, 3, 3, 'ENC003', '2024-10-03', '2024-10-08', 'Rua C, 789', 'Entregue', 150.50),
# (4, 4, 4, 'ENC004', '2024-10-04', '2024-10-09', 'Rua D, 101', 'Devolvido', 250.75),
# (5, 5, 5, 'ENC005', '2024-10-05', '2024-10-10', 'Rua E, 202', 'Entregue', 300.00),
# (6, 1, 2, 'ENC006', '2024-10-06', '2024-10-11', 'Rua F, 303', 'Entregue', 175.25),
# (7, 2, 3, 'ENC007', '2024-10-07', '2024-10-12', 'Rua G, 404', 'Devolvido', 225.50),
# (8, 3, 4, 'ENC008', '2024-10-08', '2024-10-13', 'Rua H, 505', 'Entregue', 125.00),
# (9, 4, 5, 'ENC009', '2024-10-09', '2024-10-14', 'Rua I, 606', 'Devolvido', 275.75),
# (10, 5, 1, 'ENC010', '2024-10-10', '2024-10-15', 'Rua J, 707', 'Entregue', 270.00);"

docker exec -it postgres psql -U postgres -d encomenda -c "INSERT INTO Encomenda (IDencomenda, LojaIDLoja, ClienteIDCliente, Codigo_Encomenda, Data_Entrega, Data_Devolucao, Local_Entrega, Status, Preco, Taxa_Entrega) VALUES
(1, 1, 1, 'ENC001', '2024-10-01', '2024-10-06', 'Rua A, 123', 'Entregue', 100.00, 5.00),
(2, 2, 2, 'ENC002', '2024-10-02', '2024-10-07', 'Rua B, 456', 'Devolvido', 200.00, 5.00),
(3, 3, 3, 'ENC003', '2024-10-03', '2024-10-08', 'Rua C, 789', 'Entregue', 150.50, 7.50),
(4, 4, 4, 'ENC004', '2024-10-04', '2024-10-09', 'Rua D, 101', 'Devolvido', 250.75, 5.50),
(5, 5, 5, 'ENC005', '2024-10-05', '2024-10-10', 'Rua E, 202', 'Entregue', 300.00, 5.00),
(6, 1, 2, 'ENC006', '2024-10-06', '2024-10-11', 'Rua F, 303', 'Entregue', 175.25, 5.75),
(7, 2, 3, 'ENC007', '2024-10-07', '2024-10-12', 'Rua G, 404', 'Devolvido', 225.50, 5.25),
(8, 3, 4, 'ENC008', '2024-10-08', '2024-10-13', 'Rua H, 505', 'Entregue', 125.00, 5.25),
(9, 4, 5, 'ENC009', '2024-10-09', '2024-10-14', 'Rua I, 606', 'Devolvido', 275.75, 5.75),
(10, 5, 1, 'ENC010', '2024-10-10', '2024-10-15', 'Rua J, 707', 'Entregue', 270.00, 5.50);"

docker exec -it postgres psql -U postgres -d encomenda -c "INSERT INTO Item (IDItem, EncomendaIDEncomenda, Codigo, Designacao, Preco, Nraquisicoes, Estilo, Cor, Tamanho, Genero, Imagem_Url) VALUES
(1, 1, 'IT001', 'Camisa', 19.99, 2, 'Casual', 'Azul', 'M', 'Masculino', 'http://example.com/imagens/IT001.jpg'),
(2, 2, 'IT002', 'Calças', 29.99, 1, 'Formal', 'Preto', 'L', 'Feminino', 'http://example.com/imagens/IT002.jpg'),
(3, 3, 'IT003', 'Jaqueta', 49.99, 3, 'Esportivo', 'Cinza', 'G', 'Masculino', 'http://example.com/imagens/IT003.jpg'),
(4, 4, 'IT004', 'Vestido', 39.99, 1, 'Elegante', 'Vermelho', 'M', 'Feminino', 'http://example.com/imagens/IT004.jpg'),
(5, 5, 'IT005', 'Sapatos', 59.99, 2, 'Formal', 'Preto', '42', 'Masculino', 'http://example.com/imagens/IT005.jpg'),
(6, 6, 'IT006', 'Blusa', 25.50, 2, 'Casual', 'Verde', 'S', 'Feminino', 'http://example.com/imagens/IT006.jpg'),
(7, 7, 'IT007', 'Shorts', 22.75, 1, 'Esportivo', 'Azul Marinho', 'M', 'Masculino', 'http://example.com/imagens/IT007.jpg'),
(8, 8, 'IT008', 'Saia', 30.00, 3, 'Elegante', 'Rosa', 'P', 'Feminino', 'http://example.com/imagens/IT008.jpg'),
(9, 9, 'IT009', 'Bermuda', 18.50, 2, 'Casual', 'Bege', 'L', 'Masculino', 'http://example.com/imagens/IT009.jpg'),
(10, 10, 'IT010', 'Camisa Polo', 35.00, 1, 'Casual', 'Branca', 'M', 'Masculino', 'http://example.com/imagens/IT010.jpg');"

# Acrescentando mais artigos à Encomenda ENC001 (IDEncomenda: 1) de joaosilva
docker exec -it postgres psql -U postgres -d encomenda -c "INSERT INTO Item (IDItem, EncomendaIDEncomenda, Codigo, Designacao, Preco, Nraquisicoes, Estilo, Cor, Tamanho, Genero, Imagem_Url) VALUES
(11, 1, 'IT011', 'Calça Jeans', 39.99, 1, 'Casual', 'Azul', '42', 'Masculino', 'http://example.com/imagens/IT011.jpg'),
(12, 1, 'IT012', 'Cinto de Couro', 25.50, 2, 'Formal', 'Marrom', 'M', 'Masculino', 'http://example.com/imagens/IT012.jpg');"

# Acrescentando mais artigos à Encomenda ENC010 (IDEncomenda: 10) de joaosilva
docker exec -it postgres psql -U postgres -d encomenda -c "INSERT INTO Item (IDItem, EncomendaIDEncomenda, Codigo, Designacao, Preco, Nraquisicoes, Estilo, Cor, Tamanho, Genero, Imagem_Url) VALUES
(13, 10, 'IT013', 'Sapato Social', 80.00, 1, 'Formal', 'Preto', '42', 'Masculino', 'http://example.com/imagens/IT013.jpg'),
(14, 10, 'IT014', 'Relógio', 150.00, 1, 'Esportivo', 'Prata', 'Unico', 'Unissex', 'http://example.com/imagens/IT014.jpg'),
(15, 10, 'IT015', 'Bolsa', 150.00, 1, 'Esportivo', 'Prata', 'Unico', 'Unissex', 'http://example.com/imagens/IT014.jpg'),
(16, 10, 'IT016', 'Camisa', 150.00, 1, 'Esportivo', 'Prata', 'Unico', 'Unissex', 'http://example.com/imagens/IT014.jpg');"