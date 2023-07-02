INSERT INTO paciente (cpf, nome, email, telefone, logradouro, bairro, cep, numero, complemento, cidade, uf, ativo)
VALUES ('1234567890', 'João', 'joao@example.com', '123456789', 'Rua A', 'Centro', '12345-000', '123', 'Apto 101', 'Cidade', 'UF', true),
('1234567800', 'luiz', 'luiz@example.com', '123456789', 'Rua A', 'Centro', '12345-000', '123', 'Apto 101', 'Cidade', 'UF', true),
('1234567810', 'Diego', 'diego@example.com', '123456789', 'Rua A', 'Centro', '12345-000', '123', 'Apto 101', 'Cidade', 'UF', true);

INSERT INTO medico (crm, nome, email, telefone, especialidade, logradouro, bairro, cep, numero, complemento, cidade, uf, ativo)
VALUES ('123456', 'Dr. João', 'joao@example.com', '123456789', 'CARDIOLOGIA', 'Rua A', 'Centro', '12345-000', '123', 'Apto 101', 'Cidade', 'UF', true),
('123856', 'Dr. Luis', 'luis@example.com', '123456789', 'CARDIOLOGIA', 'Rua A', 'Centro', '12345-000', '123', 'Apto 101', 'Cidade', 'UF', true),
('123756', 'Dr. danilo', 'danilo@example.com', '123456789', 'CARDIOLOGIA', 'Rua A', 'Centro', '12345-000', '123', 'Apto 101', 'Cidade', 'UF', true);
