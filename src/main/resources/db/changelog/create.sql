--liquibase formatted sql

--changeset viniciusfaria:0
CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;
--preconditions onFail:HALT onError:HALT

--changeset viniciusfaria:1
CREATE TABLE planet(
	id serial NOT NULL,
	nome text,
	clima text,
	terreno text,
	aparicoes int
);
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 select count(*) from information_schema.tables where table_schema = 'public' and table_name = 'planeta'