create table tb_mercado_usuarios (
    id serial primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    fav_items integer[]
);

create table tb_mercado_produtos (
    id serial primary key,
    stock_quantity int not null,
    name varchar(255) not null,	
    description varchar(255) not null,
    supplier varchar(255) not null,
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

insert into tb_mercado_produtos (stock_quantity, name, description, supplier, category, price, rating, tags, image) values
(100, 'Produto 1', 'Descrição do Produto 1', 'Fornecedor A', 'Categoria X', 19.99, 4.5, '{"tag1", "tag2"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
(50, 'Produto 2', 'Descrição do Produto 2', 'Fornecedor B', 'Categoria Y', 29.99, 4.7, '{"tag3", "tag4"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
(200, 'Produto 3', 'Descrição do Produto 3', 'Fornecedor C', 'Categoria Z', 9.99, 4.0, '{"tag5", "tag6"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
(80, 'Produto 4', 'Descrição do Produto 4', 'Fornecedor A', 'Categoria X', 49.99, 4.8, '{"tag1", "tag3"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png')),
(60, 'Produto 5', 'Descrição do Produto 5', 'Fornecedor B', 'Categoria Y', 59.99, 4.6, '{"tag2", "tag4"}', pg_read_binary_file('/home/felipe/Mercado/src/assets/default.25.png.png'));