CREATE TABLE Pokemon (
    ID_pokemon INT PRIMARY KEY AUTO_INCREMENT,
    Nome_pokemon VARCHAR(100),
    Peso DECIMAL(5,2),
    Altura DECIMAL(5,2),
    Tipo1 VARCHAR(50),
    Tipo2 VARCHAR(50),
    Descricao TEXT,
    Regiao_origem VARCHAR(50),
    Evolucao_anterior VARCHAR(100),
    Evolucao_posterior VARCHAR(100),
    Imagem LONGBLOB
);