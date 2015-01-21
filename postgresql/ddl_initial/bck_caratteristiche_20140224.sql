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
-- Name: caratteristiche; Type: TABLE; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE TABLE caratteristiche (
    id integer NOT NULL,
    articolo integer NOT NULL,
    unita_misura character varying(10) NOT NULL,
    scala integer DEFAULT 1 NOT NULL,
    qty numeric(9,2) DEFAULT 1 NOT NULL,
    descrizione character varying(30),
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.caratteristiche OWNER TO postgres;

--
-- Name: COLUMN caratteristiche.unita_misura; Type: COMMENT; Schema: cascino_schema; Owner: postgres
--

COMMENT ON COLUMN caratteristiche.unita_misura IS 'g
m
l
pz
mq
mc';


--
-- Name: COLUMN caratteristiche.scala; Type: COMMENT; Schema: cascino_schema; Owner: postgres
--

COMMENT ON COLUMN caratteristiche.scala IS '1000=k=10^3
';


--
-- Name: caratteristiche_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: postgres
--

CREATE SEQUENCE caratteristiche_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.caratteristiche_id_seq OWNER TO postgres;

--
-- Name: caratteristiche_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: postgres
--

ALTER SEQUENCE caratteristiche_id_seq OWNED BY caratteristiche.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY caratteristiche ALTER COLUMN id SET DEFAULT nextval('caratteristiche_id_seq'::regclass);


--
-- Data for Name: caratteristiche; Type: TABLE DATA; Schema: cascino_schema; Owner: postgres
--



--
-- Name: caratteristiche_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: postgres
--

SELECT pg_catalog.setval('caratteristiche_id_seq', 1, false);


--
-- Name: caratteristiche_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY caratteristiche
    ADD CONSTRAINT caratteristiche_prik PRIMARY KEY (id);


--
-- Name: fki_caratteristiche_articolo_fonk; Type: INDEX; Schema: cascino_schema; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_caratteristiche_articolo_fonk ON caratteristiche USING btree (articolo);


--
-- Name: updtime_caratteristiche_trg; Type: TRIGGER; Schema: cascino_schema; Owner: postgres
--

CREATE TRIGGER updtime_caratteristiche_trg BEFORE INSERT OR UPDATE ON caratteristiche FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- Name: caratteristiche_articolo_fonk; Type: FK CONSTRAINT; Schema: cascino_schema; Owner: postgres
--

ALTER TABLE ONLY caratteristiche
    ADD CONSTRAINT caratteristiche_articolo_fonk FOREIGN KEY (articolo) REFERENCES articoli(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

