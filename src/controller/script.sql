create table tb_mercado_usuarios (
    id serial primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    fav_items integer[],
    is_admin boolean not null
);

create table tb_mercado_produtos (
    id serial primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    category varchar(255) not null,
    price float not null,
    rating float not null,
    tags text[] not null,
    image bytea
);

create table tb_mercado_carrinho (
    id serial primary key,
    usuario_id int references tb_mercado_usuarios(id) on delete cascade not null,
    total_price float not null,
    quantity int not null
);

create table tb_mercado_item_carrinho (
    id serial primary key,
    carrinho_id int references tb_mercado_carrinho(id) on delete cascade not null,
    produto_id int references tb_mercado_produtos(id) on delete cascade not null,
    quantidade int not null
);

--drop table tb_mercado_produtos cascade;
--drop table tb_mercado_carrinho cascade;
--drop table tb_mercado_usuarios cascade;
--drop table tb_mercado_item_carrinho cascade;
--drop table tb_mercado_historico_compras cascade;
--drop table tb_mercado_estoque cascade;

insert into tb_mercado_produtos (name, description, category, price, rating, tags, image) values
('Produto 1', 'Descrição do Produto 1', 'Categoria X', 19.99, 4.5, '{"tag1", "tag2"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
('Produto 2', 'Descrição do Produto 2', 'Categoria Y', 29.99, 4.7, '{"tag3", "tag4"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
('Produto 3', 'Descrição do Produto 3', 'Categoria Z', 9.99, 4.0, '{"tag5", "tag6"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
('Produto 4', 'Descrição do Produto 4', 'Categoria X', 49.99, 4.8, '{"tag1", "tag3"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
('Produto 5', 'Descrição do Produto 5', 'Categoria Y', 59.99, 4.6, '{"tag2", "tag4"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png'));

-- exemplo de adminstrador padrao
insert into tb_mercado_usuarios (name, email, password, is_admin) values 
('admin', 'admin', MD5('admin'), true);

create table tb_mercado_historico_compras (
    id serial primary key,
    usuario_id int references tb_mercado_usuarios(id) on delete cascade not null,
    preco_total float not null,
    data_compra date not null,
    tipo_pagamento varchar(64) not null
);


-- nao usado --------------
create table tb_mercado_estoque (
    id serial primary key,
    produto_id int references tb_mercado_produtos(id) on delete cascade not null,
    quantidade int not null
);
-- -------------------------

create table tb_mercado_carteira_usuarios (
    id serial primary key,
    usuario_id int references tb_mercado_usuarios(id) on delete cascade not null,
    debito float not null,
    credito float not null,
    dinheiro float not null,
    limite float not null
);