--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = cascino_schema, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: articoli_fornitori; Type: TABLE; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE TABLE articoli_fornitori (
    id integer NOT NULL,
    fornitore integer,
    codice character varying(20) NOT NULL,
    nome character varying(35) NOT NULL,
    descrizione character varying(500),
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.articoli_fornitori OWNER TO postgres;

--
-- Data for Name: articoli_fornitori; Type: TABLE DATA; Schema: cascino_schema; Owner: postgres
--



--
-- Name: articoli_fornitori_fornitore_codice_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articoli_fornitori
    ADD CONSTRAINT articoli_fornitori_fornitore_codice_unqk UNIQUE (fornitore, codice);


--
-- Name: articoli_fornitori_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articoli_fornitori
    ADD CONSTRAINT articoli_fornitori_prik PRIMARY KEY (id);


--
-- Name: updtime_articoli_fornitori_trg; Type: TRIGGER; Schema: cascino_schema; Owner: postgres
--

CREATE TRIGGER updtime_articoli_fornitori_trg BEFORE INSERT OR UPDATE ON articoli_fornitori FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: articoli_fornitori_fornitore_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY articoli_fornitori
    ADD CONSTRAINT articoli_fornitori_fornitore_fonk FOREIGN KEY (fornitore) REFERENCES fornitori(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

