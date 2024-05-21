insert into usuarios (id, username, passwork, role) values
    (78,'ana@gmail.com', '$2a$12$6UJyOyR4.rHlcp8gB./Zf.ihDjRg9pdAJRCqnOT9rFS7y9rRlRr6a', 'ROLE_ADMIN'),
    (79,'paula@gmail.com', '$2a$12$6UJyOyR4.rHlcp8gB./Zf.ihDjRg9pdAJRCqnOT9rFS7y9rRlRr6a', 'ROLE_CLIENTE'),
    (80,'Babi@gmail.com', '$2a$12$6UJyOyR4.rHlcp8gB./Zf.ihDjRg9pdAJRCqnOT9rFS7y9rRlRr6a', 'ROLE_CLIENTE'),
    (81,'toby@gmail.com', '$2a$12$6UJyOyR4.rHlcp8gB./Zf.ihDjRg9pdAJRCqnOT9rFS7y9rRlRr6a', 'ROLE_CLIENTE');

insert into clientes(id, nome, cpf, id_usuario) values ( 10, 'Paula Silva', '33058164068', 79 ),
                                                       (20, 'Babi Silva', '31695880013', 80);



