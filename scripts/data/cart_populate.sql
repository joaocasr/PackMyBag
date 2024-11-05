-- Reset sequences (they now increment by 50)
ALTER SEQUENCE IF EXISTS cartservice_cart_idcart_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS cartservice_cliente_idcliente_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS cartservice_pagamento_idpagamento_seq RESTART WITH 1;

-- Insert carts first
INSERT INTO cart (idcart) VALUES
(1),
(2),
(3),
(4),
(5);

-- Insert clients (cartidcart is NOT NULL)
INSERT INTO cliente (idcliente, nome, username, email, cartidcart) VALUES
(1, 'João Silva', 'joaosilva', 'joao.silva@email.com', 1),
(2, 'Maria Santos', 'mariasantos', 'maria.santos@email.com', 2),
(3, 'Pedro Costa', 'pedrocosta', 'pedro.costa@email.com', 3),
(4, 'Ana Oliveira', 'anaoliveira', 'ana.oliveira@email.com', 4),
(5, 'Miguel Pereira', 'miguelpereira', 'miguel.pereira@email.com', 5);

-- Insert items (added nr_pecas, modified data types)
INSERT INTO item (iditem, cartidcart, codigo, designacao, preco, quantidade, dtype, id_loja, nr_pecas) VALUES
(1, 1, 'P1', 'T-SHIRT ESTAMPADOS COMBINADOS', 19.95, 2, 'Item', 1, 1),
(2, 1, 'P2', 'T-SHIRT JACQUARD RISCAS', 25.95, 1, 'Item', 1, 1),
(3, 2, 'P3', 'T-SHIRT SHAPEWEAR DE TREINO', 22.95, 3, 'Item', 1, 1),
(4, 2, 'P4', 'CALÇAS CARGO REGULAR FIT', 39.95, 1, 'Item', 1, 1),
(5, 3, 'P5', 'CASACO BOMBER BÁSICO', 45.95, 1, 'Item', 1, 1),
(6, 4, 'P6', 'SAPATOS DERBY EM PELE', 69.95, 1, 'Item', 1, 1),
(7, 5, 'P7', 'BLAZER SLIM FIT', 89.95, 1, 'Item', 1, 1);

-- Insert payments (added moeda, dates are now VARCHAR)
INSERT INTO pagamento (idpagamento, clienteidcliente, codigo, total, local_entrega, inicio_aluguer, fim_aluguer, modo_pagamento, status, moeda) VALUES
(1, 1, 'PAY001', 65.85, 'Rua das Flores, 123', '2024-03-20', '2024-03-27', 'CREDIT_CARD', 'PENDING', 'EUR'),
(2, 2, 'PAY002', 108.80, 'Avenida Central, 45', '2024-03-19', '2024-03-26', 'PAYPAL', 'COMPLETED', 'EUR'),
(3, 3, 'PAY003', 45.95, 'Rua do Comércio, 78', '2024-03-18', '2024-03-25', 'CREDIT_CARD', 'COMPLETED', 'EUR'),
(4, 4, 'PAY004', 69.95, 'Praça da República, 15', '2024-03-17', '2024-03-24', 'DEBIT_CARD', 'PENDING', 'EUR'),
(5, 5, 'PAY005', 89.95, 'Rua Nova, 234', '2024-03-16', '2024-03-23', 'CREDIT_CARD', 'COMPLETED', 'EUR');

-- Update sequences
SELECT setval('cartservice_cart_idcart_seq', (SELECT MAX(idcart) FROM cart));
SELECT setval('cartservice_cliente_idcliente_seq', (SELECT MAX(idcliente) FROM cliente));
SELECT setval('cartservice_pagamento_idpagamento_seq', (SELECT MAX(idpagamento) FROM pagamento)); 