-- =====================================================================
-- Schema inicial do projeto Agenda Telefonica
-- SGBD: MySQL 8.x
-- Executar com:
--   mysql -u root -p < database/schema.sql
-- =====================================================================

DROP DATABASE IF EXISTS agenda_telefonica;

CREATE DATABASE agenda_telefonica
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE agenda_telefonica;

CREATE TABLE contato (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL UNIQUE,
    telefone  VARCHAR(20)  NOT NULL,
    email     VARCHAR(150) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
