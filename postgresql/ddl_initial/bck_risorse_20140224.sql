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
-- Name: risorse; Type: TABLE; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE TABLE risorse (
    id integer NOT NULL,
    articolo integer,
    uri character varying(130) NOT NULL,
    descrizione character varying(50),
    tipo character varying(20),
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.risorse OWNER TO postgres;

--
-- Name: COLUMN risorse.uri; Type: COMMENT; Schema: cascino_schema; Owner: postgres
--

COMMENT ON COLUMN risorse.uri IS 'path completo';


--
-- Name: COLUMN risorse.tipo; Type: COMMENT; Schema: cascino_schema; Owner: postgres
--

COMMENT ON COLUMN risorse.tipo IS 'pdf
txt
xls
doc';


--
-- Name: risorse_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: postgres
--

CREATE SEQUENCE risorse_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.risorse_id_seq OWNER TO postgres;

--
-- Name: risorse_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: postgres
--

ALTER SEQUENCE risorse_id_seq OWNED BY risorse.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY risorse ALTER COLUMN id SET DEFAULT nextval('risorse_id_seq'::regclass);


--
-- Data for Name: risorse; Type: TABLE DATA; Schema: cascino_schema; Owner: postgres
--



--
-- Name: risorse_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: postgres
--

SELECT pg_catalog.setval('risorse_id_seq', 1, false);


--
-- Name: risorse_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY risorse
    ADD CONSTRAINT risorse_prik PRIMARY KEY (id);


--
-- Name: fki_risorse_articolo_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_risorse_articolo_fonk ON risorse USING btree (articolo);


--
-- Name: updtime_risorse_trg; Type: TRIGGER; Schema: cascino_schema; Owner: postgres
--

CREATE TRIGGER updtime_risorse_trg BEFORE INSERT OR UPDATE ON risorse FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: risorse_articolo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY risorse
    ADD CONSTRAINT risorse_articolo_fonk FOREIGN KEY (articolo) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

