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
-- Name: articoli_allegati; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE articoli_allegati (
    id integer NOT NULL,
    articolo integer,
    articolo_fornitore integer,
    allegato integer NOT NULL,
    ordinamento smallint DEFAULT 1 NOT NULL,
    updtime timestamp with time zone,
    CONSTRAINT articoli_allegati_almeno_un_articolo_articolo_fornitore_chk CHECK (((articolo IS NOT NULL) OR (articolo_fornitore IS NOT NULL)))
);


ALTER TABLE cascino_schema.articoli_allegati OWNER TO cascino_user_db;

--
-- Name: articoli_allegati_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE articoli_allegati_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.articoli_allegati_id_seq OWNER TO cascino_user_db;

--
-- Name: articoli_allegati_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE articoli_allegati_id_seq OWNED BY articoli_allegati.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_allegati ALTER COLUMN id SET DEFAULT nextval('articoli_allegati_id_seq'::regclass);


--
-- Data for Name: articoli_allegati; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--



--
-- Name: articoli_allegati_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('articoli_allegati_id_seq', 1, false);


--
-- Name: articoli_allegati_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY articoli_allegati
    ADD CONSTRAINT articoli_allegati_prik PRIMARY KEY (id);


--
-- Name: fki_articoli_allegati_allegato_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_allegati_allegato_fonk ON articoli_allegati USING btree (allegato);


--
-- Name: fki_articoli_allegati_articolo_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_allegati_articolo_fonk ON articoli_allegati USING btree (articolo);


--
-- Name: fki_articoli_allegati_articolo_fornitore_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_allegati_articolo_fornitore_fonk ON articoli_allegati USING btree (articolo_fornitore);


--
-- Name: updtime_articoli_allegati_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_articoli_allegati_trg BEFORE INSERT OR UPDATE ON articoli_allegati FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: articoli_allegati_allegato_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_allegati
    ADD CONSTRAINT articoli_allegati_allegato_fonk FOREIGN KEY (allegato) REFERENCES allegati(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: articoli_allegati_articolo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_allegati
    ADD CONSTRAINT articoli_allegati_articolo_fonk FOREIGN KEY (articolo) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: articoli_allegati_articolo_fornitore_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli_allegati
    ADD CONSTRAINT articoli_allegati_articolo_fornitore_fonk FOREIGN KEY (articolo_fornitore) REFERENCES articoli_fornitori(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

