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
-- Name: articoli; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE articoli (
    id integer NOT NULL,
    codice character varying(15) NOT NULL,
    produttore integer DEFAULT 1 NOT NULL,
    modello character varying(50) DEFAULT 'n.d.'::character varying NOT NULL,
    nome character varying(35) DEFAULT 'n.d.'::character varying NOT NULL,
    tipo integer DEFAULT 1 NOT NULL,
    descrizione character varying(70),
    articolo_fornitore integer,
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.articoli OWNER TO cascino_user_db;

--
-- Name: articoli_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE articoli_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.articoli_id_seq OWNER TO cascino_user_db;

--
-- Name: articoli_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE articoli_id_seq OWNED BY articoli.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli ALTER COLUMN id SET DEFAULT nextval('articoli_id_seq'::regclass);


--
-- Data for Name: articoli; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--



--
-- Name: articoli_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('articoli_id_seq', 1, false);


--
-- Name: articoli_codice_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_codice_unqk UNIQUE (codice);


--
-- Name: articoli_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_prik PRIMARY KEY (id);


--
-- Name: fki_articoli_articolo_fornitore_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_articolo_fornitore_fonk ON articoli USING btree (articolo_fornitore);


--
-- Name: fki_articoli_tipo_fonk; Type: INDEX; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE INDEX fki_articoli_tipo_fonk ON articoli USING btree (tipo);


--
-- Name: updtime_articoli_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_articoli_trg BEFORE INSERT OR UPDATE ON articoli FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: articoli_articolo_fornitore_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_articolo_fornitore_fonk FOREIGN KEY (articolo_fornitore) REFERENCES articoli_fornitori(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: articoli_produttore_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_produttore_fonk FOREIGN KEY (produttore) REFERENCES produttori(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: articoli_tipo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY articoli
    ADD CONSTRAINT articoli_tipo_fonk FOREIGN KEY (tipo) REFERENCES tipi(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

