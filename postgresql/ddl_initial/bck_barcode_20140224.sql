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
-- Name: barcode; Type: TABLE; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE TABLE barcode (
    id integer NOT NULL,
    articolo integer,
    articolo_fornitore integer,
    code character varying(50) NOT NULL,
    descrizione character varying(20),
    updtime timestamp with time zone,
    CONSTRAINT barcode_almeno_un_articolo_articolo_fornitore_chk CHECK (((articolo IS NOT NULL) OR (articolo_fornitore IS NOT NULL)))
);


ALTER TABLE cascino_schema.barcode OWNER TO postgres;

--
-- Name: COLUMN barcode.descrizione; Type: COMMENT; Schema: cascino_schema; Owner: postgres
--

COMMENT ON COLUMN barcode.descrizione IS 'ean-13
qrcode';


--
-- Name: barcode_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: postgres
--

CREATE SEQUENCE barcode_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.barcode_id_seq OWNER TO postgres;

--
-- Name: barcode_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: postgres
--

ALTER SEQUENCE barcode_id_seq OWNED BY barcode.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY barcode ALTER COLUMN id SET DEFAULT nextval('barcode_id_seq'::regclass);


--
-- Data for Name: barcode; Type: TABLE DATA; Schema: cascino_schema; Owner: postgres
--



--
-- Name: barcode_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: postgres
--

SELECT pg_catalog.setval('barcode_id_seq', 1, false);


--
-- Name: barcode_code_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY barcode
    ADD CONSTRAINT barcode_code_unqk UNIQUE (code);


--
-- Name: barcode_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY barcode
    ADD CONSTRAINT barcode_prik PRIMARY KEY (id);


--
-- Name: fki_barcode_articolo_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_barcode_articolo_fonk ON barcode USING btree (articolo);


--
-- Name: fki_barcode_articolo_fornitore_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_barcode_articolo_fornitore_fonk ON barcode USING btree (articolo_fornitore);


--
-- Name: updtime_barcode_trg; Type: TRIGGER; Schema: cascino_schema; Owner: postgres
--

CREATE TRIGGER updtime_barcode_trg BEFORE INSERT OR UPDATE ON barcode FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: barcode_articolo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY barcode
    ADD CONSTRAINT barcode_articolo_fonk FOREIGN KEY (articolo) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: barcode_articolo_fornitore_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY barcode
    ADD CONSTRAINT barcode_articolo_fornitore_fonk FOREIGN KEY (articolo_fornitore) REFERENCES articoli_fornitori(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

