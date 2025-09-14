-- V2: Migrations para adicionar a coluna SEXO na tabela TB_CADASTRO_DE_NINJAS

ALTER TABLE TB_CADASTRO_DE_NINJAS
ADD COLUMN sexo VARCHAR(50)