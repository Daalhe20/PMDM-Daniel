SET NAMES utf8mb4;
CREATE DATABASE IF NOT EXISTS pmdm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pmdm;

DROP TABLE IF EXISTS pokemons;
CREATE TABLE pokemons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    anio VARCHAR(4) NOT NULL,
    imagen VARCHAR(255) NOT NULL
);

INSERT INTO pokemons (titulo, anio, imagen) VALUES
('Rojo Fuego', '2004', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/a/ac/latest/20211108122617/Car%C3%A1tula_de_Rojo_Fuego.png/315px-Car%C3%A1tula_de_Rojo_Fuego.png'),
('Leyendas Arceus', '2022', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/d/d7/latest/20210526132431/Car%C3%A1tula_Leyendas_Pok%C3%A9mon_Arceus.png/390px-Car%C3%A1tula_Leyendas_Pok%C3%A9mon_Arceus.png'),
('Blanco 2, Negro 2', '2012', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/4/44/latest/20140513223804/Pok%C3%A9mon_Black_2_%26_Pok%C3%A9mon_White_2_-_Super_Music_Collection.png/330px-Pok%C3%A9mon_Black_2_%26_Pok%C3%A9mon_White_2_-_Super_Music_Collection.png'),
('Platino', '2008', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/f/f4/latest/20211108120853/Car%C3%A1tula_Pok%C3%A9mon_Platino_%28ESP%29.png/375px-Car%C3%A1tula_Pok%C3%A9mon_Platino_%28ESP%29.png'),
('Cristal', '2000', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/3/3b/latest/20160715093139/Pokemon_Edici%C3%B3n_Cristal.jpg/375px-Pokemon_Edici%C3%B3n_Cristal.jpg'),
('Esmeralda', '2004', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/0/02/latest/20211108123052/Caratula_Esmeralda.jpg/375px-Caratula_Esmeralda.jpg'),
('Oro, Plata', '1999', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/a/a3/latest/20141222215832/Pok%C3%A9mon_HeartGold_%26_Pok%C3%A9mon_SoulSilver_-_Super_Music_Collection.png/270px-Pok%C3%A9mon_HeartGold_%26_Pok%C3%A9mon_SoulSilver_-_Super_Music_Collection.png'),
('Rubi, Zafiro', '2002', 'https://images.wikidexcdn.net/mwuploads/wikidex/thumb/d/d4/latest/20140410002735/Pok%C3%A9mon_Ruby_%26_Pok%C3%A9mon_Sapphire_-_Super_Music_Collection.png/270px-Pok%C3%A9mon_Ruby_%26_Pok%C3%A9mon_Sapphire_-_Super_Music_Collection.png');