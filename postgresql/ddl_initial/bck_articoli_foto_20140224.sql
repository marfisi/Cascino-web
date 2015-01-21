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
-- Name: articoli_foto; Type: TABLE; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE TABLE articoli_foto (
    id integer NOT NULL,
    articolo integer,
    articolo_fornitore integer,
    foto integer NOT NULL,
    ordinamento numeric(2,0) DEFAULT 1 NOT NULL,
    updtime timestamp with time zone,
    CONSTRAINT articoli_foto_almeno_un_articolo_articolo_fornitore_chk CHECK (((articolo IS NOT NULL) OR (articolo_fornitore IS NOT NULL)))
);


ALTER TABLE cascino_schema.articoli_foto OWNER TO postgres;

--
-- Name: articoli_foto_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: postgres
--

CREATE SEQUENCE articoli_foto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.articoli_foto_id_seq OWNER TO postgres;

--
-- Name: articoli_foto_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: postgres
--

ALTER SEQUENCE articoli_foto_id_seq OWNED BY articoli_foto.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY articoli_foto ALTER COLUMN id SET DEFAULT nextval('articoli_foto_id_seq'::regclass);


--
-- Data for Name: articoli_foto; Type: TABLE DATA; Schema: cascino_schema; Owner: postgres
--



--
-- Name: articoli_foto_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: postgres
--

SELECT pg_catalog.setval('articoli_foto_id_seq', 1, false);


--
-- Name: articoli_foto_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY articoli_foto
    ADD CONSTRAINT articoli_foto_prik PRIMARY KEY (id);


--
-- Name: fki_articoli_foto_articolo_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_articoli_foto_articolo_fonk ON articoli_foto USING btree (articolo);


--
-- Name: fki_articoli_foto_articolo_fornitore_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_articoli_foto_articolo_fornitore_fonk ON articoli_foto USING btree (articolo_fornitore);


--
-- Name: fki_articoli_foto_foto_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_articoli_foto_foto_fonk ON articoli_foto USING btree (foto);


--
-- Name: updtime_articoli_foto_trg; Type: TRIGGER; Schema: cascino_schema; Owner: postgres
--

CREATE TRIGGER updtime_articoli_foto_trg BEFORE INSERT OR UPDATE ON articoli_foto FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: articoli_foto_articolo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY articoli_foto
    ADD CONSTRAINT articoli_foto_articolo_fonk FOREIGN KEY (articolo) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: articoli_foto_articolo_fornitore_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY articoli_foto
    ADD CONSTRAINT articoli_foto_articolo_fornitore_fonk FOREIGN KEY (articolo_fornitore) REFERENCES articoli_fornitori(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: articoli_foto_foto_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY articoli_foto
    ADD CONSTRAINT articoli_foto_foto_fonk FOREIGN KEY (foto) REFERENCES foto(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

