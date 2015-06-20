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
-- Name: allegati; Type: TABLE; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

CREATE TABLE allegati (
    id integer NOT NULL,
    path character varying(100) DEFAULT 'c:\dev\allegati'::character varying,
    nome character varying(100) NOT NULL,
    descrizione character varying(200),
    updtime timestamp with time zone
);


ALTER TABLE cascino_schema.allegati OWNER TO cascino_user_db;

--
-- Name: allegati_id_seq; Type: SEQUENCE; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE SEQUENCE allegati_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cascino_schema.allegati_id_seq OWNER TO cascino_user_db;

--
-- Name: allegati_id_seq; Type: SEQUENCE OWNED BY; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER SEQUENCE allegati_id_seq OWNED BY allegati.id;


--
-- Name: id; Type: DEFAULT; Schema: cascino_schema; Owner: cascino_user_db
--

ALTER TABLE ONLY allegati ALTER COLUMN id SET DEFAULT nextval('allegati_id_seq'::regclass);


--
-- Data for Name: allegati; Type: TABLE DATA; Schema: cascino_schema; Owner: cascino_user_db
--

INSERT INTO allegati VALUES (1, 'c:\dev\allegati', 'n.d..pdf', 'n.d.', '2015-01-01 00:01:01+01');


--
-- Name: allegati_id_seq; Type: SEQUENCE SET; Schema: cascino_schema; Owner: cascino_user_db
--

SELECT pg_catalog.setval('allegati_id_seq', 1, false);


--
-- Name: allegati_path_nome_unqk; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY allegati
    ADD CONSTRAINT allegati_path_nome_unqk UNIQUE (path, nome);


--
-- Name: allegati_prik; Type: CONSTRAINT; Schema: cascino_schema; Owner: cascino_user_db; Tablespace: 
--

ALTER TABLE ONLY allegati
    ADD CONSTRAINT allegati_prik PRIMARY KEY (id);


--
-- Name: updtime_allegati_trg; Type: TRIGGER; Schema: cascino_schema; Owner: cascino_user_db
--

CREATE TRIGGER updtime_allegati_trg BEFORE INSERT OR UPDATE ON allegati FOR EACH ROW EXECUTE PROCEDURE updtime_column();


--
-- PostgreSQL database dump complete
--

