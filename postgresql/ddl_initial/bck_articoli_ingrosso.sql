--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = articoli_parte_di, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: articoli_ingrosso; Type: TABLE; Schema: articoli_parte_di; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE articoli_ingrosso (
    id integer NOT NULL,
    articolo integer NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE articoli_parte_di.articoli_ingrosso OWNER TO cascino_user_db;

--
-- Name: articoli_ingrosso_id_seq; Type: SEQUENCE; Schema: articoli_parte_di; Owner: cascino_user_db
--

CREATE SEQUENCE articoli_ingrosso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE articoli_parte_di.articoli_ingrosso_id_seq OWNER TO cascino_user_db;

--
-- Name: articoli_ingrosso_id_seq; Type: SEQUENCE OWNED BY; Schema: articoli_parte_di; Owner: cascino_user_db
--

ALTER SEQUENCE articoli_ingrosso_id_seq OWNED BY articoli_ingrosso.id;


--
-- Name: id; Type: DEFAULT; Schema: articoli_parte_di; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_ingrosso ALTER COLUMN id SET DEFAULT nextval('articoli_ingrosso_id_seq'::regclass);

--
-- Name: articoli_ingrosso_id_seq; Type: SEQUENCE SET; Schema: articoli_parte_di; Owner: cascino_user_db
--

SELECT pg_catalog.setval('articoli_ingrosso_id_seq', 1, false);


--
-- Name: articoli_ingrosso_prik; Type: CONSTRAINT; Schema: articoli_parte_di; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY articoli_ingrosso
    ADD CONSTRAINT articoli_ingrosso_prik PRIMARY KEY (id);


--
-- Name: fki_articoli_ingrosso_articolo_fonk; Type: INDEX; Schema: articoli_parte_di; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_ingrosso_articolo_fonk ON articoli_ingrosso USING btree (articolo);


--
-- Name: updtime_articoli_ingrosso_trg; Type: TRIGGER; Schema: articoli_parte_di; Owner: cascino_user_db
--

CREATE TRIGGER updtime_articoli_ingrosso_trg BEFORE INSERT OR UPDATE ON articoli_ingrosso FOR EACH ROW EXECUTE PROCEDURE cascino_schema.updtime_column();


--
-- Name: articoli_ingrosso_articolo_fonk; Type: FK CONSTRAINT; Schema: articoli_parte_di; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_ingrosso
    ADD CONSTRAINT articoli_ingrosso_articolo_fonk FOREIGN KEY (articolo) REFERENCES cascino_schema.articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

