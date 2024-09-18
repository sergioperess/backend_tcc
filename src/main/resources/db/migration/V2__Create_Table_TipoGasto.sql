CREATE TABLE gasto (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NOT NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_gasto PRIMARY KEY (id)
);

ALTER TABLE gasto ADD CONSTRAINT FK_GASTO_ON_USER FOREIGN KEY (user_id) REFERENCES usuario (id);