CREATE TABLE transacao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   transaction DECIMAL NOT NULL,
   date datetime NOT NULL,
   type VARCHAR(255) NOT NULL,
   `description` VARCHAR(255) NOT NULL,
   user_id BIGINT NULL,
   CONSTRAINT pk_transacao PRIMARY KEY (id)
);

ALTER TABLE transacao ADD CONSTRAINT FK_TRANSACAO_ON_USER FOREIGN KEY (user_id) REFERENCES usuario (id);