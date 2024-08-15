CREATE TABLE usuario (
  id BIGINT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   cpf VARCHAR(255) NOT NULL,
   senha VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (id)
);

ALTER TABLE usuario ADD CONSTRAINT uc_usuario_cpf UNIQUE (cpf);

ALTER TABLE usuario ADD CONSTRAINT uc_usuario_email UNIQUE (email);