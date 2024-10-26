CREATE TABLE planejamento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   valor_planejado FLOAT NOT NULL,
   mes INT NOT NULL,
   ano INT NOT NULL,
   gasto_id BIGINT NULL,
   CONSTRAINT pk_planejamento PRIMARY KEY (id)
);

ALTER TABLE planejamento ADD CONSTRAINT FK_PLANEJAMENTO_ON_GASTO FOREIGN KEY (gasto_id) REFERENCES tipo_gasto (id);