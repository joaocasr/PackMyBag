docker exec -it postgres psql -U postgres -d catalogo -c "INSERT INTO loja (idloja, localizacao, marca, nome)
VALUES
(1, 'Lisboa', 'Zara', 'Zara Colombo'),
(2, 'Porto', 'H&M', 'H&M NorteShopping'),
(3, 'Faro', 'Nike', 'Nike Algarve');"
docker exec -it postgres psql -U postgres -d catalogo -c "INSERT INTO item (iditem, codigo, cor, designacao, disponibilidade, estilo, genero, imagem, nraquisicoes, preco, tamanho, lojaidloja)
VALUES
(1, 'Z001', 'Red', 'T-shirt', 'In Stock', 'Casual', 'Unisex', 'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcRj8gtZhcUenayBIIKCmmvdMFi5C7EeHfcMtOc1Tc9oREZ4UxdzynR6a26muHulZNzTI9igenTwqbcvHQij4BVghxKqUWWGChPulkiTWzgOnjktYAgCGIxp', 10, 15.99, 'M', 1),
(2, 'Z002', 'Black', 'Jeans', 'In Stock', 'Casual', 'Male', 'hB8bakmo2CfFHqvy8X721v7GFGWs62ydGjzLYmiD0GzoSV5ykPQMUBs3uqqe18o4bY4aEPxRttbyk6XWs9hYHEgrxS3zayHLuPCAtY7dcVwb4Qg', 5, 49.99, 'L', 1),
(3, 'H001', 'White,Yellow', 'CUTWORK POPLIN DRESS', 'In Stock', 'Formal', 'Female', 'https://www.chicos.com/Product_Images/570334431_3145_large.jpg', 0, 149.99, 'S', 2),
(4, 'N001', 'Yellow', 'Jeans', 'In Stock', 'Sport', 'Female', 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQQFyQsJWZ0DVVmY4_nXQBfa6n9nyooX2ok4OkPw42zo3xmWBOR', 7, 69.99, '42', 3),
(5, 'N002', 'White', 'Blouse', 'In Stock', 'Formal', 'Female', 'https://lebbse.com/cdn/shop/files/H34.webp?v=1725221117', 0, 199.99, 'S', 2);"

docker exec -it postgres psql -U postgres -d catalogo -c "INSERT INTO peca (itemiditem) VALUES 
(1), (2), (4), (5);"

docker exec -it postgres psql -U postgres -d catalogo -c "INSERT INTO set (nr_pecas, itemiditem)
VALUES
(2, 3);"

docker exec -it postgres psql -U postgres -d catalogo -c "INSERT INTO set_peca (peca_itemiditem, set_itemiditem)
VALUES
(4, 3),
(5, 3);"




