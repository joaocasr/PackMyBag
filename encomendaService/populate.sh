-- Inserindo dados na tabela Cliente
INSERT INTO Cliente (IDCliente, Nome, Username, Email) VALUES
(1, 'João Silva', 'joaosilva', 'joao.silva@email.com'),
(2, 'Maria Santos', 'mariasantos', 'maria.santos@email.com'),
(3, 'Pedro Oliveira', 'pedrooliveira', 'pedro.oliveira@email.com'),
(4, 'Ana Costa', 'anacosta', 'ana.costa@email.com'),
(5, 'Luiz Souza', 'luizsouza', 'luiz.souza@email.com');

-- Inserindo dados na tabela Loja
INSERT INTO Loja (IDLoja, Nome, Marca, Localizacao) VALUES
(1, 'Loja A', 'Marca X', 'Lisboa'),
(2, 'Loja B', 'Marca Y', 'Porto'),
(3, 'Loja C', 'Marca Z', 'Braga'),
(4, 'Loja D', 'Marca W', 'Coimbra'),
(5, 'Loja E', 'Marca V', 'Faro');

-- Inserindo dados na tabela Encomenda
INSERT INTO Encomenda (IDEncomenda, LojaIDLoja, ClienteIDCliente, CodigoEncomenda, DataEntrega, Devolucao, LocalEntrega, Status) VALUES
(1, 1, 1, 'ENC001', '2024-10-01', 'Não', 'Rua A, 123', 'Entregue'),
(2, 2, 2, 'ENC002', '2024-10-02', 'Sim', 'Rua B, 456', 'Devolvido'),
(3, 3, 3, 'ENC003', '2024-10-03', 'Não', 'Rua C, 789', 'Entregue'),
(4, 4, 4, 'ENC004', '2024-10-04', 'Sim', 'Rua D, 101', 'Devolvido'),
(5, 5, 5, 'ENC005', '2024-10-05', 'Não', 'Rua E, 202', 'Entregue');

-- Inserindo dados na tabela Item
INSERT INTO Item (IDItem, EncomendaIDEncomenda, Codigo, Designacao, Preco, Nraquisicoes, Estilo, Cor, Tamanho, Genero) VALUES
(1, 1, 'IT001', 'Camisa', 19.99, 2, 'Casual', 'Azul', 'M', 'Masculino'),
(2, 2, 'IT002', 'Calças', 29.99, 1, 'Formal', 'Preto', 'L', 'Feminino'),
(3, 3, 'IT003', 'Jaqueta', 49.99, 3, 'Esportivo', 'Cinza', 'G', 'Masculino'),
(4, 4, 'IT004', 'Vestido', 39.99, 1, 'Elegante', 'Vermelho', 'M', 'Feminino'),
(5, 5, 'IT005', 'Sapatos', 59.99, 2, 'Formal', 'Preto', '42', 'Masculino');
