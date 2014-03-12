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
-- Name: articoli_set; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE articoli_set (
    id integer NOT NULL,
    articolo integer NOT NULL,
    articolo_componente integer NOT NULL,
    qty integer DEFAULT 1 NOT NULL,
    ordinamento smallint DEFAULT 1 NOT NULL,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.articoli_set OWNER TO cascino_user_db;

--
-- Name: articoli_set_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE articoli_set_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.articoli_set_id_seq OWNER TO cascino_user_db;

--
-- Name: articoli_set_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE articoli_set_id_seq OWNED BY articoli_set.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_set ALTER COLUMN id SET DEFAULT nextval('articoli_set_id_seq'::regclass);


--
-- Data for Name: articoli_set; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--



--
-- Name: articoli_set_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('articoli_set_id_seq', 1, false);


--
-- Name: articoli_set_articolo_articolo_componente_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY articoli_set
    ADD CONSTRAINT articoli_set_articolo_articolo_componente_unqk UNIQUE (articolo, articolo_componente);


--
-- Name: articoli_set_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY articoli_set
    ADD CONSTRAINT articoli_set_prik PRIMARY KEY (id);


--
-- Name: fki_articoli_set_articolo_componente_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_set_articolo_componente_fonk ON articoli_set USING btree (articolo_componente);


--
-- Name: fki_articoli_set_articolo_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_set_articolo_fonk ON articoli_set USING btree (articolo);


--
-- Name: updtime_articoli_set_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_articoli_set_trg BEFORE INSERT OR UPDATE ON articoli_set FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: articoli_set_articolo_componente_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_set
    ADD CONSTRAINT articoli_set_articolo_componente_fonk FOREIGN KEY (articolo_componente) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: articoli_set_articolo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_set
    ADD CONSTRAINT articoli_set_articolo_fonk FOREIGN KEY (articolo) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

