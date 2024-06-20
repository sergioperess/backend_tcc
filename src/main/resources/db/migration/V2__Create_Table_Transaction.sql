CREATE TABLE transacao (
  id BINARY(16) NOT NULL,
   transaction DECIMAL NOT NULL,
   date datetime NOT NULL,
   type VARCHAR(255) NOT NULL,
   `description` VARCHAR(255) NOT NULL,
   CONSTRAINT pk_transacao PRIMARY KEY (id)
);